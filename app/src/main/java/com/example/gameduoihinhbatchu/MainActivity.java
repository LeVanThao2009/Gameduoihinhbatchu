package com.example.gameduoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gameduoihinhbatchu.api.LayCauHoi;

public class MainActivity extends AppCompatActivity {
 Button btnchoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LayCauHoi().execute();
        btnchoi = (Button)findViewById(R.id.btnchoi);
    }
    public void Nhan1(View view){
       if (DATA.getData().arrCauDo.size()>0){
           startActivity(new Intent(this,ChoiGameActivity.class));
       }
    }
}
