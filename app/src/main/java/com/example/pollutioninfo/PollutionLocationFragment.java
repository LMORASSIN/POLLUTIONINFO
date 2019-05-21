package com.example.pollutioninfo;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class PollutionLocationFragment extends Fragment implements Gps.OnReadyDataListener {
    private PollutionLocationListener pollutionLocationListener;
    private PollutionLocationViewModel mViewModel;
    private ImageButton ibSearch, ibLocalize;
    private Gps gps;
    private final int REQUEST_PERMISSION_USE_LOCATION = 100;

    public static PollutionLocationFragment newInstance() {
        return new PollutionLocationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.pollution_location_fragment, container, false);
        gps = Gps.getInstance(view.getContext());
        ibSearch = view.findViewById(R.id.ibSearch);
        ibSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText etLocation = view.findViewById(R.id.etLocation);
                pollutionLocationListener.onPollutionLocationSearchByName(etLocation.getText().toString());
            }
        });
        ibLocalize = view.findViewById(R.id.ibLocalize);
        ibLocalize.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(v.getContext().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_USE_LOCATION);

                }
                else {
                    gps.setOnReadyListener(PollutionLocationFragment.this);
                    gps.getLocation();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PollutionLocationViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void useLocation(Location location)
    {
        gps.stopLocation();
        pollutionLocationListener.onPollutionLocationSearchByLocation(location);
    }

    public interface PollutionLocationListener
    {
        void onPollutionLocationSearchByName(String city);
        void onPollutionLocationSearchByLocation(Location location);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.pollutionLocationListener = (PollutionLocationListener)context;
    }

}
