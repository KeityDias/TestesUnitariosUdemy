/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.exceptions;

/**
 *
 * @author keity.kase
 */
public class FilmeSemEstoqueException extends Exception {
    
      private static final long serialVersionUID = 4970527916966267734L;

    public FilmeSemEstoqueException(String message) {
        super (message);
    }
    
}
