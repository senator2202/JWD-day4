package test.kharitonov.day4.task2.service;

import by.kharitonov.day4.task1.entity.SortDirection;
import by.kharitonov.day4.task2.entity.PeakType;
import by.kharitonov.day4.task2.entity.SortType;
import by.kharitonov.day4.task2.service.JaggedArrayService;
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
        PeakType max = PeakType.MAX;
        PeakType min = PeakType.MIN;
        SortDirection up = SortDirection.UP;
        SortDirection down = SortDirection.DOWN;
        return new Object[][]{
                {jaggedArray(), SortType.INCREASING_SUM,
                        jaggedArraySortedUpSumItems()},
                {jaggedArray(), SortType.DECREASING_SUM,
                        jaggedArraySortedDownSumItems()},
                {jaggedArray(), SortType.INCREASING_MAX,
                        jaggedArraySortedPeakElement(max, up)},
                {jaggedArray(), SortType.DECREASING_MAX,
                        jaggedArraySortedPeakElement(max, down)},
                {jaggedArray(), SortType.INCREASING_MIN,
                        jaggedArraySortedPeakElement(min, up)},
                {jaggedArray(), SortType.DECREASING_MIN,
                        jaggedArraySortedPeakElement(min, down)}
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
        for (int i = 0; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                int koef = (j % 2 == 0) ? 1 : -1;
                jArray[i][j] = 10 * (j + 1) * koef;
            }
        }
        return jArray;
    }

    private int[][] jaggedArraySortedPeakElement(PeakType peakType,
                                                 SortDirection direction) {
        int[][] jArray = new int[5][];
        if (peakType == PeakType.MAX &&
                direction == SortDirection.DOWN) {
            jArray[0] = new int[7];
            jArray[1] = new int[5];
            jArray[2] = new int[6];
            jArray[3] = new int[4];
            jArray[4] = new int[3];
        } else if (peakType == PeakType.MIN &&
                direction == SortDirection.DOWN) {
            jArray[0] = new int[3];
            jArray[1] = new int[5];
            jArray[2] = new int[4];
            jArray[3] = new int[6];
            jArray[4] = new int[7];
        } else if (peakType == PeakType.MAX &&
                direction == SortDirection.UP) {
            jArray[0] = new int[4];
            jArray[1] = new int[3];
            jArray[2] = new int[5];
            jArray[3] = new int[6];
            jArray[4] = new int[7];
        } else if (peakType == PeakType.MIN &&
                direction == SortDirection.UP) {
            jArray[0] = new int[6];
            jArray[1] = new int[7];
            jArray[2] = new int[5];
            jArray[3] = new int[4];
            jArray[4] = new int[3];
        }
        for (int i = 0; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                int koef = (j % 2 == 0) ? 1 : -1;
                jArray[i][j] = 10 * (j + 1) * koef;
            }
        }
        return jArray;
    }


    private int[][] jaggedArraySortedUpSumItems() {
        int[][] jArray = new int[5][];
        jArray[0] = new int[6];
        jArray[1] = new int[4];
        jArray[2] = new int[3];
        jArray[3] = new int[5];
        jArray[4] = new int[7];
        for (int i = 0; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                int koef = (j % 2 == 0) ? 1 : -1;
                jArray[i][j] = 10 * (j + 1) * koef;
            }
        }
        return jArray;
    }

    private int[][] jaggedArraySortedDownSumItems() {
        int[][] jArray = new int[5][];
        jArray[0] = new int[7];
        jArray[1] = new int[5];
        jArray[2] = new int[3];
        jArray[3] = new int[4];
        jArray[4] = new int[6];
        for (int i = 0; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                int koef = (j % 2 == 0) ? 1 : -1;
                jArray[i][j] = 10 * (j + 1) * koef;
            }
        }
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
        for (int i = 1; i < jArray.length; i++) {
            for (int j = 0; j < jArray[i].length; j++) {
                jArray[i][j] = 10 * (j + 1);
            }
        }
        return jArray;
    }
}