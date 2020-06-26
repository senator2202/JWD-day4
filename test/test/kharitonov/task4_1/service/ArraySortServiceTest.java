package test.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.type.SortDirection;
import by.kharitonov.task4_1.service.ArraySortService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySortServiceTest {
    private final ArraySortService arraySortService = new ArraySortService();
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

    @DataProvider(name = "dataForSort")
    @Test
    public Object[][] dataForSort() {
        return new Object[][]{
                {testArray.copy(), SortDirection.UP, sortedUpArray},
                {testArray.copy(), SortDirection.DOWN, sortedDownArray}
        };
    }

    @Parameters({"sourceArray", "sortDirection", "expectedArray"})
    @Test(dataProvider = "dataForSort")
    public void testSelectionSort(IntegerArray sourceArray,
                                  SortDirection sortDirection,
                                  IntegerArray expectedArray) {
        arraySortService.selectionSort(sourceArray, sortDirection);
        assertEquals(sourceArray, expectedArray);
    }

    @Test
    public void testSelectionSortReturn() {
        IntegerArray array = testArray.copy();
        arraySortService.selectionSort(array, null);
        assertEquals(array,testArray);
    }

    @Parameters({"sourceArray", "sortDirection", "expectedArray"})
    @Test(dataProvider = "dataForSort")
    public void testBubbleSort(IntegerArray sourceArray,
                               SortDirection sortDirection,
                               IntegerArray expectedArray) {
        arraySortService.bubbleSort(sourceArray, sortDirection);
        assertEquals(sourceArray, expectedArray);
    }

    @Test
    public void testBubbleSortReturn() {
        IntegerArray array = testArray.copy();
        arraySortService.bubbleSort(array, null);
        assertEquals(array,testArray);
    }

    @Parameters({"sourceArray", "sortDirection", "expectedArray"})
    @Test(dataProvider = "dataForSort")
    public void testStupidSort(IntegerArray sourceArray,
                               SortDirection sortDirection,
                               IntegerArray expectedArray) {
        arraySortService.stupidSort(sourceArray, sortDirection);
        assertEquals(sourceArray, expectedArray);
    }

    @Test
    public void testStupidSortReturn() {
        IntegerArray array = testArray.copy();
        arraySortService.stupidSort(array, null);
        assertEquals(array,testArray);
    }
}