package test.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.service.ArrayMathService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ArrayMathServiceTest {
    private final ArrayMathService arrayMathService = new ArrayMathService();
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
        int actual = arrayMathService.minValue(testArray);
        assertEquals(actual, -15);
    }

    @Test
    public void testMaxValue() {
        int actual = arrayMathService.maxValue(testArray);
        assertEquals(actual, 199);
    }

    @DataProvider(name = "dataForSimpleNumbers")
    @Test
    public Object[][] dataForSimpleNumbers() {
        int[] arr1 = new int[3];
        int[] arr2 = new int[3];
        int[] arr3 = new int[3];
        arr1[0] = 2;
        arr1[1] = 199;
        arr1[2] = 23;
        arr2[0] = 2;
        arr2[1] = 23;
        arr2[2] = 199;
        arr3[0] = 199;
        arr3[1] = 23;
        arr3[2] = 2;
        return new Object[][]{
                {testArray, arr1},
                {sortedUpArray, arr2},
                {sortedDownArray, arr3}
        };
    }

    @Parameters({"array", "expected"})
    @Test(dataProvider = "dataForSimpleNumbers")
    public void testSimpleNumbers(IntegerArray array,
                                  int[] expected) {
        int[] actual = arrayMathService.simpleNumbers(array);
        boolean equals = arraysEquals(actual, expected);
        assertTrue(equals);
    }

    @Test
    public void testFibonacciNumbers() {
        IntegerArray array = new IntegerArray(7);
        int[] expected = new int[5];
        boolean equals;
        array.setElement(0, 0);
        array.setElement(1, 22);
        array.setElement(2, 3);
        array.setElement(3, -5);
        array.setElement(4, 55);
        array.setElement(5, 100);
        array.setElement(6, -4181);
        expected[0] = 0;
        expected[1] = 3;
        expected[2] = -5;
        expected[3] = 55;
        expected[4] = -4181;
        int[] actual = arrayMathService.fibonacciNumbers(array);
        equals = arraysEquals(actual, expected);
        assertTrue(equals);
    }

    @Test
    public void testThreeDifferentDigitNumbers() {
        IntegerArray array = new IntegerArray(6);
        int[] expected = new int[3];
        int[] actual;
        boolean equals;
        array.setElement(0, 0);
        array.setElement(1, 234);
        array.setElement(2, -378);
        array.setElement(3, -5678);
        array.setElement(4, 566);
        array.setElement(5, 123);
        expected[0] = 234;
        expected[1] = -378;
        expected[2] = 123;
        actual = arrayMathService.threeDifferentDigitNumbers(array);
        equals = arraysEquals(actual, expected);
        assertTrue(equals);
    }

    private boolean arraysEquals(int[] array1, int[] array2) {
        if (array1 == null || array2 == null ||
                array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}