package com.example.kitappdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class bookl extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    myadapter myAdapter;
    ArrayList<Book> list;
    FloatingActionButton floatingActionbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookl);

        recyclerView = (RecyclerView) findViewById(R.id.asd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        floatingActionbutton =findViewById(R.id.floatingActionButton);
        floatingActionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("bitem").child("bb"), Book.class)
                        .build();

        myAdapter = new myadapter(options);
        recyclerView.setAdapter(myAdapter);

        /*list = new ArrayList<>();
        myAdapter = new adapter(this,list);
        recyclerView.setAdapter(myAdapter);


        //storageReference = FirebaseStorage.getInstance().getReference();




        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Book book = dataSnapshot.getValue(Book.class);
                    list.add(book);


                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/




    }



    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch((query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<Book> options=
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("bitem").child("bb").orderByChild("book_name").startAt(str).endAt(str+"~"),Book.class)
                        .build();
        myAdapter = new myadapter(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);
    }





}