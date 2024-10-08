package com.mycompany.distribuidora.model;

import com.mycompany.distribuidora.exception.CNPJFException;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class CNPJ {

    private String cnpj;

    public CNPJ(String cnpj) throws CNPJFException {
        try {
            setCNPJ(cnpj);
        } catch (CNPJFException e) {
            throw new CNPJFException();
        }
    }

    public static boolean ehValido(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        // Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            // converte o i-ésimo caractere do CNPJ em um número:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posição de '0' na tabela ASCII)
            num = (int) (CNPJ.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig13 = '0';
        } else {
            dig13 = (char) ((11 - r) + 48);
        }

        // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = (int) (CNPJ.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - r) + 48);
        }

        // Verifica se os dígitos calculados conferem com os dígitos informados.
        return ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)));
    }

    public void setCNPJ(String CNPJ) throws CNPJFException {
        // máscara do CNPJ: 99.999.999.9999-99
        if (!ehValido(CNPJ)) {
            throw new CNPJFException();
        }

        cnpj = (CNPJ.substring(0, 2) + "."
                + CNPJ.substring(2, 5) + "."
                + CNPJ.substring(5, 8) + "."
                + CNPJ.substring(8, 12) + "-"
                + CNPJ.substring(12, 14));
    }

    @Override
    public String toString() {
        return cnpj;
    }

}
