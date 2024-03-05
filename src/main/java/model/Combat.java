package model;
import model.*;
public class Combat {
    public static void main(String[] args) {
        Weapon weapon =new Weapon ("Espada",5,"");
        Inventory i = new Inventory();
        Player player = new Player (i,weapon);
        Enemy enemy=new Enemy();

do {

    player.attack(player, enemy, weapon);
    player.takeDamage(enemy);

}while(player.getPlayerHealth()>0 || enemy.getEnemyHealth()>0);
    }
}
