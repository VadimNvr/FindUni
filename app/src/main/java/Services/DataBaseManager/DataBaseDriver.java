package Services.DataBaseManager;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Entities.Region;
import Entities.Speciality;
import Entities.Town;
import Entities.University;

public class DataBaseDriver {

    public List<University> loadUniversities(Town town, int count, int offset, AppCompatActivity activity) {
        ArrayList<University> universities = new ArrayList<>();
        String url = "http://finduniv.appspot.com/getUniversities";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("town", town.getName());
        parameters.put("count", Integer.toString(count));
        parameters.put("offset", Integer.toString(offset));
        try {
            JSONArray objects = (JSONArray) JSONParser.readJsonFromUrl(url,parameters);
            for (int i = 0; i < objects.length() ; i++) {
                universities.add(University.initFromJSON((JSONObject) objects.get(i), town, activity));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return universities;
    }

    public List<Speciality> loadSpecialies(String university_name) {
        ArrayList<Speciality> specialities = new ArrayList<>();


        //// TODO: 17.03.2016  implement
        return specialities;
    }

    public List<Town> loadTowns(Region region) {
        String url = "http://finduniv.appspot.com/getTowns";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("region", region.getName());
        List<Town> result = new ArrayList<>();
        try {
            JSONArray objects = (JSONArray) JSONParser.readJsonFromUrl(url,parameters);
            for (int i = 0; i < objects.length() ; i++) {
                result.add(Town.initFromJSON((JSONObject) objects.get(i), region));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Region> loadRegions() {
        String url = "http://finduniv.appspot.com/getRegions";
        HashMap<String, String> parameters = new HashMap<>();
        List<Region> result = new ArrayList<>();
        try {
            JSONArray objects = (JSONArray) JSONParser.readJsonFromUrl(url, parameters);
            for (int i = 0; i < objects.length() ; i++) {
                result.add(Region.initFromJSON((JSONObject) objects.get(i)));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}