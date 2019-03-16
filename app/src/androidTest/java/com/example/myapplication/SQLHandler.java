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
    public static final String COLUMN_TYPEMESSAGE = "message"; 


    public SQLHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ITEM_create = "CREATE TABLE " + TABLE_ITEM + "(" +
                COLUMN_ITEMID + " VARCHAR PRIMARY KEY, " +
                COLUMN_ITEMCO2 + " INTEGER, " +
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

        db.execSQL("INSERT INTO " + TABLE_ITEM + "(COLUMN_ITEMID, COLUMN_ITEMCO2, COLUMN_ITEMPRICE, COLUMN_ITEMNAME, COLUMN_ITEMTYPEID) VALUES ('9300675016902', 20, 2.30, 'Water Bottle', (SELECT COLUMN_TYPEID FROM TABLE_TYPE WHERE ) )");
        db.close();
    }
    //water bottle - 9300675016902
    //red bull sugarfree - 90162800
    //red bull - 90162602
    //coles bag - 9348372157405

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);

        onCreate(db);
    }



    //add a new purchase to the database
    public void addPurchase(Purchase purchase) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PURCHASEID, purchase.getID());
        values.put(COLUMN_PURCHASETRIPID, purchase.getTripID());
        values.put(COLUMN_PURCHASEITEMID, purchase.getItemID());
        values.put(COLUMN_PURCHASEQUANTITY, purchase.getQuantity());
        values.put(COLUMN_PURCHASEDATE, purchase.getDate());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PURCHASE,null, values);
        db.close();
    }

    //delete a purchase from the database
    public void deletePurchase(String purchaseID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PURCHASE + " WHERE " + COLUMN_PURCHASEID + "=\"" + purchaseID + "\";");
    }

    //add a new item to the database
    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMID, item.getID());
        values.put(COLUMN_ITEMCO2, item.getCO2());
        values.put(COLUMN_ITEMPRICE, item.getPrice());
        values.put(COLUMN_ITEMNAME, item.getName());
        values.put(COLUMN_ITEMTYPEID, item.getTypeID());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEM, null, values);
        db.close();
    }

    //delete an item from the database
    public void deleteItem(String itemID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ITEM + " WHERE " + COLUMN_ITEMID + "=\"" + itemID + "\";");
    }

    //add a new type to the database
    public void addType(Type type) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPEID, type.getID());
        values.put(COLUMN_TYPENAME, type.getType());
        values.put(COLUMN_TYPEMESSAGE, type.getMessage());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TYPE, null, values);
        db.close();
    }

    //delete a type from the database
    public void deleteType(String typeID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TYPE + " WHERE " + COLUMN_TYPEID + "=\"" + typeID + "\";");
    }

    public String getItemName(Item item){
        String query = "SELECT " + COLUMN_ITEMNAME + " FROM " + TABLE_ITEM + " WHERE " + COLUMN_ITEMID + "=\"" + item.getID();
        return query;
    }

    public String getItemCO2(Item item){
        String query = "SELECT " + COLUMN_ITEMCO2 + " FROM " + TABLE_ITEM + " WHERE " + COLUMN_ITEMID + "=\"" + item.getID();
        return query;
    }
}
