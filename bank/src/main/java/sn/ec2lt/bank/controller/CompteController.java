package sn.ec2lt.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ec2lt.bank.entity.Compte;
import sn.ec2lt.bank.service.CompteService;
import sn.ec2lt.bank.service.UtilisateurService;
import sn.ec2lt.bank.service.implement.CompteServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/compte")
public class CompteController {

    @Autowired
    private CompteServiceImpl compteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Compte> enregistrer(@RequestBody Compte compte) {

        compte.setUtilisateur(utilisateurService.findUtilisateurById((long) compte.getIdUtilisateur()));

        return new ResponseEntity<>(
                this.compteService.saveCompte(compte), HttpStatus.OK
        );

    }

    @GetMapping
    public ResponseEntity<List<Compte>> getComptes() {
        List<Compte> comptes =  this.compteService.getComptes();
        return ResponseEntity.ok(comptes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompte(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                this.compteService.getCompte(id),
                HttpStatus.OK
        );

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Compte> getByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(
                compteService.getCompteByUserId(userId),
                HttpStatus.OK
        );
    }

}
