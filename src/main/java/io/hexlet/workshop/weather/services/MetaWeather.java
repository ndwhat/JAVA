package io.hexlet.workshop.weather.services;

import io.hexlet.workshop.weather.Connectors.Connector;
import io.hexlet.workshop.weather.Connectors.MetaWeatherConnector;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MetaWeather implements Service {

    Connector connector = new MetaWeatherConnector();

    private String name = "meta";
    private URLConnection urlConnection;
    String city;

    public MetaWeather() {
    }

    public MetaWeather(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public String getData(String city) {
        try {
            this.city = city;
            if (this.urlConnection == null)
                this.urlConnection = connector.getConnection(city);
            InputStream inputStream = urlConnection.getInputStream();
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




    @Override
    public String getName() {
        return this.name;
    }
}
