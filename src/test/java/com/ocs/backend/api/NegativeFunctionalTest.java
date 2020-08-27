package com.ocs.backend.api;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ocs.backend.api.Endpoint.*;
import static com.ocs.backend.utils.StringUtils.formatPath;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.apache.http.HttpStatus.*;

public class NegativeFunctionalTest extends BaseApiTest {
	@Test(groups = {"functional", "negative"}, dataProvider = "invalidAthletesData")
	public void checkInvalidAthleteIdTest(String athleteId, int status) {
		getRestClient()
				.get(formatPath(AN_ATHLETE.getLabel(), athleteId))
				.then()
				.statusCode(status);
	}

	@Test(groups = {"functional", "negative"}, dataProvider = "invalidAthletesData")
	public void checkPhotoInvalidAthleteIdTest(String athleteId, int status) {
		getRestClient()
				.get(formatPath(ATHLETE_PHOTO.getLabel(), athleteId))
				.then()
				.statusCode(status);
	}

	@Test(groups = {"functional", "negative"}, dataProvider = "invalidAthletesData")
	public void checkResultsInvalidAthleteIdTest(String athleteId, int status) {
		getRestClient()
				.get(formatPath(ATHLETE_RESULTS.getLabel(), athleteId))
				.then()
				.statusCode(status);
	}

	@Test(groups = {"functional", "negative"}, dataProvider = "invalidGamesData")
	public void checkAthletesInvalidGamesIdTest(String gameId, int status) {
		getRestClient()
				.get(formatPath(ATHLETE_IN_GAME.getLabel(), gameId))
				.then()
				.statusCode(status);
	}

	@DataProvider(name = "invalidAthletesData")
	public Object[][] athletesDataProvider() {
		return new Object[][]{{String.valueOf(MAX_VALUE), SC_NOT_FOUND},
				{String.valueOf(MIN_VALUE), SC_BAD_REQUEST},
				{"A", SC_BAD_REQUEST}};
	}

	@DataProvider(name = "invalidGamesData")
	public Object[][] gamesDataProvider() {
		return new Object[][]{{String.valueOf(MIN_VALUE), SC_BAD_REQUEST},
				{"A", SC_BAD_REQUEST}};
	}
}
