package com.example.hw12_xkcd_comics;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_COMICID = "comicId";

    WebView webViewCartoon;
    TextView numberView;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_comic_layout);

        //Get the comic from the intent
        int comicId = (Integer)getIntent().getExtras().get(EXTRA_COMICID);

        try {
            SQLiteOpenHelper comicSQLiteHelper = new ComicSQLiteHelper(this);
            SQLiteDatabase db = comicSQLiteHelper.getReadableDatabase();
            Cursor cursor = db.query("comics",
                    new String[]{"number", "title", "cartoon"},
                    "_id = ?",
                    new String[]{Integer.toString(comicId)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                //Get comic details from the cursor
                int numberText = cursor.getInt(0);
                String titleText = cursor.getString(1);
                String cartoonText = cursor.getString(2);

                numberView = findViewById((R.id.numberView));
                titleView = findViewById((R.id.titleView));
                webViewCartoon = findViewById((R.id.webViewCartoon));

                numberView.setText(Integer.toString(numberText));
                titleView.setText(titleText);
                webViewCartoon.loadUrl(cartoonText);

            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "DATABASE UNAVAILABLE :(", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
