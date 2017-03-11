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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //if (mGoogleApiClient == null) {
          //  mGoogleApiClient = new GoogleApiClient.Builder(this)
          //          .addConnectionCallbacks(this)
          //          .addOnConnectionFailedListener(this)
          //          .addApi(LocationServices.API)
           //         .build();
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

        LatLng lux = new LatLng(49.600508, 6.113629);
        mMap.addMarker(new MarkerOptions().position(lux).title("Marker in Lux"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lux));

        /*

        Just in case this works

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();
        LatLng lux = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(lux).title("Marker at your location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lux));
        */

        // Add a marker in Sydney and move the camera
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();
       // LatLng lux = new LatLng(latitude, longitude);
        //System.out.println(latitude);
        //System.out.println(longitude);
        //Log.d("Latitude: ", String.valueOf(latitude));
        //Log.d("Longitude: ", String.valueOf(longitude));
        mMap.addMarker(new MarkerOptions().position(lux).title("Marker at your location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lux));

       // Location mLastLocation = Location.FusedLocationApi.getLastLocation(
               // mGoogleApiClient);
          //  mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
          //  mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));


    /*    public class MainActivity extends ActionBarActivity implements
                ConnectionCallbacks, OnConnectionFailedListener {
    ...
            @Override
            public void onConnected(Bundle connectionHint) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {
                    mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                    mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                }
            }*/
        }



    }


