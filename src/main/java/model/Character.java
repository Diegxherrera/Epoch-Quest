package model;

import model.Weapon;
import utils.GameController;

public abstract class   Character {
    private String name;
    private int level;
    private CharacterKind kind;
    private int health;
    private int strength;
    private int defense;

    public Character() {
        // TODO Initialize character by its sons.

    }

    public int getHealth(){ return health;}
    public void setHealth(int health){this.health = health;}


    public void attack() {

    }

    public void defend() {

    }

    public void takeDamage() {
        
    }

    public void cureHealth(){

    }

    //TODO Manage connection between items and character / player.
}

enum CharacterKind {
    IDEOLOGIST,
    PRESIDENT,
}