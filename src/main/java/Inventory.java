public class Inventory {
    private Item[] items;
    private int capacity;

    private void addItem() {
        //TODO GameController might store temporarily the item, checkCapacity() and then run this.
    }

    private void removeItem() {

    }

    private Item listItems() {
        //TODO Use availability check and then an algorithm over items.
        return new Item();
    }

    private int checkCapacity() {
        //TODO Return capacity of the Inventory - items already stored. It will be used by classes to calculate.
        return 0;
    }
}

class Item {
    private String name;
    private CharacterKind kind;
    private int value;

    private void use() {
        //TODO Return usage verification and appear in screen.
    }

    private void inspectItem() {
        //TODO Return item name, value and what is it for.
    }
}

class Weapon extends Item{

}

class Potion extends Item {

}