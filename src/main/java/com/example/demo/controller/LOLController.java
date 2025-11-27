package com.example.demo.controller;

import com.example.demo.entity.Champion;
import com.example.demo.service.ChampionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class LOLController {

    private final ChampionService championService;

    public LOLController(ChampionService service) {
        championService = service;
    }

    @GetMapping
    public ModelAndView index() {
        // sempre geramos um campeão mesmo que não fomos usá-lo, para que o usuário não possa usar o tempo de resposta do servidor para saber se é IA ou não
        Champion c = Champion.Generate();
        if (Math.random() < 0.5) {
            List<Champion> champs = championService.findAll();
            int idx = (int) (Math.random() * (champs.size() - 1));
            c = champs.get(idx);
        }

        ModelAndView mv = new ModelAndView("random_champion");
        mv.addObject("champion", c);
        return mv;
    }

}
