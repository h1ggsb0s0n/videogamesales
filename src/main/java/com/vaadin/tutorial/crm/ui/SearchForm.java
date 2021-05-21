package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.service.VideoGameService;
import com.vaadin.tutorial.crm.helpers.CollectionOperations;

import java.util.List;

public class SearchForm extends FormLayout {

    TextField filterNameTextField = new TextField("Search by Video Game Name");
    TextField yearFromTextField  = new TextField("Year from:");
    TextField yearToTextField  = new TextField("Year to:");
    Button searchFromYearToYearButton = new Button("Search From-To Sales");
    ComboBox<String> genre = new ComboBox<>("Top 5: Most Frequent Genres");


    private Grid<VideoGame> grid;
    private VideoGameService vgService;


    public SearchForm(Grid<VideoGame> grid, VideoGameService vgService){
        this.grid = grid;
        this.vgService = vgService;
        addClassName("contact-form");
        CollectionOperations.get5MostFrequentGenres(vgService.findAll());
        genre.setItems(CollectionOperations.get5MostFrequentGenres(vgService.findAll()));

        add(
                filterNameTextField,
                yearFromTextField,
                yearToTextField,
                searchFromYearToYearButton,
                genre,
                createButtonsLayout()
        );


        this.setFilterOnSearchByNameTextField();



    }

    private Component createButtonsLayout() {



        searchFromYearToYearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //searchFromYearToYearButton.addClickListener(click -> validateAndSave());

        //binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(filterNameTextField, yearFromTextField, yearToTextField, searchFromYearToYearButton);
    }


    private void setFilterOnSearchByNameTextField(){
        filterNameTextField.setClearButtonVisible(true);
        filterNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        filterNameTextField.addValueChangeListener(e -> searchForName());
    }


    /*
    private List<VideoGame> getTop5Genre(){
        return ...
    }*/

    private void searchForName(){
        grid.setItems(vgService.findByNameContaining(filterNameTextField.getValue()));
    }


}
