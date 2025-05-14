package sn.ec2lt.bank.service;

import sn.ec2lt.bank.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService{

    Utilisateur findUtilisateurById(Long id);
    Utilisateur findUtilisateurByEmail(String email);
    List<Utilisateur> findAllUtilisateurs();
}
