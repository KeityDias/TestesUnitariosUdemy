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
import br.ce.wcaquino.matchers.DiaSemanaMatcher;
import static br.ce.wcaquino.matchers.MatcherProprios.caiNumaSegunda;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Assume;
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

    //private static int contador=0;// defini��o contador que imprime seu valor sendo incrementado a cada teste;
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before //Inicializa��o: feito antes dos testes;
    public void setup() {

        service = new LocacaoService();  //Inst�ncia da classe que desejo testar. Se repete emt todas as classes de teste; 
        //contador++;// Incrementa��o do contador
        //System.out.println(contador);
    }

    /*
    @After //Encerramento: precisa ser fechado, destru�do, apenas no final dos testes;
    public void tearDown() {
        System.out.println("After");
    }
    
    
    @BeforeClass //Ao inv�s de ser executado antes de cada m�todo, � iniciado antes da classe ser inst�nciada. Precisa ser static para poder ser acessado antes da classe ser criada.
    public static void setupClass() {
             System.out.println("Before Class");
       
    }

    @AfterClass //Executado ap�s a classe ser instanciada. Precisa ser static para poder ser acessado antes da classe ser criada.
    public static void tearDownClass() {
        System.out.println("After Class");
    }
     */
    /**
     * Test - Indica que a classe possui testes do JUnit. Se o teste n�o manda
     * exce��o nenhuma, deixe o JUnit gerencie essa parte automaticamente;
     */
    @Test
    public void deveAlugarFilme() throws Exception { //Por conta do throws Exception, � o JUnit que gerencia a exce��o lan�ada;
        //Esse teste � executado se hoje (new Date()) n�o for s�bado;
         Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        
        //cen�rio: Onde as vari�veis s�o inicializadas;
        //Passa como par�metro usu�rio e filme;
        Usuario usuario = new Usuario("Song Kang");
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 2, 5.0)); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o: Onde invocamos o m�todo que queremos testar;
        Locacao locacao = service.alugarFilme(usuario, filmes); //alugarFilme retorna um objeto loca��o;

        /*Valida��o: Avaliar se o cen�rio est� de acordo com o esperado
         Para isolar os testes, teria que ser criado uma classe teste para cada uma das assertivas abaixo (Opcional)
        Assert.assertEquals(5.0, locacao.getValor(), 0.01); //Checa o valor do filme, sendo primeiro vlrEsperado, vlrRecebido, Delta;
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true)); //Checa data de loca��o;
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1))); //Checa data de devolu��o do filme;
        
         */
        
        //error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1)); //Recebe uma data no primeiro parametro e um inteiro na chamada;
        
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
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
        //Cen�rio
        Usuario usuario = new Usuario("Song Kang");
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 0, 5.0)); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        service.alugarFilme(usuario, filmes); //alugarFilme retorna um objeto loca��o;        
    }

    /**
     * Forma Robusta: Mais indicada por capturar a exce��o e permitir o acesso a
     * mensagem vinda dela ;
     *
     * @throws Exception
     */
    @Test
    public void naoDeveAlugarFilmeSemUsuarioVazio() throws Exception {
        //Cen�rio
        // LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 2, 5.0)); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        try {
            service.alugarFilme(null, filmes); //alugarFilme retorna um objeto loca��o; 
            Assert.fail("Deveria ter lan�ado alguma exce��o!");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usu�rio vazio."));
        }
    }

    /**
     * Forma Atualizada:
     *
     * @throws FilmeSemEstoqueException
     * @throws LocadoraException
     */
    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
        //Cen�rio
        //  LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        Usuario usuario = new Usuario("Song Kang");

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque.");
        //A��o
        service.alugarFilme(usuario, null); //alugarFilme retorna um objeto loca��o;  
    }

    @Test
    public void devePagar75PctoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Ana Beatriz");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Dr�cula", 2, 4.0));

        //A��o
        /*Instanciar a a��o/ servi�o se ela n�o estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico � alugar filmes, loca��o resultado � onde captura o resultado;

        //Verifica��o 4+4+3 = 11
        assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar50PctoNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("J�lia Mayumi");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Um amor para recordar", 2, 4.0),
                new Filme("Dr�cula", 2, 4.0));

        //A��o
        /*Instanciar a a��o/ servi�o se ela n�o estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico � alugar filmes, loca��o resultado � onde captura o resultado;

        //Verifica��o 4+4+3+2 = 13
        assertThat(resultado.getValor(), is(13.0));
    }

    @Test
    public void devePagar25PctoNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Miguel Haruo");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Um amor para recordar", 2, 4.0),
                new Filme("Business Proposal", 2, 4.0),
                new Filme("Dr�cula", 2, 4.0));

        //A��o
        /*Instanciar a a��o/ servi�o se ela n�o estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico � alugar filmes, loca��o resultado � onde captura o resultado;

        //Verifica��o 4+4+3+2+1 = 14
        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void devePagar0PctoNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Suegler Dias");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Um amor para recordar", 2, 4.0),
                new Filme("Business Proposal", 2, 4.0),
                new Filme("At� o �ltimo homem", 2, 4.0),
                new Filme("Dr�cula", 2, 4.0));

        //A��o
        /*Instanciar a a��o/ servi�o se ela n�o estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico � alugar filmes, loca��o resultado � onde captura o resultado;

        //Verifica��o 4+4+3+2+1+0 = 14
        assertThat(resultado.getValor(), is(14.0));
    }
    /**
     * Para n�o deixar devolver Filme no domingo, usar na verifica��o: DataUtils.verificarDiaSemana(data, diaSemana)
     * @throws FilmeSemEstoqueException
     * @throws LocadoraException 
     */
    @Test
    public void deveDevolverFilmeNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY)); //new Date = data atual;
        //Executa o teste apenas se estivermos em um s�bado.
        
        //cenario
        Usuario usuario = new Usuario ();
        List <Filme> filmes = Arrays.asList(new Filme ("True Beauty",1, 5.0));
        
        //a��o
        Locacao retorno = service.alugarFilme(usuario, filmes);
        
        //verifica��o (Calendar.Monday � uma constante de calendar, representada por um inteiro)
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());//Certifique-se que a data do retorno caia na Segunda-feira;
    }
}
