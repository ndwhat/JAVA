package io.hexlet.workshop.geo;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBase;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBaseLocator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;


public class IpGeoBaseLocatorTest {

    private static Map<String, String> xmls = new HashMap<>();

    @Mock
    private static URLConnection mockedConnection = Mockito.mock(URLConnection.class);


    @BeforeAll
    static void setData() {
        // Set xmls
        xmls.put("79.165.0.0", "src/test/resources/xml/moscow.xml");
        xmls.put("100.165.0.0", "src/test/resources/xml/notfound.xml");
    }

    @Test
    void testWithMoscowIP() {
        try {
            InputStream inputStream = new FileInputStream(xmls.get("79.165.0.0"));
            Mockito.doReturn(inputStream).when(mockedConnection).getInputStream();
            Locate locate = new IpGeoBaseLocator(mockedConnection).getLocate();
            Assertions.assertEquals(locate.getCountry(), "RU");
            Assertions.assertEquals(locate.getCity(), "Москва");
            Assertions.assertEquals(locate.getRegion(), "Москва");
            Assertions.assertEquals(locate.getDistrict(), "Центральный федеральный округ");
            Assertions.assertEquals(locate.getLat(), "55.755787");
            Assertions.assertEquals(locate.getLng(), "37.617634");
        } catch (Exception e) {
            java.util.logging.Logger logger = Logger.getGlobal();
            logger.warning("Неверный ip");
        }
    }

    @Test
    void testWithNotFoundIP() {

        try {
            InputStream inputStream = new FileInputStream(xmls.get("100.165.0.0"));
            Mockito.doReturn(inputStream).when(mockedConnection).getInputStream();
            Locate locate = new IpGeoBaseLocator(mockedConnection).getLocate();
            Assertions.assertNull(locate.getCountry());
        } catch (Exception e) {
            java.util.logging.Logger logger = Logger.getGlobal();
            logger.warning("Неверный ip");
        }
    }
}


