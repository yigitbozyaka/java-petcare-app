package com.fluffybyte.myapplication;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.RankBy;
import com.google.maps.model.LatLng;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class NearbySearch {

    public interface OnNearbySearchListener {
        void onNearbySearchResult(PlacesSearchResponse placesSearchResponse);
    }

    private FusedLocationProviderClient fusedLocationClient;
    private GeoApiContext context;

    public NearbySearch() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Vets.mContext);

        String apiKey = getKey(Vets.mContext);

        context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String getKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = applicationInfo.metaData;
            Object keyValue = metaData != null ? metaData.get("com.google.android.geo.API_KEY") : null;
            return String.valueOf(keyValue);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void run(Activity activity, final OnNearbySearchListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(Vets.mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(Vets.mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(activity, location -> {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            try {
                                PlacesSearchResponse request = PlacesApi.nearbySearchQuery(context, latLng)
                                        .radius(50000)
                                        .rankby(RankBy.PROMINENCE)
                                        .keyword("veteriner")
                                        .language("tr")
                                        .type(PlaceType.VETERINARY_CARE)
                                        .await();
                                listener.onNearbySearchResult(request);
                            } catch (ApiException | IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
}
