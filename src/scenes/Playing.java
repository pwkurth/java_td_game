package scenes;

import helperMethod.LevelBuild;
import main.Game;
import managers.TileManager;
import userInterface.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private MyButton buttonMenu;


    public Playing(Game game) {
        super(game);

        initButtons();
        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();

        //The lvl
        //tileManager
    }

    private void initButtons(){
        buttonMenu = new MyButton("Menu", 2, 2, 100, 30);
    }

    @Override
    public void render(Graphics g) {

        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*32, y*32, null);
            }
        }

        drawButtons(g);

    }

    private void drawButtons(Graphics g){
        buttonMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y))
            SetGameState(MENU);

    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        if(buttonMenu.getBounds().contains(x,y))
            buttonMenu.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x,y))
            buttonMenu.setMousePressed(true);

    }

    @Override
    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();

    }
}
