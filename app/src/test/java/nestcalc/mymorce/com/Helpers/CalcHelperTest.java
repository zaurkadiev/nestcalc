package nestcalc.mymorce.com.Helpers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by user on 07/01/2018.
 */
public class CalcHelperTest {

    @Test
    public void isExpression() throws Exception {
        String args = "1+2.7888/3*4-5mod3-360";
        Boolean actual = CalcHelper.isExpression(args);
        assertEquals(true, actual);
    }

    @Test
    public void divideExpByNumbers() throws Exception {
        String args = "1+2.7888/3*4-5mod3-360";

        String[] arg = {"+","/","*","-","mod","-"};
        List<String> expected = Arrays.asList(arg);
        List<String> actual = CalcHelper.divideExpByNumbers(args);

        assertEquals(expected, actual);
    }

    @Test
    public void isCharOperator() throws Exception {

        String args = "20+10";
        String expected = "10";
        boolean actual = CalcHelper.isCharOperator("0");
    }

    @Test
    public void divideExpByOperators1() throws Exception {
        String args = "+1+2/3*4-5mod3-360";

        String[] arg = {"1","2","3","4","5","3","360"};
        List<String> expected = Arrays.asList(arg);
        List<String> actual = CalcHelper.divideExpByOperators(args);

        assertEquals(expected, actual);
    }

    @Test
    public void isPercentDigit() throws Exception {
        String arg = "%";
        boolean ex = true;
        boolean ac = CalcHelper.isPercent(arg);
        assertEquals(ex, ac);
    }

    @Test
    public void divideExpByOperators() throws Exception {
        String arg = "1-23+23^41mod5";
        List<String> expected = Arrays.asList("1", "23", "23","41","5");

        List<String> actual = CalcHelper.divideExpByOperators(arg);
        assertEquals(expected, actual);
    }

    @Test
    public void addToExpression() throws Exception {
        String arg = "13+";
        String character = "-";
        String expected = "13-";
        String actual = CalcHelper.addToExpression(arg, character);
        assertEquals(expected, actual);
    }


    @Test
    public void divideString() throws Exception {
        String arg = "123456";
        List<String> expected = new ArrayList<>();
        expected.add("123");
        expected.add("456");
        List actual = CommonHelper.divideString(arg, 3);
        assertEquals(expected, actual);

        arg = "123456789";
        expected = new ArrayList<>();
        expected.add("12");
        expected.add("34");
        expected.add("56");
        expected.add("78");
        expected.add("9");
        actual = CommonHelper.divideString(arg, 2);
        assertEquals(expected, actual);
    }
}