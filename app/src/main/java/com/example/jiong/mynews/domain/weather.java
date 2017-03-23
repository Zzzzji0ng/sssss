package com.example.jiong.mynews.domain;

/**
 * Created by Jiong on 2017/3/3.
 */
public class weather {
    private String date;/*时间*/
    private String Hightemperature;/*最高温最低温*/
    private String Lowtemperature;
    private String Winddirection;/*风向*/
    private String Windpower;/*风力*/

    @Override
    public String toString() {
        return "weather{" +
                "date='" + date + '\'' +
                ", Hightemperature='" + Hightemperature + '\'' +
                ", Lowtemperature='" + Lowtemperature + '\'' +
                ", Winddirection='" + Winddirection + '\'' +
                ", Windpower='" + Windpower + '\'' +
                ", WeatherState='" + WeatherState + '\'' +
                '}';
    }

    private String WeatherState;/*天气状态*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHightemperature() {
        return Hightemperature;
    }

    public void setHightemperature(String hightemperature) {
        Hightemperature = hightemperature;
    }

    public String getLowtemperature() {
        return Lowtemperature;
    }

    public void setLowtemperature(String lowtemperature) {
        Lowtemperature = lowtemperature;
    }

    public String getWinddirection() {
        return Winddirection;
    }

    public void setWinddirection(String winddirection) {
        Winddirection = winddirection;
    }

    public String getWindpower() {
        return Windpower;
    }

    public void setWindpower(String windpower) {
        Windpower = windpower;
    }

    public String getWeatherState() {
        return WeatherState;
    }

    public void setWeatherState(String weatherState) {
        WeatherState = weatherState;
    }
}
