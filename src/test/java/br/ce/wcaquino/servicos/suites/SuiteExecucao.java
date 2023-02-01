
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.servicos.suites;

import br.ce.wcaquino.servicos.CalculadoraTeste;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTeste;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author keity.kase
 */
@RunWith(Suite.class) //� opcional
@SuiteClasses({//Define dentro todos os testes que desejo que sejam executados com essa suite;
    CalculadoraTeste.class,
    CalculoValorLocacaoTest.class,
    LocacaoServiceTeste.class
})
public class SuiteExecucao {
    /*Remova se puder, � obrigat�rio pelo Java ter uma declara��o de classe.
    -Acrescentar o BeforeClass e AfterClass quando a bateria de testes precisa de alguma
    ocnfigura��o inicia.
    -O ponto negativo da Suite � que voc� precisa ficar adicionando as classes de teste manualmente  no escopo acima;
    */
    
}
