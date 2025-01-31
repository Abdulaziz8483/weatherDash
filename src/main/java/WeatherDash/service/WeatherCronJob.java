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
            // O'zbekiston va Markaziy Osiyo
            "Tashkent", "Samarkand", "Astana", "Bishkek", "Dushanbe", "Ashgabat",

            // Yevropa
            "London", "Paris", "Berlin", "Rome", "Madrid", "Moscow", "Amsterdam",
            "Vienna", "Warsaw", "Prague", "Stockholm", "Oslo", "Copenhagen",
            "Brussels", "Helsinki", "Athens", "Lisbon", "Budapest", "Dublin",
            "Zurich", "Barcelona", "Munich", "Milan", "Belgrade", "Sofia",

            // Amerika
            "New York", "Los Angeles", "Chicago", "Toronto", "Mexico City",
            "Buenos Aires", "Rio de Janeiro", "Santiago", "Lima", "Bogota",
            "San Francisco", "Houston", "Miami", "Washington D.C.", "Montreal",
            "Guadalajara", "Brasilia", "Caracas", "Quito", "Panama City",

            // Osiyo
            "Tokyo", "Seoul", "Beijing", "Shanghai", "Mumbai", "Delhi",
            "Bangkok", "Kuala Lumpur", "Jakarta", "Manila", "Singapore",
            "Hanoi", "Dhaka", "Karachi", "Colombo", "Islamabad", "Kathmandu",
            "Riyadh", "Abu Dhabi", "Dubai", "Tel Aviv", "Tehran", "Doha",
            "Amman", "Beirut", "Baghdad", "Kuwait City", "Yerevan", "Baku",

            // Afrika
            "Cairo", "Nairobi", "Cape Town", "Lagos", "Accra", "Algiers",
            "Addis Ababa", "Kinshasa", "Johannesburg", "Casablanca", "Tunis",
            "Khartoum", "Dar es Salaam", "Kampala", "Luanda", "Rabat", "Harare",

            // Avstraliya va Okeaniya
            "Sydney", "Melbourne", "Brisbane", "Auckland", "Wellington",
            "Perth", "Adelaide", "Suva", "Port Moresby"
    );
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDailyWeather() {
        weatherDataService.fetchAndSaveWeatherForCities(CITIES);
    }
}
