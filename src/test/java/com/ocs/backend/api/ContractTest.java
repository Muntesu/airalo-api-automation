package com.ocs.backend.api;

import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ocs.backend.api.Endpoint.AN_ATHLETE;
import static com.ocs.backend.api.Endpoint.GAMES;
import static com.ocs.backend.utils.StringUtils.formatPath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContractTest extends BaseApiTest {
	@Test(groups = "contract", dataProvider = "athletesIds")
	public void randomAthleteApiContractTest(String athleteId) {
		getRestClient()
				.get(formatPath(AN_ATHLETE.getLabel(), athleteId))
				.then()
				.body(matchesJsonSchemaInClasspath("schemas/athletes_json_schema.json"));
	}

	@Test(groups = "contract")
	public void allGamesApiContractTest() {
		getRestClient()
				.get(GAMES.getLabel())
				.then()
				.body(matchesJsonSchemaInClasspath("schemas/games_json_schema.json"));
	}

	//TODO - add a generic data provider:
	//It could be used by creating a specific entry with a specific ID using POST method

	@DataProvider(name = "athletesIds")
	public Object[][] athleteDataProvider() {
		return new Object[][]{{"11"}};
	}

	//TODO - add contract tests for the remaining API except /athletes/:id/photo
}
