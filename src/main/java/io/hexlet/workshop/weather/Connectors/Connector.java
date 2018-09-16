package io.hexlet.workshop.weather.Connectors;

import java.net.URLConnection;

public interface Connector {

    URLConnection getConnection(String city);
}
