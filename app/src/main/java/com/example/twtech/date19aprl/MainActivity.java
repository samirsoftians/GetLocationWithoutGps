package com.example.twtech.date19aprl;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    GsmCellLocation location;
    int cellID, lac, mcc, mnc;


    //**********This is the the formate of Api to send data**********

    String url = "https://api.mylnikov.org/geolocation/cell?v=1.1&data=open&mcc=405&mnc=864&lac=135&cellid=1302545";


    //**********************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();

        if (!TextUtils.isEmpty(networkOperator)) {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
            mnc = Integer.parseInt(networkOperator.substring(3));


            String mc = String.valueOf(mcc);
            String mcn = String.valueOf(mnc);



        }


        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        location = (GsmCellLocation) tm.getCellLocation();
        cellID = location.getCid();
        lac = location.getLac();

        lac = location.getCid();
        cellID = location.getLac();

        String la = String.valueOf(lac);
        String cellI = String.valueOf(cellID);




        loadData();

    }


    public void loadData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://api.mylnikov.org/geolocation/cell?v=1.1&data=open&mcc=" + mcc + "&mnc=" + mnc + "&lac=" + cellID + "&cellid=" + lac,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONObject jsonObject = response.getJSONObject("data");
                            String Lat = jsonObject.getString("lat");
                            String Long = jsonObject.getString("lon");
                            String dateInMillis = jsonObject.getString("time");
                            Long d = java.lang.Long.valueOf(dateInMillis);




                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(d);


                            Toast.makeText(getApplicationContext(), "Lat and Long-" + Lat + " " + Long, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }


    // Create a DateFormatter object for displaying date in specified format.
    private String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public static String getFormattedDateFromTimestamp(long timestampInMilliSeconds)
    {
        Date date = new Date();
        date.setTime(timestampInMilliSeconds);
        String formattedDate=new SimpleDateFormat("MMM d, yyyy").format(date);
        return formattedDate;

    }
}

