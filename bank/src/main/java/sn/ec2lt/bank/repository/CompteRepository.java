package sn.ec2lt.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.ec2lt.bank.entity.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    @Query("select c from Compte c where c.utilisateur.id = :id")
    Compte findUtilisateurById(@Param("id") long id);
}
