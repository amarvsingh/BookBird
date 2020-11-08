
package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EachBuyerResultBook extends AppCompatActivity {

    //To Declare the Fields and Buttons
    ImageView uploaadedimages;
    TextView username,bookname,condition,price,firstimage,secondimage,thirdimage,fourthimage;
    CardView checkseller,chatseller,wishlist;

    //To declare the Srings reequired
    public String Username_Each, Bookname_Each, Condition_Each, Price_Each,Buyer_Username;
    public String Branch,Sem,Subject;
    private String Image1url,Image2url,Image3url,Image4url;

    //To Declare the Database References
    DatabaseReference referenceAddtoWishlist;

    //To create object for gettersetter class
    WishlistGetterSetter objAddtoWishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_buyer_result_book);

        final ProgressBar objProgressBar = new ProgressBar(EachBuyerResultBook.this);
        objProgressBar.startProgressBar();

        //To fetch the requiresd strings for each book selected from the previous activity
        Intent i33 = getIntent();
        Username_Each = i33.getStringExtra("username_eachbookkey");
        Bookname_Each = i33.getStringExtra("bookname_eachbookkey");
        Condition_Each = i33.getStringExtra("condition_eachbookkey");
        Price_Each = i33.getStringExtra("price_eachbookkey");
        Image1url = i33.getStringExtra("image1url_eachbookkey");
        Image2url = i33.getStringExtra("image2url_eachbookkey");
        Image3url = i33.getStringExtra("image3url_eachbookkey");
        Image4url = i33.getStringExtra("image4url_eachbookkey");

        /*Intent i38 = getIntent();
        Buyer_Username = i38.getStringExtra("username_buyerkeywishlist");
        Log.d("Buyer Username", "Buyer Username "+Buyer_Username);*/
        //To get the Buyer Username
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        Buyer_Username = sharedPreferences.getString("username_keyaddtowishlist","");
        Branch = sharedPreferences.getString("branch_keyaddtowishlist","");
        Sem = sharedPreferences.getString("sem_keyaddtowishlist","");
        Subject = sharedPreferences.getString("subject_keyaddtowishlist","");

        //To map the Fields with the xml file
        uploaadedimages = (ImageView)findViewById(R.id.buyeruploadedimage);
        username = (TextView)findViewById(R.id.buyerusername);
        bookname = (TextView)findViewById(R.id.buyerbookname);
        condition = (TextView)findViewById(R.id.buyercondition);
        price = (TextView)findViewById(R.id.buyerprice);
        firstimage = (TextView) findViewById(R.id.buyerfirstimage);
        secondimage = (TextView)findViewById(R.id.buyersecondimage);
        thirdimage = (TextView)findViewById(R.id.buyerthirdimage);
        fourthimage = (TextView)findViewById(R.id.buyerfourthimage);
        checkseller = (CardView) findViewById(R.id.sellerprofile);
        chatseller = (CardView) findViewById(R.id.chatbuyerresulteach);
        wishlist = (CardView) findViewById(R.id.addtowishlist);

        //To set first image as the default
        Picasso.get().load(Image1url).into(uploaadedimages);

        //To have the First Image Button highlighted on creating the Activity
        firstimage.setTextColor(getResources().getColor(R.color.colorPrimary));
        firstimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20f);

        //To put the values of the Strings ine Fields
        username.setText("Uploaded by: "+Username_Each);
        bookname.setText("Book: "+Bookname_Each);
        condition.setText("Condition of the Book: "+Condition_Each);
        price.setText("Price: "+Price_Each);

        Handler handlerDashboard2 = new Handler();
        handlerDashboard2.postDelayed(new Runnable() {
            @Override
            public void run() {
                objProgressBar.dismissProgresBar();
            }
        },2000);

        //To implement the changing image when the number button is clicked
        firstimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image1url).into(uploaadedimages);
                firstimage.setTextColor(getResources().getColor(R.color.colorPrimary));
                secondimage.setTextColor(getResources().getColor(R.color.black));
                thirdimage.setTextColor(getResources().getColor(R.color.black));
                fourthimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20f);
                secondimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                thirdimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                fourthimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
            }
        });
        secondimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image2url).into(uploaadedimages);
                secondimage.setTextColor(getResources().getColor(R.color.colorPrimary));
                firstimage.setTextColor(getResources().getColor(R.color.black));
                thirdimage.setTextColor(getResources().getColor(R.color.black));
                fourthimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                secondimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20f);
                thirdimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                fourthimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
            }
        });
        thirdimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image3url).into(uploaadedimages);
                thirdimage.setTextColor(getResources().getColor(R.color.colorPrimary));
                secondimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextColor(getResources().getColor(R.color.black));
                fourthimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                secondimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                thirdimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20f);
                fourthimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
            }
        });
        fourthimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Image4url).into(uploaadedimages);
                fourthimage.setTextColor(getResources().getColor(R.color.colorPrimary));
                secondimage.setTextColor(getResources().getColor(R.color.black));
                thirdimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextColor(getResources().getColor(R.color.black));
                firstimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                secondimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                thirdimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15f);
                fourthimage.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20f);
            }
        });

        //To check Seller's Profile
        checkseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i35 = new Intent(EachBuyerResultBook.this,EachBookCheckUser.class);
                i35.putExtra("username_eachsellerprofiekey",Username_Each);
                startActivity(i35);
            }
        });

        //To add the book yo wishlist
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceAddtoWishlist = FirebaseDatabase.getInstance().getReference("Wishlist").child(Buyer_Username);
                objAddtoWishlist =new WishlistGetterSetter();
                objAddtoWishlist.setBranch(Branch);
                objAddtoWishlist.setSem(Sem);
                objAddtoWishlist.setSubject(Subject);
                objAddtoWishlist.setPrice(Price_Each);
                objAddtoWishlist.setBookname(Bookname_Each);
                objAddtoWishlist.setUsername(Buyer_Username);
                objAddtoWishlist.setSellerUsername(Username_Each);
                objAddtoWishlist.setImageid(Image1url);
                referenceAddtoWishlist.child(Bookname_Each).setValue(objAddtoWishlist).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Book Successfully added to Wishlist",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Your Request could not be completed at the moment.",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        chatseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i46 = new Intent(EachBuyerResultBook.this,ChatBox.class);
                i46.putExtra("sellerusername_keychat",Username_Each);
                startActivity(i46);
            }
        });
    }
}
