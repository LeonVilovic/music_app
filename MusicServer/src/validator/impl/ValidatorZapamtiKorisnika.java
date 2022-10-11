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
public class ValidatorZapamtiKorisnika implements Validator{

    public ValidatorZapamtiKorisnika() {
    }

    
    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Korisnik korisnik = (Korisnik) value;
            if (korisnik.getIme() == null || korisnik.getIme().matches("")) {
                throw new Exception("Polje Ime je obavezno!");
            }
            
            if (korisnik.getIme().contains("\\")||korisnik.getIme().contains("?")||korisnik.getIme().contains("$")||korisnik.getIme().contains("#")||korisnik.getIme().contains("!")||korisnik.getIme().contains("&")||korisnik.getIme().contains("^")) {
                throw new Exception("Ime ne sme zadrzati sledece karaktere: ?&^%$#@!\\");
            }
            if (korisnik.getPrezime().contains("\\")||korisnik.getPrezime().contains("?")||korisnik.getPrezime().contains("&")||korisnik.getPrezime().contains("^")||korisnik.getPrezime().contains("%")||korisnik.getPrezime().contains("!")||korisnik.getPrezime().contains("&")) {
                throw new Exception("Prezime ne sme zadrzati sledece karaktere: ?&^%$#@!\\");
            }
            if (korisnik.getPrezime()== null || korisnik.getPrezime().matches("")) {
                throw new Exception("Polje Prezime je obavezno!");
            }
            if (korisnik.getUsername() == null || korisnik.getUsername().matches("")) {
                throw new Exception("Polje Username je obavezno!");
            }
            if (korisnik.getUsername().length()<3 ) {
                throw new Exception("Username mora imati bar 3 slova");
            }
            if (korisnik.getPassword()== null || korisnik.getPassword().matches("")) {
                throw new Exception("Polje Password je obavezno!");
            }
            if (korisnik.getPassword().length()<3) {
                throw new Exception("Password mora imati bar 3 slova");
            }
            if(!korisnik.getEmail().isEmpty())
            if(!korisnik.getEmail().contains("@")){
                throw new Exception("E-mail nije validan");
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
               
    
    }
    
}
