package com.example.jiong.mynews.domain;

/**
 * Created by Jiong on 2017/3/2.
 */
public class County {
    private int id;
    private int City_id;
    private String County_code;
    private String County_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_id() {
        return City_id;
    }

    public void setCity_id(int city_id) {
        City_id = city_id;
    }

    public String getCounty_code() {
        return County_code;
    }

    public void setCounty_code(String county_code) {
        County_code = county_code;
    }

    public String getCounty_name() {
        return County_name;
    }

    public void setCounty_name(String county_name) {
        County_name = county_name;
    }
}
