package com.example.pollutioninfo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService extends IntentService {
    private static String type;
    private static final String threadName = "WebService";
    private static final String ROOT_URL = "https://api.waqi.info/feed/";
    private static final String TOKEN = "7f739bba668f5c36177948cf0b1f7f348d251eed";


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public WebService() {
        super(threadName);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        type = intent.getStringExtra("type");

        if(type.equals("byName"))
        {

            getPollutionFromName(intent);
        }
        if(type.equals("byGeo"))
        {
            getPollutionFromGeo(intent);
        }

    }

    private void getPollutionFromName(Intent intent)
    {

        HttpURLConnection connexion = null;
        String response = "";
        try {
            String city = intent.getStringExtra("city");
            URL url = new URL(ROOT_URL + city + "/?token=" + TOKEN);
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            connexion.setDoInput(true);
            InputStream in = connexion.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            in.close();

        } catch (Exception ex) {

        } finally {
            connexion.disconnect();
        }
        requestEnded(response,"fromCity");


    }
    private void getPollutionFromGeo(Intent intent)
    {

        HttpURLConnection connexion = null;
        String response = "";
        try {
            String lat = intent.getStringExtra("lat");
            String lon = intent.getStringExtra("lon");
            URL url = new URL(ROOT_URL + "geo:" + lat + ";" + lon +  "/?token=" + TOKEN);
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            connexion.setDoInput(true);
            InputStream in = connexion.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            in.close();

        } catch (Exception ex) {

        } finally {
            connexion.disconnect();
        }
        requestEnded(response,"fromGeo");


    }
    private void requestEnded(String response, String from)
    {
        try
        {
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction("receive");
            broadCastIntent.putExtra("response",response);
            broadCastIntent.putExtra("from", from);
            sendBroadcast(broadCastIntent);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
