package com.example.cc11_numbersqlitehelper;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColorAdapter extends RecyclerView.Adapter {
    ColorSQLiteHelper numberSQLiteHelper;
    public ColorAdapter(ColorSQLiteHelper numberSQLiteHelper) {
        this.numberSQLiteHelper = numberSQLiteHelper;
    }
    class  NumberViewHolder extends RecyclerView.ViewHolder{

        TextView textViewPalette;
        TextView textViewRGB;
        CheckBox checkBox;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPalette = itemView.findViewById(R.id.textViewPalette);
            textViewRGB = itemView.findViewById(R.id.textViewRGB);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_layout, parent,false);

        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int r = numberSQLiteHelper.getRed(position);
        int g = numberSQLiteHelper.getGreen(position);
        int b = numberSQLiteHelper.getBlue(position);
        int RGB = Color.rgb(r, g, b);
        int fave = numberSQLiteHelper.getFav(position);

        numberSQLiteHelper.insertColors(r,g,b);
        numberSQLiteHelper.insertFavorites(fave);
        NumberViewHolder numberViewHolder = (NumberViewHolder) holder;
        numberViewHolder.textViewPalette.setBackgroundColor(RGB);
        numberViewHolder.textViewRGB.setText(r +" " + " "+ g +" "+ b);

    }

    @Override
    public int getItemCount() {
        return numberSQLiteHelper.getSize();
    }
}
