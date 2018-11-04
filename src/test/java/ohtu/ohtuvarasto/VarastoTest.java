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
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void konstruktori2Toimii() {
        Varasto varasto2 = new Varasto(10, 2);
        assertEquals(8, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2ToimiiKunSaldoAsetetaanLiianSuureksi() {
        Varasto varasto2 = new Varasto(10, 123);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2NollaaNegatiiviset() {
        Varasto varasto2 = new Varasto(-125125, -1252);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori1NollaaNegatiiviset() {
        Varasto varasto2 = new Varasto(-125125);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEiTeeMuutoksiaNegatiivisella() {
        varasto.lisaaVarastoon(3);
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonEiTeeMuutoksiaNegatiivisella() {
        varasto.lisaaVarastoon(4);
        varasto.lisaaVarastoon(-3);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonToimiiKunLisaaLiikaa() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaToimiiKunOttaaKaikki() {
        varasto.lisaaVarastoon(3);
        assertEquals(3, varasto.otaVarastosta(10), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }



    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(6);
        assertEquals("saldo = 6.0, vielä tilaa 4.0", varasto.toString());
    }
}