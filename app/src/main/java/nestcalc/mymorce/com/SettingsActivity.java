package nestcalc.mymorce.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import nestcalc.mymorce.com.Helpers.AnimationHelper;
import nestcalc.mymorce.com.Helpers.CommonHelper;
import nestcalc.mymorce.R;
import nestcalc.mymorce.com.Helpers.IntendHelper;
import nestcalc.mymorce.com.Settings.Settings;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Context context = this;
        final Settings settings = new Settings(this);

        Toolbar toolbar = findViewById(R.id.settings_toolbar_id);
        final View container = findViewById(R.id.settings_container_id);
        View back = findViewById(R.id.settings_toolbar_back_id);
        View version = findViewById(R.id.settings_version_id);


        View mrButt = findViewById(R.id.settings_mr_button_copies);
        final TextView mrButtLabel = mrButt.findViewWithTag("label");

        mrButtLabel.setText(settings.getMrCopiesDisplay(settings.getMrCopies()));

        mrButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_mr_button, null);
                RadioGroup radioGroup = content.findViewById(R.id.settings_mr_radio_group_id);
                View ok  = content.findViewById(R.id.settings_mr_button_ok_id);
                switch (settings.getMrCopies()){
                    case Settings.SETTINGS_MR_COPIES_OPTION_EXPRESSION:
                        ((RadioButton) radioGroup.findViewById(R.id.settings_mr_radio_group_option_0_id)).setChecked(true);
                        break;
                    case Settings.SETTINGS_MR_COPIES_OPTION_RESULT:
                        ((RadioButton) radioGroup.findViewById(R.id.settings_mr_radio_group_option_1_id)).setChecked(true);
                        break;

                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.settings_mr_radio_group_option_0_id:
                                RadioButton rb =  radioGroup.findViewById(R.id.settings_mr_radio_group_option_0_id);
                                rb.setChecked(true);
                                mrButtLabel.setText(rb.getText());
                                settings.setMrCopies(Settings.SETTINGS_MR_COPIES_OPTION_EXPRESSION);
                                break;
                            case R.id.settings_mr_radio_group_option_1_id:
                                RadioButton rb1 =  radioGroup.findViewById(R.id.settings_mr_radio_group_option_1_id);
                                rb1.setChecked(true);
                                mrButtLabel.setText(rb1.getText());
                                settings.setMrCopies(Settings.SETTINGS_MR_COPIES_OPTION_RESULT);
                                break;
                        }
                    }
                });


                dialog.setContentView(content);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });

                dialog.show();
            }
        });

        final View historySize = findViewById(R.id.settings_history_size_id);
        final TextView historySizeLabel = historySize.findViewWithTag("label");
        historySizeLabel.setText(settings.getHistorySizeDisplay(settings.getHistorySize()));
        historySize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_history_size, null);
                dialog.setContentView(content);

                final NumberPicker picker = content.findViewById(R.id.settings_history_size_id);
                picker.setWrapSelectorWheel(true);
                picker.setMinValue(0);
                picker.setMaxValue(4);
                picker.setDisplayedValues(Settings.SETTINGS_MAX_HISTORY_SIZE_OPTIONS);
                picker.setValue(settings.getHistorySize());
                View ok = content.findViewById(R.id.settings_history_ok_id);

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        settings.setHistorySize(i1);
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                        historySizeLabel.setText(settings.getHistorySizeDisplay(settings.getHistorySize()));
                    }
                });


                dialog.show();
            }
        });

        View saveExpRotate = findViewById(R.id.settings_save_expression_when_rotate);
        final Switch saveExpRotateSwitch = saveExpRotate.findViewWithTag("switch");
        saveExpRotateSwitch.setChecked(settings.getIsSaveExpressionWhenRotate());
        saveExpRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch sw = view.findViewWithTag("switch");
                if (settings.getIsSaveExpressionWhenRotate()){
                    settings.setIsSaveExpressionWhenRotate(false);
                    sw.setChecked(false);
                } else {
                    settings.setIsSaveExpressionWhenRotate(true);
                    sw.setChecked(true);
                }
            }
        });

        View randFormat = findViewById(R.id.settings_random_format_id);
        final TextView randFormatLabel = randFormat.findViewWithTag("label");

        randFormatLabel.setText(settings.getRandomFormatDisplay(settings.getRandomFormat()));

        randFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_random_format, null);
                dialog.setContentView(content);
                RadioGroup radioGroup = content.findViewById(R.id.settings_random_format_radio_group_id);
                final RadioButton rb1 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_1_id);
                final RadioButton rb2 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_2_id);
                final RadioButton rb3 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_3_id);
                final RadioButton rb4 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_4_id);
                final RadioButton rb5 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_5_id);
                final RadioButton rb6 = radioGroup.findViewById(R.id.settings_random_format_radio_group_option_6_id);

                rb1.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_1));
                rb2.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_2));
                rb3.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_3));
                rb4.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_4));
                rb5.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_5));
                rb6.setText(settings.getRandomFormatDisplay(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_6));

                View ok = content.findViewById(R.id.settings_random_format_ok_id);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                switch (settings.getRandomFormat()){
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_1:
                        rb1.setChecked(true);
                        break;
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_2:
                        rb2.setChecked(true);
                        break;
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_3:
                        rb3.setChecked(true);
                        break;
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_4:
                        rb4.setChecked(true);
                        break;
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_5:
                        rb5.setChecked(true);
                        break;
                    case Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_6:
                        rb6.setChecked(true);
                        break;

                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.settings_random_format_radio_group_option_1_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_1);
                                randFormatLabel.setText(rb1.getText());
                                break;
                            case R.id.settings_random_format_radio_group_option_2_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_2);
                                randFormatLabel.setText(rb2.getText());
                                break;
                            case R.id.settings_random_format_radio_group_option_3_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_3);
                                randFormatLabel.setText(rb3.getText());
                                break;
                            case R.id.settings_random_format_radio_group_option_4_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_4);
                                randFormatLabel.setText(rb4.getText());
                                break;
                            case R.id.settings_random_format_radio_group_option_5_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_5);
                                randFormatLabel.setText(rb5.getText());
                                break;
                            case R.id.settings_random_format_radio_group_option_6_id:
                                settings.setRandomFormat(Settings.SETTINGS_RAND_SIGN_AMOUNT_OPTION_6);
                                randFormatLabel.setText(rb6.getText());
                                break;
                        }
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });

                dialog.show();
            }
        });


        View dateInHistoryItems = findViewById(R.id.settings_date_in_history_items_id);
        final Switch dateInHistorySwitch = dateInHistoryItems.findViewWithTag("switch");
        dateInHistorySwitch.setChecked(settings.isDateInHistoryItems());
        dateInHistoryItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch sw = view.findViewWithTag("switch");
                if (settings.isDateInHistoryItems()){
                    settings.setIsDateInHistoryItems(false);
                    sw.setChecked(false);
                } else {
                    settings.setIsDateInHistoryItems(true);
                    sw.setChecked(true);
                }
            }
        });

        View dateFormat = findViewById(R.id.settings_date_format_id);
        final TextView dateFormatLabel = dateFormat.findViewWithTag("label");

        dateFormatLabel.setText(settings.getDateFormatDisplay(settings.getDateFormat()));

        dateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_date_format, null);
                dialog.setContentView(content);
                RadioGroup radioGroup = content.findViewById(R.id.settings_date_format_radio_group_id);
                View ok = content.findViewById(R.id.settings_date_format_ok_id);

                final RadioButton rb1 = radioGroup.findViewById(R.id.settings_date_format_radio_group_option_1_id);
                final RadioButton rb2 = radioGroup.findViewById(R.id.settings_date_format_radio_group_option_2_id);
                final RadioButton rb3 = radioGroup.findViewById(R.id.settings_date_format_radio_group_option_3_id);


                rb1.setText(settings.getDateFormatDisplay(Settings.SETTINGS_DATE_FORMAT_OPTION_1));
                rb2.setText(settings.getDateFormatDisplay(Settings.SETTINGS_DATE_FORMAT_OPTION_2));
                rb3.setText(settings.getDateFormatDisplay(Settings.SETTINGS_DATE_FORMAT_OPTION_3));

                switch (settings.getDateFormat()){
                    case Settings.SETTINGS_DATE_FORMAT_OPTION_1:
                        rb1.setChecked(true);
                        break;
                    case Settings.SETTINGS_DATE_FORMAT_OPTION_2:
                        rb2.setChecked(true);
                        break;
                    case Settings.SETTINGS_DATE_FORMAT_OPTION_3:
                        rb3.setChecked(true);
                        break;
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.settings_date_format_radio_group_option_1_id:{
                                rb1.setChecked(true);
                                settings.setDateFormat(Settings.SETTINGS_DATE_FORMAT_OPTION_1);
                                dateFormatLabel.setText(rb1.getText());
                                break;
                            }
                            case R.id.settings_date_format_radio_group_option_2_id:{
                                rb2.setChecked(true);
                                settings.setDateFormat(Settings.SETTINGS_DATE_FORMAT_OPTION_2);
                                dateFormatLabel.setText(rb2.getText());
                                break;
                            }
                            case R.id.settings_date_format_radio_group_option_3_id:{
                                rb3.setChecked(true);
                                settings.setDateFormat(Settings.SETTINGS_DATE_FORMAT_OPTION_3);
                                dateFormatLabel.setText(rb3.getText());
                                break;
                            }
                        }
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });

                dialog.show();
            }
        });

        View decimalSeparator = findViewById(R.id.settings_decimal_separator_id);
        final TextView decimalSeparatorLabel = decimalSeparator.findViewWithTag("label");
        decimalSeparatorLabel.setText(settings.getDecimalSepatratorDisplay(settings.getDecimalSeparator()));
        decimalSeparator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_decimal_separator, null);
                dialog.setContentView(content);
                RadioGroup radioGroup = content.findViewById(R.id.settings_decimal_separator_radio_group_id);
                View ok = content.findViewById(R.id.settings_decimal_separator_ok_id);

                final RadioButton rb1 = radioGroup.findViewById(R.id.settings_decimal_separator_radio_group_option_1_id);
                final RadioButton rb2 = radioGroup.findViewById(R.id.settings_decimal_separator_radio_group_option_2_id);
                final RadioButton rb3 = radioGroup.findViewById(R.id.settings_decimal_separator_radio_group_option_3_id);

                rb1.setText(settings.getDecimalSepatratorDisplay(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1));
                rb2.setText(settings.getDecimalSepatratorDisplay(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2));
                rb3.setText(settings.getDecimalSepatratorDisplay(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_3));

                switch (settings.getDecimalSeparator()){
                    case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1:
                        rb1.setChecked(true);
                        break;
                    case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2:
                        rb2.setChecked(true);
                        break;
                    case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_3:
                        rb3.setChecked(true);
                        break;
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.settings_decimal_separator_radio_group_option_1_id:{
                                rb1.setChecked(true);
                                settings.setDecimalSeparator(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1);
                                decimalSeparatorLabel.setText(rb1.getText());
                                break;
                            }
                            case R.id.settings_decimal_separator_radio_group_option_2_id:{
                                rb2.setChecked(true);
                                settings.setDecimalSeparator(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2);
                                decimalSeparatorLabel.setText(rb2.getText());
                                break;
                            }
                            case R.id.settings_decimal_separator_radio_group_option_3_id:{
                                rb3.setChecked(true);
                                settings.setDecimalSeparator(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_3);
                                decimalSeparatorLabel.setText(rb3.getText());
                                break;
                            }
                        }
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });

                dialog.show();
            }
        });

        View groupSeparator = findViewById(R.id.settings_group_separator_id);
        final TextView groupSeparatorLabel = groupSeparator.findViewWithTag("label");
        groupSeparatorLabel.setText(settings.getGroupSeparatorDisplay(settings.getGroupSeparator()));
        groupSeparator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(context);

                View content = LayoutInflater.from(context).inflate(R.layout.settings_group_separator, null);
                dialog.setContentView(content);
                RadioGroup radioGroup = content.findViewById(R.id.settings_group_separator_radio_group_id);
                View ok = content.findViewById(R.id.settings_group_separator_ok_id);

                final RadioButton rb1 = radioGroup.findViewById(R.id.settings_group_separator_radio_group_option_1_id);
                final RadioButton rb2 = radioGroup.findViewById(R.id.settings_group_separator_radio_group_option_2_id);

                rb1.setText(settings.getGroupSeparatorDisplay(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1));
                rb2.setText(settings.getGroupSeparatorDisplay(Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2));

                switch (settings.getGroupSeparator()){
                    case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_1:
                        rb1.setChecked(true);
                        break;
                    case Settings.SETTINGS_DECIMAL_SEPARATOR_OPTION_2:
                        rb2.setChecked(true);
                        break;
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.settings_group_separator_radio_group_option_1_id:{
                                rb1.setChecked(true);
                                settings.setGroupSeparator(Settings.SETTINGS_GROUP_SEPARATOR_OPTION_1);
                                groupSeparatorLabel.setText(rb1.getText());
                                break;
                            }
                            case R.id.settings_group_separator_radio_group_option_2_id:{
                                rb2.setChecked(true);
                                settings.setGroupSeparator(Settings.SETTINGS_GROUP_SEPARATOR_OPTION_2);
                                groupSeparatorLabel.setText(rb2.getText());
                                break;
                            }
                        }
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, true);
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });

                dialog.show();
            }
        });


        View sendEmail = findViewById(R.id.settings_send_email_id);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{context.getResources().getString(R.string.dev_email)});
                i.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_feedback));
                try {
                    context.startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final View setDefaults = findViewById(R.id.settings_set_to_defaults_id);
        setDefaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationHelper.animateContainerScale(container, true);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getResources().getString(R.string.settings_set_to_defaults_title));
                builder.setMessage(context.getResources().getString(R.string.settings_set_to_defaults_message));
                builder.setPositiveButton(context.getResources().getString(R.string.settings_set_to_defaults_reset), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        settings.setDefaults();
                        mrButtLabel.setText(context.getResources().getString(R.string.settings_mr_button_action_option_copy_expression));
                        historySizeLabel.setText(settings.getHistorySizeDisplay(Settings.SETTINGS_MAX_HISTORY_SIZE_DEFAULT));
                        saveExpRotateSwitch.setChecked(true);
                        randFormatLabel.setText(String.valueOf(Math.random()).substring(0, 3));
                        dateInHistorySwitch.setChecked(settings.isDateInHistoryItems());
                        dateFormatLabel.setText(CommonHelper.getRelativeDate(1518732445577L, context));
                    }
                });
                builder.setNegativeButton(context.getResources().getString(R.string.dilog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.create().dismiss();
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        AnimationHelper.animateContainerScale(container, false);
                    }
                });
                builder.create().show();
            }
        });

        View rateAnApp = findViewById(R.id.settings_rate_an_app_id);
        rateAnApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntendHelper.openBrowser(context, context.getResources().getString(R.string.app_play_store_link));
            }
        });


        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View image = view.findViewWithTag("image");
                if (image.getX() < -(image.getWidth()))
                    image.setX(CommonHelper.getScreenWidth(context)+image.getWidth());
                AnimationHelper.animateVersion(image, 100, 180);
            }
        });

        version.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View image = view.findViewWithTag("image");
                if (image.getX() < -(image.getWidth()))
                    image.setX(CommonHelper.getScreenWidth(context)+image.getWidth());
                AnimationHelper.animateVersion(image, 250, 360);
                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
