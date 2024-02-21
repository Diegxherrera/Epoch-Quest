package model;

public class Potion extends Item {
    public Potion(String name, int value, String imagePath) {
        super(name, value, imagePath);
    }

    @Override
    void use() {
        System.out.println("Drinking " + getName());
    }
}
