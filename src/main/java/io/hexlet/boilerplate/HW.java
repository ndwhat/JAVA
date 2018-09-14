package io.hexlet.boilerplate;

import io.hexlet.boilerplate.Locators.IpGeoBaseLocator;
import io.hexlet.boilerplate.Objects.Locate;
import io.hexlet.boilerplate.Services.ServiceLocator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

public final class HW {
    public static void main(String... __) {

        System.out.print("Введите IP:");
        Scanner scanner = new Scanner(System.in);
        try {
            InetAddress address = InetAddress.getByName(scanner.nextLine());
            Locate locate = ServiceLocator.getLocate(address, new IpGeoBaseLocator());
            System.out.println("Гео информация: " + locate.toString());
        } catch (UnknownHostException e) {
            Logger logger = Logger.getGlobal();
            logger.warning("Неверный ip");
        }


    }

}
