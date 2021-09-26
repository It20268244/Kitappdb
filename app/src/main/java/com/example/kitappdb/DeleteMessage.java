package com.example.kitappdb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteMessage {

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");

    public boolean deleteMessage(String messageid){

        return ref.child(messageid).removeValue().isSuccessful();

    }

}
