package com.ocs.backend.api;

import com.ocs.backend.dto.Athlete;
import com.ocs.backend.dto.Game;
import com.ocs.backend.dto.Result;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.ocs.backend.api.Endpoint.*;
import static com.ocs.backend.utils.StringUtils.formatPath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class E2ETest extends BaseApiTest {
	@Test(groups = "e2e", dataProvider = "athleteData")
	public void checkAthleteDataOnAllApis(String name, String surname) {
		log.debug("Returning all athletes data");
		Response response = getRestClient().get(ATHLETES.getLabel());
		response.then().statusCode(SC_OK);
		Athlete[] athletes = getRestClient().getResponseAsPojo(response, Athlete[].class);
		String athleteId = "";
		Athlete givenAthlete = new Athlete();

		log.debug("Looking for the athlete with provided name and last name");
		for (Athlete athlete : athletes) {
			if (athlete.getName().equalsIgnoreCase(name) &&
					athlete.getSurname().equalsIgnoreCase(surname)) {
				athleteId = athlete.getAthleteId();
				givenAthlete = athlete;
				break;
			}
		}

		log.debug("Returning the athlete by provided athlete id");
		response = getRestClient().get(formatPath(AN_ATHLETE.getLabel(), athleteId));
		response.then().statusCode(SC_OK);
		Athlete athlete = getRestClient().getResponseAsPojo(response, Athlete.class);

		log.debug("Check the returned athlete data corresponds to data from previous endpoint");
		assertThat(athlete).as("Different athlete data returned").isEqualTo(givenAthlete);

		log.debug("Make sure the athlete has a photo");
		response = getRestClient().get(formatPath(ATHLETE_PHOTO.getLabel(), athleteId));
		response.then().statusCode(SC_OK);
		// TODO check if there is a proof of truth photo storage where the data could be compared with
		assertThat(getRestClient().getResponseAsString(response)).as("No photo provided").isNotNull();

		log.debug("Check the games the athlete participated in and their results");
		response = getRestClient().get(formatPath(ATHLETE_RESULTS.getLabel(), String.valueOf(athleteId)));
		response.then().statusCode(SC_OK);
		Result[] results = getRestClient().getResponseAsPojo(response, Result[].class);
		final Map<String, Long> olympicGames = new HashMap<>();
		for (Result result : results) {
			olympicGames.put(result.getCity(), result.getYear());
			//TODO add a data provider to check the medals for each year
		}

		log.debug("Check the city and the year of the games the athlete took part in");
		response = getRestClient().get(GAMES.getLabel());
		response.then().statusCode(SC_OK);
		Game[] games = getRestClient().getResponseAsPojo(response, Game[].class);

		final List<Long> gamesIds = new ArrayList<>();
		// Improve the solution by using lambda
		for (Game game : games) {
			for (Map.Entry<String, Long> oGame : olympicGames.entrySet()) {
				if (game.getCity().equalsIgnoreCase(oGame.getKey()) &&
						game.getYear().equals(oGame.getValue())) {
					gamesIds.add(game.getGameId());
				}
			}
		}
		assertThat(gamesIds).as("Incorrect count of games").hasSize(olympicGames.size());

		log.debug("Check if the athlete participated in the previously listed games");
		for (Long gameId : gamesIds) {
			response = getRestClient().get(formatPath(ATHLETE_IN_GAME.getLabel(), String.valueOf(gameId)));
			Athlete[] aths = getRestClient().getResponseAsPojo(response, Athlete[].class);
			log.debug("Get the ids of athletes in these games");
			List<Integer> athletesIds = Arrays.stream(aths)
					.map(Athlete::getAthleteId)
					.map(Integer::parseInt)
					.collect(Collectors.toList());
			assertThat(athletesIds).as("Athlete id is not present in the list").contains(Integer.parseInt(athleteId));
		}
	}

	@DataProvider(name = "athleteData")
	public Object[][] athletesDataProvider() {
		return new Object[][]{{"Michael", "Phelps"}};
	}
}
