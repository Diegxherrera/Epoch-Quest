package model;
import java.util.*;
public class Enemy extends Character {
    private int aggresionLevel;
    private int lootDropChance;

    public void dropLoot() {

    }

    void setEnemyHealth(int health) {
        this.setHealth(health);
    }

    int getEnemyHealth() {
        return this.getHealth();
    }

    void setEnemyStrength(int strength) {
        this.setStrength(strength);
    }

    int getEnemyStrength() {
        return this.getStrength();
    }

    public int chooseAction() {
        Random rd = new Random();
        aggresionLevel = rd.nextInt(3) + 1;
        switch (aggresionLevel) {
            case 1:
                this.setEnemyStrength(1);
                return this.getEnemyStrength();
            case 2:
                this.setEnemyStrength(2);
                return this.getEnemyStrength();
            case 3:
                this.setEnemyStrength(3);
                return this.getEnemyStrength();
        }

        return 0;
        /* switch () {
            //TODO Choose between model.Character actions
        }*/

    }
}