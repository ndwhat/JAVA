package io.hexlet.workshop.weather.services;

import io.hexlet.workshop.weather.Connectors.Connector;
import io.hexlet.workshop.weather.Connectors.YahooConnector;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class Yahoo implements Service {

    private String name = "yahoo";
    private URLConnection urlConnection;

    private Connector connector = new YahooConnector();

    @Override
    public String getData(String city) {
        try {
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
        return name;
    }
}

