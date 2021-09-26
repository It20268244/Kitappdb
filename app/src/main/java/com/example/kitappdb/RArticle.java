package com.example.addarticle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

/*public class RArticle extends AppCompatActivity {
    RecyclerView recyclerView ;
    MainAdapter mainAdapter;

    //add article button
    //FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        recyclerView = (RecyclerView)findViewById(R.id.ttt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*floatingActionButton =(FloatingActionButton)findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));//add article check
            }
        });*/
       /* FirebaseRecyclerOptions<article> options=
                new FirebaseRecyclerOptions.Builder<article>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("articles"),article.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

    }

    public RArticle() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str){
        FirebaseRecyclerOptions<article> options=
                new FirebaseRecyclerOptions.Builder<article>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("articles").orderByChild("articleName").startAt(str).endAt(str+"~"),article.class)
                        .build();
        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }



  /*  protected void onResume() {
        super.onResume();
        val Category = getResources().getStringArray(R.id.et_articleType)
        val arrayAdapter = arrayAdapter(requireContext(),R.layout.dropdown,Category)
        binding.autoCompleteTextView.setAdapter(ArrayAdapter)
    }
}*/