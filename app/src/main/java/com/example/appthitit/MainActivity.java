package com.example.appthitit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2;
    RecyclerView recyclerView;
    AdapterContact adapterContact;
    EditText editText;
    LinearLayout linearLayout, baonhapvao;
    TextView textView, nhietdo1, day123, diadanh, gio, thoitiet;

    List<Contect> contacts;
    trangthaithoitiet tt = new trangthaithoitiet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.bieudo);
        baonhapvao = findViewById(R.id.baonhapvao);
        linearLayout = findViewById(R.id.backgrow);
        imageView2 = findViewById(R.id.search);
        editText = findViewById(R.id.nhapvao);
        textView = findViewById(R.id.timeofday);
        diadanh = findViewById(R.id.tendiadanh);
        nhietdo1 = findViewById(R.id.nhietdo);
        day123 = findViewById(R.id.day);
        thoitiet = findViewById(R.id.thoitiet);
        gio = findViewById(R.id.gio);
        imageView.setVisibility(View.GONE);
        baonhapvao.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerview);
GetCurrentWeatherData("Hanoi");
  imageView2.setInputType(InputType.TYPE_NULL);
        if (Build.VERSION.SDK_INT >= 11) {
            imageView2.setRawInputType(InputType.TYPE_CLASS_TEXT);
            imageView2.setTextIsSelectable(true);
        }
        imageView2.setOnClickListener(new View.OnClickListener() {
            int kcl=0;
            @Override
            public void onClick(View v) {

                String dklm = editText.getText().toString();
                if(dklm.matches("^[a-z A-Z]{1,50}$"))
                {
                   kcl=1;

                }
                else
                {
                    kcl=2;

                }
                if(kcl==1){
                    GetCurrentWeatherData(dklm+",VN");
                }else
                    GetCurrentWeatherData(dklm);


                baonhapvao.setVisibility(View.VISIBLE);

            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baonhapvao.setVisibility(View.GONE);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });


        contacts = new ArrayList<>();
        Contect contact1 = new Contect("12:6", R.drawable.weather, 6);
        Contect contact2 = new Contect("12:6", R.drawable.weather, 6);
        Contect contact3 = new Contect("12:6", R.drawable.weather, 6);
        Contect contact4 = new Contect("12:6", R.drawable.weather, 6);
        Contect contact5 = new Contect("12:6", R.drawable.weather, 6);
        Contect contact6 = new Contect("12:6", R.drawable.weather, 6);


        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 1, RecyclerView.HORIZONTAL, false);

        adapterContact = new AdapterContact(contacts);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterContact);
    }

    public void GetCurrentWeatherData(final String data) {

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" +data + "&units=metric&appid=3feaccd9934ba604e5ddbed0bc176756";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String name = jsonObject.getString("name");
                            int day = jsonObject.getInt("dt");
                            diadanh.setText(name);
// nhiệt độ
                            JSONObject jsonObject1nhietdo = jsonObject.getJSONObject("main");
                            int nhietdo = jsonObject1nhietdo.getInt("temp");
                            nhietdo1.setText(nhietdo + "");
// ngày tháng
                            long l = Long.valueOf(day);
                            Date date = new Date(l * 1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
                            // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String Day = simpleDateFormat.format(date);
                            String time = simpleDateFormat1.format(date);
                            day123.setText(Day);
                            textView.setText(time);
// gio
                            JSONObject jsonObject1Wind = jsonObject.getJSONObject("wind");
                            String gio2 = jsonObject1Wind.getString("speed");
                            gio.setText(gio2 + "m/s");
// thoi tiết
                            JSONArray mang = jsonObject.getJSONArray("weather");
                            Log.d("JSONARRAY_RESUST", "" + mang.toString());
                            int idweather = 0;
                            for (int i = 0; i < mang.length(); i++) {

                                JSONObject cit = mang.getJSONObject(i);
                                Log.d("JSONOBJECT_RESULT", "" + cit.toString());

                                idweather = cit.getInt("id");


                            }
                            thoitiet.setText(tt.dich(idweather));


//                            JSONObject main = null;
//
//
//                            //   diadanh.setText(tendiadanh);
//                            String temp = main.getString("temp");
                            //  diadanh.setText(temp);
//
//
//                            JSONArray jsonArrayWeather=jsonObject.getJSONArray("weather");
//                            JSONObject jsonObjectWeather=jsonArrayWeather.getJSONObject(0);
//                            String status=jsonObjectWeather.getString("main");
//                            String icon=jsonObjectWeather.getString("icon");
//
//                            JSONObject jsonObjectMain=jsonObject.getJSONObject("main");
//                            String nhietdo=jsonObjectMain.getString("temp");
//                            String doam=jsonObjectMain.getString("humidity");
//
//
//                            Double a = Double.valueOf(nhietdo);
//                            String Nhietdo=String.valueOf(a.intValue());
//
//
//                            nhietdo1.setText(Nhietdo+"c");
//                          //  txtHumindity.setText(doam+"%");
//

//
//                            JSONObject jsonObjectClouds=jsonObject.getJSONObject("clouds");
//                            String may=jsonObjectClouds.getString("all");
//                          //  txtCloud.setText(may+"%");
//
//                            JSONObject jsonObjectSys=jsonObject.getJSONObject("sys");
//                            String country=jsonObjectSys.getString("conuntry");
//                          //  txtCoutry.setText("Tên quốc gia:"+country);
//
//
//                        day123.setText(Day);
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi\n" + error.toString());
                    }
                });
        requestQueue.add(stringRequest);
    }


}
