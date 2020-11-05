package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BaseAdapter {
    private static final String URL = "https://api.qase.io";
    Response response;
    Gson gson;

    @Step("Doing the get request to: '{request}', validating status code: '{statusCode}'")
    public Response doGetRequest(String request, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .when().get(String.format("%s%s", URL, request))
                .then().log().body().statusCode(statusCode).contentType(ContentType.JSON)
                .extract().response();
        return response;
    }

    @Step("Doing the delete request to: '{request}', validating status code: '{statusCode}'")
    public BaseAdapter doDeleteRequest(String request, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .when().delete(String.format("%s%s", URL, request))
                .then().log().body().statusCode(statusCode)
                .extract().response();
        return this;
    }

    @Step("Doing the post request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
    public Response doPostRequestWithBody(File file, String request, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .body(file)
                .when().post(String.format("%s%s", URL, request))
                .then().log().body().statusCode(statusCode).contentType(ContentType.JSON)
                .extract().response();
        return response;
    }

    @Step("Doing the patch request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
    public BaseAdapter doPatchRequest(File file, String request, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;

        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .headers("Token", System.getenv("token"))
                .body(file)
                .when().patch(String.format("%s%s", URL, request))
                .then().log().body().statusCode(statusCode).contentType(ContentType.JSON)
                .extract().response();
        return this;
    }

    @Step("Validating response via JsonPath: '{jsonPathStatus}'")
    public BaseAdapter validateResponseViaJsonPath(String jsonPathStatus) {
        assertTrue(response.jsonPath().getBoolean(jsonPathStatus));
        return this;
    }

}
