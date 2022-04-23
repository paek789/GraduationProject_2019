package com.example.smashing2;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<com.example.smashing2.ViewHolder> {

    private ArrayList<Board> myDataList = null;

    BoardAdapter(ArrayList<Board> dataList)
    {
        myDataList = dataList;
    }

    Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_main, parent, false);
        com.example.smashing2.ViewHolder viewHolder = new com.example.smashing2.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.smashing2.ViewHolder holder, final int position) {
        holder.imageView.setImageResource(myDataList.get(position).getImgId());
        holder.userName.setText(myDataList.get(position).getUserName());
        holder.sport.setText(myDataList.get(position).getType());
        holder.date.setText(myDataList.get(position).getDate());
        holder.time.setText(myDataList.get(position).getTime());
        holder.level.setText(myDataList.get(position).getLevel());

        Geocoder geocoder = new Geocoder(context);

        double x = Double.parseDouble(myDataList.get(position).getX());
        double y = Double.parseDouble(myDataList.get(position).getY());
        try {
            List<Address> mResultList = geocoder.getFromLocation(
                    x,
                    y,
                    1
            );

            holder.loc.setText(mResultList.get(0).getAddressLine(0));
        } catch (IOException e) {

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, infoPopup.class);
                intent.putExtra("x",myDataList.get(position).getX());
                intent.putExtra("y",myDataList.get(position).getY());
                intent.putExtra("sport",myDataList.get(position).getType());
                intent.putExtra("level",myDataList.get(position).getLevel());
                intent.putExtra("date",myDataList.get(position).getDate());
                intent.putExtra("time",myDataList.get(position).getTime());
                intent.putExtra("name",myDataList.get(position).getUserName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }
}