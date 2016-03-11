package com.studytrack.app.studytrack_v1.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vadim on 11.02.16.
 */
public class JSONloader {

    public JSONArray fromHTTP(String inputUrl, String... args) {
        try {
            String target = new String(inputUrl);

            if (args.length > 0) {
                target += '?';
            }

            for (String arg: args) {
                target += arg + '&';
            }

            URL url = new URL(target);
            //URL url = new URL("http://finduniv.appspot.com/getUniversities?count=20");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line);

            urlConnection.disconnect();

            return new JSONArray(buffer.toString());
        } catch (IOException e) {
            Log.e("JSON", "Error getting from HTTP!");
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            Log.e("JSON", "Error getting JSONArray!");
            e.printStackTrace();
            return null;
        }
    }
}
