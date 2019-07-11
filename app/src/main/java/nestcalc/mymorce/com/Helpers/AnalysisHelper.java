package nestcalc.mymorce.com.Helpers;

import android.content.Context;

import nestcalc.mymorce.com.Calculator.Calculator;

public class AnalysisHelper {

    private static String LOG = "AnalysisHelper";

    /**
     * Преобразование строки после вычисления.
     * @param number
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static String formatOutput(String number, Context context){

        if(CommonHelper.isError(number))
            return number;

        if (CommonHelper.isNaN(number))
            return number;

        if (CommonHelper.isInfinity(number))
            return number;

        Double db = Double.valueOf(number);

        number = CommonHelper.decorateNumbers(db, context);

        return number;
    }

    /**
     * Преобразование строки до вычисления.
     * @param insertExp
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static String formatInput(String insertExp){

        insertExp = replaceDisplayOperators(insertExp);

        char[] chars = insertExp.toCharArray();
        int addedCount = 0;
        for (int i = 0; i < chars.length; i++) {
            // Обработка +- в начале строк или начале в скобках.
            if (chars[i] == '-' || chars[i] == '+'){
                if (i == 0){
                    insertExp = "0" + insertExp;
                    addedCount++;
                    continue;
                }

                if(i == chars.length - 1){
                    insertExp = insertExp + "0";
                    addedCount++;
                    continue;
                }

                char prev = chars[i-1]; //"-1-2-(-5+1-)-";
                char foll = chars[i+1];


                if(prev == '('){
                    insertExp = insertExp.substring(0, i + addedCount) + "0" + insertExp.substring(i+addedCount, insertExp.length());
                    addedCount++;
                }

                if(foll == ')'){
                    insertExp = insertExp.substring(0, i + addedCount + 1) + "0" + insertExp.substring(i+addedCount + 1, insertExp.length());
                    addedCount++;
                }
            }
        }

        chars = insertExp.toCharArray();
        addedCount = 0;
        for (int i = 0; i < chars.length; i++) {
            // Обработка * в начале строк или начале в скобках.
            if (chars[i] == '*') {
                if (i == 0) {
                    insertExp = "1" + insertExp;
                    addedCount++;
                    continue;
                }

                if (i == chars.length - 1) {
                    insertExp = insertExp + "1";
                    addedCount++;
                    continue;
                }

                char prev = chars[i - 1];
                char foll = chars[i + 1];

                if (prev == '(') {
                    insertExp = insertExp.substring(0, i + addedCount) + "1" + insertExp.substring(i + addedCount, insertExp.length());
                    addedCount++;
                }

                if (foll == ')') {
                    insertExp = insertExp.substring(0, i + addedCount + 1) + "1" + insertExp.substring(i + addedCount + 1, insertExp.length());
                    addedCount++;
                }
            }
        }

        // Перемещение знака факториал
        insertExp = replaceFactorialSymbol(insertExp);

        chars = insertExp.toCharArray();
        addedCount = 0;
        // Вставка знаков умножения в выражения (10log2, 10√2, e2, 2e, (6+6)e, (3)√2)
        for (int i = 0; i < chars.length; i++) {

            String ch = String.valueOf(chars[i]);
            String chPrev = i > 0 ? String.valueOf(chars[i-1]) : "";
            String chNext = i < chars.length - 1 ? String.valueOf(chars[i+1]) : "";

            if (ch.equals("(") || CalcHelper.isCharConstSymbol(ch) || CalcHelper.isCharUnaryOperation(ch)) {

                if (CalcHelper.isCharDigit(chPrev) || chPrev.equals(")") || CalcHelper.isCharConstSymbol(chPrev)){
                    System.out.println(ch);
                    insertExp = insertExp.substring(0, i + addedCount)+"*"+ insertExp.substring(i + addedCount, insertExp.length());
                    addedCount++;
                }
            }

            if (ch.equals(")") || CalcHelper.isCharConstSymbol(ch)){

                if (CalcHelper.isCharDigit(chNext)){
                    System.out.println(ch);
                    insertExp = insertExp.substring(0, i + addedCount + 1)+"*"+insertExp.substring(i + addedCount +1, insertExp.length());
                    addedCount++;
                }
            }
        }

        // Замена логарифма
        insertExp = insertExp.replace("log₂", "lg");
        insertExp = insertExp.replace("log₁₀", "log");


        // Обработка мат. символов(E, pi)
        insertExp = insertExp.replace("e", String.valueOf(Calculator.e()));
        insertExp = insertExp.replace("π", String.valueOf(Calculator.pi()));

        // Замена арксинусов и т.д..
        insertExp = insertExp.replace("sin⁻¹", "asin");
        insertExp = insertExp.replace("cos⁻¹", "acos");
        insertExp = insertExp.replace("tan⁻¹", "atan");
        insertExp = insertExp.replace("sinh⁻¹", "asinh");
        insertExp = insertExp.replace("cosh⁻¹", "acosh");
        insertExp = insertExp.replace("tanh⁻¹", "atanh");

        // Замена запятых на точку
        insertExp = insertExp.replace(",", ".");

        // Обработка процентов
        // Перемещение знака процента
        // insertExp = replacePercentSymbol(insertExp);

        insertExp+="+0"; // для процента
        return insertExp;
    }

    public static String replacePercentSymbol(String insertExp){

        for (int i = 0; i < insertExp.toCharArray().length; i++) {
            String character = String.valueOf(insertExp.toCharArray()[i]);
            if(character.equals("%")){
                String str = insertExp.substring(0, i);
                System.out.println(str);
                insertExp = str + insertExp.substring(i+1);
                System.out.println(str);
                for (int j = str.toCharArray().length - 1; j >= 0; j--) {
                    if(CalcHelper.isCharOperator(String.valueOf(str.toCharArray()[j]))){
                        insertExp = insertExp.substring(0, j+1) + "%" + insertExp.substring(j+1);
                        break;
                    }
                }
            }
        }

        return insertExp;
    }

    public static String replaceFactorialSymbol(String insertExp){

        if(insertExp.equals(""))
            return "";

        for (int i = 0; i < insertExp.toCharArray().length; i++) {
            String character = String.valueOf(insertExp.toCharArray()[i]);
            if(character.equals("!")){
                String str = insertExp.substring(0, i);
                System.out.println(str);
                insertExp = str + insertExp.substring(i+1);
                System.out.println(str);

                if(str.substring(str.length()-1).equals(")")){
                    for (int j = str.toCharArray().length - 1; j >= 0; j--) {
                        if (String.valueOf(str.toCharArray()[j]).equals("(")){
                            insertExp = insertExp.substring(0, j) + "!" + insertExp.substring(j);
                            break;
                        }
                    }
                }

                if(CalcHelper.isCharDigit(str.substring(str.length()-1))){
                    for (int j = str.toCharArray().length - 1; j >= 0; j--) {
                        if(!CalcHelper.isCharDigit(String.valueOf(str.toCharArray()[j]))){
                            insertExp = insertExp.substring(0, j+1) + "!" + insertExp.substring(j+1);
                            break;
                        }
                        if (j==0){
                            insertExp = "!" + str;
                        }
                        System.out.println(str);
                    }
                }
            }
        }

        return insertExp;
    }

    public static String replaceDisplayOperators(String insertExp){
        insertExp = insertExp.replaceAll("[×/]", "*");
        insertExp = insertExp.replaceAll("[÷/]", "/");
        return insertExp;
    }

}
