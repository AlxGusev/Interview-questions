package main;

import domain.Movie;
import service.InMemoryMovieService;
import service.MovieService;

import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.*;

/**
 * EXERCISE #9
 * Group the movies by the year and list them:
 */
public class Exer9 {
    public static void main(String[] args) {
        MovieService instance = InMemoryMovieService.getInstance();

        Map<Integer, List<String>> moviesGroupedByYear = instance.findAllMovies().stream()
                .collect(groupingBy(
                        Movie::getYear,
                        mapping(
                                Movie::getTitle,
                                toList())
                ));
        moviesGroupedByYear.entrySet().stream()
                .sorted(comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": ");
                    entry.getValue().forEach(movie -> System.out.println("\t" + movie));
                });
    }
}
