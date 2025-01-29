package WeatherDash.controller;

import WeatherDash.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Mono<String> getWeather(@PathVariable String city){
        return weatherService.getWeather(city);
    }

}
