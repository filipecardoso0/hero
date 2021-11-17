import com.googlecode.lanterna.TerminalRectangle;
import com.googlecode.lanterna.TextCharacter;
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

    public void draw(Screen screen) throws IOException {
        screen.setCharacter(this.hero.position.getX(), this.hero.position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() <= width && position.getY() <= height){
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
