/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.persistencia;

/*
 João Nilson Quintão Barros - 202276007
 Pedro Souza Pinheiro da Silva Araujo - 202165560C
 Lucas Torres Martins - 202135023
 Gustavo do Bem Ferreira - 202065036AC
*/

import com.mycompany.distribuidora.model.Usuarios.Pessoa;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
/**
 *
 * @author joao
 */
public class PessoaPersistencia implements Persistencia<Pessoa>  {

    private static final String FILE_NAME = "pessoas.json";
    private Gson gson;

    public PessoaPersistencia() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarPessoa(Pessoa usuario) {
        List<Pessoa> usuarios = carregarPessoas();
        usuarios.add(usuario);
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar cadastro: " + e.getMessage());
        }
    }

    public List<Pessoa> carregarPessoas() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Pessoa[] usuariosArray = gson.fromJson(reader, Pessoa[].class);
            return new ArrayList<>(List.of(usuariosArray));
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado; retorna lista vazia
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar cadastros: " + e.getMessage());
        }
    }

    @Override
    public void save(List<Pessoa> itens) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(itens, writer); // Converte a lista de pessoas para JSON e escreve no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a lista de pessoas: " + e.getMessage());
        }    }

    @Override
    public List<Pessoa> findAll() {
        return carregarPessoas();
    }

}
