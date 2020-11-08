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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdaptorUploaded extends RecyclerView.Adapter<RecyclerAdaptorUploaded.MyViewHolder > {
    Context context;
    ArrayList<RecyclerUploadedConstruct> uploads;
    public String Username,Bookname,Sem,Subject,Branch,Condition,Mrp,Price;

    public RecyclerAdaptorUploaded(Context c, ArrayList<RecyclerUploadedConstruct> p){
        context = c;
        uploads = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_uploadedproducts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.bookname.setText(uploads.get(position).getName());
        holder.condition.setText(uploads.get(position).getCondition());
        holder.mrp.setText(uploads.get(position).getMrp());
        holder.price.setText(uploads.get(position).getPrice()+" (INR)");
        holder.branch.setText(uploads.get(position).getBranch());
        holder.sem.setText(uploads.get(position).getSem());
        holder.subject.setText(uploads.get(position).getSubject());
        Picasso.get().load(uploads.get(position).getImageid()).into(holder.imageshown);
        holder.openproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting the string for the selected product
                Username = uploads.get(position).getUsername();
                Bookname = uploads.get(position).getName();
                Branch = uploads.get(position).getBranch();
                Sem = uploads.get(position).getSem();
                Subject = uploads.get(position).getSubject();
                Condition = uploads.get(position).getCondition();
                Mrp = uploads.get(position).getMrp();
                Price = uploads.get(position).getPrice();

                //To send the data of selected product to next activity
                Intent i26 = new Intent(context,EachProductUploaded.class);
                Log.d("Download URL","Bookname: " + Bookname);
                i26.putExtra("username_keyeachproduct",Username);
                i26.putExtra("bookname_keyeachproduct",Bookname);
                i26.putExtra("branch_keyeachproduct",Branch);
                i26.putExtra("sem_keyeachproduct",Sem);
                i26.putExtra("subject_keyeachproduct",Subject);
                i26.putExtra("condition_keyeachproduct",Condition);
                i26.putExtra("mrp_keyeachproduct",Mrp);
                i26.putExtra("price_keyeachproduct",Price);
                context.startActivity(i26);
            }
        });
      }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookname,condition,mrp,price,branch,sem,subject;
        ImageView imageshown;
        Button openproduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname = (TextView)itemView.findViewById(R.id.bookname);
            condition = (TextView)itemView.findViewById(R.id.condition);
            mrp = (TextView)itemView.findViewById(R.id.mrp);
            price = (TextView)itemView.findViewById(R.id.price);
            branch = (TextView)itemView.findViewById(R.id.Branch);
            sem = (TextView)itemView.findViewById(R.id.Semester);
            subject = (TextView)itemView.findViewById(R.id.Subject);
            imageshown = (ImageView)itemView.findViewById(R.id.uploadedimage);
            openproduct = (Button)itemView.findViewById(R.id.openproduct);
        }
    }
}
