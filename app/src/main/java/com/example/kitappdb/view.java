package com.example.kitappdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view extends AppCompatActivity {


    TextView  bname , bprice, bdescription;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        bname = findViewById(R.id.bookview);
        bprice = findViewById(R.id.totprice2);
        bdescription = findViewById(R.id.bookdescription);

        show = findViewById(R.id.addbtn);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("item").child("book").child("5");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){

                            bname.setText(snapshot.child("book_name").getValue().toString());
                           bprice.setText(snapshot.child("price").getValue().toString());
                            bdescription.setText(snapshot.child("description").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No data to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}