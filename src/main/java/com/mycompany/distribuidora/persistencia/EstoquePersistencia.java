/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.distribuidora.model.Estoque.Produto;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ltmar
 */
public class EstoquePersistencia implements Persistencia<Produto>{
    
    private static final String PATH =
            DIRECTORY+ File.separator + "estoque.json";
    
    @Override
    public void save(List<Produto> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();        
        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Produto> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Produto> contatos = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Produto>>() {
            }.getType();
        contatos = gson.fromJson(json, tipoLista);

            if (contatos == null)
                contatos = new ArrayList<>();
        }
        return contatos;
    }


    
}
