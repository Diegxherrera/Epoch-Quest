package gui.object;

import gui.entity.Entity;
import gui.main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

         name = "Wood Shield";
         down1 = getImage("objects/shield_wood.png", gp.tileSize, gp.tileSize);
         defenseValue = (int) (Math.random() * 2+1);
    }
}
