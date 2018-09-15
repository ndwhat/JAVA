package io.hexlet.workshop.ServiceLocator.Locators;

import io.hexlet.workshop.ServiceLocator.Objects.Locate;

import java.net.InetAddress;

public interface Locator {
     Locate getLocate(InetAddress address);
}
