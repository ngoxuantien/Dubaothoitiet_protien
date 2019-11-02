package com.example.appthitit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.appthitit.Weather.Json;
import com.example.appthitit.Weather.Weather;
import com.example.appthitit.Weather.trangthaithoitiet;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    ImageView imageView, imageView2, quatgio,iconweather;
    RecyclerView recyclerView,recyclerView6time;
    AdapterContact adapterContact;
    AdapterContect6time adapterContect6time;
    EditText editText;
    LinearLayout linearLayout, baonhapvao;
    TextView textView, nhietdo1, day123, diadanh, gio, thoitiet;

    List<Contect> contacts = new ArrayList<Contect>();
    List<Contect> contacts6time = new ArrayList<Contect>();

    trangthaithoitiet tt = new trangthaithoitiet();
    CombinedChart mChart;
    Json JSON = new Json();
    Weather weathertt = new Weather();
    Weather weathertt1 = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iconweather=findViewById(R.id.iconweather);

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
        baonhapvao.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView6time=findViewById(R.id.recyclerview6time);

        GetCurrentWeatherData("Hanoi");
        GetCurrentWeatherData2("Hanoi");
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bong);
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.quay);
        imageView2.setOnClickListener(new View.OnClickListener() {
            int kcl = 0;
            int l = 0;

            @Override
            public void onClick(View v) {

                String dklm = editText.getText().toString();
                if (dklm.matches("^[a-z A-Z]{1,50}\\W\\S+$")) {
                    kcl = 1;
                } else {
                    kcl = 2;
                }
                if (kcl == 1) {
                    GetCurrentWeatherData(dklm);
                    GetCurrentWeatherData2(dklm + ",VN");
                } else
                    GetCurrentWeatherData(dklm);
                GetCurrentWeatherData2(dklm);
                l++;
                baonhapvao.setVisibility(View.VISIBLE);
                if (l == 1)
                    baonhapvao.startAnimation(animation);

            }


        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baonhapvao.setVisibility(View.GONE);
            }
        });







    }


    public void GetCurrentWeatherData(final String data) {

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=3feaccd9934ba604e5ddbed0bc176756";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSON.JSONWEATHER(response, weathertt);
                            diadanh.setText(weathertt.getDiadiem());
                            nhietdo1.setText(weathertt.getNhietdo() + "");
                            textView.setText(weathertt.getTimeweather());
                            day123.setText(weathertt.getDayweather());
                            gio.setText(weathertt.getTocdogio() + "m/s");
                            thoitiet.setText(weathertt.getTrangthaithoitietweather());
                            iconweather.setImageResource(weathertt.getIcon());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
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

    public void GetCurrentWeatherData2(final String data) {

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + data + "&units=metric&appid=3feaccd9934ba604e5ddbed0bc176756";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSON.JSONWEATHERoneday(response, weathertt1, contacts);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 1, RecyclerView.HORIZONTAL, false);
                            adapterContact = new AdapterContact(contacts);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapterContact);

                            JSON.JSONWEATHERoneday6time(response,weathertt1,contacts6time);
                            RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getBaseContext(), 1, RecyclerView.HORIZONTAL, false);

                            adapterContect6time = new AdapterContect6time(contacts6time);
                            recyclerView6time.setLayoutManager(layoutManager2);
                            recyclerView6time.setAdapter(adapterContect6time);














                            mChart = (CombinedChart) findViewById(R.id.combinedChart);
                            mChart.getDescription().setEnabled(false);
                            //   mChart.setBackgroundColor(R.color.colorAccent1);
                            mChart.setDrawGridBackground(false);
                            mChart.setDrawBarShadow(false);
                            mChart.setHighlightFullBarEnabled(false);
                            mChart.setOnChartValueSelectedListener(MainActivity.this);
                            YAxis rightAxis = mChart.getAxisRight();
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMinimum(0f);
                            YAxis leftAxis = mChart.getAxisLeft();
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMinimum(0f);
                            final List<String> xLabel = new ArrayList<String>();
                            xLabel.add(contacts.get(0).getTime()+"");
                            xLabel.add(contacts.get(1).getTime()+"");
                            xLabel.add(contacts.get(2).getTime()+"");
                            xLabel.add(contacts.get(3).getTime()+"");
                            xLabel.add(contacts.get(4).getTime()+"");
                            xLabel.add(contacts.get(5).getTime()+"");
                            xLabel.add(contacts.get(6).getTime()+"");
                            xLabel.add(contacts.get(7).getTime()+"");

                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setAxisMinimum(0f);
                            xAxis.setGranularity(1f);
                            leftAxis.setTextColor(Color.WHITE);//xét màu thanh bên trái
                            rightAxis.setTextColor(Color.WHITE);//xét màu thanh bên phải
                            xAxis.setValueFormatter(new IAxisValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    return xLabel.get((int) value % xLabel.size());
                                }
                            });
                            CombinedData data = new CombinedData();
                            LineData lineDatas = new LineData();
                            lineDatas.addDataSet((ILineDataSet) dataChart());
                            lineDatas.addDataSet((ILineDataSet) datachar2());

                            data.setData(lineDatas);
                            xAxis.setAxisMaximum(data.getXMax() + 0.35f);
                            xAxis.setTextColor(Color.WHITE);// xét màu chữ
                            xAxis.setGridColor(Color.WHITE);//xét màu dòng kẻ
                            mChart.setData(data);
                            mChart.invalidate();
dataChart();
datachar2();

                        } catch (JSONException e) {
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


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

   public  DataSet dataChart() {


        int[] data=new int[]{contacts.get(0).getNhietdo(),contacts.get(1).getNhietdo(),contacts.get(2).getNhietdo(),contacts.get(3).getNhietdo(),contacts.get(4).getNhietdo(),contacts.get(5).getNhietdo(),contacts.get(6).getNhietdo(),contacts.get(7).getNhietdo()};
        LineData d = new LineData();


        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < 8; index++) {
            entries.add(new Entry(index, data[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Nhiệt Độ");
        set.getValueTextColor(Color.WHITE);
        set.setColor(Color.GREEN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.GREEN);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return set;



    }
    public DataSet datachar2(){
        int[] data2a=new int[]{contacts.get(0).getDoam(),contacts.get(1).getDoam(),contacts.get(2).getDoam(),contacts.get(3).getDoam(),contacts.get(4).getDoam(),contacts.get(5).getDoam(),contacts.get(6).getDoam(),contacts.get(7).getDoam()};
        LineData d2 = new LineData();


        ArrayList<Entry> entries2 = new ArrayList<Entry>();

        for (int index = 0; index < 8; index++) {
            entries2.add(new Entry(index, data2a[index]));
        }

        LineDataSet set2 = new LineDataSet(entries2, "Độ Âm");
        set2.getValueTextColor(Color.WHITE);
        set2.setColor(Color.YELLOW);
        set2.setLineWidth(2.5f);
        set2.setCircleColor(Color.YELLOW);
        set2.setCircleRadius(5f);
        set2.setFillColor(Color.YELLOW);
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set2.setDrawValues(true);
        set2.setValueTextSize(10f);
        set2.setValueTextColor(Color.YELLOW);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        d2.addDataSet(set2);

        return set2;
    }
}
