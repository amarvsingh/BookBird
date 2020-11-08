package com.example.bookbird;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdaptorWishlist extends RecyclerView.Adapter<RecyclerAdaptorWishlist.MyHolderWishlit> {

    Context ContextWishlist;
    ArrayList<WishlistConstructor> UploadsWishlist;

    public RecyclerAdaptorWishlist(Context c, ArrayList<WishlistConstructor> p){
        ContextWishlist = c;
        UploadsWishlist = p;
    }

    @NonNull
    @Override
    public MyHolderWishlit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolderWishlit(LayoutInflater.from(ContextWishlist).inflate(R.layout.cardview_wishlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderWishlit holder, final int position) {
        holder.bookname.setText(UploadsWishlist.get(position).getBookname());
        holder.branch.setText("Branch: "+UploadsWishlist.get(position).getBranch());
        holder.subject.setText("Subject: "+UploadsWishlist.get(position).getSubject());
        holder.price.setText("Price: "+UploadsWishlist.get(position).getPrice());
        Picasso.get().load(UploadsWishlist.get(position).getImageid()).into(holder.wishlistimage);

        //To remove the book from the wishlist
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference referenceRemoveWishlist;
                referenceRemoveWishlist = FirebaseDatabase.getInstance().getReference("Wishlist").child(UploadsWishlist.get(position).getUsername()).child(UploadsWishlist.get(position).getBookname());
                referenceRemoveWishlist.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ContextWishlist,"The Book will soon be removed from the Wishlist, Reopen your Wishlist to check the change",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ContextWishlist,"Unable to process your request at the moment",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return UploadsWishlist.size();
    }

    class MyHolderWishlit extends RecyclerView.ViewHolder{

        ImageView wishlistimage;
        TextView bookname,branch,subject,price;
        Button remove;

        public MyHolderWishlit(@NonNull View itemView) {
            super(itemView);
            wishlistimage = (ImageView)itemView.findViewById(R.id.wishlistimage);
            bookname = (TextView)itemView.findViewById(R.id.wishlistbookname);
            branch = (TextView)itemView.findViewById(R.id.wishlistbranchname);
            subject = (TextView)itemView.findViewById(R.id.wishlistsubjectname);
            price = (TextView)itemView.findViewById(R.id.wishlistpricename);
            remove = (Button)itemView.findViewById(R.id.removewishlist);
        }
    }
}
