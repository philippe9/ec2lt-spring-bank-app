package sn.ec2lt.bank.service.implement;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.ec2lt.bank.dto.LoginUserDto;
import sn.ec2lt.bank.dto.RegisterUserDto;
import sn.ec2lt.bank.entity.Utilisateur;
import sn.ec2lt.bank.repository.UtilisateurRepository;
import sn.ec2lt.bank.service.AuthentificationService;

@Service
public class AuthentificationServiceImple implements AuthentificationService {

    private final UtilisateurRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthentificationServiceImple(UtilisateurRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Utilisateur SignUp(RegisterUserDto input) {
        Utilisateur user = new Utilisateur();
        user.setNom(input.getFullName());
        String [] part = input.getFullName().split(" ");
        user.setPrenom(part[0]);
        user.setEmail(input.getEmail());
        user.setRole(input.getRole());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Utilisateur login(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(), loginUserDto.getPassword()
                )
        );
        return userRepository.findByEmail(loginUserDto.getEmail()).orElse(null);
    }
}
