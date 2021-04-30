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


        this.getFirst100VideoGames();

        Button mapButton = new Button("Map");
        mapButton.addClickListener(clickEvent -> mapOperation());

        Button filterSortLimit = new Button("filterSortLimit");
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


    private void getFirst100VideoGames(){
        this.listOfVideoGames = this.vgService.findByRankBetween(1L, 1000L);
        Notification.show("Number of items in List" + this.listOfVideoGames.size());;

    }


    private void mapOperation(){
        CollectionOperations.CreateVideoGameName(this.listOfVideoGames);
    }

    //Filter Sort Limit
    private void filterSortLimit(){
        CollectionOperations.filterSortLimit(this.listOfVideoGames);
    }

    //skip

    private void SkipFirstTenRanks(){
        System.out.println("Skip First 10 Ranks: ");
        Collections.shuffle(this.listOfVideoGames);
        CollectionOperations.skipFirstTenRanks(this.listOfVideoGames);
    }


    //anymatch

    private void startsWithB(){
        System.out.println("Is there a Game that starts with B: " + CollectionOperations.videoGameStartsWith(this.listOfVideoGames, "B"));
    }

    private void startsWithASDF(){
        System.out.println("Is there a Game that starts with ASDF: " + CollectionOperations.videoGameStartsWith(this.listOfVideoGames, "ASDF"));
    }

    //matchall

    private void compareGenreOfBorderlandsGames(){
        List<VideoGame> codGames = vgService.findByNameContaining("Borderlands");
        //System.out.println(codGames.size());
        //codGames.forEach(vg -> System.out.println(vg.getName() + " Genre: "+ vg.getGenre()));
        System.out.println("All Genre the same: " + CollectionOperations.checkIfGenreIsTheSame(codGames, "Shooter"));

    }

    //negative check with assasin's creed


    private void getCountOfBorderlandsGames(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        System.out.println(borderlandsGames.size());
        borderlandsGames.forEach(vg -> System.out.println(vg.getName() + " Genre: "+ vg.getGlobal_sales()));
        System.out.println(CollectionOperations.numberOfGames(borderlandsGames));

    }



    private void getLowestSellingBorderlandsGame(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        //Da die Liste schon sortiert ist wird testmässig geshuffelt
        Collections.shuffle(borderlandsGames);
        VideoGame lowestSellingGame = CollectionOperations.getLowestTotalSales(borderlandsGames);
        System.out.println("The lowest selling game in the Borderlands Series is: ");
        System.out.println("Game:" + lowestSellingGame.getName() + " Global Sales: " +  lowestSellingGame.getGlobal_sales());
    }

    private void getFirst10Ranks(){
        List<VideoGame> borderlandsGames = vgService.findByNameContaining("Borderlands");
        Collections.shuffle(borderlandsGames);
        List<VideoGame> fiveMostPopularBorderlandsGames = CollectionOperations.get5MostPopularResults(borderlandsGames);
        fiveMostPopularBorderlandsGames.forEach(
                vg -> System.out.println("Rank: " + vg.getRank() + " Name: " + vg.getName())
        );
    }



}
