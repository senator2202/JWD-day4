package test.kharitonov.task4_1.validator;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_1.validator.ArrayValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayValidatorTest {
    private final ArrayValidator arrayValidator = new ArrayValidator();
    private final IntegerArray testArray;
    private final IntegerArray sortedUpArray;
    private final IntegerArray sortedDownArray;

    {
        testArray = new IntegerArray(7);
        sortedUpArray = new IntegerArray(7);
        sortedDownArray = new IntegerArray(7);
        testArray.setElement(0, 2);
        testArray.setElement(1, 199);
        testArray.setElement(2, -3);
        testArray.setElement(3, 0);
        testArray.setElement(4, -18);
        testArray.setElement(5, 23);
        testArray.setElement(6, -15);
        sortedUpArray.setElement(0, -18);
        sortedUpArray.setElement(1, -15);
        sortedUpArray.setElement(2, -3);
        sortedUpArray.setElement(3, 0);
        sortedUpArray.setElement(4, 2);
        sortedUpArray.setElement(5, 23);
        sortedUpArray.setElement(6, 199);
        sortedDownArray.setElement(0, 199);
        sortedDownArray.setElement(1, 23);
        sortedDownArray.setElement(2, 2);
        sortedDownArray.setElement(3, 0);
        sortedDownArray.setElement(4, -3);
        sortedDownArray.setElement(5, -15);
        sortedDownArray.setElement(6, -18);
    }

    @DataProvider(name = "dataForValidateIndexes")
    @Test
    public Object[][] dataForValidateIndexes() {
        IntegerArray array = new IntegerArray(5);
        array.setElement(0, 25);
        return new Object[][]{
                {array, true, new int[]{0, 4}},
                {array, false, new int[]{-1, 3}},
                {array, false, new int[]{2, 3, 5}},
                {array, true, new int[]{0, 1, 2, 4}},
                {array, false, null}
        };
    }

    @Parameters({"integerArray", "expected", "indexes"})
    @Test(dataProvider = "dataForValidateIndexes")
    public void testValidateIndexes(IntegerArray array, boolean expected,
                                    int[] indexes) {
        boolean actual = arrayValidator.validateIndexes(array, indexes);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForValidateIsSorted")
    @Test
    public Object[][] dataForValidateIsSorted() {
        return new Object[][]{
                {testArray, SortDirection.UP, false},
                {sortedUpArray, SortDirection.UP, true},
                {sortedDownArray, SortDirection.DOWN, true},
                {sortedUpArray, SortDirection.DOWN, false},
                {sortedDownArray, SortDirection.UP, false},
                {testArray, SortDirection.DOWN, false},
                {null, null, false},
                {testArray, null, false},
                {null, SortDirection.UP, false}
        };
    }

    @Parameters({"integerArray", "sortDirection", "expected"})
    @Test(dataProvider = "dataForValidateIsSorted")
    public void testValidateIsSorted(IntegerArray array,
                                     SortDirection sortDirection,
                                     boolean expected) {
        boolean actual = arrayValidator.validateIsSorted(array, sortDirection);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataValidateBinarySearchValue")
    @Test
    public Object[][] dataValidateBinarySearchValue() {
        return new Object[][]{
                {testArray, 24, false},
                {sortedUpArray, -9999, false},
                {sortedUpArray, 9999, false},
                {sortedUpArray, 10, true},
                {null, 2, false}
        };
    }

    @Parameters({"integerArray", "value", "expected"})
    @Test(dataProvider = "dataValidateBinarySearchValue")
    public void testValidateBinarySearchValue(IntegerArray array,
                                              int searchValue,
                                              boolean expected) {
        boolean actual = arrayValidator.validateBinarySearchValue(array,
                searchValue);
        assertEquals(actual, expected);
    }
}