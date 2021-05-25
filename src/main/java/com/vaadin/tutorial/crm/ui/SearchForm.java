package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.service.VideoGameService;
import com.vaadin.tutorial.crm.helpers.CollectionOperations;

import java.util.List;

public class SearchForm extends FormLayout {

    //filterNameTextField Text Field: Die Suche wird über Typen gestartet
    TextField filterNameTextField = new TextField("Search by Video Game Name");
    IntegerField yearFromIntegerField  = new IntegerField("Year from:");
    IntegerField yearToIntegerField  = new IntegerField("Year to:");
    Button searchFromYearToYearButton = new Button("Search From-To Year");
    ComboBox<String> genreComboBox = new ComboBox<>("Top 5: Most Frequent Genres");


    private Grid<VideoGame> grid;
    private VideoGameService vgService;

    /**
     * Constructor, ein Grid wird übergeben und kann somit Dynamisch in diesem Component angepasst werden
     * @param grid
     * @param vgService
     */
    public SearchForm(Grid<VideoGame> grid, VideoGameService vgService){
        this.grid = grid;
        this.vgService = vgService;
        addClassName("search-form");
        //Dynamisches Verhalten nur die 5 gängisgsten Genres werden angezeigt.
        genreComboBox.setItems(CollectionOperations.get5MostFrequentGenres(vgService.findAll()));

        add(
                filterNameTextField,
                yearFromIntegerField,
                yearToIntegerField,
                searchFromYearToYearButton,
                genreComboBox,
                addLayout()
        );


        filterNameTextField.setClearButtonVisible(true);
        filterNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        filterNameTextField.addValueChangeListener(e -> {
            this.yearFromIntegerField.clear();
            this.yearToIntegerField.clear();
            this.genreComboBox.clear();
            searchForName();
        });

        //ComboBox
        this.genreComboBox.setClearButtonVisible(true);
        this.genreComboBox.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                this.yearFromIntegerField.clear();
                this.yearToIntegerField.clear();
                this.filterNameTextField.clear();
                this.searchForGenre();

            }
        });

    }


    /**
     * Methode um durch den Range Button nach Jahr zu selektieren.
     * Enthält Validierung für keine negativen Zahlen, sowie das erste IntField muss kleiner gleich das zweite sein.
     * Notification wird dem Nutzer bei invalidem input angzeigt.
     */
    private void setActionOnRangeButton(){

        this.filterNameTextField.clear();
        this.genreComboBox.clear();

        if (this.yearFromIntegerField.isEmpty() || this.yearToIntegerField.isEmpty()) {
            Notification notification = new Notification(
                    "Bitte geben Sie einen Nummerischen Wert in den TextBoxen ein", 3000);
            notification.open();
         return;
        }

        int yearFromInt = this.yearFromIntegerField.getValue();
        int yearToInt = this.yearToIntegerField.getValue();

        //validation
        boolean firstNumberLowerThanSecond = yearFromInt <= yearToInt ? true : false;
        boolean bothNumbersNotNegative = yearFromInt >= 0 && yearToInt >= 0 ? true : false;
        if(firstNumberLowerThanSecond && bothNumbersNotNegative){
            searchForYearRange(yearFromInt, yearToInt);
        }else{
            Notification notification = new Notification(
                    "Bitte geben Sie ein gültiges Format ein", 3000);
            notification.open();
        }

    }



    //Search Funktionen

    /**
     * Nach video Game Namen suchen. Nicht case sensitive. Auch Teilnamen werden erkannt.
     */
    private void searchForName(){
        grid.setItems(vgService.findByNameContaining(filterNameTextField.getValue()));
    }

    /**
     * NAch genre Suchen. Auch Teilnamen werden erkannt.
     */
    private void searchForGenre(){
        grid.setItems(vgService.findByGenreContaining(this.genreComboBox.getValue()));
    }

    /**
     * Nach Range Jahr suchen.
     * @param yearFrom
     * @param yearTo
     */
    private void searchForYearRange(int yearFrom, int yearTo){
        this.grid.setItems(CollectionOperations.findByRangeYear(yearFrom, yearTo, vgService.findAll()));
    }


    //Layout

    /**
     * Layout erstellen
     * @return
     */
    private Component addLayout() {

        searchFromYearToYearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        searchFromYearToYearButton.addClickListener(click -> setActionOnRangeButton());

        return new HorizontalLayout(filterNameTextField, yearFromIntegerField, yearToIntegerField, searchFromYearToYearButton);
    }


}
