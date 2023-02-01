/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

/**
 * - Apenas o m�todo de entrada deve ser Static, os outros v�o depender de j� ter uma inst�ncia do builder.
 * @author keity.kase
 */
public class FilmeBuilder {
    
    private Filme filme;
    
    private FilmeBuilder(){//Private pra n�o deixar n�ngu�m criar a inst�ncia do builder externamente ao pr�prio builder
        
    }
    
    public static FilmeBuilder umFilme (){ //Permite que seja acessado externamente sem a necessidade de uma inst�ncia
        FilmeBuilder builder = new FilmeBuilder ();//Cria a inst�ncia do builder
        builder.filme = new Filme ();//Inicializa a constru��o do filme
        builder.filme.setEstoque (2);// Atribui ao filme o dado estoque
        builder.filme.setNome("Harry Potter");// Atribui ao filme o dado nome
        builder.filme.setPrecoLocacao(4.0);// Atribui ao filme o dado pre�o
        return builder;//Por retornar o pr�prio builder, permite que sejam chamados outros m�todos do pr�prio builder;
    }
     public static FilmeBuilder umFilmeSemEstoque (){ //Permite que seja acessado externamente sem a necessidade de uma inst�ncia
        FilmeBuilder builder = new FilmeBuilder ();//Cria a inst�ncia do builder
        builder.filme = new Filme ();//Inicializa a constru��o do filme
        builder.filme.setEstoque (0);// Atribui ao filme o dado estoque
        builder.filme.setNome("Harry Potter");// Atribui ao filme o dado nome
        builder.filme.setPrecoLocacao(4.0);// Atribui ao filme o dado pre�o
        return builder;//Por retornar o pr�prio builder, permite que sejam chamados outros m�todos do pr�prio builder;
    }
    public FilmeBuilder semEstoque(){ //M�todo com uma inst�ncia do builder
        filme.setEstoque(0);
        return this; //Por estar num m�todo de inst�ncia, retorna-se a pr�pria inst�ncia
    }
    
    public FilmeBuilder comVaor(Double valor){ //M�todo para alterar o valor do filme, criado se necess�rio seguindo as RNs;
        filme.setPrecoLocacao(valor);
        return this;
    }
    
    public Filme agora(){ //O Agora, informa ao builder que finalizei o encadeamento de todos os m�todos de cria��o;
        return filme;
    }
}
