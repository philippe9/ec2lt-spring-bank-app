package sn.ec2lt.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ec2lt.bank.entity.Compte;
import sn.ec2lt.bank.entity.Transaction;
import sn.ec2lt.bank.service.CompteService;
import sn.ec2lt.bank.service.TransactionService;
import sn.ec2lt.bank.service.implement.CompteServiceImpl;
import sn.ec2lt.bank.service.implement.TransactionServiceImple;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImple transactionService;

    @Autowired
    private CompteServiceImpl compteService;

    @PostMapping
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction transaction) {

        Compte compte = compteService.getCompte(transaction.getIdCompte());

        if(transaction.getType().equals("depot")) {
            compte.setMontant(transaction.getMontant() + compte.getMontant());
            transaction.setCompte(compteService.saveCompte(compte));

            return new ResponseEntity<>(
                    transactionService.saveTransaction(transaction),
                    HttpStatus.CREATED
            );
        }
        else if(transaction.getType().equals("retrait")) {

            if(compte.getMontant() >= transaction.getMontant()) {

                compte.setMontant(compte.getMontant() - transaction.getMontant());
                transaction.setCompte(compteService.saveCompte(compte));
                return new ResponseEntity<>(
                        transactionService.saveTransaction(transaction),
                        HttpStatus.CREATED
                );
            }

        }
        else if(transaction.getType().equals("transfert")) {
            if(compte.getMontant() >= transaction.getMontant()) {
                Compte destination = compteService.getCompte(transaction.getDestination());
                compte.setMontant(compte.getMontant() - destination.getMontant());
                destination.setMontant(destination.getMontant() + transaction.getMontant());
                transaction.setCompte(compteService.saveCompte(compte));
                compteService.saveCompte(destination);

                return new ResponseEntity<>(
                        transactionService.saveTransaction(transaction),
                        HttpStatus.CREATED
                );
            }
        }

        return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/compte/{id}")
    public ResponseEntity<List<Transaction>> getCompte(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                transactionService.getTransactionsByAccount(id),
                HttpStatus.OK
        );
    }


}
