package br.com.welson.clinic.ejb;

import br.com.welson.clinic.persistence.model.CEP;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Stateless
public class CepEJB {

    public CEP getAddress(String cep) {
        return ClientBuilder.newClient().target("https://viacep.com.br/ws/" + cep + "/json/").
                request(MediaType.APPLICATION_JSON).get(CEP.class);
    }
}
