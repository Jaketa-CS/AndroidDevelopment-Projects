package com.example.cc11_numbersqlitehelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import java.io.File;
import java.util.Random;

public class ColorSQLiteHelper extends SQLiteOpenHelper {
    static final String NAME= "database.sqlite";
    static final int VERSION = 1;
    static final int DEFAULT = 0;
    static final int DEF = 1;
    static final int R = 2;
    static final int G = 3;
    static final int B = 4;
    static final int FAV = 5;
    int sortBy = DEFAULT;
    Cursor cursor = null;


    public ColorSQLiteHelper(Context context) {super(context,NAME,null,VERSION);}
    public void insert(int color) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("color",color);
        sqLiteDatabase.insert("colors",null,contentValues);

    }
    public void insertColors(int r, int g, int b) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("red",r);
        contentValues.put("green",g);
        contentValues.put("blue",b);
        sqLiteDatabase.insert("colors",null,contentValues);

    }
    public void insertFavorites(int fave) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorites",fave);
        sqLiteDatabase.insert("colors",null,contentValues);

    }
    public void selectAll() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM colors";
        if(sortBy == R){
            query += " WHERE red >= 150 AND blue <= 100  AND green <= 100 ORDER BY red DESC";

        }else if(sortBy == G){
            query += " WHERE green >= 150 AND blue <= 100  AND red <= 100 ORDER BY green DESC";
        }else if(sortBy == B){
            query += " WHERE blue >= 150 AND blue <= 100  AND red <= 100 ORDER BY blue DESC";
        }else if(sortBy == FAV){
            query += " ORDER BY favorites DESC";

        }else if(sortBy == DEF){
            query += " ORDER BY favorites DESC";

        }
        cursor = sqLiteDatabase.rawQuery(query,null);
    }

    public int select(int position) {
        if(cursor == null){
            selectAll();
        }
        if (cursor.moveToPosition(position)){
            return cursor.getInt(1);
        }
        return -1;
    }

    public int getSize() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM colors",null);

        return cursor.getCount();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE colors("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "color Integer, "+
                "red Integer, "+
                "green Integer, "+
                "blue Integer,"+
                "favorites boolean);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public int getRed(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("colors", new String[]{"_id","color","red","green","blue"},
                null,null,null,null,"red");
        if(cursor.moveToPosition(position)) {
            int rVal = (int) Math.floor(Math.random() * 256);
            return rVal;
        }
        return 0;
    }
    public int getGreen(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("colors", new String[]{"_id","color","red","green","blue"},
                null,null,null,null,"green");
        if(cursor.moveToPosition(position)) {
            int gVal = (int) Math.floor(Math.random() * 256);
            return gVal;
        }
        return 0;
    }
    public int getBlue(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("colors", new String[]{"_id","color","red","green","blue"},
                null,null,null,null,"blue");
        if(cursor.moveToPosition(position)) {
            int bVal = (int) Math.floor(Math.random() * 256);
            return bVal;
        }
        return 0;
    }
    public int getFav(int position) {
        int fave = position;
        return fave;
    }
    public void sortR() {
        sortBy = R;
        selectAll();
    }

    public void sortG() {
        sortBy = G;
        selectAll();
    }
    public void sortB() {
        sortBy = B;
        selectAll();
    }
    public void sortFav() {
        sortBy = FAV;
        selectAll();
    }

}
