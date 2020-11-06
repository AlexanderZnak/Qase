package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static utils.PropertyReader.getProperty;

@Log4j2
abstract class BaseAdapter {
    private static final String URL = getProperty("qase.api");

    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    Response response;

    @Step("Doing the get request to: '{request}'")
    protected Response get(String request) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .when().get(String.format("%s%s", URL, request))
                .then().log().body().statusCode(200).contentType(ContentType.JSON)
                .extract().response();
        return response;
    }

    @Step("Doing the delete request to: '{uri}', validating status code: '{statusCode}'")
    protected Response delete(String uri, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .when().delete(String.format("%s%s", URL, uri))
                .then().log().body().statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Doing the post request to: '{request}', sending the body: '{body}', validating status code: '{statusCode}'")
    protected Response post(String request, String body, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .body(body)
                .when().post(String.format("%s%s", URL, request))
                .then().log().body().statusCode(statusCode).contentType(ContentType.JSON)
                .extract().response();
        return response;
    }

    @Step("Doing the patch request to: '{uri}', sending the body: '{body}', validating status code: '{statusCode}'")
    protected Response patch(String uri, String body, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .body(body)
                .when().patch(String.format("%s%s", URL, uri))
                .then().log().body().statusCode(statusCode).contentType(ContentType.JSON)
                .extract().response();
        return response;
    }

    @Step("Validating response status")
    protected BaseAdapter validateStatus(Response response) {
        assertTrue(response.jsonPath().getBoolean("status"));
        return this;
    }

}
