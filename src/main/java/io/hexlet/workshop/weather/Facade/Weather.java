package io.hexlet.workshop.weather.Facade;


import io.hexlet.workshop.weather.Factory.ServiceFactory;
import io.hexlet.workshop.weather.services.Service;

import java.util.ArrayList;
import java.util.List;

public class Weather {


   private static List<Service> services = new ArrayList<>();

    public String getData(String service, String city){
        Service oService = ServiceFactory.getService(service,services);
        return oService.getData(city);
    }


    public void addData(Service service){
        services.add(service);
    }


}
