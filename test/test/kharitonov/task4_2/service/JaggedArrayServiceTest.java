package test.kharitonov.task4_2.service;

import by.kharitonov.task4_2.enumeration.SortType;
import by.kharitonov.task4_2.service.JaggedArrayService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JaggedArrayServiceTest {
    private final JaggedArrayService service = new JaggedArrayService();

    @DataProvider(name = "dataGeneralSorting")
    @Test
    public Object[][] dataGeneralSorting() {
        SortType upSum = SortType.INCREASING_SUM;
        SortType downSum = SortType.DECREASING_SUM;
        SortType upMax = SortType.INCREASING_MAX;
        SortType downMax = SortType.DECREASING_MAX;
        SortType upMin = SortType.INCREASING_MIN;
        SortType downMin = SortType.DECREASING_MIN;
        return new Object[][]{
                {jaggedArray(), upSum, jaggedArraySortedSumItems(upSum)},
                {jaggedArray(), downSum, jaggedArraySortedSumItems(downSum)},
                {jaggedArray(), upMax, jaggedArraySortedPeakElement(upMax)},
                {jaggedArray(), downMax, jaggedArraySortedPeakElement(downMax)},
                {jaggedArray(), upMin, jaggedArraySortedPeakElement(upMin)},
                {jaggedArray(), downMin, jaggedArraySortedPeakElement(downMin)}
        };
    }

    @Parameters({"jaggedArray", "sortType", "expectedJArray"})
    @Test(dataProvider = "dataGeneralSorting")
    public void testGeneralSorting(int[][] jArray, SortType sortType,
                                   int[][] expectedJArray) {
        boolean equals;
        service.generalSorting(jArray, sortType);
        equals = jaggedArraysEquals(jArray, expectedJArray);
        assertTrue(equals);
    }

    @DataProvider(name = "dataGeneralSortingBoolean")
    @Test
    public Object[][] dataGeneralSortingBoolean() {
        return new Object[][]{
                {jaggedArray(), SortType.DECREASING_MIN, true},
                {null, SortType.DECREASING_MAX, false},
                {badJaggedArray(), SortType.INCREASING_MAX, false}
        };
    }

    @Parameters({"jaggedArray", "sortType", "expectedResult"})
    @Test(dataProvider = "dataGeneralSortingBoolean")
    public void testGeneralSortingBoolean(int[][] jArray,
                                          SortType sortType,
                                          boolean expected) {
        boolean actual = service.generalSorting(jArray, sortType);
        assertEquals(actual, expected);
    }

    private int[][] jaggedArray() {
        int[][] jArray = new int[5][];
        jArray[0] = new int[5];
        jArray[1] = new int[4];
        jArray[2] = new int[3];
        jArray[3] = new int[6];
        jArray[4] = new int[7];
        fillJArray(jArray, 0);
        return jArray;
    }

    private int[][] jaggedArraySortedPeakElement(SortType sortType) {
        int[][] jArray = new int[5][];
        if (sortType == SortType.DECREASING_MAX) {
            jArray[0] = new int[7];
            jArray[1] = new int[5];
            jArray[2] = new int[6];
            jArray[3] = new int[4];
            jArray[4] = new int[3];
        } else if (sortType == SortType.DECREASING_MIN) {
            jArray[0] = new int[3];
            jArray[1] = new int[5];
            jArray[2] = new int[4];
            jArray[3] = new int[6];
            jArray[4] = new int[7];
        } else if (sortType == SortType.INCREASING_MAX) {
            jArray[0] = new int[4];
            jArray[1] = new int[3];
            jArray[2] = new int[5];
            jArray[3] = new int[6];
            jArray[4] = new int[7];
        } else if (sortType == SortType.INCREASING_MIN) {
            jArray[0] = new int[6];
            jArray[1] = new int[7];
            jArray[2] = new int[5];
            jArray[3] = new int[4];
            jArray[4] = new int[3];
        }
        fillJArray(jArray, 0);
        return jArray;
    }

    private int[][] jaggedArraySortedSumItems(SortType sortType) {
        int[][] jArray = new int[5][];
        if (sortType == SortType.INCREASING_SUM) {
            jArray[0] = new int[6];
            jArray[1] = new int[4];
            jArray[2] = new int[3];
            jArray[3] = new int[5];
            jArray[4] = new int[7];
        } else {
            jArray[0] = new int[7];
            jArray[1] = new int[5];
            jArray[2] = new int[3];
            jArray[3] = new int[4];
            jArray[4] = new int[6];
        }
        fillJArray(jArray, 0);
        return jArray;
    }

    private boolean jaggedArraysEquals(int[][] jArray1, int[][] jArray2) {
        if (jArray1 == null || jArray2 == null) {
            return false;
        }
        if (jArray1.length != jArray2.length) {
            return false;
        }
        for (int i = 0; i < jArray1.length; i++) {
            if (jArray1[i].length != jArray2[i].length) {
                return false;
            }
            for (int j = 0; j < jArray1[i].length; j++) {
                if (jArray1[i][j] != jArray2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] badJaggedArray() {
        int[][] jArray = new int[5][];
        jArray[1] = new int[4];
        jArray[2] = new int[3];
        jArray[3] = new int[6];
        jArray[4] = new int[7];
        fillJArray(jArray, 1);
        return jArray;
    }

    private void fillJArray(int[][] jArray, int startRow) {
        for (int i = startRow; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                int koef = (j % 2 == 0) ? 1 : -1;
                jArray[i][j] = 10 * (j + 1) * koef;
            }
        }
    }
}