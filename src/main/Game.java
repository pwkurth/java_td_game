package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private BufferedImage img;
    private int updates;
    private long lastTimeUPS;
    private Thread gameThread;
    private final double FPS__SET = 120.0;
    private final double UPS__SET = 60.0;

    public Game() {

        importImg();

        setSize(640,640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(img);
        add(gameScreen);
        setVisible(true);
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void start(){
        gameThread = new Thread(this) {};

        gameThread.start();

    }

    private void callUPS() {
        if(System.currentTimeMillis() - lastTimeUPS >= 1000) {
            System.out.println("UPS: " + updates);
            updates = 0;
            lastTimeUPS = System.currentTimeMillis();
        }
    }

    private void updateGame() {
        updates++;

    }

    public static void  main(String[] args) {

        Game game = new Game();
        game.start();

    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS__SET;
        double timePerUpdate = 1000000000.0 / UPS__SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        while(true){

            //Render
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
            }

            //Update
            if (System.nanoTime() - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = System.nanoTime();
                updates++;
            }

            //Checking FPS and UPS
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();

            }
        }
    }
}
