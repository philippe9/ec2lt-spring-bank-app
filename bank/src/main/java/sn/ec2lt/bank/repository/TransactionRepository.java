package sn.ec2lt.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.ec2lt.bank.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("select t from Transaction t where t.compte.id = :id")
    List<Transaction> findByAccountId(@Param("id") Long accountId);
}
