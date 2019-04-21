package com.example.android.fcisactivities.Network;


import com.example.android.fcisactivities.Model.FcisActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiBuilder {

    final static String BASE_URL="https://script.googleusercontent.com/macros/echo?user_content_key=24YdTdAQiOrsMEpa6H8tP_VNWL5FPYFyv1O5tE-DE-_U9K88PLe0I9DpGLT8kLG10PRNYbKkMkTl8twCtBUMXuiU5cKOvi3JOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUVPxrLlGT-lMOTdNMFwVSc_aax-sRBnELnvupCLNBYjsegw2fMRHLhcnc4gaZBoxI5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
    public  static ArrayList<FcisActivity>activities;
        public static URL buildUrl( )
        {
            URL url=null;
            try {
                url=new URL(BASE_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<FcisActivity>   extractFromJson(String ActivityJson)
    {
        try {
            JSONObject jsonObject=new JSONObject(ActivityJson);
            JSONArray jsonArray=jsonObject.getJSONArray("Sheet1");
            activities=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++)
            {
                FcisActivity currentActivity=new FcisActivity();
                JSONObject currentObject=jsonArray.getJSONObject(i);
                currentActivity.setBackGround(currentObject.getString("backGround"));
                currentActivity.setForeground(currentObject.getString("foreGround"));
                currentActivity.setMission((currentObject.getString("mission")));
                currentActivity.setName(currentObject.getString("activityName"));
                currentActivity.setVision(currentObject.getString("vision"));
                currentActivity.setYear(currentObject.getInt("year"));
               activities.add(currentActivity);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
