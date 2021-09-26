package com.example.kitappdb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class UpdateMessage {

    public void updateMessage(String msgID, String reply){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages").child(msgID);
        ref.child("reply").setValue(reply);
        ref.child("replyDate").setValue(dateTimeToDate(new Date(System.currentTimeMillis())));

    }

    public String dateTimeToDate(Date currentDate){

        String first = currentDate.toString().substring(4,10);
        String second = currentDate.toString().substring(30,34);

        return first + " "+second;

    }

}
