package io.hexlet.workshop.ServiceLocator.Objects;

public class Locate {


    private String city;
    private String region;
    private String district;
    private String lat;
    private String lng;
    private String country;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        if (getCity() !=  null)
            return
                    "Страна{" + getCountry() + "} " +
                            "Город{" + getCity() + "} " +
                            "Регион{" + getRegion() + "} " +
                            "Округ{" + getDistrict() + "} " +
                            "Координаты{" + getLat() + " x " + getLng() + "} ";
   return "Информация не найдена";
    }
}
