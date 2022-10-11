/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.impl;

import Domain.Korisnik;
import exception.ValidationException;
import validator.Validator;

/**
 *
 * @author Leon
 */
public class ValidatorUlogujKorisnka implements Validator {

    public ValidatorUlogujKorisnka() {
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Korisnik korisnik = (Korisnik) value;
            if ((korisnik.getUsername() == null || korisnik.getUsername().matches("")) && (korisnik.getPassword() == null || korisnik.getPassword().matches(""))) {
                throw new Exception("Niste uneli ni username ni password!");
            }

            if (korisnik.getUsername() == null || korisnik.getUsername().matches("")) {
                throw new Exception("Niste uneli username!");
            }

            if (korisnik.getPassword() == null || korisnik.getPassword().matches("")) {
                throw new Exception("Niste uneli password!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
