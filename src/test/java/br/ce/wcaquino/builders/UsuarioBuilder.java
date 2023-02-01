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
    
    private UsuarioBuilder (){ //Private pra n�o deixar n�ngu�m criar a inst�ncia do builder externamente ao pr�prio builder
    }
    
    public static UsuarioBuilder umUsuario(){ //Permite que seja acessado externamente sem a necessidade de uma inst�ncia
    
        UsuarioBuilder builder = new UsuarioBuilder(); //Cria a inst�ncia do builder
        builder.usuario = new Usuario (); //Inicializa a constru��o do usu�rio
        builder.usuario.setNome("Hideki"); // Atribui ao usu�rio o dado nome
        return builder; //Por retornar o pr�prio builder, permite que sejam chamados outros m�todos do pr�prio builder;
    }
    
    public Usuario agora (){
    
        return usuario;
    }
    
}
