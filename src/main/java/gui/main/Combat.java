package gui.main;

import gui.entity.Entity;

public class Combat {
    GamePanel gp;
    private boolean playerTurn;
    private javax.swing.Timer combatTimer;


    public Combat(GamePanel gp) {
        this.gp = gp;
        this.playerTurn = true;

        // Configurar el temporizador con una acción que se realiza en cada tic
        combatTimer = new javax.swing.Timer(100, e -> turnoCombate());
    }

    public void ataqueJugadorASlime() {
        if (gp.monster[10].life > 0) {
            int damage = gp.player.attack - gp.monster[10].defense;
            System.out.println("Juegador ataca al slime - Daño calculado: " + damage);
            realizarAtaque(damage, gp.monster[10], "Jugador");
        }
    }

    public void ataqueSlime() {
        if (gp.monster[10].life > 0 && gp.player.life > 0) {
            int damage = gp.monster[10].getAttack() - gp.player.defense;
            System.out.println("Slime ataca al jugador - Daño calculado: " + damage);
            realizarAtaque(damage, gp.player, "Slime");

            // Verificar si el jugador ha muerto después del ataque del monstruo
            if (gp.player.dead) {
                detenerCombate();
            }
        }
    }


    private void realizarAtaque(int damage, Entity target, String attackerName) {
        if (damage > 0) {
            target.life -= damage;

            // Asegurar que la vida no sea menor que cero
            if (target.life < 0) {
                target.life = 0;
            }
        }

        // Actualizar la interfaz después de cada ataque
        gp.repaint();

        // Mostrar la vida después del ataque
        System.out.println(attackerName + " atacó a " + target.getClass().getSimpleName() + " - Vida restante: " + target.life);
    }


    public void ataqueJugadorAGoblin(){
        if (gp.monster[11].life > 0) {
            int damage = gp.player.attack - gp.monster[11].defense;
            System.out.println("Juegador ataca al slime - Daño calculado: " + damage);
            realizarAtaque(damage, gp.monster[11], "Jugador");
        }    }
    public void ataqueJugadorARedBoy(){
        if (gp.monster[12].life > 0) {
            int damage = gp.player.attack - gp.monster[12].defense;
            System.out.println("Juegador ataca al slime - Daño calculado: " + damage);
            realizarAtaque(damage, gp.monster[10], "Jugador");
        }    }
    public void ataqueGoblin(){
        if (gp.monster[11].life > 0 && gp.player.life > 0) {
            int damage = gp.monster[11].getAttack() - gp.player.defense;
            System.out.println("Slime ataca al jugador - Daño calculado: " + damage);
            realizarAtaque(damage, gp.player, "Slime");

            // Verificar si el jugador ha muerto después del ataque del monstruo
            if (gp.player.dead) {
                detenerCombate();
            }
        }
    }
    public void ataqueRedBoy(){
        if (gp.monster[12].life > 0 && gp.player.life > 0) {
            int damage = gp.monster[12].getAttack() - gp.player.defense;
            System.out.println("Slime ataca al jugador - Daño calculado: " + damage);
            realizarAtaque(damage, gp.player, "Slime");

            // Verificar si el jugador ha muerto después del ataque del monstruo
            if (gp.player.dead) {
                detenerCombate();
            }
        }
    }

    public void startCombatTimer() {
        combatTimer.start();
    }
    public void stopCombatTimer() {
        combatTimer.stop();
    }

    public void turnoCombate() {
        int i;
        if (playerTurn) {
            if (gp.keyH.enterPressed) {
                gp.keyH.enterPressed = false; // Reiniciar la bandera de Enter
                ataqueJugadorASlime();
                playerTurn = false; // Cambia al turno del enemigo
            }
        } else {
            // Ataque del Slime solo cuando Enter fue presionado
            if (gp.keyH.enterPressed) {
                gp.keyH.enterPressed = false; // Reiniciar la bandera de Enter
                ataqueSlime();
                // Verificar si el jugador ha muerto después del ataque del monstruo
                if (gp.player.dead) {
                    detenerCombate();
                } else {
                    playerTurn = true; // Cambia al turno del jugador solo si el jugador no está muerto
                }
            }
        }

        // Verifica si alguien ha muerto después de cada turno
        if (gp.player.dead || gp.monster[10].dead) {
            detenerCombate();
        }
        // Repintar la interfaz después de cada turno
        gp.repaint();
    }

    private void detenerCombate() {
        // Detener el temporizador cuando el combate ha terminado
        stopCombatTimer();

        // Realizar limpieza adicional si es necesario
        limpiarCombate();

        // Cerrar el menú de batalla solo si el monstruo está vivo
        if (gp.monster[10].dead) {
            gp.ui.battleMenuVisible = true;
        }if (!gp.monster[10].dead) {
            gp.ui.battleMenuVisible = false;
        }

        // Realizar la transición de estados
        if (gp.player.dead) {
            // Si el jugador está muerto, transición al estado de muerte
            System.out.println("Transición al estado de muerte");
            gp.gameState = gp.deadState;
        } else if (gp.monster[10].dead) {
            // Si el monstruo está muerto, transición al estado de juego
            System.out.println("Transición al estado de juego");
            gp.gameState = gp.playState;
        }
    }

    // Método para realizar limpieza adicional
    private void limpiarCombate() {
        // Reiniciar la vida de los personajes si no están muertos
        if (!gp.player.dead) {
            gp.player.life = gp.player.maxLife;
        }

        if (!gp.monster[10].dead) {
            gp.monster[10].life = gp.monster[10].maxLife;
        }

        // También puedes realizar otras acciones de limpieza si es necesario
    }
}
