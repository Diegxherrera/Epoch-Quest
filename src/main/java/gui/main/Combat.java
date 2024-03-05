package gui.main;

public class Combat {
    GamePanel gp;

    public Combat(GamePanel gp){
        this.gp = gp;
    }

    public void ataqueJugadorASlime(){
        gp.monster[10].life -= gp.player.attack - gp.monster[10].defense;
    }
    public void ataqueJugadorAGoblin(){
        gp.monster[11].life -= gp.player.attack - gp.monster[10].defense;
    }
    public void ataqueJugadorARedBoy(){
        gp.monster[12].life -= gp.player.attack - gp.monster[10].defense;
    }

    public void ataqueSlime(){
        gp.player.life -= gp.monster[10].attack - gp.player.defense;
    }
    public void ataqueGoblin(){
        gp.player.life -= gp.monster[11].attack - gp.player.defense;

    }
    public void ataqueRedBoy(){
        gp.player.life -= gp.monster[12].attack - gp.player.defense;

    }




}
