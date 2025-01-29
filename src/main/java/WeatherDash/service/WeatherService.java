package WeatherDash.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    private static final String API_KEY = "3a7dab2d5f7e4302a90120723252701";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    public Mono<String> getWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", API_KEY)
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .bodyToMono(String.class).doOnEach(System.out::println);
    }
}
