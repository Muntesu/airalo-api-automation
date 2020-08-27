package com.ocs.backend.api;

import com.ocs.backend.db.DBConnector;
import com.ocs.backend.dto.Athlete;
import com.ocs.backend.dto.Game;
import com.ocs.backend.dto.Result;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ocs.backend.api.Endpoint.*;
import static com.ocs.backend.utils.StringUtils.formatPath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalTest extends BaseApiTest {
	private final DBConnector db = new DBConnector();

	@BeforeClass(alwaysRun = true)
	public void setup() {
		db.connect();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		db.closeConnection();
	}

	@Test(groups = {"functional", "positive"}, dataProvider = "athletesData")
	public void checkAthleteByIdTest(String athleteId, int status, Athlete athleteFromDB) {
		Response response = getRestClient()
				.get(formatPath(AN_ATHLETE.getLabel(), athleteId));
		response.then().statusCode(status);
		Athlete athlete = getRestClient().getResponseAsPojo(response, Athlete.class);
		assertThat(athlete).as("Different athlete data returned").isEqualTo(athleteFromDB);
	}

	@Test(groups = {"functional", "positive"})
	public void checkAllAthletesTest() {
		Response response = getRestClient().get(ATHLETES.getLabel());
		response.then().statusCode(SC_OK);
		Athlete[] athletes = getRestClient().getResponseAsPojo(response, Athlete[].class);
		assertThat(athletes).as("No athletes returned").hasSizeGreaterThan(1);
		Athlete athleteFromDB = db.selectAthletesById(String.valueOf(athletes[0].getAthleteId()));
		assertThat(athletes[0]).as("Different athlete data returned").isEqualTo(athleteFromDB);
	}

	@Test(groups = {"functional", "positive"})
	public void checkAllGamesTest() {
		Response response = getRestClient().get(GAMES.getLabel());
		response.then().statusCode(SC_OK);
		Game[] games = getRestClient().getResponseAsPojo(response, Game[].class);
		assertThat(games).as("No games returned").hasSizeGreaterThan(1);
		Game gameFromDb = db.selectGameById(String.valueOf(games[0].getGameId()));
		assertThat(games[0]).as("Different games data returned").isEqualTo(gameFromDb);
	}

	// TODO it is possible to implement this using data provider
	@Test(groups = {"functional", "positive"})
	public void checkAthletesByGameTest() {
		Response response = getRestClient().get(GAMES.getLabel());
		response.then().statusCode(SC_OK);
		Game[] games = getRestClient().getResponseAsPojo(response, Game[].class);
		assertThat(games).as("No games returned").hasSizeGreaterThan(1);
		for (Game game : games) {
			response = getRestClient().get(formatPath(ATHLETE_IN_GAME.getLabel(), String.valueOf(game.getGameId())));
			Athlete[] athletes = getRestClient().getResponseAsPojo(response, Athlete[].class);
			if (athletes.length != 0) {
				Athlete athleteFromDB = db.selectAthletesById(String.valueOf(athletes[0].getAthleteId()));
				assertThat(athletes[0]).as("Different athlete data returned").isEqualTo(athleteFromDB);
				break;
			}
		}
	}

	// TODO it is possible to make it more generic as in checkAthletesByGameTest()
	@Test(groups = {"functional", "positive"}, dataProvider = "resultsData")
	public void checkResultsByAthleteTest(String athleteId, String gameId, int status, Result resultFromDb) {
		Response response = getRestClient().get(GAMES.getLabel());
		response.then().statusCode(status);
		Game[] games = getRestClient().getResponseAsPojo(response, Game[].class);
		assertThat(games).as("No games returned").hasSizeGreaterThan(1);
		for (Game game : games) {
			String city;
			long year;
			if (game.getGameId().equals(Long.getLong(gameId))) {
				city = game.getCity();
				year = game.getYear();
				response = getRestClient().get(formatPath(ATHLETE_RESULTS.getLabel(), String.valueOf(athleteId)));
				Result[] results = getRestClient().getResponseAsPojo(response, Result[].class);
				for (Result res: results) {
					if (city.equalsIgnoreCase(res.getCity())) {
						assertThat(res.getYear()).as("Incorrect year for the olympic game").isEqualTo(year);
						assertThat(res.getGold()).as("Incorrect gold medals count").isEqualTo(resultFromDb.getGold());
						assertThat(res.getSilver()).as("Incorrect silver medals count").isEqualTo(resultFromDb.getSilver());
						assertThat(res.getBronze()).as("Incorrect bronze medals count").isEqualTo(resultFromDb.getBronze());
					}
				}
			}
		}
	}


	@DataProvider(name = "athletesData")
	public Object[][] athletesDataProvider() {
		return new Object[][]{{String.valueOf(2), SC_OK, db.selectAthletesById(String.valueOf(11))}};
	}

	@DataProvider(name = "resultsData")
	public Object[][] resultsDataProvider() {
		return new Object[][]{{String.valueOf(17), String.valueOf(2), SC_OK,
				db.selectResultsByAthleteGameIds(String.valueOf(17), String.valueOf(2))}};
	}
}
