package nestcalc.mymorce.com.Calculator;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Analyzer {

    private int erPosition = 0;
    private boolean showMessage = false;
    private String LOG = getClass().getName();
    private int angleType;

    public final static int ANGLE_DEG = 0;
    public final static int ANGLE_RAD = 1;

    int LIST_CAPACITY = 1000;
    int OPERATORS_CAPACITY = 1000;

    public Analyzer(){

    }

    public Analyzer(int angleType){
        this.angleType = angleType;
    }

    public void setAngleType(int angleType) {
        this.angleType = angleType;
    }

    public int getAngleType() {
        return angleType;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    /**
     * Проверка скобок в выражении
     * @return верно ли выражение относительно скобок
     */
    public boolean checkCurrency(String expression) {
        boolean correct = true;
        int num = 0;
        for (int i=0; i < expression.length();i++)
        {
            if (expression.toCharArray()[i] == '(')
            {
                num++;
            }
            else
            {
                if (expression.toCharArray()[i] == ')')
                {
                    if (i > 0 && expression.toCharArray()[i-1] == '(') // ()
                    {
                        correct = false;
                        erPosition = i;
                        return correct;
                    }
                    num--;
                }
            }
            if (num < 0)
            {
                correct = false;
                erPosition = i;
                return correct;
            }
        }
        if (num != 0)
        {
            erPosition = expression.length();
            correct = false;
        }
        return correct;
    }

    /**
     * Форматирование выражение проставление проболов. В конце всегда пробел "_"
     * @return Форматированное выражение
     */
    public String format(String expression)
    {
        StringBuilder formstr = new StringBuilder();
        String prev = "";

        if (expression.length() <= 65536) {
            for (int i = 0; i < expression.length(); i++) {
                switch (expression.toCharArray()[i])
                {
                    case '0':
                    {
                        if (Objects.equals(prev, "число") || Objects.equals(prev, ""))
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {
                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '1':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '2':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '3':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '4':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '5':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '6':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '7':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '8':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case '9':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case 'E':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {
                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    case ' ':
                    {
                        break;
                    }
                    case '+':
                    {
                        if (prev != "")
                        {
                            if (prev != "оператор" && prev !="(" && prev != "унарный оператор")
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]);
                            }
                            else
                            {
                                if (prev == "оператор" || prev =="унарный оператор")
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(),"(+)Неверная синтаксическая конструкция входного выражения!");
                                    }
                                    return "&Error 03";
                                }
                            }

                        }
                        else
                        {
                            //ошибка!
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "оператор";
                        break;
                    }
                    case '-':
                    {
                        if (prev != "")
                        {
                            if (prev != "оператор" && prev != "(" && prev != "унарный оператор")
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]);
                            }
                            else
                            {
                                if (prev == "оператор" || prev == "унарный оператор")
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                                    }
                                    return "&Error 03";
                                }
                            }
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "оператор";
                        break;
                    }
                    case '*':
                    {

                        if (prev != "" )
                        {
                            if (prev != "оператор" && prev != "(" && prev != "унарный оператор")
                            {
                                formstr.append(" " + expression.toCharArray()[i]);
                            }
                            else
                            {
                                if (prev == "оператор" || prev == "унарный оператор")
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                                    }
                                    return "&Error 03";
                                }
                            }
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "оператор";
                        break;
                    }
                    case '/':
                    {

                        if (prev != "" )
                        {
                            if (prev != "оператор" && prev != "(" && prev != "унарный оператор")
                            {
                                formstr.append(" " + expression.toCharArray()[i]);
                            }
                            else
                            {
                                if (prev == "оператор" || prev == "унарный оператор")
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                                    }
                                    return "&Error 03";
                                }
                            }
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "оператор";
                        break;
                    }
                    case '^':
                    {

                        if (prev != "" )
                        {
                            if (prev != "оператор" && prev != "(" && prev != "унарный оператор")
                            {
                                formstr.append(" " + expression.toCharArray()[i]);
                            }
                            else
                            {
                                if (prev == "оператор" || prev == "унарный оператор")
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                                    }
                                    return "&Error 03";
                                }
                            }
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "оператор";
                        break;
                    }
                    case '(':
                    {

                        if (prev != "число")
                        {
                            if (prev != "")
                            {
                                formstr.append(" " + expression.toCharArray()[i]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]);
                            }
                        } else {
                            if (showMessage)
                            {
                                Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                            }
                            return "&Error 03";
                        }
                        prev = "(";
                        break;
                    }
                    case ')':
                    {

                        if (prev != "оператор" && prev != "унарный оператор")
                        {
                            if (prev != "")
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]);
                            }
                        }
                        else
                        {
                            if (showMessage)
                            {
                                Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                            }
                            return "&Error 03";
                        }
                        prev = ")";
                        break;
                    }
                    case 'm'://mod
                    {
                        if (i + 1 < expression.length() && expression.toCharArray()[i + 1] == 'o' && expression.toCharArray()[i + 2] == 'd')
                        {
                            if (prev != "")
                            {
                                if (prev != "оператор")
                                {
                                    formstr.append(" " + expression.toCharArray()[i] + expression.toCharArray()[i + 1] + expression.toCharArray()[i + 2]);
                                }
                                else
                                {
                                    if (showMessage)
                                    {
                                        Log.d(getClass().getName(), "Два подряд оператора на " + i + " символе.");
                                    }
                                    return "&Error 04 at " + i;
                                }
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i] + expression.toCharArray()[i + 1] + expression.toCharArray()[i + 2]);
                            }
                            prev = "оператор";
                            i += 2;
                        }
                        else
                        {
                            if (prev != "")
                            {
                                formstr.append(" " + expression.toCharArray()[i]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]);
                            }
                            prev = "унарный оператор";
                        }
                        break;
                    }
                    case 'l': // Логарифмы
                    {
                        // lg -> log2
                        if (i + 1 < expression.length() && expression.toCharArray()[i + 1] == 'g' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i + 1]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]);
                            }
                            prev = "унарный оператор";
                            i += 1;
                            break;
                        }

                        // ln -> ln
                        if (i + 1 < expression.length() && expression.toCharArray()[i + 1] == 'n' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i + 1]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i + 1]);
                            }
                            prev = "унарный оператор";
                            i += 1;
                            break;
                        }

                        // log -> log10
                        if (i + 1 < expression.length() && expression.toCharArray()[i + 1] == 'o' && expression.toCharArray()[i + 2] == 'g')
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i + 1]).append(expression.toCharArray()[i + 2]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i + 1]).append(expression.toCharArray()[i + 2]);
                            }
                            prev = "унарный оператор";
                            i += 2;
                            break;
                        }

                        break;
                    }
                    case 's': // Синус SIN, SINH
                    {
                        // sinh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'i' &&
                                expression.toCharArray()[i + 2] == 'n' &&
                                expression.toCharArray()[i + 3] == 'h') {

                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        // sin
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'i' &&
                                expression.toCharArray()[i + 2] == 'n' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2]);
                            }
                            prev = "унарный оператор";
                            i += 2;
                            break;
                        }

                        break;
                    }
                    case 'c': // Косинус COS, COSH
                    {
                        // cosh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'o' &&
                                expression.toCharArray()[i + 2] == 's' &&
                                expression.toCharArray()[i + 3] == 'h')
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]).append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]).append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        // cos
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'o' &&
                                expression.toCharArray()[i + 2] == 's' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]);
                            }
                            prev = "унарный оператор";
                            i += 2;
                            break;
                        }

                        break;
                    }
                    case 't': // Косинус TAN, TANH
                    {
                        // tanh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'a' &&
                                expression.toCharArray()[i + 2] == 'n' &&
                                expression.toCharArray()[i + 3] == 'h')
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]).append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]).append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        // tan
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'a' &&
                                expression.toCharArray()[i + 2] == 'n' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ").append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i]).append(expression.toCharArray()[i+1]).append(expression.toCharArray()[i+2]);
                            }
                            prev = "унарный оператор";
                            i += 2;
                            break;
                        }

                        break;
                    }
                    case 'a': // Косинус TAN, TANH
                    {
                        // asinh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 's' &&
                                expression.toCharArray()[i + 2] == 'i' &&
                                expression.toCharArray()[i + 3] == 'n' &&
                                expression.toCharArray()[i + 4] == 'h' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            prev = "унарный оператор";
                            i += 4;
                            break;
                        }

                        // acosh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'c' &&
                                expression.toCharArray()[i + 2] == 'o' &&
                                expression.toCharArray()[i + 3] == 's' &&
                                expression.toCharArray()[i + 4] == 'h' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            prev = "унарный оператор";
                            i += 4;
                            break;
                        }

                        // atanh
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 't' &&
                                expression.toCharArray()[i + 2] == 'a' &&
                                expression.toCharArray()[i + 3] == 'n' &&
                                expression.toCharArray()[i + 4] == 'h' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3])
                                        .append(expression.toCharArray()[i+4]);
                            }
                            prev = "унарный оператор";
                            i += 4;
                            break;
                        }

                        // asin
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 's' &&
                                expression.toCharArray()[i + 2] == 'i' &&
                                expression.toCharArray()[i + 3] == 'n') {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        // acos
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 'c' &&
                                expression.toCharArray()[i + 2] == 'o' &&
                                expression.toCharArray()[i + 3] == 's' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        // atan
                        if (i + 1 < expression.length() &&
                                expression.toCharArray()[i + 1] == 't' &&
                                expression.toCharArray()[i + 2] == 'a' &&
                                expression.toCharArray()[i + 3] == 'n' )
                        {
                            if (!Objects.equals(prev, ""))
                            {
                                formstr.append(" ")
                                        .append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            else
                            {
                                formstr.append(expression.toCharArray()[i])
                                        .append(expression.toCharArray()[i+1])
                                        .append(expression.toCharArray()[i+2])
                                        .append(expression.toCharArray()[i+3]);
                            }
                            prev = "унарный оператор";
                            i += 3;
                            break;
                        }

                        break;
                    }
                    case '!':
                    {
                        if (!Objects.equals(prev, ""))
                        {
                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "унарный оператор";
                        break;
                    }
                    case '√':
                    {
                        if (!Objects.equals(prev, ""))
                        {
                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "унарный оператор";
                        break;
                    }
                    case '∛':
                    {
                        if (!Objects.equals(prev, ""))
                        {
                            formstr.append(" ").append(expression.toCharArray()[i]);
                        }
                        else
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        prev = "унарный оператор";
                        break;
                    }
                    // для вычисления значений double
                    case '.':
                    {
                        if(prev == "число"){
                            formstr.append(expression.toCharArray()[i]);
                        } else {
                            if (showMessage)
                            {
                                Log.d(getClass().getName(), "Неверная синтаксическая конструкция входного выражения!");
                            }
                            return "&Error 03";
                        }
                        prev = "число";
                        break;
                    }
                    // процент
                    case '%':
                    {
                        if (prev == "число" || prev == "")
                        {
                            formstr.append(expression.toCharArray()[i]);
                        }
                        else
                        {

                            formstr.append(" " + expression.toCharArray()[i]);
                        }
                        prev = "число";
                        break;
                    }
                    default:
                    {
                        if (showMessage)
                        {
                            Log.d(getClass().getName(), "Неизвестный оператор на " + i + " символе.");
                        }
                        return "&Error 02 at " + i;
                    }
                }
            }
        } else {
            if (showMessage)
            {
                Log.d(getClass().getName(), "Слишком длинное выражение. Максмальная длина - 65536 символов.");
            }
            return "&Error 07";
        }
        if (prev != "оператор" && prev != "унарный оператор") {
//            Log.e(LOG, "Форматированная строка ->"+formstr.toString());
            return formstr + " ";
        } else {
            if (showMessage)
            {
                Log.d(getClass().getName(), "Незаконченное выражение. ");
            }
            return "&Error 05";
        }
    }


    /**
     * Перевод строки в польскую нотацию перед вычислением
     * @return Список в польской нотации
     */
    public List<String> runPolishNotation(String formatedExpression){

        List<String> resList = new ArrayList<>();
        List<String> operList = new ArrayList<>();

        String expr = formatedExpression;

        while (!Objects.equals(expr, ""))
        {
            String op = expr.substring(0, expr.indexOf(' '));
            expr = expr.substring(expr.indexOf(' ') + 1);
            switch (op)
            {
                case "(": //1
                {
                    //Кладем в стэк
                    operList.add(op);
                    break;
                }
                case "√":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "m") ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "∛":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                Objects.equals(operList.get(operList.size() - 1), "√") ||
                                Objects.equals(operList.get(operList.size() - 1), "log") ||
                                Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "!":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "ln":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                Objects.equals(operList.get(operList.size() - 1), "√") ||
                                Objects.equals(operList.get(operList.size() - 1), "log") ||
                                Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "lg":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "log":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "sin":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "sinh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "cos":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "cosh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "tan":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "tanh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "asin":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "acos":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "atan":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "asinh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "acosh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "atanh":
                {
                    //вытаскиваем из стека все операторы чей приоритет больше либо равен унарному минусу
                    while (operList.size() != 0 && (
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнгение стека - возвращем null
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "*": //3
                {
                    while (operList.size() != 0 && (
                            Objects.equals(operList.get(operList.size() - 1), "*") ||
                            Objects.equals(operList.get(operList.size() - 1), "/") ||
                            Objects.equals(operList.get(operList.size() - 1), "^") ||
                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if ( LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "/": //3
                {
                    while (operList.size() != 0 && (
                            Objects.equals(operList.get(operList.size() - 1), "*") ||
                            Objects.equals(operList.get(operList.size() - 1), "/") ||
                            Objects.equals(operList.get(operList.size() - 1), "^") ||
                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "^": //3
                {
                    while (operList.size() != 0 && (
//                            Objects.equals(operList.get(operList.size() - 1), "*") ||
//                            Objects.equals(operList.get(operList.size() - 1), "/") ||
//                            Objects.equals(operList.get(operList.size() - 1), "^") ||
//                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
//                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if ( LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "mod": //3
                {
                    while (operList.size() != 0 &&
                            (
//                            Objects.equals(operList.get(operList.size() - 1), "*") ||
//                            Objects.equals(operList.get(operList.size() - 1), "/") ||
//                            Objects.equals(operList.get(operList.size() - 1), "^") ||
//                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
//                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size() - 1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "+": //2
                {
                    while (operList.size() != 0 && (
                            Objects.equals(operList.get(operList.size() - 1), "*") ||
                            Objects.equals(operList.get(operList.size() - 1), "/") ||
                            Objects.equals(operList.get(operList.size() - 1), "^") ||
                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                            Objects.equals(operList.get(operList.size() - 1), "+") ||
                            Objects.equals(operList.get(operList.size() - 1), "-") ||
                            Objects.equals(operList.get(operList.size() - 1), "-%") ||
                            Objects.equals(operList.get(operList.size() - 1), "+%") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case "-": //2
                {
                    while ((operList.size() != 0) && (
                            Objects.equals(operList.get(operList.size() - 1), "*") ||
                            Objects.equals(operList.get(operList.size() - 1), "/") ||
                            Objects.equals(operList.get(operList.size() - 1), "^") ||
                            Objects.equals(operList.get(operList.size() - 1), "mod") ||
                            Objects.equals(operList.get(operList.size() - 1), "+") ||
                            Objects.equals(operList.get(operList.size() - 1), "-") ||
                            Objects.equals(operList.get(operList.size() - 1), "-%") ||
                            Objects.equals(operList.get(operList.size() - 1), "+%") ||
                            Objects.equals(operList.get(operList.size() - 1), "*%") ||
                            Objects.equals(operList.get(operList.size() - 1), "/%") ||
                                    Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                    Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                    Objects.equals(operList.get(operList.size() - 1), "√") ||
                                    Objects.equals(operList.get(operList.size() - 1), "∛") ||
                                    Objects.equals(operList.get(operList.size() - 1), "log") ||
                                    Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                    Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.add(op);
                    break;
                }
                case ")":
                {
                    while (!Objects.equals(operList.get(operList.size() - 1), "("))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    operList.remove(operList.size()-1);
                    while (operList.size() != 0 && (
                            Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                            Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                            Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                            Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                            Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                            Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                            Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                            Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                            Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                            Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                            Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                            Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                            Objects.equals(operList.get(operList.size() - 1), "!")  ||
                            Objects.equals(operList.get(operList.size() - 1), "√")  ||
                            Objects.equals(operList.get(operList.size() - 1), "∛")  ||
                            Objects.equals(operList.get(operList.size() - 1), "log")||
                            Objects.equals(operList.get(operList.size() - 1), "lg") ||
                            Objects.equals(operList.get(operList.size() - 1), "ln")))
                    {
                        if (LIST_CAPACITY > resList.size())
                        {
                            resList.add(operList.get(operList.size()-1));
                            operList.remove(operList.size()-1);
                        }
                        else
                        {
                            // переполнение стека - возвращем null
                            return null;
                        }
                    }
                    break;
                }
                default:
                {
                    //на входе - число - помещаем в выходной массив
                    if (LIST_CAPACITY > resList.size())
                    {
                        resList.add(op);
                        while (operList.size() != 0 && (
                                Objects.equals(operList.get(operList.size() - 1), "sin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "sinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "cos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "cosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "tan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "tanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "asin")  ||
                                Objects.equals(operList.get(operList.size() - 1), "asinh") ||
                                Objects.equals(operList.get(operList.size() - 1), "acos")  ||
                                Objects.equals(operList.get(operList.size() - 1), "acosh") ||
                                Objects.equals(operList.get(operList.size() - 1), "atan")  ||
                                Objects.equals(operList.get(operList.size() - 1), "atanh") ||
                                Objects.equals(operList.get(operList.size() - 1), "!")  ||
                                Objects.equals(operList.get(operList.size() - 1), "√")  ||
                                Objects.equals(operList.get(operList.size() - 1), "∛")  ||
                                Objects.equals(operList.get(operList.size() - 1), "log")||
                                Objects.equals(operList.get(operList.size() - 1), "lg") ||
                                Objects.equals(operList.get(operList.size() - 1), "ln")))
                        {
                            if (LIST_CAPACITY > resList.size())
                            {
                                resList.add(operList.get(operList.size()-1));
                                operList.remove(operList.size()-1);
                            }
                            else
                            {
                                // переполнение стека - возвращем null
                                return null;
                            }
                        }
                    }
                    else {
                        return null;
                    }
                    break;
                }
            }
        }
        //записываем все что осталовь в стеке в выходной массив
        while (operList.size() != 0)
        {
            resList.add(operList.get(operList.size()-1));
            operList.remove(operList.size()-1);
        }
        return resList;
    }

    /**
     * Вычисление из польской нотации
     * @return Вычесленное значение выражения
     */
    public String runEstimate(List<String> opz) {
        Log.e(LOG, opz.toString());
        boolean endwork = false;
        // этот цикл будет выполняться до тех пор , пока в массиве не остануться одни числа
        while (!endwork)
        {
            int i = 0;
            boolean found = false;
            //этот цикл выполняется до тех пор, пока не будет найден первый оператор
            while (i < opz.size() && !found)
            {
                found = true;
                try
                {
                    switch (opz.get(i))
                    {
                        case "%": {
                            // Если процент стоит отдельно, тогда спарить его с
                            // Предыдущим числом
                            opz.set(i-1, opz.get(i-1)+opz.get(i));
                            opz.remove(i);
                            break;
                        }
                        // БИНАРНЫЕ ОПЕРАЦИИ
                        case "+":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                opz.set(i - 2, String.valueOf(a+(a*b)/100));
                            } else {
                                b = Double.valueOf(bs);
                                opz.set(i - 2, String.valueOf(Calculator.sum(a, b)));
                            }

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        case "-":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                opz.set(i - 2, String.valueOf(a-(a*b)/100));
                            } else {
                                b = Double.valueOf(bs);
                                opz.set(i - 2, String.valueOf(Calculator.minus(a, b)));
                            }

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        case "*":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                b = b / 100;
                            } else {
                                b = Double.valueOf(bs);
                            }

                            opz.set(i - 2, String.valueOf(Calculator.mult(a, b)));

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        case "/":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                b = b / 100;
                            } else {
                                b = Double.valueOf(bs);
                            }

                            opz.set(i - 2, String.valueOf(Calculator.div(a, b)));

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        case "^":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                b = b / 100;
                            } else {
                                b = Double.valueOf(bs);
                            }

                            opz.set(i - 2, String.valueOf(Calculator.pow(a, b)));

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        case "mod":
                        {
                            String as = opz.get(i - 2);
                            double a;
                            if(as.contains("%")){
                                as = as.replace("%", "");
                                a = Double.valueOf(as);
                                a = a / 100;
                            }
                            else {
                                a = Double.valueOf(as);
                            }

                            String bs = opz.get(i - 1);
                            double b;
                            if(bs.contains("%")){
                                bs = bs.replace("%", "");
                                b = Double.valueOf(bs);
                                b = b / 100;
                            } else {
                                b = Double.valueOf(bs);
                            }

                            opz.set(i - 2, String.valueOf(Calculator.mod(a, b)));

                            opz.remove(i - 1);
                            opz.remove(i - 1);
                            break;
                        }
                        // УНАРНЫЕ ОПЕРАЦИИ
                        case "m":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.IABS(a)));
                            opz.remove(i);
                            break;
                        }
                        case "p":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.ABS(a)));
                            opz.remove(i);
                            break;
                        }
                        case "√":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.sqrt(a)));
                            opz.remove(i);
                            break;
                        }
                        case "∛":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.cbrt(a)));
                            opz.remove(i);
                            break;
                        }
                        case "!":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.factorial(a)));
                            opz.remove(i);
                            break;
                        }
                        case "ln": // ln
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.log(Math.E, a)));
                            opz.remove(i);
                            break;
                        }
                        case "lg": // log2 Американский формат
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.log(2, a)));
                            opz.remove(i);
                            break;
                        }
                        case "log": // log10
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.log(10, a)));
                            opz.remove(i);
                            break;
                        }
                        case "sin":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.sin((2*Calculator.pi()/360)*a);
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.sin(a);
                                    break;
                                default:
                                    res = Calculator.sin(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "sinh":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.sinh(a)));
                            opz.remove(i);
                            break;
                        }
                        case "cos":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.cos((2*Calculator.pi()/360)*a);
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.cos(a);
                                    break;
                                default:
                                    res = Calculator.cos(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "cosh":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.cosh(a)));
                            opz.remove(i);
                            break;
                        }
                        case "tan":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.tan((2*Calculator.pi()/360)*a);
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.tan(a);
                                    break;
                                default:
                                    res = Calculator.tan(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "tanh":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.tanh(a)));
                            opz.remove(i);
                            break;
                        }
                        case "asin":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.asin(a)*360/(2*Calculator.pi());
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.asin(a);
                                    break;
                                default:
                                    res = Calculator.asin(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "acos":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.acos(a)*360/(2*Calculator.pi());
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.acos(a);
                                    break;
                                default:
                                    res = Calculator.acos(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "atan":
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            Double res;
                            switch (angleType){
                                case ANGLE_DEG:
                                    res = Calculator.atan(a)*360/(2*Calculator.pi());
                                    break;
                                case ANGLE_RAD:
                                    res = Calculator.atan(a);
                                    break;
                                default:
                                    res = Calculator.atan(a);
                            }

                            opz.set(i - 1, String.valueOf(res));
                            opz.remove(i);
                            break;
                        }
                        case "asinh": // log10
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.asinh(a)));
                            opz.remove(i);
                            break;
                        }
                        case "acosh": // log10
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.acosh(a)));
                            opz.remove(i);
                            break;
                        }
                        case "atanh": // log10
                        {
                            Double a = Double.valueOf(opz.get(i - 1));
                            opz.set(i - 1, String.valueOf(Calculator.atanh(a)));
                            opz.remove(i);
                            break;
                        }
                        default:
                        {
                            found = false;
                            break;
                        }
                    }
                }
                catch (IllegalArgumentException ex)
                {
                    if (showMessage)
                    {
                        Log.e(getClass().getName(),"Ошибка деления на 0");
                    }
                    ex.getMessage();
                    return "Error 09";
                }
                catch (IndexOutOfBoundsException ex)
                {
                    //Перехватили ошибку переполнения - выдаем сообщение
                    if (showMessage)
                    {
                        Log.e( getClass().getName(),"Слишком малое или слишком большое значение числа для int\n Числа должны быть в пределах от -2147483648 до 2147483647");
                    }
                    ex.getMessage();
                    return "Error 06";
                }
                //catch (ArgumentOutOfRangeException ex)
                //{
                //    if (ShowMessage)
                //    {
                //        MessageBox.Show("Неверная синтаксическая конструкция входного выражения!");
                //    }
                //    Program.res = 3;
                //    return "Error 03";
                //}
                i++;
            }
            if (!found && i == opz.size())
            {
                endwork = true;
            }
//            string le = CalcClass.lastError;
//            if (le != "")
//            {
//                return le;
//            }
        }
        if (opz.size() != 1)
        {
            // в результате вычислений в массиве осталось несколько чисел - значит где-то ошибка в выражении, которую мы не отловили на более раннем этапе.
            if (showMessage)
            {
                Log.e(getClass().getName(),"(?)Неверная синтаксическая конструкция входного выражения!");
                Log.e(LOG, opz.toString());
            }
            return "Error 03";
        }
        else
        {
            return opz.get(0);
        }
    }

    /**
     * Вычисление строки
     * @return результат вычисления строки
     */
    public String estimate(String expression) {

        if (checkCurrency(expression)) {
            String formated;
            try {
                formated = format(expression);
            } catch (Exception e){
                e.getMessage();
                formated = "&Error 03";
            }

            if (formated.toCharArray()[0] == '&') {
                return formated.substring(1);
            }

            List<String> opz = runPolishNotation(formated);
            if (opz != null)
            {
                return runEstimate(opz);
            }
            else
            {
                if (showMessage)
                {
                    Log.e(getClass().getName(),"Суммарное количество чисел и операторов превышает 30!");
                }
                return "Error 08";
            }
        }
        else
        {
            if (showMessage)
            {
                Log.e(getClass().getName(), "Неправильная скобочная структура, ошибка на " +
                        erPosition + " символе !");
            }
            return "Error 01 at "+ erPosition;
        }
    }

}
