package android.sweproject.fyndhr;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "account_table";
    private static final String COL1 = "username";
    private static final String COL2 = "pass";
    private static final String COL3 = "first_name";
    private static final String COL4 = "last_name";
    private static final String COL5 = "sex";
    private static final String COL6 = "birth_date";
    private static final String COL7 = "nreviews";
    private static final String COL8 = "fun";
    private static final String COL9 = "safe";
    private static final String COL10 = "reliable";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
        //onUpgrade(db, 1, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (username varchar(12) PRIMARY KEY, pass varchar(15), first_name varchar(12), last_name varchar(15), sex varchar(1), birth_date date, nreviews int DEFAULT 0, fun real DEFAULT 0, safe real DEFAULT 0, reliable real DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Inserts account information into the table.
     *
     * */
    public boolean insertData(String username, String password, String first, String last, String sex, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3, first);
        contentValues.put(COL4, last);
        contentValues.put(COL5, sex);
        contentValues.put(COL6, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /* Returns all table information in the form of a Cursor
     *
     * */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    /* Returns the password for a specified account
     *
     * */
    public Cursor getPassword(String udgggg1) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL1 + "= '" + udgggg1 + "';", null);
        return res;
    }

    /* Returns the number of reviews for a specified account
     *
     * */
    public int getReviews(String userin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE username= '" + userin + "'", null);
        if (res.getCount() == 0) {
            //error message
            return -1;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(6));

            }
            return Integer.parseInt(buffer.toString());

        }
    }

    /* Returns rating value for given account
     * @param idexin: 7 for fun
     *                8 for safe
     *                9 for reliable
     **/
    public double getRating(String userin, int indexin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE username= '" + userin + "';", null);
        if (res.getCount() == 0) {
            //error message
            return -1;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getInt(indexin));

            }
            return Double.parseDouble(buffer.toString());

        }
    }

    /* Adds 1 to the reviews for an account
     *
     * */
    public void addReview(String usr) {
        int reviews = getReviews(usr);
        int newReviews = reviews + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL7, newReviews);
        db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
    }

    /* Inserts fun/safe/reliable into table with new averages.
     *
     * */
    public void insertRating(double fun, double safe, double reliable, String usr) {

        double reviews = getReviews(usr);

        // No reviews / new account ->
        // Add review and rating
        if (reviews == 0) {
            addReview(usr);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL8, fun);
            contentValues.put(COL9, safe);
            contentValues.put(COL10, reliable);
            db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
        }

        // One or more reviews ->
        // Compute average FUN and store it in the table

        addReview(usr);
        double averageFun = getRating(usr, 7);
        double totalFun = averageFun * (reviews - 1);
        double newAverageFun = (totalFun + fun) / (reviews);

        // Compute average SAFE and store it in the table
        double averageSafe = getRating(usr, 8);
        double totalSafe = averageSafe * (reviews - 1);
        double newAverageSafe = (totalSafe + safe) / (reviews);

        // Compute average RELIABLE and store it in the table
        double averageReliable = getRating(usr, 9);
        double totalReliable = averageReliable * (reviews - 1);
        double newAverageReliable = (totalReliable + reliable) / (reviews);

        // Write to DB
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL8, newAverageFun);
        contentValues.put(COL9, newAverageSafe);
        contentValues.put(COL10, newAverageReliable);
        db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
    }
}

