package model;

public class Potion extends Item {
    public Potion(String name, int value) {
        super(name, value);
    }

    @Override
    void use() {
        System.out.println("Drinking " + getName());
    }
}
