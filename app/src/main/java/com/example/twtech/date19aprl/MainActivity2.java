package com.example.twtech.date19aprl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {
//http://192.168.2.124:9090/FWebservice/rest/fuelinfo?spveh=MH2009&dat=23-07-2018_16:07:38&spdrivername="+name+"&odom="+spVeh+"&loginuser=shiva@gmail.com&imgdata=hhjhhj&Litres="+fuelLtrs+"&amount="+str_Expense+"&FullTank="+fulltank+"&format=json"

   // String url2="http://samirmishra159.000webhostapp.com/clientphone.php?email=";

    String name="samir", spVeh="abc",fuelLtrs="230",str_Expense="600",fulltank="full";

    String url2="http://192.168.43.166:9090/FWebservice/rest/fuelinfo?spveh=MH2009&dat=23-07-2018_16:07:38&spdrivername="+name+"&odom="+spVeh+"&loginuser=shiva@gmail.com&imgdata=hhjhhj&Litres="+fuelLtrs+"&amount="+str_Expense+"&FullTank="+fulltank+"&format=json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StringRequest stringRequest = new StringRequest(url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
//                showJSON(response);
//                myPd_ring.dismiss();
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (error instanceof NetworkError)
                        {
                            Toast.makeText(MainActivity2.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ServerError)
                        {
                            Toast.makeText(MainActivity2.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof AuthFailureError)
                        {
                            Toast.makeText(MainActivity2.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ParseError)
                        {
                            Toast.makeText(MainActivity2.this,"Parsing error! Please try again after some time !!",Toast.LENGTH_LONG ).show();

                        }
                        else if (error instanceof NoConnectionError)
                        {
                            Toast.makeText(MainActivity2.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof TimeoutError)
                        {
                            Toast.makeText(MainActivity2.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }




                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

   /* public void start(View v) {
        if (isMyServiceRunning(MyService.class)) return;
        Intent startIntent = new Intent(this, MyService.class);
        startIntent.setAction("start");
        startService(startIntent);
        Toast.makeText(this, "SERVICE RUNNING", Toast.LENGTH_SHORT).show();
    }

    public void stop(View v) {
        if (!isMyServiceRunning(MyService.class)) return;
        Intent stopIntent = new Intent(this, MyService.class);
        stopIntent.setAction("stop");
        startService(stopIntent);
        Toast.makeText(this, "SERVICE STOPPED", Toast.LENGTH_SHORT).show();

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }*/
}
