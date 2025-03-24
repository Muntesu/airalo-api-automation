package com.airalo.api;

import static com.airalo.api.configuration.ConfigurationManager.getConfig;
import static com.airalo.api.logger.LoggerManager.logInfo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;

import java.util.Map;
import org.testng.annotations.Test;

public class ContractTest extends BaseApiTest {

    @Test(groups = "contract")
    public void allSimsApiContractTest() {
        createTest("Contract Sims API Test");
        logInfo("Contract Sims API Test");

        var simsPath = getConfig().simsUri();
        getRestClient().get(simsPath, getHeaders(), Map.of()).then().statusCode(SC_OK)
            .body(matchesJsonSchemaInClasspath("schemas/sims_schema.json"));
    }

    @Test(groups = "contract")
    public void allOrdersApiContractTest() {
        createTest("Contract Orders API Test");
        logInfo("Contract Orders API Test");

        var orderPath = getConfig().ordersUri();
        getRestClient().get(orderPath, getHeaders(), Map.of()).then().statusCode(SC_OK)
            .body(matchesJsonSchemaInClasspath("schemas/orders_schema.json"));
    }
}
