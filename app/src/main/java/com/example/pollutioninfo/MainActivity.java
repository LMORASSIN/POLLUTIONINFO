package com.example.pollutioninfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements PollutionLocationFragment.PollutionLocationListener {
    private ImageButton imSearch;
    private Location location;
    private String city;
    private WebServiceReceiver webServiceReceiver;
    private Tools tools;
    private EditText etLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webServiceReceiver = new WebServiceReceiver();
        tools = Tools.getInstance(this);
        etLocation= findViewById(R.id.etLocation);
        registerMyReceiver();
    }


    @Override
    public void onPollutionLocationSearchByName(String city) {
        this.city = city;
        Intent i = new Intent(MainActivity.this, WebService.class);
        i.putExtra("type","byName");
        i.putExtra("city", city);
        startService(i);



    }

    @Override
    public void onPollutionLocationSearchByLocation(Location location) {
        Intent i = new Intent(MainActivity.this, WebService.class);
        i.putExtra("type","byGeo");
        i.putExtra("lat", String.format("%f",location.getLatitude()));
        i.putExtra("lon", String.format("%f",location.getLongitude()));
        startService(i);


    }
    private void registerMyReceiver() {

        try
        {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("receive");
            registerReceiver(webServiceReceiver, intentFilter);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }




    class WebServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String from = intent.getStringExtra("from");
                String response = intent.getStringExtra("response");
                if (!response.equals("")) {

                    PollutionDetailViewModel pollutionDetails = tools.getPollutionValues(response);
                    Fragment newFragment = new PollutionDetailFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fPollutionDetail, newFragment);
                    transaction.commit();
                    PollutionDetailFragment fragment = (PollutionDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fPollutionDetail);
                    fragment.InsertValue(fragment.getView(),pollutionDetails);
                }
                if(from.equals("fromGeo"))
                {
                    etLocation.setText(R.string.tvLocation);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(webServiceReceiver);
    }
}
