/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author keity.kase
 */
public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {
    
    private Integer diaSemana;
    
    public DiaSemanaMatcher(Integer diaSemana){
        this.diaSemana = diaSemana; 
    
    }
    
    public void describeTo(Description desc){
        Calendar data = Calendar.getInstance();//Cria uma instância de Calendar;
        data.set(Calendar.DAY_OF_WEEK, diaSemana);// set o diaSemana (o valor que estou recebendo);
        String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR")); //Mostra a data por extenso(LONG é o formato);
        desc.appendText(dataExtenso);//Adiciona este valor ao description
    }
    
    @Override
    protected boolean matchesSafely(Date data){ //Onde a comparação é realizada
        return DataUtils.verificarDiaSemana(data, diaSemana);
    }
    
}
