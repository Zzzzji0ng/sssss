package com.example.jiong.mynews.domain;

/**
 * Created by Jiong on 2017/3/2.
 */
public class Province {
    /*省市县三个实体类*/

    private int id;
    private String Province_name;
    private String Province_code;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince_name() {
        return Province_name;
    }

    public void setProvince_name(String province_name) {
        Province_name = province_name;
    }

    public String getProvince_code() {
        return Province_code;
    }

    public void setProvince_code(String province_code) {
        Province_code = province_code;
    }



}
