package com.example.bookbird;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InboxAdaptor extends RecyclerView.Adapter<InboxAdaptor.MyInboxViewHolder> {

    Context ContextInbox;
    ArrayList<InboxConstructor> InboxChats;

    public InboxAdaptor(Context c, ArrayList<InboxConstructor> p ) {
        ContextInbox = c;
        InboxChats = p;
    }

    @NonNull
    @Override
    public MyInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInboxViewHolder(LayoutInflater.from(ContextInbox).inflate(R.layout.cardview_inbox,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInboxViewHolder holder, final int position) {
        holder.usernameinbox.setText(InboxChats.get(position).getOtherUsername());
        holder.openchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Otherusername =  InboxChats.get(position).getOtherUsername();
                Intent i51 = new Intent(ContextInbox,ChatBox.class);
                i51.putExtra("otherusername_key_inboxtochatbox",Otherusername);
                ContextInbox.startActivity(i51);
            }
        });
    }

    @Override
    public int getItemCount() {
        return InboxChats.size();
    }

    class MyInboxViewHolder extends RecyclerView.ViewHolder {

        TextView usernameinbox;
        Button openchat;

        public MyInboxViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameinbox = (TextView)itemView.findViewById(R.id.usernameinbox);
            openchat = (Button)itemView.findViewById(R.id.openchatinbox);
        }
    }
}
