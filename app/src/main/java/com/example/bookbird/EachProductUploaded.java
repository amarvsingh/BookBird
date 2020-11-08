package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EachProductUploaded extends AppCompatActivity {

    //To Declare the strings required
    private String Username, Bookname, Branch, Sem, Subject, Condition, Mrp, Price, Image1Url, Image2Url, Image3Url, Image4Url;

    //To declare fields and button
    TextView bookname,condition,price,branch,sem,subject;
    Button image1,image2,image3,image4;
    ImageView imageholder;

    //To declare the database references
    DatabaseReference referenceeachproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_product_uploaded);

        //To fetch the strings from the adaptor class
        Intent i27 = getIntent();
        Username = i27.getStringExtra("username_keyeachproduct");
        Branch = i27.getStringExtra("branch_keyeachproduct");
        Sem = i27.getStringExtra("sem_keyeachproduct");
        Subject = i27.getStringExtra("subject_keyeachproduct");
        Bookname = i27.getStringExtra("bookname_keyeachproduct");
        Condition = i27.getStringExtra("condition_keyeachproduct");
        Mrp = i27.getStringExtra("mrp_keyeachproduct");
        Price = i27.getStringExtra("price_keyeachproduct");

        //To map each field with xml file
        bookname = (TextView)findViewById(R.id.bookname);
        condition = (TextView)findViewById(R.id.conditionname);
        price = (TextView)findViewById(R.id.priceamount);
        branch = (TextView)findViewById(R.id.branchname);
        sem = (TextView)findViewById(R.id.semname);
        subject = (TextView)findViewById(R.id.subjectname);
        image1 = (Button)findViewById(R.id.firstimagebutton);
        image2 = (Button)findViewById(R.id.secondimagebutton);
        image3 = (Button)findViewById(R.id.thirdimagebutton);
        image4 = (Button)findViewById(R.id.fourthimagebutton);
        imageholder = (ImageView)findViewById(R.id.bookimage);

        //To set the fields(directly)
        bookname.setText(Bookname);
        price.setText(Price+" (INR)");
        condition.setText(Condition);
        branch.setText(Branch);
        sem.setText(Sem);
        subject.setText(Subject);

        //To have the First Image Button highlighted on creating the Activity
        image1.setTextColor(getResources().getColor(R.color.hover1));
        image1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        //To fetch the images of the selected products from realtime database and show on the Imageview
        referenceeachproduct = FirebaseDatabase.getInstance().getReference("Seller").child(Branch).child(Sem).child(Subject).child(Bookname).child(Username);
        referenceeachproduct.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Image1Url = dataSnapshot.child("imageid1").getValue().toString().trim();
                Image2Url = dataSnapshot.child("imageid2").getValue().toString().trim();
                Image3Url = dataSnapshot.child("imageid3").getValue().toString().trim();
                Image4Url = dataSnapshot.child("imageid4").getValue().toString().trim();
                //setting image 1 as default image
                Picasso.get().load(Image1Url).into(imageholder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Cannot Retrieve Data at this moment", Toast.LENGTH_LONG).show();
            }
        });

        //To add changing image effect on clicking the image number button
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image1Url).into(imageholder);
                image1.setTextColor(getResources().getColor(R.color.hover1));
                image2.setTextColor(getResources().getColor(R.color.black));
                image3.setTextColor(getResources().getColor(R.color.black));
                image4.setTextColor(getResources().getColor(R.color.black));
                image1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                image2.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image3.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image4.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image2Url).into(imageholder);
                image2.setTextColor(getResources().getColor(R.color.hover1));
                image1.setTextColor(getResources().getColor(R.color.black));
                image3.setTextColor(getResources().getColor(R.color.black));
                image4.setTextColor(getResources().getColor(R.color.black));
                image2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                image1.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image3.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image4.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image3Url).into(imageholder);
                image3.setTextColor(getResources().getColor(R.color.hover1));
                image2.setTextColor(getResources().getColor(R.color.black));
                image1.setTextColor(getResources().getColor(R.color.black));
                image4.setTextColor(getResources().getColor(R.color.black));
                image3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                image2.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image1.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image4.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image4Url).into(imageholder);
                image4.setTextColor(getResources().getColor(R.color.hover1));
                image2.setTextColor(getResources().getColor(R.color.black));
                image3.setTextColor(getResources().getColor(R.color.black));
                image1.setTextColor(getResources().getColor(R.color.black));
                image4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                image2.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image3.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
                image1.setBackgroundColor(getResources().getColor(R.color.colorscheme1));
            }
        });
    }
}
