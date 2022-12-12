package pl.jakubmarcinkowski.model;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.Rain;
import com.github.prominence.openweathermap.api.model.forecast.Snow;

public class FiveDaysWeatherData {

    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");

    final Forecast forecastWeather = openWeatherClient

            .forecast5Day3HourStep()
            .byCityName("Gdansk")
            .language(Language.POLISH)
            .unitSystem(UnitSystem.METRIC)
            .count(40)
            .retrieve()
            .asJava();

    public Forecast getWeather() {
        return forecastWeather;
    }
}
