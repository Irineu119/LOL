package com.example.demo;

import com.example.demo.entity.Champion;
import com.example.demo.service.ChampionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    public static String askGemini(String prompt) {
        String s =  """
                      {
                          "contents": [
                            {
                              "role": "user",
                              "parts": [
                                {
                                  "text": "%s"
                                }
                              ]
                            }
                          ],
                        }
                    """;

        URL url = null;
        try {
            url = new URL("https://aiplatform.googleapis.com/v1/publishers/google/models/gemini-2.5-flash-lite:streamGenerateContent?key=${AQ.Ab8RN6IKoI4cD-ehgqd36wLtJy-8al0F34wuf-ZBDX8_0C5cBg}");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
        }
        catch (Exception e) {
            System.out.println("Erro ao criar URL/conexão. Erro: " + e.getMessage());
            return "";
        }
        return "";
    }

    @Bean
    CommandLineRunner runChampion(ChampionService s) {
        return args -> {
            List<Champion> champions = s.findAll();
            if (champions.isEmpty()) {
                String data = Files.readString(Paths.get("championdata.txt"), StandardCharsets.UTF_8);
                String[] data2 = data.split("[\t\n]");
                for (int i = 0; i < data2.length; i += 20) {
                    try {
                        Champion c = new Champion();
                        c.setName(data2[i]);
                        c.setFotoUrl(data2[i + 1]);
                        c.setHealthPoints(Float.parseFloat(data2[i + 2]));
                        c.setHp5(Float.parseFloat(data2[i + 4]));
                        c.setMagicPoints(Float.parseFloat(data2[i + 6]));
                        c.setMp5(Float.parseFloat(data2[i + 8]));
                        c.setAttackDamage(Float.parseFloat(data2[i + 10]));
                        c.setAttackSpeed(Float.parseFloat(data2[i + 12]));
                        c.setArmor(Float.parseFloat(data2[i + 14]));
                        c.setMagicResistance(Float.parseFloat(data2[i + 16]));
                        c.setMovementSpeed(Float.parseFloat(data2[i + 18]));
                        c.setAttackRange(Float.parseFloat(data2[i + 19]));
                        s.save(c);
                    }
                    catch (Exception e) {
                        System.out.printf("Erro ao registrar campeão %s. Erro: %s.\n", data2[i], e.getMessage());
                    }
                }
            }
        };
    }

}
