package com.wilddev.geo.models;

public record Location(Country country, String city) {

    @Override
    public String toString() {
        return "Location{" +
                "country=" + country +
                ", city='" + city + '\'' +
                '}';
    }
}
