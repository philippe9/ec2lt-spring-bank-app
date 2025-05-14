package sn.ec2lt.bank.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ec2lt.bank.entity.Transaction;
import sn.ec2lt.bank.repository.TransactionRepository;
import sn.ec2lt.bank.service.TransactionService;

import java.util.List;

@Service
public class TransactionServiceImple implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(long id) {
        return transactionRepository.findByAccountId(id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(long id) {
        this.transactionRepository.deleteById(id);
    }

    @Override
    public Transaction getById(long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }
}
