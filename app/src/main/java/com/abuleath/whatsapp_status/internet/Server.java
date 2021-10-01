package com.abuleath.whatsapp_status.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;


public class Server extends AppCompatActivity {


    Context context;

    public Server(Context context) {
        this.context = context;
    }
    public Server(){}

    public boolean CheckInternetConnection()
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] ni = cm.getAllNetworkInfo();

        for (NetworkInfo info:ni)
        {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if(info.isConnected())
                    return true;
            }
            else if (info.getTypeName().equalsIgnoreCase("MOBILE"))
            {
                if(info.isConnected())
                    return true;
            }
        }

        return false;
    }

}