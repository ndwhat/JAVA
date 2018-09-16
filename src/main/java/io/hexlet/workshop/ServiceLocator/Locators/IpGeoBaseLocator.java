package io.hexlet.workshop.ServiceLocator.Locators;

import io.hexlet.workshop.ServiceLocator.Objects.Locate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.logging.Logger;

public class IpGeoBaseLocator implements Locator {


    private Logger logger = Logger.getGlobal();
    private Locate locate = new Locate();
    private URLConnection urlConnection;

    public IpGeoBaseLocator(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    @Override
    public Locate getLocate() {
        try {

            //Builder
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setNamespaceAware(false);
            f.setValidating(false);
            DocumentBuilder b = f.newDocumentBuilder();

            //UrlConnection
            urlConnection.addRequestProperty("Accept", "application/xml");

            //Parse
            Document doc = b.parse(urlConnection.getInputStream());
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            NodeList values = list.item(1).getChildNodes();
            for (int i = 0; i < values.getLength(); i++) {
                switch (values.item(i).getNodeName()) {
                    case "country":
                        locate.setCountry(values.item(i).getTextContent());
                        break;
                    case "city":
                        locate.setCity(values.item(i).getTextContent());
                        break;
                    case "region":
                        locate.setRegion(values.item(i).getTextContent());
                        break;
                    case "district":
                        locate.setDistrict(values.item(i).getTextContent());
                        break;
                    case "lat":
                        locate.setLat(values.item(i).getTextContent());
                        break;
                    case "lng":
                        locate.setLng(values.item(i).getTextContent());
                        break;
                }
            }
        } catch (ParserConfigurationException e) {
            logger.warning("Проблемы при парсинге:" + e.getMessage());
        } catch (IOException e) {
            logger.warning("Ошибки ввода-вывода:" + e.getMessage());
        } catch (SAXException e) {
            logger.warning("Ошибки SAX парсинга:" + e.getMessage());
        }


        return locate;
    }
}
