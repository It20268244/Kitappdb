package com.example.kitappdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import kotlin.jvm.internal.MagicApiIntrinsics;

public class Addactivity extends AppCompatActivity {


    EditText bkname,author,publisher,url;
    Button btnadd, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        bkname = findViewById(R.id.txtbookname);
        author = findViewById(R.id.author);
        publisher = findViewById(R.id.bookpublisher);
        url = findViewById(R.id.bkprice);

        btnadd = findViewById(R.id.addbtn);
        btnback = findViewById(R.id.bkbutton);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
                clearControls();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void insertdata(){

        Map<String , Object> map = new HashMap<>();
        map.put("bkname", bkname.getText().toString());
        map.put("author",author.getText().toString());
        map.put("publisher",publisher.getText().toString());
        map.put("url",url.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("bitem").child("bb").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Addactivity.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Addactivity.this, "Error while insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearControls(){
        bkname.setText("");
        author.setText("");
        publisher.setText("");
        url.setText("");
    }
}