package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyerBookResults extends AppCompatActivity {

    //To declare the strings required
    public String Username,Branch,Sem,Subject,Bookname;
    ArrayList<RecyclerBuyerResultConstruct> listbuyer;

    //To declare the fields
    RecyclerView recyclerViewBuyer;
    TextView mapping;
    private TextView noResulttext;
    private ImageView noResult;

    //To declare Database References
    DatabaseReference referencebuyerbookresultscheck;
    DatabaseReference referencebuyerbookresults;

    //To declare the object the Adaptor class
    RecyclerAdaptorBuyerResult objAdaptorBuyerBookResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_book_results);

        //To fetch all the strings from previous activity
        Intent i32 = getIntent();
        Username = i32.getStringExtra("username_buyerkey4");
        Branch = i32.getStringExtra("branch_buyerkey3");
        Sem = i32.getStringExtra("sem_buyerkey3");
        Subject = i32.getStringExtra("subject_buyerkey2");
        Bookname = i32.getStringExtra("book_buyerkey1");

        //Log.d("Buyer Username","Buyer Username1 "+Username);

        //To send the Username to Wishlist ACTIVITY
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username_keyaddtowishlist", Username);
        editor.putString("branch_keyaddtowishlist", Branch);
        editor.putString("sem_keyaddtowishlist", Sem);
        editor.putString("subject_keyaddtowishlist", Subject);
        editor.apply();

        //To map the Recyclerview with the xml
        recyclerViewBuyer = (RecyclerView)findViewById(R.id.bookresultrecyclerview);
        mapping = (TextView)findViewById(R.id.mappingtext);
        noResult = (ImageView)findViewById(R.id.nobooksfound);
        noResulttext = (TextView)findViewById(R.id.nobooksfoundtext);

        //To add the Mapping
        mapping.setText(Branch+"->"+"Sem"+Sem+"->"+Subject+"->"+Bookname);

        recyclerViewBuyer.setLayoutManager(new LinearLayoutManager(this));
        listbuyer = new ArrayList<RecyclerBuyerResultConstruct>();
        referencebuyerbookresultscheck = FirebaseDatabase.getInstance().getReference("Seller").child(Branch).child(Sem).child(Subject);
        referencebuyerbookresultscheck.orderByKey().equalTo(Bookname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.child(Bookname).exists()){
                    getBooks();
                }else{
                    noResult.setVisibility(View.VISIBLE);
                    noResulttext.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please check Later",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }

    public void getBooks(){
        referencebuyerbookresults = FirebaseDatabase.getInstance().getReference("Seller").child(Branch).child(Sem).child(Subject).child(Bookname);
        referencebuyerbookresults.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    RecyclerBuyerResultConstruct p = dataSnapshot1.getValue(RecyclerBuyerResultConstruct.class);
                    listbuyer.add(p);
                }
                objAdaptorBuyerBookResult = new RecyclerAdaptorBuyerResult(BuyerBookResults.this,listbuyer);
                recyclerViewBuyer.setAdapter(objAdaptorBuyerBookResult);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }
}
