package WeatherDash.dto;

import lombok.Data;

@Data
public class WeatherResponse {
    private String name;
    private String country;
    private double lat;
    private double lon;
    private double temp_c;
    private String temp_color;
    private double wind_kph;
    private String wind_color;
    private int cloud;
    private String cloud_color;

    public WeatherResponse(String name, String country, double lat, double lon, double temp_c, String temp_color, double wind_kph, String wind_color, int cloud, String cloud_color) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.temp_c = temp_c;
        this.temp_color = temp_color;
        this.wind_kph = wind_kph;
        this.wind_color = wind_color;
        this.cloud = cloud;
        this.cloud_color = cloud_color;
    }

}

