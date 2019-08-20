package com.ifpb.arquivos.com.ifpb.arquivos.dao;

import com.ifpb.arquivos.modelo.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDaoArquivo {

    private File arquivo;

    public PessoaDaoArquivo() throws IOException {
        arquivo = new File("Pessoas");

        if(!arquivo.exists()) arquivo.createNewFile();
    }

    public boolean salvar(Pessoa pessoa) throws IOException, ClassNotFoundException {
        Set<Pessoa> pessoas = getPessoas();

        if(pessoas.add(pessoa)){
            atualizarArquivo(pessoas);
            return true;
        }else{
            return false;
        }

    }

    private void atualizarArquivo(Set<Pessoa> pessoas) throws IOException {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(arquivo))){
            
            
            outputStream.writeObject(pessoas);
        }
    }

    public boolean deletar(Pessoa pessoa) throws IOException, ClassNotFoundException{
        Set<Pessoa> pessoas = getPessoas();
        
        if(pessoas.remove(pessoa)){
            atualizarArquivo(pessoas);
            return true;
        }
        
        return false;
    }
    
    public boolean atualizar(Pessoa nova) throws IOException, ClassNotFoundException{
        
        Pessoa pessoaBusca = busca(nova.getCpf());
        
        if(pessoaBusca != null){
            
            deletar(pessoaBusca);
            pessoaBusca = nova;
            salvar(pessoaBusca);
            
            return true;
            
        }
        
        return false;
        
        
    }
    

    public Set<Pessoa> getPessoas() throws IOException, ClassNotFoundException {
        //Será utilizado na primeira execução (quando arquivo estiver vazio)
        if(arquivo.length()==0) return new HashSet<Pessoa>();

        try(ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(arquivo))){
            return (Set<Pessoa>) inputStream.readObject();
        }
    }

    public Pessoa busca(String cpf) throws IOException, ClassNotFoundException {
        Set<Pessoa> pessoas = getPessoas();
        
        for(Pessoa pessoa : pessoas){
            if(pessoa.getCpf().equals(cpf)){
                return pessoa;
            }
        }
        
        return null;
        
    }

}