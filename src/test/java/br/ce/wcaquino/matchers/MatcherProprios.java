/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ce.wcaquino.matchers;

import java.util.Calendar;

/**
 *
 * @author keity.kase
 */
public class MatcherProprios {

    public static DiaSemanaMatcher caiNumaSegunda() {
        return new DiaSemanaMatcher(Calendar.MONDAY);

    }
    public static DiferencaDiasMatcher ehHojeComDiferencaDias (Integer qtdDias){
        return new DiferencaDiasMatcher(qtdDias);
    }
     public static DiferencaDiasMatcher ehHoje(){
        return new DiferencaDiasMatcher(0);
    }
}
