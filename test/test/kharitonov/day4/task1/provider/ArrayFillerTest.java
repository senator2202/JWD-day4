package test.kharitonov.day4.task1.provider;

import by.kharitonov.day4.task1.entity.Array;
import by.kharitonov.day4.task1.provider.ArrayFiller;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayFillerTest {
    private ArrayFiller arrayFiller = new ArrayFiller();

    @DataProvider(name = "dataForRandomFill")
    @Test
    public Object[][] dataForRandomFill() {
        return new Object[][]{
                {1000, false},
                {250, true},
                {-1234, false},
                {0, false},
                {999, true},
        };
    }

    @Parameters("highBound")
    @Test(dataProvider = "dataForRandomFill")
    public void testRandomFill(int bound, boolean expected) {
        Array array = new Array(25);
        boolean actual = arrayFiller.randomFill(array, bound);
        assertEquals(actual, expected);
    }


    @Test
    public void testConsoleFill() {
        Array array = new Array(5);
        boolean actual = arrayFiller.consoleFill(array, 900);
        assertTrue(actual);
    }
}