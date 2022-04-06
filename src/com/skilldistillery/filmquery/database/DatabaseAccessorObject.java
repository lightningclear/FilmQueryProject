package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT * FROM film where id = ?";
			String sql = "SELECT film.id, film.title, film.release_year, film.rating," 
					+ " film.description, language.name "
					+ " FROM film " 
					+ " Join language on film.language_id = language.id " 
					+ " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setLanguage(rs.getString("language.name"));
//				film.setRental_duration(rs.getDouble("rental_duration"));
//				film.setRental_rate(rs.getDouble("rental_rate"));
//				film.setLength(rs.getInt("length"));
//				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
//				film.setSpecial_features(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return film;
	}
	public Film findFilmById(String filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT * FROM film where id = ?";
			String sql = "SELECT film.id, film.title, film.release_year, film.rating," 
					+ " film.description, language.name "
					+ " FROM film " 
					+ " Join language on film.language_id = language.id " 
					+ " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setLanguage(rs.getString("language.name"));
//				film.setRental_duration(rs.getDouble("rental_duration"));
//				film.setRental_rate(rs.getDouble("rental_rate"));
//				film.setLength(rs.getInt("length"));
//				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
//				film.setSpecial_features(rs.getString("special_features"));
//				film.setActors(rs.getString("firstName"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		// TODO: Actor query for film id
		// Instantiate new Actor for each rs row,
		// add to actors list.
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// String sql = "SELECT * FROM film where id = ?";
			String sql = "SELECT id,first_name, last_name From actor where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		// TODO: Actor query for film id
		// Instantiate new Actor for each rs row,
		// add to actors list.

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			// String sql = "SELECT * FROM film where id = ?";
			String sql = " SELECT id,first_name, last_name " + " FROM actor join film_actor "
					+ " ON actor.id = film_actor.actor_id " + " WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
				actors.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actors;
	}

	public List<Film> searchFilmByKeyword(String filmId) {
		Film film = null;
		List<Film> films = new ArrayList<Film>();
		List<Actor> actors = new ArrayList<Actor>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT * FROM film where id = ?";
			String sql = "SELECT film.id, film.title, film.release_year, film.rating, "
					+ " film.description, language.name "
					+ " FROM film "
					+ " Join language on film.language_id = language.id "
					+ " WHERE title like ? OR description like ? ";

//			String sql = "SELECT id,first_name, last_name, film.id, film.title, film.release_year, film.rating, film.description, language.name From actor a film Join language on film.language_id = language.id where id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + filmId + "%");
			stmt.setString(2, "%" + filmId + "%");

//			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			boolean foundFilm = false;
			while (rs.next()) {
				foundFilm = true;
				film = new Film();
				Actor actor = new Actor();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setRelease_year(rs.getInt("release_year"));
				film.setLanguage(rs.getString("language.name"));
//				film.setRental_duration(rs.getDouble("rental_duration"));
//				film.setRental_rate(rs.getDouble("rental_rate"));
//				film.setLength(rs.getInt("length"));
//				film.setReplacement_cost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
//				film.setSpecial_features(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(film.getId()));
//				actor.setId(rs.getInt(1));
//				film.setActors(actors);
//				findActorById(1);
//				actor.setFirst_name(rs.getString("first_name"));
//				actor.setLast_name(rs.getString("last_name"));
//				actors.add(actor);
				films.add(film);

			}
			if (!foundFilm) {
				System.out.println("The film could not be found, please try again");
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return films;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not found.");
			throw new RuntimeException("Unable to load MYSQL driver class");

		}
	}
}
