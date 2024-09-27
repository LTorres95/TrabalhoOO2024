package com.mycompany.distribuidora.controller;

import com.mycompany.distribuidora.model.Telefone;
import com.mycompany.distribuidora.controller.Pessoa;

public class Gerente extends Pessoa {


    public Gerente(String nome, Telefone telefone, String email) {
        super(nome, telefone, email);
    }
}
