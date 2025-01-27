package WeatherDash.service;


import WeatherDash.dto.RegistrationDTO;
import WeatherDash.entity.ProfileEntity;
import WeatherDash.enums.GeneralStatus;
import WeatherDash.exps.AppBadException;
import WeatherDash.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String Registration(RegistrationDTO dto) {


        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRepository.delete(profile);
                //send sms /// email
            } else {
                throw new AppBadException("User already exists");
            }
        }

        ProfileEntity profile = new ProfileEntity();
        profile.setUsername(dto.getUsername());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        profile.setStatus(GeneralStatus.IN_REGISTRATION);
        profile.setCreatedDate(LocalDateTime.now());
        profile.setVisible(true);
        profileRepository.save(profile);//save

        return "success";
    }
}
