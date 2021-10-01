package com.abuleath.whatsapp_status;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abuleath.whatsapp_status.Model.DBHelper;
import com.abuleath.whatsapp_status.internet.Server;
import com.abuleath.whatsapp_status.internet.URLs;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Splash_Activity extends AppCompatActivity {

    Server server = new Server(this);
    DBHelper mydb;
    ArrayList<Status> status = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        mydb = new DBHelper(this);

        if(server.CheckInternetConnection())
        {
            getData();
        }
        else
            Toast.makeText(this, "إتصل بالانترنت للتاكد من وجود حالات جديدة", Toast.LENGTH_LONG).show();

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent m = new Intent(Splash_Activity.this , MainActivity.class);
                startActivity(m);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    public void getData()
    {
        RequestQueue queue = Volley.newRequestQueue(Splash_Activity.this);
        int lastId = ((GlobalVariable) this.getApplication()).getLastStatusId();
        String lastStatusId = Integer.toString(lastId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.ALL_STATUS_URL+"/"+lastStatusId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("statuses");
                    JSONArray statusArray = object.getJSONArray("data");
                    for(int i = 0 ; i < statusArray.length() ; i++)
                    {
                        JSONObject statusObj = statusArray.getJSONObject(i);
                        Status s = new Status();
                        s.setStatusId(statusObj.getInt("id"));
                        s.setCategoryId(statusObj.getInt("category_id"));
                        s.setStatusText(statusObj.getString("body"));
                        s.setCreated_at("1");
                        status.add(s);
                    }

                    mydb.insertStatusFromService(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonObjectRequest);
    }
}
