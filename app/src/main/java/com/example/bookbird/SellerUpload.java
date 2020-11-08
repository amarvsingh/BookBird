 package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.stream.IntStream;

public class SellerUpload extends AppCompatActivity {

    /*To Declare Buttons and Fields*/
    private Button condition1, condition2, condition3, condition4;
    private CardView add1, add2, add3, add4,upload,confirm;
    private TextInputLayout mrp, price;
    private ImageView first, second, third, fourth;

    /*To Declare all the Strings required for input*/
    public String Mrp = null, Price = null, Condition = null, Subject = null, Branch = null, Sem = null, Username = null, Book = null, Status = "Unsold";
    public String imageid1,imageid2,imageid3,imageid4,downloadimage1,downloadimage2,downloadimage3,downloadimage4;
    private int Picture_Count[] = {0,0,0,0,0};
    private int Uploadcount;
    private Boolean error1 = true, error2 = true,error3 = true, error4 = true;
    public Uri Picture1_Uri,Picture2_Uri,Picture3_Uri,Picture4_Uri;

    /*To Declare Database Reference*/
    StorageReference referenceSellerUpload;
    StorageReference referenceSellerUpload1;
    StorageReference referenceSellerUpload2;
    StorageReference referenceSellerUpload3;
    StorageReference referenceSellerUpload4;
    private StorageTask uploadTask;
    SellerUploadInfo objsellerupload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_upload);

        /*To fetch the Subject from SellerSubject activity*/
        Intent i15 = getIntent();
        Username = i15.getStringExtra("username_key5");
        Branch = i15.getStringExtra("branch_key4");
        Sem = i15.getStringExtra("sem_key4");
        Subject = i15.getStringExtra("subject_key3");
        Book = i15.getStringExtra("book_key1");

        /*To Map the Buttons and Fields with the xml file*/
        condition1 = (Button)findViewById(R.id.cond1);
        condition2 = (Button)findViewById(R.id.cond2);
        condition3 = (Button)findViewById(R.id.cond3);
        condition4= (Button)findViewById(R.id.cond4);
        upload = (CardView)findViewById(R.id.upload);
        add1 = (CardView) findViewById(R.id.addimage1);
        add2 = (CardView) findViewById(R.id.addimage2);
        add3 = (CardView) findViewById(R.id.addimage3);
        add4 = (CardView) findViewById(R.id.addimage4);
        mrp = findViewById(R.id.mrpask);
        price = findViewById(R.id.priceask);
        first = (ImageView)findViewById(R.id.image1);
        second = (ImageView)findViewById(R.id.image2);
        third = (ImageView)findViewById(R.id.image3);
        fourth = (ImageView)findViewById(R.id.image4);
        confirm = (CardView)findViewById(R.id.confirm);

        Condition = "";

        /*To Add clicking effect and set the condition of the Book*/
        condition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                condition2.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition3.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition4.setBackgroundColor(getResources().getColor(R.color.hover1));
                Condition = condition1.getText().toString().trim();
            }
        });

        condition2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                condition1.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition3.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition4.setBackgroundColor(getResources().getColor(R.color.hover1));
                Condition = condition2.getText().toString().trim();
            }
        });

        condition3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                condition2.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition1.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition4.setBackgroundColor(getResources().getColor(R.color.hover1));
                Condition = condition3.getText().toString().trim();
            }
        });

        condition4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                condition2.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition3.setBackgroundColor(getResources().getColor(R.color.hover1));
                condition1.setBackgroundColor(getResources().getColor(R.color.hover1));
                Condition = condition4.getText().toString().trim();
            }
        });

        /*To Add pictures to the respective fields*/
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture_Count[1] = 0;
                filechooser1();
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture_Count[2] = 0;
                filechooser2();
            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture_Count[3] = 0;
                filechooser3();
            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture_Count[4] = 0;
                filechooser4();
            }
        });


        /*To upload images and other inputs into database*/
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mrp = mrp.getEditText().getText().toString().trim();
                Price = price.getEditText().getText().toString().trim();
                if (Mrp.isEmpty()){
                    mrp.setError("Field cannot be left empty");
                    error1 = true;
                } else{
                    mrp.setError(null);
                    error1 = false;
                }
                if (Price.isEmpty()){
                    price.setError("Field cannot be left empty");
                    error2 = true;
                } else{
                    price.setError(null);
                    error2 = false;
                }
                if (error1 == false && error2 == false) {
                    if (Integer.parseInt(Mrp) <= Integer.parseInt(Price)) {
                        mrp.setError("Selling Price cannot be more then the Mrp..");
                        price.setError("Selling Price cannot be more then the Mrp..");
                        error3 = true;
                    }
                    if (Integer.parseInt(Mrp) > Integer.parseInt(Price)) {
                        mrp.setError(null);
                        price.setError(null);
                        error3 = false;
                    }
                }
                if (Condition.isEmpty()){
                    error4 = true;
                    Toast.makeText(getApplicationContext(),"Please Select the Condition for the Book you wish to Sell.",Toast.LENGTH_LONG).show();
                }else {
                    error4 = false;
                }
                if (error1 == false && error2 == false && error3 == false && error4 == false){
                    int CheckPicture;
                    CheckPicture = Picture_Count[1] + Picture_Count[2] + Picture_Count[3] + Picture_Count[4];
                    if (CheckPicture < 4) {
                        Toast.makeText(getApplicationContext(), "You have selected only " + CheckPicture + " Image(s); Select 4 Images", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Click the confirm button which will appear once the upload is finished", Toast.LENGTH_LONG).show();
                        referenceSellerUpload = FirebaseStorage.getInstance().getReference("Images").child(Branch).child(Sem).child(Subject).child(Book).child(Username);
                        if (uploadTask != null && uploadTask.isInProgress()) {
                            Toast.makeText(getApplicationContext(), "Upload Once Done!", Toast.LENGTH_LONG).show();
                        } else {
                            fileuploader();
                        }
                    }
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*To Upload text fields into Realtime database*/
                /*To Create object of getter setter class*/
                objsellerupload = new SellerUploadInfo();
                getdownloadimageurl();

            }
        });
    }

    public void filechooser1(){
        Intent picture1 = new Intent();
        picture1.setType("image/*");
        picture1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(picture1,1);
    }

    public void filechooser2(){
        Intent picture2 = new Intent();
        picture2.setType("image/*");
        picture2.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(picture2,2);
    }

    public void filechooser3(){
        Intent picture3 = new Intent();
        picture3.setType("image/*");
        picture3.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(picture3,3);
    }

    public void filechooser4(){
        Intent picture4 = new Intent();
        picture4.setType("image/*");
        picture4.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(picture4,4);
    }

    /*Getter to get the File Extensions*/
    private String getExtensions(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void getdownloadimageurl(){
                DatabaseReference referenceSellerUploadtext;
                referenceSellerUploadtext = FirebaseDatabase.getInstance().getReference("Seller").child(Branch).child(Sem).child(Subject).child(Book);
                objsellerupload.setUsername(Username);
                objsellerupload.setBookname(Book);
                objsellerupload.setMrp(Mrp);
                objsellerupload.setPrice(Price);
                objsellerupload.setCondition(Condition);
                /*Log.d("Download URL","Download Url Imageid 1 : " + downloadimage1);
                Log.d("Download URL","Download Url Imageid 2 : " + downloadimage2);
                Log.d("Download URL","Download Url Imageid 3 : " + downloadimage3);
                Log.d("Download URL","Download Url Imageid 4 : " + downloadimage4);*/
                objsellerupload.setImageid1(downloadimage1);
                objsellerupload.setImageid2(downloadimage2);
                objsellerupload.setImageid3(downloadimage3);
                objsellerupload.setImageid4(downloadimage4);
                referenceSellerUploadtext.child(Username).setValue(objsellerupload);
                //
                /*To Enter information into SellerList in Database*/
                SellerListGetterSetter objsellerlistgettersetter = new SellerListGetterSetter();
                DatabaseReference referenceSellerUploadlist;
                referenceSellerUploadlist = FirebaseDatabase.getInstance().getReference("SellerList").child(Username);
                objsellerlistgettersetter.setUsername(Username);
                objsellerlistgettersetter.setBranch(Branch);
                objsellerlistgettersetter.setSem(Sem);
                objsellerlistgettersetter.setSubject(Subject);
                objsellerlistgettersetter.setName(Book);
                objsellerlistgettersetter.setCondition(Condition);
                objsellerlistgettersetter.setMrp(Mrp);
                objsellerlistgettersetter.setPrice(Price);
                objsellerlistgettersetter.setImageid(downloadimage1);
                referenceSellerUploadlist.child(Book).setValue(objsellerlistgettersetter);

                /*To move to uploadfinish activity if no errors are occured*/
                Intent i19 = new Intent(SellerUpload.this, SellerUploadFinish.class);
                i19.putExtra("username_key6",Username);
                i19.putExtra("book_key2",Book);
                i19.putExtra("subject_key4",Subject);
                i19.putExtra("branch_key5",Branch);
                i19.putExtra("sem_key5",Sem);
                i19.putExtra("mrp_key1",Mrp);
                i19.putExtra("price_key1",Price);
                i19.putExtra("condition_key1",Condition);
                i19.putExtra("username_key6",Username);
                startActivity(i19);
                finish();
    }


    public void fileuploader(){
        imageid1 = System.currentTimeMillis()+"."+getExtensions(Picture1_Uri);
        imageid2 = System.currentTimeMillis()+"."+getExtensions(Picture2_Uri);
        imageid3 = System.currentTimeMillis()+"."+getExtensions(Picture3_Uri);
        imageid4 = System.currentTimeMillis()+"."+getExtensions(Picture4_Uri);
        //To set Uploadcount image to 0
        Uploadcount = 0;

        referenceSellerUpload1 = referenceSellerUpload.child(imageid1);
        referenceSellerUpload2 = referenceSellerUpload.child(imageid2);
        referenceSellerUpload3 = referenceSellerUpload.child(imageid3);
        referenceSellerUpload4 = referenceSellerUpload.child(imageid4);
        uploadTask = referenceSellerUpload1.putFile(Picture1_Uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        referenceSellerUpload1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadimage1 = uri.toString();
                                Uploadcount++;
                                if (Uploadcount==4){
                                    confirm.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Toast.makeText(getApplicationContext(),"Successfully uploaded Image 1", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        referenceSellerUpload2.putFile(Picture2_Uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        referenceSellerUpload2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadimage2 = uri.toString();
                                Uploadcount++;
                                if (Uploadcount==4){
                                    confirm.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Toast.makeText(getApplicationContext(),"Successfully uploaded Image 2", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        referenceSellerUpload3.putFile(Picture3_Uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        referenceSellerUpload3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadimage3 = uri.toString();
                                Uploadcount++;
                                if (Uploadcount==4){
                                    confirm.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Toast.makeText(getApplicationContext(),"Successfully uploaded Image 3", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        referenceSellerUpload4.putFile(Picture4_Uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        referenceSellerUpload4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadimage4 = uri.toString();
                                Uploadcount++;
                                if (Uploadcount==4){
                                    confirm.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        Toast.makeText(getApplicationContext(),"Successfully uploaded Image 4", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Picture1_Uri = data.getData();
            first.setImageURI(Picture1_Uri);
            Picture_Count[requestCode] = 1;
        }
        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Picture2_Uri = data.getData();
            second.setImageURI(Picture2_Uri);
            Picture_Count[requestCode] = 1;
        }
        if(requestCode==3 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Picture3_Uri = data.getData();
            third.setImageURI(Picture3_Uri);
            Picture_Count[requestCode] = 1;
        }
        if(requestCode==4 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Picture4_Uri = data.getData();
            fourth.setImageURI(Picture4_Uri);
            Picture_Count[requestCode] = 1;
        }
    }
}
