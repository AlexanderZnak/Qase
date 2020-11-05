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

@Log4j2
abstract class BaseAdapter {
    //TODO Вычитывать с помощью PropertyReader https://gist.github.com/dzmitryrak/84773c08e3ecd4ede56897035a62348c
    private static final String URL = "https://api.qase.io";
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    Response response;

    @Step("Doing the get request to: '{request}', validating status code: '{statusCode}'")
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

    @Step("Doing the delete request to: '{request}', validating status code: '{statusCode}'")
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

    @Step("Doing the post request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
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

    @Step("Doing the patch request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
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

    @Step("Validating response via JsonPath: '{status}'")
    protected BaseAdapter validateStatus(Response response) {
        assertTrue(response.jsonPath().getBoolean("status"));
        return this;
    }

}
