package com.hnq40.firebaseexam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Demo7MainActivity2 extends AppCompatActivity {
    EditText txt1,txt2,txt3;
    Button btnSelect, btn2;
    TextView tvKQ;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo7_main2);
        txt1=findViewById(R.id.demo71Txt1);
        txt2=findViewById(R.id.demo71Txt2);
        txt3=findViewById(R.id.demo71Txt3);
        btn2=findViewById(R.id.demo71Btn2);
        btnSelect=findViewById(R.id.demo71BtnSelect);
        tvKQ=findViewById(R.id.demo71TvKQ);
        btnSelect.setOnClickListener(v->{
            selectVolley();
        });
        btn2.setOnClickListener(v->{

        });
    }
    String strKQ="";
    private void selectVolley(){
        strKQ="";
        //1. tao request
        RequestQueue queue= Volley.newRequestQueue(context);
        //2.url:
        String url="https://hungnq28.000webhostapp.com/su2024/select.php";
        //3. goi request: JsonArrayRequest request=new JsonArrayRequest(url,thanhcong,thatbai);
        //lay ve object {
        JsonObjectRequest request1=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //lay ve array:  "products": [
                    JSONArray jsonArray=response.getJSONArray("products");
                    //for array
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject p=jsonArray.getJSONObject(i);//lay ve doi tuong i
                        String MaSP=p.getString("MaSP");
                        String TenSP=p.getString("TenSP");
                        String MoTa=p.getString("MoTa");
                        strKQ+="MaSP: "+MaSP+"; TenSP:"+TenSP+"; MoTa: "+MoTa+"\n";
                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        //4. thuc thi request
        queue.add(request1);

//        JsonArrayRequest request=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for(int i=0;i<response.length();i++){
//                    try {
//                        JSONObject p=response.getJSONObject(i);//lay ve doi tuong i
//                        String MaSP=p.getString("MaSP");
//                        String TenSP=p.getString("TenSP");
//                        String MoTa=p.getString("MoTa");
//                        strKQ+="MaSP: "+MaSP+"; TenSP:"+TenSP+"; MoTa: "+MoTa+"\n";
//
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                tvKQ.setText(strKQ);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                tvKQ.setText(error.getMessage());
//            }
//        });
//        //4. thuc thi request
//        queue.add(request);
    }
}