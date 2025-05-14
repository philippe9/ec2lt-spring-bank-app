package sn.ec2lt.bank.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ec2lt.bank.entity.Utilisateur;
import sn.ec2lt.bank.repository.UtilisateurRepository;
import sn.ec2lt.bank.service.UtilisateurService;

import java.util.List;

@Service
public class UtilisateurServiceImple implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur findUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}
