package nestcalc.mymorce.com.Calculator;


public class Calculator {

    public static double sum(double a, double b){
        return a + b;
    }

    public static double div(double a, double b){
        return a / b;
    }

    public static double mult(double a, double b){
        return a * b;
    }

    public static double minus(double a, double b){
        return a - b;
    }

    public static double exp(double a){
        return Math.exp(a);
    }

    public static double power(double a, double b){
        return Math.pow(a,b);
    }

    public static double mod(double a, double b){
        return a % b;
    }

    public static double IABS(double a){
        return -Math.abs(a);
    }
    public static double ABS(double a){
        return Math.abs(a);
    }

    public static double minusPercent(double a, double b){
        return a - a * b / 100;
    }

    public static double plusPercent(double a, double b){
        return a + a * b / 100;
    }

    public static double divPercent(double a, double b){
        return a / (a * b / 100);
    }

    public static double multPercent(double a, double b){
        return a * a * b / 100;
    }

    public static double random(){
        return Math.random();
    }

    public static double sqrt(double a){
        return Math.sqrt(a);
    }

    public static double cbrt(double a){
        return Math.cbrt(a);
    }

    public static double pow(double a, double b){
        return Math.pow(a,b);
    }

    public static double e(){
        return Math.E;
    }

    public static double pi(){
        return Math.PI;
    }

    public static double log(double base, double a){
        return Math.log(a) / Math.log(base);
    }

    public static double sin(double a){
        return Math.sin(a);
    }

    public static double sinh(double a){
        return Math.sinh(a);
    }

    public static double cos(double a){
        return Math.cos(a);
    }

    public static double cosh(double a){
        return Math.cosh(a);
    }

    public static double tan(double a){
        return Math.tan(a);
    }

    public static double tanh(double a){
        return Math.tanh(a);
    }

    public static double asin(double a){
        return Math.asin(a);
    }

    public static double acos(double a){
        return Math.acos(a);
    }

    public static double atan(double a){
        return Math.atan(a);
    }

    public static double asinh(double x)
    {
        return Math.log(x + Math.sqrt(x*x + 1.0));
    }

    public static double acosh(double x)
    {
        return Math.log(x + Math.sqrt(x*x - 1.0));
    }

    public static double atanh(double x)
    {
        return 0.5*Math.log( (x + 1.0) / (x - 1.0) );
    }

    public static double factorial(double x)
    {
        double d = 1d;

        for (int i = 1; i <= x; i++) {
            d *= i;
        }
        return d;
    }
}
