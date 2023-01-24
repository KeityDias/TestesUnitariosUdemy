/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

/**
 *
 * @author keity.kase
 */
class Calculadora {

    int somar(int a, int b) {
        return a + b;
    }

    int subtrair(int a, int b) {
        return a - b;

    }

    int multiplicar(int a, int b) {
        return a*b;
    }

    int dividir(int a, int b) throws NaoPodeDividirPorZeroException {
        if (b==0){
            throw new NaoPodeDividirPorZeroException();
        }
        return a/b;
    }
}
