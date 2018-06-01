package br.com.welson.clinic.ejb;

import br.com.welson.clinic.config.ApplicationConstants;
import br.com.welson.clinic.exception.InvalidKeyException;
import br.com.welson.clinic.persistence.dao.DAO;
import br.com.welson.clinic.persistence.model.ApplicationUser;
import br.com.welson.clinic.persistence.model.PasswordRecovery;
import br.com.welson.clinic.utils.CryptographyUtil;
import br.com.welson.clinic.utils.EmailMessage;
import br.com.welson.clinic.utils.FacesUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class PasswordRecoveryEJB {

    @Inject
    private DAO<ApplicationUser> applicationUserDAO;

    @Inject
    private DAO<PasswordRecovery> passwordRecoveryDAO;

    @EJB
    private EmailEJB emailEJB;

    public void sendMailRecoveryPassword(String mail) {
        List<ApplicationUser> searchApplicationUserByMail = applicationUserDAO.findByHQLQuery(0, "searchApplicationUserByMail", mail);
        if (searchApplicationUserByMail.size() > 0) {
            sendEmail(createPasswordRecovery(searchApplicationUserByMail.get(0)));
        }
    }

    public PasswordRecovery validateAndReturnPasswordRecovery(String key) {
        List<PasswordRecovery> searchPasswordRecoveryByKey = passwordRecoveryDAO.findByHQLQuery(0, "searchPasswordRecoveryByKey", key);
        if (searchPasswordRecoveryByKey.size() == 0 || searchPasswordRecoveryByKey.get(0).getExpiration().isBefore(LocalDateTime.now())) {
            throw new InvalidKeyException("Invalid key or key is expired");
        }
        return searchPasswordRecoveryByKey.get(0);
    }

    public void updatePassword(PasswordRecovery passwordRecovery, String newPassword) {
        ApplicationUser applicationUser = passwordRecovery.getApplicationUser();
        applicationUser.setPassword(CryptographyUtil.encodePassword(applicationUser.getUsername(), newPassword));
        passwordRecovery.setEnabled(false);
        passwordRecoveryDAO.update(passwordRecovery);
    }

    private PasswordRecovery createPasswordRecovery(ApplicationUser applicationUser) {
        return passwordRecoveryDAO.save(new PasswordRecovery(applicationUser));
    }

    private void sendEmail(PasswordRecovery passwordRecovery) {
        emailEJB.send(
                passwordRecovery.getApplicationUser().getEmail(),
                FacesUtil.getBundleValue("subjectRecoveryPasswordMail"),
                EmailMessage.getEmailMessageForRecoveryPassword(
                        passwordRecovery.getApplicationUser().getUsername(),
                        ApplicationConstants.BASE_URL + "/recovery-password.xhtml?key=" + passwordRecovery.getUniqueKey()));
    }
}
