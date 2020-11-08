package com.example.bookbird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class BuyerBookSelection extends AppCompatActivity {

    /*To Declare Buttons and Fields*/
    private Button book1,book2,book3,book4,next;
    private TextView mapping;
    private String Book1,Book2,Book3,Book4,Branch,Sem,Subject,Username,BookChosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_book_selection);

        //To fetch the Strings from previous activity
        Intent i30 = getIntent();
        Username = i30.getStringExtra("username_buyerkey3");
        Branch = i30.getStringExtra("branch_buyerkey2");
        Sem = i30.getStringExtra("sem_buyerkey2");
        Subject = i30.getStringExtra("subject_buyerkey1");
        Log.d("Buyer Username", "Buyer Username Book Sel"+Username);

        /*To Map the Buttons and Fields with the xml file*/
        book1 = (Button) findViewById(R.id.book1);
        book2 = (Button) findViewById(R.id.book2);
        book3 = (Button) findViewById(R.id.book3);
        book4 = (Button) findViewById(R.id.book4);
        next = (Button) findViewById(R.id.next);
        mapping = (TextView)findViewById(R.id.mappingbuyerbook);

        //To set the text of the mapping
        mapping.setText(Branch+"->"+Sem+"->"+Subject);

        BookChosen = "";

        /*To Retrieve Book names from the Database*/
        Book4 = "Not Available";
        DatabaseReference referencebuyerbookseletion;
        referencebuyerbookseletion = FirebaseDatabase.getInstance().getReference("Books").child(Branch).child(Sem).child(Subject);
        referencebuyerbookseletion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book1 = dataSnapshot.child("Book 1").getValue().toString().trim();
                Book2 = dataSnapshot.child("Book 2").getValue().toString().trim();
                Book3 = dataSnapshot.child("Book 3").getValue().toString().trim();
                Book4 = dataSnapshot.child("Book 4").getValue().toString().trim();
                book1.setText(Book1);
                book2.setText(Book2);
                book3.setText(Book3);
                book4.setText(Book4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Book not found", Toast.LENGTH_LONG).show();
            }
        });

        //To implement the changing button color effect
        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                book2.setBackgroundColor(getResources().getColor(R.color.hover1));
                book3.setBackgroundColor(getResources().getColor(R.color.hover1));
                book4.setBackgroundColor(getResources().getColor(R.color.hover1));
                BookChosen = new String(Book1);
            }
        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                book1.setBackgroundColor(getResources().getColor(R.color.hover1));
                book3.setBackgroundColor(getResources().getColor(R.color.hover1));
                book4.setBackgroundColor(getResources().getColor(R.color.hover1));
                BookChosen = new String(Book2);
            }
        });

        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                book2.setBackgroundColor(getResources().getColor(R.color.hover1));
                book1.setBackgroundColor(getResources().getColor(R.color.hover1));
                book4.setBackgroundColor(getResources().getColor(R.color.hover1));
                BookChosen = new String(Book3);
            }
        });

        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookChosen = "";
                if (Book4.equals("Not Available")) {
                    book4.setBackgroundColor(getResources().getColor(R.color.hover1));
                    book2.setBackgroundColor(getResources().getColor(R.color.hover1));
                    book1.setBackgroundColor(getResources().getColor(R.color.hover1));
                    book3.setBackgroundColor(getResources().getColor(R.color.hover1));
                    Toast.makeText(getApplicationContext(), "Book not found", Toast.LENGTH_LONG).show();
                } else {
                    book4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    book2.setBackgroundColor(getResources().getColor(R.color.hover1));
                    book1.setBackgroundColor(getResources().getColor(R.color.hover1));
                    book3.setBackgroundColor(getResources().getColor(R.color.hover1));
                    BookChosen = new String(Book4);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BookChosen.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select a Book", Toast.LENGTH_LONG).show();
                } else {
                    Intent i31 = new Intent(BuyerBookSelection.this, BuyerBookResults.class);
                    i31.putExtra("username_buyerkey4", Username);
                    i31.putExtra("branch_buyerkey3", Branch);
                    i31.putExtra("sem_buyerkey3", Sem);
                    i31.putExtra("subject_buyerkey2", Subject);
                    i31.putExtra("book_buyerkey1", BookChosen);
                    startActivity(i31);
                }
            }
        });
    }
}
