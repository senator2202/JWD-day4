package test.kharitonov.day4.task1.parser;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.exception.ArrayException;
import by.kharitonov.day4.task1.parser.ArrayParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.FileAssert.fail;

public class ArrayParserTest {
    private final ArrayParser arrayParser = new ArrayParser();

    @Test
    public void testParseArray() {
        String[] data = {"7", "100 255 -99 755 -666 1 -22"};
        IntegerArray actual = null;
        try {
            actual = arrayParser.parseArray(data);
        } catch (ArrayException e) {
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
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testParseArrayException() throws ArrayException {
        String[] data = {"7", "100Ulala 255 -99 755 -666 1 -22"};
        arrayParser.parseArray(data);
    }
}