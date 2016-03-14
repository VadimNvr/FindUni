package com.studytrack.app.studytrack_v1;

import android.app.Application;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;

import com.studytrack.app.studytrack_v1.Utils.DBHelper;

/**
 * Created by vadim on 31.01.16.
 */
public class StudyTrackApplication extends Application {
    private SQLiteDatabase database;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //deleteDatabase("mainDB");
        DBHelper dbHelper = new DBHelper(this);

        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public SQLiteDatabase getDB() {
        return database;
    }
}
