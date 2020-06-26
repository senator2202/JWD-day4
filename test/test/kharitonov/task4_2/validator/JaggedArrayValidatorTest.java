package test.kharitonov.task4_2.validator;

import by.kharitonov.task4_2.type.SortType;
import by.kharitonov.task4_2.validator.JaggedArrayValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class JaggedArrayValidatorTest {
    private final JaggedArrayValidator validator = new JaggedArrayValidator();

    @DataProvider(name = "dataValidateJaggedArray")
    @Test
    public Object[][] dataValidateJaggedArray() {
        return new Object[][]{

                {null, SortType.DECREASING_MAX, false},
                {jaggedArray(), SortType.DECREASING_MIN, true},
                {badJaggedArray(), SortType.INCREASING_SUM, false},
                {jaggedArray(), null, false}
        };
    }

    @Parameters({"jaggedArray", "sortType", "expectedResult"})
    @Test(dataProvider = "dataValidateJaggedArray")
    public void testValidateJaggedArray(int[][] jArray, SortType sortType,
                                        boolean expected) {
        boolean actual = validator.validateSortingParameters(jArray, sortType);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataValidateRow")
    @Test
    public Object[][] dataValidateRow() {
        return new Object[][]{
                {jaggedArray(), 3, true},
                {jaggedArray(), 7, false},
                {jaggedArray(), -2, false},
                {badJaggedArray(), 1, true},
                {badJaggedArray(), 0, false},
                {null, 2, false}
        };
    }

    @Parameters({"jaggedArray", "rowNumber", "expectedResult"})
    @Test(dataProvider = "dataValidateRow")
    public void testValidateRow(int[][] jArray, int row, boolean expected) {
        boolean actual = validator.validateRow(jArray, row);
        assertEquals(actual, expected);
    }

    private int[][] jaggedArray() {
        int[][] jArray = new int[5][];
        jArray[0] = new int[5];
        jArray[1] = new int[4];
        jArray[2] = new int[3];
        jArray[3] = new int[6];
        jArray[4] = new int[7];
        for (int i = 0; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                jArray[i][j] = 10 * (j + 1);
            }
        }
        return jArray;
    }

    private int[][] badJaggedArray() {
        int[][] jArray = new int[5][];
        jArray[1] = new int[4];
        jArray[2] = new int[3];
        jArray[3] = new int[6];
        jArray[4] = new int[7];
        for (int i = 1; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                jArray[i][j] = 10 * (j + 1);
            }
        }
        return jArray;
    }
}