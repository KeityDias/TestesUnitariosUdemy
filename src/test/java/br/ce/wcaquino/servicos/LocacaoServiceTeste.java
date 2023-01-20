package br.ce.wcaquino.servicos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

/**
 * - Pacotes com o mesmo nome/estrutura (permite visualizar todas as vari�veis e
 * m�todos, exceto private);
 *
 * @author keity.kase
 */
public class LocacaoServiceTeste {

    /* TESTANDO SEM AS FERRAMENTAS DO JUNIT   
          public static void main(String[] args) {
               
		//cen�rio: Onde as vari�veis s�o inicializadas;
                 LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar
                //Passa como par�metro usu�rio e filme;
                Usuario usuario = new Usuario ("Song Kang");
                Filme filme = new Filme ("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;
                
                //A��o: Onde invocamos o m�todo que queremos testar;
                Locacao locacao = service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto loca��o;
                
                //Valida��o: Avaliar se o cen�rio est� de acordo com o esperado
                System.out.println(locacao.getValor() == 5.0); //Checa o valor do filme;
                System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date())); //Checa data de loca��o;
                System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1))); //Checa data de devolu��o do filme;
              
          }*/
    /**
     * Rule -> Altera alguns comportamentos dos testes; Error colector pode ser
     * colocado no lugar de assertThat;
     */
    private LocacaoService service; //Private indica que � uma inst�ncia global, para que seja vis�vel todos os testes/classes
    
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before //Inicializa��o: feito antes dos testes;
    public void setup() {

       // LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar. Se repete emt todas as classes de teste; 
    }

    @After //Encerramento: precisa ser fechado, destru�do, apenas no final dos testes;
    public void tearDown() {
        System.out.println("After");
    }

    /**
     * Test - Indica que a classe possui testes do JUnit; Se o teste n�o manda
     * exce��o nenhuma, deixe o JUnit gerencie essa parte automaticamente;
     */
    @Test
    public void testeAlugarFilme() throws Exception { //Por conta do throws Exception, � o JUnit que gerencia a exce��o lan�ada;

        //cen�rio: Onde as vari�veis s�o inicializadas;
        //Passa como par�metro usu�rio e filme;
        Usuario usuario = new Usuario("Song Kang");
        Filme filme = new Filme("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o: Onde invocamos o m�todo que queremos testar;
        Locacao locacao = service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto loca��o;

        /*Valida��o: Avaliar se o cen�rio est� de acordo com o esperado
         Para isolar os testes, teria que ser criado uma classe teste para cada uma das assertivas abaixo (Opcional)
        Assert.assertEquals(5.0, locacao.getValor(), 0.01); //Checa o valor do filme, sendo primeiro vlrEsperado, vlrRecebido, Delta;
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true)); //Checa data de loca��o;
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1))); //Checa data de devolu��o do filme;
        
         */
        error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0))); //Valor atual - Matcher
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true)); //Checa data de loca��o;
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(false)); //Checa data de devolu��o do filme;
    }

    //             ******TRATAMENTO DE ERROS*******
    /**
     * Forma Elegante: Usada se voc� puder garantir que a exce��o est� vindo
     * apenas por um motivo, n�o traz a mensagem especificando;
     *
     * @throws Exception
     */
    //@Test(expected = Exception.class)//Informa ao teste que existe uma exce��o esperada.
    @Test(expected = FilmeSemEstoqueException.class)
    public void testeLocacao_FilmeSemEstoque() throws Exception {
        //Cen�rio
        Usuario usuario = new Usuario("Song Kang");
        Filme filme = new Filme("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto loca��o;        
    }

    /**
     * Forma Robusta: Mais indicada por capturar a exce��o e permitir o acesso a
     * mensagem vinda dela ;
     *
     * @throws Exception
     */
    @Test
    public void testeLocacao_UsuarioVazio() throws Exception {
        //Cen�rio
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        Filme filme = new Filme("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        try {
            service.alugarFilme(null, filme); //alugarFilme retorna um objeto loca��o; 
            Assert.fail("Deveria ter lan�ado alguma exce��o!");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usu�rio vazio."));
        }
    }

    /**
     * Forma Atualizada:
     *
     * @throws Exception
     */
    @Test
    public void testeLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
        //Cen�rio
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        Usuario usuario = new Usuario("Song Kang");

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque.");
        //A��o
        service.alugarFilme(usuario, null); //alugarFilme retorna um objeto loca��o;  
    }
}
