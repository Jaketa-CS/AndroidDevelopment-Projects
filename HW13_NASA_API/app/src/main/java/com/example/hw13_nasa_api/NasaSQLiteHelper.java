package com.example.hw13_nasa_api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NasaSQLiteHelper extends SQLiteOpenHelper {
    static final String DB_NAME= "database2.sqlite";
    static final int DB_VERSION = 1;
    Cursor cursor = null;

    public NasaSQLiteHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE images("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "date Text UNIQUE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insert(String date) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);

        sqLiteDatabase.insert("images",null,contentValues);
    }
    public void selectAll() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM images";

        cursor = sqLiteDatabase.rawQuery(query,null,null);
    }
    public String getDate(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("images", new String[]{"_id","date"},
                null,null,null,null,null);
        if(cursor == null){
            selectAll();
        }
        if(cursor.moveToPosition(position)){
            String date = cursor.getString(1);
            return date;
        }
        return null;
    }


    public int getCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM images",null);

        return cursor.getCount();
    }
    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "DELETE FROM images";

        db.execSQL(selectQuery);
    }
}
