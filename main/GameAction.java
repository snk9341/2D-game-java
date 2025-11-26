package main;

import characters.Attack;
import characters.Character;
import java.util.Random;

public class GameAction {
    public static int turn = 1;
    final static public int playerTurn = 0;
    final static public int iATurn = 1;
    public static boolean canPlay = false;
    public static boolean asSelectedAllies = false;
    public static boolean asSelectedEnemies = false;
    public static boolean asSelectedAttack = false;
    private Character selectedAllies;
    private Character selectedEnemies;
    private Attack selectedAttack;
    public static int indexAllies = 0;
    public static int indexenemies = 0;
    public static int indexAttack = 0;

    GamePanel gp;

    public GameAction(GamePanel gp) {
        this.gp = gp;
    }

    public Character getSelected(){
        return this.selectedAllies = GamePanel.currentDungeon.getCurrentRoom().getPlayer().get(indexAllies);
    }

    public void setSelected(Character selected) {
        this.selectedAllies = selected;
    }

    public Character getSelectedEnemies() {
        return  this.selectedEnemies = GamePanel.currentDungeon.getCurrentRoom().getEnemies().get(indexenemies);
    }

    public void setSelectedEnemies(Character selected) {
       this.selectedEnemies = selected;
    }

    public void setSelectedAttack(Attack attack) {
        this.selectedAttack = attack;
    }

    public Attack getSelectedAttack() {
        return this.selectedAttack = selectedAllies.getAttack().get(indexAttack);
    }

    public void displayDamage() {
        selectedAllies.performAttack(selectedAttack, selectedEnemies);
        System.out.println("the PV of " + getSelectedEnemies().getName() + "is now " + getSelectedEnemies().getPV());
    }

    public void handleDeath() {
        if (selectedEnemies != null && selectedEnemies.getPV() <= 0) {
            asSelectedEnemies = false;
        }
        if (selectedAllies != null && selectedAllies.getPV() <= 0) {
            asSelectedAllies = false;
        }
    }

    public boolean handleLoose() {
        int deathCount = 0;
        for (int i = 0; i < GamePanel.currentDungeon.getCurrentRoom().getPlayer().size(); i++) {
            if (GamePanel.currentDungeon.getCurrentRoom().getPlayer().get(i).getPV() <= 0) {
                deathCount ++;
            }
        }

        if (deathCount == GamePanel.currentDungeon.getCurrentRoom().getPlayer().size()) {
            System.out.println("you loose");
            GamePanel.gameState = GamePanel.menuState;
            return true;
        } else {
            return false;
        }
    }

    public void handleEndDungeon() {
        int deathCount = 0;

        for(int i = 0; i < GamePanel.currentDungeon.getCurrentRoom().getEnemies().size(); i++) {
            if (GamePanel.currentDungeon.getCurrentRoom().getEnemies().get(i).getPV() <= 0) {
                deathCount ++;
            }
        }


        if (deathCount == GamePanel.forest_of_dispair.getCurrentRoom().getEnemies().size() && GamePanel.forest_of_dispair.getNextRoom() == null) {
                GamePanel.gameState = GamePanel.menuState;
                System.out.println("You've finish the dungeon");
            }
        }

    public void handleEndRoom() {
        int deathCount = 0;

        for(int i = 0; i < GamePanel.currentDungeon.getCurrentRoom().getEnemies().size(); i++) {
            if (GamePanel.currentDungeon.getCurrentRoom().getEnemies().get(i).getPV() <= 0) {
                GamePanel.currentDungeon.getCurrentRoom().getEnemies().get(i).setStatus("dead");
                deathCount ++;
            }
        }

        if (deathCount == GamePanel.currentDungeon.getCurrentRoom().getEnemies().size()) {
            GamePanel.currentDungeon.moveToNextRoom();
            handleEndDungeon();
            //System.out.println("room is now : " + GamePanel.currentDungeon.getCurrentRoom().getName());
        }
    }

    public void resetPlay() {
        turn = 0;
        asSelectedAllies = false;
        asSelectedEnemies = false;
        asSelectedAttack = false;
        gp.keyH.alliesReleased = false;
        gp.keyH.attackReleased = false;
    }

    public void resetChoice() {
        asSelectedAllies = false;
        asSelectedEnemies = false;
        asSelectedAttack = false;
    }

    public void iaPlay() {
        turn = iATurn;
        Random rand = new Random();
        Character selectedIaCharacter;
        Attack selectedIaAttack;
        Character selectedIaVictim;

        do {
            int n2 = rand.nextInt(GamePanel.currentDungeon.getCurrentRoom().getEnemies().size());
            selectedIaCharacter = GamePanel.currentDungeon.getCurrentRoom().getEnemies().get(n2);
        } while (selectedIaCharacter.getPV() <= 0 && !handleLoose());

        int a = rand.nextInt(selectedIaCharacter.getAttack().size());
        selectedIaAttack = selectedIaCharacter.getAttack(a);

        do {
            int r2 = rand.nextInt(GamePanel.currentDungeon.getCurrentRoom().getPlayer().size());
            selectedIaVictim = GamePanel.currentDungeon.getCurrentRoom().getPlayer().get(r2);
        } while (selectedIaVictim.getPV() <= 0 && !handleLoose());


        // CASE 1 : Joueur frappe d'abord
        if (selectedIaCharacter.getVitesse() < getSelected().getVitesse()) {

            displayDamage(); // animation joueur

            Character finalSelectedIaVictim = selectedIaVictim;
            Character finalSelectedIaCharacter = selectedIaCharacter;
            new javax.swing.Timer(1000, e -> {
                finalSelectedIaCharacter.performAttack(selectedIaAttack, finalSelectedIaVictim);  // attaque IA
                handleEndRoom();
                resetPlay();
                GameAction.canPlay = true;
                ((javax.swing.Timer)e.getSource()).stop();
            }).start();

        }

        // CASE 2 : IA frappe d'abord
        else if (selectedIaCharacter.getVitesse() > getSelected().getVitesse()) {

            selectedIaCharacter.performAttack(selectedIaAttack, selectedIaVictim); // attaque IA

            new javax.swing.Timer(1000, e -> {
                displayDamage(); // animation joueur ensuite
                handleEndRoom();
                resetPlay();
                GameAction.canPlay = true;
                ((javax.swing.Timer)e.getSource()).stop();
            }).start();

        }

        // CASE 3 : vitesse égale
        else {

            selectedIaVictim.takeDamage(selectedIaAttack.getDamage());

            new javax.swing.Timer(1000, e -> {
                displayDamage();
                handleEndRoom();
                resetPlay();
                GameAction.canPlay = true;
                ((javax.swing.Timer)e.getSource()).stop();
            }).start();
        }
    }

    public void update() {
        handleEndRoom();
        handleDeath();
        handleLoose();
        handleEndDungeon();
    }
}