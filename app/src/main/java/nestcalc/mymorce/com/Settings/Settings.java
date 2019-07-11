package nestcalc.mymorce.com.Settings;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import nestcalc.mymorce.com.Helpers.CommonHelper;

public class Settings {
    private SharedPreferences mSettingsPrefs;
    private String SETTINGS_SHARED_PREFS_KEY = "settings_shared_prefs_key";
    private String LOG = getClass().getName();

    private Context context;

    public static int MAX_EXPRESSION_LENGHT = 200;

    // MR
    private String SETTINGS_MR_COPIES = "settings_mr_copies";
    private int SETTINGS_MR_COPIES_DEFAULT = 0;
    public  static final int SETTINGS_MR_COPIES_OPTION_EXPRESSION = 0;
    public  static final int SETTINGS_MR_COPIES_OPTION_RESULT = 1;

    // MAX HISTORY SIZE
    private String SETTINGS_MAX_HISTORY_SIZE = "settings_max_number_of_items_in_history";
    public static int SETTINGS_MAX_HISTORY_SIZE_DEFAULT = 2;
    public static String[] SETTINGS_MAX_HISTORY_SIZE_OPTIONS = {"10", "50", "100", "500", "1000"};

    // SAVE WHEN ROTATE
    private String SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE= "settings_save_expression";
    private Boolean SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE_DEFAULT = true;

    // RAND
    private String SETTINGS_RAND_SIGN_AMOUNT = "settings_rand_sign_amount";
    private int SETTINGS_RAND_SIGN_AMOUNT_DEFAULT = 0;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_1 = 0;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_2 = 1;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_3 = 2;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_4 = 3;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_5 = 4;
    public  static final int SETTINGS_RAND_SIGN_AMOUNT_OPTION_6 = 5;

    // DATE IN RECORDS
    private String SETTINGS_IS_DATE_IN_HISTORY_ITEMS = "settings_is_date_in_history";
    private Boolean SETTINGS_IS_DATE_IN_HISTORY_ITEMS_DEFAULT = false;

    // DATE FORMAT
    private String SETTINGS_DATE_FORMAT = "settings_date_format";
    private int SETTINGS_DATE_FORMAT_DEFAULT = 0;
    public  static final int SETTINGS_DATE_FORMAT_OPTION_1 = 0;
    public  static final int SETTINGS_DATE_FORMAT_OPTION_2 = 1;
    public  static final int SETTINGS_DATE_FORMAT_OPTION_3 = 2;

    // DECIMAL SEPARATOR
    public String SETTINGS_DECIMAL_SEPARATOR = "settings_decimal_separator";
    public static final int SETTINGS_DECIMAL_SEPARATOR_DEFAULT = 0; // locale
    public static final int SETTINGS_DECIMAL_SEPARATOR_OPTION_1 = 0;// locale
    public static final int SETTINGS_DECIMAL_SEPARATOR_OPTION_2 = 1;// dot
    public static final int SETTINGS_DECIMAL_SEPARATOR_OPTION_3 = 2;// comma

    // GROUP SEPARATOR
    public String SETTINGS_GROUP_SEPARATOR = "settings_group_separator";
    public static final int SETTINGS_GROUP_SEPARATOR_DEFAULT = 0; //space
    public static final int SETTINGS_GROUP_SEPARATOR_OPTION_1 = 0;//space
    public static final int SETTINGS_GROUP_SEPARATOR_OPTION_2 = 1;//opostrof

    public Settings(Context context){
        this.context = context;
        mSettingsPrefs = context.getSharedPreferences(SETTINGS_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public int getMrCopies(){
        return mSettingsPrefs.getInt(SETTINGS_MR_COPIES, SETTINGS_MR_COPIES_DEFAULT);
    }

    public void setMrCopies(int option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_MR_COPIES, option);
        editor.apply();
    }

    public String getMrCopiesDisplay(int option){
        switch (option){
            case SETTINGS_MR_COPIES_OPTION_EXPRESSION:{
                return "Expression";
            }
            case SETTINGS_MR_COPIES_OPTION_RESULT:{
                return "Result";
            }
            default:
                return "Expression";
        }
    }

    public int getRandomFormat(){
        return mSettingsPrefs.getInt(SETTINGS_RAND_SIGN_AMOUNT, SETTINGS_RAND_SIGN_AMOUNT_DEFAULT);
    }

    public void setRandomFormat(int option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_RAND_SIGN_AMOUNT, option);
        editor.apply();
    }

    public String getRandomFormatDisplay(int option){
        Random random = new Random(1994);
        String rand = String.valueOf(random.nextDouble());
        switch (option){
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_1:
                rand = rand.substring(0, 3);
                break;
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_2:
                rand = rand.substring(0, 4);
                break;
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_3:
                rand = rand.substring(0, 5);
                break;
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_4:
                rand = rand.substring(0, 6);
                break;
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_5:
                rand = rand.substring(0, 7);
                break;
            case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_6:
                rand = rand.substring(0, 8);
                break;
            default:
                rand = rand.substring(0, 3);
                break;

        }
        return rand;
    }

    public Boolean isDateInHistoryItems(){
        return mSettingsPrefs.getBoolean(SETTINGS_IS_DATE_IN_HISTORY_ITEMS, SETTINGS_IS_DATE_IN_HISTORY_ITEMS_DEFAULT);
    }

    public void setIsDateInHistoryItems(Boolean option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putBoolean(SETTINGS_IS_DATE_IN_HISTORY_ITEMS, option);
        editor.apply();
    }

    public int getDateFormat(){
        return mSettingsPrefs.getInt(SETTINGS_DATE_FORMAT, SETTINGS_DATE_FORMAT_DEFAULT);
    }

    public void setDateFormat(int option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_DATE_FORMAT, option);
        editor.apply();
    }

    public String getDateFormatDisplay(int option){
        String string;
        long time = System.currentTimeMillis();
        DateFormat df;

        switch (option){
            case SETTINGS_DATE_FORMAT_OPTION_1:
                string = CommonHelper.getRelativeDate(1518732445577L, context);
                break;
            case SETTINGS_DATE_FORMAT_OPTION_2:
                df = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
                string = df.format(time);
                break;
            case SETTINGS_DATE_FORMAT_OPTION_3:
                df = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
                string = df.format(time);
                df = SimpleDateFormat.getTimeInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
                string += " " + df.format(time);
                break;
            default:
                string = CommonHelper.getRelativeDate(1518732445577L, context);
        }
        return string;
    }

    public Boolean getIsSaveExpressionWhenRotate(){
        return mSettingsPrefs.getBoolean(SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE, SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE_DEFAULT);
    }

    public void setIsSaveExpressionWhenRotate(Boolean option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putBoolean(SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE, option);
        editor.apply();
    }

    public int getHistorySize(){
        return mSettingsPrefs.getInt(SETTINGS_MAX_HISTORY_SIZE, SETTINGS_MAX_HISTORY_SIZE_DEFAULT);
    }

    public void setHistorySize(Integer option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_MAX_HISTORY_SIZE, option);
        editor.apply();
    }

    public String getHistorySizeDisplay(Integer option){
        return SETTINGS_MAX_HISTORY_SIZE_OPTIONS[option];
    }

    public void setDecimalSeparator(int option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_DECIMAL_SEPARATOR, option);
        editor.apply();
    }

    public int getDecimalSeparator(){
        return mSettingsPrefs.getInt(SETTINGS_DECIMAL_SEPARATOR, SETTINGS_DECIMAL_SEPARATOR_DEFAULT);
    }

    public String getDecimalSepatratorDisplay(int option){
        switch (option){
            case SETTINGS_DECIMAL_SEPARATOR_OPTION_1:{
                return "Based on Locale";
            }
            case SETTINGS_DECIMAL_SEPARATOR_OPTION_2:{
                return "Dot";
            }
            case SETTINGS_DECIMAL_SEPARATOR_OPTION_3:{
                return "Comma";
            }
            default:{
                return "Based on Locale";
            }
        }
    }

    public void setGroupSeparator(int option){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_GROUP_SEPARATOR, option);
        editor.apply();
    }

    public int getGroupSeparator(){
        return mSettingsPrefs.getInt(SETTINGS_GROUP_SEPARATOR, SETTINGS_GROUP_SEPARATOR_DEFAULT);
    }

    public String getGroupSeparatorDisplay(int option){
        switch (option){
            case SETTINGS_GROUP_SEPARATOR_OPTION_1:{
                return "Space";
            }
            case SETTINGS_GROUP_SEPARATOR_OPTION_2:{
                return "Apostrophe";
            }
            default:{
                return "Space";
            }
        }
    }

    public void setDefaults(){
        SharedPreferences.Editor editor = mSettingsPrefs.edit();
        editor.putInt(SETTINGS_MR_COPIES, SETTINGS_MR_COPIES_DEFAULT);
        editor.putInt(SETTINGS_RAND_SIGN_AMOUNT, SETTINGS_RAND_SIGN_AMOUNT_DEFAULT);
        editor.putBoolean(SETTINGS_IS_DATE_IN_HISTORY_ITEMS, SETTINGS_IS_DATE_IN_HISTORY_ITEMS_DEFAULT);
        editor.putInt(SETTINGS_MAX_HISTORY_SIZE, SETTINGS_MAX_HISTORY_SIZE_DEFAULT);
        editor.putInt(SETTINGS_DATE_FORMAT, SETTINGS_DATE_FORMAT_DEFAULT);
        editor.putBoolean(SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE, SETTINGS_IS_SAVE_EXPRESSION_WHEN_ROTATE_DEFAULT);
        editor.putInt(SETTINGS_DECIMAL_SEPARATOR, SETTINGS_DECIMAL_SEPARATOR_DEFAULT);
        editor.putInt(SETTINGS_GROUP_SEPARATOR, SETTINGS_GROUP_SEPARATOR_DEFAULT);
        editor.apply();
    }
}
