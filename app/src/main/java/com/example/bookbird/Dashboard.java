package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    //To declare the strings required
    public String Username,Fullname,Branch,Userid;

    /*To Declare all Fields and Buttons*/
    TextView username,fullname,branch;
    private Button Buyer,Seller,Profile,Logout,inbox;

    //To Declare Dtabase References
    DatabaseReference referenceDash;
    DatabaseReference referenceGetUsername;
    FirebaseAuth AuthDashboard;
    FirebaseUser UserDashboard;

    //To Declare Connectivity Managers
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toast.makeText(getApplicationContext(),"Please wait untill your name is loaded and displayed on your screen",Toast.LENGTH_LONG).show();
        /*To Map each field with the xml*/
        username = (TextView)findViewById(R.id.username2);
        fullname = (TextView)findViewById(R.id.fullname);
        branch = (TextView)findViewById(R.id.branch);
        Buyer = (Button)findViewById(R.id.buyer);
        Seller = (Button)findViewById(R.id.seller);
        Profile = (Button)findViewById(R.id.profile);
        Logout =(Button)findViewById(R.id.logout);
        inbox = (Button)findViewById(R.id.inbox);

        //To get the Username for the Current User
        AuthDashboard = FirebaseAuth.getInstance();
        UserDashboard = AuthDashboard.getCurrentUser();
        Userid = UserDashboard.getUid();
        referenceGetUsername = FirebaseDatabase.getInstance().getReference("Users").child(Userid);
        referenceGetUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                Username = dataSnapshot1.child("username").getValue().toString().trim();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveInternet = checkInternet.getActiveNetworkInfo();
        if (checkActiveInternet == null){
            Intent i45 = new Intent(Dashboard.this,NoInternet.class);
            startActivity(i45);
            finish();
        }else {
            referenceDash = FirebaseDatabase.getInstance().getReference().child("Users").child(Userid);
            referenceDash.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Fullname = dataSnapshot.child("fullName").getValue().toString().trim();
                    Branch = dataSnapshot.child("branch").getValue().toString().trim();
                    username.setText(Username);
                    fullname.setText(Fullname);
                    branch.setText(Branch);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        //To Logout from the app
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                AuthDashboard.signOut();
                Intent i43 = new Intent(Dashboard.this,HomeActivity.class);
                startActivity(i43);
                finish();
            }
        });

        /*To go to BookPreferences Activity*/
        Buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Intent i6 = new Intent(Dashboard.this,BookPreference.class);
                i6.putExtra("username_key2",Username);
                startActivity(i6);
            }
        });
        //To enter the seller Branch
        Seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Intent i12 = new Intent(Dashboard.this,SellerPage1.class);
                i12.putExtra("username_key2",Username);
                startActivity(i12);
            }
        });

        /*To Open Profile activity*/
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Intent intentProfile = new Intent(Dashboard.this,UserProfile.class);
                intentProfile.putExtra("username_keyprofile", Username);
                startActivity(intentProfile);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                Intent i48 = new Intent(Dashboard.this,Inbox.class);
                i48.putExtra("username_keyinbox", Username);
                startActivity(i48);
            }
        });
    }

    public void checkConnection(){
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveInternet = checkInternet.getActiveNetworkInfo();
        if (checkActiveInternet == null){
            Intent i45 = new Intent(Dashboard.this,NoInternet.class);
            startActivity(i45);
            finish();
        }
    }
}

