module Test1 {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    requires openweathermap.api;
    requires com.google.gson;
    requires java.prefs;


    opens pl.jakubmarcinkowski;

    opens pl.jakubmarcinkowski.view;

}