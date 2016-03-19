package Services.LocalDataBaseManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.studytrack.app.studytrack_v1.StudyTrackApplication;

import java.util.ArrayList;
import java.util.List;

import Entities.Entity;
import Entities.Region;
import Entities.Speciality;
import Entities.Town;
import Entities.University;

/**
 * Created by yudzh_000 on 17.03.2016.
 */
public class LocalDataBaseDriver {
    private SQLiteDatabase db;

    public LocalDataBaseDriver(AppCompatActivity activity) {
        db = ((StudyTrackApplication) activity.getApplicationContext()).getDB();
    }

    public List<University> loadUniversities(Town town, int count, int offset) {
        ArrayList<University> universities = new ArrayList<>();
        Cursor cur = db.rawQuery("Select * from University",null);
        while (cur.moveToNext()) {
            String data = "";
            for (int i = 0; i < cur.getColumnCount(); i++) {
                data+=cur.getString(i) +"\t";
            }
            Log.d("fail", data);
        }

        Cursor cursor = db.rawQuery("Select * from University Where town_id = ? LIMIT ? OFFSET ?",
                new String[]{Integer.toString(town.getId()),Integer.toString(count),
                        Integer.toString(offset)});
        while (cursor.moveToNext()) {
            universities.add(University.initFromCursor(cursor,town));
        }
        return universities;
    }

    public List<University> loadLiked() {
        ArrayList<University> universities = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from University Where liked = 1", null);
        while (cursor.moveToNext()) {
        //    universities.add(University.initFromCursor(cursor));
        }
        return universities;

    }

    public List<Speciality> loadSpecialies(University university) {
        ArrayList<Speciality> specialities = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Speciality Where id_univ = ?",
                new String[]{Integer.toString(university.getId())});

        while (cursor.moveToNext()) {
            specialities.add(Speciality.initFromCursor(cursor));
        }
        //// TODO: 17.03.2016  discuss about add to obj university
        return specialities;
    }

    public List<Town> loadTowns(Region region) {
        ArrayList<Town> towns = new ArrayList<>();
        Cursor cursor1 = db.rawQuery("Select * from Town ",null);
        while (cursor1.moveToNext()) {
            String data = "";
            for (int i = 0; i < cursor1.getColumnCount(); i++) {
                data+=cursor1.getString(i) +"\t";
            }
            Log.d("fail", data);
        }
        Cursor cursor = db.rawQuery("Select * from Town Where region_id = ?", new String[]{Integer.toString(region.getId())});
        while (cursor.moveToNext()) {
            towns.add(Town.initFromCursor(cursor, region));
        }
        cursor.close();
        return towns;
    }

    public <T extends Entity> void saveData(T param) {
        param.put(db);
    }

    public List<Region> loadRegions() {
        ArrayList<Region> region = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Region", null);
        while (cursor.moveToNext()) {
            region.add(Region.initFromCursor(cursor));
        }
        return region;
    }


}
