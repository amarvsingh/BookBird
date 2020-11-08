package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassword extends AppCompatActivity {

    //To declare fields and Buttons
    private TextInputLayout email;
    private CardView reset;

    //To declare the Strings required
    private String Email;

    //To declare Database References
    FirebaseAuth AuthForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //To map the fields and buttons with the xml file
        email = findViewById(R.id.EmailForgotPassword);
        reset = (CardView) findViewById(R.id.resetpassword);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = email.getEditText().getText().toString().trim();
                if (Email.isEmpty()){
                    email.setError("Field cannot be empty");
                }else{
                    email.setError(null);
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (Email.matches(emailPattern)){
                        email.setError(null);
                        AuthForgotPassword = FirebaseAuth.getInstance();
                        AuthForgotPassword.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Password reset email has been sent to your Registered Email address",Toast.LENGTH_LONG).show();
                                    Intent i43 = new Intent(ForgotPassword.this, Login.class);
                                    startActivity(i43);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Email address entered does not exists",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else{
                        email.setError("Invalid Email address entered");
                    }
                }
            }
        });
    }
}
