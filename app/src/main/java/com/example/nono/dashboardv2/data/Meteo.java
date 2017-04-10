package com.example.nono.dashboardv2.data;

/**
 * Created by nono on 24/03/2017.
 */

public class Meteo {
    CurrentCondition currentCondition;

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public class CurrentCondition{
        String date;
        String hour;
        double tmp;
        double wnd_spd;
        double wnd_gust;
        String wnd_dir;
        double pressure;
        double humidity;
        String condition;
        String condition_key;
        String iconUrl;

        public String getDate() {
            return date;
        }

        public String getHour() {
            return hour;
        }

        public double getTmp() {
            return tmp;
        }

        public double getWnd_spd() {
            return wnd_spd;
        }

        public double getWnd_gust() {
            return wnd_gust;
        }

        public String getWnd_dir() {
            return wnd_dir;
        }

        public double getPressure() {
            return pressure;
        }

        public double getHumidity() {
            return humidity;
        }

        public String getCondition() {
            return condition;
        }

        public String getCondition_key() {
            return condition_key;
        }

        public String getIconUrl() {
            return iconUrl;
        }
    }
}
