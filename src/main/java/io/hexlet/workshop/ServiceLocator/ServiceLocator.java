package io.hexlet.workshop.ServiceLocator;


import io.hexlet.workshop.ServiceLocator.Locators.Locator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;

import java.net.InetAddress;

public class ServiceLocator {

    private ServiceLocator(){}

    public static Locate getLocate(InetAddress address, Locator locator) {
        return locator.getLocate();
    }
}
