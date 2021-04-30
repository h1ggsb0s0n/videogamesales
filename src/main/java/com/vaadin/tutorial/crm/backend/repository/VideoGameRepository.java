package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.VideoGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoGameRepository extends CrudRepository<VideoGame, Long>{

    VideoGame findByRank(Long rank);

    List<VideoGame> findByRankBetween(Long rankStart, Long rankEnd);

    List<VideoGame> findByNameContaining(String videoGameName);

}
