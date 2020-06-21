package test.kharitonov.day4.task2.service;

import by.kharitonov.day4.task1.entity.SortDirection;
import by.kharitonov.day4.task2.entity.PeakType;
import by.kharitonov.day4.task2.service.JaggedArrayService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JaggedIntegerArrayServiceTest {
    JaggedArrayService service = new JaggedArrayService();

    @DataProvider(name = "dataForSortRowsSumItems")
    @Test
    public Object[][] dataForSortRowsSumItems() {
        return new Object[][]{
                {jaggedArray(), SortDirection.UP,
                        jaggedArraySortedUpSumItems()},
                {jaggedArray(), SortDirection.DOWN,
                        jaggedArraySortedDownSumItems()},
                {jaggedArraySortedDownSumItems(), SortDirection.UP,
                        jaggedArraySortedUpSumItems()},
                {jaggedArraySortedUpSumItems(), SortDirection.DOWN,
                        jaggedArraySortedDownSumItems()}
        };
    }

    @Parameters({"sourceJArray", "sortDirection", "expectedJArray"})
    @Test(dataProvider = "dataForSortRowsSumItems",
            groups = "rowsSumItems", priority = 1)
    public void testSortRowsSumItems(int[][] jArray,
                                     SortDirection direction,
                                     int[][] expectedJArray) {
        boolean equals;
        service.sortRowsBySumItems(jArray, direction);
        equals = jaggedArraysEquals(jArray, expectedJArray);
        assertTrue(equals);
    }

    @DataProvider(name = "dataSortRowsSumItemsBoolean")
    @Test
    public Object[][] dataSortRowsSumItemsBoolean() {
        return new Object[][]{
                {jaggedArray(), true},
                {null, false},
                {badJaggedArray(), false}
        };
    }

    @Parameters({"jaggedArray", "expectedResult"})
    @Test(groups = "rowsSumItems", priority = 2,
            dataProvider = "dataSortRowsSumItemsBoolean")
    public void testSortRowsSumItemsBoolean(int[][] jArray, boolean expected) {
        boolean actual = service.sortRowsBySumItems(jArray, SortDirection.UP);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataSortRowsPeakElement")
    @Test
    public Object[][] dataSortRowsPeakElement() {
        PeakType MAX = PeakType.MAX;
        PeakType MIN = PeakType.MIN;
        SortDirection up = SortDirection.UP;
        SortDirection down = SortDirection.DOWN;
        return new Object[][]{
                {jaggedArray(), MAX, up,
                        jaggedArraySortedPeakElement(MAX, up)},
                {jaggedArray(), MAX, down,
                        jaggedArraySortedPeakElement(MAX, down)},
                {jaggedArray(), MIN, up,
                        jaggedArraySortedPeakElement(MIN, up)},
                {jaggedArray(), MIN, down,
                        jaggedArraySortedPeakElement(MIN, down)}
        };
    }

    @Parameters({"sourceJArray", "peakType", "sortDirection", "expectedJArray"})
    @Test(dataProvider = "dataSortRowsPeakElement")
    public void testSortRowsPeakElement(int[][] jArray, PeakType peakType,
                                        SortDirection sortDirection,
                                        int[][] expectedJArray) {
        boolean equals;
        service.sortRowsByPeakElement(jArray, peakType, sortDirection);
        equals = jaggedArraysEquals(jArray, expectedJArray);
        assertTrue(equals);
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