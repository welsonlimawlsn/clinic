package br.com.welson.clinic.persistence.model;

import br.com.welson.clinic.utils.CryptographyUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class ActivateAccount extends AbstractEntity {

    @Column(nullable = false)
    private String uniqueKey;
    @ManyToOne(optional = false)
    private ApplicationUser applicationUser;
    @Column(nullable = false)
    private LocalDateTime expiration = LocalDateTime.now().plusHours(24);
    @Column(nullable = false)
    private Boolean activated = false;

    public ActivateAccount() {
    }

    public ActivateAccount(ApplicationUser applicationUser) {
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public void configUniqueKey() {
        uniqueKey = CryptographyUtil.encodeEmailForRecoveyAndConfirmation(applicationUser.getEmail(), expiration);
    }
}
