package nestcalc.mymorce.com.Helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import nestcalc.mymorce.R;
import nestcalc.mymorce.com.MainActivity;
import nestcalc.mymorce.com.Settings.RuntimeSettings;



public class RateAppHelper {

    private static String LOG = "RateAppHelper";

    /**
     * Show rate app dialog
     * @param context Context
     */
    public static void showRateAppMessage(final Context context){

        final RuntimeSettings runtimeSettings = new RuntimeSettings(context);

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle(context.getResources().getString(R.string.rate_an_app_title));
        View view = LayoutInflater.from(context).inflate(R.layout.rate_app_dialog, null);
        RatingBar ratingBar = view.findViewById(R.id.rating_bar_id);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                runtimeSettings.setRateAppInterval(runtimeSettings.getRateAppInterval() + 146*24*60*60); // 146 days + 2 days dismiss
                dialog.dismiss();
                if (v > 3){ // 4 and 5
                    // to play store
                   IntendHelper.openBrowser(context, context.getResources().getString(R.string.app_play_store_link));
                } else {
                    // to send an email
                   IntendHelper.sendEmail(
                           context,
                           context.getResources().getString(R.string.app_feedback),
                           context.getResources().getString(R.string.dev_email));
                }
            }
        });
        dialog.setView(view);

        View later = view.findViewById(R.id.rating_bar_positive_id);
        View no = view.findViewById(R.id.rating_bar_negative_id);

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runtimeSettings.setRateAppInterval(runtimeSettings.getRateAppInterval() + 274*24*60*60); // 274 days + 2 days dismiss
                dialog.dismiss();
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                runtimeSettings.setRateAppInterval(runtimeSettings.getRateAppInterval() + 2*24*60*60); // 2 days
            }
        });

        dialog.show();
    }
}
