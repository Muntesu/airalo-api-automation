package com.ocs.backend.api;

import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteClassListenerAdapter;
import com.ocs.backend.utils.Environment;
import com.ocs.backend.utils.PropertiesReader;
import lombok.Getter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.ocs.backend.utils.Environment.valueOfLabel;
import static com.ocs.backend.utils.PropertiesReader.getProperty;
import static io.restassured.RestAssured.*;

@Listeners({ExtentIReporterSuiteClassListenerAdapter.class})
public class BaseApiTest {

	@Getter
	private RestClient restClient;

	private String contentType;

	@BeforeClass(alwaysRun = true)
	public void suiteSetup() {
		setUpApi();
		restClient = new RestClient(baseURI, port, contentType);
	}

	private void setUpApi() {
		String environment = PropertiesReader.getSystemProperty("environment");
		setUpEnvApiConfig(valueOfLabel(environment));
	}

	private void setUpEnvApiConfig(Environment env) {
		baseURI = getProperty(env, "base.uri");
		port = Integer.parseInt(getProperty(env, "base.port"));
		contentType = getProperty(env, "base.content.type");
	}

}
