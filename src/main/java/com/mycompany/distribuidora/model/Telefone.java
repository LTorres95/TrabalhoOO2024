package com.mycompany.distribuidora.model;

import com.mycompany.distribuidora.exception.TelefoneException;

public class Telefone {

    private String telefone;
    private int ddd;

    public Telefone(String telefoneInput) throws TelefoneException {
        try {
            setTelefone(telefoneInput);
        } catch (TelefoneException e) {
            throw new TelefoneException();
        }
    }

    //telefone válido formato "(32)99175-2829"
    //tamanho 
    public boolean validaTelefone(String telefoneInput) {
        telefoneInput = telefoneInput.trim();
        if (telefoneInput.charAt(0) != '(' || telefoneInput.charAt(3) != ')'
                || !telefoneInput.contains("-")) {
            return false;
        } else if (!(Character.isDigit(telefoneInput.charAt(1))
                && Character.isDigit(telefoneInput.charAt(2)))) {
            return false;
        } else if (telefoneInput.length() != 14 || telefoneInput.length() != 13) {
            return false;
        }

        return true;

    }

    public void setTelefone(String telefoneInput) throws TelefoneException {
        if (!validaTelefone(telefoneInput)) {
            throw new TelefoneException();
        }

        this.ddd = Integer.parseInt(telefoneInput.substring(1, 3));
        this.telefone = telefoneInput.substring(5, telefoneInput.length() - 4)
                + "-"
                + telefoneInput.substring(telefoneInput.length() - 4);
    }

    public int getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "(" + ddd + ")" + telefone;
    }

}
