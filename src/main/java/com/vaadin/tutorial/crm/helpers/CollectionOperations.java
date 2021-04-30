package com.vaadin.tutorial.crm.helpers;

import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.entity.VideoGameName;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionOperations {

    //Intermediaäre Operationen
    //skip(), map(), filter(), limit(), sorted()


    //map()

    public static void CreateVideoGameName(List<VideoGame> col){
        col.stream().map(vg ->{
            VideoGameName vgName = new VideoGameName();
            vgName.setName(vg.getName().toUpperCase());
            vgName.setShortName(StringUtils.abbreviate(vg.getName(), 5));
            return vgName;
        }).forEach(System.out::println);
    }


    //skip()

    public static void skipFirstTenRanks(List<VideoGame> col){
        col.stream()
                .sorted(Comparator.comparing(VideoGame::getRank))
                .skip(10)
                .forEach( vg -> System.out.println("Rank: " + vg.getRank() + " Name: " + vg.getName()));
    }





    //filter() + Limit() + Sort()

    public static void filterSortLimit(List<VideoGame> col){
        col.stream()
                .filter(vg -> vg.getYear() > 2014)
                .sorted((v1, v2)-> v2.getYear().compareTo(v1.getYear()))
                .limit(20)
                .forEach(vg -> {
                   System.out.println("Name: " + vg.getName() + " Year: " + vg.getYear() + " Publisher: " + vg.getPublisher());
                });
    }




    //Terminale Operatoren

    //AnyMatch()
    public static boolean videoGameStartsWith(List<VideoGame> col, String startLetter){
        Predicate<VideoGame> p = vg -> vg.getName().startsWith(startLetter);

        return col.stream().anyMatch(p);

    }

    public static boolean checkIfGenreIsTheSame(List<VideoGame> col, String genre){
        Predicate<VideoGame> p = vg -> vg.getGenre().equalsIgnoreCase(genre);
        return col.stream().allMatch(p);
    }

    public static long numberOfGames(List<VideoGame> col){
        return col.stream().count();
    }


    public static VideoGame getLowestTotalSales(List<VideoGame> col) {
        return  col.stream().min(Comparator.comparing(VideoGame::getGlobal_sales)).get();
    }

    //collect:

    //Collect
    public static List<VideoGame> get5MostPopularResults(List<VideoGame> col) {
        return  col.stream().sorted(Comparator.comparing(VideoGame::getRank)).limit(5).collect(Collectors.toList());
    }


}
