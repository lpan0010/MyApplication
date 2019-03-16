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
    public static final String COLUMN_ITEMCO2 = "CO2";
    public static final String COLUMN_ITEMPRICE = "price";
    public static final String COLUMN_ITEMNAME = "name";
    public static final String COLUMN_ITEMTYPEID = "TypeID";

    public static final String TABLE_ITEMTYPE= "ITEM_TYPE";
    public static final String COLUMN_ITEMTYPEMESSAGE = "message";


    public SQLHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
