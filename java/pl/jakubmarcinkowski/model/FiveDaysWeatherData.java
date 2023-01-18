package pl.jakubmarcinkowski.model;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;

public class FiveDaysWeatherData {

    public String cityName;

    public FiveDaysWeatherData(String cityName) {
        this.cityName = cityName;
    }

    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");


    public Forecast getWeather() {

        try {


            Forecast forecastWeather = openWeatherClient


                    .forecast5Day3HourStep()
                    //.byCityName("Gdansk")
                    .byCityName(cityName)
                    .language(Language.POLISH)
                    .unitSystem(UnitSystem.METRIC)
                    .count(40)
                    .retrieve()
                    .asJava();

            return forecastWeather;
        } catch (NoDataFoundException e) {

            System.out.println("Nie rozpoznano miasta!");
            return null;

        }


    }
}
