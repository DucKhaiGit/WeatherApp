package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    private List<Forecast> list;

    public List<Forecast> getList() {
        return list;
    }

    public static class Forecast {
        @SerializedName("dt")
        private long dt;

        @SerializedName("main")
        private Temp temp;

        @SerializedName("weather")
        private List<Weather> weather;

        public long getDt() {
            return dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public List<Weather> getWeather() {
            return weather;
        }
    }

    public static class Temp {
        @SerializedName("temp")
        private float temp;

        @SerializedName("temp_min")
        private float tempMin;

        @SerializedName("temp_max")
        private float tempMax;

        public float getTemp() {
            return temp;
        }

        public float getTempMin() {
            return tempMin;
        }

        public float getTempMax() {
            return tempMax;
        }
    }

    public static class Weather {
        @SerializedName("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}


