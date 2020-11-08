package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

public class UserInfo extends AppCompatActivity {
    /*To Declare the buttons*/
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,Signup;

    //To Declare the Strings Required
    public String Branch,Year,Fullname,Contact,Username,Email,Password;
    private Boolean error1,error2;

    //To Declare Dtabase Reference
    FirebaseAuth AuthUserinfo;
    FirebaseUser newUser;
    DatabaseReference referenceusernames;
    DatabaseReference referenceSignup1;

    //To Declare objects of getter setter classes
    SignupInfo objSignup;
    Usernamelist objUsernamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        //To Map the Field and Button with xml
        b1 = (Button)findViewById(R.id.comps);
        b2 = (Button)findViewById(R.id.it);
        b3 = (Button)findViewById(R.id.extc);
        b4 = (Button)findViewById(R.id.mech);
        b5 = (Button)findViewById(R.id.etrx);
        b6 = (Button)findViewById(R.id.first);
        b7 = (Button)findViewById(R.id.second);
        b8 = (Button)findViewById(R.id.third);
        b9 = (Button)findViewById(R.id.fourth);
        Signup = (Button)findViewById(R.id.signup);

        Branch = "";
        Year = "";

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
                Year = "First";
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                Year = "Second";
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                b9.setBackgroundColor(getResources().getColor(R.color.hover1));
                Year = "Third";
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                b7.setBackgroundColor(getResources().getColor(R.color.hover1));
                b8.setBackgroundColor(getResources().getColor(R.color.hover1));
                b6.setBackgroundColor(getResources().getColor(R.color.hover1));
                Year = "Fourth";
            }
        });

        /*To push each and every input into Database on clicking Signup button*/
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error1 = true;
                error2 = true;
                /*To obtain value from previous activity*/
                Intent i2 = getIntent();
                Fullname = i2.getStringExtra("fullname_key");
                Contact = i2.getStringExtra("contact_key");
                Username = i2.getStringExtra("username_key");
                Email = i2.getStringExtra("email_key");
                Password = i2.getStringExtra("password_key");
                if (Branch.isEmpty()){
                    error1 = true;
                    Toast.makeText(getApplicationContext(),"Please Enter the Branch Before Proceeding",Toast.LENGTH_LONG).show();
                }else{
                    error1 = false;
                }
                if (Year.isEmpty()){
                    error2 = true;
                    Toast.makeText(getApplicationContext(),"Please Enter the Year Before Proceeding",Toast.LENGTH_LONG).show();
                }else{
                    error2 = false;
                }
                if(error1 == false && error2 == false) {
                    AuthUserinfo = FirebaseAuth.getInstance();
                    AuthUserinfo.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Unknwon Error Occured", Toast.LENGTH_LONG).show();
                            }else{
                                CreateAccount();
                            }
                        }
                    });

                }
            }
        });
    }

    public void CreateAccount(){
        /*To Enter Data into Database */
        /*Using Getter Setter Class SignupInfo.java*/
        newUser = AuthUserinfo.getCurrentUser();
        objSignup = new SignupInfo();
        objSignup.setFullName(Fullname);
        objSignup.setContact(Contact);
        objSignup.setUsername(Username);
        objSignup.setEmail(Email);
        objSignup.setBranch(Branch);
        objSignup.setYear(Year);
        referenceSignup1 = FirebaseDatabase.getInstance().getReference().child("Users");
        referenceSignup1.child(newUser.getUid()).setValue(objSignup);
        //To make a list of usernames in Database
        referenceusernames = FirebaseDatabase.getInstance().getReference().child("Usernames");
        objUsernamelist = new Usernamelist();
        objUsernamelist.setUserid(newUser.getUid());
        referenceusernames.child(Username).setValue(objUsernamelist);
        /*To go to login page*/
        Intent i3 = new Intent(UserInfo.this, Login.class);
        startActivity(i3);
        finish();
    }
}
