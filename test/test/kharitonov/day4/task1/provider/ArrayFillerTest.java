package test.kharitonov.day4.task1.provider;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.exception.ArrayException;
import by.kharitonov.day4.task1.provider.ArrayFiller;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.testng.Assert.*;

public class ArrayFillerTest {
    private final ArrayFiller arrayFiller = new ArrayFiller();
    private final IntegerArray testArray;

    {
        testArray = new IntegerArray(5);
        testArray.setElement(0, 100);
        testArray.setElement(1, 255);
        testArray.setElement(2, -99);
        testArray.setElement(3, 755);
        testArray.setElement(4, -666);
    }

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
        IntegerArray array = new IntegerArray(25);
        boolean actual = arrayFiller.randomFill(array, bound);
        assertEquals(actual, expected);
    }


    @Test
    public void testConsoleFillTrue() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        String data = "100" + System.lineSeparator() +
                "255" + System.lineSeparator() +
                "-99" + System.lineSeparator() +
                "755" + System.lineSeparator() +
                "-666" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        boolean actual = arrayFiller.consoleFill(array, 900, in);
        System.setIn(sysInBackup);
        assertTrue(actual);
    }

    @Test
    public void testConsoleFillFalse() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        String data = "100" + System.lineSeparator() +
                "255" + System.lineSeparator() +
                "-99" + System.lineSeparator() +
                "755" + System.lineSeparator() +
                "-666" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        boolean actual = arrayFiller.consoleFill(array, 1000, in);
        System.setIn(sysInBackup);
        assertFalse(actual);
    }

    @Test
    public void testConsoleFill() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        String data = "100" + System.lineSeparator() +
                "255" + System.lineSeparator() +
                "-99" + System.lineSeparator() +
                "755" + System.lineSeparator() +
                "-666" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        arrayFiller.consoleFill(array, 800, in);
        System.setIn(sysInBackup);
        assertEquals(array, testArray);
    }

    @Test
    public void testFileFillTrue() {
        try {
            IntegerArray array = new IntegerArray(5);
            arrayFiller.fileFill(array, "IntegerArray.txt");
            assertEquals(array, testArray);
        } catch (ArrayException e) {
            fail();
        }
    }

    @DataProvider(name = "dataForFileFillException")
    @Test
    public Object[][] dataForFileFillException() {
        return new Object[][]{
                {5, "WrongFile.txt"},
                {5, "WrongArray.txt"}
        };
    }

    @Parameters({"size", "fileName"})
    @Test(expectedExceptions = ArrayException.class,
            dataProvider = "dataForFileFillException")
    public void testFileFillException(int size, String fileName)
            throws ArrayException {
        IntegerArray array = new IntegerArray(size);
        arrayFiller.fileFill(array, fileName);
    }
}