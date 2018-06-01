package br.com.welson.clinic.ejb;

import br.com.welson.clinic.exception.InvalidKeyException;
import br.com.welson.clinic.exception.KeyExpiredException;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ActivateAccount;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.utils.EmailMessage;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.welson.clinic.config.ApplicationConstants.BASE_URL;

@Stateless
public class ActivateAccountEJB implements Serializable {

    @Inject
    private DAO<ActivateAccount> activateAccountDAO;

    @EJB
    private EmailEJB emailEJB;

    public void createActivateAccount(ApplicationUser applicationUser) {
        ActivateAccount activateAccountEntity = activateAccountDAO.save(new ActivateAccount(applicationUser));
        emailEJB.send(
                applicationUser.getEmail(),
                FacesUtil.getBundleValue("subjectActivateAccountMail"),
                EmailMessage.getEmailMessageForActivateAccount(applicationUser.getUsername(), BASE_URL + "/active-account.xhtml?key=" + activateAccountEntity.getUniqueKey()));
    }

    private void createActivateAccountFromOldActivateAccount(ActivateAccount oldActivateAccount) {
        createActivateAccount(oldActivateAccount.getApplicationUser());
    }

    public void verifyIfApplicationUserIsActivated(ApplicationUser applicationUser) {
        List<ActivateAccount> applicationUserList = activateAccountDAO.findByHQLQuery(0, "searchActivateAccountByApplicationUser", applicationUser);
        if (applicationUserList.size() > 0) {
            if (!applicationUserList.get(0).getActivated()) {
                throw new RuntimeException("User is not active");
            }
        }
    }

    public ActivateAccount verifyAndReturnActivateAccount(String key) {
        List<ActivateAccount> searchActivateAccountByKey = getListFromKey(key);
        if (searchActivateAccountByKey.size() == 0 || searchActivateAccountByKey.get(0).getActivated()) {
            throw new InvalidKeyException("Key is not valid!");
        }
        if (searchActivateAccountByKey.get(0).getExpiration().isBefore(LocalDateTime.now())) {
            createActivateAccountFromOldActivateAccount(searchActivateAccountByKey.get(0));
            activateAccountDAO.delete(searchActivateAccountByKey.get(0));
            throw new KeyExpiredException("Link to activate account is expired! Another email has been sent!");
        }
        return searchActivateAccountByKey.get(0);
    }

    public void activateAccount(ActivateAccount activateAccount) {
        activateAccount.setActivated(true);
        activateAccountDAO.update(activateAccount);
    }

    private List<ActivateAccount> getListFromKey(String key) {
        return activateAccountDAO.findByHQLQuery(0, "searchActivateAccountByKey", key);
    }
}
