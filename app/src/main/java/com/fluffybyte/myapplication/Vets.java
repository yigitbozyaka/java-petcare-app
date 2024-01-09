package com.fluffybyte.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

public class    Vets extends AppCompatActivity implements OnMapReadyCallback, NearbySearch.OnNearbySearchListener {
    public static Context mContext;
    private MapView mapView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vets);

        mContext = this;

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mapView = findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);

            new NearbySearch().run(Vets.this, this);

        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }


    public void onNearbySearchResult(PlacesSearchResponse placesSearchResponse) {
        PlacesSearchResult[] placesSearchResults = placesSearchResponse.results;
        if (placesSearchResults.length >= 2) {
            Log.e("response1Tag", placesSearchResults[0].toString());
            Log.e("response2Tag", placesSearchResults[1].toString());

            double lat1 = placesSearchResults[0].geometry.location.lat;
            double lng1 = placesSearchResults[0].geometry.location.lng;

            double lat2 = placesSearchResults[1].geometry.location.lat;
            double lng2 = placesSearchResults[1].geometry.location.lng;

            mMap.addMarker(new MarkerOptions().position(new LatLng(lat1, lng1)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat2, lng2)));

            mMap.setMinZoomPreference(14.0f);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat1, lng1)));
        } else {
            Log.e("responseTag", "No results found");
        }
    }

    public void handleHome(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void handleVaccines(View view) {
        if (!isFinishing()) {
            Intent intent = new Intent(this, Vaccines.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }
}