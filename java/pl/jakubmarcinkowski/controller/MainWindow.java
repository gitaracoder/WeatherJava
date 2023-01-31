package pl.jakubmarcinkowski.controller;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.jakubmarcinkowski.model.FiveDaysWeatherData;
import pl.jakubmarcinkowski.model.CurrentWeatherData;



import java.util.Collections;
import java.util.prefs.Preferences;


public class MainWindow {

    @FXML
    private Text curCity1;

    @FXML
    private Text curCity11;

    @FXML
    private Text curCloud1;

    @FXML
    private Text curCloud11;

    @FXML
    private Text curHum1;

    @FXML
    private Text curHum11;

    @FXML
    private Text curPress1;

    @FXML
    private Text curPress11;

    @FXML
    private Text curTemp1;

    @FXML
    private Text curTemp11;

    @FXML
    private Text infoMessage;

    @FXML
    private TextField location1;

    @FXML
    private TextField location11;

    @FXML
    private LineChart<?, ?> rainChart1;

    @FXML
    private LineChart<?, ?> rainChart11;

    @FXML
    private LineChart<?, ?> tempChart1;

    @FXML
    private LineChart<?, ?> tempChart11;

    @FXML
    private LineChart<?, ?> windChart1;

    @FXML
    private LineChart<?, ?> windChart11;


    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("3e2875ddbebc19351228389a0657f01e");

    private Preferences prefs;

    public void setPreference(String toRemember1, String toRemember2) {

        prefs = Preferences.userRoot().node(this.getClass().getName());
        String ID1 = "City1";
        String ID2 = "City2";
        prefs.put(ID1, toRemember1);
        prefs.put(ID2, toRemember2);

    }

    public String getPreference(String ID) {

        prefs = Preferences.userRoot().node(this.getClass().getName());
        return prefs.get(ID, "");
    }

    public String location1Name;
    public String location2Name;


    public void initialize() {

        location1Name = getPreference("City1");
        location2Name = getPreference("City2");
        location1.setText(location1Name);
        location11.setText(location2Name);
       // city1Action(location1Name);
       // city2Action(location2Name);
    }

    public void city1Action(String cityName) {

        CurrentWeatherData weatherData = new CurrentWeatherData(cityName);
        FiveDaysWeatherData fiveDaysWeatherData = new FiveDaysWeatherData(cityName);

        curCity1.setText(weatherData.getWeather().getLocation().getName() + ", " + weatherData.getWeather().getLocation().getCountryCode());
        curTemp1.setText(weatherData.getWeather().getTemperature().getValue() + "°C");
        curHum1.setText(weatherData.getWeather().getHumidity().getValue() + "%");
        curPress1.setText(weatherData.getWeather().getAtmosphericPressure().getValue() + "hPa");
        curCloud1.setText(String.valueOf(weatherData.getWeather().getClouds().getValue()) + "%");

        XYChart.Series tempSeries = new XYChart.Series();
        XYChart.Series windSeries = new XYChart.Series();
        XYChart.Series rainSeries = new XYChart.Series();

        rainSeries.getData().removeAll(Collections.singleton(rainChart1.getData().setAll()));
        windSeries.getData().removeAll(Collections.singleton(windChart1.getData().setAll()));
        tempSeries.getData().removeAll(Collections.singleton(tempChart1.getData().setAll()));


        for (int i = 0; i < 34; i = i + 2) {

            tempSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getTemperature().getValue()));
            windSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getWind().getSpeed() * 3.6));
            rainSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getClouds().getValue()));

        }

        tempChart1.getData().add(tempSeries);
        windChart1.getData().add(windSeries);
        rainChart1.getData().add(rainSeries);
        tempChart1.setAnimated(false);
        rainChart1.setAnimated(false);
        windChart1.setAnimated(false);
        tempChart1.getXAxis().setTickLabelsVisible(false);

        tempChart1.getXAxis().setOpacity(0);
        rainChart1.getXAxis().setTickLabelsVisible(false);
        rainChart1.getXAxis().setOpacity(0);

    }

    public void city2Action(String cityName) {

        CurrentWeatherData weatherData = new CurrentWeatherData(location11.getCharacters().toString());
        FiveDaysWeatherData fiveDaysWeatherData = new FiveDaysWeatherData(location11.getCharacters().toString());

        curCity11.setText(weatherData.getWeather().getLocation().getName() + ", " + weatherData.getWeather().getLocation().getCountryCode());
        curTemp11.setText(weatherData.getWeather().getTemperature().getValue() + "°C");
        curHum11.setText(weatherData.getWeather().getHumidity().getValue() + "%");
        curPress11.setText(weatherData.getWeather().getAtmosphericPressure().getValue() + "hPa");
        curCloud11.setText(String.valueOf(weatherData.getWeather().getClouds().getValue()) + "%");


        XYChart.Series tempSeries = new XYChart.Series();
        XYChart.Series windSeries = new XYChart.Series();
        XYChart.Series rainSeries = new XYChart.Series();

        rainSeries.getData().removeAll(Collections.singleton(rainChart11.getData().setAll()));
        windSeries.getData().removeAll(Collections.singleton(windChart11.getData().setAll()));
        tempSeries.getData().removeAll(Collections.singleton(tempChart11.getData().setAll()));


        for (int i = 0; i < 34; i = i + 2) {

            tempSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getTemperature().getValue()));
            windSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getWind().getSpeed() * 3.6));
            rainSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour() + ":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getClouds().getValue()));

        }

        tempChart11.getData().add(tempSeries);
        windChart11.getData().add(windSeries);
        rainChart11.getData().add(rainSeries);
        tempChart11.setAnimated(false);
        rainChart11.setAnimated(false);
        windChart11.setAnimated(false);

        tempChart11.getXAxis().setTickLabelsVisible(false);
        tempChart11.getXAxis().setOpacity(0);

        rainChart11.getXAxis().setTickLabelsVisible(false);
        rainChart11.getXAxis().setOpacity(0);


    }

    public void buttonAction1() {

        location1Name = location1.getCharacters().toString();
        location2Name = location11.getCharacters().toString();

        setPreference(location1Name, location2Name);
        city1Action(location1Name);

    }

    public void buttonAction11() {

        location1Name = location1.getCharacters().toString();
        location2Name = location11.getCharacters().toString();

        setPreference(location1Name, location2Name);
        city2Action(location1Name);

    }


}














