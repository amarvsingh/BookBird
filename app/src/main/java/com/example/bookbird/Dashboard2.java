package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Dashboard2 extends AppCompatActivity {

    //To Declare all Fields and Buttons
    private TextView username;
    private CardView Buyer,Seller,Uploaded,Logout,Profile,Chats,Wishlist,Calendar;

    //To declare the strings required
    private String Username,Userid;

    //To Declare Dtabase References
    private DatabaseReference referenceDash;
    private DatabaseReference referenceGetUsername;
    private FirebaseAuth AuthDashboard;
    private FirebaseUser UserDashboard;

    //To Declare Connectivity Managers
    private ConnectivityManager checkInternet;
    private NetworkInfo checkActiveInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        final ProgressBar objProgressBar = new ProgressBar(Dashboard2.this);
        objProgressBar.startProgressBar();

        //To Map each field with the xml
        username = (TextView)findViewById(R.id.username2);
        Buyer = (CardView)findViewById(R.id.buyertab);
        Seller = (CardView)findViewById(R.id.sellertab);
        Profile = (CardView)findViewById(R.id.checkprofiletab);
        Chats = (CardView)findViewById(R.id.chattab);
        Logout = (CardView)findViewById(R.id.logouttab);
        Uploaded = (CardView)findViewById(R.id.uploadedtab);
        Wishlist = (CardView)findViewById(R.id.wishlisttab);
        Calendar = (CardView)findViewById(R.id.calendartab);

        //To get the Username for the Current User
        AuthDashboard = FirebaseAuth.getInstance();
        UserDashboard = AuthDashboard.getCurrentUser();
        Userid = UserDashboard.getUid();
        referenceGetUsername = FirebaseDatabase.getInstance().getReference("Users").child(Userid);
        referenceGetUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                Username = dataSnapshot1.child("username").getValue().toString().trim();
                username.setText(Username);
                Handler handlerDashboard2 = new Handler();
                handlerDashboard2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        objProgressBar.dismissProgresBar();
                    }
                },2000);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        //to handle if no connectivity
        checkInternet = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        checkActiveInternet = checkInternet.getActiveNetworkInfo();
        if (checkActiveInternet == null){
            Intent i45 = new Intent(Dashboard2.this,NoInternet.class);
            startActivity(i45);
            finish();
        }

        //To Logout from the app
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthDashboard.signOut();
                Intent i43 = new Intent(Dashboard2.this,HomeActivity.class);
                startActivity(i43);
                finish();
            }
        });
        /*To go to BookPreferences Activity*/
        Buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i55 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i55);
                    finish();
                }else{
                    Intent i6 = new Intent(Dashboard2.this,BookPreference.class);
                    i6.putExtra("username_key2",Username);
                    startActivity(i6);
                }
            }
        });
        //To enter the seller Branch
        Seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i56 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i56);
                    finish();
                }else {
                    Intent i12 = new Intent(Dashboard2.this, SellerPage1.class);
                    i12.putExtra("username_key2", Username);
                    startActivity(i12);
                }
            }
        });
        /*To Open Profile activity*/
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i57 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i57);
                    finish();
                }else {
                    Intent intentProfile = new Intent(Dashboard2.this, UserProfile.class);
                    intentProfile.putExtra("username_keyprofile", Username);
                    startActivity(intentProfile);
                }
            }
        });

        //To go to Chats activity
        Chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i58 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i58);
                    finish();
                }else {
                    Intent i48 = new Intent(Dashboard2.this, Inbox.class);
                    i48.putExtra("username_keyinbox", Username);
                    startActivity(i48);
                }
            }
        });
        //To go o calendar Activity
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i23 = new Intent(Dashboard2.this, AcademicCalendar.class);
                startActivity(i23);
            }
        });
        //To go to productsuploaded activity
        Uploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i59 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i59);
                    finish();
                }else {
                    Intent i25 = new Intent(Dashboard2.this, ProductsUploaded.class);
                    i25.putExtra("username_keyuploaded", Username);
                    startActivity(i25);
                }
            }
        });

        Wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkActiveInternet == null){
                    Intent i60 = new Intent(Dashboard2.this,NoInternet.class);
                    startActivity(i60);
                    finish();
                }else {
                    Intent i39 = new Intent(Dashboard2.this, Wishlist.class);
                    i39.putExtra("username_keymywishlist", Username);
                    startActivity(i39);
                }
            }
        });
    }
}
