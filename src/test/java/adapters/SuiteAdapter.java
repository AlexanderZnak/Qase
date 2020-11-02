package adapters;

import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.api.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.testng.Assert.assertEquals;

@Log4j2
public class SuiteAdapter extends BaseAdapter {

    @Step("Validating response via objects using json at the path: '{pathName}'")
    public SuiteAdapter validateResponseViaObjects(String pathName, Response response) {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Result actualResult = gson.fromJson(response.body().asString(), Result.class);
        log.info(actualResult);
        Result expectedResult = null;
        try {
            expectedResult = gson.fromJson(new FileReader(new File(pathName)), Result.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        log.info(expectedResult);
        assertEquals(actualResult, expectedResult);
        return this;
    }

    @Step("Getting suite id")
    public int getSuiteId(Response response) {
        return response.jsonPath().getInt("result.id");
    }

}
