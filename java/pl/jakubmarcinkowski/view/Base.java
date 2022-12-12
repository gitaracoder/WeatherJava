package pl.jakubmarcinkowski.view;

import com.github.prominence.openweathermap.api.model.forecast.Rain;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import pl.jakubmarcinkowski.model.FiveDaysWeatherData;
import pl.jakubmarcinkowski.model.WeatherData;
public class Base {
    @FXML
    private LineChart<?, ?> rainChart1;

    @FXML
    private LineChart<?, ?> tempChart1;

    @FXML
    private LineChart<?, ?> windChart1;
    @FXML
    private Text curCity1;

    @FXML
    private Text curCloud1;

    @FXML
    private Text curHum1;

    @FXML
    private Text curPress1;

    @FXML
    private Text curTemp1;

    WeatherData weatherData = new WeatherData();
    FiveDaysWeatherData fiveDaysWeatherData = new FiveDaysWeatherData();
    public void buttonAction(){

        curCity1.setText(String.valueOf(weatherData.getWeather().getLocation().getName()));
        curTemp1.setText(String.valueOf(weatherData.getWeather().getTemperature().getValue())+"°C");
        curHum1.setText(String.valueOf(weatherData.getWeather().getHumidity().getValue())+"%");
        curPress1.setText(String.valueOf(weatherData.getWeather().getAtmosphericPressure().getValue())+"hPa");
        curCloud1.setText(String.valueOf(weatherData.getWeather().getClouds().getValue())+"%");

        XYChart.Series tempSeries = new XYChart.Series();
        XYChart.Series windSeries = new XYChart.Series();
        XYChart.Series rainSeries = new XYChart.Series();


        for (int i = 0; i < 40; i=i+2) {

            tempSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour()+":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getTemperature().getValue()));
            windSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour()+":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getWind().getSpeed()*3.6));
            rainSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + "." + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getMonthValue() + " / " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour()+":00", fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getClouds().getValue()));




            //rainSeries.getData().add(new XYChart.Data(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getDayOfMonth() + " " + fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getForecastTime().getHour(), fiveDaysWeatherData.getWeather().getWeatherForecasts().get(i).getRain()));
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

        //System.out.println(fiveDaysWeatherData.getWeather().getWeatherForecasts().get(1).getRain().getThreeHourLevel()+"");
    }

}
