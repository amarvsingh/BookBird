package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Wishlist extends AppCompatActivity {

    //To Declare Fields and Buttons
    RecyclerView recyclerViewWishlist;
    private ImageView NoWishlist;
    private TextView NoWishlisttext;

    //To Declare the Requires Strings;
    public String Username_Buyer,SellerUsername,WishedBookname;
    ArrayList<WishlistConstructor> listWishlist;
  // public String Branchsellerlist,Booknamesellerlist,Subjectsellerlist,Semsellerlist,Mrpsellerlist,Conditionsellerlist,Pricesellerlist,Imageidsellerlist,Usernamesellerlist;

    //To Declare Database References
    DatabaseReference referenceWishlistpage;
    DatabaseReference referenceWishlistcheck;

    //To create an object of the Adaptop class
    RecyclerAdaptorWishlist objAdaptorWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        //To Ftech the strings from previous activity
        Intent i40 = getIntent();
        Username_Buyer = i40.getStringExtra("username_keymywishlist");

        //To map the fields with the layout xml file
        recyclerViewWishlist = (RecyclerView)findViewById(R.id.recyclerviewwishlist);
        NoWishlist = (ImageView)findViewById(R.id.wishlistnotfound);
        NoWishlisttext = (TextView)findViewById(R.id.wishlistnotfoundtext);

        recyclerViewWishlist.setLayoutManager(new LinearLayoutManager(this));
        listWishlist = new ArrayList<WishlistConstructor>();

        //To check if wishlist is empty
        referenceWishlistcheck = FirebaseDatabase.getInstance().getReference("Wishlist");
        referenceWishlistcheck.orderByKey().equalTo(Username_Buyer).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.child(Username_Buyer).exists()){
                    getWishlist();
                }
                else{
                    NoWishlist.setVisibility(View.VISIBLE);
                    NoWishlisttext.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            //
            }
        });
    }

    public void getWishlist(){
        referenceWishlistpage = FirebaseDatabase.getInstance().getReference("Wishlist").child(Username_Buyer);
        referenceWishlistpage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    WishlistConstructor p = dataSnapshot1.getValue(WishlistConstructor.class);
                    listWishlist.add(p);
                }
                objAdaptorWishlist = new RecyclerAdaptorWishlist(Wishlist.this,listWishlist);
                recyclerViewWishlist.setAdapter(objAdaptorWishlist);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }
}
