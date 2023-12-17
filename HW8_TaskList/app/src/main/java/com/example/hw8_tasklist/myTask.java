package com.example.hw8_tasklist;

import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.Random;

public class myTask {
    public  String name;
    public String desc;
    public String priorty;


    public myTask(String name, String desc, String priorty) {
        this.name = name;
        this.desc = desc;
        this.priorty = priorty;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){ return desc;}
    public String getPriority(){ return priorty; }
    @NonNull
    public String toString(){return this.name + this.desc + this.priorty;}


}
