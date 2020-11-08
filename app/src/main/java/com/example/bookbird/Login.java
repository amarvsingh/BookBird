package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    /*To Declare Fields and Buttons*/
    private TextInputLayout email, password;
    private TextView forgotpassword;
    private CardView login;


    /*To Declare Strings for the Fields*/
    public String Email,Password;
    private Boolean error1,error2,error3,error4;

    //To declare Database Refereces
    FirebaseUser AuthCheckCurrentUser;
    FirebaseAuth AuthLogincheck;
    FirebaseAuth AuthLogin;

    //To Declare Connectivity Managers
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*To Map the Field and Button with xml*/
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = (CardView)findViewById(R.id.login);
        forgotpassword = (TextView)findViewById(R.id.forgotpassword);

        //To check if Internet is available
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveInternet = checkInternet.getActiveNetworkInfo();
        if (checkActiveInternet == null){
            Intent i45 = new Intent(Login.this,NoInternet.class);
            startActivity(i45);
            finish();
        }else{
            //If the user is already logged in redirect to Dashboard Activity
            AuthLogincheck = FirebaseAuth.getInstance();
            AuthCheckCurrentUser = AuthLogincheck.getCurrentUser();
            if (AuthCheckCurrentUser != null) {
                Intent i42 = new Intent(Login.this, Dashboard2.class);
                startActivity(i42);
                finish();
            }
        }


        /*To check for the availabilty of Username and the correct Password on clicking Login Button*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Email = email.getEditText().getText().toString().trim();
                Password = password.getEditText().getText().toString().trim();
                error1 = true;
                error2 = true;
                error3 = true;
                error4 = true;

                //To check if any already logged in user exists
                if (Email.isEmpty()) {
                    email.setError("This Field cannot be empty");
                    error1 = true;
                } else {
                    email.setError(null);
                    error1 = false;
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (Email.matches(emailPattern)) {
                        error2 = false;
                        email.setError(null);
                        AuthLogincheck.fetchSignInMethodsForEmail(Email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean check = !task.getResult().getSignInMethods().isEmpty();
                                if (!check) {
                                    error3 = true;
                                    email.setError("Email Address does not exists");
                                } else {
                                    error3 = false;
                                    email.setError(null);
                                    if (Password.isEmpty()) {
                                        password.setError("This Field cannot be empty");
                                        error4 = true;
                                    } else {
                                        password.setError(null);
                                        error4 = false;
                                        LoginMethod();
                                    }
                                }
                            }
                        });
                    } else {
                        error2 = true;
                        email.setError("Enter a valid Email address");
                    }
                }
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Intent i44 = new Intent(Login.this,ForgotPassword.class);
                startActivity(i44);
                finish();
            }
        });
    }

    public void checkConnection(){
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveInternet = checkInternet.getActiveNetworkInfo();
        if (checkActiveInternet == null){
            Intent i45 = new Intent(Login.this,NoInternet.class);
            startActivity(i45);
            finish();
        }
    }

    public void LoginMethod(){
        AuthLogin = FirebaseAuth.getInstance();
        AuthLogin.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
                }else{
                    Intent i4 = new Intent(Login.this,Dashboard2.class);
                    startActivity(i4);
                    finish();
                }
            }
        });
    }
}