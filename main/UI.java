package main;

import characters.Attack;

import java.awt.*;

public class UI {

    GamePanel gp;
    public int commandNum = 0;
    public int optionNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    // Méthodes pour obtenir des polices adaptatives
    private Font getLargeFont() {
        int size = Math.max(30, gp.screenHeight / 15);
        return new Font("Arial", Font.PLAIN, size);
    }

    private Font getSmallFont() {
        int size = Math.max(12, gp.screenHeight / 35);
        return new Font("Arial", Font.PLAIN, size);
    }

    public static void makeButton(Graphics2D g2d, int x, int y, int width, int height) {
        // Dessiner un bouton simple pour la barre de PV
        g2d.setColor(new Color(50, 50, 50, 100));
        g2d.fillRect(x, y, width, height);


        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y, width, height);
    }

    public static void makeSelectButton(Graphics2D g2d, int x, int y, int width, int height) {
        // Dessiner un bouton simple pour la barre de PV
        g2d.setColor(new Color(100, 100, 100, 250));
        g2d.fillRect(x, y, width, height);


        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y, width, height);
    }

    public void draw(Graphics2D g2) {
        if (GamePanel.gameState == GamePanel.menuState) {
            HomeMenu(g2);
        } else if (GamePanel.gameState == GamePanel.forestOfDispair || GamePanel.gameState == GamePanel.IceDungeon){
            if (GameAction.turn == GameAction.playerTurn) {
                drawPlayerTurn(g2);
            } else if (GameAction.turn == GameAction.iATurn) {
                TestIa(g2);
            }
        } else if (GamePanel.gameState == GamePanel.optionState) {
            OptionMenu(g2);
        }
    }

    public void ChooseEnemy(Graphics2D g2) {
        g2.setFont(getLargeFont());

        if (gp.gameAction.getSelectedEnemies().getPV() <= 0) {
            g2.setColor(Color.red);

            String text;
            int textLength;
            int x;

            text = gp.gameAction.getSelectedEnemies().getName() + " is already dead";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight / 4;

            g2.drawString(text, x, y);
        }
        if (gp.gameAction.getSelected().getPV() <= 0) {
            g2.setColor(Color.red);

            String text;
            int textLength;
            int x;

            text = gp.gameAction.getSelected().getName() + " is already dead";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight / 4;

            g2.drawString(text, x, y);
        }



        g2.setColor(Color.orange);

        String text;
        int textLength;
        int x;

        text = gp.gameAction.getSelectedEnemies().getName() + " will takes damage";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        int y = gp.screenHeight / 6;

        g2.drawString(text, x, y);
    }

    public void DisplayAttack (Graphics2D g2) {

        g2.setFont(getSmallFont());
        g2.setColor(Color.WHITE);
        int x = gp.screenWidth / 20;
        int y = (int)(gp.screenHeight * 0.92);


        if (!GameAction.asSelectedAttack) {
            for (int i = 0; i < gp.gameAction.getSelected().getAttack().size(); i++) {
                Attack attack = gp.gameAction.getSelected().getAttack().get(i);
                String text = attack.getName() + " : " + attack.getDamage() + " dmg";
                int textLength = g2.getFontMetrics().stringWidth(text);
                int barHeight = 20;

                int barX = x - 4;
                int barY = y - 15;

                if (gp.gameAction.getSelectedAttack() == gp.gameAction.getSelected().getAttack().get(i)) {
                    makeSelectButton(g2, barX, barY, textLength + 8, barHeight);
                } else {
                    makeButton(g2, barX, barY, textLength + 8, barHeight);
                }
                g2.drawString(text, x, y);
                x += gp.screenWidth / 5;
            }
        } else {
            ChooseEnemy(g2);
        }
    }

    public void drawPlayerTurn(Graphics2D g2) {
        g2.setFont(getSmallFont());
        int x;
        int y = gp.screenHeight / 20;
        int textLength;

        String text = "It's your turn to play";

        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        int barHeight = 20;

        int barX = x - 4;
        int barY = y - 15;


        makeButton(g2, barX, barY, textLength + 8, barHeight);
        g2.drawString(text, x, y);

        if(!GameAction.asSelectedAllies) {
            g2.setFont(getSmallFont());
            g2.setColor(Color.WHITE);

            text = "You're currently using : " + gp.gameAction.getSelected().getName();
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight / 8;
            barHeight = 20;

            barX = x - 4;
            barY = y - 15;

            x = gp.screenWidth/2 - textLength/2;

            makeSelectButton(g2, barX, barY, textLength + 8, barHeight);
            g2.drawString(text, x, y);
        } else if (GameAction.asSelectedAllies) {
            DisplayAttack(g2);
        }
    }

    public void TestIa(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.orange);

        String text;
        int textLength;
        int x;

        text = "It's ia's turn to play";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        int y = gp.screenHeight / 10;

        g2.drawString(text, x, y);
    }

    public void HomeMenu(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);

        String text;
        int textLength;
        int x;
        int y;

        // Titre principal
        text = "Welcome in Timeless Dungeon";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight / 8;
        g2.drawString(text, x, y);

        // Sous-titre
        text = "Select a dungeon";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight / 4;
        g2.drawString(text, x, y);

        // First dungeon (gauche, milieu)
        text = "First dungeon";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/4 - textLength/2;
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Second dungeon (droite, milieu)
        text = "Second dungeon";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = (gp.screenWidth * 3)/4 - textLength/2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Options et Quit (bas)
        y = (gp.screenHeight * 3) / 4;
        
        text = "Options";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/4 - textLength/2;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = (gp.screenWidth * 3)/4 - textLength/2;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void OptionMenu(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);

        String text;
        int textLength;
        int x;
        int y;

        text = "Choose your resolution :";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 6;
        g2.drawString(text, x, y);

        text = "960x528p";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 3;
        g2.drawString(text, x, y);
        if (optionNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "768x432p";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        if (optionNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "1280x720p";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = (gp.screenHeight * 2) / 3;
        g2.drawString(text, x, y);
        if (optionNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Back";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = (gp.screenHeight * 5) / 6;
        g2.drawString(text, x, y);
        if (optionNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
}