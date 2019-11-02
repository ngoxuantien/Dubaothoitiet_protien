package com.example.appthitit.Weather;

import android.util.Log;

import com.example.appthitit.Contect;
import com.example.appthitit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Json {
    public Json() {
    }

    public Json(Weather weather) {
        this.weather = weather;
    }

    Contect contect123;
    List<Contect> contectsList = new ArrayList<Contect>();
    trangthaithoitiet thoitiettt = new trangthaithoitiet();
    Weather weather = new Weather();

    public void JSONWEATHER(String weatherjson, Weather weather) throws JSONException, IOException {

        JSONObject jsonObject = new JSONObject(weatherjson);
        String name = jsonObject.getString("name");
        int day = jsonObject.getInt("dt");
        weather.setDiadiem(name);
// nhiệt độn
        JSONObject jsonObject1nhietdo = jsonObject.getJSONObject("main");
        int nhietdo = jsonObject1nhietdo.getInt("temp");
        weather.setNhietdo(nhietdo);
        // nhietdo1.setText(nhietdo + "");
// ngày tháng
        long l = Long.valueOf(day);
        Date date = new Date(l * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
        String Day = simpleDateFormat.format(date);
        String time = simpleDateFormat1.format(date);
        weather.setDayweather(Day);

        weather.setTimeweather(time);
// gio
        JSONObject jsonObject1Wind = jsonObject.getJSONObject("wind");
        double gio2 = jsonObject1Wind.getDouble("speed");
        weather.setTocdogio(gio2);

// thoi tiết
        JSONArray mang = jsonObject.getJSONArray("weather");
        Log.d("JSONARRAY_RESUST", "" + mang.toString());
        int idweather = 0;
        String iconweather="rong";
        for (int i = 0; i < mang.length(); i++) {

            JSONObject cit = mang.getJSONObject(i);
            Log.d("JSONOBJECT_RESULT", "" + cit.toString());

            idweather = cit.getInt("id");
            iconweather=cit.getString("icon");


        }
        int iconthoitiet=0;
        if(iconweather.equals("01d")){
            iconthoitiet=R.drawable.sun;
        }
        if(iconweather.equals("01n")){
            iconthoitiet=R.drawable.moon;
        }
        if(iconweather.equals("02d")){
            iconthoitiet=R.drawable.sun1;
        }
        if(iconweather.equals("02n")){
            iconthoitiet=R.drawable.sunnight;
        }
        if(iconweather.equals("03d")){
            iconthoitiet=R.drawable.cloud;
        }
        if(iconweather.equals("03n")){
            iconthoitiet=R.drawable.cloud;
        }
        if(iconweather.equals("04d")||iconweather.equals("04n")){
            iconthoitiet=R.drawable.clouds;
        }
        if(iconweather.equals("09d")||iconweather.equals("09n")){
            iconthoitiet=R.drawable.rain;
        }
        if(iconweather.equals("10d")){
            iconthoitiet=R.drawable.icon8ddd;
        }
        if(iconweather.equals("10n")){
            iconthoitiet=R.drawable.rain;
        }
        if(iconweather.equals("11d")||iconweather.equals("11n")){
            iconthoitiet=R.drawable.storm;
        }
        if(iconweather.equals("13d")||iconweather.equals("13n")){
            iconthoitiet=R.drawable.snowflake;
        }

        if(iconweather.equals("50d")||iconweather.equals("50n")){

            iconthoitiet=R.drawable.fog;
        }
weather.setIcon(iconthoitiet);
        weather.setTrangthaithoitietweather(thoitiettt.dich(idweather));
        //thoitiet.setText(tt.dich(idweather));


    }


    public void JSONWEATHERoneday(String weatheroneday, Weather weather1, List<Contect> contectsList) throws JSONException {
            JSONObject jsonObject = new JSONObject(weatheroneday);
            JSONArray mang1 = jsonObject.getJSONArray("list");
            Log.d("JSONARRAY_RESUST", "" + mang1.toString());
            contectsList.clear();
        for (int i = 0; i < 8; i++) {//mang1.length()
            JSONObject cit = mang1.getJSONObject(i);
            Log.d("JSONOBJECT_RESULT", "" + cit.toString());
// nhiệt độn, độ ẩm
            JSONObject jsonObject1nhietdo = cit.getJSONObject("main");
            int nhietdo = jsonObject1nhietdo.getInt("temp");
            int doam = jsonObject1nhietdo.getInt("humidity");
            //  String day678=cit.getString("dt");
            // gio
            int day56 = cit.getInt("dt");
            Long l2 = Long.valueOf(day56 - 7 * 60 * 60);
            Date date = new Date((l2 * 1000L));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd-MM-yyyy HH:mm");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
            // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
            String time1 = simpleDateFormat1.format(date);

         //   thoi tiết
            JSONArray weathermini = cit.getJSONArray("weather");
            Log.d("JSONARRAY_RESUST", "" + weathermini.toString());

            String iconweather="rong";
            for (int i2 = 0; i2 < weathermini.length(); i2++) {

                JSONObject cit2 = weathermini.getJSONObject(i2);
                Log.d("JSONOBJECT_RESULT", "" + cit2.toString());

               iconweather=cit2.getString("icon");


            }
            int iconthoitiet=0;
            if(iconweather.equals("01d")){
                iconthoitiet=R.drawable.sun;
            }
            if(iconweather.equals("01n")){
                iconthoitiet=R.drawable.moon;
            }
            if(iconweather.equals("02d")){
                iconthoitiet=R.drawable.sun1;
            }
            if(iconweather.equals("02n")){
                iconthoitiet=R.drawable.sunnight;
            }
            if(iconweather.equals("03d")){
                iconthoitiet=R.drawable.cloud;
            }
            if(iconweather.equals("03n")){
                iconthoitiet=R.drawable.cloud;
            }
            if(iconweather.equals("04d")||iconweather.equals("04n")){
                iconthoitiet=R.drawable.clouds;
            }
            if(iconweather.equals("09d")||iconweather.equals("09n")){
                iconthoitiet=R.drawable.rain;
            }
            if(iconweather.equals("10d")){
                iconthoitiet=R.drawable.icon8ddd;
            }
            if(iconweather.equals("10n")){
                iconthoitiet=R.drawable.rain;
            }
            if(iconweather.equals("11d")||iconweather.equals("11n")){
                iconthoitiet=R.drawable.storm;
            }
            if(iconweather.equals("13d")||iconweather.equals("13n")){
                iconthoitiet=R.drawable.snowflake;
            }

            if(iconweather.equals("50d")||iconweather.equals("50n")){

                iconthoitiet=R.drawable.fog;
            }


            contect123 = new Contect(time1,iconthoitiet, nhietdo, doam);
            contectsList.add(contect123);



        }


    }
    public void JSONWEATHERoneday6time(String weatheroneday, Weather weather1, List<Contect> contectsList) throws JSONException {
        JSONObject jsonObject = new JSONObject(weatheroneday);
        JSONArray mang1 = jsonObject.getJSONArray("list");
        Log.d("JSONARRAY_RESUST", "" + mang1.toString());
        contectsList.clear();
        for (int i = 0; i < mang1.length(); i++) {//





            JSONObject cit = mang1.getJSONObject(i);




            Log.d("JSONOBJECT_RESULT", "" + cit.toString());
// nhiệt độn, độ ẩm
            JSONObject jsonObject1nhietdo = cit.getJSONObject("main");
            int nhietdo = jsonObject1nhietdo.getInt("temp");
            int doam = jsonObject1nhietdo.getInt("humidity");
            //  String day678=cit.getString("dt");


            //   thoi tiết
            JSONArray weathermini = cit.getJSONArray("weather");
            Log.d("JSONARRAY_RESUST", "" + weathermini.toString());

            String iconweather="rong";
            for (int i2 = 0; i2 < weathermini.length(); i2++) {

                JSONObject cit2 = weathermini.getJSONObject(i2);
                Log.d("JSONOBJECT_RESULT", "" + cit2.toString());

                iconweather=cit2.getString("icon");


            }
            int iconthoitiet=0;
            if(iconweather.equals("01d")){
                iconthoitiet=R.drawable.sun;
            }
            if(iconweather.equals("01n")){
                iconthoitiet=R.drawable.moon;
            }
            if(iconweather.equals("02d")){
                iconthoitiet=R.drawable.sun1;
            }
            if(iconweather.equals("02n")){
                iconthoitiet=R.drawable.sunnight;
            }
            if(iconweather.equals("03d")){
                iconthoitiet=R.drawable.cloud;
            }
            if(iconweather.equals("03n")){
                iconthoitiet=R.drawable.cloud;
            }
            if(iconweather.equals("04d")||iconweather.equals("04n")){
                iconthoitiet=R.drawable.clouds;
            }
            if(iconweather.equals("09d")||iconweather.equals("09n")){
                iconthoitiet=R.drawable.rain;
            }
            if(iconweather.equals("10d")){
                iconthoitiet=R.drawable.icon8ddd;
            }
            if(iconweather.equals("10n")){
                iconthoitiet=R.drawable.rain;
            }
            if(iconweather.equals("11d")||iconweather.equals("11n")){
                iconthoitiet=R.drawable.storm;
            }
            if(iconweather.equals("13d")||iconweather.equals("13n")){
                iconthoitiet=R.drawable.snowflake;
            }

            if(iconweather.equals("50d")||iconweather.equals("50n")){

                iconthoitiet=R.drawable.fog;
            }

            // gio

            int day56 = cit.getInt("dt");
            Long l2 = Long.valueOf(day56 - 7 * 60 * 60);
            Date date = new Date((l2 * 1000L));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
            // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
            String time1 = simpleDateFormat1.format(date);
            String time2=simpleDateFormat.format(date);
            if(time1.equals("06:00")){

                contect123 = new Contect(time2,iconthoitiet, nhietdo, doam);
                contectsList.add(contect123);

            }




        }


    }
}

















