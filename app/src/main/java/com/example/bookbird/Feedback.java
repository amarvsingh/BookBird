package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.DefaultTaskExecutor;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.microedition.khronos.egl.EGLDisplay;

public class Feedback extends AppCompatActivity {

    //To declare fields and Buttons
    EditText experience,changes;
    RatingBar ratingBar;
    Button submit;

    //To declare database references
    FirebaseAuth AuthFeedback;
    FirebaseUser userFeedback;
    private DatabaseReference referenceOwnUsername;
    private DatabaseReference referenceFeedback;

    //To declare the strings required
    public String OwnUid,OwnUsername,Experience,Changes,Rating;
    private Boolean error1,error2,error3;

    //To declare the object of getter setter class
    FeedbackGetterSetter objFeedback;

    //To Declare Connectivity Managers
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //To get Current Username;
        AuthFeedback = FirebaseAuth.getInstance();
        userFeedback = AuthFeedback.getCurrentUser();
        OwnUid = userFeedback.getUid();
        referenceOwnUsername = FirebaseDatabase.getInstance().getReference("Users").child(OwnUid);
        referenceOwnUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OwnUsername = dataSnapshot.child("username").getValue().toString().trim();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });

        //To map fields with the xml file
        experience = (EditText)findViewById(R.id.feedbackexperience);
        changes = (EditText)findViewById(R.id.feedbackchanges);
        ratingBar = (RatingBar)findViewById(R.id.feedbackrating);
        submit = (Button)findViewById(R.id.feedbacksubmission);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error1 = true;
                error2 = true;
                error3 = true;
                Experience = experience.getText().toString().trim();
                Changes = changes.getText().toString().trim();
                Rating = String.valueOf(ratingBar.getRating());
                if (Experience.isEmpty()){
                    error1 = true;
                    Toast.makeText(getApplicationContext(),"Please Fill all the Fields",Toast.LENGTH_SHORT).show();
                }else{
                    error1 = false;
                }
                if (Changes.isEmpty()){
                    error2 = true;
                    Toast.makeText(getApplicationContext(),"Please Fill all the Fields",Toast.LENGTH_SHORT).show();
                }else{
                    error2 = false;
                }
                if (Rating.isEmpty()){
                    error3 = true;
                    Toast.makeText(getApplicationContext(),"Please rate the App",Toast.LENGTH_SHORT).show();
                }else{
                    error3 = false;
                }
                if (error1 == false && error2 == false && error3 == false){
                    checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    checkActiveInternet = checkInternet.getActiveNetworkInfo();
                    if (checkActiveInternet == null){
                        Intent i52 = new Intent(Feedback.this,NoInternet.class);
                        startActivity(i52);
                    }
                    else{
                        sendFeedback();
                    }
                }
            }
        });
    }


    public void sendFeedback(){
        referenceFeedback = FirebaseDatabase.getInstance().getReference("Feedbacks").child(OwnUsername);
        objFeedback = new FeedbackGetterSetter();
        objFeedback.setExperience(Experience);
        objFeedback.setChanges(Changes);
        objFeedback.setRating(Rating);
        referenceFeedback.setValue(objFeedback);
        Toast.makeText(getApplicationContext(),"Feedback Sent Successfully",Toast.LENGTH_SHORT).show();
        Intent i53 = new Intent(Feedback.this,HomeActivity.class);
        startActivity(i53);
        finish();
    }
}
