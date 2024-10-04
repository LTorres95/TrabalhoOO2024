package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.DataException;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Data {

    private int dia;
    private int mes;
    private int ano;

    private boolean valid(int number, int lowestLimit, int greatest) {
        return number > lowestLimit & number <= greatest;
    }

    Data(int d, int m, int a) throws DataException {
        if (!valid(d, 0, 30)) {
            throw new DataException("O dia inserido é inválido. Deve estar entre 1 e 31.");
        } else if (!valid(m, 0, 12)) {
            throw new DataException("O mês inserido é inválido. Deve estar entre 1 e 12.");
        } else if (!valid(a, 0, 3000)) {
            throw new DataException("O ano inserido é inválido. Deve estar entre 1 e 3000.");
        } else {
            dia = d;
            mes = m;
            ano = a;
        }
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int toDays() {
        return dia + 30 * mes + 360 * ano;
    }

    public boolean compara(Data date) throws DataException {
        if (date == null) {
            throw new DataException("A data fornecida é nula.");
        }
        return (date.getDia() == dia & date.getMes() == mes & date.getAno() == ano);
    }
}