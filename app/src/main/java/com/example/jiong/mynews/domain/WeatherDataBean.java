package com.example.jiong.mynews.domain;

import java.util.List;

/**
 * Created by Jiong on 2017/3/3.
 */
public class WeatherDataBean  {

    /**
     * data : {"aqi":"71","city":"杭州","forecast":[{"date":"3日星期五","fengli":"微风级","fengxiang":"东南风","high":"高温 15℃","low":"低温 6℃","type":"多云"},{"date":"4日星期六","fengli":"微风级","fengxiang":"东南风","high":"高温 18℃","low":"低温 9℃","type":"小雨"},{"date":"5日星期天","fengli":"3-4级","fengxiang":"北风","high":"高温 12℃","low":"低温 6℃","type":"小雨"},{"date":"6日星期一","fengli":"3-4级","fengxiang":"北风","high":"高温 11℃","low":"低温 5℃","type":"阴"},{"date":"7日星期二","fengli":"微风级","fengxiang":"东风","high":"高温 12℃","low":"低温 5℃","type":"多云"}],"ganmao":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。","wendu":"17","yesterday":{"date":"2日星期四","fl":"3-4级","fx":"北风","high":"高温 13℃","low":"低温 2℃","type":"晴"}}
     * desc : OK
     * status : 1000
     */

    private DataBean data;
    private String desc;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * aqi : 71
         * city : 杭州
         * forecast : [{"date":"3日星期五","fengli":"微风级","fengxiang":"东南风","high":"高温 15℃","low":"低温 6℃","type":"多云"},{"date":"4日星期六","fengli":"微风级","fengxiang":"东南风","high":"高温 18℃","low":"低温 9℃","type":"小雨"},{"date":"5日星期天","fengli":"3-4级","fengxiang":"北风","high":"高温 12℃","low":"低温 6℃","type":"小雨"},{"date":"6日星期一","fengli":"3-4级","fengxiang":"北风","high":"高温 11℃","low":"低温 5℃","type":"阴"},{"date":"7日星期二","fengli":"微风级","fengxiang":"东风","high":"高温 12℃","low":"低温 5℃","type":"多云"}]
         * ganmao : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
         * wendu : 17
         * yesterday : {"date":"2日星期四","fl":"3-4级","fx":"北风","high":"高温 13℃","low":"低温 2℃","type":"晴"}
         */

        private String aqi;
        private String city;
        private String ganmao;
        private String wendu;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 2日星期四
             * fl : 3-4级
             * fx : 北风
             * high : 高温 13℃
             * low : 低温 2℃
             * type : 晴
             */

            private String date;
            private String fl;
            private String fx;
            private String high;
            private String low;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ForecastBean {
            /**
             * date : 3日星期五
             * fengli : 微风级
             * fengxiang : 东南风
             * high : 高温 15℃
             * low : 低温 6℃
             * type : 多云
             */

            private String date;
            private String fengli;
            private String fengxiang;
            private String high;
            private String low;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
