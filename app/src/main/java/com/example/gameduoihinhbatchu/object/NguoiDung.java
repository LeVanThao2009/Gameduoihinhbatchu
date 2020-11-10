package com.example.gameduoihinhbatchu.object;

import android.content.Context;
import android.content.SharedPreferences;

public class NguoiDung {
    public int tien;
    public String nameData = "appData";

    public void saveTT(Context ct) {
        SharedPreferences settings = ct.getSharedPreferences("nameData", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("tien", tien);
        editor.commit();
        }
        public void getTT(Context ct){
        SharedPreferences settings = ct.getSharedPreferences("nameData", 0);
        tien = settings.getInt("tien",0);
        }
}
