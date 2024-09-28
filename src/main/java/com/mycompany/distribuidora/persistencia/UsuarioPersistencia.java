/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.persistencia;

import com.mycompany.distribuidora.controller.Usuario;

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
public class UsuarioPersistencia implements Persistencia<Usuario>  {

    private static final String FILE_NAME = "usuarios.json";
    private Gson gson;

    public UsuarioPersistencia() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarUsuario(Usuario usuario) {
        List<Usuario> usuarios = carregarUsuarios();
        usuarios.add(usuario);
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar cadastro: " + e.getMessage());
        }
    }

    public List<Usuario> carregarUsuarios() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Usuario[] usuariosArray = gson.fromJson(reader, Usuario[].class);
            return new ArrayList<>(List.of(usuariosArray));
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado; retorna lista vazia
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar cadastros: " + e.getMessage());
        }
    }

    @Override
    public void save(List<Usuario> itens) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
