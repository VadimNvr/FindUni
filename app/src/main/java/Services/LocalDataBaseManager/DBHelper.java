package Services.LocalDataBaseManager;

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
        createRegionTable(db);
        createTownTable(db);
        createUniversityTable(db);
        createSpecialityTypeTable(db);
        createSpecialityTable(db);
    }

    private void createSpecialityTypeTable(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE SpecialityType(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "name text" +
                ");");
    }

    private void createTownTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Town(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "region_id integer," +
                "name TEXT," +
                "count integer," +
                "FOREIGN KEY(region_id) REFERENCES Region(id));");
    }

    private void createRegionTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Region (" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "name text);");
    }

    private void createSpecialityTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Speciality (" +
                        "id integer PRIMARY KEY AUTOINCREMENT," +
                        "university_id integer," +
                        "type_id integer," +
                        "price integer," +
                        "points integer," +
                        "subjects integer," +
                        "FOREIGN KEY(university_id) REFERENCES University(id),"+
                        "FOREIGN KEY(type_id) REFERENCES SpecialityType(id)" +
                        ");");
    }

    private void createUniversityTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE University ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "town_id INTEGER,"
                + "mean_price INTEGER,"
                + "mean_points INTEGER,"
                + "image_path TEXT,"
                + "logo_path TEXT,"
                + "is_loaded INTEGER DEFAULT 0,"
                + "is_favourite INTEGER DEFAULT 0,"
                + "site TEXT DEFAULT NULL,"
                + "address TEXT DEFAULT NULL,"
                + "phone TEXT DEFAULT NULL,"
                + "FOREIGN KEY(town_id) REFERENCES Town(id)"
                + ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
