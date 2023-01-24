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
 * - Pacotes com o mesmo nome/estrutura (permite visualizar todas as variáveis e
 * métodos, exceto private);
 *
 * @author keity.kase
 */
public class LocacaoServiceTeste {

    /* TESTANDO SEM AS FERRAMENTAS DO JUNIT   
          public static void main(String[] args) {
               
		//cenário: Onde as variáveis são inicializadas;
                 LocacaoService service = new LocacaoService();  //Instância da classe que desejo testar
                //Passa como parâmetro usuário e filme;
                Usuario usuario = new Usuario ("Song Kang");
                Filme filme = new Filme ("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, preço de locação;
                
                //Ação: Onde invocamos o método que queremos testar;
                Locacao locacao = service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto locação;
                
                //Validação: Avaliar se o cenário está de acordo com o esperado
                System.out.println(locacao.getValor() == 5.0); //Checa o valor do filme;
                System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date())); //Checa data de locação;
                System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1))); //Checa data de devolução do filme;
              
          }*/
    /**
     * Rule -> Altera alguns comportamentos dos testes; Error colector pode ser
     * colocado no lugar de assertThat;
     */
    private LocacaoService service; //Private indica que é uma instância global, para que seja visível todos os testes/classes

    //private static int contador=0;// definição contador que imprime seu valor sendo incrementado a cada teste;
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before //Inicialização: feito antes dos testes;
    public void setup() {

        service = new LocacaoService();  //Instância da classe que desejo testar. Se repete emt todas as classes de teste; 
        //contador++;// Incrementação do contador
        //System.out.println(contador);
    }

    /*
    @After //Encerramento: precisa ser fechado, destruído, apenas no final dos testes;
    public void tearDown() {
        System.out.println("After");
    }
    
    
    @BeforeClass //Ao invés de ser executado antes de cada método, é iniciado antes da classe ser instânciada. Precisa ser static para poder ser acessado antes da classe ser criada.
    public static void setupClass() {
             System.out.println("Before Class");
       
    }

    @AfterClass //Executado após a classe ser instanciada. Precisa ser static para poder ser acessado antes da classe ser criada.
    public static void tearDownClass() {
        System.out.println("After Class");
    }
     */
    /**
     * Test - Indica que a classe possui testes do JUnit. Se o teste não manda
     * exceção nenhuma, deixe o JUnit gerencie essa parte automaticamente;
     */
    @Test
    public void deveAlugarFilme() throws Exception { //Por conta do throws Exception, é o JUnit que gerencia a exceção lançada;
        //Esse teste é executado se hoje (new Date()) não for sábado;
         Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        
        //cenário: Onde as variáveis são inicializadas;
        //Passa como parâmetro usuário e filme;
        Usuario usuario = new Usuario("Song Kang");
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 2, 5.0)); //Nome do filme, quantidade em estoque, preço de locação;

        //Ação: Onde invocamos o método que queremos testar;
        Locacao locacao = service.alugarFilme(usuario, filmes); //alugarFilme retorna um objeto locação;

        /*Validação: Avaliar se o cenário está de acordo com o esperado
         Para isolar os testes, teria que ser criado uma classe teste para cada uma das assertivas abaixo (Opcional)
        Assert.assertEquals(5.0, locacao.getValor(), 0.01); //Checa o valor do filme, sendo primeiro vlrEsperado, vlrRecebido, Delta;
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true)); //Checa data de locação;
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1))); //Checa data de devolução do filme;
        
         */
        
        //error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1)); //Recebe uma data no primeiro parametro e um inteiro na chamada;
        
        error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0))); //Valor atual - Matcher
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true)); //Checa data de locação;
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(false)); //Checa data de devolução do filme;
    }

    //             ******TRATAMENTO DE ERROS*******
    /**
     * Forma Elegante: Usada se você puder garantir que a exceção está vindo
     * apenas por um motivo, não traz a mensagem especificando;
     *
     * @throws Exception
     */
    //@Test(expected = Exception.class)//Informa ao teste que existe uma exceção esperada.
    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Song Kang");
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 0, 5.0)); //Nome do filme, quantidade em estoque, preço de locação;

        //Ação
        service.alugarFilme(usuario, filmes); //alugarFilme retorna um objeto locação;        
    }

    /**
     * Forma Robusta: Mais indicada por capturar a exceção e permitir o acesso a
     * mensagem vinda dela ;
     *
     * @throws Exception
     */
    @Test
    public void naoDeveAlugarFilmeSemUsuarioVazio() throws Exception {
        //Cenário
        // LocacaoService service = new LocacaoService();  //Instância da classe que desejo testar;
        List<Filme> filmes = Arrays.asList(new Filme("Love Alarme", 2, 5.0)); //Nome do filme, quantidade em estoque, preço de locação;

        //Ação
        try {
            service.alugarFilme(null, filmes); //alugarFilme retorna um objeto locação; 
            Assert.fail("Deveria ter lançado alguma exceção!");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usuário vazio."));
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
        //Cenário
        //  LocacaoService service = new LocacaoService();  //Instância da classe que desejo testar;
        Usuario usuario = new Usuario("Song Kang");

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque.");
        //Ação
        service.alugarFilme(usuario, null); //alugarFilme retorna um objeto locação;  
    }

    @Test
    public void devePagar75PctoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Ana Beatriz");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Drácula", 2, 4.0));

        //Ação
        /*Instanciar a ação/ serviço se ela não estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico é alugar filmes, locação resultado é onde captura o resultado;

        //Verificação 4+4+3 = 11
        assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar50PctoNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Júlia Mayumi");
        List<Filme> filmes = Arrays.asList(
                new Filme("Harry Potter", 3, 4.0),
                new Filme("Pride and Prejudice", 2, 4.0),
                new Filme("Um amor para recordar", 2, 4.0),
                new Filme("Drácula", 2, 4.0));

        //Ação
        /*Instanciar a ação/ serviço se ela não estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico é alugar filmes, locação resultado é onde captura o resultado;

        //Verificação 4+4+3+2 = 13
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
                new Filme("Drácula", 2, 4.0));

        //Ação
        /*Instanciar a ação/ serviço se ela não estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico é alugar filmes, locação resultado é onde captura o resultado;

        //Verificação 4+4+3+2+1 = 14
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
                new Filme("Até o último homem", 2, 4.0),
                new Filme("Drácula", 2, 4.0));

        //Ação
        /*Instanciar a ação/ serviço se ela não estiver declarada no @Before;
	service = new LocacaoService(); */
        Locacao resultado = service.alugarFilme(usuario, filmes); //O servico é alugar filmes, locação resultado é onde captura o resultado;

        //Verificação 4+4+3+2+1+0 = 14
        assertThat(resultado.getValor(), is(14.0));
    }
    /**
     * Para não deixar devolver Filme no domingo, usar na verificação: DataUtils.verificarDiaSemana(data, diaSemana)
     * @throws FilmeSemEstoqueException
     * @throws LocadoraException 
     */
    @Test
    public void deveDevolverFilmeNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY)); //new Date = data atual;
        //Executa o teste apenas se estivermos em um sábado.
        
        //cenario
        Usuario usuario = new Usuario ();
        List <Filme> filmes = Arrays.asList(new Filme ("True Beauty",1, 5.0));
        
        //ação
        Locacao retorno = service.alugarFilme(usuario, filmes);
        
        //verificação (Calendar.Monday é uma constante de calendar, representada por um inteiro)
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());//Certifique-se que a data do retorno caia na Segunda-feira;
    }
}
