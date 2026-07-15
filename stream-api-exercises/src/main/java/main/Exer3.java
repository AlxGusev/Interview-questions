package main;

import domain.Director;
import domain.Movie;
import service.InMemoryMovieService;
import service.MovieService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * EXERCISE #3
 * Find the number of movies of each director: Try to solve this problem by assuming that Director class has not the member movies.
 */
public class Exer3 {

    public static void main(String[] args) {
        MovieService instance = InMemoryMovieService.getInstance();

        instance.findAllMovies().stream()
                .map(Movie::getDirectors)
                .flatMap(Collection::stream)
                .collect(groupingBy(Director::getName, counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new))
                .forEach((k, v) -> System.out.println(k + " - " + v));
    }
}



