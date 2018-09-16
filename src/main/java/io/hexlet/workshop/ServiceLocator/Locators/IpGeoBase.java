package io.hexlet.workshop.ServiceLocator.Locators;

import io.hexlet.workshop.ServiceLocator.Objects.Locate;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class IpGeoBase {

    private final String urlBefore = "http://ipgeobase.ru:7020/geo?ip=";

    private IpGeoBaseLocator ipGeoBaseLocator;


    public IpGeoBase(InetAddress address) {
        try {
            String surl = urlBefore + address.getHostAddress();
            final URLConnection urlConnection = new URL(surl).openConnection();
            ipGeoBaseLocator = new IpGeoBaseLocator(urlConnection);
        } catch (IOException e) {
            Logger logger = Logger.getGlobal();
            logger.warning("Ошибки connect-a:" + e.getMessage());
        }
    }

    public Locate getLocate() {
        return ipGeoBaseLocator.getLocate();
    }
}

