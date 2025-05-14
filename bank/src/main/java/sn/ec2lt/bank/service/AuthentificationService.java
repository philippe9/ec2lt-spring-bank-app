package sn.ec2lt.bank.service;

import sn.ec2lt.bank.dto.LoginUserDto;
import sn.ec2lt.bank.dto.RegisterUserDto;
import sn.ec2lt.bank.entity.Utilisateur;

public interface AuthentificationService {

    Utilisateur SignUp(RegisterUserDto registerUserDto);
    Utilisateur login(LoginUserDto loginUserDto);
}
