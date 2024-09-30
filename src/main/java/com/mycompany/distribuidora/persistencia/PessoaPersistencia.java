package com.mycompany.distribuidora.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.distribuidora.model.Usuarios.Pessoa;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class PessoaPersistencia implements Persistencia<Pessoa> {

    private static final String PATH
            = DIRECTORY + File.separator + "pessoas.json";

    public void save(Pessoa pessoa) {
        Gson gson = new Gson();
        String json = gson.toJson(pessoa);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Pessoa> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Pessoa> contatos = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Pessoa>>() {
            }.getType();
            contatos = gson.fromJson(json, tipoLista);

            if (contatos == null) {
                contatos = new ArrayList<>();
            }
        }
        return contatos;
    }

    @Override
    public void save(List<Pessoa> itens) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
