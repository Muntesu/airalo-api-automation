package com.airalo.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record RestClient(String restService, int port, String contentType,
                         Map<String, String> defaultHeaders) {

    private RequestSpecification buildRequestSpec() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        return new RequestSpecBuilder()
            .setBaseUri(restService)
            .setPort(port)
            .setContentType(contentType)
            .addHeaders(defaultHeaders != null ? defaultHeaders : Collections.emptyMap())
            .build();
    }

    private RequestSpecification givenRequest(Map<String, String> headers) {
        return given()
            .spec(buildRequestSpec())
            .headers(headers != null ? headers : Collections.emptyMap());
    }

    public Response get(String path, Map<String, String> headers, Map<String, ?> queryParams) {
        return givenRequest(headers)
            .queryParams(queryParams != null ? queryParams : Collections.emptyMap())
            .get(path);
    }

    public Response post(String path, Map<String, String> headers, Object body) {
        return givenRequest(headers)
            .body(body)
            .post(path);
    }

    public Response put(String path, Map<String, String> headers, Object body) {
        return givenRequest(headers)
            .body(body)
            .put(path);
    }

    public Response patch(String path, Map<String, String> headers, Object body) {
        return givenRequest(headers)
            .body(body)
            .patch(path);
    }

    public Response delete(String path, Map<String, String> headers, Map<String, ?> queryParams) {
        return givenRequest(headers)
            .queryParams(queryParams != null ? queryParams : Collections.emptyMap())
            .delete(path);
    }

    public String getResponseAsString(Response response) {
        return response.getBody().asString();
    }

    public <T> T getResponseAsPojo(Response response, Class<T> responseClass,
        int expectedStatusCode) {
        return response
            .then()
            .statusCode(expectedStatusCode)
            .extract()
            .as(responseClass);
    }

    public <T> T getResponseAsPojo(Response response, Class<T> responseClass) {
        return response
            .then()
            .extract()
            .as(responseClass);
    }
}
