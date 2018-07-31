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

    public static void convertCepToAddress(CEP cep, Address address) {
        try {
            address.setState(cep.getUf());
            address.setDistrict(cep.getBairro());
            address.setCity(cep.getLocalidade());
            address.setAddress(cep.getLogradouro());
            address.setComplement(cep.getComplemento());
        } catch (Exception e) {
            throw new RuntimeException("CEP invalido!");
        }
    }
}
