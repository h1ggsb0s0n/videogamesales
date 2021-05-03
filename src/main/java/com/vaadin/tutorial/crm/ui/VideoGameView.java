package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.backend.service.VideoGameService;
import com.vaadin.tutorial.crm.helpers.CollectionOperations;

import java.util.Collections;
import java.util.List;

@Route("")
public class VideoGameView extends VerticalLayout {

    private VideoGameService vgService;

    private List<VideoGame> listOfVideoGames;

    public VideoGameView(VideoGameService vgservice) {
        this.vgService = vgservice;
        addClassName("list-view");
        setSizeFull();


        this.getFirst1000VideoGames();

        Button mapButton = new Button("Map");
        mapButton.addClickListener(clickEvent -> mapOperation());

        Button filterSortLimit = new Button("filter Sort Limit");
        filterSortLimit.addClickListener(clickEvent -> filterSortLimit());

        Button skipFirstTenRanksButton = new Button("Skip 10 Ranks");
        skipFirstTenRanksButton.addClickListener(clickEvent -> SkipFirstTenRanks());

        Button anymatch = new Button("anymatch");
        anymatch.addClickListener(clickEvent -> {
            startsWithB();
            startsWithASDF();
        });

        Button allMatch = new Button("allMatch");
        allMatch.addClickListener(clickEvent -> compareGenreOfBorderlandsGames());

        Button countButton = new Button("count");
        countButton.addClickListener(clickEvent -> getCountOfBorderlandsGames());

        Button minButton = new Button("min");
        minButton.addClickListener(clickEvent -> getLowestSellingBorderlandsGame());

        Button collectButton = new Button("collect");
        collectButton.addClickListener(clickEvent -> getFirst10Ranks());


        add(mapButton);
        add(filterSortLimit);
        add(skipFirstTenRanksButton);
        add(anymatch);
        add(allMatch);
        add(countButton);
        add(minButton);
        add(collectButton);

    }

    /**
     * Holt die ersten 1000 Ränge an Video Spielen und shuffled sie damit sie unsortiert getestet werden können
     */
    private void getFirst1000VideoGames(){
        this.listOfVideoGames = this.vgService.findByRankBetween(1L, 1000L);
        Collections.shuffle(this.listOfVideoGames);
        Notification.show("Number of items in List" + this.listOfVideoGames.size());

    }

    /**
     * Die Methode erstellt einen Video Game Namen.
     * Enthält ein Name in Capital Letters um das vorhandensein eines Namen zu testen.
     * Enthält einen Kurznamen zur Darstellung in einem Typeahead.
     */
    private void mapOperation(){
        CollectionOperations.CreateVideoGameName(this.listOfVideoGames);
    }

    /**
     * Filtert nach Jahr > 2014
     * Sortiert nach Jahr
     * Limitiert die ersten 20 Resultate
     *
     */
    private void filterSortLimit(){
        CollectionOperations.filterSortLimit(this.listOfVideoGames);
    }


    /**
     * Sortiert nach Rang und Überspringt die ersten 10 Ränge
     */
    private void SkipFirstTenRanks(){
        System.out.println("Skip First 10 Ranks: ");
        Collections.shuffle(this.listOfVideoGames);
        CollectionOperations.skipFirstTenRanks(this.listOfVideoGames);
    }


    /**
     *  Gibt True auf der Console aus wenn es ein Spiel mit dem Anfangsbuchstaben B enthält
     */
    private void startsWithB(){
        System.out.println("Is there a Game that starts with B: " + CollectionOperations.videoGameStartsWith(this.listOfVideoGames, "B"));
    }


    /**
     * Negativ Test: Gibt True wenn ein Spiel mit den Anfangsbuchstaben ASDF gefunden wurde.
     */
    private void startsWithASDF(){
        System.out.println("Is there a Game that starts with ASDF: " + CollectionOperations.videoGameStartsWith(this.listOfVideoGames, "ASDF"));
    }


    /**
     * Tested ob alle Borderland Spiele das Genre Shooter haben.
     * Negativ Test -> Assesins creed
     * Gibt true oder False auf der Commandozeile aus.
     */
    private void compareGenreOfBorderlandsGames(){
        System.out.println("Compare Genre of Borderlands Games");
        List<VideoGame> codGames = vgService.findByNameContaining("Borderlands");
        System.out.println("All Genre the same: " + CollectionOperations.checkIfGenreIsTheSame(codGames, "Shooter"));
        //negativTest

        System.out.println("Compare Genre of Assassin Games");
        List<VideoGame> assasinscreedGame = vgService.findByNameContaining("Assassin");
        System.out.println("All Genre the same of Assasins Creed: " + CollectionOperations.checkIfGenreIsTheSame(assasinscreedGame, "Shooter"));

    }

    /**
     * Zählt die Anzahl an Borderland Spiele
     */
    private void getCountOfBorderlandsGames(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        System.out.println(CollectionOperations.numberOfGames(borderlandsGames));

    }


    /**
     * Gibt das am wenigsten Verkaufte Borderlands Game auf der Kommandozeile aus.
     */
    private void getLowestSellingBorderlandsGame(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        VideoGame lowestSellingGame = CollectionOperations.getLowestTotalSales(borderlandsGames);
        System.out.println("The lowest selling game in the Borderlands Series is: ");
        System.out.println("Game:" + lowestSellingGame.getName() + " Global Sales: " +  lowestSellingGame.getGlobal_sales());
    }

    /**
     * gibt die die 10 meistverkauften Borderlands spiele auf der Kommandozeile aus.
     */
    private void getFirst10Ranks(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        List<VideoGame> fiveMostPopularBorderlandsGames = CollectionOperations.get5MostPopularResults(borderlandsGames);
        fiveMostPopularBorderlandsGames.forEach(
                vg -> System.out.println("Rank: " + vg.getRank() + " Name: " + vg.getName())
        );
    }



}
