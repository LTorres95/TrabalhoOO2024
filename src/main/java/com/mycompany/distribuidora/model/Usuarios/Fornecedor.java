package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.model.*;
import com.mycompany.distribuidora.exception.CNPJFException;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Fornecedor {

    private String nomeSocial;
    private Telefone telefone;
    private Email email;
    private String endereco;
    private CNPJ cnpj;

    public Fornecedor(String nomeSocial, Telefone telefone, Email email,
            String endereco, String cnpj) throws CNPJFException {
        this.nomeSocial = nomeSocial;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cnpj = new CNPJ(cnpj);
    }

}