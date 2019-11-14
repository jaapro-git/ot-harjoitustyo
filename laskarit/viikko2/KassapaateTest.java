package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jukka
 */
public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testi1(){
        Kassapaate paate = new Kassapaate();
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void testi2(){
        Kassapaate paate = new Kassapaate();
        paate.syoEdullisesti(250);
        paate.syoMaukkaasti(500);
        assertEquals(100640, paate.kassassaRahaa());
        assertEquals(2, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }
    
    public void testi3(){
        Kassapaate paate = new Kassapaate();
        paate.syoEdullisesti(250000);
        paate.syoMaukkaasti(500000);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void testi4(){
        Kassapaate paate = new Kassapaate();
        Maksukortti kortti = new Maksukortti(1000);
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(2, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void testi5(){
        Kassapaate paate = new Kassapaate();
        Maksukortti kortti = new Maksukortti(10);
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void testi6(){
        Kassapaate paate = new Kassapaate();
        Maksukortti kortti = new Maksukortti(10);
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, paate.kassassaRahaa());
        assertEquals(2000, kortti.saldo());
    }
}
