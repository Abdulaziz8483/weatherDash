package WeatherDash.service;


import WeatherDash.config.CustomUserDetails;
import WeatherDash.dto.AuthDto;
import WeatherDash.dto.RegistrationDTO;
import WeatherDash.entity.ProfileEntity;
import WeatherDash.enums.GeneralStatus;
import WeatherDash.enums.ProfileRole;
import WeatherDash.exps.AppBadException;
import WeatherDash.repository.ProfileRepository;
import WeatherDash.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProfileService profileService;

    public String Registration(RegistrationDTO dto) {


        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRoleService.delete(profile.getId());
                profileRepository.delete(profile);

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
        profileRepository.save(entity);

        profileRoleService.createProfileRole(entity.getId(), ProfileRole.ROLE_USER);

        emailSendingService.sendRegistrationEmail(entity.getUsername(), entity.getId());

        return "Registration successful";
    }

    public String regVerification(Integer profileId) {
        ProfileEntity profile = profileService.getbyId(profileId);
        if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
            profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
            return "verification finished";
        }
        throw new AppBadException("Verification failed. User is not in registration status");
    }

    public String login(@Valid AuthDto dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            if (authentication.isAuthenticated()) {
                CustomUserDetails profile = (CustomUserDetails) authentication.getPrincipal();
                String token = JwtUtil.encode(profile.getUsername(), profile.getId(), profile.getRole());
                return "{\"message\": \"Login successful.\", \"token\": \"" + token + "\"}";
            }
            throw new AppBadException("Invalid username or password");
        } catch (BadCredentialsException e) {
            throw new AppBadException("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppBadException("Internal server error");
        }

    }

}
