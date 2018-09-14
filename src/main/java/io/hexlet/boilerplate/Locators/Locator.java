package io.hexlet.boilerplate.Locators;

import io.hexlet.boilerplate.Objects.Locate;

import java.net.InetAddress;

public interface Locator {
     Locate getLocate(InetAddress address);
}
