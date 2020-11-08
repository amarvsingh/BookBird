package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup2 extends AppCompatActivity {

    /*To Declare Fields and Buttons*/
    private TextInputLayout fullname,contact,username,email,password,confirmpassword;
    private CardView next;

    /* To Declare Strings for the Fields*/
    public String Fullname,Contact,Username,Email,Password,ConfirmPassword;
    private Boolean error1,error2,error3,error4,error5,error6,error7,error8,error9,error10,error11,errorInternet;

    //To Declare Database References
    DatabaseReference referenceSignup2;
    FirebaseAuth AuthSignup2;

    //To Declare Connectivity Manager
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        /*To Map the Field and Button with xml*/
        fullname = findViewById(R.id.FullnameAsk);
        contact = findViewById(R.id.ContactAsk);
        username = findViewById(R.id.UsernameAsk);
        password = findViewById(R.id.PasswordAsk);
        confirmpassword = findViewById(R.id.ConfirmPasswordAsk);
        email = findViewById(R.id.EmailAsk);
        next = (CardView)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To check whether internet connection present
                checkConnection();
                error11 = true;
                error10 = true;
                /*To Obtain each Field Input as String*/
                Fullname = fullname.getEditText().getText().toString().trim();
                Contact = contact.getEditText().getText().toString().trim();
                Username = username.getEditText().getText().toString().trim();
                Email = email.getEditText().getText().toString().trim();
                Password = password.getEditText().getText().toString().trim();
                ConfirmPassword = confirmpassword.getEditText().getText().toString().trim();
                //To check if the entered email address already exists and if not is valid
                if (Email.isEmpty()){
                    error11 = true;
                    email.setError("Field cannot be empty");
                }else {
                    email.setError(null);
                    error11 = false;
                    String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (Email.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(),"Please wait while your Data is being processed", Toast.LENGTH_SHORT).show();
                        email.setError(null);
                        AuthSignup2 = FirebaseAuth.getInstance();
                        AuthSignup2.fetchSignInMethodsForEmail(Email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean check = !task.getResult().getSignInMethods().isEmpty();
                                if (!check) {
                                    error10 = false;
                                    email.setError(null);
                                    SignupMethod1();
                                } else {
                                    error10 = true;
                                    email.setError("Email address already exists");
                                }
                            }
                        });
                    }else{
                        email.setError("Invalid Email address");
                    }
                }
            }
        });
    }

    public void checkConnection(){
        errorInternet = true;
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveNetwork = checkInternet.getActiveNetworkInfo();
        if (checkActiveNetwork == null){
            errorInternet = true;
            Intent i44 = new Intent(Signup2.this,NoInternet.class);
            startActivity(i44);
            finish();
        }else{
            errorInternet = false;
        }
    }

    public void SignupMethod1(){
        /*To check if any user with the same username exists as entered*/
        referenceSignup2 = FirebaseDatabase.getInstance().getReference("Usernames");
        referenceSignup2.orderByKey().equalTo(Username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                error1 = true;
                error2 = true;
                error3 = true;
                error4 = true;
                error5 = true;
                error6 = true;
                error7 = true;
                error8 = true;
                error9 = true;
                if (dataSnapshot.child(Username).exists()){
                    username.setError("Username already exists");
                    error1 = true;
                }else{
                    error1 = false;
                    /*To Validate the inputs*/
                    if (Fullname.isEmpty()){
                        fullname.setError("Field cannot be left Empty");
                        error2 = true;
                    }else{
                        fullname.setError(null);
                        error2 = false;
                    }
                    if(Contact.isEmpty()){
                        contact.setError("Field cannot be left Empty");
                        error3 = true;
                    }else{
                        contact.setError(null);
                        error3 = false;
                        int FirstContactDigit = Integer.parseInt(String.valueOf(Contact.charAt(0)));
                        if (FirstContactDigit != 9 && FirstContactDigit != 8 && FirstContactDigit !=7 && FirstContactDigit != 6){
                            contact.setError("Enter a valid contact number");
                            error4 = true;
                        }else{
                            error4 = false;
                            contact.setError(null);
                            if(Contact.length() != 10){
                                error5 = true;
                                contact.setError("Enter a valid contact number, 10 digits");
                            }else{
                                error5 = false;
                                contact.setError(null);
                            }
                        }
                    }
                    if (Username.isEmpty()){
                        username.setError("Field cannot be left empty");
                        error6 = true;
                    }else{
                        username.setError(null);
                        error6 = false;
                    }
                    if (Password.isEmpty()){
                        password.setError("Field cannot be left empty");
                        error7 = true;
                    }else{
                        password.setError(null);
                        error7 = false;
                    }
                    if (ConfirmPassword.isEmpty()){
                        confirmpassword.setError("Field cannot be left empty");
                        error8 = true;
                    } else{
                        confirmpassword.setError(null);
                        error8 = false;
                    }
                    if ((Password.isEmpty() && ConfirmPassword.isEmpty()) || !Password.equals(ConfirmPassword)){
                        password.setError("The Passwords you have do not match");
                        confirmpassword.setError(("The Passwords you have do not match"));
                        error9 = true;
                    }else{
                        password.setError(null);
                        confirmpassword.setError(null);
                        error9 = false;
                    }
                    /*To send data to next activity*/
                    if (error1 == false && error2 == false && error3 == false && error4 == false && error5 == false && error6 == false && error7 == false && error8 == false && error9 == false && errorInternet == false){
                        Intent i1 = new Intent(Signup2.this,UserInfo.class);
                        i1.putExtra("fullname_key",Fullname);
                        i1.putExtra("contact_key",Contact);
                        i1.putExtra("username_key",Username);
                        i1.putExtra("email_key",Email);
                        i1.putExtra("password_key",Password);
                        startActivity(i1);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Fields are not properly filled", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"An unknown error has occured please restart the application", Toast.LENGTH_LONG).show();
            }
        });
    }
}
