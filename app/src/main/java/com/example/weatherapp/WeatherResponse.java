package com.example.weatherapp;

public class WeatherResponse {
    private Main main;
    private String name;

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public double getFeelsLike() {
            return feels_like;
        }

        public double getTempMin() {
            return temp_min;
        }

        public double getTempMax() {
            return temp_max;
        }

        public int getHumidity() {
            return humidity;
        }
    }
}

