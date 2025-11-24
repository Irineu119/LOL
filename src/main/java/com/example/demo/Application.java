package com.example.demo;

import com.example.demo.entity.Champion;
import com.example.demo.service.ChampionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    CommandLineRunner runChampion(ChampionService s) {
        return args -> {
            List<Champion> champions = s.findAll();
            if (champions.isEmpty()) {
                String data = Files.readString(Paths.get("championdata.txt"), StandardCharsets.UTF_8);
                String[] data2 = data.split("[\t]");
                for (String s2 : data2) {
                    Champion c = new Champion();

                    s.save(c);
                }
            }
        };
    }

}
