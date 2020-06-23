package test.kharitonov.task4_1.entity;

import by.kharitonov.task4_1.entity.IntegerArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class IntegerArrayTest {
    private final IntegerArray testArray;

    {
        testArray = new IntegerArray(5);
        testArray.setElement(0, 1);
        testArray.setElement(1, 2);
        testArray.setElement(2, -33);
        testArray.setElement(3, 0);
        testArray.setElement(4, 23);
    }

    @Test
    public void testGetLength() {
        assertEquals(testArray.getLength(), 5);
    }

    @DataProvider(name = "dataForSetElement")
    @Test
    public Object[][] dataForSetElement() {
        return new Object[][]{
                {-1, 2, false},
                {0, -23, true},
                {4, 5, true},
                {25, 100, false}
        };
    }

    @Parameters({"index", "value", "expectedResult"})
    @Test(dataProvider = "dataForSetElement")
    public void testSetElement(int index, int value, boolean expectedResult) {
        boolean actualResult = testArray.setElement(index, value);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataForGetOptionalElement")
    @Test
    public Object[][] dataForGetOptionalElement() {
        return new Object[][]{
                {-1, Optional.empty()},
                {0, Optional.of(1)},
                {4, Optional.of(23)},
                {25, Optional.empty()}
        };
    }

    @Parameters({"index", "expectedResult"})
    @Test(dataProvider = "dataForGetOptionalElement")
    public void testGetOptionalElement(int index, Optional<Integer> expectedResult) {
        Optional<Integer> actualResult = testArray.getOptionalElement(index);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testGetFirst() {
        int actualResult = testArray.getFirst();
        assertEquals(actualResult, 1);
    }

    @Test
    public void testGetLast() {
        int actualResult = testArray.getLast();
        assertEquals(actualResult, 23);
    }

    @Test
    public void testMaxValue() {
        int actualResult = testArray.maxValue();
        assertEquals(actualResult, 23);
    }

    @Test
    public void testMinValue() {
        int actualResult = testArray.minValue();
        assertEquals(actualResult, -33);
    }

    @Test
    public void testAbsValue() {
        int actualResult = testArray.absValue();
        assertEquals(actualResult, 33);
    }
}