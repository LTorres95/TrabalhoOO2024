/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.persistencia;

import java.io.*;
/*
 João Nilson Quintão Barros - 202276007
 Pedro Souza Pinheiro da Silva Araujo - 202165560C
 Lucas Torres Martins - 202135023
 Gustavo do Bem Ferreira - 202065036AC
*/
public class Arquivo {
    public static String le(String caminhoArquivo){
        StringBuilder conteudo = new StringBuilder();
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))){
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
            
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return conteudo.toString();
    }
    
    public static void salva(String caminhoArquivo, String conteudo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            escritor.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
