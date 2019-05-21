package com.example.pollutioninfo;

import android.arch.lifecycle.ViewModel;

public class PollutionLocationViewModel extends ViewModel {
   private String location;
   public PollutionLocationViewModel()
   {
       location = "";

   }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
