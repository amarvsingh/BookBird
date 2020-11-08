package com.example.bookbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookPreference extends AppCompatActivity {
    /*To Declare Buttons and Fields*/
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,search;
    public String Branch, Sem, Username;
    TextView username;
    private Boolean error1,error2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_preference);
        /*To fetch Username from previous activity*/
        Intent i7 = getIntent();
        Username = i7.getStringExtra("username_key2");

        //To Map the Field and Button with xml
        b1 = (Button)findViewById(R.id.comps);
        b2 = (Button)findViewById(R.id.it);
        b3 = (Button)findViewById(R.id.extc);
        b4 = (Button)findViewById(R.id.mech);
        b5 = (Button)findViewById(R.id.etrx);
        b6 = (Button)findViewById(R.id.sem1);
        b7 = (Button)findViewById(R.id.sem2);
        b8 = (Button)findViewById(R.id.sem3);
        b9 = (Button)findViewById(R.id.sem4);
        b10 = (Button)findViewById(R.id.sem5);
        b11 = (Button)findViewById(R.id.sem6);
        b12 = (Button)findViewById(R.id.sem7);
        b13 = (Button)findViewById(R.id.sem8);
        search = (Button)findViewById(R.id.search);
        username = (TextView)findViewById(R.id.username2);
        username.setText(Username);
        Branch = "";
        Sem = "";
        /*For clicking Transition*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b2.setBackgroundColor(getResources().getColor(R.color.hover1));
                b3.setBackgroundColor(getResources().getColor(R.color.hover1));
                b4.setBackgroundColor(getResources().getColor(R.color.hover1));
                b5.setBackgroundColor(getResources().getColor(R.color.hover1));
                Branch = "Computer Engineering";
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b1.setBackgroundColor(getResources().getColor(R.color.hover1));
                b3.setBackgroundColor(getResources().getColor(R.color.hover1));
                b4.setBackgroundColor(getResources().getColor(R.color.hover1));
                b5.setBackgroundColor(getResources().getColor(R.color.hover1));
                Branch = "Information Technology";
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b1.setBackgroundColor(getResources().getColor(R.color.hover1));
                b2.setBackgroundColor(getResources().getColor(R.color.hover1));
                b4.setBackgroundColor(getResources().getColor(R.color.hover1));
                b5.setBackgroundColor(getResources().getColor(R.color.hover1));
                Branch = "Electronics and Telecommunication";
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b1.setBackgroundColor(getResources().getColor(R.color.hover1));
                b3.setBackgroundColor(getResources().getColor(R.color.hover1));
                b2.setBackgroundColor(getResources().getColor(R.color.hover1));
                b5.setBackgroundColor(getResources().getColor(R.color.hover1));
                Branch = "Mechanical Engineering";
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b1.setBackgroundColor(getResources().getColor(R.color.hover1));
                b3.setBackgroundColor(getResources().getColor(R.color.hover1));
                b4.setBackgroundColor(getResources().getColor(R.color.hover1));
                b2.setBackgroundColor(getResources().getColor(R.color.hover1));
                Branch = "Electronics Engineering";
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "1";
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "2";
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "3";
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "4";
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "5";
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b11.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "6";
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b12.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b13.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "7";
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b13.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                b10.setBackgroundColor(getResources().getColor(R.color.hover1));
                b11.setBackgroundColor(getResources().getColor(R.color.hover1));
                b12.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                Sem = "8";
            }
        });

        /*To Send fetched data to next activity on clicking the search button*/
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error1 = true;
                error2 = true;
                if(Branch.isEmpty()){
                    error1 = true;
                    Toast.makeText(getApplicationContext(),"Please select a Branch before proceeding",Toast.LENGTH_LONG).show();
                }
                else{
                    error1 = false;
                }
                if(Sem.isEmpty()){
                    error2 = true;
                    Toast.makeText(getApplicationContext(),"Please select a Sem before proceeding",Toast.LENGTH_LONG).show();
                }
                else{
                    error2 = false;
                }
                if (error1 == false && error2 == false) {
                    Intent i8 = new Intent(BookPreference.this, SearchResult.class);
                    i8.putExtra("username_keybuyer1", Username);
                    i8.putExtra("branch_key1", Branch);
                    i8.putExtra("sem_key1", Sem);
                    startActivity(i8);
                }
            }
        });
    }
}
