package com.example.addarticle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/*public class MainAdapter extends FirebaseRecyclerAdapter<article,MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
  /*  public MainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<article> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder,final int position, @NonNull @NotNull article model) {
        holder.articleName.setText(model.getArticleName());
        holder.publisherName.setText(model.getPublisherName());
        holder.articleType.setText(model.getArticleType());


        //bind that to edit button
        holder.btn_updateArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.articleName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1800)
                        .create();

               // dialogPlus.show();
            View view1 = dialogPlus.getHolderView();

            EditText articleName = view1.findViewById(R.id.et_articleName);
            EditText publisherName = view1.findViewById(R.id.et_publisherName);
            EditText publishDate = view1.findViewById(R.id.et_publishDate);
            EditText articleType = view1.findViewById(R.id.et_articleType);
            EditText article = view1.findViewById(R.id.et_articleWrite);

            Button btn_updateArticle = view1.findViewById(R.id.btn_update);

            articleName.setText(model.getArticleName());
            publisherName.setText(model.getPublisherName());
           //publishDate.setText(model.getPublishDate());
            articleType.setText(model.getArticleType());
            article.setText(model.getArticle());

            dialogPlus.show();

            btn_updateArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("articleName",articleName.getText().toString());
                    map.put("publisherName",publisherName.getText().toString());
                  //  map.put("publishDate",publishDate.getText().toString());
                    map.put("articleType",articleType.getText().toString());
                    map.put("article",article.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("articles")
                            .child(getRef(position).getKey()).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(holder.articleName.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                    //after update disapper the update popup
                                    dialogPlus.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(holder.articleName.getContext(), "Try Again", Toast.LENGTH_SHORT).show();
                                    dialogPlus.dismiss();
                                }
                            });

                }
            });
            }
        });

        holder.btn_deleteArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.articleName.getContext());
                builder.setTitle("Are you sure? ");
                builder.setMessage("");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("articles")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.articleName.getContext(), "Article permanently deleted.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.articleName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //bind the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView articleName , publisherName , articleType;
        Button btn_updateArticle , btn_deleteArticle;
        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            articleName =(TextView)itemView.findViewById(R.id.et_articleName);
            publisherName =(TextView)itemView.findViewById(R.id.et_publisherName);
            articleType = (TextView)itemView.findViewById(R.id.et_articleType);

            btn_updateArticle=(Button)itemView.findViewById(R.id.btn_updateArticle);
            btn_deleteArticle=(Button)itemView.findViewById(R.id.btn_deleteArticle);

        }
    }
}*/
