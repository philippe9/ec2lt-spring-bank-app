package sn.ec2lt.bank.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ec2lt.bank.entity.Compte;
import sn.ec2lt.bank.repository.CompteRepository;
import sn.ec2lt.bank.service.CompteService;

import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private CompteRepository compteRepository;


    @Override
    public Compte getCompte(long id) {
        return compteRepository.findById(id).orElse(null);
    }

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void deleteCompte(long id) {
        this.compteRepository.deleteById(id);
    }

    @Override
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Compte getCompteByUserId(long id) {
        return compteRepository.findUtilisateurById(id);
    }
}
