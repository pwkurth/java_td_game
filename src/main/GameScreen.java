package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img) {
        this.img = img;

        loadSprites();
        
        random = new Random();



    }

    private void loadSprites() {

        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                //Add our sprite images to our sprites array
                //The grid is 640 total pixels, with 32 pixel blocks
                sprites.add(img.getSubimage(x*32, y*32, 32,32));
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

//            g.drawImage(sprites.get(19), 0,0, null);
//        g.drawImage(img.getSubimage(32 * 9, 32, 32, 32), 0,0,null);

        for (int y= 0; y < 20; y++) {
            for (int x= 0; x < 20; x++) {

                g.drawImage(sprites.get(getRandomInt()), x*32, y*32, null);

            }
        }
    }



    private int getRandomInt(){
        return random.nextInt(100);
    }

    private Color getRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r,g,b);
    }

}
