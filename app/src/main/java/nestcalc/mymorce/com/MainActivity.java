package nestcalc.mymorce.com;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.util.LruCache;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import nestcalc.mymorce.com.Helpers.CommonHelper;
import nestcalc.mymorce.com.Helpers.RateAppHelper;
import nestcalc.mymorce.com.Interface.ScienceCalcLayout;
import nestcalc.mymorce.com.Interface.SimpleCalcLayout;
import nestcalc.mymorce.R;
import nestcalc.mymorce.com.Settings.RuntimeSettings;
import nestcalc.mymorce.com.Settings.Settings;

public class MainActivity extends AppCompatActivity {

    SimpleCalcLayout simpleCalcLayout;
    ScienceCalcLayout scienceCalcLayout;
    static LruCache<String, String> cache = new LruCache<>(16);
    String EXP_CACHE = "exp_cache";
    String RES_CACHE = "res_cache";

    private String LOG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings settings = new Settings(this);


        String res;
        String exp;
        if(cache.size() == 0 || !settings.getIsSaveExpressionWhenRotate()){
            res = "0";
            exp = "0";
        } else {
            res = cache.get(RES_CACHE);
            exp = cache.get(EXP_CACHE);
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.simple_calc);
            simpleCalcLayout = new SimpleCalcLayout(this, this.findViewById(android.R.id.content), res, exp);
        } else {
            setContentView(R.layout.science_calc);
            scienceCalcLayout = new ScienceCalcLayout(this, this.findViewById(android.R.id.content), res, exp);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleCalcLayout != null){
            cache.put(EXP_CACHE, simpleCalcLayout.getExpression());
            cache.put(RES_CACHE, simpleCalcLayout.getResult());
        } else{
            cache.put(EXP_CACHE, scienceCalcLayout.getExpression());
            cache.put(RES_CACHE, scienceCalcLayout.getResult());
        }

        simpleCalcLayout = null;
        scienceCalcLayout = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (simpleCalcLayout != null){
            simpleCalcLayout.resume();
        }
        if (scienceCalcLayout != null){
            scienceCalcLayout.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // Rate App section
            Context context = this;
            RuntimeSettings runtimeSettings = new RuntimeSettings(context);

            long currentInterval = CommonHelper.getCurrentUnixTime() - CommonHelper.getInstallUnixTime(context);
            Log.e(LOG, currentInterval+"");
            if (currentInterval >= runtimeSettings.getRateAppInterval()){
                RateAppHelper.showRateAppMessage(context);
            }
            else {
                onBackPressed();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
