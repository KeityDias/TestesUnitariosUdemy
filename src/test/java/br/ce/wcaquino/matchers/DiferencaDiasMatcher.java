/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import java.util.Date;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author keity.kase
 */
public class DiferencaDiasMatcher extends TypeSafeMatcher <Date> {
    
    private Integer qtdDias;
    
    public DiferencaDiasMatcher (Integer qtdDias){
        this.qtdDias = qtdDias;
    }

    public void describeTo(Description description) {

    }
    
        @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qtdDias));
    }
    
}
