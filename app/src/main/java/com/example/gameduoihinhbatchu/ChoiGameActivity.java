package com.example.gameduoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gameduoihinhbatchu.Model.ChoiGameModel;
import com.example.gameduoihinhbatchu.adapter.DapAnAdapter;
import com.example.gameduoihinhbatchu.object.CauDo;
import com.example.gameduoihinhbatchu.object.NguoiDung;

import java.util.ArrayList;
import java.util.Random;

public class ChoiGameActivity extends AppCompatActivity {

    ChoiGameModel model;
    CauDo cauDo;
    private String dapan = "baocao";

    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraloi;
    int index = 0;

    ArrayList<String> arrDapAn;
    GridView gdvDapAn;

    ImageView imageAnhCauDo;
    TextView txtTenNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        init();
        anhxa();
        setOnClick();
        hienCauDo();
    }

    private void anhxa() {
        gdvCauTraloi = (GridView) findViewById(R.id.gdvCauTraloi);
        gdvDapAn = (GridView) findViewById(R.id.gdvDapAn);
        imageAnhCauDo = (ImageView) findViewById(R.id.imgAnhCauDo);
        txtTenNguoiDung = (TextView) findViewById(R.id.txtTienNguoiDung);
    }

    private void init() {
        model = new ChoiGameModel(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }

    private void hienCauDo() {
        cauDo = model.layCauDo();
        dapan = cauDo.dapan;

        Database();
        hienthiCauTraLoi();
        hienthiDapAn();
        Glide.with(this)
                .load(cauDo.anh)
                .into(imageAnhCauDo);
        model.LayThongTin();
        txtTenNguoiDung.setText(model.nguoiDung.tien + "$");
    }

    private void hienthiCauTraLoi() {
        gdvCauTraloi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraloi.setAdapter(new DapAnAdapter(this, 0, arrCauTraLoi));
    }

    private void hienthiDapAn() {
        gdvDapAn.setNumColumns(arrDapAn.size() / 2);
        gdvDapAn.setAdapter(new DapAnAdapter(this, 0, arrDapAn));
    }

    private void setOnClick() {

        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0 && index < arrCauTraLoi.size()) {
                    for (int i = 0; i < arrCauTraLoi.size(); i++) {
                        if (arrCauTraLoi.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    arrDapAn.set(position, "");
                    arrCauTraLoi.set(index, s);
                    index++;
                    hienthiCauTraLoi();
                    hienthiDapAn();
                    checkWin();
                }
            }
        });
        gdvCauTraloi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;
                    arrCauTraLoi.set(position, "");
                    for (int i = 0; i < arrDapAn.size(); i++) {
                        if (arrDapAn.get(i).length() == 0) {
                            arrDapAn.set(i, s);
                            break;
                        }
                    }
                    hienthiDapAn();
                    hienthiCauTraLoi();
                }
            }
        });
    }

    private void Database() {
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();
        for (int i = 0; i < dapan.length(); i++) {
            arrCauTraLoi.add("");
            String s = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s1);
        }
        for (int i = 0; i < dapan.length(); i++) {
            String s = "" + dapan.charAt(i);
            arrDapAn.set(i, s.toUpperCase());
        }
        for (int i = 0; i < arrDapAn.size(); i++) {
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i, arrDapAn.get(vt));
            arrDapAn.set(vt, s);
        }
    }

    private void checkWin() {
        String s = "";
        for (String s1 : arrCauTraLoi) {
            s = s + s1;
        }
        s = s.toUpperCase();
        if (s.equals(dapan.toUpperCase())) {
            model.LayThongTin();
            model.nguoiDung.tien = model.nguoiDung.tien + 10;
            model.luuthongtin();
            Toast.makeText(this, "Bạn Đã Trả Lời Đúng", Toast.LENGTH_SHORT).show();
            hienCauDo();
        }

    }

    public void goiy(View view) {
        model.LayThongTin();
        if (model.nguoiDung.tien < 5) {
            Toast.makeText(this, "Bạn không đủ tiền để dùng gợi ý", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = -1;
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (arrCauTraLoi.get(i).length() == 0) {
                id = i;
                break;
            }
        }
        if (id == -1) {
            for (int i = 0; i < arrCauTraLoi.size(); i++) {
                String s = dapan.toUpperCase().charAt(i) + "";
                if (arrCauTraLoi.get(i).toUpperCase().equals(s)) {
                    id = -1;
                    break;
                }
            }
            for (int i = 0; i < arrDapAn.size(); i++) {
                if (arrDapAn.get(i).length() == 0) {
                    arrDapAn.set(i, arrCauTraLoi.get(i));
                    break;
                }
            }
        }
        String goiy = "" + dapan.charAt(id);
        goiy = goiy.toUpperCase();
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (arrCauTraLoi.get(i).toUpperCase().equals(goiy)) {
                arrCauTraLoi.set(i, "");
                break;
            }
        }
        for (int i = 0; i < arrDapAn.size(); i++) {
            if (goiy.equals(arrDapAn.get(i))) {
                arrDapAn.set(i, "");
                break;
            }
        }
        arrCauTraLoi.set(id, goiy);
        hienthiDapAn();
        hienthiCauTraLoi();
        model.LayThongTin();
        model.nguoiDung.tien = model.nguoiDung.tien - 5;
        model.luuthongtin();
        txtTenNguoiDung.setText(model.nguoiDung.tien + "$");
    }

    public void doicauhoi(View view) {
        model.LayThongTin();
        if (model.nguoiDung.tien < 5) {
            Toast.makeText(this, "Bạn không đủ tiền để dùng để đổi câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        }
        hienCauDo();
    }
}
