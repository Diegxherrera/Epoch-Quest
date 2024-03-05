package gui.object;

import gui.entity.Entity;
import gui.main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = getImage("/objects/sword_normal.png", gp.tileSize, gp.tileSize);
        attackValue = 2;
    }
}
