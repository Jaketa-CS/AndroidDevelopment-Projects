package com.example.hw12_xkcd_comics;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ComicSQLiteHelper extends SQLiteOpenHelper {
    static final String NAME= "database.sqlite";
    static final int DB_VERSION = 1;
    Cursor cursor = null;

    public ComicSQLiteHelper(Context context) {super(context,NAME,null,DB_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE comics("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "number Integer UNIQUE , "+
                "title Text UNIQUE , "+
                "cartoon Text UNIQUE);"
        );
    }
    public void insert(int number, String title, String cartoon) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",number);
        contentValues.put("title",title);
        contentValues.put("cartoon",cartoon);


        sqLiteDatabase.insert("comics",null,contentValues);
    }

    int getCount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM comics",null);

        return cursor.getCount();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }


    public void selectAll() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM comics";
        query += " ORDER BY number DESC";

        cursor = sqLiteDatabase.rawQuery(query,null,null);
    }

    public int getNumber(int position) {
        if(cursor == null){
            selectAll();
        }
        if (cursor.moveToPosition(position)){
            return cursor.getInt(1);
        }
        return -1;
    }

    public String getTitle(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics", new String[]{"_id","title"},
                null,null,null,null,null);
        if(cursor == null){
            selectAll();
        }
        if(cursor.moveToPosition(position)){
            String title = cursor.getString(1);
            return title;
        }
        return null;
    }

    public String getCartoon(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics", new String[]{"_id","cartoon"},
                null,null,null,null,null);
        if(cursor == null){
            selectAll();
        }
        if(cursor.moveToPosition(position)){
            String cartoon = cursor.getString(1);
            return cartoon;
        }
        return null;
    }


    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "DELETE FROM comics";

        db.execSQL(selectQuery);
    }
}
