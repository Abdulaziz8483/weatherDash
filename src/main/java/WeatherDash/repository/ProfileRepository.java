package WeatherDash.repository;



import WeatherDash.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {

    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);

}
