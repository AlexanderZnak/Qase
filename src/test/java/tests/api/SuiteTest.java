package tests.api;

import adapters.SuiteAdapter;
import io.qameta.allure.Description;
import models.TestSuite;
import models.api.Result;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.base.Retry;
import tests.base.TestListener;

import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class SuiteTest {
    SuiteAdapter suiteAdapter = new SuiteAdapter();

    @Test(description = "Success get a specific test suite ", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to get a specific test suite")
    public void getSpecificTestSuite() {
        TestSuite expectedResult = TestSuite.builder()
                .description("This suite contains cases that belong to the following processes: resolve defect, edit defect, delete defect")
                .preconditions(null)
                .suiteName("Defects")
                .casesCount(3)
                .parentId(1)
                .build();

        Result result = suiteAdapter
                .get("QASE", 21);
        assertEquals(result.getResult(), expectedResult);
    }

    @Test(description = "Success CRUD for Test Suite", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to create, edit and delete Test Suite")
    public void testSuiteShouldBeCreatedEditedDeleted() {
        TestSuite suite = TestSuite.builder()
                .description("Suite should be updated")
                .preconditions("do correct api request")
                .suiteName("API test suite")
                .build();

        int id = suiteAdapter.post("QASE", suite);

        Result result = suiteAdapter
                .get("QASE", id);
        assertEquals(result.getResult(), suite);

        suite.setSuiteName("Updated suite");
        suite.setDescription("Suite should be updated");

        suiteAdapter
                .patch("QASE", id, suite);
        result = suiteAdapter
                .get("QASE", id);
        assertEquals(result.getResult(), suite);
        suiteAdapter
                .delete("QASE", id);
    }

}
