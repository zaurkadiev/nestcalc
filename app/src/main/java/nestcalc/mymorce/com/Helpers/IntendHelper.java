package nestcalc.mymorce.com.Helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class IntendHelper {

    /**
     * Intend Email Action
     * @param context Context
     * @param topic topic of the email
     * @param to recipient address
     */
    public static void sendEmail(Context context, String topic, String to){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{to});
        i.putExtra(Intent.EXTRA_SUBJECT, topic);
        try {
            context.startActivity(Intent.createChooser(i, "Send Feedback..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Browser Action
     * @param context Context
     * @param link link to Open
     */
    public static void openBrowser(Context context, String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(browserIntent);
    }
}
