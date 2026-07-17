package main;

import dao.InMemoryWorldDao;
import domain.Country;

import static java.lang.System.out;
import static java.util.Comparator.comparingInt;

/**
 * EXERCISE #7
 * Sort the countries by number of their cities in descending order:
 *
 */
public class Exer7 {
    public static void main(String[] args) {
        InMemoryWorldDao instance = InMemoryWorldDao.getInstance();

        instance.findAllCountries().stream()
                .filter(country -> !country.getCities().isEmpty())
                .sorted(comparingInt((Country country) -> country.getCities().size()).reversed())
                .forEach(country -> out.printf("%38s %3d%n", country.getName(), country.getCities().size()));
    }
}
