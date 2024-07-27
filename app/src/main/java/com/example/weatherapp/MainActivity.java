package com.example.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextCity;
    private Button buttonGetWeather;
    private TextView textViewWeather;
    private WeatherAPI weatherAPI;
    private TextView textViewForecast;
    private String apiKey = "313862dc7baa05a9f5b59d7a5435c8d6";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = findViewById(R.id.editTextCity);
        buttonGetWeather = findViewById(R.id.buttonGetWeather);
        textViewWeather = findViewById(R.id.textViewWeather);

        weatherAPI = RetrofitClient.getRetrofitInstance().create(WeatherAPI.class);

        buttonGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCity.getText().toString();
                if(cityName != null && !cityName.isEmpty())
                {
                    getWeatherDetails(cityName);
                    getWeatherForecast(cityName);
                }
                else{
                    Toast.makeText(MainActivity.this,"Lỗi định dạng tên thành phố",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getWeatherDetails(String cityName) {
        weatherAPI.getCurrentWeather(cityName, apiKey, "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    String weatherDetails = "Thành phố: " + weatherResponse.getName() + "\n" +
                            "Nhiệt độ: " + weatherResponse.getMain().getTemp() + "°C\n" +
                            "Cảm giác thực tế: " + weatherResponse.getMain().getFeelsLike() + "°C\n" +
                            "Nhiệt độ thấp nhất: " + weatherResponse.getMain().getTempMin() + "°C\n" +
                            "Nhiệt độ cao nhất: " + weatherResponse.getMain().getTempMax() + "°C\n" +
                            "Độ ẩm: " + weatherResponse.getMain().getHumidity() + "%";
                    textViewWeather.setText(weatherDetails);
                } else {
                    textViewWeather.setText("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                textViewWeather.setText("Failure: " + t.getMessage());
            }
        });
    }

    private void getWeatherForecast(String cityName) {
        weatherAPI.getWeatherForecast(cityName, apiKey, "metric").enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    ForecastResponse forecastResponse = response.body();
                    List<ForecastResponse.Forecast> forecastList = forecastResponse.getList();
                    ForecastAdapter adapter = new ForecastAdapter(forecastList);
                    RecyclerView recyclerView = findViewById(R.id.recyclerViewForecast);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    textViewForecast.setText("Lỗi: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                textViewForecast.setText("Thất bại: " + t.getMessage());
            }
        });
    }

    public static class DateUtils {

        // Chuyển đổi Unix timestamp thành định dạng dd/MM/yy
        public static String convertUnixTimestampToDate(long timestamp) {
            // Unix timestamp được tính bằng giây, còn Date sử dụng mili giây, do đó nhân với 1000
            Date date = new Date(timestamp * 1000L);
            // Đặt múi giờ theo UTC
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(date);
        }
    }





}
