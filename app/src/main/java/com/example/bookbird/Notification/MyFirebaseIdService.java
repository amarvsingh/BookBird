package com.example.bookbird.Notification;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String RefreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (firebaseUser != null){
            updateToken(RefreshedToken);
        }
    }

    private void updateToken(String refreshedToken) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference referenceNotification1 = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token = new Token(refreshedToken);
        referenceNotification1.child(firebaseUser.getUid()).setValue(token);
    }
}
