package gui.object;

import gui.entity.Entity;
import gui.main.EventRect;
import gui.main.GamePanel;



public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = getImage("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image2 = getImage("/objects/heart_half.png",gp.tileSize,gp.tileSize);
        image3 = getImage("/objects/heart_blank.png",gp.tileSize,gp.tileSize);
    }
}
