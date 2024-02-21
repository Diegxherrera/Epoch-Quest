package model;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> items;
    private int currentItem;
    private final int capacity;

    public Inventory() {
        this.capacity = 10;
        this.items = new ArrayList<>(capacity);
        this.currentItem = 0;
    }

    public void addItem(Item item) {
        if (items.size() < capacity) {
            items.add(item);
            System.out.println("A " + item.getName() + " was successfully added to the inventory!");
        } else {
            // Replace the current item if inventory is full
            items.set(currentItem, item);
            System.out.println("The current item was replaced with " + item.getName());
        }
    }

    public void dropItem() {
        if (!items.isEmpty() && currentItem < items.size()) {
            Item removed = items.remove(currentItem);
            System.out.println("Dropped " + removed.getName() + " from the inventory.");
        } else {
            System.out.println("No item to drop from the current slot.");
        }
    }

    public void listItems() {
        if (items.isEmpty()) {
            // TODO Show a message telling the user the inv. is empty.
            System.out.println("The inventory is empty.");
        } else {
            // TODO Manage listing of items, proposing the approach shown below:
            System.out.println("Items in inventory:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ": " + items.get(i).getName());
            }
        }
    }

    private int checkCapacity() {
        return capacity - items.size();
    }

    public void setCurrentItem(int currentItem) {
        if (currentItem >= 0 && currentItem < capacity) {
            this.currentItem = currentItem;
        } else {
            System.out.println("Invalid item slot.");
            // TODO Throw error because of invalid item slot.
        }
    }

    public int getCurrentItem() {
        return this.currentItem;
    }
}

abstract class Item {
    private final String name;
    private final int value;
    private final String imagePath;

    public Item(String name, int value, String imagePath) {
        this.name = name;
        this.value = value;
        this.imagePath = imagePath;
    }

    abstract void use();

    String inspectItem() {
        return "Name: " + name + "\n Value: " + value;
    }

    String getItemImage() {
        return imagePath;
    }

    public String getName() {
        return name;
    }
}

