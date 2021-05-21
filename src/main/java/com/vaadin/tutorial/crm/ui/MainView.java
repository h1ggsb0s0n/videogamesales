package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.backend.service.VideoGameService;

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    Grid<VideoGame> grid = new Grid<>(VideoGame.class);
    private VideoGameService vgService;
    private final SearchForm form;

    TextField filterText = new TextField();

    public MainView(VideoGameService vgService) {
        this.vgService = vgService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new SearchForm(grid, vgService);
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        add(content);

        updateList();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("na_sales");
        grid.removeColumnByKey("eu_sales");
        grid.removeColumnByKey("jp_sales");
        grid.removeColumnByKey("other_sales");

        grid.setColumns("rank", "name", "platform", "year", "genre", "publisher", "global_sales");


        /* when strange stuff gets returned
        grid.addColumn(videoGame -> {
           Company company = videoGame.getCompany();
           return company == null ? "-" : company.getName();
        }).setHeader("Company");*/

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(vgService.findAll());
    }
}
