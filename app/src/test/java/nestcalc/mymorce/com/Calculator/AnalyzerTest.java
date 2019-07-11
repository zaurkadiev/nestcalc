package nestcalc.mymorce.com.Calculator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AnalyzerTest {
    @Test
    public void estimate() throws Exception {
        String arg = "(1+2)*4+3";
        Analyzer analyzer = new Analyzer();
        String expected = "15.0";
        String actual = analyzer.estimate(arg);
        assertEquals(expected, actual);

        arg = "(1+2)*4.2/2+3.2";
        expected = "9.5";
        actual = analyzer.estimate(arg);
        assertEquals(expected, actual);

        arg = "30/2/2+(9+9)*8/2-0.5+1";
        expected = "80.0";
        actual = analyzer.estimate(arg);
        assertEquals(expected, actual);
    }

    @Test
    public void runEstimate() throws Exception {

        List<String> opz= new ArrayList<>();
        opz.add("1");
        opz.add("2");
        opz.add("+");
        opz.add("4");
        opz.add("*");
        opz.add("3");
        opz.add("+");
        Analyzer analyzer = new Analyzer();

        String expected = "15.0";
        String actual = analyzer.runEstimate(opz);

        assertEquals(expected, actual);
    }

    @Test
    public void runPolishNotation() throws Exception {
        String arg = "( 1 + 2 ) * 4 + 3 ";
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("+");
        expected.add("4");
        expected.add("*");
        expected.add("3");
        expected.add("+");
        Analyzer analyzer = new Analyzer();
        List actual = analyzer.runPolishNotation(arg);
        assertEquals(expected, actual);

        arg = "( 12.3 + 2 ) * 4.1 + 3 ";
        expected = new ArrayList<>();
        expected.add("12.3");
        expected.add("2");
        expected.add("+");
        expected.add("4.1");
        expected.add("*");
        expected.add("3");
        expected.add("+");
        analyzer = new Analyzer();
        actual = analyzer.runPolishNotation(arg);
        assertEquals(expected, actual);
    }

    @Test
    public void checkCurrency() throws Exception {
        String arg = "2+2*3+4+(2-4)/2";
        Analyzer analyzer = new Analyzer();
        boolean isCorrect = analyzer.checkCurrency(arg);
        assertTrue(isCorrect);

        arg = "2+2*)3+4+(2-4/2";
        analyzer = new Analyzer();
        isCorrect = analyzer.checkCurrency(arg);
        assertFalse(isCorrect);
    }

    @Test
    public void format() throws Exception {
        String arg = "2+2*3+4+(2-4)/2";
        String expected = "2 + 2 * 3 + 4 + ( 2 - 4 ) / 2 ";
        Analyzer analyzer = new Analyzer();
        String actual = analyzer.format(arg);
        assertEquals(expected, actual);

        arg = "2.3/2 + 2.7/2.6";
        analyzer = new Analyzer();
        expected = "2.3 / 2 + 2.7 / 2.6 ";
        actual = analyzer.format(arg);
        assertEquals(expected, actual);

        arg = "-1-1-1-1-1-1";
        analyzer = new Analyzer();
        expected = "- 1 - 1 - 1 - 1 - 1 - 1 ";
        actual = analyzer.format(arg);
        assertEquals(expected, actual);
    }
}