package com.mycompany.distribuidora.persistencia;

import java.io.*;

public class Arquivo {

    public static String le(String caminhoArquivo) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

        } catch (IOException e) {
            System.out.println("Arquivo será carregado após fechar");
        }

        return conteudo.toString();
    }

    public static void salva(String caminhoArquivo, String conteudo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            escritor.write(conteudo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar");
        }
    }
}
