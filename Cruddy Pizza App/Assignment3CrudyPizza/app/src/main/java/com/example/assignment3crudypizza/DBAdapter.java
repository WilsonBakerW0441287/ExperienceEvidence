package com.example.assignment3crudypizza;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

import androidx.annotation.NonNull;

public class DBAdapter {
    // Database fields, we need size, toppings, order date, and "customer info"
    public static final String KEY_ROWID = "id";
    public static final String KEY_SIZE = "Size";
    public static final String KEY_TOPPINGS = "Toppings";
    public static final String KEY_ORDERDATE = "Orders Date/Time";
    public static final String KEY_INFO = "info";

    public static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "CruddyPizzaDB";
    private static final String DATABASE_TABLE = "Orders";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table Orders (id integer primary key autoincrement, "
                    + "Size text not null, Toppings text not null, "
                    + "'Order Date/Time' text not null, info text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public void onCreate(@NonNull SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            Log.w(TAG,"Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Orders");
            onCreate(db);
        }//end method onUpgrade
    }//end class DatabaseHelper

    //open the database
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close()
    {
        DBHelper.close();
    }

    //insert a Orders into the database
    public long insertOrder(String size, String toppings, String orderDate, String info)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SIZE, size);
        initialValues.put(KEY_TOPPINGS, toppings);
        initialValues.put(KEY_ORDERDATE, orderDate);
        initialValues.put(KEY_INFO, info);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
//    original example
//    public long insertOrders(String name,String email)
//    {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(KEY_NAME, name);
//        initialValues.put(KEY_EMAIL, email);
//        return db.insert(DATABASE_TABLE, null, initialValues);
//    }

    //delete a particular Orders
    public boolean deleteOrder(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
//    public boolean deleteOrders(long rowId)
//    {
//        return db.delete(DATABASE_TABLE,KEY_ROWID + "=" + rowId,null) >0;
//    }

    //retrieve all the Orders
    public Cursor getAllOrders()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SIZE, KEY_TOPPINGS, KEY_ORDERDATE, KEY_INFO},
                null, null, null, null, null);
    }
//    public Cursor getAllOrders()
//    {
//        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,
//                KEY_EMAIL},null,null,null,null,null);
//    }

    //retrieve a single Orders
    public Cursor getOrder(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SIZE, KEY_TOPPINGS, KEY_ORDERDATE, KEY_INFO},
                        KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
//    public Cursor getOrders(long rowId) throws SQLException
//    {
//        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
//                KEY_NAME,KEY_EMAIL},KEY_ROWID + "=" + rowId,null,null,null,null,null);
//        if(mCursor != null)
//        {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//    }

    //updates a Orders
    public boolean updateOrder(long rowId, String size, String toppings, String orderDate, String info)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_SIZE, size);
        args.put(KEY_TOPPINGS, toppings);
        args.put(KEY_ORDERDATE, orderDate);
        args.put(KEY_INFO, info);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
//    public boolean updateOrders(long rowId,String name,String email)
//    {
//        ContentValues cval = new ContentValues();
//        cval.put(KEY_NAME, name);
//        cval.put(KEY_EMAIL, email);
//        return db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId,null) >0;
//    }

}//end class DBAdapter
