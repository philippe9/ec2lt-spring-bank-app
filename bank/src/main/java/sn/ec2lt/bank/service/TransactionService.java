package sn.ec2lt.bank.service;

import sn.ec2lt.bank.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    List<Transaction> getTransactionsByAccount(long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(long id);
    Transaction getById(long id);
}
