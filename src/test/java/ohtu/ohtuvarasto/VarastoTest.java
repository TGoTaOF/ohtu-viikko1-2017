package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(-10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test // Omat metodit olisivat mahtavat, mutta nyt saa kelvata. :D
    public void konstruktioAlkusaldolla() {
        Varasto varasto3 = new Varasto(10, 4);
        Varasto varasto4 = new Varasto(-5, -4);
        Varasto varasto5 = new Varasto(5, 10);
        assertEquals(4, varasto3.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto4.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(7, varasto5.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-4);
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaVainTayteen() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(4);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiVahennaTiavuutta() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-4);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanMitäVoidaan() {
        varasto.lisaaVarastoon(3);
        
        assertEquals(3,varasto.otaVarastosta(7), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToString() {
        varasto.lisaaVarastoon(2);
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }

}