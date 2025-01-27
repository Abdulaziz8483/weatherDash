package WeatherDash.service;


import WeatherDash.dto.RegistrationDTO;
import WeatherDash.entity.ProfileEntity;
import WeatherDash.enums.GeneralStatus;
import WeatherDash.enums.ProfileRole;
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
    @Autowired
    private ProfileRoleService profileRoleService;
    @Autowired
    private EmailSendingService emailSendingService;

    public String Registration(RegistrationDTO dto) {


        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRoleService.delete(profile.getId());
                profileRepository.delete(profile);
                //send sms /// email
            } else {
                throw new AppBadException("User already exists");
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        profileRepository.save(entity);//save

        profileRoleService.createProfileRole(entity.getId(), ProfileRole.ROLE_USER);

        emailSendingService.sendRegistrationEmail(entity.getUsername(),entity.getId());

        return "success";
    }
}
