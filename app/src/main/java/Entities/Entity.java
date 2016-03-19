package Entities;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yudzh_000 on 19.03.2016.
 */
public interface Entity {
    void put(SQLiteDatabase db);
}
