package WeatherDash.config;

import WeatherDash.entity.ProfileEntity;
import WeatherDash.entity.ProfileRoleEntity;
import WeatherDash.enums.GeneralStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private GeneralStatus status;
    private List<ProfileRoleEntity> role;

    public CustomUserDetails(ProfileEntity profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.surname = profile.getSurname();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
        this.status = profile.getStatus();
        this.role=profile.getRole();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
