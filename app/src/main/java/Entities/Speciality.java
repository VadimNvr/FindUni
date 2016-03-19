package Entities;

import android.database.Cursor;

/**
 * Created by yudzh_000 on 17.03.2016.
 */
public class Speciality {
    
    String name;
    int price;
    int points;
    String subjects;
    
    public Speciality(String name, int price, int points, String subjects) {
        this.name = name;
        this.price = price;
        this.points = points;
        this.subjects = subjects;
    }

    public static Speciality initFromCursor(Cursor cursor) {
        return null; //// TODO: 17.03.2016  
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPoints() {
        return points;
    }

    public String getSubjects() {
        return subjects;
    }
}
