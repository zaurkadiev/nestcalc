package nestcalc.mymorce.com.Helpers;

import android.content.Context;

import java.text.DecimalFormatSymbols;

import nestcalc.mymorce.com.Settings.Settings;

public class SettingsHelper {

    public static char getDecimalSeparator(Context context){
        Settings settings = new Settings(context);
        switch (settings.getDecimalSeparator()){
            case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1:{
                DecimalFormatSymbols dfs = new DecimalFormatSymbols(CommonHelper.getLocale(context));
                return dfs.getDecimalSeparator();
            }
            case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2:{
                return '.';
            }
            case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_3:{
                return ',';
            }
            default: {
                DecimalFormatSymbols dfs = new DecimalFormatSymbols(CommonHelper.getLocale(context));
                return dfs.getDecimalSeparator();
            }
        }
    }

    public static char getGroupSeparator(Context context){
        Settings settings = new Settings(context);
        switch (settings.getGroupSeparator()){
            case Settings.SETTINGS_GROUP_SEPARATOR_OPTION_1:{
                return ' ';
            }
            case Settings.SETTINGS_GROUP_SEPARATOR_OPTION_2:{
                return '\'';
            }
            default: {
                return ' ';
            }
        }
    }

}
