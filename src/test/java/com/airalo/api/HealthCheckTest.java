package com.airalo.api;

import static com.airalo.api.configuration.ConfigurationManager.property;
import static org.apache.http.HttpStatus.SC_OK;

import com.airalo.api.pojo.sims.SimListResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.annotations.Test;

public class HealthCheckTest extends BaseApiTest {

    @Test(groups = "health")
    public void allSimsApiHealthCheckTest() {
        createTest("Health Check Sims API Test");
        var simsPath = property().simsUri();
        getRestClient().get(simsPath, getHeaders(), null).then().statusCode(SC_OK);
    }

    @Test(groups = "health")
    public void allOrdersApiHealthCheckTest() {
        createTest("Health Check Orders API Test");
        var orderPath = property().ordersUri();
        getRestClient().get(orderPath, getHeaders(), null).then().statusCode(SC_OK);
    }
}
