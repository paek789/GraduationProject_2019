package com.example.smashing2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class infoPopup extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_popup);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);


        TextView add = findViewById(R.id.textAdd);
        TextView type = findViewById(R.id.textType);
        TextView lev = findViewById(R.id.textLev);
        TextView date = findViewById(R.id.textDate);
        TextView time = findViewById(R.id.textTime);

        double x = Double.parseDouble(getIntent().getStringExtra("x"));
        double y = Double.parseDouble(getIntent().getStringExtra("y"));

        Geocoder geocoder = new Geocoder(this);

        List<Address> mResultList = null;

        try {
            mResultList = geocoder.getFromLocation(
                    x,
                    y,
                    1
            );

        } catch (IOException e) {

        }


        add.setText(mResultList.get(0).getAddressLine(0));
        type.setText(getIntent().getStringExtra("sport"));
        lev.setText(getIntent().getStringExtra("level"));
        date.setText(getIntent().getStringExtra("date"));
        time.setText(getIntent().getStringExtra("time"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double x = Double.parseDouble(getIntent().getStringExtra("x"));
        double y = Double.parseDouble(getIntent().getStringExtra("y"));

        LatLng add = new LatLng(x, y);

        Geocoder geocoder = new Geocoder(this);

        List<Address> mResultList = null;

        try {
            mResultList = geocoder.getFromLocation(
                    x,
                    y,
                    1
            );

        } catch (IOException e) {

        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(add);
        markerOptions.title("만남 장소");
        markerOptions.snippet(mResultList.get(0).getAddressLine(0));
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(add,15));
    }
}
