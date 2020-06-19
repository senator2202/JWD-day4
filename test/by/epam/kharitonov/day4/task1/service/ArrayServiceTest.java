package by.epam.kharitonov.day4.task1.service;

import by.kharitonov.day4.task1.entity.Array;
import by.kharitonov.day4.task1.entity.SortDirection;
import by.kharitonov.day4.task1.service.ArrayService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayServiceTest {
    ArrayService arrayService = new ArrayService();
    private final Array testArray;
    private final Array sortedUpArray;
    private final Array sortedDownArray;

    {
        testArray = new Array(6);
        sortedUpArray = new Array(6);
        sortedDownArray = new Array(6);
        testArray.setElement(0, 1);
        testArray.setElement(1, 222);
        testArray.setElement(2, -3);
        testArray.setElement(3, 0);
        testArray.setElement(4, -18);
        testArray.setElement(5, 23);
        sortedUpArray.setElement(0, -18);
        sortedUpArray.setElement(1, -3);
        sortedUpArray.setElement(2, 0);
        sortedUpArray.setElement(3, 1);
        sortedUpArray.setElement(4, 23);
        sortedUpArray.setElement(5, 222);
        sortedDownArray.setElement(0, 222);
        sortedDownArray.setElement(1, 23);
        sortedDownArray.setElement(2, 1);
        sortedDownArray.setElement(3, 0);
        sortedDownArray.setElement(4, -3);
        sortedDownArray.setElement(5, -18);
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
    public void testSelectionSort(Array sourceArray,
                                  SortDirection sortDirection,
                                  Array expectedArray) {
        arrayService.selectionSort(sourceArray, sortDirection);
        assertEquals(sourceArray, expectedArray);
    }

    @Parameters({"sourceArray", "sortDirection", "expectedArray"})
    @Test(dataProvider = "dataForSort")
    public void testBubbleSort(Array sourceArray,
                               SortDirection sortDirection,
                               Array expectedArray) {
        arrayService.bubbleSort(sourceArray, sortDirection);
        assertEquals(sourceArray, expectedArray);
    }

    @Parameters({"sourceArray", "sortDirection", "expectedArray"})
    @Test(dataProvider = "dataForSort")
    public void testQuickSort(Array sourceArray,
                              SortDirection sortDirection,
                              Array expectedArray) {
        arrayService.quickSort(sourceArray, sortDirection,0,
                sourceArray.getLength() - 1);
        assertEquals(sourceArray, expectedArray);
    }
}