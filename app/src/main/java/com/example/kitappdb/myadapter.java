package com.example.kitappdb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<Book , myadapter.myViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myadapter(@NonNull FirebaseRecyclerOptions<Book> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, final int position , @NonNull Book model) {
                holder.bookname.setText(model.getBook_name());
                holder.author.setText(model.getAuthor());


       // Picasso.get().load(model.getImageURL()).into(holder.imagebk);
        Glide.with(holder.imagebk.getContext()).load(model.getImageURL()).placeholder(R.drawable.index)
                .circleCrop().error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imagebk);
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.bookname.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1800)
                        .create();

                View view1 = dialogPlus.getHolderView();
                EditText bname = view1.findViewById(R.id.txtbookname);
                EditText bauthor = view1.findViewById(R.id.author);
                EditText bpublisher = view1.findViewById(R.id.bookpublisher);
                //EditText bprice = view1.findViewById(R.id.bprice);
                //EditText bquantity = view1.findViewById(R.id.bkquantityy);
                EditText bdescription = view1.findViewById(R.id.bkdescription);

                //save updated details
                Button btnupdate = view1.findViewById(R.id.btnupdate);

                bname.setText(model.getBook_name());
                bauthor.setText(model.getAuthor());
                bpublisher.setText(model.getPublisher());
                //bprice.setText(String.valueOf(model.getPrice()));
               // bquantity.setText(model.getQuantity());
                bdescription.setText(model.getDescription());



                dialogPlus.show();
                btnupdate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("book_name", bname.getText().toString());
                        map.put("author", bauthor.getText().toString());
                        map.put("publisher", bpublisher.getText().toString());
                       //map.put("description",bdescription.getText().toString());
                     // map.put("price",bprice.getText().toString());
                     //  map.put("quantity",bquantity.getText().toString());




                        FirebaseDatabase.getInstance().getReference().child("bitem").child("bb").child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.bookname.getContext() , "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });}});


                }});


                        holder.btndelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(holder.bookname.getContext());
                                builder.setTitle("Are you sure");
                                builder.setMessage("deleted data cannot be undone");

                                builder.setPositiveButton("Delte", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseDatabase.getInstance().getReference().child("bitem").child("bb").child(getRef(position).getKey())
                                                .removeValue();
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(holder.bookname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                            }
                        });






            }



    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends  RecyclerView.ViewHolder{
        TextView bookname , author ;
        Button btnedit,btndelete;
        CircleImageView imagebk;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            bookname = itemView.findViewById(R.id.bkname);
            author = itemView.findViewById(R.id.bkauthor);
            btnedit = (Button) itemView.findViewById(R.id.updatebut);
            btndelete = (Button) itemView.findViewById(R.id.deletebutt);
            imagebk =(CircleImageView) itemView.findViewById(R.id.img);

        }
    }

}
