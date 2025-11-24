package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
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
    private float range;

    public Champion() {
    }

    public Champion(String name, String fotoUrl, float healthPoints, float hp5, float magicPoints, float mp5, float attackDamage, float attackSpeed,
                    float armor, float magicResistance, float movementSpeed, float range) {
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
        this.range = range;
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

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
