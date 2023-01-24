/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




/**
 *
 * @author keity.kase
 */

public class CalculadoraTeste {
    
    private Calculadora calc; //Torna a calculadora global;
    
    @Before
    public void setup(){
        calc = new Calculadora();
    }
    
    @Test
    public void deveSomarDoisValores (){
    // cenário
        int a = 5;
        int b = 3;

     //ação
        int resultado =calc.somar(a,b);
        
     //verficação
        Assert.assertEquals(8, resultado);
    }
    
    @Test
    public void deveSubtrairDoisVslores(){
        //cenario
        int a= 8;
        int b= 5;
        
        //ação
        int resultado= calc.subtrair(a,b);
        
        //Verificação
        Assert.assertEquals(3, resultado);
    
    }
    
    @Test
    public void deveMultiplicarDoisValores(){
    //cenário    
    int a= 4;
    int b= 5;
    
    //ação
    int resultado= calc.multiplicar(a,b);
    
    //verificação
    Assert.assertEquals(20, resultado);
    }
    
    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
    //cenario
    int a= 10;
    int b= 5;
    
    //ação
    int resultado = calc.dividir(a,b);
    
    //verificação
    Assert.assertEquals(2, resultado);
    
    }
    @Test (expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException{
    int a = 10;
    int b= 0;
    //Calculadora calc = new Calculadora(); - não é necessário porque já está declarado no before;
    calc.dividir(a,b);
    }
    
}
