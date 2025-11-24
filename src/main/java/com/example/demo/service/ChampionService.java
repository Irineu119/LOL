package com.example.demo.service;

import com.example.demo.entity.Champion;
import com.example.demo.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;

    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    public List<Champion> findAll() {
        return championRepository.findAll();
    }

    public Champion save(Champion c) {
        return championRepository.save(c);
    }

}
