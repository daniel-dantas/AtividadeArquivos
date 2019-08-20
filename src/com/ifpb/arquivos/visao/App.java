package com.ifpb.arquivos.visao;

import com.ifpb.arquivos.com.ifpb.arquivos.dao.PessoaDaoArquivo;
import com.ifpb.arquivos.modelo.Pessoa;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        
        try {
            PessoaDaoArquivo dao = new PessoaDaoArquivo();
            System.out.println(dao.salvar(new Pessoa(
                    "111", "João", 20)));
            
            System.out.println(dao.getPessoas());
            
            dao.atualizar(new Pessoa("111", "Daniel", 19));
            
            
            System.out.println(dao.getPessoas());
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //usandoFileChooser();
        //apresentandoOsMetodos();

    }

    

}