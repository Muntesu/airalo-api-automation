package com.ocs.backend.api;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ocs.backend.api.Endpoint.*;
import static com.ocs.backend.utils.StringUtils.formatPath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

public class HealthCheckTest extends BaseApiTest {
	@Test(groups = "health")
	public void allAthletesApiHealthCheckTest() {
		getRestClient()
				.get(ATHLETES.getLabel())
				.then()
				.statusCode(SC_OK);
	}

	@Test(groups = "health", dataProvider = "athletesIds")
	public void randomAthleteApiHealthCheckTest(String athleteId) {
		getRestClient()
				.get(formatPath(AN_ATHLETE.getLabel(), athleteId))
				.then()
				.statusCode(SC_OK);
	}

	@Test(groups = "health", dataProvider = "athletesIds")
	public void randomAthletePhotoApiHealthCheckTest(String athleteId) {
		getRestClient()
				.get(formatPath(ATHLETE_PHOTO.getLabel(), athleteId))
				.then()
				.statusCode(SC_OK);
	}

	@Test(groups = "health", dataProvider = "athletesIds")
	public void randomAthleteResultsApiHealthCheckTest(String athleteId) {
		getRestClient()
				.get(formatPath(ATHLETE_RESULTS.getLabel(), athleteId))
				.then()
				.statusCode(SC_OK);
	}

	@Test(groups = "health")
	public void allGamesApiHealthCheckTest() {
		getRestClient()
				.get(GAMES.getLabel())
				.then()
				.statusCode(SC_OK);
	}

	@Test(groups = "health", dataProvider = "gameIds")
	public void randomGameAthletesApiHealthCheckTest(String gameId) {
		getRestClient()
				.get(formatPath(ATHLETE_IN_GAME.getLabel(), gameId))
				.then()
				.statusCode(SC_OK);
	}

	//TODO - add a generic data provider:
	//It could be used by creating a specific entry with a specific ID using POST method

	@DataProvider(name = "athletesIds")
	public Object[][] athleteDataProvider() {
		return new Object[][]{{"8"}};
	}

	@DataProvider(name = "gameIds")
	public Object[][] gameDataProvider() {
		return new Object[][]{{"2"}};
	}
}
