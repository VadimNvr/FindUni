package Requests;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import Entities.Town;

/**
 * Created by yudzh_000 on 06.04.2016.
 */
public class GetTownsWithSpecial extends Request<Town> {

    String characters;

    public GetTownsWithSpecial(AppCompatActivity activity, String characters) {
        super(activity);
        this.characters = characters;
    }

    @Override
    protected List<Town> doInBackground(Void... params) {
        List<Town> towns = this.localDb.loadTownsWithSpecifickName(characters);
        return towns;
    }
}
