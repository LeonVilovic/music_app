/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.impl;

import Domain.Kupovina;
import database.DatabaseBroker;
import exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import validator.Validator;

/**
 *
 * @author Leon
 */
public class ValidatorKupovinaKompozicije implements Validator {

    DatabaseBroker dbbr;

    public ValidatorKupovinaKompozicije() {
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Kupovina k = (Kupovina) value;

            List<Long> complexKey = new ArrayList<>();
            complexKey.add(k.getKorisnikID().getKorisnikID());
            complexKey.add(k.getKompozicijaID().getKompozicijaID());
            dbbr = new DatabaseBroker();
            dbbr.connect();
            if (dbbr.getODOUsingComplexkey(k, complexKey) != null) {
                throw new Exception("Vec ste kupili ovu kompoziciju!");
            }
            dbbr.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
