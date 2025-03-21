package com.airalo.api;

import static com.airalo.api.configuration.ConfigurationManager.property;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.airalo.api.dto.OrderDTO;
import com.airalo.api.pojo.orders.SimPurchaseResponse;
import com.airalo.api.pojo.sims.SimData;
import com.airalo.api.pojo.sims.SimListResponse;
import io.restassured.response.Response;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2ETest extends BaseApiTest {

    @Test(groups = "e2e", dataProvider = "orderESim")
    public void checkESimOrderTest(String quantity, String type, String packageId,
        String description, String brand, String email) {

        createTest("Check eSim Purchase Test");
        var ordersPath = property().ordersUri();

        Map<String, String> formParams = Map.of(
            "quantity", quantity,
            "package_id", packageId,
            "type", type,
            "description", description,
            "brand", brand,
            "email", email
        );

        Response response = getRestClient().post(ordersPath, getHeaders(), formParams);
        response.then().statusCode(SC_OK);

        SimPurchaseResponse simPurchaseResponse = getRestClient().getResponseAsPojo(response,
            SimPurchaseResponse.class);

        OrderDTO orderDTO = getOrderDTO(simPurchaseResponse);
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(orderDTO.getPackageId()).as("The package_id is not correct")
                .isEqualTo(packageId);
            softly.assertThat(orderDTO.getDescription()).as("The description is not correct")
                .isEqualTo(description);
            softly.assertThat(orderDTO.getType()).as("The type is not correct")
                .isEqualTo(type);
            softly.assertThat(orderDTO.getQuantity()).as("The quantity is not correct")
                .isEqualTo(Integer.valueOf(quantity));
        }
        Set<String> iccids = simPurchaseResponse.getData().getSims().stream()
            .map(SimData::getIccid)
            .collect(Collectors.toSet());

        var simsPath = property().simsUri();
        for (String iccid : iccids) {
            Map<String, String> queryParams = Map.of("filter[iccid]", iccid);

            Response simsResponse = getRestClient().get(simsPath, getHeaders(), queryParams);
            simsResponse.then().statusCode(SC_OK);
            SimListResponse simListResponse = getRestClient().getResponseAsPojo(simsResponse,
                SimListResponse.class);
            assertThat(simListResponse.getData().size()).as("The size is not correct").isEqualTo(1);
            assertThat(simListResponse.getData().getFirst().getIccid()).as("The iccid is not correct").isEqualTo(iccid);
        }
    }

    @DataProvider(name = "orderESim")
    public Object[][] orderESimDataProvider() {
        return new Object[][]{{"6", "sim", "merhaba-7days-1gb", "6 sim merhaba-7days-1gb",
            "moldcell", "muntesu@gmail.com"}};
    }

    private OrderDTO getOrderDTO(SimPurchaseResponse simListResponse) {
        return OrderDTO.builder().packageId(simListResponse.getData().getPackageId())
            .quantity(simListResponse.getData().getQuantity())
            .description(simListResponse.getData().getDescription())
            .type(simListResponse.getData().getType()).build();
    }
}
