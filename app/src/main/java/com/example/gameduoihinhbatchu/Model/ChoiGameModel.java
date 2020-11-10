package com.example.gameduoihinhbatchu.Model;

import com.example.gameduoihinhbatchu.ChoiGameActivity;
import com.example.gameduoihinhbatchu.DATA;
import com.example.gameduoihinhbatchu.object.CauDo;
import com.example.gameduoihinhbatchu.object.NguoiDung;

import java.util.ArrayList;

public class ChoiGameModel {
    ChoiGameActivity c;
    ArrayList<CauDo> arr;
    int causo=-1;
    public NguoiDung nguoiDung;
    public ChoiGameModel(ChoiGameActivity c){
        this.c=c;
        nguoiDung = new NguoiDung();
        TaoData();
    }
    private void TaoData(){
        arr = new ArrayList<>(DATA.getData().arrCauDo);
    }
    public CauDo layCauDo(){
       causo++;
       if (causo>=arr.size()){
           causo=arr.size()-1;
       }
       return arr.get(causo);
    }

    public void LayThongTin(){
        nguoiDung.getTT(c);
    }

    public void luuthongtin(){
       nguoiDung.saveTT(c);
    }
}
