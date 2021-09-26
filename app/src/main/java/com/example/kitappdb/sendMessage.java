package com.example.kitappdb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class sendMessage extends AppCompatActivity {

    Button btn_send;
    EditText et_mesage;
    Intent intent;

    String cusID;
    String sellerID;
    String msg;
    Date date;
    String bookName;

    DatabaseReference ref;

    MessageDB dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        //getting values from intent

        intent = getIntent();

        cusID = intent.getStringExtra("userID");
        sellerID = intent.getStringExtra("sellerID");
        bookName = intent.getStringExtra("bookName");

        //initializing ui components

        btn_send = findViewById(R.id.RET_BTN_SEND);
        et_mesage = findViewById(R.id.RET_ET_MESSAGE);

        //initializing db connection

        ref = FirebaseDatabase.getInstance().getReference("Messages");

        //initializing messageDB

        dataList = new MessageDB();

    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(et_mesage.getText().toString().trim())){

                    Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT).show();

                }
                else{

                    msg = et_mesage.getText().toString();

                    //setting values to messageDB

                    dataList.setSellerID(sellerID);
                    dataList.setCusID(cusID);
                    dataList.setSendDate(getOnlyDate(new Date(System.currentTimeMillis())));
                    dataList.setMessage(msg);
                    dataList.setBookName(bookName);
                    dataList.setReply("Not Replied");
                    dataList.setReplyDate("Not Replied");

                    //setting values in database

                    ref.push().setValue(dataList);

                    //setting a toast for success

                    Toast.makeText(getApplicationContext(), "Message Send  Successfully..!!", Toast.LENGTH_SHORT).show();

                    //Redirecting back

                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }

            }
        });
    }

    public String getOnlyDate(Date date){

        String first = date.toString().substring(4,10);
        String second = date.toString().substring(30,34);

        return first+" "+second;
    }

}