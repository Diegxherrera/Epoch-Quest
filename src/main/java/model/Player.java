package model;

import model.Character;
import model.Inventory;
import model.Weapon;

public class Player extends Character {
    private final Inventory personalInventory;
    private final Weapon currentWeapon;
    int experience;
    int currentLevel;

    public Player(Inventory i, Weapon cW) {
        this.personalInventory = i;
        this.currentWeapon = cW;
    }

    public void takeItem() {

    }

    public void dropItem() {

    }

    public void levelUp() {
        if (this.experience > 100 && this.currentLevel > 0) {
            currentLevel++;
        }
    }

}