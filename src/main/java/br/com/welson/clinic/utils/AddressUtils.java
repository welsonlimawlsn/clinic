package br.com.welson.clinic.utils;

import br.com.welson.clinic.persistence.model.Address;
import br.com.welson.clinic.persistence.model.CEP;
import br.com.welson.clinic.persistence.model.Clinic;

public class AddressUtils {

    public static void connectAddressToClinic(CEP address, Clinic clinic) {
        try {
            clinic.getAddress().setState(address.getUf());
            clinic.getAddress().setDistrict(address.getBairro());
            clinic.getAddress().setCity(address.getLocalidade());
            clinic.getAddress().setAddress(address.getLogradouro());
            clinic.getAddress().setComplement(address.getComplemento());
        } catch (Exception e) {
            clinic.setAddress(new Address());
            throw new RuntimeException("CEP invalido!");
        }
    }
}
