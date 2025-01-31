package WeatherDash.service;

import WeatherDash.dto.WeatherApiResponse;
import WeatherDash.dto.WeatherResponse;
import WeatherDash.exps.AppBadException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private static final String API_KEY = "3a7dab2d5f7e4302a90120723252701";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();


    public Mono<WeatherResponse> getWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", API_KEY)
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        Mono.error(new AppBadException("Invalid city name: " + city)))
                .bodyToMono(WeatherApiResponse.class)
                .map(this::mapToWeatherResponse);
    }




    public List<WeatherResponse> getWeatherForCities(List<String> cities) {
        List<WeatherResponse> weatherResponses = new ArrayList<>();

        for (String city : cities) {
            WeatherApiResponse apiResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("key", API_KEY)
                            .queryParam("q", city)
                            .build())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse ->
                            Mono.error(new AppBadException("Invalid city name: " + city)))
                    .bodyToMono(WeatherApiResponse.class)
                    .block();
            if (apiResponse != null) {
                weatherResponses.add(mapToWeatherResponse(apiResponse));
            }
        }
        return weatherResponses;
    }





    private WeatherResponse mapToWeatherResponse(WeatherApiResponse apiResponse) {
        return new WeatherResponse(
                apiResponse.location.name,
                apiResponse.location.country,
                apiResponse.location.lat,
                apiResponse.location.lon,
                apiResponse.current.temp_c,
                getTemperatureColor(apiResponse.current.temp_c),
                apiResponse.current.wind_kph,
                getWindColor(apiResponse.current.wind_kph),
                apiResponse.current.cloud,
                getCloudColor(apiResponse.current.cloud)
        );
    }

    private String getTemperatureColor(double tempC) {
        if (tempC <= -30) return "#003366";
        if (tempC <= -20) return "#4A90E2";
        if (tempC <= -10) return "#B3DFFD";
        if (tempC == 0) return "#E6F7FF";
        if (tempC < 10) return "#D1F2D3";
        if (tempC < 20) return "#FFFACD";
        if (tempC < 30) return "#FFCC80";
        if (tempC < 40) return "#FF7043";
        return "#D32F2F";
    }

    private String getWindColor(double windKph) {
        if (windKph < 10) return "#E0F7FA";
        if (windKph < 20) return "#81D4FA";
        if (windKph < 40) return "#4DD0E1";
        if (windKph < 60) return "#0288D1";
        return "#01579B";
    }

    private String getCloudColor(int cloud) {
        if (cloud < 10) return "#FFF9C4";
        if (cloud < 30) return "#FFF176";
        if (cloud < 60) return "#E0E0E0";
        if (cloud < 90) return "#9E9E9E";
        return "#616161";
    }

}
