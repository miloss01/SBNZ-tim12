package com.ftn.sbnz.service.tests;

import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.model.models.enums.SubscriptionType;
import org.drools.core.time.SessionPseudoClock;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.conf.ClockTypeOption;

public class GameTests {

    @Test
    public void testAkoJeIgraIzabranogZanraPovecajScore() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("gameKsession");
        SessionPseudoClock clock = ksession.getSessionClock();


        AppUser user = new AppUser();
        ArrayList<String> favouriteZenres = new ArrayList<>();
        favouriteZenres.add("MMORPG");
        user.setFavouriteGenres(favouriteZenres);
        user.setUsername("123");
        user.setBalance(25d);
        user.setSubscriptionType(SubscriptionType.GOLD);
        user.setTimezone("+2");

        Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 5d, 8d, true, LocalDateTime.now().minusMonths(3), LocalDateTime.now().plusMonths(3));
        Game game2 = new Game("game2", "FPS", 5d, LocalDateTime.now().minusMonths(16), false, 10d, 8d, false, null, LocalDateTime.now().minusMonths(3));
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

        AppUser friend = new AppUser("BestoFrendo", 100d, new ArrayList<>(List.of(game1, game2)));
        friend.setFavouriteGenres(new ArrayList<>());
        ArrayList favGenres = new ArrayList();
        favGenres.add("asd");
        favGenres.add("neki drugi");
        friend.setFavouriteGenres(favGenres);
        friend.setTimezone("+5");

        user.getFriends().add(friend);

        FriendScore fs1 = new FriendScore(0d, user, friend);
        FriendScore fs2 = new FriendScore(0d, friend, user);

        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 10, game1, new ArrayList<>(), user);
            Session session1 = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 15, game1, new ArrayList<>(), friend);
            ksession.insert(session);
            ksession.insert(session1);
        }

        clock.advanceTime(8, TimeUnit.DAYS);

        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 5, game2, new ArrayList<>(), user);
            Session session1 = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 5, game2, new ArrayList<>(), friend);
            ksession.insert(session);
            ksession.insert(session1);
        }
//        friend.getFriends().add(user);
        Purchase purchase = new Purchase(game1, user, LocalDateTime.now(), 30d);
        Purchase purchase1 = new Purchase(game2, user, LocalDateTime.now(), 30d);
        Purchase purchase2 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);
        Purchase purchase3 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);
        Purchase purchase4 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);

        user.getWishlist().add(game1);
        user.getWishlist().add(game2);

        ksession.insert(fs1);
        ksession.insert(fs2);


        ksession.insert(user);
        ksession.insert(game1);
        ksession.insert(game2);
        ksession.insert(game3);
        ksession.insert(gameScore1);
        ksession.insert(gameScore2);
        ksession.insert(gameScore3);
        ksession.insert(friend);

        ksession.insert(purchase);
        ksession.insert(purchase1);
        ksession.insert(purchase2);
        ksession.insert(purchase3);
        ksession.insert(purchase4);

        int rulesFired = -1;

        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
    }

    @Test
    public void forwardChaining1Test(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("forward1Ksession");
        SessionPseudoClock clock = ksession.getSessionClock();

        AppUser user = new AppUser();
        ArrayList<String> favouriteZenres = new ArrayList<>();
        favouriteZenres.add("MMORPG");
        user.setFavouriteGenres(favouriteZenres);
        user.setUsername("123");
        user.setBalance(25d);
        user.setSubscriptionType(SubscriptionType.GOLD);
        user.setTimezone("+2");


        AppUser user1 = new AppUser();
        ArrayList<String> user1FavouriteGenres = new ArrayList<>();
        user1FavouriteGenres.add("MMORPG");
        user1.setFavouriteGenres(user1FavouriteGenres);
        user1.setUsername("321");
        user1.setBalance(25d);
        user1.setSubscriptionType(SubscriptionType.PLATINUM);
        user1.setTimezone("+2");


        FriendScore fs1 = new FriendScore(0d, user, user1);
        FriendScore fs2 = new FriendScore(0d, user1, user);

        Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 5d, 8d, true, LocalDateTime.now().minusMonths(3), LocalDateTime.now().plusMonths(3));
        Game game2 = new Game("game2", "FPS", 5d, LocalDateTime.now().minusMonths(16), false, 10d, 8d, false, null, LocalDateTime.now().minusHours(5));
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

        ksession.insert(user);
        ksession.insert(user1);
        ksession.insert(game1);
        ksession.insert(game2);
        ksession.insert(game3);
        ksession.insert(gameScore1);
        ksession.insert(gameScore2);
        ksession.insert(gameScore3);

        ksession.insert(fs1);
        ksession.insert(fs2);

        int rulesFired = -1;


        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);

    }


    @Test
    public void testZaOdbranu(){

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("gameKsession");
        SessionPseudoClock clock = ksession.getSessionClock();

        List<String> gameGenres = List.of("MMORPG", "FPS", "RPG", "RTS", "Sport", "Betting", "Racing");
        List<SubscriptionType> subscriptionTypes = List.of(SubscriptionType.BRONZE, SubscriptionType.SILVER, SubscriptionType.GOLD, SubscriptionType.PLATINUM);

        int max;
        int min;

        ArrayList<AppUser> users = new ArrayList<>();
        final Integer NUM_OF_USERS = 30;
        for(int i = 0; i < NUM_OF_USERS; i++){
            AppUser user = new AppUser();
            ArrayList<String> favouriteGenres = new ArrayList<>();
            for(String genre : gameGenres){
                max = 1;
                min = 0;
                int num = (int)Math.floor(Math.random() *(max - min + 1) + min);
                if(num == 1) favouriteGenres.add(genre);
            }
            user.setFavouriteGenres(favouriteGenres);
            user.setUsername(String.valueOf(i));
            max = 200;
            min = 0;
            user.setBalance(Double.valueOf((int)Math.floor(Math.random() *(max - min + 1) + min)));

            max = 3;
            min = 0;
            int num = (int)Math.floor(Math.random() *(max - min + 1) + min);
            user.setSubscriptionType(subscriptionTypes.get(num));

            max = 12;
            min = -12;
            num = (int)Math.floor(Math.random() *(max - min + 1) + min);
            user.setTimezone(String.valueOf(num));
            users.add(user);
        }

        ArrayList<FriendScore> friendScores = new ArrayList<>();
        for(int i = 0; i < NUM_OF_USERS; i++){
            for(int j = i + 5; j < NUM_OF_USERS; j += Math.ceil(NUM_OF_USERS / 5)){
                users.get(i).getFriends().add(users.get(j));
                users.get(j).getFriends().add(users.get(i));
            }
        }

        for(int i = 0; i < NUM_OF_USERS; i++){
            for(int j = i + 1; j < NUM_OF_USERS; j++){
                FriendScore friendScore1 = new FriendScore(0d, users.get(i), users.get(j));
                FriendScore friendScore2 = new FriendScore(0d, users.get(j), users.get(i));
                friendScores.add(friendScore1);
                friendScores.add(friendScore2);
            }
        }

        ArrayList<Game> games = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            Game game = new Game();
            Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 5d, 8d, true, LocalDateTime.now().minusMonths(3), LocalDateTime.now().plusMonths(3));
            game.setName("game" + i);
            max = 6;
            min = 0;
            game.setGenre(gameGenres.get((int)Math.floor(Math.random() *(max - min + 1) + min)));
            max = 60;
            min = 0;
            game.setPrice(Math.floor(Math.random() *(max - min + 1) + min));
            max = 2 * 30 * 24;
            min = -2 * 30 * 24;
            game.setReleaseDate(LocalDateTime.now().plusHours((int)Math.floor(Math.random() *(max - min + 1) + min)));
            game.setSinglePlayer(false);
            max = 50;
            min = 0;
            game.setOnSale(Math.floor(Math.random() *(max - min + 1) + min));
            max = 10;
            min = 1;
            game.setRating(Math.floor(Math.random() *(max - min + 1) + min));
            max = 1;
            min = 0;
            int num = (int)Math.floor(Math.random() *(max - min + 1) + min);
            if(num == 1)
                game.setBeta(true);
            else
                game.setBeta(false);

            max = 2 * 30 * 24;
            min = -2 * 30 * 24;
            if(game.getBeta())
                game.setBetaReleaseDate(LocalDateTime.now().plusHours((int)Math.floor(Math.random() *(max - min + 1) + min)));
            if(game.getOnSale() != 0)
                game.setSaleEndDate(LocalDateTime.now().plusHours((int)Math.floor(Math.random() *(max - min + 1) + min)));
            games.add(game);
        }

        ArrayList<GameScore> gameScores = new ArrayList<>();
        for(AppUser user : users){
            for(Game game : games){
                GameScore gameScore = new GameScore(0d, user, game);
                gameScores.add(gameScore);
            }
        }

        for(AppUser user : users){
            ksession.insert(user);
        }
        for(FriendScore friendScore: friendScores){
            ksession.insert(friendScore);
        }
        for(Game game: games){
            ksession.insert(game);
        }
        for(GameScore gameScore : gameScores){
            ksession.insert(gameScore);
        }

        int rulesFired = -1;


        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
    }

    @Test
    public void template1Test() {
        InputStream template = GameTests.class.getResourceAsStream("/templatetable/template1.drt");

        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
                new String[]{"BRONZE", "5", "100", "5"},
                new String[]{"SILVER", "10", "200", "10"},
                new String[]{"GOLD", "20", "300", "20"},
                new String[]{"PLATINUM", "30", "400", "30"},
        });

        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);

        System.out.println(drl);

//        KieSession ksession = createKieSessionFromDRL(drl);

    }

    @Test
    public void template2Test() {
        InputStream template = GameTests.class.getResourceAsStream("/templatetable/template2.drt");

        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
                new String[]{"2", "20", "10"},
                new String[]{"4", "15", "30"},
                new String[]{"6", "10", "60"},
                new String[]{"8", "8", "100"},
        });

        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);

        System.out.println(drl);

//        KieSession ksession = createKieSessionFromDRL(drl);

    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}
