package nestcalc.mymorce.com.Storage;

public class Record {

    private String ID;
    private String title;
    private String expression;
    private String result;
    private long date;
    private boolean isFavorited;

    static String ID_KEY = "RECORD_ID";
    static String TITLE_KEY = "RECORD_TITLE_KEY";
    static String EXPRESSION_KEY = "RECORD_EXPRESSION_KEY";
    static String RESULT_KEY = "RECORD_RESULT_KEY";
    static String DATE_KEY = "DATE_KEY";
    static String FAVORITE_KEY = "RECORD_FAVORITE_KEY";

    public static Record newInstance(
            String title,
            String expression,
            String result,
            long date,
            boolean favorited
    ) {
        Record record = new Record(title, expression, result, date, favorited);
        record.generateID();
        return record;
    }

    private Record(String title, String expression, String result, long date, Boolean favorited) {
        this.title = title;
        this.expression = expression;
        this.result = result;
        this.date = date;
        this.isFavorited = favorited;
    }

    public Record(){
        this.ID = "";
        this.title = "";
        this.expression = "";
        this.result = "";
        this.isFavorited = false;
        this.date = System.currentTimeMillis();
    }

    /**
     * Установть дату
     * @param date
     */
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Возвратить дату
     * @return
     */
    public long getDate() {
        return date;
    }

    /**
     * Создать новй идентификатор для записи.
     */
    public void generateID(){
        String time = String.valueOf(System.currentTimeMillis());
        this.ID = String.valueOf(time.hashCode());
    }

    /**
     * Установить существующий идентификатор.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Получить идентификатор.
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Получить выражение.
     * @return
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Получить результат.
     * @return Result
     */
    public String getResult() {
        return result;
    }

    /**
     * Получить название.
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Проверка по идентификатору.
     * @param id
     * @return
     */
    public boolean isEqual(String id){
        return ID.equals(id);
    }

    /**
     * Является ли запись избранной?
     * @return
     */
    public boolean isFavorited() {
        return isFavorited;
    }

    /**
     * Установить избранна ли запись.
     * @param favorited
     */
    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    /**
     * Установить выражение записи.
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Установить результат записи.
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Установить наименование записи.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
