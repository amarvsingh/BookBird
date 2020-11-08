package com.example.bookbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class NoInternet extends AppCompatActivity {

    //To Declare Connectivity Manager
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

            /*checkInternet = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            checkActiveInternet = checkInternet.getActiveNetworkInfo();
            if (checkActiveInternet != null){
                finish();
            }else{
                //
            }*/
    }
}
