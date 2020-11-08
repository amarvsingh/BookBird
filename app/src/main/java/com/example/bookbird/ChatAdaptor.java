package com.example.bookbird;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.MyChatViewHolder> {

    Context ContextChat;
    ArrayList<ChatConstructor> ChatMessages;
    FirebaseAuth AuthChatAdaptor;
    FirebaseUser UserChatAdaptor;

    public String UserChatAdaptorUID;
    private String SenderUID,RecieverUID,Message;
    public ChatAdaptor(Context c, ArrayList<ChatConstructor> p ){
        ContextChat = c;
        ChatMessages = p;
    }
    @NonNull
    @Override
    public MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyChatViewHolder(LayoutInflater.from(ContextChat).inflate(R.layout.cardeview_each_text_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatViewHolder holder, int position) {
        AuthChatAdaptor = FirebaseAuth.getInstance();
        UserChatAdaptor = AuthChatAdaptor.getCurrentUser();
        UserChatAdaptorUID = UserChatAdaptor.getUid();
        SenderUID = ChatMessages.get(position).getSender();
        RecieverUID = ChatMessages.get(position).getReciever();
        Message = ChatMessages.get(position).getMessage();
        Log.d("sender",SenderUID);
        if (UserChatAdaptorUID.equals(SenderUID)){
            holder.cardsender.setVisibility(View.VISIBLE);
            holder.chatsender.setText(Message);
        }
        if (UserChatAdaptorUID.equals(RecieverUID)){
            holder.cardreciever.setVisibility(View.VISIBLE);
            holder.chatreciever.setText(Message);
        }
    }

    @Override
    public int getItemCount() {
        return ChatMessages.size();
    }

    class MyChatViewHolder extends RecyclerView.ViewHolder{

        TextView chatsender,chatreciever;
        CardView cardsender,cardreciever;

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chatsender = (TextView)itemView.findViewById(R.id.chatsender);
            chatreciever = (TextView)itemView.findViewById(R.id.chatreciever);
            cardsender = (CardView)itemView.findViewById(R.id.cardsender);
            cardreciever = (CardView)itemView.findViewById(R.id.cardreciever);
        }
    }
}
