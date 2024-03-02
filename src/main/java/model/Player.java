package model;

public class Player extends Character {
    private final Inventory personalInventory;
    private final Weapon currentWeapon;
    int experience;
    int currentLevel;

    public Player(Inventory i, Weapon cW) {
        this.personalInventory = i;
        this.currentWeapon = cW;
    }


    void setPlayerHealth(int health){
        this.setHealth(health);
    }

    int getPlayerHealth(){
        return this.getHealth();
    }


    public void takeItem(Item item) {
        this.personalInventory.addItem(item);
    }

    public void dropItem() {
        this.personalInventory.dropItem();
    }

    public void takeDamage(Weapon weapon) {
        this.setPlayerHealth(getHealth()-weapon.getDamageHealth());
    }
    public void cureHealth(Potion potion){
        this.setPlayerHealth(getPlayerHealth()+potion.getCuredHealth());
    }

    public void levelUp() {
        if (this.experience > 100 && this.currentLevel > 0) {
            currentLevel++;
        }
    }

}