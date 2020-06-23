package test.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.exception.ArrayException;
import by.kharitonov.task4_1.service.ArraySearchService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ArraySearchServiceTest {
    private final ArraySearchService arraySearchService;
    private final IntegerArray testArray;
    private final IntegerArray sortedUpArray;
    private final IntegerArray sortedDownArray;

    {
        arraySearchService = new ArraySearchService();
        testArray = new IntegerArray(7);
        sortedUpArray = new IntegerArray(7);
        sortedDownArray = new IntegerArray(7);
        testArray.setElement(0, 2);
        testArray.setElement(1, 199);
        testArray.setElement(2, -3);
        testArray.setElement(3, 0);
        testArray.setElement(4, 18);
        testArray.setElement(5, 23);
        testArray.setElement(6, -15);
        sortedUpArray.setElement(0, -15);
        sortedUpArray.setElement(1, -3);
        sortedUpArray.setElement(2, 0);
        sortedUpArray.setElement(3, 2);
        sortedUpArray.setElement(4, 18);
        sortedUpArray.setElement(5, 23);
        sortedUpArray.setElement(6, 199);
        sortedDownArray.setElement(0, 199);
        sortedDownArray.setElement(1, 23);
        sortedDownArray.setElement(2, 18);
        sortedDownArray.setElement(3, 2);
        sortedDownArray.setElement(4, 0);
        sortedDownArray.setElement(5, -3);
        sortedDownArray.setElement(6, -15);
    }

    @DataProvider(name = "dataForTestBinarySearch")
    @Test
    public Object[][] dataForTestBinarySearch() {
        return new Object[][]{
                {sortedUpArray, 18, 4},
                {sortedUpArray, -3, 1},
                {sortedUpArray, 199, 6},
                {sortedUpArray, 100, -1},
                {sortedUpArray, -100, -1}
        };
    }

    @Parameters({"array", "searchValue", "expected"})
    @Test(dataProvider = "dataForTestBinarySearch")
    public void testBinarySearch(IntegerArray array, int searchValue,
                                 int expected) {
        try {
            int actual = arraySearchService.binarySearch(array, searchValue);
            assertEquals(actual, expected);
        } catch (ArrayException e) {
            fail();
        }
    }

    @DataProvider(name = "dataForTestBinarySearchException")
    @Test
    public Object[][] dataForTestBinarySearchException() {
        return new Object[][]{
                {testArray, -18},
                {sortedDownArray, 222}
        };
    }

    @Parameters({"array", "searchValue"})
    @Test(dataProvider = "dataForTestBinarySearchException",
            expectedExceptions = ArrayException.class)
    public void testBinarySearchException(IntegerArray array, int searchValue)
            throws ArrayException {
        arraySearchService.binarySearch(array, searchValue);
    }
}