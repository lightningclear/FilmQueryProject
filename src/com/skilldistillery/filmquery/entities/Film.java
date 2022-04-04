package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

//classes the represent data entries

// build out film class with app fields

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer release_year;
	private String language;
	private double rental_duration;
	private double rental_rate;
	private Integer length;
	private double replacement_cost;
	private String rating;
	private String special_features;
	
	private List<Actor> actors;

	public Film() {
		super();
	}

	public Film(int id, String title, String description, Integer release_year, String language, int rental_duration,
			double rental_rate, Integer length, double replacement_cost, String rating, String special_features) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language = language;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
		this.special_features = special_features;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(Integer release_year) {
		this.release_year = release_year;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public double getRental_duration() {
		return rental_duration;
	}

	public void setRental_duration(double rental_duration) {
		this.rental_duration = rental_duration;
	}

	public double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacement_cost() {
		return replacement_cost;
	}

	public void setReplacement_cost(double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecial_features() {
		return special_features;
	}

	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, id, language, length, rating, release_year, rental_duration,
				rental_rate, replacement_cost, special_features, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(language, other.language) && Objects.equals(length, other.length)
				&& Objects.equals(rating, other.rating) && Objects.equals(release_year, other.release_year)
				&& Double.doubleToLongBits(rental_duration) == Double.doubleToLongBits(other.rental_duration)
				&& Double.doubleToLongBits(rental_rate) == Double.doubleToLongBits(other.rental_rate)
				&& Double.doubleToLongBits(replacement_cost) == Double.doubleToLongBits(other.replacement_cost)
				&& Objects.equals(special_features, other.special_features) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		String output = "Film: \n\n Id: " + id
				+ "	\n\n Title: " + title
				+ "	\n\n Description: " + description
				+ "	\n\n Release_year: " + release_year
				+ "	\n\n Language: " + language
				+ " \n\n Rating: " + rating
				+ " \n\n Actors: " + getActors();
		return output;
	}

}
