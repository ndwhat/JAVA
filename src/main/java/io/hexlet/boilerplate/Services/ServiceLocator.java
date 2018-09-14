package io.hexlet.boilerplate.Services;


import io.hexlet.boilerplate.Locators.Locator;
import io.hexlet.boilerplate.Objects.Locate;

import java.net.InetAddress;

public class ServiceLocator {

    private ServiceLocator(){}

    public static Locate getLocate(InetAddress address, Locator locator) {
        return locator.getLocate(address);
    }
}
