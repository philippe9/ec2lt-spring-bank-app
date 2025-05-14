package sn.ec2lt.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.ec2lt.bank.entity.Utilisateur;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository <Utilisateur, Long> {

    @Query("select u from Utilisateur u where u.email = :email")
    Optional<Utilisateur> findByEmail(@Param("email") String email);
}
