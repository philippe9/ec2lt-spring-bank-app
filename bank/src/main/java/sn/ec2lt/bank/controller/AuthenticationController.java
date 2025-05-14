package sn.ec2lt.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ec2lt.bank.dto.LoginResponse;
import sn.ec2lt.bank.dto.LoginUserDto;
import sn.ec2lt.bank.dto.RegisterUserDto;
import sn.ec2lt.bank.entity.Utilisateur;
import sn.ec2lt.bank.service.AuthentificationService;
import sn.ec2lt.bank.service.JwtService;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthentificationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Utilisateur> register(@RequestBody RegisterUserDto registerUserDto) {
        Utilisateur registeredUser = authenticationService.SignUp(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Utilisateur authenticatedUser = authenticationService.login(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpiration());
        loginResponse.setUtilisateur(authenticatedUser);

        return ResponseEntity.ok(loginResponse);
    }

}
