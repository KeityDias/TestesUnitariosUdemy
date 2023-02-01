/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

/**
 *
 * @author keity.kase
 */
public class UsuarioBuilder {
    
    private Usuario usuario;
    
    private UsuarioBuilder (){ //Private pra não deixar nínguém criar a instância do builder externamente ao próprio builder
    }
    
    public static UsuarioBuilder umUsuario(){ //Permite que seja acessado externamente sem a necessidade de uma instância
    
        UsuarioBuilder builder = new UsuarioBuilder(); //Cria a instância do builder
        builder.usuario = new Usuario (); //Inicializa a construção do usuário
        builder.usuario.setNome("Hideki"); // Atribui ao usuário o dado nome
        return builder; //Por retornar o próprio builder, permite que sejam chamados outros métodos do próprio builder;
    }
    
    public Usuario agora (){
    
        return usuario;
    }
    
}
