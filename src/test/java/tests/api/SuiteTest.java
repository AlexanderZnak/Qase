package tests.api;

import adapters.SuiteAdapter;
import models.TestSuite;
import models.api.Result;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SuiteTest {
    SuiteAdapter suiteAdapter = new SuiteAdapter();

    @Test
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

    @Test
    public void testSuiteShouldBeCreatedEditedDeleted() {
        TestSuite suite = TestSuite.builder()
                .description("Suite should be updated")
                .preconditions("do correct api request")
                .suiteName("API test suite")
                .build();

        int id  = suiteAdapter.post("QASE", suite);

        suite.setSuiteName("Updated suite");
        suite.setDescription("Suite should be updated");

        suiteAdapter
                .patch("QASE", id, suite);
        suiteAdapter
                .delete("QASE", id);
    }

}
