package com.example.demo.entity;

import com.example.demo.Application;
import jakarta.persistence.*;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.util.LinkedHashMap;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String fotoUrl;

    private float healthPoints;
    private float hp5;
    private float magicPoints;
    private float mp5;
    private float attackDamage;
    private float attackSpeed;
    private float armor;
    private float magicResistance;
    private float movementSpeed;
    private float attackRange;

    public static Champion Generate() {
        Champion c = new Champion();
        String s = Application.askGemini("Generate a brand new League of Legends champion with these stats: name, healthPoints, healthRegen, magicPoints, " +
                                                "magicRegen, attackDamage, attackSpeed, armor, magicResistance, movementSpeed and attackRange. These need to be the champion's " +
                                                "stats at level 1. Your response must ONLY be a JSON object where each key has the exact name as the properties i've told you" +
                                                " to generate and the keys are the generated values, and nothing else. Don't format it to be pretty either, no `'s or newlines.")
                   .replace("\\\"", "\"");
        try {
            LinkedHashMap<String, Object> json = new JSONParser(s).parseObject();
            c.setName(json.get("name").toString());
            c.setHealthPoints(Float.parseFloat(json.get("healthPoints").toString()));
            c.setHp5(Float.parseFloat(json.get("healthRegen").toString()));
            c.setMagicPoints(Float.parseFloat(json.get("magicPoints").toString()));
            c.setMp5(Float.parseFloat(json.get("magicRegen").toString()));
            c.setAttackDamage(Float.parseFloat(json.get("attackDamage").toString()));
            c.setAttackSpeed(Float.parseFloat(json.get("attackSpeed").toString()));
            c.setArmor(Float.parseFloat(json.get("armor").toString()));
            c.setMagicResistance(Float.parseFloat(json.get("magicResistance").toString()));
            c.setMovementSpeed(Float.parseFloat(json.get("movementSpeed").toString()));
            c.setAttackRange(Float.parseFloat(json.get("attackRange").toString()));

            if (Application.generateImage("Generate a League of Legends champion's splash art based on their level 1 stats and name (ignore 'id' and 'fotoUrl'): " + c.toString(),
                                "ai_images/ai_champ.png")) {
                c.setFotoUrl("ai_images/ai_champ.png");
                return c;
            }
        }
        catch (Exception e) {
            System.out.printf("Erro ao gerar campeão. Erro: %s.\n", e.getMessage());
        }

        return null;
    }

    public Champion() {
    }

    public Champion(String name, String fotoUrl, float healthPoints, float hp5, float magicPoints, float mp5, float attackDamage, float attackSpeed,
                    float armor, float magicResistance, float movementSpeed, float attackRange) {
        this.name = name;
        this.fotoUrl = fotoUrl;
        this.healthPoints = healthPoints;
        this.hp5 = hp5;
        this.magicPoints = magicPoints;
        this.mp5 = mp5;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.movementSpeed = movementSpeed;
        this.attackRange = attackRange;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fotoUrl='" + fotoUrl + '\'' +
                ", healthPoints=" + healthPoints +
                ", hp5=" + hp5 +
                ", magicPoints=" + magicPoints +
                ", mp5=" + mp5 +
                ", attackDamage=" + attackDamage +
                ", attackSpeed=" + attackSpeed +
                ", armor=" + armor +
                ", magicResistance=" + magicResistance +
                ", movementSpeed=" + movementSpeed +
                ", attackRange=" + attackRange +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public float getHp5() {
        return hp5;
    }

    public void setHp5(float hp5) {
        this.hp5 = hp5;
    }

    public float getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(float magicPoints) {
        this.magicPoints = magicPoints;
    }

    public float getMp5() {
        return mp5;
    }

    public void setMp5(float mp5) {
        this.mp5 = mp5;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(float magicResistance) {
        this.magicResistance = magicResistance;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }
}
