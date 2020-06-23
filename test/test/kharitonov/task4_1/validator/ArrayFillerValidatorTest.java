package test.kharitonov.task4_1.validator;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.validator.ArrayFillerValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class ArrayFillerValidatorTest {
    private final ArrayFillerValidator validator = new ArrayFillerValidator();
    private final IntegerArray testArray = new IntegerArray(7);

    @DataProvider(name = "dataValidateFillParameters1")
    @Test
    public Object[][] dataValidateFillParameters1() {
        return new Object[][]{
                {testArray, 555, true},
                {testArray, 1000, false},
                {null, 555, false},
        };
    }

    @Parameters({"integerArray", "bound", "expected"})
    @Test(dataProvider = "dataValidateFillParameters1")
    public void testValidateFillParameters1(IntegerArray array, int bound,
                                            boolean expected) {
        boolean actual = validator.validateFillParameters(array, bound);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataValidateFillParameters2")
    @Test
    public Object[][] dataValidateFillParameters2() {
        return new Object[][]{
                {testArray, System.in, true},
                {null, System.in, false},
                {testArray, null, false}
        };
    }

    @Parameters({"integerArray", "inputStream", "expected"})
    @Test(dataProvider = "dataValidateFillParameters2")
    public void testValidateFillParameters2(IntegerArray array, InputStream in,
                                            boolean expected) {
        boolean actual = validator.validateFillParameters(array, in);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataValidateFillParameters3")
    @Test
    public Object[][] dataValidateFillParameters3() {
        return new Object[][]{
                {testArray, "File.txt", true},
                {testArray, "", false},
                {null, "Any text", false},
                {testArray, null, false}
        };
    }


    @Parameters({"integerArray", "fileName", "expected"})
    @Test(dataProvider = "dataValidateFillParameters3")
    public void testValidateFillParameters3(IntegerArray array, String fileName,
                                            boolean expected) {
        boolean actual = validator.validateFillParameters(array, fileName);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataValidateInRange")
    @Test
    public Object[][] dataValidateInRange() {
        return new Object[][]{
                {-900, true},
                {0, false},
                {900, true},
                {1255, false},
                {-9999, false}
        };
    }

    @Parameters({"value", "expected"})
    @Test(dataProvider = "dataValidateInRange")
    public void testValidateInRange(int value, boolean expected) {
        boolean actual = validator.validateInRange(value);
        assertEquals(actual, expected);
    }
}