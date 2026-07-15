package main;


import dao.InMemoryWorldDao;
import domain.City;

import java.util.Optional;

import static java.lang.System.out;
import static java.util.Comparator.comparing;

/**
 * EXERCISE #5
 * Find the highest populated capital city:
 */
public class Exer5 {

    public static void main(String[] args) {
        InMemoryWorldDao instance = InMemoryWorldDao.getInstance();

        Optional<City> highestPopulatedCapitalCity = instance.findAllCountries().stream()
                .map(country -> country.getCities().stream()
                        .filter(city -> country.getCapital() == city.getId()).findAny())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(comparing(City::getPopulation));
        highestPopulatedCapitalCity.ifPresentOrElse(out::println, () -> out.println("No capital city found"));

    }
}
