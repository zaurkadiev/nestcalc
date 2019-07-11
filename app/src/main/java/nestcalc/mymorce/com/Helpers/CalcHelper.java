package nestcalc.mymorce.com.Helpers;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nestcalc.mymorce.com.Settings.Settings;

public class CalcHelper {

    private static  String LOG = "CalcHelper";
    private static  int MAX_EXPRESSION_LENGLT = Settings.MAX_EXPRESSION_LENGHT;

    public static String addToExpression(String expression, String value){

        if (expression.length() == MAX_EXPRESSION_LENGLT){
            Log.e(LOG, "Maximum lenght reached");
            return expression;
        }

        Log.e(LOG, "EXP: "+expression+" NEXT: " + value);
        if (expression.equals("")) return "0";

        List<String> digits = divideExpByOperators(expression);
        String prevNum = digits.size() == 0 ? "" : digits.get(digits.size()-1);
        String prevChar = expression.substring(expression.length()-1);

        // Добавить оператор если последний оператор. Замена опрератора.
        if (isCharOperator(value) && isCharOperator(prevChar)){
            expression = expression.substring(0, expression.length()-1) + value;
            return expression;
        }

        // Добавление точки после числа.
        if(isCharDot(value) && !isCharOperator(prevChar) && !isPercent(prevChar) && digits.size() != 0 && (
                isCharDigit(prevChar) ||
                !isCharDot(prevChar))){


            Log.e(LOG, digits.get(digits.size() - 1));
            if (!(digits.get(digits.size() - 1).contains(".") || digits.get(digits.size() - 1).contains(",")))
                expression += value;


            return expression;
        }

        // Добавление оператра
        if(isCharOperator(value) && (
                isCharDigit(prevChar) ||
                isPercent(prevChar) ||
                isDouble(prevNum) ||
                isCharConstSymbol(prevChar) ||
                isCharBracket(prevChar))){

            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Добавление чисел
        if((isCharDigit(value)) && (
                !isPercent(prevChar)
                )) {
            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Добавление склобок
        if((isCharBracket(value))) {
            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Добавить знак процента
        if(isPercent(value) && digits.size() != 0 && (
                isCharDigit(prevChar) ||
                isCharDot(prevChar) ||
                isCharBracket(prevChar)
        ) ){

            expression += value;

            return expression;
        }

        // Добавить число rand
        if(isDouble(value) && (isCharOperator(prevChar) || prevNum.equals("0"))){
            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Унарные операции
        if(isCharUnaryOperation(value) && (isDouble(prevChar) || prevNum.equals("0") || isCharOperator(prevChar) || isCharBracket(prevChar))){
            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Математический const символ
        if(isCharConstSymbol(value) && (
                isCharOperator(prevChar) ||
                prevNum.equals("0") ||
                !isCharConstSymbol(prevChar))) {

            if (expression.equals("0"))
                expression = value;
            else
                expression += value;

            return expression;
        }

        // Выражение
        if(isExpression(value)){
            if (expression.equals("0"))
                expression = value;
            else
                expression += value;
        }

        return expression;
    }

    public static String backspaceExpression(String expression){

//        String lastChar = expression.substring(expression.length()-1);
        expression = expression.substring(0, expression.length()-1);
//        if (isCharDigit(lastChar) || isCharBracket(lastChar) || isCharDot(lastChar)){
//            expression = expression.substring(0, expression.length()-1);
//        } else {
//            char[] cha = expression.toCharArray();
//            for (int i = cha.length - 1; i >= 0; i--) {
//                String lc = String.valueOf(cha[i]);
//                if (!isCharDigit(lc) && !isCharBracket(lc) && !isCharDot(lc)){
//
//                    List<String> digits = divideExpByNumbers(expression);
//                    String l = digits.get(digits.size()-1);
//                    expression = expression.substring(0, expression.length()-l.length());
//
//                    expression.
//                    break;
//                } else {
//                    break;
//                }
//            }
//        }

        if (expression.equals("")){
            return "0";
        }

        return expression;
    }

    public static boolean isCharOperator(String character){
        // display/underhood multiplication
// display/underhood multiplication
// underhood multiplication
// underhood multiplication
// display multiplication
// display multiplication
// display division
// mod
        return character.equals("+") || // display/underhood multiplication
                character.equals("-") || // display/underhood multiplication
                character.equals("/") || // underhood multiplication
                character.equals("*") || // underhood multiplication
                character.equals("×") || // display multiplication
                character.equals("^") || // display multiplication
                character.equals("÷") || // display division
                character.equals("mod");
    }

    public static boolean isCharDot(String character){
        return character.equals(".") || character.equals(",");
    }

    public static boolean isDouble(String character){
        try {
            Double db = Double.valueOf(character);
            return true;
        } catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public static boolean isCharDigit(String character){
        return character.equals("0") ||
                character.equals("1") ||
                character.equals("2") ||
                character.equals("3") ||
                character.equals("4") ||
                character.equals("5") ||
                character.equals("6") ||
                character.equals("7") ||
                character.equals("8") ||
                character.equals("9");
    }

    public static boolean isCharConstSymbol(String character){
        return character.equals("e") ||
                character.equals("π");
    }

    public static boolean isCharUnaryOperation(String character){
        // log2  -> lg
// log10 -> log
// ln
// First letters of unary operations
        return character.equals("√") ||
                character.equals("∛") ||
                character.equals("!") ||
                character.equals("log₂") || // log2  -> lg
                character.equals("log₁₀") || // log10 -> log
                character.equals("ln") || // ln
                character.equals("sin") ||
                character.equals("cos") ||
                character.equals("tan") ||
                character.equals("sinh") ||
                character.equals("cosh") ||
                character.equals("tanh") ||
                character.equals("sin⁻¹") ||
                character.equals("cos⁻¹") ||
                character.equals("tan⁻¹") ||
                character.equals("sinh⁻¹") ||
                character.equals("cosh⁻¹") ||
                character.equals("tanh⁻¹") ||

                // First letters of unary operations
                character.equals("l") ||
                character.equals("s") ||
                character.equals("c") ||
                character.equals("t") ||
                character.equals("a");
    }

    public static boolean isPercent(String character){
        return Objects.equals(character, "%");
    }

    public static boolean isCharBracket(String character){
        return character.equals("(") || character.equals(")");
    }

    public static List<String> divideExpByOperators(String expression){
        String regex = "((mod|log₂|log₁₀|ln|arcsin|arccos|arctan|sinh|cosh|tanh|sin|cos|tan)|[-+*/^÷×]|[∛√!])";
        String[] digits = expression.split(regex);
        List<String> list = new ArrayList<>();
        for (String digit : digits) {
            if (digit != null && !digit.equals(""))
                list.add(digit);
        }
        return list;
    }

    public static List<String> divideExpByNumbers(String expression){
        String regex = "(([0-9]*\\.[0-9]+|[0-9]+)|[eπ])";
        String[] digits = expression.split(regex);
        List<String> list = new ArrayList<>();
        for (String digit : digits) {
            if (digit != null && !digit.equals(""))
                list.add(digit);
        }
        return list;
    }

    public static boolean isExpression(String expression){
        String regex = "((mod|log₂|log₁₀|ln|arcsin|arccos|arctan|sinh|cosh|tanh|sin|cos|tan)|[-+*/^÷×]|[∛√!]|(([0-9]*\\.[0-9]+|[0-9]+)|[eπ]))";
        String[] digits = expression.split(regex);
        return digits.length == 0;
    }

    public static boolean isNumber(String expression){
        String regex = "(([0-9]*\\.[0-9]+|[0-9]+)|[eπE])";
        String[] digits = expression.split(regex);
        return digits.length == 0;
    }
}
