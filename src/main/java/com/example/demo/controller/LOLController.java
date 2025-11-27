package com.example.demo.controller;

import com.example.demo.entity.Champion;
import com.example.demo.service.ChampionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LOLController {

    private final ChampionService championService;

    public LOLController(ChampionService service) {
        championService = service;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("random_champion");
        Champion c = Champion.Generate();
        mv.addObject("champion", c);
        return mv;
    }

}
