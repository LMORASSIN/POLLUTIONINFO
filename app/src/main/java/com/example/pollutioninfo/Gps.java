package com.example.pollutioninfo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public final class Gps implements LocationListener {
    private LocationManager locationManager;
    private Context context;
    public Location location;
    private OnReadyDataListener onReadyListener;
    public static Gps instance;
    private Gps(Context ctxt)
    {
    this.context = ctxt;
    locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);

    }

    public static Gps getInstance(Context ctxt)
    {
        if (instance == null) {
            instance = new Gps(ctxt);
        }
        return instance;
    }
    public boolean isLocationEnabled() {


        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void getLocation() {
        try
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }

    }

    public void setOnReadyListener(OnReadyDataListener onReadyListener) {
        this.onReadyListener = onReadyListener;
    }

    public interface OnReadyDataListener{
        void useLocation(Location location);
    }
    public void stopLocation()
    {
        locationManager.removeUpdates(this);

    }



    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        if(onReadyListener !=null)
        {
            onReadyListener.useLocation(this.location);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {


    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
