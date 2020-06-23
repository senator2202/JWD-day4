package test.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_1.exception.ArrayException;
import by.kharitonov.task4_1.service.ArrayService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ArrayServiceTest {
    private final ArrayService arrayService = new ArrayService();
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

    @Test
    public void testMinValue() {
        int actual = arrayService.minValue(testArray);
        assertEquals(actual, -15);
    }

    @Test
    public void testMaxValue() {
        int actual = arrayService.maxValue(testArray);
        assertEquals(actual, 199);
    }

    @DataProvider(name = "dataForSimpleNumbers")
    @Test
    public Object[][] dataForSimpleNumbers() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        list1.add(2);
        list1.add(199);
        list1.add(23);
        list2.add(2);
        list2.add(23);
        list2.add(199);
        list3.add(199);
        list3.add(23);
        list3.add(2);
        return new Object[][]{
                {testArray, list1},
                {sortedUpArray, list2},
                {sortedDownArray, list3}
        };
    }

    @Parameters({"array", "expected"})
    @Test(dataProvider = "dataForSimpleNumbers")
    public void testSimpleNumbers(IntegerArray array,
                                  ArrayList<Integer> expected) {
        ArrayList<Integer> actual = (ArrayList<Integer>)
                arrayService.simpleNumbers(array);
        assertEquals(actual, expected);
    }

    @Test
    public void testFibonacciNumbers() {
        IntegerArray array = new IntegerArray(7);
        ArrayList<Integer> expected = new ArrayList<>();
        array.setElement(0, 0);
        array.setElement(1, 22);
        array.setElement(2, 3);
        array.setElement(3, -5);
        array.setElement(4, 55);
        array.setElement(5, 100);
        array.setElement(6, -4181);
        expected.add(0);
        expected.add(3);
        expected.add(-5);
        expected.add(55);
        expected.add(-4181);
        ArrayList<Integer> actual = (ArrayList<Integer>)
                arrayService.fibonacciNumbers(array);
        assertEquals(actual, expected);
    }

    @Test
    public void testThreeDifferentDigitNumbers() {
        IntegerArray array = new IntegerArray(6);
        ArrayList<Integer> expected = new ArrayList<>();
        array.setElement(0, 0);
        array.setElement(1, 234);
        array.setElement(2, -378);
        array.setElement(3, -5678);
        array.setElement(4, 566);
        array.setElement(5, 123);
        expected.add(234);
        expected.add(-378);
        expected.add(123);
        ArrayList<Integer> actual = (ArrayList<Integer>)
                arrayService.threeDifferentDigitNumbers(array);
        assertEquals(actual, expected);
    }
}