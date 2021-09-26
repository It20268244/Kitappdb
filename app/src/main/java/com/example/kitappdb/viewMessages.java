package com.example.kitappdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewMessages extends AppCompatActivity {

    TabLayout TL;
    TabItem TI1;
    TabItem TI2;

    Intent intent;

    String userID;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
    ArrayList<MessageDB> list = new ArrayList<MessageDB>();
    MessageDB msgInstance;
    MessageDB msgInstance_a;

    AskedByMeAdapter adapter;
    AskedFromMeAdapter adapter_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_messages);

        //initializing view objects

        TL = findViewById(R.id.TL1);
        TI1 = findViewById(R.id.TI1);
        TI2 =findViewById(R.id.TI2);

        //getting values for the user ID

        intent = getIntent();
        userID = intent.getStringExtra("userID");

        //getting asked_from_me_details from messages database

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

                        msgInstance.setChildName(d1.getKey());
                        list.add(msgInstance);

                    }

                }

                // call recycler view
                initRecyclerViewForAskedByMe();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        TL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0){

                    //asked by me here

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
                                    msgInstance.setChildName(d1.getKey());
                                    list.add(msgInstance);
                                }


                            }

                            // call recycler view
                            initRecyclerViewForAskedByMe();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    //Toast.makeText(getApplicationContext(), "Aksed by me called", Toast.LENGTH_SHORT).show();


                }
                else{

                    //asked from me here

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot d1 : snapshot.getChildren()){

                                if(d1.child("sellerID").getValue().toString().equals(userID)){

                                    //setting values to msgInstance (instance of MessageDB)

                                    msgInstance_a = new MessageDB(
                                            d1.child("sellerID").getValue().toString(),
                                            d1.child("cusID").getValue().toString(),
                                            d1.child("message").getValue().toString(),
                                            d1.child("reply").getValue().toString(),
                                            d1.child("sendDate").getValue().toString(),
                                            d1.child("replyDate").getValue().toString(),
                                            d1.child("bookName").getValue().toString()
                                    );

                                    msgInstance_a.setChildName(d1.getKey());
                                    list.add(msgInstance_a);

                                }

                            }

                            // call recycler view
                            initRecyclerViewForAskedFromMe();

                            Toast.makeText(getApplicationContext(), "Aksed from me called "+list.size(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    //Toast.makeText(getApplicationContext(), "Aksed from me called", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                if(tab.getPosition()==0)
                    adapter.clearAdapter();
                else
                    adapter_a.clearAdapter();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //setting data to the asked by me recycler view

    public void initRecyclerViewForAskedByMe(){

        //Toast.makeText(getApplicationContext(), "The count is "+list.size(), Toast.LENGTH_SHORT).show();

        displayNoItem();

        RecyclerView recyclerView  = findViewById(R.id.RV1);
        //AskedByMeAdapter adapter = new AskedByMeAdapter(list,this); [old code]
        adapter = new AskedByMeAdapter(list,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //setting data into asked from me recycler view

    public void initRecyclerViewForAskedFromMe(){

        //Toast.makeText(getApplicationContext(), "The count is "+list.size(), Toast.LENGTH_SHORT).show();

        //display toast if there is no Items

        displayNoItem();

        RecyclerView recyclerView  = findViewById(R.id.RV1);
        //AskedFromMeAdapter adapter = new AskedFromMeAdapter(list,this);
        adapter_a = new AskedFromMeAdapter(list,this);
        recyclerView.setAdapter(adapter_a);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //displaying toast if there is no Items

    public void displayNoItem(){

        if(list.size()==0){
            Toast.makeText(getApplicationContext(), "There is no Items", Toast.LENGTH_SHORT).show();
        }

    }

}