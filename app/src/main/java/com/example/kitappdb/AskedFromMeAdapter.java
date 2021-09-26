package com.example.kitappdb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AskedFromMeAdapter extends RecyclerView.Adapter<AskedFromMeAdapter.ViewHolder>{

    ArrayList<MessageDB> list;
    Context mContext;

    public AskedFromMeAdapter() {
    }

    public AskedFromMeAdapter(ArrayList<MessageDB> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asked_from_me,parent,false);

        ViewHolder vh  = new ViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //setting values to single items in the list

        holder.view_viewMsg.setText(list.get(position).getMessage());
        holder.view_date.setText(list.get(position).getReplyDate());
        holder.view_title.setText(list.get(position).getBookName());

        //disabling button if replied

        if(!list.get(position).getReply().equals("Not Replied")) {
            holder.btn_send_reply.setVisibility(View.GONE);
            holder.et_reply.setFocusable(false);
            holder.et_reply.setText(list.get(position).getReply());
        }

        //responding to the click event
        //by using this the full conversation will be displayed

        holder.btn_send_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // implement update [reply]

                //getting values from edittext

                String reply = holder.et_reply.getText().toString();

                //setting values to database

                UpdateMessage um = new UpdateMessage();
                um.updateMessage(list.get(position).childName,reply);

                //setting a toast for success

                Toast.makeText(mContext, "Message Send Successfully..!!!", Toast.LENGTH_SHORT).show();

                //reloading view messges

                Intent intent = new Intent(mContext,viewMessages.class);
                intent.putExtra("userID",list.get(position).getCusID());
                mContext.startActivity(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView view_title;
        TextView view_viewMsg;
        TextView view_date;
        CircleImageView image;
        ConstraintLayout parent_layout;
        Button btn_send_reply;
        EditText et_reply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_title = itemView.findViewById(R.id.FROM_ME_TV_TITLE);
            view_date = itemView.findViewById(R.id.FROM_ME_TV_DATE);
            view_viewMsg = itemView.findViewById(R.id.FROM_ME_TV_MESSAGE);
            image = itemView.findViewById(R.id.FROM_ME_IMAGE1);
            parent_layout =itemView.findViewById(R.id.FROM_ME_PARENT_LAYOUT);
            btn_send_reply = itemView.findViewById(R.id.FROM_ME_BTN_UPDATE);
            et_reply = itemView.findViewById(R.id.FROM_ME_ET_REPLY);
        }
    }

    public void clearAdapter(){
        if(list.size()!=0){
            list.clear(); //clear Adapter
            notifyDataSetChanged(); //notify adapter

        }
    }

}
