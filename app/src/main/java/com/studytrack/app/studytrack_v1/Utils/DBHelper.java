package com.studytrack.app.studytrack_v1.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vadim on 31.01.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "mainDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE university ("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT,"
                + "city TEXT,"
                + "score REAL,"
                + "price INTEGER,"
                + "logo_path TEXT"
                + "isloaded INTEGER DEFAULT 0,"
                + "isfavourite INTEGER DEFAULT 0,"
                + "site TEXT DEFAULT NULL,"
                + "address TEXT DEFAULT NULL,"
                + "phone TEXT DEFAULT NULL" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
