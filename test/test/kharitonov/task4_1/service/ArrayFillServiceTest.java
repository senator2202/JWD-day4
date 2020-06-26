package test.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.exception.IntegerArrayException;
import by.kharitonov.task4_1.service.ArrayFillService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.testng.Assert.*;

public class ArrayFillServiceTest {
    private final ArrayFillService arrayFillService = new ArrayFillService();
    private final IntegerArray testArray;
    private final String dataConsoleValid;

    {
        testArray = new IntegerArray(5);
        testArray.setElement(0, 100);
        testArray.setElement(1, 255);
        testArray.setElement(2, -99);
        testArray.setElement(3, 755);
        testArray.setElement(4, -666);
        dataConsoleValid = "100" + System.lineSeparator() +
                "255" + System.lineSeparator() +
                "-99" + System.lineSeparator() +
                "755" + System.lineSeparator() +
                "-666" + System.lineSeparator();
    }

    @DataProvider(name = "dataForRandomFill")
    @Test
    public Object[][] dataForRandomFill() {
        return new Object[][]{
                {null, 23, false},
                {null, -2, false},
                {testArray, 1000, false},
                {testArray, 250, true},
                {testArray, -1234, false},
                {testArray, 0, false},
                {testArray, 999, true},
        };
    }

    @Parameters({"integerArray", "bound", "expected"})
    @Test(dataProvider = "dataForRandomFill")
    public void testRandomFill(IntegerArray array, int bound,
                               boolean expected) {
        boolean actual = arrayFillService.randomFill(array, bound);
        assertEquals(actual, expected);
    }

    @Test
    public void testConsoleFill() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in =
                new ByteArrayInputStream(dataConsoleValid.getBytes());
        System.setIn(in);
        try {
            arrayFillService.consoleFill(array);
        } catch (IntegerArrayException e) {
            fail();
        }
        System.setIn(sysInBackup);
        assertEquals(array, testArray);
    }

    @Test
    public void testConsoleFillTrue() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in =
                new ByteArrayInputStream(dataConsoleValid.getBytes());
        System.setIn(in);
        boolean actual = false;
        try {
            actual = arrayFillService.consoleFill(array);
        } catch (IntegerArrayException e) {
            fail();
        }
        System.setIn(sysInBackup);
        assertTrue(actual);
    }

    @Test
    public void testConsoleFillBadData() {
        IntegerArray array = new IntegerArray(5);
        InputStream sysInBackup = System.in;
        String data = "1100" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        boolean actual = false;
        try {
            actual = arrayFillService.consoleFill(array);
        } catch (IntegerArrayException e) {
            fail();
        }
        System.setIn(sysInBackup);
        assertFalse(actual);
    }

    @Test
    public void testConsoleFillNullArray() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in =
                new ByteArrayInputStream(dataConsoleValid.getBytes());
        System.setIn(in);
        boolean actual = false;
        try {
            actual = arrayFillService.consoleFill(null);
        } catch (IntegerArrayException e) {
            fail();
        }
        System.setIn(sysInBackup);
        assertFalse(actual);
    }

    @Test(expectedExceptions = IntegerArrayException.class,
            expectedExceptionsMessageRegExp = "All values must be integer!")
    public void testConsoleFillException() throws IntegerArrayException {
        IntegerArray array = new IntegerArray(5);
        String data = "100.23" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        arrayFillService.consoleFill(array);
    }

    @Test(expectedExceptions = IntegerArrayException.class,
            expectedExceptionsMessageRegExp = "Not enough data!")
    public void testConsoleFillException1() throws IntegerArrayException {
        IntegerArray array = new IntegerArray(5);
        String data = "100" + System.lineSeparator();
        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        arrayFillService.consoleFill(array);
    }

    @Test
    public void testFileFillTrue() {
        try {
            IntegerArray array = new IntegerArray(5);
            arrayFillService.fileFill(array, "resources\\" +
                    "IntegerArray.txt");
            assertEquals(array, testArray);
        } catch (IntegerArrayException e) {
            fail();
        }
    }

    @DataProvider(name = "dataFileFillFalse")
    @Test
    public Object[][] dataFileFillFalse() {
        return new Object[][]{
                {null, null},
                {null, ""},
                {testArray, ""},
                {new IntegerArray(4), null}
        };
    }

    @Parameters({"array", "fileName"})
    @Test(dataProvider = "dataFileFillFalse")
    public void testFileFillFalse(IntegerArray array, String fileName) {
        try {
            boolean actual = arrayFillService.fileFill(array, fileName);
            assertFalse(actual);
        } catch (IntegerArrayException e) {
            fail();
        }
    }

    @DataProvider(name = "dataForFileFillException")
    @Test
    public Object[][] dataForFileFillException() {
        return new Object[][]{
                {5, "resources\\NotExistingFile.txt"},
                {5, "resources\\IncompleteArray.txt"},
                {5, "resources\\InvalidArray.txt"}
        };
    }

    @Parameters({"size", "fileName"})
    @Test(expectedExceptions = IntegerArrayException.class,
            dataProvider = "dataForFileFillException")
    public void testFileFillException(int size, String fileName)
            throws IntegerArrayException {
        IntegerArray array = new IntegerArray(size);
        arrayFillService.fileFill(array, fileName);
    }
}