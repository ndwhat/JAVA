package io.hexlet.workshop.weather.Factory;

import io.hexlet.workshop.weather.services.MetaWeather;
import io.hexlet.workshop.weather.services.Service;
import io.hexlet.workshop.weather.services.Yahoo;

import java.util.ArrayList;
import java.util.List;

public class ServiceFactory {

    private static List<Service> services = new ArrayList<>();

    static {
        services.add(new MetaWeather());
        services.add(new Yahoo());
    }

    private ServiceFactory() {
    }

    public static Service getService(String name, List<Service> list) {
        services.addAll(list);
        System.out.println(name);

        return services.stream().filter(f -> name.equals(f.getName())).findFirst().get();
    }
}
