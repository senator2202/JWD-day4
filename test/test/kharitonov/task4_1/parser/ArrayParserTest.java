package test.kharitonov.task4_1.parser;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.exception.IntegerArrayException;
import by.kharitonov.task4_1.parser.ArrayParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

public class ArrayParserTest {
    private final ArrayParser arrayParser = new ArrayParser();

    @Test
    public void testParseArray() {
        String[] data = {"7", "100 255 -99 755 -666 1 -22"};
        IntegerArray actual = null;
        try {
            actual = arrayParser.parseArray(data);
        } catch (IntegerArrayException e) {
            fail();
        }
        IntegerArray expected = new IntegerArray(7);
        expected.setElement(0, 100);
        expected.setElement(1, 255);
        expected.setElement(2, -99);
        expected.setElement(3, 755);
        expected.setElement(4, -666);
        expected.setElement(5, 1);
        expected.setElement(6, -22);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataParseArrayException")
    @Test
    public Object[][] dataParseArrayException() {
        return new Object[][]{
                {new String[]{"7", "100Ulala 255 -99 755 -666 1 -22"}},
                {new String[]{"Ulala7", "100 255 -99 755 -666 1 -22"}}
        };
    }

    @Parameters("data")
    @Test(expectedExceptions = IntegerArrayException.class,
            dataProvider = "dataParseArrayException")
    public void testParseArrayException(String[] data) throws IntegerArrayException {
        arrayParser.parseArray(data);
    }
}