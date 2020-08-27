package com.ocs.backend.db;

import com.ocs.backend.dto.Athlete;
import com.ocs.backend.dto.Game;
import com.ocs.backend.dto.Result;
import com.ocs.backend.utils.PropertiesReader;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class DBConnector {
	private Connection conn = null;

	// TODO add generic methods for executing the sql queries with and without params

	public Athlete selectAthletesById(String athleteId) {
		final String sql = "SELECT * FROM Athlete WHERE athlete_id = ?";
		final Athlete athlete = new Athlete();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, athleteId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				athlete.withAthleteId(String.valueOf(rs.getLong("athlete_id")))
						.withDateOfBirth(rs.getString("date_of_birth"))
						.withName(rs.getString("name"))
						.withSurname(rs.getString("surname"))
						.withHeight(rs.getLong("height"))
						.withWeight(rs.getLong("weight"))
						.withPhotoId(rs.getLong("photo_id"))
						.withBio(rs.getString("bio"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return athlete;
	}

	public Game selectGameById(String gameId) {
		final String sql = "SELECT * FROM GAME WHERE game_id = ?";
		final Game game = new Game();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, gameId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				game.withGameId(rs.getLong("game_id"))
						.withCity(rs.getString("city"))
						.withYear(rs.getLong("year"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return game;
	}

	public Result selectResultsByAthleteGameIds(String athleteId, String gameId) {
		final String sql = "SELECT * FROM ATHLETERESULT WHERE athlete_id = ? and game_id = ?";
		final Result result = new Result();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, athleteId);
			pstmt.setString(2, gameId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.withGold(rs.getLong("gold"))
						.withBronze(rs.getLong("bronze"))
						.withSilver(rs.getLong("silver"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public void connect() {
		String url = PropertiesReader.getSystemProperty("db.connect");
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}


}
