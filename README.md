WeatherApp
Overview:

WeatherApp is an Android application designed to provide users with current weather conditions and a 7-day weather forecast for any city worldwide. The app leverages the OpenWeatherMap API to fetch and display weather data in a user-friendly interface.

Features:

Current Weather Information:

Displays the current temperature, feels-like temperature, minimum and maximum temperatures, and humidity.
Provides a brief weather description for the selected city.

5-Day Weather Forecast(report radpidly every 3h):

Shows daily weather forecasts including day temperature and weather description.
Forecasts are presented in a clear and concise manner for easy readability.


Technical Details:

Programming Language: Java
Architecture: MVVM (Model-View-ViewModel)
API Integration: OpenWeatherMap API
Libraries Used:
Retrofit for network requests
Gson for JSON parsing
AndroidX libraries for UI components
Usage:

Enter City Name:
Users can enter the name of the city they want to get weather information for in the provided text field.
Get Weather Details:
On clicking the "Get Weather" button, the app fetches and displays the current weather details and a 7-day forecast for the entered city.

API Key:

To use the app, you need an API key from OpenWeatherMap. Sign up on OpenWeatherMap to get your free API key.
