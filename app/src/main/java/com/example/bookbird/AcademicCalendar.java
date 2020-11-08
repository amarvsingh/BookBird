package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class AcademicCalendar extends AppCompatActivity {

    //To declare Buttons and Fields
    Button image, pdf;

    //To delclare Database storage reference
    StorageReference referenceDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);

        //To map with xml file
        image = (Button)findViewById(R.id.calendarimage);
        pdf = (Button)findViewById(R.id.calendarpdfdownload);

        //To open Image Activity
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i24 = new Intent(AcademicCalendar.this,CalendarImage.class);
                startActivity(i24);
            }
        });

        //To download the Academic Calendar as pdf
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadpdf();
            }
        });
    }


    public void downloadpdf(){
        referenceDownload = FirebaseStorage.getInstance().getReference("Academic Calendar").child("Academic Calendar 19-20.pdf");
        referenceDownload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadManager(AcademicCalendar.this, "Academic Calendar 19-20", ".pdf", DIRECTORY_DOWNLOADS,url);
                Toast.makeText(getApplicationContext(),"The requested file will be downloaded on your device", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Download unsuccessful, please check you connectivity", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void downloadManager(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request requestDownload = new DownloadManager.Request(uri);
        requestDownload.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        requestDownload.setDestinationInExternalFilesDir(context,destinationDirectory,fileName+fileExtension);
        downloader.enqueue(requestDownload);
    }
}
