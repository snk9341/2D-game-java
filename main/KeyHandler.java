package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean alliesReleased = false;
    public boolean attackReleased = false;
    public boolean enterReleased = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if(GamePanel.gameState == GamePanel.menuState) {
            if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP){
                gp.ui.commandNum -= 1;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum += 1;
                if(gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    GamePanel.currentDungeon = GamePanel.forest_of_dispair;
                    GamePanel.gameState = GamePanel.forestOfDispair;
                    GameAction.canPlay = true;
                    gp.sound.stop();
                    gp.sound.setFile(Sound.FOREST_MUSIC);
                    gp.sound.loop();
                } else if (gp.ui.commandNum == 1) {
                    GamePanel.currentDungeon = GamePanel.iceDungeon;
                    GamePanel.gameState = GamePanel.IceDungeon;
                    GameAction.canPlay = true;
                    gp.sound.stop();
                    gp.sound.setFile(Sound.ICE_DUNGEON_MUSIC);
                    gp.sound.loop();
                } else if (gp.ui.commandNum == 2) {
                    GamePanel.gameState = GamePanel.optionState;
                    enterReleased = false; // Réinitialiser le flag
                } else if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        }
//hereeeeeeeeeeeeeeeeeeeeeee
        if (GamePanel.gameState == GamePanel.optionState) {
            if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
                gp.ui.optionNum -= 1;
                if (gp.ui.optionNum < 0) {
                    gp.ui.optionNum = 3;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.optionNum += 1;
                if (gp.ui.optionNum > 3) {
                    gp.ui.optionNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER && enterReleased) {
                if (gp.ui.optionNum == 0) {
                    GamePanel.maxScreenCol = 20;
                    GamePanel.maxScreenRow = 11;
                    gp.resize();
                } else if (gp.ui.optionNum == 1) {
                    GamePanel.maxScreenCol = 16;
                    GamePanel.maxScreenRow = 9;
                    gp.resize();
                } else if (gp.ui.optionNum == 2) {
                    GamePanel.maxScreenCol = 27;
                    GamePanel.maxScreenRow = 15;
                    gp.resize();
                } else if (gp.ui.optionNum == 3) {
                    // Retour au menu
                    GamePanel.gameState = GamePanel.menuState;
                    enterReleased = false; // Réinitialiser le flag
                }
            }
            if (code == KeyEvent.VK_ESCAPE) {
                GamePanel.gameState = GamePanel.menuState;
                enterReleased = false; // Réinitialiser le flag
            }
        }


        if (GameAction.canPlay) {
            if ((GamePanel.gameState == GamePanel.forestOfDispair || GamePanel.gameState == GamePanel.IceDungeon) && GameAction.turn == GameAction.playerTurn) {
                if (!GameAction.asSelectedAllies) {
                    if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
                        GameAction.indexAllies -= 1;
                        if(GameAction.indexAllies < 0) {
                            GameAction.indexAllies = GamePanel.currentDungeon.getCurrentRoom().getPlayer().size() - 1;
                        }
                    }
                    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                        GameAction.indexAllies += 1;
                        if (GameAction.indexAllies >= GamePanel.currentDungeon.getCurrentRoom().getPlayer().size()) {
                            GameAction.indexAllies = 0;
                        }
                    }

                    if (code == KeyEvent.VK_ENTER && enterReleased) {
                        gp.gameAction.setSelected(gp.gameAction.getSelected());
                        GameAction.asSelectedAllies = true;
                        enterReleased = false;
                    }

                    if (code == KeyEvent.VK_ESCAPE) {
                        GamePanel.gameState = GamePanel.menuState;
                        GameAction.turn = GameAction.iATurn;
                        gp.sound.stop();
                        gp.sound.setFile(Sound.MENU_MUSIC);
                        gp.sound.loop();
                    }
                }

                if (GameAction.asSelectedAllies && !GameAction.asSelectedAttack && GameAction.turn == GameAction.playerTurn) {

                    if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
                        GameAction.indexAttack -= 1;
                        if(GameAction.indexAttack < 0) {
                            GameAction.indexAttack = gp.gameAction.getSelected().getAttack().size() - 1;
                        }
                    }
                    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                        GameAction.indexAttack += 1;
                        if (GameAction.indexAttack >= gp.gameAction.getSelected().getAttack().size()) {
                            GameAction.indexAttack = 0;
                        }
                    }
                    if (code == KeyEvent.VK_ENTER && alliesReleased) {
                        gp.gameAction.setSelectedAttack(gp.gameAction.getSelectedAttack());
                        GameAction.asSelectedAttack = true;
                    }

                    if (code == KeyEvent.VK_ESCAPE) {
                        GameAction.asSelectedAllies = false;
                    }
                }

                if (GameAction.asSelectedAttack && attackReleased && GameAction.turn == GameAction.playerTurn) {
                    if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
                        GameAction.indexenemies -= 1;
                        if(GameAction.indexenemies < 0) {
                            GameAction.indexenemies = GamePanel.currentDungeon.getCurrentRoom().getEnemies().size() - 1;
                        }
                    }
                    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                        GameAction.indexenemies += 1;
                        if (GameAction.indexenemies >= GamePanel.currentDungeon.getCurrentRoom().getEnemies().size()) {
                            GameAction.indexenemies = 0;
                        }
                    }

                    if (code == KeyEvent.VK_ENTER) {
                        gp.gameAction.setSelectedEnemies(gp.gameAction.getSelectedEnemies());
                        GameAction.asSelectedEnemies = true;
                        gp.gameAction.handleDeath();
                    }

                    if (code == KeyEvent.VK_ESCAPE) {
                        GameAction.asSelectedAttack = false;
                        attackReleased = false;
                    }
                }
            }
        }



        if(code == KeyEvent.VK_Z){
            upPressed = true;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // Réinitialiser le flag ENTER pour permettre de réappuyer
        if (code == KeyEvent.VK_ENTER) {
            enterReleased = true;
        }

            if (code == KeyEvent.VK_Z) {
                upPressed = false;
            }
            if(code == KeyEvent.VK_Q){
                leftPressed = false;
            }
            if(code == KeyEvent.VK_S){
                downPressed = false;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = false;
            }
            if (code == KeyEvent.VK_ENTER) {
                GameAction.turn = GameAction.playerTurn;
                GameAction.canPlay = true;
            }
        
            if (GameAction.asSelectedAllies) {
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println(gp.gameAction.getSelected().getName());
                    alliesReleased = true;
                }
            }
            if (GameAction.asSelectedAttack) {
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println(gp.gameAction.getSelectedAttack());
                    attackReleased = true;
                }
            }
            if (GameAction.asSelectedAttack && GameAction.asSelectedEnemies) {
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println(gp.gameAction.getSelectedEnemies().getName());
                    GameAction.canPlay = false;
                    gp.gameAction.resetChoice();
                    gp.gameAction.iaPlay();
                }
            }


    }

}
