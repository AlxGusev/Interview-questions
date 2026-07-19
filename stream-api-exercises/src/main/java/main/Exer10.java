package main;

import dao.InMemoryWorldDao;
import domain.Country;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparingDouble;

/**
 * EXERCISE #10
 * Sort the countries by their population densities in descending order ignoring zero population countries:
 */
public class Exer10 {
    public static void main(String[] args) {
        InMemoryWorldDao instance = InMemoryWorldDao.getInstance();

        Predicate<Country> isPopulated = country -> country.getPopulation() > 0;
        Function<Country, Double> populationDensity = country -> country.getPopulation() / country.getSurfaceArea();
        Comparator<Country> byDensityDesc = comparingDouble(populationDensity::apply).reversed();

        instance.findAllCountries().stream()
                .filter(isPopulated)
                .sorted(byDensityDesc)
                .forEach(country -> System.out.printf(
                        "%s | Density %.2f | Population %d%n",
                        country.getName(),
                        populationDensity.apply(country),
                        country.getPopulation()));
    }
}
