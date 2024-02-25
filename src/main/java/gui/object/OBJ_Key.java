package gui.object;

import gui.entity.Entity;
import gui.main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setUp("/objects/key");
    }
}
