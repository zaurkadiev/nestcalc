package nestcalc.mymorce.com.Interface;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;

import java.util.List;

import nestcalc.mymorce.com.Calculator.Analyzer;
import nestcalc.mymorce.com.Helpers.AnalysisHelper;
import nestcalc.mymorce.com.Helpers.AnimationHelper;
import nestcalc.mymorce.com.Helpers.CalcHelper;
import nestcalc.mymorce.com.Helpers.CommonHelper;
import nestcalc.mymorce.com.Helpers.SettingsHelper;
import nestcalc.mymorce.com.Interface.Adapters.RecordAdapter;
import nestcalc.mymorce.com.Interface.Adapters.RecordAdapterInterface;
import nestcalc.mymorce.R;
import nestcalc.mymorce.com.Settings.Settings;
import nestcalc.mymorce.com.SettingsActivity;
import nestcalc.mymorce.com.Storage.Record;
import nestcalc.mymorce.com.Storage.RecordStorage;


public class SimpleCalcLayout {

    private Context context;
    private View container;
    private RecyclerView recordRecycler;
    private RecordLayotManager recordLayotManager;
    private RecordAdapter recordAdapter;
    private Settings settings;
    private TextView expression;
    private TextView result;
    private final TextView recordsLabel;
//    private AdView adView;
    private Analyzer analyzer = new Analyzer();
    private RecordStorage recordStorage;
    private View dot;
    private ViewGroup bannerContainer;

    // EDGES
    private float edgeBottom;
    private float edgeTop;
    private float edge;
    // LOG
    private String LOG = getClass().getName();

    public SimpleCalcLayout(final Context context, View root, final String res, final String exp){

        analyzer = new Analyzer();
        analyzer.setShowMessage(true);
        final String[] buffer = {""};
        this.context = context;
        settings = new Settings(context);

        this.bannerContainer = root.findViewById(R.id.simple_calc_banner_container);
//        FrameLayout frameLayout = ((Activity)context).findViewById(R.id.simp_calc_status_bar_id);
//        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonHelper.getStatusBarHeight(context)));

        result = root.findViewById(R.id.simple_calc_result_id);
        expression = root.findViewById(R.id.simp_calc_display_exp_id);
        final Toolbar toolbar = root.findViewById(R.id.simp_calc_history_toolbar_id);
        View startBracket = root.findViewById(R.id.simple_calc_start_bracket_id);
        final View endBracket = root.findViewById(R.id.simple_calc_end_bracket_id);
        dot = root.findViewById(R.id.simple_calc_dot_butt_id);
        View percent = root.findViewById(R.id.simple_calc_percent_butt_id);
        final View sqrt = root.findViewById(R.id.simple_calc_sqrt_id);
        View butt0 = root.findViewById(R.id.simple_calc_0_butt_id);
        View butt1 = root.findViewById(R.id.simple_calc_1_butt_id);
        View butt2 = root.findViewById(R.id.simple_calc_2_butt_id);
        View butt3 = root.findViewById(R.id.simple_calc_3_butt_id);
        View butt4 = root.findViewById(R.id.simple_calc_4_butt_id);
        View butt5 = root.findViewById(R.id.simple_calc_5_butt_id);
        View butt6 = root.findViewById(R.id.simple_calc_6_butt_id);
        View butt7 = root.findViewById(R.id.simple_calc_7_butt_id);
        View butt8 = root.findViewById(R.id.simple_calc_8_butt_id);
        View butt9 = root.findViewById(R.id.simple_calc_9_butt_id);

        final View div = root.findViewById(R.id.simple_calc_div_id);
        View mult = root.findViewById(R.id.simple_calc_mult_id);
        View plus = root.findViewById(R.id.simple_calc_sum_id);
        final View kostyl = root.findViewById(R.id.simple_calc_kostyl_id);
        final View minus = root.findViewById(R.id.simple_calc_minus_id);
        final View equal = root.findViewById(R.id.simple_calc_equal_id);
        final View mr = root.findViewById(R.id.simple_calc_mr_id);
        final View backspace = root.findViewById(R.id.simple_calc_backspace_id);
        final View display = root.findViewById(R.id.simp_calc_display_cont_id);
        container = root.findViewById(R.id.simp_calc_main_container_id);
        final View historyContainer = root.findViewById(R.id.simp_calc_history_cont_id);
        recordsLabel = root.findViewById(R.id.simple_calc_records_label_id);


        int statusBarHeight = CommonHelper.getStatusBarHeight(context);
        int navbarHeight = CommonHelper.getNavBarHeight(context);

        ViewGroup.LayoutParams historyParams = historyContainer.getLayoutParams();
        historyParams.height = CommonHelper.getScreenHeight(context) - CommonHelper.convertDpToPixels(120, context)  - navbarHeight + statusBarHeight;
        historyContainer.setLayoutParams(historyParams);

        edgeBottom = historyParams.height - CommonHelper.convertDpToPixels(120, context);
        edgeTop = 0;
        edge = edgeBottom/2;

        final ViewGroup.LayoutParams containerParams = container.getLayoutParams();
        containerParams.height = historyParams.height +
                CommonHelper.convertDpToPixels(120, context) +
                (CommonHelper.getScreenHeight(context)) - CommonHelper.convertDpToPixels(240, context)-statusBarHeight;

        container.setLayoutParams(containerParams);

        container.setY(-edgeBottom);

        List<Record> recordList;
        recordLayotManager = new RecordLayotManager(context);
        recordStorage = new RecordStorage(context);
        recordList = recordStorage.getAll();
        recordRecycler = root.findViewById(R.id.simple_calc_records_list_id);
        recordAdapter = new RecordAdapter(context, container, recordList);


        // SHOW/HIDE RECORD LABEL
        if (recordList.size() == 0)
            recordsLabel.setVisibility(View.VISIBLE);
        else recordsLabel.setVisibility(View.GONE);

        recordRecycler.setAdapter(recordAdapter);
        recordRecycler.setLayoutManager(recordLayotManager);
        recordLayotManager.setStackFromEnd(true);
        recordLayotManager.setMillisecondsPerInch(300f);

        expression.setText(exp);
        result.setText(res);



        // SET ELEMENTS
        toolbar.inflateMenu(R.menu.history_toolbar_menu_0);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history_toolbar_icon_show_favorites_id:
//                        Log.e(LOG, "show faves");
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.history_toolbar_menu_1);
                        recordAdapter.showFaves();
                        break;
                    case R.id.history_toolbar_icon_show_all_id:
//                        Log.e(LOG, "show all");
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.history_toolbar_menu_0);
                        recordAdapter.showAll();
                        if(recordAdapter.getItemCount() == 0) return false;
                        recordRecycler.scrollToPosition(recordAdapter.getItemCount()-3);
                        recordRecycler.smoothScrollToPosition(recordAdapter.getItemCount()-1);
                        break;
                    case R.id.history_toolbar_icon_delete_id:
//                        Log.e(LOG, "delete");

                        recordAdapter.showAll();
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.history_toolbar_menu_0);

                        AnimationHelper.animateContainerScale(container, true);
                        final AlertDialog.Builder ad = new AlertDialog.Builder(context);
                        ad.setTitle(context.getResources().getString(R.string.erase_history_title));
                        ad.setMessage(context.getResources().getString(R.string.erase_history_message));
                        ad.setNeutralButton(context.getResources().getString(R.string.erase_all), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                recordAdapter.removeAll();
                                recordStorage.removeAll();
                                recordsLabel.setVisibility(View.VISIBLE);
                                ad.create().dismiss();
                            }
                        });

                        ad.setPositiveButton(context.getResources().getString(R.string.erase), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                recordAdapter.removeNonFaves();
                                recordStorage.removeAllUnfavorited();
                                if (recordStorage.isEmpty())
                                    recordsLabel.setVisibility(View.VISIBLE);
                                ad.create().dismiss();
                            }
                        });
                        ad.setNegativeButton(context.getResources().getString(R.string.dilog_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ad.create().dismiss();
                            }
                        });
                        ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                AnimationHelper.animateContainerScale(container, false);
                            }
                        });
                        ad.show();
                        recordAdapter.showAll();
                        break;
                    case R.id.history_toolbar_icon_settings_id:
                        Intent intent  = new Intent(context, SettingsActivity.class);
                        context.startActivity(intent);
                        break;
                }
                return false;
            }
        });

        startBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "(");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        startBracket.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString();
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                expression.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        startBracket.setOnTouchListener(new ButtonTouchListener());

        endBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), ")");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        endBracket.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString()+")";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                endBracket.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        endBracket.setOnTouchListener(new ButtonTouchListener());

        TextView label = dot.findViewWithTag("label");
        label.setText(String.valueOf(SettingsHelper.getDecimalSeparator(context)));
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), String.valueOf(SettingsHelper.getDecimalSeparator(context)));
                expression.setText(value);
            }
        });
        dot.setOnTouchListener(new ButtonTouchListener());


        butt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "0");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt0.setOnTouchListener(new ButtonTouchListener());

        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "1");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt1.setOnTouchListener(new ButtonTouchListener());

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "2");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt2.setOnTouchListener(new ButtonTouchListener());

        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "3");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt3.setOnTouchListener(new ButtonTouchListener());

        butt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "4");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt4.setOnTouchListener(new ButtonTouchListener());

        butt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "5");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt5.setOnTouchListener(new ButtonTouchListener());

        butt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "6");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt6.setOnTouchListener(new ButtonTouchListener());

        butt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "7");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt7.setOnTouchListener(new ButtonTouchListener());

        butt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "8");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt8.setOnTouchListener(new ButtonTouchListener());

        butt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "9");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        butt9.setOnTouchListener(new ButtonTouchListener());


        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.backspaceExpression(expression.getText().toString());
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                expression.setText("0");
                result.setText("0");
                estimateInstant(expression, analyzer, result);
                result.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        backspace.setOnTouchListener(new ButtonTouchListener());

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "+");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        plus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString()+")+";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                expression.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        plus.setOnTouchListener(new ButtonTouchListener(new Animator.AnimatorListener() { // upListener
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                kostyl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                kostyl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }, new Animator.AnimatorListener() { //downListener
            @Override
            public void onAnimationStart(Animator animator) {
                kostyl.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                kostyl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }));

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "×");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        mult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString()+")×";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                expression.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        mult.setOnTouchListener(new ButtonTouchListener());

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "÷");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        div.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString()+")÷";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                div.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        div.setOnTouchListener(new ButtonTouchListener());

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "-");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        minus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = "("+expression.getText().toString()+")-";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                minus.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        minus.setOnTouchListener(new ButtonTouchListener());

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estimate(expression, analyzer, result, recordStorage, recordAdapter);
                equal.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });
        equal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Record record = estimate(expression, analyzer, result, recordStorage, recordAdapter);
                if (record != null){
                    String s = record.getResult().replace(".", String.valueOf(SettingsHelper.getDecimalSeparator(context)));
                    expression.setText(s);
                }
                equal.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        equal.setOnTouchListener(new ButtonTouchListener());

        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = expression.getText().toString()+buffer[0];
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });

        mr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                switch (settings.getMrCopies()){
                    case Settings.SETTINGS_MR_COPIES_OPTION_EXPRESSION:{
                        buffer[0] = expression.getText().toString();
                        AnimationHelper.animateSaveBuffer(expression);
                        View v = mr.findViewWithTag("label");
                        v.setBackgroundColor(context.getResources().getColor(R.color.colorGreyDeepDark));
                        mr.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        break;
                    }
                    case Settings.SETTINGS_MR_COPIES_OPTION_RESULT:{

                        String res = result.getText().toString();
                        buffer[0] = res;

                        if (res != null)
                            buffer[0] = res;

                        View v = mr.findViewWithTag("label");
                        v.setBackgroundColor(context.getResources().getColor(R.color.colorGreyDeepDark));
                        mr.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        AnimationHelper.animateSaveBuffer(result);

                        break;
                    }
                    default:{
                        buffer[0] = expression.getText().toString();
                        break;
                    }

                }
                return true;
            }
        });
        mr.setOnTouchListener(new ButtonTouchListener());

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "%");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        percent.setOnTouchListener(new ButtonTouchListener());

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = CalcHelper.addToExpression(expression.getText().toString(), "√");
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
            }
        });
        sqrt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String value = expression.getText().toString();
                value = "√("+value+")";
                expression.setText(value);
                estimateInstant(expression, analyzer, result);
                expression.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                return true;
            }
        });
        sqrt.setOnTouchListener(new ButtonTouchListener());

        recordAdapter.setListener(new RecordAdapterInterface() {
            @Override
            public void onTakeRecord(Record record, int position) {
                expression.setText(record.getExpression());
                estimateInstant(expression, analyzer, result);
            }

            @Override
            public void onCopyResult(Record record, int position) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", record.getResult());
                try {
                    assert clipboard != null;
                    clipboard.setPrimaryClip(clip);
                } catch (NullPointerException e){
                    e.getMessage();
                }
            }

            @Override
            public void onCopyExpression(Record record, int position) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", record.getExpression());
                try {
                    assert clipboard != null;
                    clipboard.setPrimaryClip(clip);
                } catch (NullPointerException e){
                    e.getMessage();
                }
            }

            @Override
            public void onShare(Record record, int position) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        record.getExpression()  + " = " + record.getResult()
                );
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }

            @Override
            public void onRemove(Record record, int position) {
                recordStorage.remove(record.getID());
                recordAdapter.removeItem(position);
                // SHOW/HIDE LABEL RECORD
                if(recordStorage.isEmpty())
                    recordsLabel.setVisibility(View.VISIBLE);
                else recordsLabel.setVisibility(View.GONE);
            }

            @Override
            public void onFavorite(Record record, int position) {
                recordStorage.update(record.getID(), record);
            }

            @Override
            public void onUnFavorite(Record record, int position) {
                recordStorage.update(record.getID(), record);
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e(LOG, "CLICK DISPLAY");
                final BottomSheetDialog dialog = new BottomSheetDialog(context);
                View v = LayoutInflater.from(context).inflate(R.layout.display_bottom_sheet_layout, null);
                View paste = v.findViewById(R.id.display_bottom_sheet_paste_id);
                View copyRes = v.findViewById(R.id.display_bottom_sheet_copy_result_id);
                final View copyExp = v.findViewById(R.id.display_bottom_sheet_copy_expression_id);
                View share = v.findViewById(R.id.display_bottom_sheet_share_id);
                View erase = v.findViewById(R.id.display_bottom_sheet_erase_id);

                paste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            String ex = clipboard != null ? clipboard.getPrimaryClip().getItemAt(0).getText().toString() : null;

                            String value = CalcHelper.addToExpression(expression.getText().toString(), ex);
                            expression.setText(value);

                            estimateInstant(expression, analyzer, result);
                            dialog.dismiss();
                        } catch (Exception e){
                            e.getMessage();
                            dialog.dismiss();
                        }
                    }
                });

                copyRes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String string = result.getText().toString().replace(" ","");
                        Log.e(LOG, string);
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label", string);
                        try {
                            assert clipboard != null;
                            clipboard.setPrimaryClip(clip);
                        } catch (NullPointerException e){
                            e.getMessage();
                        }
                        dialog.dismiss();
                    }
                });

                copyExp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String string = expression.getText().toString();

                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label", string);
                        try {
                            assert clipboard != null;
                            clipboard.setPrimaryClip(clip);
                        } catch (NullPointerException e){
                            e.getMessage();
                        }
                        dialog.dismiss();
                    }
                });

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(
                                Intent.EXTRA_TEXT,
                                expression.getText()  + " = " + result.getText()
                        );
                        sendIntent.setType("text/plain");
                        context.startActivity(sendIntent);
                        dialog.dismiss();
                    }
                });

                erase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        expression.setText("0");
                        result.setText("0");
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(v);

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(
                                ObjectAnimator.ofFloat(container, View.SCALE_X, 1f, 0.92f),
                                ObjectAnimator.ofFloat(container, View.SCALE_Y, 1f, 0.92f)
                        );
                        set.setInterpolator(new OvershootInterpolator());
                        set.start();
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(
                                ObjectAnimator.ofFloat(container, View.SCALE_X, 0.92f, 1f),
                                ObjectAnimator.ofFloat(container, View.SCALE_Y, 0.92f, 1f)
                        );
                        set.setInterpolator(new OvershootInterpolator());
                        set.start();
                    }
                });
                dialog.show();
            }
        });


        // DISPLAY ANIMATION AND CLICK
        final float[] distance = {0};
        display.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switchLayoutListener.onTouch(view, motionEvent);
                distance[0] += Math.abs(offset[0]);
                // Если расстояние меньше порога срабатывает клик item.
                int thresh = CommonHelper.convertDpToPixels(5, context);
//                Log.e(LOG, offset + " > " + thresh +" < " + distance[0]);

                if (distance[0] > thresh) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        distance[0] = 0;
                    }
                    return true;
                }
                else {
//                    Log.e(LOG, "perform click");
                    distance[0] = 0;
                    return false;
                }
            }
        });




        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordAdapter.getItemCount() == 0) return;

                recordLayotManager.scrollToPosition(recordAdapter.getItemCount()-3);
                recordRecycler.post(new Runnable() {
                    @Override
                    public void run() {
                        recordRecycler.smoothScrollToPosition(recordAdapter.getItemCount()-1);
                    }
                });
            }
        });

        setCalcLayout();
    }

    private void estimateInstant(TextView expression, Analyzer analyzer, TextView result){
        if(expression.getText().equals(""))
            return;

        try {
            String insertExp = AnalysisHelper.formatInput(expression.getText().toString());
            String raw = analyzer.estimate(insertExp);

            if (CommonHelper.isError(raw))
                return;

            String display = AnalysisHelper.formatOutput(raw, context);
            result.setText(display);

        } catch (Exception e){
            e.getMessage();
        }

    }

    private Record estimate(TextView expression, Analyzer analyzer, TextView result, RecordStorage recordStorage, final RecordAdapter recordAdapter){
        if(expression.getText().equals(""))
            return null;

        String insertExp = AnalysisHelper.formatInput(expression.getText().toString());
        String raw = analyzer.estimate(insertExp);
        Log.e(LOG, raw);
        if (CommonHelper.isError(raw)) {
            AnimationHelper.animateEstimateError(expression, context);
            return null;
        }

        String display = AnalysisHelper.formatOutput(raw, context);
        result.setText(display);


        Record record = Record.newInstance(
                "untitled",
                expression.getText().toString(),
                raw,
                System.currentTimeMillis(),
                false
        );
        recordStorage.add(record);
        recordAdapter.appendRecord(record);

        // SHOW/HIDE LABEL RECORD
        if (recordStorage.isEmpty())
            recordsLabel.setVisibility(View.VISIBLE);
        else recordsLabel.setVisibility(View.GONE);

        recordRecycler.post(new Runnable() {
            @Override
            public void run() {
                if (recordLayotManager.findLastVisibleItemPosition() < recordAdapter.getItemCount() - 2)
                {
                    recordRecycler.scrollToPosition(recordAdapter.getItemCount() - 3);
                    recordRecycler.smoothScrollToPosition(recordAdapter.getItemCount()-1);
                } else {
                    if(recordAdapter.getItemCount() != 0){
                        recordRecycler.smoothScrollToPosition(recordAdapter.getItemCount()-1);
                    }
                }
            }
        });


        return record;
    }

    private void setCalcLayout(){
        recordLayotManager.setScrollEnabled(true);
        recordRecycler.removeOnItemTouchListener(recyclerOnItemListener);
        recordRecycler.addOnItemTouchListener(recyclerOnItemListener);

        if (recordAdapter.getItemCount() != 0){
            recordRecycler.post(new Runnable() {
                @Override
                public void run() {
                    recordRecycler.smoothScrollToPosition(recordLayotManager.findLastVisibleItemPosition());
                }
            });
        }

        // HIDE AD
//        if (adView == null || bannerContainer.getChildCount() == 0) return;
//
//        Animator animator = ObjectAnimator.ofFloat(adView, View.ALPHA, 1f, 0f);
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                ValueAnimator slideAnimator = ValueAnimator.ofInt(bannerContainer.getHeight(), 0);
//                slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        bannerContainer.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
//                        bannerContainer.requestLayout();
//                        if ((Integer) valueAnimator.getAnimatedValue() == 0){
//                            bannerContainer.removeAllViews();
//                            adView.destroy();
//                            bannerContainer.refreshDrawableState();
//                        }
//                    }
//                });
//
//                slideAnimator.setDuration(300);
//                slideAnimator.setInterpolator(new DecelerateInterpolator());
//                slideAnimator.start();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//                bannerContainer.removeAllViews();
//                adView.destroy();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animator.start();
    }

    private void setHistoryLayout(){
        recordLayotManager.setScrollEnabled(true);
        recordRecycler.removeOnItemTouchListener(recyclerOnItemListener);
//
//        // LOAD AD
//        if(bannerContainer.getChildCount() != 0) return;
//
//        adView = new AdView(context);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(context.getResources().getString(R.string.adBanner));
//        adView.setBackground(context.getResources().getDrawable(R.drawable.banner_background));
//        bannerContainer.addView(adView);
//        bannerContainer.setVisibility(View.GONE);
//        adView.setVisibility(View.GONE);
//        adView.setAlpha(0f);
//        adView.loadAd(new AdRequest.Builder().build());
//        adView.setAdListener(new AdListener(){
//            @Override
//            public void onAdLoaded() {
//                adView.setVisibility(View.VISIBLE);
//                bannerContainer.setVisibility(View.VISIBLE);
//                ValueAnimator slideAnimator = ValueAnimator.ofInt(bannerContainer.getHeight(), CommonHelper.convertDpToPixels(60, context));
//                slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        bannerContainer.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
//                        bannerContainer.requestLayout();
//                        if ((Integer) valueAnimator.getAnimatedValue() == CommonHelper.convertDpToPixels(60, context)) {
//                            ObjectAnimator.ofFloat(adView, View.ALPHA, 0f, 1f).start();
//                            bannerContainer.refreshDrawableState();
//                            adView.refreshDrawableState();
//                        }
//                    }
//                });
//                slideAnimator.setDuration(300);
//                slideAnimator.setInterpolator(new DecelerateInterpolator());
//                slideAnimator.start();
//            }
//        });
    }

    public void resume(){
        // UPDATE SETTINGS
        recordAdapter.notifyDataSetChanged(); // update list
        estimateInstant(expression, analyzer, result); // update display
        ((TextView) dot.findViewWithTag("label")).setText(String.valueOf(SettingsHelper.getDecimalSeparator(context))); // update decimal separator
    }

    public String getExpression(){
        return expression.getText().toString();
    }

    public String getResult(){
        return result.getText().toString();
    }

    // АНИМАЦИЯ ЛЕЙАУТОВ
    private RecyclerView.OnItemTouchListener recyclerOnItemListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            float offset = Math.abs(-edgeBottom-container.getY());
//            Log.e(LOG, ""+offset);
            switchLayoutListener.onTouch(rv, e);
            // Если расстояние меньше порога срабатывает клик item.
            int thresh = CommonHelper.convertDpToPixels(5, context);
            // down to items
            return offset >= thresh;
        }

        @Override
        public void onTouchEvent(final RecyclerView rv, final MotionEvent e) {
           switchLayoutListener.onTouch(rv, e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//            Log.e(LOG, "onRequestDisallowInterceptTouchEvent");
        }
    };
    private GestureDetector switchLayoutGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) { return false; }

        @Override
        public void onShowPress(MotionEvent motionEvent) {}

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) { return false; }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {return false;}

        @Override
        public void onLongPress(MotionEvent motionEvent) {}

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

            Long duration = Math.min(300000/((long) Math.abs(v1)+10), 350);
            duration = Math.max(duration, 300);

//            Log.e("FLING", v1+"");

            if( v1 > 200){
                AnimationHelper.animateTranslationY(container, container.getY(),
                        edgeTop, new DecelerateInterpolator(), duration, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        setHistoryLayout();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        setHistoryLayout();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }

            if(v1 < -200){
                AnimationHelper.animateTranslationY(container, container.getY(),
                        -edgeBottom, new DecelerateInterpolator(), duration, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        setCalcLayout();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        setCalcLayout();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }

            return false;
        }
    });
    private final float[] downY = {0};
    private final float[] offset = {0};
    private View.OnTouchListener switchLayoutListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (switchLayoutGestureDetector.onTouchEvent(motionEvent))
                return true;

            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
//                    Log.e(LOG, "DOWN");
                    downY[0] = motionEvent.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    recordLayotManager.setScrollEnabled(false);
                    offset[0] = motionEvent.getY() - downY[0];

                    if (container.getY() <= edgeTop && container.getY() >= -edgeBottom){

                        if (container.getY()+offset[0] > edgeTop){
                            setHistoryLayout();
                            container.setY(edgeTop);
                        }
                        else{
                            if (container.getY() + offset[0] < -edgeBottom)
                                container.setY(-edgeBottom);
                            else
                                container.setY(container.getY()+offset[0]);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
//                    Log.e(LOG, "UP");
                    downY[0] = 0;
                    offset[0] = 0;
                    if(container.getY() <= -edge){
                        AnimationHelper.animateTranslationY(container, container.getY(), -edgeBottom, new DecelerateInterpolator(), null, new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                setCalcLayout();
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {
                                setCalcLayout();
                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }else {
                        AnimationHelper.animateTranslationY(container, container.getY(), edgeTop, new DecelerateInterpolator(), null, new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                setHistoryLayout();
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {
                                setHistoryLayout();
                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }
                    break;
            }
            return true;
        }
    };
}
