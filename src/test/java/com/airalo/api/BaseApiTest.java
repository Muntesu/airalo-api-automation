package com.airalo.api;

import com.airalo.api.client.RestClient;
import com.airalo.api.pojo.auth.AuthResponse;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import lombok.Getter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.airalo.api.configuration.ConfigurationManager.getConfig;
import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.SC_OK;

public class BaseApiTest {

	@Getter
	private RestClient restClient;

	private String contentType;

	protected static ExtentReports extent;
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@BeforeSuite(alwaysRun = true)
	public void setupExtentReports() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
			"test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@BeforeClass(alwaysRun = true)
	public void suiteSetup() {
		setUpEnvApiConfig();
		restClient = new RestClient(baseURI, port, contentType, Map.of());
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		ExtentTest test = extentTest.get();

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped: " + result.getThrowable());
		}
	}

	@AfterSuite(alwaysRun = true)
	public void flushExtentReports() {
		if (extent != null) {
			extent.flush();
		}
	}

	protected void createTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTest.set(test);
	}

	private void setUpEnvApiConfig() {
		baseURI = getConfig().baseUri();
		port = getConfig().httpPort();
		contentType = getConfig().contentType();
	}

	public String getAccessToken() {
		var tokenPath = getConfig().tokenUri();
		Map<String, String> tokenHeaders = Map.of("Accept", "application/json");
		Map<String, String> formParams = Map.of(
			"client_id", getConfig().clientId(),
			"client_secret", getConfig().clientSecret(),
			"grant_type", "client_credentials"
		);

		Response response = getRestClient().post(tokenPath, tokenHeaders, formParams);
		response.then().statusCode(SC_OK);
		AuthResponse authResponse = getRestClient().getResponseAsPojo(response, AuthResponse.class);
		return authResponse.getData().getAccessToken();
	}

	public Map<String, String>  getHeaders() {
		return Map.of("Accept", "application/json",
			"Authorization", "Bearer " + getAccessToken());
	}

}
