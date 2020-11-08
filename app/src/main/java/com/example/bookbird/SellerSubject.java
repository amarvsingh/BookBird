package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.UserRecoverableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SellerSubject extends AppCompatActivity {

    /*To Declare Variables*/
    private String Username,Branch,Sem,Subject1,Subject2,Subject3,Subject4,Subject5,Subject6,SubjectChosen;
    private Boolean error;

    /*To Declare Buttons and Fields*/
    private Button Result,sub1,sub2,sub3,sub4,sub5,sub6;
    private TextView mapping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_subject);

        /*To fetch data from previous activity*/
        Intent i13 = getIntent();
        Username = i13.getStringExtra("username_key3");
        Branch = i13.getStringExtra("branch_key2");
        Sem = i13.getStringExtra("sem_key2");

        /*To map various Fields in the xml file*/
        Result = (Button) findViewById(R.id.result);
        sub1 = (Button) findViewById(R.id.sub1);
        sub2 = (Button) findViewById(R.id.sub2);
        sub3 = (Button) findViewById(R.id.sub3);
        sub4 = (Button) findViewById(R.id.sub4);
        sub5 = (Button) findViewById(R.id.sub5);
        sub6 = (Button) findViewById(R.id.sub6);
        mapping = (TextView)findViewById(R.id.mappingsellersubject);

        //To set the text int the mapping
        mapping.setText(Branch+"->"+Sem);

        SubjectChosen = "";

        /*Fetching data from Database to show on the Buttons*/
        DatabaseReference referenceSellerSubject;
        referenceSellerSubject = FirebaseDatabase.getInstance().getReference("Subjects").child(Branch).child(Sem);
        referenceSellerSubject.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Subject1 = dataSnapshot.child("Subject 1").getValue().toString().trim();
                Subject2 = dataSnapshot.child("Subject 2").getValue().toString().trim();
                Subject3 = dataSnapshot.child("Subject 3").getValue().toString().trim();
                Subject4 = dataSnapshot.child("Subject 4").getValue().toString().trim();
                Subject5 = dataSnapshot.child("Subject 5").getValue().toString().trim();
                Subject6 = dataSnapshot.child("Subject 6").getValue().toString().trim();
                sub1.setText(Subject1);
                sub2.setText(Subject2);
                sub3.setText(Subject3);
                sub4.setText(Subject4);
                sub5.setText(Subject5);
                sub6.setText(Subject6);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Data not found", Toast.LENGTH_LONG).show();
            }
        });

        /*To declare the action on clicking a particular subject button*/
        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                SubjectChosen = new String(Subject1);
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                SubjectChosen = new String(Subject2);
            }
        });

        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                SubjectChosen = new String(Subject3);
            }
        });

        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                SubjectChosen = new String(Subject4);
            }
        });

        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                SubjectChosen = new String(Subject5);
            }
        });

        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectChosen = "";
                if (Subject6.equals("Not available")){
                    Toast.makeText(getApplicationContext(),"Subject not found", Toast.LENGTH_LONG).show();
                    sub6.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                }else{
                    SubjectChosen = new String(Subject6);
                    sub6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    sub2.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub3.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub4.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub1.setBackgroundColor(getResources().getColor(R.color.hover1));
                    sub5.setBackgroundColor(getResources().getColor(R.color.hover1));
                }
            }
        });

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = true;
                if (SubjectChosen.isEmpty()) {
                    error = false;
                    Toast.makeText(getApplicationContext(),"Please select a Subject", Toast.LENGTH_LONG).show();
                }
                else{
                    error = false;
                    Intent i14 = new Intent(SellerSubject.this, SellerBookSelection.class);
                    i14.putExtra("username_key4", Username);
                    i14.putExtra("subject_key2", SubjectChosen);
                    i14.putExtra("branch_key3", Branch);
                    i14.putExtra("sem_key3", Sem);
                    startActivity(i14);
                }
            }
        });
    }
}
