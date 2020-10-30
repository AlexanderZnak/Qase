package tests.api;

import adapters.BaseAdapter;
import org.testng.annotations.Test;

import java.io.File;

public class SuiteTest {
    BaseAdapter baseAdapter = new BaseAdapter();

    @Test
    public void getSpecificTestSuite() {
        baseAdapter
                .doGetRequest("/v1/suite/QASE/21", 200)
                .validateResponseViaObjects("src/test/resources/defects.json");
    }

    @Test
    public void testSuiteShouldBeCreatedEditedDeleted() {
        baseAdapter
                .doPostRequestWithBody(new File("src/test/resources/new-suite.json"), "/v1/suite/QASE", 200)
                .validateResponseViaJsonPath("status");
        int id = baseAdapter.getSuiteId();
        baseAdapter
                .doPatchRequest(new File("src/test/resources/update-suite.json"), "/v1/suite/QASE/" + id, 200)
                .validateResponseViaJsonPath("status");
        baseAdapter
                .doDeleteRequest("/v1/suite/QASE/" + id, 200)
                .validateResponseViaJsonPath("status");

    }

}
