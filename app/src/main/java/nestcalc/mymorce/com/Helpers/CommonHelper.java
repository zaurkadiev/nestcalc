package nestcalc.mymorce.com.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;

import org.ocpsoft.prettytime.PrettyTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nestcalc.mymorce.com.Settings.Settings;
import nestcalc.mymorce.com.Storage.Record;

public class CommonHelper {

    public static int convertSpToPixels(float sp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static int convertDpToSp(float dp, Context context) {
        int sp = (int) (convertDpToPixels(dp, context) / (float) convertSpToPixels(dp, context));
        return sp;
    }

    public static int convertDpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    /**
     * Декорирование результатов вычисления.
     * @return
     */
    public static String decorateNumbers(double db, Context context){
        Settings settings = new Settings(context);

        NumberFormat nf = NumberFormat.getNumberInstance(CommonHelper.getLocale(context));
        DecimalFormat df = (DecimalFormat)nf;
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(CommonHelper.getLocale(context));
        dfs.setGroupingSeparator(SettingsHelper.getGroupSeparator(context));
        dfs.setDecimalSeparator(SettingsHelper.getDecimalSeparator(context));
//        df.applyPattern("############.############");
        df.setDecimalFormatSymbols(dfs);
        df.setMaximumFractionDigits(12);


        Double dabs = Math.abs(db);
        if(dabs > 999999999999d || dabs < 0.000000000001){

            if(dabs < 0.000000000001)
                return "0";

            return String.valueOf(db);
        } else {
            return df.format(db);
        }
    }


    /**
     * Разделение строки на подстроки в которых по n символов
     * @param str string
     * @param n count
     * @return
     */
    public static List<String> divideString(String str, int n) {
        int strSize = str.length();
        int wCount = strSize / n; // части полные
        int pPart = strSize % n; // 1 части в остатке

        List<String> res = new ArrayList<>();


        for (int i = 0; i < wCount; i++) {
            res.add(str.substring(i * n, i * n + n));
        }

        if(pPart != 0){
            res.add(str.substring(wCount * n, str.length()));
        }

        return res;

    }

    /**
     * Являеся ли реультат в научном виде 1E8 = 1000000000
     * @param string
     * @return
     */
    static boolean isScientific(String string){
        return string.contains("E");
    }


    public static boolean isError(String string){
        try {
            Double dbl = Double.valueOf(string);
            return false;
        } catch (Exception e){
            e.getMessage();
        }
        return true;
    }

    public static boolean isInfinity(String string){
        try {
            Double dbl = Double.valueOf(string);
            return dbl.isInfinite();
        } catch (Exception e){
            e.getMessage();
        }
        return false;
    }

    public static boolean isNaN(String string){
        try {
            Double dbl = Double.valueOf(string);
            return dbl.isNaN();
        } catch (Exception e){
            e.getMessage();
        }
        return false;
    }

    public static long getInstallUnixTime(Context context){
        try {
            return context
                .getPackageManager()
                .getPackageInfo(context.getPackageName(), 0)
                .firstInstallTime/1000;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getCurrentUnixTime(){
        return System.currentTimeMillis()/1000;
    }

    public static int getScreenWidth(Context context){
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Context context){
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    static public int getNavBarHeight(Context context){
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    static public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static Locale getLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

    public static String getRelativeDate(Long time, Context context){
        PrettyTime prettyTime = new PrettyTime(new Date(System.currentTimeMillis()), getLocale(context));
        return prettyTime.format(new Date(time));
    }

    public static  String getDate(Long time, Context context){
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
        return df.format(new Date(time));
    }

    public static  String getDateTime(Long time, Context context){
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
        String s = df.format(new Date(time));
        df = SimpleDateFormat.getTimeInstance(DateFormat.DEFAULT, CommonHelper.getLocale(context));
        s += "\n"+df.format(new Date(time));
        return s;
    }

    public static  List<Record> cropList(List<Record> list, int count){
        if (list.size() <= count) return list;
        return list.subList(list.size()-count, list.size());
    }

    public static boolean headsOrTails(){
        return Math.random() > 0.5;
    }
    public static boolean headsOrTails(float chance){
        return Math.random() < chance;
    }
}
