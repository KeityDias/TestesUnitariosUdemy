package br.ce.wcaquino.servicos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
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
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Test - Indica que a classe possui testes do JUnit; Se o teste n�o manda
     * exce��o nenhuma, deixe o JUnit gerencie essa parte automaticamente;
     */
    @Test
    public void testeAlugarFilme() throws Exception { //Por conta do throws Exception, � o JUnit que gerencia a exce��o lan�ada;

        //cen�rio: Onde as vari�veis s�o inicializadas;
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar
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
     * Forma Elegante: Usada se voc� puder garantir que a
     * exce��o est� vindo apenas por um motivo;
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)//Informa ao teste que existe uma exce��o esperada.
    public void testeFilmeSemEstoque() throws Exception {
        //Cen�rio
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
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
    public void testeFilmeSemEstoque2() throws Exception {
        //Cen�rio
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        Usuario usuario = new Usuario("Song Kang");
        Filme filme = new Filme("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        try {
            service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto loca��o; 
            Assert.fail("Deveria ter lan�ado alguma exce��o!");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("Filme sem estoque."));
        }
    }
    
    /**
     * Forma Atualizada: 
     * @throws Exception 
     */
    @Test
    public void testeFilmeSemEstoque3() throws Exception {
        //Cen�rio
        LocacaoService service = new LocacaoService();  //Inst�ncia da classe que desejo testar;
        Usuario usuario = new Usuario("Song Kang");
        Filme filme = new Filme("Love Alarme", 2, 5.0); //Nome do filme, quantidade em estoque, pre�o de loca��o;

        //A��o
        service.alugarFilme(usuario, filme); //alugarFilme retorna um objeto loca��o;  
        
        exception.expect(Exception.class);       
        exception.expectMessage("Filme sem estoque.");

        
    }
}
