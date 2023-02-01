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
 * Para reduzir o código replicado, fazer a parametrização, possibilitando a
 * criação dinâmica de testes baseando-se no conjunto de dados que desejamos
 * testar, utilizaremos o Parameterize do JUnit; 
 *- Nome desta técnica é Data Driven Test (teste orientado a dados);
 * 
 *- *** Essa classe substitui todos o métodos da LocacaoServiceTeste desde devePagar75PctoNoFilme3 até devePagar0PctoNoFilme6***
 *
 * @author keity.kase
 */
@RunWith(Parameterized.class) 
public class CalculoValorLocacaoTest {

    //Variáveis necessárias para rodar o teste: Lista de filmes e o resultado do valor da locação.
    private LocacaoService service;
    
    @Parameter //Inicial - 0 (Usado para linkar os parâmetros definidos em Collection com as variáveis usadas no @Test).
    public List<Filme> filmes;
    
    @Parameter(value=1) //(Usado para linkar os parâmetros definidos em Collection com as variáveis usadas no @Test).
    public Double valorLocacao; 
    
    @Parameter (value=2)
    public String cenario;

    @Before
    public void setup() {
        service = new LocacaoService();
    }
    //Valores da lista armazenados em variáveis
    private static Filme filme1 = umFilme().agora();
    private static Filme filme2 = umFilme().agora();
    private static Filme filme3 = umFilme().agora();
    private static Filme filme4 = umFilme().agora();
    private static Filme filme5 = umFilme().agora();
    private static Filme filme6 = umFilme().agora();
    private static Filme filme7 = umFilme().agora();
    
    /**
     * Define o conjunto de dados que será testado;
     * Os dados do teste devem ficar em um Array;
     * O método que define a coleção deve sempre ser estático, pois ele 
     * define a quantidade de execuções de cada método de teste
     * o JUnit precisa dessa informação antes mesmo da instanciação dessa classe;  
     * Nesse caso há 4 linhas de dados, então serão executados 4 testes;
     * @return 
     */ 
    //Informa ao JUnit que esse método será a base de dados
    @Parameters (name= "{2}") //Parâmetro 2;
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

    @Test //Possui um nome genérico porque servirá para todas as situações em que há desconto
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {

        // Cenario
        Usuario usuario = new Usuario("Miguel Haruo");

        //Ação
        /*Instanciar a ação/ serviço se ela não estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico é alugar filmes, locação resultado é onde captura o resultado;

        //Verificação dos valores de locação
        assertThat(resultado.getValor(), is(valorLocacao));
    }
    
    @Test
    public void print(){
        System.out.println(valorLocacao); //Imprime os valores na tela
    }

}
