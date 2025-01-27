package WeatherDash.controller;


import WeatherDash.dto.RegistrationDTO;
import WeatherDash.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<String> Registration(@Valid @RequestBody RegistrationDTO dto){

        return ResponseEntity.ok().body(authService.Registration(dto));
    }
}
