package io.hexlet.workshop;

import io.hexlet.workshop.ServiceLocator.Locators.IpGeoBaseLocator;
import io.hexlet.workshop.ServiceLocator.Objects.Locate;
import io.hexlet.workshop.ServiceLocator.ServiceLocator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class HW {
    public static void main(String... __) {



    //    1 task
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


    //2nd task
    String plural (String current, String add){
        StringBuilder builder = new StringBuilder(current);
        return current.endsWith("s") ? current : builder.append(add).toString();
    }


    public String Do(List<String> files) {
        String result = files.stream().
                filter(f -> !f.startsWith(".")).
                sorted().
                skip(files.size() / 2).findFirst().toString();
        return plural(result,(result.substring(result.length() - 1))).toUpperCase();


    }

}
