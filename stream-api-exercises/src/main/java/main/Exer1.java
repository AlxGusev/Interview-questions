package main;

import dao.InMemoryWorldDao;
import domain.City;
import domain.Country;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * EXERCISE #1
 * Find the highest populated city of each country:
 * */
public class Exer1 {

    public static void main(String[] args) {
        InMemoryWorldDao instance = InMemoryWorldDao.getInstance();

        Map<Country, City> highestPopulatedCitiesInEachCountry = instance.findAllCountries().stream()
                .map(country -> country.getCities().stream()
                        .max(Comparator.comparing(City::getPopulation)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        city -> instance.findCountryByCode(city.getCountryCode()),
                        Function.identity()));
        highestPopulatedCitiesInEachCountry.forEach(
                (k, v) -> System.out.println(k.getName() + " - " + v.getName() + " (" + v.getPopulation() + ")"));
    }
}


