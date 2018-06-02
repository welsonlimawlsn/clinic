package br.com.welson.clinic.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import static java.lang.Character.getNumericValue;

@Named
@RequestScoped
public class Validators {

    public void validateCpf(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        char[] cpf = ((String) o).toCharArray();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF invalido", "");
        if (cpf.length != 11) {
            throw new ValidatorException(message);
        }
        if (getDigit(cpf, 10) != getNumericValue(cpf[9])) {
            throw new ValidatorException(message);
        }
        if (getDigit(cpf, 11) != getNumericValue(cpf[10])) {
            throw new ValidatorException(message);
        }
    }

    private int getDigit(char[] cpf, int amount) {
        int sum = 0;
        int i = 0;
        for (int m = amount; m >= 2; m--) {
            sum += getNumericValue(cpf[i++]) * m;
        }
        int number = (sum * 10) % 11;
        return number == 10 ? 0 : number;
    }

}
