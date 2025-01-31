package WeatherDash.controller;


import WeatherDash.dto.AuthDto;
import WeatherDash.dto.ProfileDto;
import WeatherDash.dto.RegistrationDTO;
import WeatherDash.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> Registration(@Valid @RequestBody RegistrationDTO dto){

        return ResponseEntity.ok().body(authService.Registration(dto));
    }

    @GetMapping("/verification/{profileId}")
    public ResponseEntity<String> regVerification(@PathVariable("profileId") Integer profileId){

        return ResponseEntity.ok().body(authService.regVerification(profileId));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}
