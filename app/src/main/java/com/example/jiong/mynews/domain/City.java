package com.example.jiong.mynews.domain;

/**
 * Created by Jiong on 2017/3/2.
 */
public class City {
    private int id;
    private int Province_id;
    private String City_name;
    private String City_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvince_id() {
        return Province_id;
    }

    public void setProvince_id(int province_id) {
        Province_id = province_id;
    }

    public String getCity_name() {
        return City_name;
    }

    public void setCity_name(String city_name) {
        City_name = city_name;
    }

    public String getCity_code() {
        return City_code;
    }

    public void setCity_code(String city_code) {
        City_code = city_code;
    }
}
