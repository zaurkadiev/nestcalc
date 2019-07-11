package nestcalc.mymorce.com.Calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 28/01/2018.
 */
public class CalculatorTest {
    @Test
    public void factorial() throws Exception {
        double a = 5;
        double ex = 120;
        double ac = Calculator.factorial(a);
        assertEquals(ex, ac, 0);

        a = 0;
        ex = 1;
        ac = Calculator.factorial(a);
        assertEquals(ex, ac, 0);

        a = 9;
        ex = 362880;
        ac = Calculator.factorial(a);
        assertEquals(ex, ac, 0);
    }

    @Test
    public void log() throws Exception {

        double a = 25;
        double b = 625;

        double ex = 2;
        double ac = Calculator.log(a, b);

        assertEquals(ex, ac, 0);
    }

    @Test
    public void multPercent() throws Exception {

        double a = 10;
        double b = 10;

        double ex = 10;
        double ac = Calculator.multPercent(a, b);

        assertEquals(ex, ac, 0);
    }

}