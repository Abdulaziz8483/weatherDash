package WeatherDash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherCronJob {

    private final WeatherDataService weatherDataService;
    private static final List<String> CITIES = List.of(
            "Tashkent", "New York", "London", "Tokyo", "Moscow",
            "Paris", "Berlin", "Beijing", "Istanbul", "Seoul"
    );
    @Scheduled(cron = "0 */5 * * * ?")
    public void updateDailyWeather() {
        weatherDataService.fetchAndSaveWeatherForCities(CITIES);
    }
}
