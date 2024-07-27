package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<ForecastResponse.Forecast> forecastList;

    public ForecastAdapter(List<ForecastResponse.Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForecastResponse.Forecast forecast = forecastList.get(position);
        holder.textViewDate.setText(MainActivity.DateUtils.convertUnixTimestampToDate(forecast.getDt()));
        holder.textViewTemp.setText("Nhiệt độ: " + forecast.getTemp().getTemp() + "°C");
        holder.textViewDescription.setText("Mô tả: " + forecast.getWeather().get(0).getDescription());
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private TextView textViewTemp;
        private TextView textViewDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
