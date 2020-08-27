package com.ocs.backend.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
@Getter

// TODO make RestClient thread safe using ThreadLocal

public class RestClient {

	private String restService; // in case there will be one more service added
	private int port;
	private String contentType;

	public RequestSpecification buildRequestSpec() {
		// enable logging only for the failed validations
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		return new RequestSpecBuilder().build()
				.baseUri(restService)
				.port(port)
				.contentType(contentType);
	}

	public Response get(String path) {
		return given(buildRequestSpec()).get(path);
	}

	public Response post(String path) {
		return given(buildRequestSpec()).post(path);
	}

	public Response put(String path) {
		return given(buildRequestSpec()).get(path);
	}

	public Response patch(String path) {
		return given(buildRequestSpec()).post(path);
	}

	public Response delete(String path) {
		return given(buildRequestSpec()).delete(path);
	}

	public String getResponseAsString(Response response) {
		return response.getBody().asString();
	}

	public  <T> T getResponseAsPojo(Response response, Class<T> responseClass) {
		return response
				.then()
				.statusCode(200)
				.extract()
				.as(responseClass);
	}

}
