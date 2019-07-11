package nestcalc.mymorce.com.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import nestcalc.mymorce.com.Settings.Settings;

public class RecordStorage {

    private SharedPreferences mStoragePrefs;
    private String RECORD_STORAGE_PREFS_KEY = "RECORD_STORAGE_PREFS_KEY";
    private String RECORD_STORAGE_ARRAY_KEY = "RECORD_STORAGE_ARRAY_KEY";
    private String LOG = getClass().getName();
    private Settings settings;
    /**
     * Конструктор хранилища записей.
     * @param context
     */
    public RecordStorage(Context context){
        mStoragePrefs = context.getSharedPreferences(RECORD_STORAGE_PREFS_KEY, Context.MODE_PRIVATE);
        settings = new Settings(context);
        if (mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, "").equals("")){
            JSONArray jsonArray = new JSONArray();
            mStoragePrefs
                    .edit()
                    .putString(RECORD_STORAGE_ARRAY_KEY, jsonArray.toString())
                    .apply();
        }
    }

    /**
     * Добавление записи в хранилище записей.
     * @param record
     * @return
     */
    public boolean add(Record record){
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));
            JSONObject recordJson = new JSONObject();
            recordJson.put(Record.ID_KEY, record.getID());
            recordJson.put(Record.TITLE_KEY, record.getTitle());
            recordJson.put(Record.EXPRESSION_KEY, record.getExpression());
            recordJson.put(Record.RESULT_KEY, record.getResult());
            recordJson.put(Record.FAVORITE_KEY, record.isFavorited());
            recordJson.put(Record.DATE_KEY, String.valueOf(record.getDate()));

            recordArray.put(recordJson);

            Integer maxItems = Integer.valueOf(settings.getHistorySizeDisplay(settings.getHistorySize()));
            // Удалить старые записи при переполнении
            if (recordArray.length() > maxItems){
                for (int i = 0; i < recordArray.length() - maxItems; i++) {
                    recordArray.remove(0);
                }
            }

            // Сохранение
            mStoragePrefs
                    .edit()
                    .putString(RECORD_STORAGE_ARRAY_KEY, recordArray.toString())
                    .apply();

            Log.e(LOG, "ADDED RECORD "+record.getExpression());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Получить запись из хранилища по выражению.
     * @param ID
     * @return
     */
    public Record get(String ID){
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));
            Record record = new Record();

            for (int i = 0; i < recordArray.length(); i++) {
                JSONObject jo = recordArray.getJSONObject(i);

                if (jo.getString(Record.ID_KEY).equals(ID)){
                    record.setTitle(jo.getString(Record.TITLE_KEY));
                    record.setExpression(jo.getString(Record.EXPRESSION_KEY));
                    record.setResult(jo.getString(Record.RESULT_KEY));
                    record.setDate(jo.getLong(Record.DATE_KEY));
                    record.setFavorited(Boolean.valueOf(jo.getString(Record.FAVORITE_KEY)));
                    break;
                }
            }

            return record;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Заменить запись на новую запись по выражению.
     * @param id
     * @param newRecord
     * @return
     */
    public boolean update(String id, Record newRecord){
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));

            for (int i = 0; i < recordArray.length(); i++) {
                JSONObject jo = recordArray.getJSONObject(i);

                if (jo.getString(Record.ID_KEY).equals(id)){
                    JSONObject newRecordJson = new JSONObject();
                    newRecordJson.put(Record.ID_KEY, newRecord.getID());
                    newRecordJson.put(Record.TITLE_KEY, newRecord.getTitle());
                    newRecordJson.put(Record.EXPRESSION_KEY, newRecord.getExpression());
                    newRecordJson.put(Record.RESULT_KEY, newRecord.getResult());
                    newRecordJson.put(Record.DATE_KEY, String.valueOf(newRecord.getDate()));
                    newRecordJson.put(Record.FAVORITE_KEY, newRecord.isFavorited());
                    recordArray.put(i, newRecordJson);
                    break;
                }
            }
            mStoragePrefs
                    .edit()
                    .putString(RECORD_STORAGE_ARRAY_KEY, recordArray.toString())
                    .apply();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удаление записи по выражению.
     * @param id
     * @return
     */
    public boolean remove(String id){
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));

            for (int i = 0; i < recordArray.length(); i++) {
                JSONObject jo = recordArray.getJSONObject(i);

                if (jo.getString(Record.ID_KEY).equals(id)){
                    recordArray.remove(i);
                    break;
                }
            }
            mStoragePrefs
                    .edit()
                    .putString(RECORD_STORAGE_ARRAY_KEY, recordArray.toString())
                    .apply();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удаление записи по выражению.
     * @return
     */
    public boolean removeAllUnfavorited(){
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < recordArray.length(); i++) {
                JSONObject jo = recordArray.getJSONObject(i);
                if (jo.getString(Record.FAVORITE_KEY).equals("false")){
                    list.add(i);
                    Log.e("c", jo.getString(Record.RESULT_KEY));
                    recordArray.remove(i);
                    i--;
                }
            }

            mStoragePrefs
                    .edit()
                    .putString(RECORD_STORAGE_ARRAY_KEY, recordArray.toString())
                    .apply();

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удаление всех записей
     * @return
     */
    public void removeAll(){
        mStoragePrefs
                .edit()
                .putString(RECORD_STORAGE_ARRAY_KEY, new JSONArray().toString())
                .apply();
    }

    /**
     * Получить все записи.
     * @return
     */
    public List<Record> getAll(){
        List<Record> recordList = new ArrayList<>();
        try {
            JSONArray recordArray = new JSONArray(mStoragePrefs.getString(RECORD_STORAGE_ARRAY_KEY, ""));

            for (int i = 0; i < recordArray.length(); i++) {
                Record record = new Record();
                JSONObject jo = recordArray.getJSONObject(i);

                record.setID(jo.getString(Record.ID_KEY));
                record.setTitle(jo.getString(Record.TITLE_KEY));
                record.setExpression(jo.getString(Record.EXPRESSION_KEY));
                record.setResult(jo.getString(Record.RESULT_KEY));
                record.setDate(jo.getLong(Record.DATE_KEY));
                record.setFavorited(Boolean.valueOf(jo.getString(Record.FAVORITE_KEY)));

                recordList.add(record);

                Log.e(LOG, record.getResult());
            }

            return recordList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Является ли хранилище пустым?
     * @return
     */
    public boolean isEmpty(){
        return !(getAll().size() > 0);
    }
}
