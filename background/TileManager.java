package background;

import main.GamePanel;
import java.awt.Graphics2D;

import java.io.IOException;
import javax.imageio.ImageIO;

public class TileManager {
    
    GamePanel gp;
    Background[] background;

    public TileManager(GamePanel gp){

        this.gp =  gp;

        background = new Background[3];

        getTileImage();
    }

    public void getTileImage(){
        try {

            background[0] = new Background();
            background[0].image = ImageIO.read(getClass().getResource("/Biome/biome_foret_morte.png"));

            background[1] = new Background();
            background[1].image = ImageIO.read(getClass().getResource("/Biome/biome_neige.png"));

            background[2] = new Background();
            background[2].image = ImageIO.read(getClass().getResource("/Biome/biome_plaine.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        if (GamePanel.gameState == GamePanel.forestOfDispair) {
            g2.drawImage(background[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else if (GamePanel.gameState == GamePanel.menuState) {
            g2.drawImage(background[1].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else if (GamePanel.gameState == GamePanel.IceDungeon){
            g2.drawImage(background[2].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }
    }
}
