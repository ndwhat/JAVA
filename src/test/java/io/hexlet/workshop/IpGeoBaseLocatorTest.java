package io.hexlet.workshop;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBaseLocator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;
import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;


public class IpGeoBaseLocatorTest {

    private static Map<String, String> xmls = new HashMap<>();

    @Mock
    private static URLStreamHandlerFactory urlStreamHandlerFactory;

    @Mock
    private static URLConnection mockedConnection = Mockito.mock(URLConnection.class);


    @BeforeAll
    static void setData() {

        // Set xmls
        xmls.put("79.165.0.0", "src/test/resources/xml/moscow.xml");
        xmls.put("100.165.0.0", "src/test/resources/xml/notfound.xml");


        try {
            urlStreamHandlerFactory = Mockito.mock(URLStreamHandlerFactory.class);
            URL.setURLStreamHandlerFactory(urlStreamHandlerFactory);
            AbstractPublicStreamHandler publicStreamHandler = Mockito.mock(AbstractPublicStreamHandler.class);
            Mockito.doReturn(publicStreamHandler).when(urlStreamHandlerFactory).createURLStreamHandler(Matchers.eq("http"));
            Mockito.doReturn(mockedConnection).when(publicStreamHandler).openConnection(Matchers.any(URL.class));
        } catch (IOException e) {
            java.util.logging.Logger logger = Logger.getGlobal();
            logger.warning("Mockito errors");
        }
    }

    @Test
    void testWithMoscowIP() {
        IpGeoBaseLocator ipGeoBaseLocator = new IpGeoBaseLocator();
        try {
            InputStream inputStream = new FileInputStream(xmls.get("79.165.0.0"));
            Mockito.doReturn(inputStream).when(mockedConnection).getInputStream();
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
            InputStream inputStream = new FileInputStream(xmls.get("100.165.0.0"));
            Mockito.doReturn(inputStream).when(mockedConnection).getInputStream();
            Locate locate = ipGeoBaseLocator.getLocate(InetAddress.getByName("100.165.0.0"));
            Assertions.assertNull(locate.getCountry());
        } catch (Exception e) {
            java.util.logging.Logger logger = Logger.getGlobal();
            logger.warning("Неверный ip");
        }
    }


}


abstract class AbstractPublicStreamHandler extends URLStreamHandler {
    @Override
    public URLConnection openConnection(URL url) throws IOException {
        return null;
    }
}