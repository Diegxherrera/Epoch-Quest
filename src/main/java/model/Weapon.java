package model;

public class Weapon extends Item {
    public Weapon(String name, int value, String imagePath) {
        super(name, value, imagePath);
    }


    void setDamageHealth(int health){
        this.setItemHealth(health);
    }
    int getDamageHealth(){
        return this.getItemHealth();
    }

    @Override
    void use() {

    }
}