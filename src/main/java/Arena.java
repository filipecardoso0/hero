import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width, height;
    private Hero hero;
    private List<Wall> walls;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public void draw(TextGraphics graphics) throws IOException {
        //Draws the Arena Background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        //Draws the Arena Walls
        for (Wall wall : walls) {
            wall.draw(graphics);
        }


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
        for (Wall wall : walls) {
            if(position.getX() == wall.position.getX() && position.getY() == wall.position.getY()){ //If hero's coordinates are equal to wall coordinates he won't be able to move
                return false;
            }
        }

        if(position.getX() < width && position.getY() < height){ //If the hero is inside the arena he can move
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
