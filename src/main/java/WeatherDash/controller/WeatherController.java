package WeatherDash.controller;

import WeatherDash.dto.WeatherResponse;
import WeatherDash.service.WeatherDataService;
import WeatherDash.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/{city}")
    public Mono<WeatherResponse> getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);

    }

//    @GetMapping("/cities")
//    public List<WeatherResponse> getWeatherForCities(@RequestParam List<String> cities) {
//        return weatherService.getWeatherForCities(cities);
//    }

}
