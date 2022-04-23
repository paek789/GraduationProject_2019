package com.example.smashing2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<Friend> mFriend;
    String stEmail;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvEmail;
        ImageView ivUser;
        public LinearLayout goChat;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            this.ivUser = (ImageView)itemView.findViewById(R.id.ivUser);
            this.goChat = (LinearLayout)itemView.findViewById(R.id.goChat);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FriendAdapter(List<Friend> mFriend, Context context) {
        this.mFriend = mFriend;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_friend, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.tvEmail.setText(mFriend.get(position).getEmail());
//        Picasso.with(context)
//                .load(mFriend.get(position).getPhoto())
//                .fit()
//                .centerInside()
//                .into(holder.ivUser);
        Glide.with(holder.itemView)
                .load(mFriend.get(position).getPhoto())
                .into(holder.ivUser);
        holder.tvEmail.setText(mFriend.get(position).getEmail());
        holder.goChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stFriendid = mFriend.get(position).getKey();
                Intent in =  new Intent(context, ChatActivity.class);
                in.putExtra("friendUid", stFriendid);
                context.startActivity(in);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFriend.size();
    }
}


