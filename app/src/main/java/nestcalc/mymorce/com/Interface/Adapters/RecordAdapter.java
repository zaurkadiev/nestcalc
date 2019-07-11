package nestcalc.mymorce.com.Interface.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.formats.NativeAppInstallAdView;

import java.util.ArrayList;
import java.util.List;

import nestcalc.mymorce.com.Helpers.AnalysisHelper;
import nestcalc.mymorce.com.Helpers.CommonHelper;
import nestcalc.mymorce.R;
import nestcalc.mymorce.com.Settings.Settings;
import nestcalc.mymorce.com.Storage.Record;

public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Record> recordList;
    private List<Record> bufferList;
    private Context context;
    private String LOG = getClass().getName();
    private RecordAdapterInterface mListener;
    private View container;
    private Settings settings;

    private int AD_APP = 0;
    private int AD_CONTENT = 1;
    private int MENU_ITEM = 2;

    public RecordAdapter(Context context, View container, List<Record> recordList){
        this.context = context;
        this.container = container;
        this.recordList = recordList;
        this.settings = new Settings(context);
    }

    public void setRecordList(List<Record> mRecordList) {
        this.recordList = mRecordList;
    }

    public void setListener(RecordAdapterInterface recordAdapterInterface){
        this.mListener = recordAdapterInterface;
    }

    public boolean appendRecord(Record record){
        Integer maxSize = Integer.valueOf(settings.getHistorySizeDisplay(settings.getHistorySize()));
        if (bufferList != null){
            bufferList.add(record);
            bufferList = CommonHelper.cropList(bufferList, maxSize);
        }else {

            if (maxSize < getItemCount() + 1){
                recordList.remove(0);
                recordList.add(record);
                notifyItemRemoved(0);
                notifyItemInserted(getItemCount()-1);
                notifyItemChanged(0);
                notifyItemChanged(getItemCount()-1);
            } else {
                recordList.add(record);
                notifyItemInserted(getItemCount()-1);
                notifyItemChanged(getItemCount()-1);
            }
        }
        return true;
    }

    class RecordHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView expression;
        TextView result;
        TextView date;
        ImageView favoriteStar;
        View container;

        public RecordHolder(View v){
            super(v);
            this.container = v.findViewById(R.id.record_container_id);
            this.title = v.findViewById(R.id.record_title_id);
            this.expression = v.findViewById(R.id.record_expression_id);
            this.result = v.findViewById(R.id.record_result_id);
            this.favoriteStar = v.findViewById(R.id.record_favorite_id);
            this.date = v.findViewById(R.id.record_date_id);
        }
    }

    class NativeAppHolder extends RecyclerView.ViewHolder{
        TextView title;
        NativeAppInstallAdView container;
        ImageView logo;
        TextView body;
        TextView price;

        public NativeAppHolder(View v){
            super(v);
            this.title = v.findViewById(R.id.native_app_text);
            this.logo = v.findViewById(R.id.native_app_logo);
            this.container = v.findViewById(R.id.native_app_container);
            this.body = v.findViewById(R.id.native_app_body);
            this.price = v.findViewById(R.id.native_app_price);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == AD_APP){
             view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.record_ad_layout, parent, false);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = CommonHelper.getScreenWidth(context);
            view.setLayoutParams(layoutParams);
            return new NativeAppHolder(view);

        }else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.record_layout, parent, false);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = CommonHelper.getScreenWidth(context);
            view.setLayoutParams(layoutParams);
            return new RecordHolder(view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        Object recyclerViewItem = recordList.get(position);
//        if (position % 10 == 0) {
//            return AD_APP;
//        } else{
//            return MENU_ITEM;
//        }
        return MENU_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int position) {


        if(getItemViewType(position) == AD_APP){

//            final NativeAppHolder holder = (NativeAppHolder) h;
//            holder.container.setVisibility(View.GONE);
//
//            holder.title.setText("");
//            holder.body.setText("");
//            holder.logo.refreshDrawableState();
//            holder.logo.setImageDrawable(null);
//
//            AdLoader.Builder adLoader = new AdLoader.Builder(context, advertizing.getAdIdNative());
//            adLoader.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
//                @Override
//                public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
//                    Picasso.with(context)
//                            .load(nativeAppInstallAd.getIcon().getUri())
//                            .fit()
//                            .centerCrop()
//                            .into(holder.logo);
//                    holder.title.setText(nativeAppInstallAd.getHeadline());
//                    holder.body.setText(nativeAppInstallAd.getBody());
//                    holder.price.setText(nativeAppInstallAd.getPrice());
//                    holder.container.setBodyView(holder.body);
//                    holder.container.setPriceView(holder.price);
//                    holder.container.setHeadlineView(holder.title);
//                    holder.container.setIconView(holder.logo);
//                    holder.container.setNativeAd(nativeAppInstallAd);
////                    holder.container.set
//                    holder.container.setVisibility(View.VISIBLE);
//                }
//            });
//            adLoader.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
//                @Override
//                public void onContentAdLoaded(NativeContentAd nativeContentAd) {
//
//                }
//            });
//            adLoader.build();
//            AdLoader loader = adLoader.build();
//            loader.loadAd(new AdRequest.Builder().build());
        }else {

            final Record record =  recordList.get(position);
            final RecordHolder holder = (RecordHolder) h;
            holder.title.setText(record.getTitle());
            holder.expression.setText(record.getExpression());

            if(settings.isDateInHistoryItems()){
                holder.date.setVisibility(View.VISIBLE);
            }else {
                holder.date.setVisibility(View.GONE);
            }

            switch (settings.getDateFormat()){
                case Settings.SETTINGS_DATE_FORMAT_OPTION_1:{
                    String s = CommonHelper.getRelativeDate(record.getDate(), context);
                    holder.date.setText(s);
                    break;
                }
                case Settings.SETTINGS_DATE_FORMAT_OPTION_2:{
                    holder.date.setText(CommonHelper.getDate(record.getDate(), context));
                    break;
                }

                case Settings.SETTINGS_DATE_FORMAT_OPTION_3:{
                    holder.date.setText(CommonHelper.getDateTime(record.getDate(), context));
                    break;
                }
                default:{
                    holder.date.setText(CommonHelper.getRelativeDate(record.getDate(), context));
                    break;
                }
            }

            try {
                holder.result.setText(AnalysisHelper.formatOutput(record.getResult(), context));
            } catch (NumberFormatException exeption){
                exeption.getMessage();
            }

            if (record.isFavorited())
                holder.favoriteStar.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_filled_gold_48dp));
            else
                holder.favoriteStar.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_empty_48dp));

            holder.favoriteStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (record.isFavorited()){
                        record.setFavorited(false);
                        mListener.onUnFavorite(record, position);
                        holder.favoriteStar.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_empty_48dp));
                    } else {
                        record.setFavorited(true);
                        mListener.onFavorite(record, position);
                        holder.favoriteStar.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_filled_gold_48dp));
                    }
                }
            });

            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final BottomSheetDialog dialog = new BottomSheetDialog(context);
                    View v = LayoutInflater.from(context).inflate(R.layout.record_bottom_sheet_layout, null);
                    View take = v.findViewById(R.id.record_bottom_sheet_take_id);
                    View cRes = v.findViewById(R.id.record_bottom_sheet_copy_result_id);
                    final View cExp = v.findViewById(R.id.record_bottom_sheet_copy_expression_id);
                    View share = v.findViewById(R.id.record_bottom_sheet_share_id);
                    View remove = v.findViewById(R.id.record_bottom_sheet_remove_id);

                    take.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onTakeRecord(record, position);
                            dialog.dismiss();
                        }
                    });

                    cRes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onCopyResult(record, position);
                            dialog.dismiss();
                        }
                    });

                    cExp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onCopyExpression(record, position);
                            dialog.dismiss();
                        }
                    });

                    share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onShare(record, position);
                            dialog.dismiss();
                        }
                    });

                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onRemove(record, position);
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
//                        AnimationHelper.animateContainerScale(container, true);
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
//                        AnimationHelper.animateContainerScale(container, false);
                        }
                    });
                    dialog.show();
                }
            });

        }


    }

    public void removeItem(int position){
        recordList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, recordList.size());
    }

    public void removeAll(){
        notifyItemRangeRemoved(0, recordList.size());
        notifyItemRangeChanged(0, recordList.size());
        recordList.clear();
    }

    public void removeNonFaves(){

        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            if (!record.isFavorited())
            {
                recordList.remove(record);
                notifyItemRemoved(i);
                notifyItemChanged(i);
                i--;
            }
        }
    }

    public void showFaves() {

        if (bufferList != null) return;

        bufferList = new ArrayList<>(recordList);

        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            if (!record.isFavorited())
            {
                recordList.remove(record);
                notifyItemRemoved(i);
                notifyItemChanged(i);
                i--;
            }
        }
    }

    public void showAll() {

        if (bufferList == null) return;

        recordList = bufferList;
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            if (!record.isFavorited())
            {
                notifyItemInserted(i);
                notifyItemChanged(i);
            }
        }

        bufferList = null;
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
