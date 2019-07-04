package io.example.test.steps;

import io.example.test.model.Country;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class on Rest assured for reusable functions and to report the response
 */
public class RestWrapper {

    /**
     * Method to get the GET API
     * @param path API URL
     * @return API Respose
     */
    @Step
    public Response getCountry(String path){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-RapidAPI-Host","restcountries-v1.p.rapidapi.com");
        headers.put("X-RapidAPI-Key","8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03");
        return SerenityRest.given().headers(headers).get(path);
    }

    /**
     * Method to verify the status code
     * @param response API Response
     */
    @Step
    public void verifyStatusCode(Response response){
        response.then().statusCode(200);
    }

}
