package nestcalc.mymorce.com.Helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalysisHelperTest {

    @Test
    public void replaceFactorialSymbol() throws Exception {
        String args = "1*32-log56+(43+3)+54!+35";
        String expected = "1*32-log56+(43+3)+!54+35";
        String actual = AnalysisHelper.replaceFactorialSymbol(args);
        assertEquals(expected, actual);

        args = "1*32!-log56!+(43+3!)+54!+35+(3+3)!";
        expected = "1*!32-log!56+(43+!3)+!54+35+!(3+3)";
        actual = AnalysisHelper.replaceFactorialSymbol(args);
        assertEquals(expected, actual);

        args = "1*32!-log56!+(43+3!)+54!+35+(3+3)!^(35*34)!*(4!+(sin10!))";
        expected = "1*!32-log!56+(43+!3)+!54+35+!(3+3)^!(35*34)*(!4+(sin!10))";
        actual = AnalysisHelper.replaceFactorialSymbol(args);
        assertEquals(expected, actual);

        args = "2!";
        expected = "!2";
        actual = AnalysisHelper.replaceFactorialSymbol(args);
        assertEquals(expected, actual);
    }

    @Test
    public void replacePercentSymbol() throws Exception {
        String args = "1*5-3%-144+123%";
        String expected = "1*5-%3-144+%123";
        String actual = AnalysisHelper.replacePercentSymbol(args);
        assertEquals(expected, actual);

        args = "144+123%-(134-23%)";
        expected = "144+%123-(134-%23)";
        actual = AnalysisHelper.replacePercentSymbol(args);
        assertEquals(expected, actual);

        args = "10*10%-1";
        expected = "10*%10-1";
        actual = AnalysisHelper.replacePercentSymbol(args);
        assertEquals(expected, actual);

        args = "100-10%/9";
        expected = "100-%10/9";
        actual = AnalysisHelper.replacePercentSymbol(args);
        assertEquals(expected, actual);
    }

    @Test
    public void replaceDisplayOperators() throws Exception {
        String args = "1×5÷3";
        String expected = "1*5/3";
        String actual = AnalysisHelper.replaceDisplayOperators(args);
        assertEquals(expected, actual);
    }

    @Test
    public void formatInput() throws Exception {
        String arg = "-1-2-(-5+1-)-(+5-1+)+10log2+(20)5";
        String expected = "0-1-2-(0-5+1-0)-(0+5-1+0)+10*log2+(20)*5";
        String actual = AnalysisHelper.formatInput(arg);
        assertEquals(expected, actual);

        arg = "ee";
        expected = "2.718281828459045*2.718281828459045";
        actual = AnalysisHelper.formatInput(arg);
        assertEquals(expected, actual);

        arg = "((sin(((+)97)×)×).1×)*()+)-)+51×)";
        expected = "((sin(((0+0)*97)*1)*1).1*1)*()+0)-0)+51*1)";
        actual = AnalysisHelper.formatInput(arg);
        assertEquals(expected, actual);

    }

//    @Test
//    public void formatOutputTest() throws Exception {
//        String arg = "74.0";
//        String expected = "74";
//        String actual = AnalysisHelper.formatOutput(arg);
//        assertEquals(expected, actual);
//
//        arg = "74.564";
//        expected = "74.564";
//        actual = AnalysisHelper.formatOutput(arg);
//        assertEquals(expected, actual);
//    }

}