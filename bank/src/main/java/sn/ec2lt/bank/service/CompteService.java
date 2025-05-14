package sn.ec2lt.bank.service;

import sn.ec2lt.bank.entity.Compte;

import java.util.List;

public interface CompteService {

    Compte getCompte(long id);
    Compte saveCompte(Compte compte);
    void deleteCompte(long id);
    List<Compte> getComptes();
    Compte getCompteByUserId(long id);
}
