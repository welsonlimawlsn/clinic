package br.com.welson.clinic.persistence.model;

import br.com.welson.clinic.utils.CryptographyUtil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class PasswordRecovery extends AbstractEntity {

    @Column(nullable = false)
    private String uniqueKey;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    private ApplicationUser applicationUser;
    @Column(nullable = false)
    private LocalDateTime expiration = LocalDateTime.now().plusHours(24);

    public PasswordRecovery() {
    }

    public PasswordRecovery(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        configUniqueKey();
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public void configUniqueKey() {
        uniqueKey = CryptographyUtil.encodeEmailForRecoveyAndConfirmation(applicationUser.getEmail(), expiration);
    }
}
