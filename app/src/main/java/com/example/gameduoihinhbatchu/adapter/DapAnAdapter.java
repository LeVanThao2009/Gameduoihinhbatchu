package com.example.gameduoihinhbatchu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gameduoihinhbatchu.R;

import java.util.ArrayList;
import java.util.List;

public class DapAnAdapter extends ArrayAdapter<String> {
    private Context myCT;
    private ArrayList<String> arr;
    public DapAnAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.myCT = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)myCT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cau_tra_loi, null);
        }
        TextView txtCauTraLoi = convertView.findViewById(R.id.txtCauTraLoi);
        txtCauTraLoi.setText(this.arr.get(position));
        return convertView;
    }
}
