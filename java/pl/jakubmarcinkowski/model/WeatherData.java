package pl.jakubmarcinkowski.model;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;

public class WeatherData {
    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");
    final Weather currentWeather = openWeatherClient
            .currentWeather()
            .single()
            .byCityName("Gdansk")
            .language(Language.POLISH)
            .unitSystem(UnitSystem.METRIC)
            .retrieve()
            .asJava();

    public Weather getWeather() {
        return currentWeather;
    }
}