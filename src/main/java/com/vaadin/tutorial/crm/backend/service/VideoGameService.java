package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import com.vaadin.tutorial.crm.backend.repository.CompanyRepository;
import com.vaadin.tutorial.crm.backend.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository vgRepostitory;


    public VideoGameService() {
    }

    public VideoGame findByRank() {
        return vgRepostitory.findByRank(1L);
    }

    public List<VideoGame> findByRankBetween(Long rankStart, Long rankEnd){

        return  vgRepostitory.findByRankBetween(rankStart, rankEnd);
    }

    public List<VideoGame> findByNameContaining(String videoGameName){
        return  vgRepostitory.findByNameContaining(videoGameName);
    };


    public List<VideoGame> findAll() {
        return vgRepostitory.findAll();
    }
}
