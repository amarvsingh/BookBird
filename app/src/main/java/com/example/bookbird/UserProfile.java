package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    //To declare basic info fields
    TextView username,branch,fullname,contact;
    //Button calendar,selleruploaded,wishlist;

    //To Declare database References
    DatabaseReference referenceProfile1;
    FirebaseAuth AuthUserProfile;
    FirebaseUser AuthUserProfileUser;

    //To declare Variables
    private String Username,Branch,Year,Fullname,Contact,Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        /*To get Username from the Dashboard Activity*/
        Intent i21 = getIntent();
        Username = i21.getStringExtra("username_keyprofile");

        //To get the Uid of the current user
        AuthUserProfile = FirebaseAuth.getInstance();
        AuthUserProfileUser = AuthUserProfile.getCurrentUser();
        Userid = AuthUserProfileUser.getUid();

        //To Map fields with xml file
        username = (TextView)findViewById(R.id.username2);
        branch = (TextView)findViewById(R.id.branchprofile);
        fullname = (TextView)findViewById(R.id.fullnameprofile);
        contact = (TextView)findViewById(R.id.contactprofile);
        //calendar = (Button)findViewById(R.id.academic_calendar);
        //selleruploaded = (Button)findViewById(R.id.seller_uploaded);
        //wishlist = (Button)findViewById(R.id.wishlist);
        username.setText(Username);
        //To get Branch and year for the username logged  in
        referenceProfile1 = FirebaseDatabase.getInstance().getReference("Users").child(Userid);
        referenceProfile1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Branch = dataSnapshot.child("branch").getValue().toString().trim();
                Year = dataSnapshot.child("year").getValue().toString().trim();
                Fullname = dataSnapshot.child("fullName").getValue().toString().trim();
                Contact = dataSnapshot.child("contact").getValue().toString().trim();
                fullname.setText(Fullname);
                contact.setText("+91 "+Contact);
                if (Year.equals("Third")){
                    branch.setText(Branch+" (T.Y.)");
                }
                if (Year.equals("Second")){
                    branch.setText(Branch+" (S.Y.)");
                }
                if (Year.equals("First")){
                    branch.setText(Branch+" (F.Y.)");
                }
                if (Year.equals("Fourth")){
                    branch.setText(Branch+" (L.Y.)");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });

        /*calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i23 = new Intent(UserProfile.this, AcademicCalendar.class);
                startActivity(i23);
            }
        });

        selleruploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i25 = new Intent(UserProfile.this,ProductsUploaded.class);
                i25.putExtra("username_keyuploaded",Username);
                startActivity(i25);
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i39 = new Intent(UserProfile.this,Wishlist.class);
                i39.putExtra("username_keymywishlist",Username);
                startActivity(i39);
            }
        });*/
    }
}
