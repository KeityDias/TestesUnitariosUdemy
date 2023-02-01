/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Para reduzir o c�digo replicado, fazer a parametriza��o, possibilitando a
 * cria��o din�mica de testes baseando-se no conjunto de dados que desejamos
 * testar, utilizaremos o Parameterize do JUnit; 
 *- Nome desta t�cnica � Data Driven Test (teste orientado a dados);
 * 
 *- *** Essa classe substitui todos o m�todos da LocacaoServiceTeste desde devePagar75PctoNoFilme3 at� devePagar0PctoNoFilme6***
 *
 * @author keity.kase
 */
@RunWith(Parameterized.class) 
public class CalculoValorLocacaoTest {

    //Vari�veis necess�rias para rodar o teste: Lista de filmes e o resultado do valor da loca��o.
    private LocacaoService service;
    
    @Parameter //Inicial - 0 (Usado para linkar os par�metros definidos em Collection com as vari�veis usadas no @Test).
    public List<Filme> filmes;
    
    @Parameter(value=1) //(Usado para linkar os par�metros definidos em Collection com as vari�veis usadas no @Test).
    public Double valorLocacao; 
    
    @Parameter (value=2)
    public String cenario;

    @Before
    public void setup() {
        service = new LocacaoService();
    }
    //Valores da lista armazenados em vari�veis
    private static Filme filme1 = umFilme().agora();
    private static Filme filme2 = umFilme().agora();
    private static Filme filme3 = umFilme().agora();
    private static Filme filme4 = umFilme().agora();
    private static Filme filme5 = umFilme().agora();
    private static Filme filme6 = umFilme().agora();
    private static Filme filme7 = umFilme().agora();
    
    /**
     * Define o conjunto de dados que ser� testado;
     * Os dados do teste devem ficar em um Array;
     * O m�todo que define a cole��o deve sempre ser est�tico, pois ele 
     * define a quantidade de execu��es de cada m�todo de teste
     * o JUnit precisa dessa informa��o antes mesmo da instancia��o dessa classe;  
     * Nesse caso h� 4 linhas de dados, ent�o ser�o executados 4 testes;
     * @return 
     */ 
    //Informa ao JUnit que esse m�todo ser� a base de dados
    @Parameters (name= "{2}") //Par�metro 2;
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][] {
            {Arrays.asList(filme1, filme2), 8.0, "2 Filmes, sem desconto"},
            {Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes, 25% de desconto"},
            {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes, 50% de desconto"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes, 75% de desconto"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes, 100% de desconto"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes, sem desconto"},
        });
    }

    @Test //Possui um nome gen�rico porque servir� para todas as situa��es em que h� desconto
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {

        // Cenario
        Usuario usuario = new Usuario("Miguel Haruo");

        //A��o
        /*Instanciar a a��o/ servi�o se ela n�o estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico � alugar filmes, loca��o resultado � onde captura o resultado;

        //Verifica��o dos valores de loca��o
        assertThat(resultado.getValor(), is(valorLocacao));
    }
    
    @Test
    public void print(){
        System.out.println(valorLocacao); //Imprime os valores na tela
    }

}
