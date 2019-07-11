package nestcalc.mymorce.com.Interface.Adapters;

import nestcalc.mymorce.com.Storage.Record;

public interface RecordAdapterInterface {
    void onTakeRecord(Record record, int position);
    void onCopyResult(Record record, int position);
    void onCopyExpression(Record record, int position);
    void onShare(Record record, int position);
    void onRemove(Record record, int position);
    void onFavorite(Record record, int position);
    void onUnFavorite(Record record, int position);
}
