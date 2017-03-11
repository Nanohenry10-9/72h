package com.a72h.a72h;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public class MeetingActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng met1 = new LatLng(49.500308, 6.113629);
        mMap.addMarker(new MarkerOptions().position(met1).title("Marker in Evacuation Point 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(met1));

        LatLng met2 = new LatLng(49.800608, 6.113629);
        mMap.addMarker(new MarkerOptions().position(met2).title("Marker in Evacuation Point 2"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(met2));

        LatLng met3 = new LatLng(49.700408, 6.113629);
        mMap.addMarker(new MarkerOptions().position(met3).title("Marker in Evacuation Point 3"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(met3));
    }
}
