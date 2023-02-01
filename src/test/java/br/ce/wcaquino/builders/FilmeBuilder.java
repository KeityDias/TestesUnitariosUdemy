/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

/**
 * - Apenas o método de entrada deve ser Static, os outros vão depender de já ter uma instância do builder.
 * @author keity.kase
 */
public class FilmeBuilder {
    
    private Filme filme;
    
    private FilmeBuilder(){//Private pra não deixar nínguém criar a instância do builder externamente ao próprio builder
        
    }
    
    public static FilmeBuilder umFilme (){ //Permite que seja acessado externamente sem a necessidade de uma instância
        FilmeBuilder builder = new FilmeBuilder ();//Cria a instância do builder
        builder.filme = new Filme ();//Inicializa a construção do filme
        builder.filme.setEstoque (2);// Atribui ao filme o dado estoque
        builder.filme.setNome("Harry Potter");// Atribui ao filme o dado nome
        builder.filme.setPrecoLocacao(4.0);// Atribui ao filme o dado preço
        return builder;//Por retornar o próprio builder, permite que sejam chamados outros métodos do próprio builder;
    }
     public static FilmeBuilder umFilmeSemEstoque (){ //Permite que seja acessado externamente sem a necessidade de uma instância
        FilmeBuilder builder = new FilmeBuilder ();//Cria a instância do builder
        builder.filme = new Filme ();//Inicializa a construção do filme
        builder.filme.setEstoque (0);// Atribui ao filme o dado estoque
        builder.filme.setNome("Harry Potter");// Atribui ao filme o dado nome
        builder.filme.setPrecoLocacao(4.0);// Atribui ao filme o dado preço
        return builder;//Por retornar o próprio builder, permite que sejam chamados outros métodos do próprio builder;
    }
    public FilmeBuilder semEstoque(){ //Método com uma instãncia do builder
        filme.setEstoque(0);
        return this; //Por estar num método de instância, retorna-se a própria instância
    }
    
    public FilmeBuilder comVaor(Double valor){ //Método para alterar o valor do filme, criado se necessário seguindo as RNs;
        filme.setPrecoLocacao(valor);
        return this;
    }
    
    public Filme agora(){ //O Agora, informa ao builder que finalizei o encadeamento de todos os métodos de criação;
        return filme;
    }
}
