package com.mycompany.distribuidora.persistencia;

/*
 João Nilson Quintão Barros - 202276007
 Pedro Souza Pinheiro da Silva Araujo - 202165560C
 Lucas Torres Martins - 202135023
 Gustavo do Bem Ferreira - 202065036AC
*/

import java.util.List;


public interface Persistencia<T> {
    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> findAll();
}
