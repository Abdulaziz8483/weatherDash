package WeatherDash.service;

import WeatherDash.dto.WeatherResponse;
import WeatherDash.entity.WeatherDataEntity;
import WeatherDash.repository.WeatherDataRepository;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WeatherDataService {
    private final WeatherDataRepository repository;
    private final WeatherService weatherService;

    public WeatherDataService(WeatherDataRepository repository, WeatherService weatherService, MultipartConfigElement multipartConfigElement) {
        this.repository = repository;
        this.weatherService = weatherService;
    }

    public void saveWeatherData(WeatherResponse weatherData) {
        WeatherDataEntity existingData = repository.findByName(weatherData.getName());

        if (existingData != null) {
            if (!isSameWeatherData(existingData, weatherData)) {
                WeatherDataEntity entity = updateWeatherEntity(existingData, weatherData);
                repository.save(entity);
            }
        } else {
            WeatherDataEntity entity = createWeatherEntity(weatherData);
            repository.save(entity);
        }
    }


    public void fetchAndSaveWeatherForCities(List<String> cities) {
        List<WeatherResponse> weatherData = weatherService.getWeatherForCities(cities);

        for (WeatherResponse weather : weatherData) {
            saveWeatherData(weather);
        }

        System.out.println("Weather data processed");
    }

    private boolean isSameWeatherData(WeatherDataEntity existingData, WeatherResponse newData) {
        return existingData.getName().equals(newData.getName()) &&
                existingData.getCountry().equals(newData.getCountry()) &&
                existingData.getLat() == newData.getLat() &&
                existingData.getLon() == newData.getLon() &&
                existingData.getTempC() == newData.getTemp_c() &&
                existingData.getTempColor().equals(newData.getTemp_color()) &&
                existingData.getWindKph() == newData.getWind_kph() &&
                existingData.getWindColor().equals(newData.getWind_color()) &&
                existingData.getCloud() == newData.getCloud() &&
                existingData.getCloudColor().equals(newData.getCloud_color());
    }

    public WeatherDataEntity createWeatherEntity(WeatherResponse data) {
        WeatherDataEntity entity = new WeatherDataEntity();
        entity.setName(data.getName());
        entity.setCountry(data.getCountry());
        entity.setLat(data.getLat());
        entity.setLon(data.getLon());
        entity.setTempC(data.getTemp_c());
        entity.setTempColor(data.getTemp_color());
        entity.setWindKph(data.getWind_kph());
        entity.setWindColor(data.getWind_color());
        entity.setCloud(data.getCloud());
        entity.setCloudColor(data.getCloud_color());
        return entity;
    }

    public WeatherDataEntity updateWeatherEntity(WeatherDataEntity entity, WeatherResponse data) {

        entity.setName(data.getName());
        entity.setCountry(data.getCountry());
        entity.setLat(data.getLat());
        entity.setLon(data.getLon());
        entity.setTempC(data.getTemp_c());
        entity.setTempColor(data.getTemp_color());
        entity.setWindKph(data.getWind_kph());
        entity.setWindColor(data.getWind_color());
        entity.setCloud(data.getCloud());
        entity.setCloudColor(data.getCloud_color());
        return entity;
    }


}
