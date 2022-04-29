package scenes;

import main.Game;
import userInterface.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods{

    private MyButton buttonMenu;
    public Settings(Game game) {

        super(game);
        initButtons();
    }

    private void initButtons() {
        buttonMenu = new MyButton("Menu", 2, 2, 100, 30);
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(0,0, 640, 640);

        drawButtons(g);

    }


    private void drawButtons(Graphics g) {
        buttonMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y))
            SetGameState(MENU);

    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        if (buttonMenu.getBounds().contains(x, y))
            buttonMenu.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y))
            buttonMenu.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        buttonMenu.resetBooleans();

    }
}
