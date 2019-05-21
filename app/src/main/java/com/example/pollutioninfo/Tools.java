package com.example.pollutioninfo;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Tools {
    private Context context;

    private Tools(Context context){
        this.context = context;

    }

    public static Tools getInstance(Context context)
    {
        Tools instance = new Tools(context);
        return instance;
    }

    public PollutionDetailViewModel getPollutionValues(String response)
    {
        PollutionDetailViewModel pollutionDetails = new PollutionDetailViewModel();
        if (!response.equals(""))
        {
            try {
                JSONObject responseJson = new JSONObject(response);
                if(responseJson.get("status").equals("ok"))
                {
                    String aqi = "";
                    String pm10 = "";
                    String pm25 ="";
                    String dominentpol = "";

                    JSONObject data = (JSONObject)responseJson.get("data");
                    aqi = data.get("aqi").toString();
                    dominentpol = data.getString("dominentpol");
                    JSONObject iaqi = (JSONObject)data.get("iaqi");

                    if(iaqi.has("pm10"))
                    {
                        JSONObject pm10Json = (JSONObject) iaqi.get("pm10");
                        pm10 = pm10Json.get("v").toString();
                    }
                    if(iaqi.has("pm25"))
                    {
                        JSONObject pm25Json = (JSONObject) iaqi.get("pm25");
                        pm25 = pm25Json.get("v").toString();
                    }


                    pollutionDetails.setAqi(aqi);
                    pollutionDetails.setDominentpol(dominentpol);
                    pollutionDetails.setPm10(pm10);
                    pollutionDetails.setPm25(pm25);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return pollutionDetails;
    }

}
