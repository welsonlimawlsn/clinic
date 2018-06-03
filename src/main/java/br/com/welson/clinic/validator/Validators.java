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
        if (getDigitCpf(cpf, 10) != getNumericValue(cpf[9])) {
            throw new ValidatorException(message);
        }
        if (getDigitCpf(cpf, 11) != getNumericValue(cpf[10])) {
            throw new ValidatorException(message);
        }
    }

    public void validateCnpj(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        char[] cnpj = ((String) o).toCharArray();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CNPJ invalido", "");
        if (cnpj.length != 14) {
            throw new ValidatorException(message);
        }
        if (getDigitCnpj(cnpj, 5) != getNumericValue(cnpj[12])) {
            throw new ValidatorException(message);
        }
        if (getDigitCnpj(cnpj, 6) != getNumericValue(cnpj[13])) {
            throw new ValidatorException(message);
        }
    }

    private int getDigitCnpj(char[] cnpj, int start) {
        int sum = 0;
        int end = start == 5 ? 11 : 12;
        for (int i = 0; i <= end; i++) {
            int n1 = getNumericValue(cnpj[i]);
            int n2 = start;
            sum += getNumericValue(cnpj[i]) * start--;
            if (start == 1) {
                start = 9;
            }
        }
        int number = 11 - (sum % 11);
        return number;
    }

    private int getDigitCpf(char[] cpf, int amount) {
        int sum = 0;
        int i = 0;
        for (int m = amount; m >= 2; m--) {
            sum += getNumericValue(cpf[i++]) * m;
        }
        int number = (sum * 10) % 11;
        return number == 10 ? 0 : number;
    }

}
