package sn.ec2lt.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ec2lt.bank.entity.Utilisateur;


@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;

    private long expiresIn;

    private Utilisateur user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Utilisateur getUtilisateur() {
        return user;
    }

    public void setUtilisateur(Utilisateur user) {
        this.user = user;
    }

}
