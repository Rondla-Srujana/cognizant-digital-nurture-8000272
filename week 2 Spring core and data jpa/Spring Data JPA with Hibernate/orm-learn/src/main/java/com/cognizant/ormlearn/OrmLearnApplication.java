package com.cognizant.ormlearn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        
        LOGGER.info("Inside main - Application Context loaded successfully.");

        // Sequential test runner executions
        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
    }

    private static void testGetAllCountries() {
        LOGGER.info("--- Start testGetAllCountries ---");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("--- End testGetAllCountries ---");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("--- Start testFindCountryByCode ---");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Fetched Country: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Exception occurred: ", e);
        }
        LOGGER.info("--- End testFindCountryByCode ---");
    }

    private static void testAddCountry() {
        LOGGER.info("--- Start testAddCountry ---");
        try {
            Country newCountry = new Country("ZZ", "Test Country Framework");
            countryService.addCountry(newCountry);
            
            Country checkedCountry = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Successfully Added & Verified: {}", checkedCountry);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Add verification failed: ", e);
        }
        LOGGER.info("--- End testAddCountry ---");
    }

    private static void testUpdateCountry() {
        LOGGER.info("--- Start testUpdateCountry ---");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country Title");
            Country updatedCountry = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Successfully Updated & Verified: {}", updatedCountry);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Update verification failed: ", e);
        }
        LOGGER.info("--- End testUpdateCountry ---");
    }

    private static void testDeleteCountry() {
        LOGGER.info("--- Start testDeleteCountry ---");
        try {
            countryService.deleteCountry("ZZ");
            LOGGER.info("Country with code 'ZZ' successfully passed to delete execution.");
            countryService.findCountryByCode("ZZ");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Delete Verified! Exception successfully thrown as expected: {}", e.getMessage());
        }
        LOGGER.info("--- End testDeleteCountry ---");
    }
}
