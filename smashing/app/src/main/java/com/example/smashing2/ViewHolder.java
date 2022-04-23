package com.example.smashing2;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView userName;
    TextView sport;
    TextView date;
    TextView time;
    TextView loc;
    TextView level;


    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.cardImage);
        userName = itemView.findViewById(R.id.cardName);
        sport = itemView.findViewById(R.id.cardType);
        date = itemView.findViewById(R.id.cardDate);
        time = itemView.findViewById(R.id.cardTime);
        loc = itemView.findViewById(R.id.cardLoc);
        level = itemView.findViewById(R.id.cardLevel);

    }
}
