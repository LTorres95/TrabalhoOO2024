package com.mycompany.distribuidora.persistencia;

import java.util.List;


public interface Persistencia<T> {
    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> findAll();
}
