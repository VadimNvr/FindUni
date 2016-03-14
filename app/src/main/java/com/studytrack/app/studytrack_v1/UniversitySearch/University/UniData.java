package com.studytrack.app.studytrack_v1.UniversitySearch.University;

import java.io.Serializable;

/**
 * Created by vadim on 30.01.16.
 */
public class UniData implements Serializable {
    String name;
    String address;
    String phone;
    String site;
    boolean isFavourite;

    public void setName(String _name) { name = _name; }
    public void setFavourite(boolean fav) { isFavourite = fav; }
    public void setAddress(String _address) { address = _address; }
    public void setPhone(String _phone) { phone = _phone; }
    public void setSite(String _site) { site = _site; }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getSite() { return site; }
}
