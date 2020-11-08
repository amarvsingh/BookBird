package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth AuthHome;
    FirebaseUser fUser;
    private StorageReference referenceDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(getApplicationContext(),"Already logged in? Click on Log in.",Toast.LENGTH_SHORT).show();

        TextView b1 = (TextView)findViewById(R.id.signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(HomeActivity.this, Signup2.class);
                startActivity(i1);
                finish();
            }
        });
        CardView b2 = (CardView)findViewById(R.id.login);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(HomeActivity.this, Login.class);
                startActivity(i2);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //To download User Guide
        if (id == R.id.userguide){
            downloadUserGuide();
        }
        //To go to feedback form
        if (id == R.id.feeback){
           //Check if the user has logged in
          AuthHome = FirebaseAuth.getInstance();
          fUser = AuthHome.getCurrentUser();
          if (fUser != null){
                // Code to enter the Feedback activity
              Intent i51 = new Intent(HomeActivity.this,Feedback.class);
              startActivity(i51);
          }else{
              Toast.makeText(getApplicationContext(),"Please log in to give the feedback",Toast.LENGTH_SHORT).show();
          }
        }
        return super.onOptionsItemSelected(item);
    }

    public void downloadUserGuide(){
        referenceDownload = FirebaseStorage.getInstance().getReference("User Guide").child("User Guide.pdf");
        referenceDownload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadManager(HomeActivity.this, "User Guide", ".pdf", DIRECTORY_DOWNLOADS,url);
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
