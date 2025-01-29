package WeatherDash.dto;


import WeatherDash.enums.GeneralStatus;
import WeatherDash.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private GeneralStatus status;
    private ProfileRole role;
    private String jwtToken;
}
