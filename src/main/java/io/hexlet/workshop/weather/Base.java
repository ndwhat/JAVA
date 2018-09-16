package io.hexlet.workshop.weather;

import io.hexlet.workshop.weather.Facade.Weather;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Base {

    public static void main(String[] args) {

        // 3rd task
        List<String> collect = Arrays.stream(args).dropWhile(arg -> !arg.equals("--service")).skip(1).limit(2).collect(Collectors.toList());
        String service = collect.get(0);
        String city = collect.get(1);
        final Weather weather = new Weather();
        System.out.println(weather.getData(service, city));
    }
}
