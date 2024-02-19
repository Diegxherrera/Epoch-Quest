package model;

public abstract class Character {
    private String name;
    private int level;
    private CharacterKind kind;
    private int health;
    private int strength;
    private int defense;

    public Character() {
        // TODO Initialize character by its sons.
    }

    public void attack() {
        // TODO Generate a
    }

    public void defend() {

    }

    public void takeDamage() {
        
    }

    //TODO Manage connection between items and character / player.
}

enum CharacterKind {
    IDEOLOGIST,
    PRESIDENT,
}