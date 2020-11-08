package com.example.bookbird;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class ProgressBar   {
    private Activity activity;
    private AlertDialog dialog;

    public ProgressBar(Activity myActivity) {
        activity = myActivity;
    }

     void startProgressBar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
         LayoutInflater inflater = activity.getLayoutInflater();
         builder.setView(inflater.inflate(R.layout.progress_bar1,null));
         builder.setCancelable(false);
         dialog = builder.create();
         dialog.show();
     }

     void dismissProgresBar(){
        dialog.dismiss();
     }
}
