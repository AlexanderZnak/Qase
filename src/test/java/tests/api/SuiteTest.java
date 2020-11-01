package tests.api;

import adapters.BaseAdapter;
import adapters.SuiteAdapter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class SuiteTest {
    BaseAdapter baseAdapter = new BaseAdapter();
    SuiteAdapter suiteAdapter = new SuiteAdapter();

    @Test
    public void getSpecificTestSuite() {
        Response response = baseAdapter.doGetRequest("/v1/suite/QASE/21", 200);
        suiteAdapter.validateResponseViaObjects("src/test/resources/defects.json", response);
    }

    @Test
    public void testSuiteShouldBeCreatedEditedDeleted() {
        Response response = baseAdapter.doPostRequestWithBody(new File("src/test/resources/new-suite.json"), "/v1/suite/QASE", 200);
        baseAdapter.validateResponseViaJsonPath("status");
        int id = suiteAdapter.getSuiteId(response);
        baseAdapter
                .doPatchRequest(new File("src/test/resources/update-suite.json"), "/v1/suite/QASE/" + id, 200)
                .validateResponseViaJsonPath("status");
        baseAdapter
                .doDeleteRequest("/v1/suite/QASE/" + id, 200)
                .validateResponseViaJsonPath("status");
    }

}
