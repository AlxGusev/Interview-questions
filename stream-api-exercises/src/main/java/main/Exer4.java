package main;

import domain.Director;
import domain.Genre;
import service.InMemoryMovieService;
import service.MovieService;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * EXERCISE #4
 * Find the number of genres of each director's movies:
 */
public class Exer4 {
    public static void main(String[] args) {
        MovieService instance = InMemoryMovieService.getInstance();

        Map<String, Map<Genre, Long>> map = instance.findAllDirectors().stream()
                .collect(toMap(
                        Director::getName,
                        director -> director.getMovies().stream()
                                .flatMap(movie -> movie.getGenres().stream())
                                .collect(groupingBy(
                                        identity(),
                                        counting()
                                ))
                ));
        map.forEach(
                (k1, v1) -> {
                    System.out.println(k1);
                    v1.forEach((k2, v2) -> {
                        System.out.printf("\t%-12s: %2d%n", k2.getName(), v2);
                    });
                    System.out.println();
                }
        );
    }
}