package io.hexlet.workshop.weather.Connectors;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class YahooConnector implements Connector {


    private final String URLbase = "https://www.metaweather.com/api/location/search/?query=";

    @Override
    public URLConnection getConnection(String city) {
        try {
            return new URL(URLbase + city).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
