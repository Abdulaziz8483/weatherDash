package WeatherDash.repository;

import WeatherDash.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, String> {
    WeatherDataEntity findByName(String name);
}
