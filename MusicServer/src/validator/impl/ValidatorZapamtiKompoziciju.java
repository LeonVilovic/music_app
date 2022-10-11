/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.impl;

import Domain.Kompozicija;
import database.DatabaseBroker;
import Domain.OpstiDomenskiObjekat;
import exception.ValidationException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Leon
 */
public class ValidatorZapamtiKompoziciju implements validator.Validator {

    DatabaseBroker dbbr;

    public ValidatorZapamtiKompoziciju() {
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Kompozicija kompozicija = (Kompozicija) value;
            if (kompozicija.getNaziv() == null || kompozicija.getNaziv().equals("")) {
                throw new Exception("Polje Naziv kompozicije je obavezno!");
            }

            dbbr = new DatabaseBroker();
            dbbr.connect();
            List<OpstiDomenskiObjekat> lista = dbbr.search(kompozicija, kompozicija.getNaziv());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    Kompozicija kompozicija2 = (Kompozicija) dbbr.search(kompozicija, kompozicija.getNaziv()).get(i);
                    if (Objects.equals(kompozicija.getKorisnikID().getKorisnikID(), kompozicija2.getKorisnikID().getKorisnikID())) {
                        throw new Exception("Ovaj kompozitor vec ima kompoziciju sa istim nazivom!");
                    }
                }
            }
            dbbr.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
