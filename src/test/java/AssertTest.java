/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe com anota��o Test ser� sempre void;
 * Utilizar o m�nimo de nega��es poss�veis;
 * @author keity.kase
 */
public class AssertTest {
    
    @Test
    public void Test(){
        Assert.assertTrue(true); //Verifica se a express�o � verdadeira;
        Assert.assertFalse(false); //Verifica se a express�o � falsa;
        Assert.assertEquals(1,1);//Verifica se um valor a outro, mas trata cada tipo de uma forma diferente;
        Assert.assertEquals("Erro de compara��o.",1,2); //Tamb�m podemos acrescentar uma mensagem de erro na frente;
        Assert.assertEquals(0.52, 0.52, 0.01);//� informado os valores double para comparar e um Delta de compara��o/ margem de erro no final;
        Assert.assertEquals(Math.PI, 3.14, 0.01);//Nesse caso como PI � infinito, � obrigat�rio colocar uma margem de erro considerandp apenas os valores que eu quero;
        
        //Todos os tipos primitivos possuem uma representa��o em forma de objeto:
        int i= 5; //Primitivo;
        Integer i2= 5; //Objeto;
        //Para compar�-los � preciso converter eles para o mesmo tipo de dado:
        Assert.assertEquals(Integer.valueOf(i), i2); //Passa o tipo primitivo para objeto;
        Assert.assertEquals(i, i2.intValue()); //Passa o objeto para o tipo primitivo;
        
        //Compara��o de String:
        Assert.assertEquals("bola", "bola"); //Simples
        Assert.assertNotEquals("bola", "casa"); //Simples - Compara se eles s�o diferentes;
        Assert.assertTrue("Bola".equalsIgnoreCase("bola")); //Ignora letras mai�sculas e min�sculas;
        Assert.assertTrue("bola".startsWith("bo")); //Ignora o radical, encontrando tudo que come�a com "bo";
        
        //Compara��o de Objetos
        Usuario Song1 = new Usuario("Song"); //Se esse usu�rio n�o tem equals declarado, � comparado o extends da Superclasse Object,
        Usuario Song2 = new Usuario("Song");//e nesse caso, daria erro porque  eles n�o tem a mesma inst�ncia/ id;
        Usuario Song3 = null;
        
        Assert.assertEquals(Song1, Song2); //Com o Equals implementado, ele compara os atributos, nesse caso, os nomes "Song";
        Assert.assertSame(Song1, Song2); //Compara a inst�ncia mesmo com o Equals implementado na classe Usu�rio;
        Assert.assertNotSame(Song1, Song2); //Compara se as inst�ncias s�o diferentes;
        Assert.assertTrue(Song3 == null); //Verifica se o objeto Song3 � nulo;
        Assert.assertNull(Song3); //Verifica se o objeto Song3 � nulo;
        Assert.assertNotNull(Song3); //Verifica se o objeto Song3 n�o � nulo;
       
    }
    
}
