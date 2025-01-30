package WeatherDash.dto;

import lombok.Data;

@Data
public class WeatherApiResponse {
    public Location location;
    public CurrentWeather current;

    public static class Location {
        public String name;
        public String country;
        public double lat;
        public double lon;
    }

    public static class CurrentWeather {
        public double temp_c;
        public double wind_kph;
        public int cloud;
    }
}

