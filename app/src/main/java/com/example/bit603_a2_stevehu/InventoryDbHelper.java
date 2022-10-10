package com.example.bit603_a2_stevehu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InventoryItem.TABLE_NAME + " (" +
                    InventoryItem._ID + " INTEGER PRIMARY KEY," +
                    InventoryItem.COLUMN_NAME_NAME + " TEXT," +
                    InventoryItem.COLUMN_NAME_QUANTITY + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + InventoryItem.TABLE_NAME;


    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean updateItem(String name, int quantity) {
        //try to obtain the existing item by name
        Integer oldQuantity = getAllItems().get(name);

        if (oldQuantity != null) {
            //update quantity when item already exists
            return updateQuantityByName(name, oldQuantity + quantity);
        } else {
            //insert quantity when item doesn't exist
            return insertItem(name, quantity);
        }
    }

    /**
     * insert a new inventory item
     * @param name
     * @param quantity
     * @return
     */
    private boolean insertItem(String name, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryItem.COLUMN_NAME_NAME, name);
        values.put(InventoryItem.COLUMN_NAME_QUANTITY, quantity);
        long newRowId = db.insert(InventoryItem.TABLE_NAME, null, values);
        return newRowId != -1;
    }

    /**
     * update the quantity of a inventory item by name
     * @param name
     * @param newQuantity
     * @return
     */
    private boolean updateQuantityByName(String name, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryItem.COLUMN_NAME_QUANTITY, newQuantity);
        String selection = InventoryItem.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = {name};

        int count = db.update(
                InventoryItem.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count > 0;
    }

    /**
     * get all saved inventory items
     * @return
     */
    public Map<String, Integer> getAllItems() {
        Map<String, Integer> rst = new HashMap<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(InventoryItem.TABLE_NAME, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(InventoryItem.COLUMN_NAME_NAME));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryItem.COLUMN_NAME_QUANTITY));
            rst.put(name, quantity);
        }
        cursor.close();
        return rst;
    }
}