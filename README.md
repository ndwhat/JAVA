tests works with
```
-ea --illegal-access=deny --add-opens java.base/java.net=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED -f pom.xml
 ```

# Gis meteo

Для работы с либой нужно заимплементить интерфейс Service
  weather/services/service

После этого можно добавить его при вызове, like so
   ```
           final Weather weather = new Weather();
   // Добавление
       weather.addData(SuperService);
       // Получение
        weather.getData("super service", "berlin");
   ```