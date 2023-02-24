package com.example.crudypizzaassignmnet3;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class DBAdapter {
    //static variables for the names of the colums of the database
    public static final String KEY_ROWID = "id";
    public static final String KEY_CUSTOMERINFO = "CustomerInfo";
    public static final String KEY_SIZE = "Size";
    public static final String KEY_TOPPING1 = "Topping1";
    public static final String KEY_TOPPING2 = "Topping2";
    public static final String KEY_TOPPING3 = "Topping3";
    public static final String KEY_DATETIME = "DateTime";
    //Tag for the logcat
    public static final String TAG = "DBAdapter";
    //name of the database, table, version
    private static final String DATABASE_NAME = "CruddyPizzaDB";
    private static final String DATABASE_TABLE = "Orders";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table Orders(id integer primary key autoincrement,"
                    + "CustomerInfo text not null, Size integer, Topping1 integer, Topping2 integer, Topping3 integer, DateTime text not null);";

    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Orders");
            onCreate(db);
        }//end method onUpgrade
    }

    //open the database
    public DBAdapter open() {
        try {
            db = DBHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    //close the database
    public void close() {
        DBHelper.close();
    }

    //insert a contact into the database
    public long insertOrder(String name, int size, int topping1, int topping2, int topping3, String datetime) {
        ContentValues initialValues = new ContentValues();
        try {

            initialValues.put(KEY_CUSTOMERINFO, name);
            initialValues.put(KEY_SIZE, size);
            initialValues.put(KEY_TOPPING1, topping1);
            initialValues.put(KEY_TOPPING2, topping2);
            initialValues.put(KEY_TOPPING3, topping3);
            initialValues.put(KEY_DATETIME, datetime);
            return db.insert(DATABASE_TABLE, null, initialValues);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //delete a particular contact
    public boolean deleteOrder(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //retrieve all the contacts
    public Cursor getAllOrders() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_CUSTOMERINFO, KEY_SIZE, KEY_TOPPING1, KEY_TOPPING2, KEY_TOPPING3, KEY_DATETIME}, null, null, null, null, null);
    }

    //retrieve a single contact
    public Cursor getOrder(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{
                KEY_ROWID,
                        KEY_CUSTOMERINFO,
                        KEY_SIZE, KEY_TOPPING1,
                        KEY_TOPPING2,
                        KEY_TOPPING3,
                        KEY_DATETIME},
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //updates a contact
    public boolean updateOrder(long rowId, String name, int size, int topping1, int topping2, int topping3, String datetime) {
        ContentValues cval = new ContentValues();
        cval.put(KEY_CUSTOMERINFO, name);
        cval.put(KEY_SIZE, size);
        cval.put(KEY_TOPPING1, topping1);
        cval.put(KEY_TOPPING2, topping2);
        cval.put(KEY_TOPPING3, topping3);
        cval.put(KEY_DATETIME, datetime);

        return db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId, null) > 0;
    }

}//end class DBAdapter
