package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductsUploaded extends AppCompatActivity {

    DatabaseReference referenceUpload;
    StorageReference referenceUploadimage;
    RecyclerView recyclerView;
    private String Username;
    ArrayList<RecyclerUploadedConstruct> list;
    RecyclerAdaptorUploaded objAdapter;
    private DatabaseReference referenceCheckProduct;

    //
    private TextView noproducttext;
    private ImageView noproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_uploaded);

        //
        Intent i26 = getIntent();
        Username = i26.getStringExtra("username_keyuploaded");

        //
        noproduct = (ImageView)findViewById(R.id.noproduct);
        noproducttext = (TextView)findViewById(R.id.noproducttext);
        recyclerView = (RecyclerView)findViewById(R.id.productsuploadedrecyclerview);

        //
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<RecyclerUploadedConstruct>();

        //To check if any product exist
        referenceCheckProduct = FirebaseDatabase.getInstance().getReference("SellerList");
        referenceCheckProduct.orderByKey().equalTo(Username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.child(Username).exists()){
                    getProductsUploaded();
                }else{
                    noproduct.setVisibility(View.VISIBLE);
                    noproducttext.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getProductsUploaded(){
        referenceUpload = FirebaseDatabase.getInstance().getReference("SellerList").child(Username);
        referenceUpload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    RecyclerUploadedConstruct p = dataSnapshot1.getValue(RecyclerUploadedConstruct.class);
                    list.add(p);
                }
                objAdapter = new RecyclerAdaptorUploaded(ProductsUploaded.this,list);
                recyclerView.setAdapter(objAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
    }
}
