package com.example.kitappdb;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageRecycleViewAndDBService {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
    ArrayList<MessageDB> list = new ArrayList<MessageDB>();
    MessageDB msgInstance;
    viewMessages msgInterface = new viewMessages();

    public void getRecycleViewForAskedByMe(String userID){

        ref.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot d1 : snapshot.getChildren()){

                    if(d1.child("cusID").getValue().toString().equals(userID)){

                        //setting values to msgInstance (instance of MessageDB)

                        msgInstance = new MessageDB(
                                d1.child("sellerID").getValue().toString(),
                                d1.child("cusID").getValue().toString(),
                                d1.child("message").getValue().toString(),
                                d1.child("reply").getValue().toString(),
                                d1.child("sendDate").getValue().toString(),
                                d1.child("replyDate").getValue().toString(),
                                d1.child("bookName").getValue().toString()
                        );


                    }

                    list.add(msgInstance);

                }

                // call recycler view

                msgInterface.initRecyclerViewForAskedByMe();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public void getRecycleViewForAskedFromMe(String userID){

        ref.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot d1 : snapshot.getChildren()){

                    if(d1.child("cusID").getValue().toString().equals(userID)){

                        //setting values to msgInstance (instance of MessageDB)

                        msgInstance = new MessageDB(
                                d1.child("sellerID").getValue().toString(),
                                d1.child("cusID").getValue().toString(),
                                d1.child("message").getValue().toString(),
                                d1.child("reply").getValue().toString(),
                                d1.child("sendDate").getValue().toString(),
                                d1.child("replyDate").getValue().toString(),
                                d1.child("bookName").getValue().toString()
                        );


                    }

                    list.add(msgInstance);

                }

                // call recycler view

                msgInterface.initRecyclerViewForAskedFromMe();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

}
