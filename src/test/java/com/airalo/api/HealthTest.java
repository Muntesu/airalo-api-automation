package com.airalo.api;

import static com.airalo.api.configuration.ConfigurationManager.getConfig;
import static com.airalo.api.logger.LoggerManager.logInfo;
import static org.apache.http.HttpStatus.SC_OK;

import java.util.Map;
import org.testng.annotations.Test;

public class HealthTest extends BaseApiTest {

    @Test(groups = "health")
    public void allSimsApiHealthTest() {
        createTest("Health Check Sims API Test");
        logInfo("Health Check Sims API Test");
        var simsPath = getConfig().simsUri();
        getRestClient().get(simsPath, getHeaders(), Map.of()).then().statusCode(SC_OK);
    }

    @Test(groups = "health")
    public void allOrdersApiHealthTest() {
        createTest("Health Check Orders API Test");
        logInfo("Health Check Orders API Test");
        var orderPath = getConfig().ordersUri();
        getRestClient().get(orderPath, getHeaders(), Map.of()).then().statusCode(SC_OK);
    }
}
