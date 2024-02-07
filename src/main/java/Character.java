public abstract class Character {
    private String name;
    private int level;
    private CharacterKind kind;
    private int health;
    private int strength;
    private int defense;
    private int receivedDamage;

    public Character() {

    }

    public void attack() {

    }

    public void defend() {

    }

    public void takeDamage() {

    }

    //TODO Manage connection between items and character / player.
}

enum CharacterKind {
    EMPEROR,
    ACTOR,
    PRESIDENT,
    BUSINESSMAN,
    SCIENTIST,
    PHILOSOPHER,
    PLAYER
}

class Player extends Character {
    private Inventory inventory;
    private Weapon currentWeapon;

    public Player(Inventory i, Weapon cW) {
        this.inventory = i;
        this.currentWeapon = cW;
    }

    public void takeItem() {

    }


    public void dropItem() {

    }

    public void levelUp() {
        //TODO Create algorithm to calculate if player is capable of leveling up.
    }

}

class Enemy extends Character {
    private int aggresionLevel;
    private int lootDropChance;

    public void dropLoot() {

    }

    public void chooseAction() {
        /*switch () {
            //TODO Choose between Character actions
        }*/
    }
}