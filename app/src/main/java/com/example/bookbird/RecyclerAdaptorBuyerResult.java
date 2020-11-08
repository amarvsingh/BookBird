package com.example.bookbird;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.IntentCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerAdaptorBuyerResult extends RecyclerView.Adapter<RecyclerAdaptorBuyerResult.MyViewHolderBuyerResult>{

    Context ContextBuyerResult;
    ArrayList<RecyclerBuyerResultConstruct> UploadsBuyer;
    public String image1id,image2id,image3id,image4id,Username,Bookname,Condition,Mrp,Price;

    public RecyclerAdaptorBuyerResult(Context c, ArrayList<RecyclerBuyerResultConstruct> p){ ContextBuyerResult = c;
        UploadsBuyer = p;
    }

    @NonNull
    @Override
    public MyViewHolderBuyerResult onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderBuyerResult(LayoutInflater.from(ContextBuyerResult).inflate(R.layout.cardview_buyerbookresults,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderBuyerResult holder, final int position) {

        holder.username.setText(UploadsBuyer.get(position).getUsername());
        holder.bookname.setText(UploadsBuyer.get(position).getBookname());
        holder.condition.setText(UploadsBuyer.get(position).getCondition());
        holder.mrp.setText(UploadsBuyer.get(position).getMrp());
        holder.price.setText(UploadsBuyer.get(position).getPrice()+" (INR)");
        Picasso.get().load(UploadsBuyer.get(position).getImageid1()).into(holder.imageshown);
        holder.openproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1id = UploadsBuyer.get(position).getImageid1();
                image2id = UploadsBuyer.get(position).getImageid2();
                image3id = UploadsBuyer.get(position).getImageid3();
                image4id = UploadsBuyer.get(position).getImageid4();
                Username = UploadsBuyer.get(position).getUsername();
                Bookname = (UploadsBuyer.get(position).getBookname());
                Condition = UploadsBuyer.get(position).getCondition();
                Mrp = UploadsBuyer.get(position).getMrp();
                Price = UploadsBuyer.get(position).getPrice();
                Intent i32 = new Intent(ContextBuyerResult,EachBuyerResultBook.class);
                i32.putExtra("username_eachbookkey",Username);
                i32.putExtra("bookname_eachbookkey",Bookname);
                i32.putExtra("condition_eachbookkey",Condition);
                i32.putExtra("price_eachbookkey",Price);
                //TO send the Imageurl of each book
                i32.putExtra("image1url_eachbookkey",image1id);
                i32.putExtra("image2url_eachbookkey",image2id);
                i32.putExtra("image3url_eachbookkey",image3id);
                i32.putExtra("image4url_eachbookkey",image4id);
                ContextBuyerResult.startActivity(i32);
            }
        });
    }

    @Override
    public int getItemCount() {
        return UploadsBuyer.size();
    }

    class MyViewHolderBuyerResult extends RecyclerView.ViewHolder{

        TextView bookname,condition,mrp,price,username;
        ImageView imageshown;
        Button openproduct;

        public MyViewHolderBuyerResult(@NonNull View itemView) {
            super(itemView);
            bookname = (TextView)itemView.findViewById(R.id.buyerbookname);
            condition = (TextView)itemView.findViewById(R.id.buyercondition);
            mrp = (TextView)itemView.findViewById(R.id.buyermrp);
            price = (TextView)itemView.findViewById(R.id.buyerprice);
            username = (TextView)itemView.findViewById(R.id.buyerusername);
            imageshown = (ImageView)itemView.findViewById(R.id.buyeruploadedimage);
            openproduct = (Button)itemView.findViewById(R.id.buyeropenproduct);
        }
    }
}
