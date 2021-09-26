package com.example.kitappdb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AskedByMeAdapter extends RecyclerView.Adapter<AskedByMeAdapter.ViewHolder>{

    public AskedByMeAdapter(ArrayList<MessageDB> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    ArrayList<MessageDB> list;
    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asked_by_me,parent,false);

        ViewHolder vh  = new ViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //setting values to single items in the list

        holder.view_viewMsg.setText(list.get(position).getMessage());
        holder.view_date.setText(list.get(position).getSendDate());
        holder.view_title.setText(list.get(position).getBookName());
        holder.view_reply.setText(list.get(position).getReply());

        //responding to the click event
        //by using this the full conversation will be displayed

        holder.btn_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // implement delete
                DeleteMessage dm = new DeleteMessage();
                dm.deleteMessage(list.get(position).getChildName());
                Toast.makeText(mContext, "The Delete Successfull..!!", Toast.LENGTH_SHORT).show();

                //ridirecting back to aksed by me
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
        TextView view_reply;
        TextView view_date;
        CircleImageView image;
        ConstraintLayout parent_layout;
        Button btn_delet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_title = itemView.findViewById(R.id.BY_ME_TV_TITLE);
            view_date = itemView.findViewById(R.id.BY_ME_TV_DATE);
            view_viewMsg = itemView.findViewById(R.id.BY_ME_TV_MESSAGE);
            view_reply = itemView.findViewById(R.id.BY_ME_TV_REPLY);
            image = itemView.findViewById(R.id.BY_ME_IMAGE1);
            parent_layout =itemView.findViewById(R.id.BY_ME_PARENT_LAYOUT);
            btn_delet = itemView.findViewById(R.id.BY_ME_BTN_DELETE);

        }
    }

    public void clearAdapter(){
        if(list.size()!=0){
            list.clear(); //clear Adapter
            notifyDataSetChanged(); //notify adapter
        }
    }

}
