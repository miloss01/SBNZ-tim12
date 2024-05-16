package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.events.LogEvent;
import com.ftn.sbnz.model.models.*;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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
