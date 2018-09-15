package io.hexlet.workshop;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBaseLocator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;


import java.io.InputStream;
import java.net.InetAddress;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;


public class IpGeoBaseLocatorTest {

    private static Map<String, String> xmls = new HashMap<>();


    @BeforeAll
    static void setData() {
        xmls.put("79.165.0.0", "<ip-answer>\n" +
                "<ip value=\"79.165.0.0\">\n" +
                "<inetnum>79.164.16.0 - 79.165.255.255</inetnum>\n" +
                "<country>RU</country>\n" +
                "<city>Москва</city>\n" +
                "<region>Москва</region>\n" +
                "<district>Центральный федеральный округ</district>\n" +
                "<lat>55.755787</lat>\n" +
                "<lng>37.617634</lng>\n" +
                "</ip>\n" +
                "</ip-answer>");
        xmls.put("100.165.0.0", "<ip-answer>\n" +
                "<message>Incorrect request</message>\n" +
                "</ip-answer>");
    }

    @Test
    void testWithMoscowIP() {
        IpGeoBaseLocator ipGeoBaseLocator = new IpGeoBaseLocator();
        try {
            InputStream inputStreamMoscow = IOUtils.toInputStream(xmls.get("79.165.0.0"), "UTF-8");
            ipGeoBaseLocator.setInputStreamXml(Optional.of(inputStreamMoscow));
            Locate locate = ipGeoBaseLocator.getLocate(InetAddress.getByName("79.165.0.0"));
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
        IpGeoBaseLocator ipGeoBaseLocator = new IpGeoBaseLocator();
        try {
            InputStream inputStreamMoscow = IOUtils.toInputStream(xmls.get("100.165.0.0"), "UTF-8");
            ipGeoBaseLocator.setInputStreamXml(Optional.of(inputStreamMoscow));
            Locate locate = ipGeoBaseLocator.getLocate(InetAddress.getByName("100.165.0.0"));
            Assertions.assertNull(locate.getCountry());


        } catch (Exception e) {
            java.util.logging.Logger logger = Logger.getGlobal();
            logger.warning("Неверный ip");
        }
    }


}
