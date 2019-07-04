package io.example.test;


import io.example.test.model.Country;
import io.example.test.steps.RestWrapper;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test Runner
 */
@RunWith(SerenityRunner.class)
public class ApiTest {

    /**
     * Steps class with Dependency Injection
     */
    @Steps
    RestWrapper apiSteps;

    /**
     * Test to verify all Countries API
     */
    @Test
    public void getAllCountries(){
        Response response = apiSteps.getCountry("https://restcountries-v1.p.rapidapi.com/all");
        apiSteps.verifyStatusCode(response);
        Country[] countries = response.as(Country[].class);
        Assert.assertEquals(250, countries.length);
    }

    /**
     * Test to verify country by code
     */
    @Test
    public void getCountryByCode(){
        Response response = apiSteps.getCountry("https://restcountries-v1.p.rapidapi.com/alpha/ru");
        apiSteps.verifyStatusCode(response);
        Country country = response.as(Country.class);
        Assert.assertEquals("Russia", country.getName());
    }

    /**
     * Test to verify country by capital and calling code
     */
    @Test
    public void verifyCountrybyCapitalandCallingCode(){
        Response responseForCapital = apiSteps.getCountry("https://restcountries-v1.p.rapidapi.com/capital/tallinn");
        apiSteps.verifyStatusCode(responseForCapital);
        Response responseForCallingCode = apiSteps.getCountry("https://restcountries-v1.p.rapidapi.com/callingcode/372");
        apiSteps.verifyStatusCode(responseForCallingCode);
        Country[] countriesForCode = responseForCapital.as(Country[].class);
        Country[] countriesForCallingCode = responseForCallingCode.as(Country[].class);
        Assert.assertEquals(countriesForCode[0].getName(), countriesForCallingCode[0].getName());
    }
}
