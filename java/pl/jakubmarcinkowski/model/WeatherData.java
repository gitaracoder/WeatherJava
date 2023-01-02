package pl.jakubmarcinkowski.model;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;

public class WeatherData {


    public String cityName;

    public WeatherData(String cityName) {
        this.cityName = cityName;
    }




    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");






    public Weather getWeather() {

         Weather currentWeather = openWeatherClient
                .currentWeather()
                .single()
                .byCityName(cityName)
                .language(Language.POLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        return currentWeather;
    }


}