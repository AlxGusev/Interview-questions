package main;

import domain.Genre;
import domain.Movie;
import service.InMemoryMovieService;
import service.MovieService;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * EXERCISE #8
 * Find the list of movies having the genres "Drama" and "Comedy" only:
 * */
public class Exer8 {
    public static void main(String[] args) {
        MovieService instance = InMemoryMovieService.getInstance();

        Predicate<Movie> hasTwoGenresOnly = movie -> movie.getGenres().size() == 2;
        Predicate<Movie> hasDramaGenre = movie -> movie.getGenres().stream().anyMatch(genre -> genre.getName().equals("Drama"));
        Predicate<Movie> hasComedyGenre = movie -> movie.getGenres().stream().anyMatch(genre -> genre.getName().equals("Comedy"));

        List<Movie> moviesWithDramaAndComedyGenresOnly = instance.findAllMovies().stream()
                .filter(hasTwoGenresOnly.and(hasDramaGenre.and(hasComedyGenre)))
                .toList();
        moviesWithDramaAndComedyGenresOnly.forEach(
                movie -> System.out.printf(
                        "%-30s | %s%n",
                        movie.getTitle(),
                        movie.getGenres().stream()
                                .map(Genre::getName)
                                .collect(Collectors.joining(", ")))
        );
    }
}
