package com.example.bookbird;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText FullnameInput;
    private EditText ContactInput;
    private EditText EmailInput;
    private EditText UsernameInput;
    private EditText PasswordInput;
    private EditText ConfirmPasswordInput;
    private Button b1;
    String FullnameInputString;
    String ConfirmPasswordInputString;
    String PasswordInputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        b1 = (Button)findViewById(R.id.next);
        FullnameInput = (EditText)findViewById(R.id.FullnameInput);
        FullnameInputString = FullnameInput.getText().toString().trim();
        PasswordInput = (EditText)findViewById(R.id.PasswordInput);
        ConfirmPasswordInput = (EditText)findViewById(R.id.ConfirmPasswordInput);
        PasswordInputString = PasswordInput.getText().toString();
        ConfirmPasswordInputString = ConfirmPasswordInput.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validating();
            }
        });
    }

    public void Validating(){
        if (FullnameInputString.length()==0){
            Toast.makeText(this, "You did not enter the Fullname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!(ConfirmPasswordInputString.equals(PasswordInputString))){
            Toast.makeText(this, "Please make sure the Password and the Confirm Password match", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
