package WeatherDash.config;


import WeatherDash.entity.ProfileEntity;
import WeatherDash.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername" + username);

        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile);
    }

}
