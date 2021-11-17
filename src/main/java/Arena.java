import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width, height;
    private Hero hero;

    Arena(int width, int height, Hero hero){
        this.width = width;
        this.height = height;
        this.hero = hero;
    }

    public void draw(TextGraphics graphics) throws IOException {
        //Draws the Arena Background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        /* A Way to make the game bigger
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        */

        //Draws Character
        hero.draw(graphics);
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() < width && position.getY() < height){
            return true;
        } else {
            return false;
        }
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                System.out.println("Pressed Key -> Arrow Up");
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
            {
                System.out.println("Pressed Key -> Arrow Down");
                moveHero(hero.moveDown());
                break;
            }
            case ArrowLeft:
            {
                System.out.println("Pressed Key -> Left Arrow");
                moveHero(hero.moveLeft());
                break;
            }
            case ArrowRight:
            {
                System.out.println("Pressed Key -> Right Arrow");
                moveHero(hero.moveRight());
                break;
            }
            default:
                System.out.println("Pressed Key -> Not Assigned");
                break;
        }
    }
}
