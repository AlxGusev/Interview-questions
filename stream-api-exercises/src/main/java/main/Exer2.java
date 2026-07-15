package main;

import dao.InMemoryWorldDao;
import domain.City;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * EXERCISE #2
 * Find the most populated city of each continent:
 */
public class Exer2 {
    public static void main(String[] args) {
        InMemoryWorldDao instance = InMemoryWorldDao.getInstance();

        Predicate<Map.Entry<String, Optional<City>>> cityIsPresent = entry -> entry.getValue().isPresent();
        Consumer<Map.Entry<String, City>> printInfo = entry ->
                System.out.println(
                    entry.getKey() + " - " +
                    entry.getValue().getName() + " (" +
                    entry.getValue().getPopulation() + ")"
                );

        instance.findAllCountries().stream()
                .flatMap(country -> country.getCities().stream())
                .collect(Collectors.groupingBy(
                        city -> instance.findCountryByCode(city.getCountryCode()).getContinent(),
                        Collectors.maxBy(Comparator.comparing(City::getPopulation))))
                .entrySet().stream()
                .filter(cityIsPresent)
                .map(e -> Map.entry(e.getKey(), e.getValue().get()))
                .forEach(printInfo);
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
}