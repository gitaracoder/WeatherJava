package pl.jakubmarcinkowski.model;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;

public class CurrentWeatherData {


    public String cityName;
    public boolean state;

    public CurrentWeatherData(String cityName) {
        this.cityName = cityName;
    }


    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");


    public Weather getWeather() {
        Weather currentWeather = null;
        try {
            currentWeather = openWeatherClient
                    .currentWeather()
                    .single()
                    .byCityName(cityName)
                    .language(Language.POLISH)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
            return currentWeather;
        } catch (NoDataFoundException e) {
            return null;
        }

    }
}