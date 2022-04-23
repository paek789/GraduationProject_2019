package com.example.smashing2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Menu1Fragment extends Fragment {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mMainRecyclerView;
    private BoardAdapter mAdapter;
    private List<Board> mBoardList;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);

        mMainRecyclerView = v.findViewById(R.id.main_recycler_view);

        mBoardList = new ArrayList<>();



        mStore.collection("board")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                                String id = (String) dc.getDocument().getData().get("id");
                                String level = (String) dc.getDocument().getData().get("level");
                                String name = (String) dc.getDocument().getData().get("name");
                                String type = (String) dc.getDocument().getData().get("sports");
                                String year = (String)dc.getDocument().getData().get("matchingYear");
                                String mon = (String)dc.getDocument().getData().get("matchingMonth");
                                String day = (String)dc.getDocument().getData().get("matchingDate");
                                String sh = (String)dc.getDocument().getData().get("startHour");
                                String sm = (String)dc.getDocument().getData().get("startMin");
                                String eh = (String)dc.getDocument().getData().get("endHour");
                                String em = (String)dc.getDocument().getData().get("endMin");
                                String x = (String)dc.getDocument().getData().get("x");
                                String y = (String)dc.getDocument().getData().get("y");




                            String time = String.format("%s : %s ~ %s : %s",sh,sm,eh,em);
                            String date = String.format("%s - %s - %s",year, mon, day);

                            Board data = new Board(R.drawable.logo_simple,name,type,date,time,x,y,level);

                            mBoardList.add(data);

                        }
                        mAdapter = new BoardAdapter((ArrayList<Board>) mBoardList);
                        mMainRecyclerView.setAdapter(mAdapter);
                    }
                });


        v.findViewById(R.id.main_write_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WriteActivity.class));
            }
        });

        return v;
    }
}
