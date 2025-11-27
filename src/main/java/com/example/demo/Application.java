package com.example.demo;

import com.example.demo.entity.Champion;
import com.example.demo.service.ChampionService;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
        s = String.format(s, prompt);

        try {
            URL url = new URL("https://aiplatform.googleapis.com/v1/publishers/google/models/gemini-2.5-flash-lite:generateContent?key=CHAVE");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // n sei oq q eu fiz aqui
            LinkedHashMap<String, Object> responseJson = new JSONParser(content.toString()).parseObject();
            ArrayList<LinkedHashMap<String, Object>> candidates = (ArrayList<LinkedHashMap<String, Object>>) responseJson.get("candidates");
            LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> content2 = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>) candidates.get(0).get("content");
            ArrayList<LinkedHashMap<String, Object>> parts = content2.get("parts");
            return (String) parts.get(0).get("text");
        }
        catch (Exception e) {
            System.out.println("Erro ao perguntar para o gemini. Erro: " + e.getMessage());
            return "";
        }
    }

    public static boolean generateImage(String prompt, String fileName) {
        String s =  """
                        {
                            "instances": [
                                {
                                    "prompt": "%s"
                                }
                            ],
                            "parameters": {
                                "sampleCount": 1
                            }
                        }
                    """;
        s = String.format(s, prompt);

        try {
            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/imagen-4.0-generate-001:predict");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("x-goog-api-key", "CHAVE");
            con.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            LinkedHashMap<String, Object> responseJson = new JSONParser(content.toString()).parseObject();
            ArrayList<LinkedHashMap<String, Object>> predictions = (ArrayList<LinkedHashMap<String, Object>>) responseJson.get("predictions");
            String base64Image = predictions.get(0).get("bytesBase64Encoded").toString();
            byte[] image = Base64.getDecoder().decode(base64Image);
            Files.write(Paths.get(fileName), image);
            return true;
        }
        catch (Exception e) {
            System.out.println("Erro ao gerar imagem. Erro: " + e.getMessage());
            return false;
        }
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
