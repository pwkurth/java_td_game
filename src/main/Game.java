package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private int updates;
    private long lastTimeUPS;
    private Thread gameThread;
    private final double FPS__SET = 120.0;
    private final double UPS__SET = 60.0;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;


    public Game() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initClasses();
        add(gameScreen);
        pack();

        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void initInputs(){
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();

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
        game.initInputs();
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

        long now;

        while(true){

            now = System.nanoTime();
            //Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
            }

            //Update
            if (now - lastUpdate >= timePerUpdate) {
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

    //Getters and setters
    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Render getRender(){
        return render;
    }
}
