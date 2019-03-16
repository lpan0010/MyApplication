package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class SQLHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "E-mission.db";
    public static final String TABLE_PURCHASE = "purchase";
    public static final String COLUMN_PURCHASEID = "ID";
    public static final String COLUMN_PURCHASETRIPID = "tripID";
    public static final String COLUMN_PURCHASEITEMID = "itemID";
    public static final String COLUMN_PURCHASEQUANTITY = "quantity";
    public static final String COLUMN_PURCHASEDATE = "date";

    public static final String TABLE_ITEM= "ITEM";
    public static final String COLUMN_ITEMID = "itemID";
    public static final String COLUMN_ITEMCO2 = "CO2";
    public static final String COLUMN_ITEMPRICE = "price";
    public static final String COLUMN_ITEMNAME = "name";
    public static final String COLUMN_ITEMTYPEID= "ITEM_TYPE";

    public static final String TABLE_TYPE= "ITEM_TYPE";
    public static final String COLUMN_TYPEID = "TypeID";
    public static final String COLUMN_TYPENAME = "name";
    public static final String COLUMN_TYPEMESSAGE = "message"; //hi


    public SQLHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ITEM_create = "CREATE TABLE " + TABLE_ITEM + "(" +
                COLUMN_ITEMID + " VARCHAR PRIMARY KEY, " +
                COLUMN_ITEMCO2 + " VARCHAR, " +
                COLUMN_ITEMPRICE + " INTEGER, " +
                COLUMN_ITEMNAME + " VARCHAR, " +
                COLUMN_ITEMTYPEID + " VARCHAR, " +
                "FOREIGN KEY (COLUMN_ITEMTYPEID) REFERENCES TABLE_TYPE (COLUMN_TYPEID)" + ");";

        String PURCHASE_create = "CREATE TABLE " + TABLE_PURCHASE + "(" +
                COLUMN_PURCHASEID + " VARCHAR PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PURCHASETRIPID + " VARCHAR, " +
                COLUMN_PURCHASEITEMID + " VARCHAR, " +
                COLUMN_PURCHASEQUANTITY + " INTEGER," +
                COLUMN_PURCHASEDATE + " VARCHAR, " +
                "FOREIGN KEY (COLUMN_PURCHASEITEMID) REFERENCES TABLE_ITEM (COLUMN_ITEMTYPEID)" + ");";

        String TYPE_create = "CREATE TABLE " + TABLE_TYPE + "(" +
                COLUMN_TYPEID + " VARCHAR PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPENAME + " VARCHAR, " +
                COLUMN_TYPEMESSAGE + " VARCHAR " + ");";

        db.execSQL(ITEM_create);
        db.execSQL(PURCHASE_create);
        db.execSQL(TYPE_create);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);

        onCreate(db);
    }


}
