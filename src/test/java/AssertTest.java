/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe com anotação Test será sempre void;
 * Utilizar o mínimo de negações possíveis;
 * @author keity.kase
 */
public class AssertTest {
    
    @Test
    public void Test(){
        Assert.assertTrue(true); //Verifica se a expressão é verdadeira;
        Assert.assertFalse(false); //Verifica se a expressão é falsa;
        Assert.assertEquals(1,1);//Verifica se um valor a outro, mas trata cada tipo de uma forma diferente;
        Assert.assertEquals("Erro de comparação.",1,2); //Também podemos acrescentar uma mensagem de erro na frente;
        Assert.assertEquals(0.52, 0.52, 0.01);//É informado os valores double para comparar e um Delta de comparação/ margem de erro no final;
        Assert.assertEquals(Math.PI, 3.14, 0.01);//Nesse caso como PI é infinito, é obrigatório colocar uma margem de erro considerandp apenas os valores que eu quero;
        
        //Todos os tipos primitivos possuem uma representação em forma de objeto:
        int i= 5; //Primitivo;
        Integer i2= 5; //Objeto;
        //Para compará-los é preciso converter eles para o mesmo tipo de dado:
        Assert.assertEquals(Integer.valueOf(i), i2); //Passa o tipo primitivo para objeto;
        Assert.assertEquals(i, i2.intValue()); //Passa o objeto para o tipo primitivo;
        
        //Comparação de String:
        Assert.assertEquals("bola", "bola"); //Simples
        Assert.assertNotEquals("bola", "casa"); //Simples - Compara se eles são diferentes;
        Assert.assertTrue("Bola".equalsIgnoreCase("bola")); //Ignora letras maiúsculas e minúsculas;
        Assert.assertTrue("bola".startsWith("bo")); //Ignora o radical, encontrando tudo que começa com "bo";
        
        //Comparação de Objetos
        Usuario Song1 = new Usuario("Song"); //Se esse usuário não tem equals declarado, é comparado o extends da Superclasse Object,
        Usuario Song2 = new Usuario("Song");//e nesse caso, daria erro porque  eles não tem a mesma instância/ id;
        Usuario Song3 = null;
        
        Assert.assertEquals(Song1, Song2); //Com o Equals implementado, ele compara os atributos, nesse caso, os nomes "Song";
        Assert.assertSame(Song1, Song2); //Compara a instância mesmo com o Equals implementado na classe Usuário;
        Assert.assertNotSame(Song1, Song2); //Compara se as instâncias são diferentes;
        Assert.assertTrue(Song3 == null); //Verifica se o objeto Song3 é nulo;
        Assert.assertNull(Song3); //Verifica se o objeto Song3 é nulo;
        Assert.assertNotNull(Song3); //Verifica se o objeto Song3 não é nulo;
       
    }
    
}
