package WeatherDash.config;

import WeatherDash.entity.ProfileEntity;
import WeatherDash.entity.ProfileRoleEntity;
import WeatherDash.enums.GeneralStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private GeneralStatus status;
    private ProfileRoleEntity role;

    public CustomUserDetails(ProfileEntity profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.surname = profile.getSurname();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
        this.status = profile.getStatus();
        this.role = profile.getRole().get(0);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return role.stream()
//                .map(r -> (GrantedAuthority) () -> String.valueOf(r.getRoles()))
//                .collect(Collectors.toList());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoles().name()));
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == GeneralStatus.ACTIVE;
    }
}