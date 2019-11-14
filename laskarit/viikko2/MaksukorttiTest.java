package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        //kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
     public void konstruktoriAsettaaSaldonOikein(){
        Maksukortti kortti = new Maksukortti(10);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldoaOikein(){
        Maksukortti kortti = new Maksukortti(10);
        Kassapaate paate = new Kassapaate();
         
        paate.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 7 euroa", kortti.toString());
     }
     
    public void latausToimiiOikein(){
        Maksukortti kortti = new Maksukortti(10);
        kortti.lataaRahaa(10);
        assertEquals("Kortilla on rahaa 20.0 euroa", kortti.toString());
    }
    
    public void liianVahanRahaa(){
        Maksukortti kortti = new Maksukortti(1); 
        Kassapaate paate = new Kassapaate();
         
        paate.syoEdullisesti(kortti);
        assertEquals("Kortilla on rahaa 1.0 euroa", kortti.toString());
    }
    
    public void riittaakoRahat(){
        Maksukortti kortti = new Maksukortti(1);
        Kassapaate paate = new Kassapaate();
         
        paate.syoEdullisesti(kortti);
        assertEquals(false, paate.syoEdullisesti(kortti));
        kortti.lataaRahaa(10);
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
}
