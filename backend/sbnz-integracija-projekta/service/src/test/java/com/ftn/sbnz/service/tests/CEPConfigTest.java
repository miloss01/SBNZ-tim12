package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.events.LogEvent;
import com.ftn.sbnz.model.models.*;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CEPConfigTest {

    @Test
    public void testPrvi() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("cepKsession");
        SessionPseudoClock clock = ksession.getSessionClock();

        AppUserOld user1 = new AppUserOld("1.1.1.1", false);
        LogEvent log1 = new LogEvent(user1, true);
        LogEvent log2 = new LogEvent(user1, false);
        LogEvent log3 = new LogEvent(user1, false);
        LogEvent log4 = new LogEvent(user1, false);
        LogEvent log5 = new LogEvent(user1, false);

        AppUser user = new AppUser();
        user.setUsername("123");
        ksession.insert(user);

        int rulesFired = -1;

        ksession.insert(user1);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
        clock.advanceTime(1, TimeUnit.SECONDS);

        ksession.insert(log1);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
        clock.advanceTime(1, TimeUnit.MINUTES);

        ksession.insert(log2);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
        clock.advanceTime(1, TimeUnit.MINUTES);

        ksession.insert(log3);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
        clock.advanceTime(1, TimeUnit.MINUTES);

        ksession.insert(log4);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
        clock.advanceTime(1, TimeUnit.MINUTES);

        ksession.insert(log5);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired + " treba da se blokira i admin da se obavesti");

    }

    @Test
    public void testAkoJeIgraIzabranogZanraPovecajScore() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("gameKsession");
        // SessionPseudoClock clock = ksession.getSessionClock();

        AppUser user = new AppUser();
        ArrayList<String> favouriteZenres = new ArrayList<>();
        favouriteZenres.add("MMORPG");
        user.setFavouriteGenres(favouriteZenres);
        user.setUsername("123");

        Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 0d, 8d, false, null, null);
        Game game2 = new Game("game2", "FPS", 20d, LocalDateTime.now().minusMonths(16), false, 10d, 8d, false, null, null);
        Game game3 = new Game("game3", "RPG", 30d, LocalDateTime.now().minusMonths(7), false, 20d, 4d, false, null, null);
        GameScore gameScore1 = new GameScore();
        GameScore gameScore2 = new GameScore();
        GameScore gameScore3 = new GameScore();

        gameScore1.setGame(game1);
        gameScore1.setUser(user);
        gameScore1.setScore(0d);

        gameScore2.setGame(game2);
        gameScore2.setUser(user);
        gameScore2.setScore(0d);

        gameScore3.setGame(game3);
        gameScore3.setUser(user);
        gameScore3.setScore(0d);


        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.now().minusDays(8L), 10, game1, new ArrayList<>());
            ksession.insert(session);
            Session session1 = new Session(LocalDateTime.now().minusDays(3L), 10, game2, new ArrayList<>());
            ksession.insert(session1);
        }

        ksession.insert(user);
        ksession.insert(game1);
        ksession.insert(game2);
        ksession.insert(game3);
        ksession.insert(gameScore1);
        ksession.insert(gameScore2);
        ksession.insert(gameScore3);

        int rulesFired = -1;

        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
    }
    @Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("cepKsession");
        SessionPseudoClock clock = ksession.getSessionClock();

        AppUser user = new AppUser();

        int rulesFired = -1;

        ksession.insert(user);
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);

    }

    @Test
    public void biljeTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("bwKsession");

        ksession.insert(new Plant("Golosemenice", "Biljka"));
        ksession.insert(new Plant("Skrivenosemenice", "Biljka"));

        ksession.insert(new Plant("Alge", "Golosemenice"));
        ksession.insert(new Plant("Kombu alga", "Alga"));
        ksession.insert(new Plant("Nori alga", "Alga"));
        ksession.insert(new Plant("Agar agar alga", "Alga"));

        ksession.insert(new Plant("Mahovine", "Golosemenice"));
        ksession.insert(new Plant("Plutajuća Riccia", "Mahovine"));
        ksession.insert(new Plant("Javanska mahovina", "Mahovine"));

        ksession.insert(new Plant("Paprati", "Golosemenice"));
        ksession.insert(new Plant("Dicksonia antarctica", "Paprati"));
        ksession.insert(new Plant("Blechnum nudum", "Paprati"));

        ksession.insert(new Plant("Sa pupoljkom", "Skrivenosemenice"));
        ksession.insert(new Plant("Ljiljan", "Sa pupoljkom"));
        ksession.insert(new Plant("Orhideja", "Sa pupoljkom"));
        ksession.insert(new Plant("Ruza", "Sa pupoljkom"));

        ksession.insert(new Plant("Bez pupoljka", "Skrivenosemenice"));
        ksession.insert(new Plant("Drvo zivota", "Bez pupoljka"));
        ksession.insert(new Plant("Asparagus", "Bez pupoljka"));
        ksession.insert(new Plant("Hrizantema", "Bez pupoljka"));

        int c = ksession.fireAllRules();
        System.out.println(c);

    }

}
