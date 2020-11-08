package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookbird.Notification.ClientNotification;
import com.example.bookbird.Notification.DataNotification;
import com.example.bookbird.Notification.MyResponseNotification;
import com.example.bookbird.Notification.SenderNotification;
import com.example.bookbird.Notification.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatBox extends AppCompatActivity {

    //To declare fields and buttons
    TextView otherusername;
    EditText textmessage;
    ImageView sendbutton;
    RecyclerView recyclerViewChatBox;

    //To declare the strings required
    public String OtherUsername,OwnUsername,OtherUID,OwnUID,TextMessage;

    //To Declare database references
    FirebaseAuth AuthChatBox;
    FirebaseUser Own;
    FirebaseUser Other;
    DatabaseReference referenceSellerUID;
    DatabaseReference referenceOwnUsername;
    DatabaseReference referenceEachChatlist;
    DatabaseReference referenceTextMessage;
    DatabaseReference referenceChat;

    //To create objects of GetterSetter Classes
    ChatEachListGetterSetter obj1;
    ChatEachListGetterSetter obj2;
    ChatGetterSetter objChatConstructor1;
    ChatGetterSetter objChatConstructor2;

    //To declare Arraylist for Adaptorclass
     ArrayList<ChatConstructor> ListChat;

     //To declare object of Adaptor class
    ChatAdaptor objChatAdaptor;

    //To declare Api Service for Notification
    APIservice apiservice;
    boolean notify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);

        //To get OwnUID
        AuthChatBox = FirebaseAuth.getInstance();
        Own = AuthChatBox.getCurrentUser();
        OwnUID = Own.getUid();
        GetOwnUsername();

        //apiservice code for notification to chat
        apiservice = ClientNotification.getClient("https://fcm.googleapis.com/").create(APIservice.class);

        //To fetch the seller username from previous activity
        Intent i47 = getIntent();
        OtherUsername = i47.getStringExtra("sellerusername_keychat");
        if (OtherUsername==null){
            Intent i50 = getIntent();
            OtherUsername = i50.getStringExtra("otherusername_key_inboxtochatbox");
        }
        ListChat = new ArrayList<ChatConstructor>();
        //To map the fields with the xml file
        otherusername = (TextView)findViewById(R.id.otherusernamechat);
        textmessage  = (EditText)findViewById(R.id.textmessagechat);
        sendbutton = (ImageView)findViewById(R.id.sendbutton);
        recyclerViewChatBox = (RecyclerView)findViewById(R.id.recyclerviewchat);
        otherusername.setText(OtherUsername);

        //To fix the RecycleView
        recyclerViewChatBox.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatBox.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewChatBox.setLayoutManager(linearLayoutManager);

        //To get UID of seller(other)
        GetOtherUId();

        //On cliking the send Button
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextMessage = textmessage.getText().toString().trim();
                //If the textmessage field is not empty
                if (!TextMessage.isEmpty()){
                    notify = true;
                    updateToken(FirebaseInstanceId.getInstance().getToken());
                    SetChatList();
                    textmessage.setText(null);
                    objChatConstructor1 = new ChatGetterSetter();
                    objChatConstructor2 = new ChatGetterSetter();
                    referenceTextMessage = FirebaseDatabase.getInstance().getReference("Chats");
                    objChatConstructor1.setMessage(TextMessage);
                    objChatConstructor1.setSender(OwnUID);
                    objChatConstructor1.setReciever(OtherUID);
                    referenceTextMessage.child(OwnUID).child(OtherUID).push().setValue(objChatConstructor1);
                    objChatConstructor2.setMessage(TextMessage);
                    objChatConstructor2.setSender(OwnUID);
                    objChatConstructor2.setReciever(OtherUID);
                    referenceTextMessage.child(OtherUID).child(OwnUID).push().setValue(objChatConstructor2);
                    //getChat();
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot send Empty Text", Toast.LENGTH_SHORT).show();
                }

                final String msg = TextMessage;
                if (notify == true) {
                    sendNotification(OtherUID, OwnUID, msg);
                }
                notify = false;
            }
        });
    }

    public void updateToken(String token){
        DatabaseReference referenceToken = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        referenceToken.child(OwnUID).setValue(token1);
    }

    public void GetOwnUsername(){
        referenceOwnUsername = FirebaseDatabase.getInstance().getReference("Users").child(OwnUID);
        referenceOwnUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                OwnUsername = dataSnapshot1.child("username").getValue().toString().trim();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }

    public void GetOtherUId(){
        referenceSellerUID = FirebaseDatabase.getInstance().getReference("Usernames").child(OtherUsername);
        referenceSellerUID.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OtherUID = dataSnapshot.child("userid").getValue().toString().trim();
                //To get the Existing Chat
                getChat();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }

    public void SetChatList(){
        referenceEachChatlist = FirebaseDatabase.getInstance().getReference("Chatlist");
        obj1 = new ChatEachListGetterSetter();
        obj1.setOwnUsername(OwnUsername);
        obj1.setOtherUsername(OtherUsername);
        referenceEachChatlist.child(OwnUsername).child(OtherUsername).setValue(obj1);
        obj2 = new ChatEachListGetterSetter();
        obj2.setOwnUsername(OtherUsername);
        obj2.setOtherUsername(OwnUsername);
        referenceEachChatlist.child(OtherUsername).child(OwnUsername).setValue(obj2);
    }

    public void getChat(){
        referenceChat = FirebaseDatabase.getInstance().getReference("Chats").child(OwnUID).child(OtherUID);
        referenceChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListChat.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ChatConstructor p = dataSnapshot1.getValue(ChatConstructor.class);
                    ListChat.add(p);
                }
                objChatAdaptor = new ChatAdaptor(ChatBox.this,ListChat);
                recyclerViewChatBox.setAdapter(objChatAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }

    public void sendNotification(String Otherusername, final String Ownusername, final String Message){
        DatabaseReference referenceToken = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = referenceToken.orderByKey().equalTo(Otherusername);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Token token = dataSnapshot1.getValue(Token.class);
                    DataNotification data = new DataNotification(OwnUID,R.mipmap.app_logo_2,Ownusername+": "+Message,"New Message",OtherUID);
                    SenderNotification sender = new SenderNotification(data, token.getToken());
                    apiservice.sendNotification(sender).enqueue(new Callback<MyResponseNotification>() {
                        @Override
                        public void onResponse(Call<MyResponseNotification> call, Response<MyResponseNotification> response) {
                            if (response.code() == 200){
                                if (response.body().success != 1){
                                    Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<MyResponseNotification> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
