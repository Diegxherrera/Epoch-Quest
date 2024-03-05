package model;

public class Potion extends Item {
    public Potion(String name, int value, String imagePath) {
        super(name, value, imagePath);

    }

    void setCuredHealth(int health){
        this.setItemHealth(health);
    }
    int getCuredHealth(){
        return this.getItemHealth();
    }




    @Override
    void use() {
        System.out.println("Drinking " + getName());
    }
}
