package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EachBookCheckUser extends AppCompatActivity {

    //To declare Fields and Buttons
    TextView username, fullname, branch, year, contact;
    Button callseller;
    private static final int REQUEST_CALL = 1;

    //To declare the strings required
    public String UsernameSellerProfile,SellerUid;
    private String Fullname, Branch, Year, Contact;

    //To Declare the database references
    DatabaseReference referencesellerprofile;
    DatabaseReference referencesellerUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_book_check_user);

        //To fecth username from previous activity
        Intent i36 = getIntent();
        UsernameSellerProfile = i36.getStringExtra("username_eachsellerprofiekey");

        //To fetch the Uid of the Seller
        referencesellerUid = FirebaseDatabase.getInstance().getReference("Usernames").child(UsernameSellerProfile);
        referencesellerUid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                SellerUid = dataSnapshot1.child("userid").getValue().toString().trim();
                getSellerDetails();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Cannot Reach the Services at the moment",Toast.LENGTH_LONG).show();
            }
        });

        //To map each field with the xml file
        username = (TextView) findViewById(R.id.usernameeach);
        fullname = (TextView) findViewById(R.id.fullnameeach);
        branch = (TextView) findViewById(R.id.Brancheach);
        year = (TextView) findViewById(R.id.yeareach);
        contact = (TextView) findViewById(R.id.contacteach);
        callseller = (Button) findViewById(R.id.callseller);

        //To call the Seller
        callseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakePhoneCall();
            }
        });
    }

    public void getSellerDetails(){
        //To fetch seler information from the database and show on the activity
        referencesellerprofile = FirebaseDatabase.getInstance().getReference("Users").child(SellerUid);
        referencesellerprofile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Fullname = dataSnapshot.child("fullName").getValue().toString().trim();
                Branch = dataSnapshot.child("branch").getValue().toString().trim();
                Year = dataSnapshot.child("year").getValue().toString().trim();
                Contact = dataSnapshot.child("contact").getValue().toString().trim();
                username.setText(UsernameSellerProfile);
                fullname.setText(Fullname);
                branch.setText(Branch);
                year.setText(Year);
                contact.setText(Contact);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Required Data cannot be fetched at the moment. Please try later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void MakePhoneCall(){
        if (ContextCompat.checkSelfPermission(EachBookCheckUser.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EachBookCheckUser.this, new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else{
            Intent i41 = new Intent(Intent.ACTION_CALL);
            i41.setData(Uri.parse("tel:"+Contact));
            startActivity(i41);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                MakePhoneCall();
            }else{
                Toast.makeText(getApplicationContext(),"Permission For making a Phone Call Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
