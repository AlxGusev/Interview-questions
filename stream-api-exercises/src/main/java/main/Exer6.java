package main;

import dao.InMemoryWorldDao;
import dao.WorldDao;
import domain.City;
import domain.Country;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static java.util.function.BinaryOperator.maxBy;

/**
 * EXERCISE #6
 * Find the highest populated capital city of each continent:
 *
 */
public class Exer6 {
    public static void main(String[] args) {
        WorldDao instance = InMemoryWorldDao.getInstance();

        BiConsumer<String, City> print = (k, v) -> System.out.printf("%s: %s (%d)%n", k, v.getName(), v.getPopulation());

        instance.findAllCountries().stream()
                .filter(country -> country.getCapital() > 0)
                .collect(Collectors.toMap(
                        Country::getContinent,
                        country -> instance.findCityById(country.getCapital()),
                        maxBy(Comparator.comparingInt(City::getPopulation))
                ))
                .forEach(print);
    }
}
