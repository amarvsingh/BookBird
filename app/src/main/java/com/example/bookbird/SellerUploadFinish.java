package com.example.bookbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SellerUploadFinish extends AppCompatActivity {

    /*To Declare fields on the layout.xml*/
    ImageView uploadgif;
    TextView bookline,subjectline,branchline,semline,mrpline,priceline,conditionline;
    Button gotodashboard;

    /*To declare Strings that are required*/
    public String Username;
    private String Bookname;
    private String Subjectname;
    private String Branchname;
    private String Semname;
    private String Mrp;
    private String Price;
    private String Condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_upload_finish);

        /*To Map the fields on the xml file*/
        uploadgif = (ImageView)findViewById(R.id.uploadgif);
        bookline = (TextView)findViewById(R.id.bookname);
        subjectline = (TextView)findViewById(R.id.subjectname);
        branchline = (TextView)findViewById(R.id.branchname);
        semline = (TextView)findViewById(R.id.semname);
        mrpline = (TextView)findViewById(R.id.mrp);
        priceline = (TextView)findViewById(R.id.price);
        conditionline = (TextView)findViewById(R.id.condition);
        gotodashboard = (Button)findViewById(R.id.gotodashboard);

        /*To Declare online Gif Url*/
        String GifURL = "https://media.giphy.com/media/hvMz6f9pEqCMFHYqGg/giphy.gif";

        /*Displaying GIF using Glide library*/
        Glide.with(this).load(GifURL).into(uploadgif);

        /*To get the inputs from previous activities*/
        Intent i20 = getIntent();
        Username = i20.getStringExtra("username_key6");
        Bookname = i20.getStringExtra("book_key2");
        Subjectname = i20.getStringExtra("subject_key4");
        Branchname = i20.getStringExtra("branch_key5");
        Semname = i20.getStringExtra("sem_key5");
        Condition = i20.getStringExtra("condition_key1");
        Mrp = i20.getStringExtra("mrp_key1");
        Price = i20.getStringExtra("price_key1");


        /*To show uploaded book IMformation*/
        bookline.setText("Book Name: "+Bookname);
        subjectline.setText("Subject: "+Subjectname);
        branchline.setText("Branch: "+Branchname);
        semline.setText("Semester: "+Semname);
        mrpline.setText("MRP of the book: "+Mrp);
        priceline.setText("Selling Price (negotiable): "+Price);
        conditionline.setText("Condition of the book: "+Condition);

        //To Go To DASHBOARD Activity
        gotodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i33 = new Intent(SellerUploadFinish.this, Dashboard2.class);
                startActivity(i33);
                finish();
            }
        });
    }
}
