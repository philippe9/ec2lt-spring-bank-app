package sn.ec2lt.bank.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sn.ec2lt.bank.entity.Utilisateur;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository repository;


    @Test
    public void testSaveUtilisateur() {
        Optional<Utilisateur>   utilisateur = repository.findByEmail("sergio@gmail.com");

        assertTrue(utilisateur.isPresent());
        assertEquals("sergio@gmail.com", utilisateur.get().getEmail());
    }
}
