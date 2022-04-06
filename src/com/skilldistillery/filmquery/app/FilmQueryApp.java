package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		Actor actor = db.findActorById(1);
		System.out.println(actor);
		List<Actor> actors = db.findActorsByFilmId(1);
		System.out.println(actors);
		for (Actor a : film.getActors()) {
			System.out.println(a.getFirst_name() + " " + a.getLast_name());
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		// TODO: Start menu, input, etc.
		System.out.println("-----------------Instructions-----------------");
		System.out.println("Please enter the Film you would like to look up");
		System.out.println("You can either look up a Film by its Id or by");
		System.out.println("entering a keyword you would like to search");
		System.out.println("otherwise enter 3 to exit the program");
		System.out.println("-----------------------------------------------");
		boolean menu = true;

		while (menu) {
			System.out.println("\n 1. Enter a Film ID.");
			System.out.println("\n 2. Search a Film by Keyword.");
			System.out.println("\n 3. Exit.");
			System.out.println("Enter your choice here: ");

			int userInput = input.nextInt();
			switch (userInput) {
			case 1:
				System.out.println("Enter Id: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				if (film != null) {
					System.out.println(film);
				} else {
					System.out.println("No Id Found, please enter a valid Film Id");
				}
				break;
			case 2:
				System.out.println("Please enter a Keyword to search: ");
				String keyword = input.next();
				List<Film> films = db.searchFilmByKeyword(keyword);
				for (Film film2 : films) {
					System.out.println(film2);
				}
				break;
			case 3:
				System.out.println("Bye!");
				menu = false;
				break;
			default:
				System.out.println("Please enter a valid choice from the selection above");

			}
		}

	}

}
