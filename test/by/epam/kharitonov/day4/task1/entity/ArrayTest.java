package by.epam.kharitonov.day4.task1.entity;

import by.kharitonov.day4.task1.entity.Array;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class ArrayTest {
    private final Array testArray;

    {
        testArray = new Array(5);
        testArray.setElement(0, 1);
        testArray.setElement(1, 2);
        testArray.setElement(2, -3);
        testArray.setElement(3, 0);
        testArray.setElement(4, 23);
    }

    @Test
    public void testToString() {
        assertEquals(testArray.toString(), "Array{1,2,-3,0,23}");
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

    @DataProvider(name = "dataForGetElement")
    @Test
    public Object[][] dataForGetElement() {
        return new Object[][]{
                {-1, Optional.ofNullable(null)},
                {0, Optional.of(1)},
                {4, Optional.of(23)},
                {25, Optional.ofNullable(null)}
        };
    }

    @Parameters({"index", "expectedResult"})
    @Test(dataProvider = "dataForGetElement")
    public void testGetElement(int index, Optional<Integer> expectedResult) {
        Optional<Integer> actualResult = testArray.getElement(index);
        assertEquals(actualResult, expectedResult);
    }
}