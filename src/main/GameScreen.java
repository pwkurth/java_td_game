package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Game game;

    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;



    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();

    }

    public void initInputs(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();

    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);

//            g.drawImage(sprites.get(19), 0,0, null);
//        g.drawImage(img.getSubimage(32 * 9, 32, 32, 32), 0,0,null);

    }
}
