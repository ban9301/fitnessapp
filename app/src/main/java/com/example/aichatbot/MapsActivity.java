package com.example.aichatbot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import com.example.aichatbot.R;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.example.aichatbot.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

private GoogleMap mMap;
private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int height = 100;
        int width = 100;
        int color = Color.rgb(255, 201, 14);
        // Add a marker in Sydney and move the camera
        LatLng marker1 = new LatLng(22.4549218861773, 114.00679040915594);
        LatLng marker2 = new LatLng(22.44343623507649, 114.03077689070942);
        LatLng marker3 = new LatLng(22.442982042475773, 114.02056686678128);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, true);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);
        //LatLng marker2 = new LatLng(-34, 120);
        mMap.addMarker(new MarkerOptions()
                .position(marker1)
                .alpha(1)
                .draggable(false)
                .title("Tin Shui Wai Sports Centre")
                .icon(smallMarkerIcon)
                .snippet("天水圍體育館")
                //.visible(false)
                //.rotation(75)
                .zIndex(1.0f)
                );
        mMap.addMarker(new MarkerOptions()
                        .position(marker2)
                        //透明度
                        .alpha(1)
                        .draggable(false)
                        .title("Yuen Long Jockey Club Town Square")
                        .icon(smallMarkerIcon)
                        .snippet("元朗賽馬會廣場")
                //.visible(false)
                //.rotation(20)
                .zIndex(1.0f)
        );
        mMap.addMarker(new MarkerOptions()
                .position(marker3)
                .alpha(1)
                .draggable(false)
                .title("Yuen Long Stadium")
                .icon(smallMarkerIcon)
                .snippet("天水圍運動場")
                .zIndex(1.0f)
                .icon(smallMarkerIcon)
                //.visible(false)
                //.rotation(75)
                );
        //move camera marker1
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marker1));

        //zoom and move camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1,13f));

        // add marker
       // mMap.addMarker(new MarkerOptions().position(marker2).title("天水圍體育館"));
        //move camera marker2
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marker2));
    }
}

/*import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Implement OnMapReadyCallback.
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        setContentView(R.layout.activity_main);

        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }
}*/

