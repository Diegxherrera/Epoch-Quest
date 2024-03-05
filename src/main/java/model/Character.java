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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void attack(Player player, Enemy enemy, Weapon weapon) {
        enemy.setEnemyHealth(enemy.getEnemyHealth() - (player.getStrength() + weapon.getDamageHealth()));
    }

    public void defend(Player player, Enemy enemy, Weapon weapon) {

        if (enemy.getDefense() > 0) {
            enemy.setEnemyHealth(enemy.getEnemyHealth() - (player.getStrength() + weapon.getDamageHealth()));
        } else {
            enemy.setDefense(enemy.getDefense() - (player.getStrength() + weapon.getDamageHealth()));
        }
    }

    public void takeDamage(Player player, Enemy enemy) {
        player.setPlayerHealth(player.getPlayerHealth() - enemy.chooseAction());
    }

    public void cureHealth(Player player, Enemy enemy, Potion potion) {
        player.setPlayerHealth(player.getPlayerHealth() + potion.getCuredHealth());

        if (this.getHealth() > 6) {
            this.setHealth(6);
        }

        //TODO Manage connection between items and character / player.
    }

    enum CharacterKind {
        IDEOLOGIST,
        PRESIDENT,
    }
}