package com.example.kitappp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends FirebaseRecyclerAdapter<CartView,CartAdapter.myViewholder> {
    int qua ;
   // float bookp =0 ;
   // float tot =0 ;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartAdapter(@NonNull FirebaseRecyclerOptions<CartView> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, @SuppressLint("RecyclerView") int position, @NonNull CartView model) {

        //Retrieve data to the cart
        holder.bookn.setText(model.getBook_name());
        holder.bookque.setText(String.valueOf(model.getQuantity()));
        holder.bookp.setText(String.valueOf(model.getPrice()));
        //holder.tot.setText(String.valueOf(model.getTotprice()));



        //Delete items from the books
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alert box for Warning
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.bookn.getContext());
                builder.setTitle("Are You Sure??");
                builder.setMessage("You want to Delete this book ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("Cart").child("c6")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.bookn.getContext(), "Deleted From the Cart", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.bookn.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });


        //Increase Quantity of the book
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                qua = qua + 1;

                holder.bookque.setText(String.valueOf(qua));
                FirebaseDatabase.getInstance().getReference().child("Cart").child("c6")
                        .child(getRef(position).getKey()).child("quantity").setValue(qua);


                //Total price Calculation
                //holder.bookp.setText(String.valueOf(bookp));

                // tot = qua * bookp;
                // holder.tot.setText(String.valueOf(tot));



            }
        });


       //Decrease Quantity of the book
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validate the Value
                if (qua <= 0) {
                    Toast.makeText(holder.bookn.getContext(), " Opps!!, Cannot sell zero books", Toast.LENGTH_SHORT).show();
                } else {
                    qua = qua - 1;
                    holder.bookque.setText(String.valueOf(qua));


                    FirebaseDatabase.getInstance().getReference().child("Cart").child("c6")
                            .child(getRef(position).getKey()).child("quantity").setValue(qua);

                   // bookp = Float.parseFloat(String.valueOf(bookp));
                    //Total price Calculation
                    //holder.bookp.setText(String.valueOf(bookp));
                   // tot = qua * bookp;
                   // holder.tot.setText(String.valueOf(tot));


                }
            }
        });




    }







    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_cart , parent,false);
        return new myViewholder  (view);
    }





    public class myViewholder extends RecyclerView.ViewHolder {

        TextView bookn, bookp, tot;
        Button sub, add, delete;
        ImageView bookimg;
        EditText bookque ;


        public myViewholder(@NonNull View itemView) {
            super(itemView);
            bookn = (TextView) itemView.findViewById(R.id.cart_bname);
            bookp = (TextView) itemView.findViewById(R.id.cart_price);
            bookque = (EditText) itemView.findViewById(R.id.quant);
            //tot = (TextView) itemView.findViewById(R.id.cart_btotal);
            sub = (Button) itemView.findViewById(R.id.btn_cart_sub);
            add = (Button) itemView.findViewById(R.id.btn_cart_add);
            delete = (Button) itemView.findViewById(R.id.btn_cart_delete);
            bookimg = (ImageView) itemView.findViewById(R.id.cart_image);
        }


    }

}

