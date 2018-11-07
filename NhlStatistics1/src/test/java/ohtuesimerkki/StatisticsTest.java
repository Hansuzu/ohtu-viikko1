package ohtuesimerkki;


import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class StatisticsTest {
    Player playerKurri=new Player("Kurri",   "EDM", 42, 59);
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(playerKurri);
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    @Test 
    public void hakuLoytaaPelaajan(){
        assertEquals(playerKurri, stats.search("Kurri"));
    }

    @Test 
    public void hakuPalauttaaNull(){
        assertEquals(null, stats.search("Semenkoasg"));
    }

    @Test 
    public void tiimiHakuPalauttaaOikeanMaaranPelaajia(){
        List<Player> res=stats.team("EDM");
        assertEquals(3, res.size());
    }

    @Test 
    public void topScorersHakuPalauttaaOikeanMaaranPelaajia(){
        List<Player> res=stats.topScorers(1);
        assertEquals(2, res.size());
    }
}