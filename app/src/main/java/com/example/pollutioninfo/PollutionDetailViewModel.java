package com.example.pollutioninfo;

import android.arch.lifecycle.ViewModel;

public class PollutionDetailViewModel extends ViewModel {
    private String aqi, pm25,pm10,dominentpol;
    public PollutionDetailViewModel()
    {
        aqi = "";
        pm25 = "";
        pm10 = "";
        dominentpol = "";

    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getDominentpol() {
        return dominentpol;
    }

    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }
}
