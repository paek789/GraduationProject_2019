package com.example.smashing2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Menu2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu2, container, false);

        ImageView sc = view.findViewById(R.id.img1);
        ImageView bb = view.findViewById(R.id.img2);
        ImageView bm = view.findViewById(R.id.img3);
        ImageView fb = view.findViewById(R.id.img4);
        ImageView tb = view.findViewById(R.id.img5);
        ImageView t = view.findViewById(R.id.img6);

//        sc.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.frame_layout, soccer).commit();
////            }
////        });

        return view;
    }
}
