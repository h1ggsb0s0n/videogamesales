package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "vgsales")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rank;


    private String name;

    private String platform;


    private Long year;

    private String genre;

    private String publisher;


    private Double na_sales;
    private Double eu_sales;
    private Double jp_sales;
    private Double other_sales;
    private Double global_sales;




    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getNa_sales() {
        return na_sales;
    }

    public void setNa_sales(Double na_sales) {
        this.na_sales = na_sales;
    }

    public Double getEu_sales() {
        return eu_sales;
    }

    public void setEu_sales(Double eu_sales) {
        this.eu_sales = eu_sales;
    }

    public Double getJp_sales() {
        return jp_sales;
    }

    public void setJp_sales(Double jp_sales) {
        this.jp_sales = jp_sales;
    }

    public Double getOther_sales() {
        return other_sales;
    }

    public void setOther_sales(Double other_sales) {
        this.other_sales = other_sales;
    }

    public Double getGlobal_sales() {
        return global_sales;
    }

    public void setGlobal_sales(Double global_sales) {
        this.global_sales = global_sales;
    }



}
