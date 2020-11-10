package com.example.gameduoihinhbatchu.api;

import android.os.AsyncTask;

import com.bumptech.glide.RequestBuilder;
import com.example.gameduoihinhbatchu.DATA;
import com.example.gameduoihinhbatchu.object.CauDo;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LayCauHoi extends AsyncTask<Void, Void, Void> {
    String data;

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.1.49/duoihinhbatchu/layCauHoi.php")
                .build();

        Response responce = null;//lay ra du lieu
        try{
            responce = client.newCall(request).execute();
            ResponseBody responseBody = responce.body();
            data = responseBody.string();//gan ket qua cho data
        }catch (IOException e){
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data !=null){
            try {
                DATA.getData().arrCauDo.clear();
                JSONArray array = new JSONArray(data);
                for(int i=0;i<array.length();i++){
                    JSONObject o = array.getJSONObject(i);
                    CauDo c = new CauDo();
                    c.anh = o.getString("anh");
                    c.ten = o.getString("ten");
                    c.dapan = o.getString("dapan");
                    DATA.getData().arrCauDo.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

        }
    }
}
