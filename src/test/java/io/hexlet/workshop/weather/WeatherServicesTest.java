package io.hexlet.workshop.weather;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBase;
import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBaseLocator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;
import io.hexlet.workshop.weather.Connectors.MetaWeatherConnector;
import io.hexlet.workshop.weather.services.MetaWeather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class WeatherServicesTest {

    private static Map<String, String> xmls = new HashMap<>();

    @Mock
    private static URLConnection mockedConnection = Mockito.mock(URLConnection.class);

    @Mock
    private static MetaWeatherConnector mockedConnector = Mockito.mock(MetaWeatherConnector.class);

    @BeforeAll
    static void setData() {
        // Set xmls
        xmls.put("berlin", "[{\"title\":\"Berlin\",\"location_type\":\"City\",\"woeid\":638242,\"latt_long\":\"52.516071,13.376980\"}]");
    }

    @Test
    void testWeatherServiceWork() {
        try {
            InputStream inputStream = new ByteArrayInputStream(xmls.get("berlin").getBytes());
            Mockito.doReturn(inputStream).when(mockedConnection).getInputStream();
            String result = new MetaWeather(mockedConnection).getData("london");
            //   System.out.println(result);
            Assertions.assertEquals(result, xmls.get("berlin"));
        } catch (Exception e) {
            Logger logger = Logger.getGlobal();
            logger.warning("Some problems" + e.getMessage());
        }
    }

}


