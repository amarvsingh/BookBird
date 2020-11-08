package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookbird.Notification.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class Inbox extends AppCompatActivity {

    //To declare fields and buttons
    RecyclerView recyclerViewinbox;
    CardView cardbeta;
    TextView nochattext;
    ImageView nochat;

    //To declare the Strings required
    private String Username,OwnUID;

    //To declare Arraylist required
    ArrayList<InboxConstructor> ListInbox;

    //To declare Dtabase References
    FirebaseAuth AuthInbox;
    FirebaseUser InboxUser;
    DatabaseReference referenceCheckInbox;
    DatabaseReference referenceInbox;

    //To declare object of Adaptor class
    InboxAdaptor objInbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        //To get the OwnUsername
        Intent i48 = getIntent();
        Username = i48.getStringExtra("username_keyinbox");

        //To map the fields with the xml file
        nochat = (ImageView)findViewById(R.id.nochat);
        nochattext = (TextView)findViewById(R.id.nochattext);
        cardbeta = (CardView)findViewById(R.id.cardbeta);
        recyclerViewinbox = (RecyclerView)findViewById(R.id.recyclerviewinbox);
        recyclerViewinbox.setLayoutManager(new LinearLayoutManager(this));
        ListInbox = new ArrayList<InboxConstructor>();

        //To get own UID and Username
        AuthInbox = FirebaseAuth.getInstance();
        InboxUser = AuthInbox.getCurrentUser();
        OwnUID = InboxUser.getUid();

        //To check if existing chats exists
        referenceCheckInbox = FirebaseDatabase.getInstance().getReference("Chatlist");
        referenceCheckInbox.orderByKey().equalTo(Username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.child(Username).exists()){
                    //To show all the activities on the Inbox activity
                    getInbox();
                }
                else{
                    nochat.setVisibility(View.VISIBLE);
                    nochattext.setVisibility(View.VISIBLE);
                    cardbeta.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "An unknown error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getInbox(){
        /*referenceOwnUsername = FirebaseDatabase.getInstance().getReference("Users").child(OwnUID);
        referenceOwnUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Username = dataSnapshot.child("username").getValue().toString();
                Log.d("username",Username);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Cannot process your request at the moment",Toast.LENGTH_SHORT).show();
            }
        });*/
        referenceInbox = FirebaseDatabase.getInstance().getReference("Chatlist").child(Username);
        referenceInbox.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListInbox.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    InboxConstructor p = dataSnapshot1.getValue(InboxConstructor.class);
                    ListInbox.add(p);
                }
                objInbox = new InboxAdaptor(Inbox.this,ListInbox);
                recyclerViewinbox.setAdapter(objInbox);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
