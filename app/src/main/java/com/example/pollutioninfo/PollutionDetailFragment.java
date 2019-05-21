package com.example.pollutioninfo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PollutionDetailFragment extends Fragment {

    private PollutionDetailViewModel mViewModel;

    public static PollutionDetailFragment newInstance() {
        return new PollutionDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pollution_detail_fragment, container, false);
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PollutionDetailViewModel.class);
        // TODO: Use the ViewModel
    }
    public void InsertValue(View view, PollutionDetailViewModel pollutionData)
    {
        TextView tvAqi = view.findViewById(R.id.tvAqi);
        TextView tvPm10 = view.findViewById(R.id.tvPm10);
        TextView tvPm25 = view.findViewById(R.id.tvPm25);
        TextView tvDominentpol = view.findViewById(R.id.tvDominentpol);
        tvAqi.setText(pollutionData.getAqi());
        tvDominentpol.setText(pollutionData.getDominentpol());
        tvPm10.setText(pollutionData.getPm10());
        tvPm25.setText(pollutionData.getPm25());
    }


}
