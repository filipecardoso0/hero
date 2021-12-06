import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width, height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>(); //Creates a new coins ArrayList object
        for (int i = 0; i < 5; i++)
            //Makes it impossible for the coin to spawn on top of the hero
            if(!(((random.nextInt(width - 2) + 1) == hero.getPosition().getX()) && ((random.nextInt(height - 2) + 1 == hero.getPosition().getY())))){
                coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }
            //Dont know how I can disable a coin from spawning on top of another one but they are not spawning on top of each other

        return coins;
    }

    private List<Coin> retrieveCoins() {
       for(Coin coin : coins){ //Searches for every coin position
           if(coin.getPosition().equals(hero.getPosition())){
               coins.remove(coin); //Removes the coin out of the array
               break; //We only remove a coin at a time so it wont make sense to continue looping over it. Therefore we just break out of the loop
           }
       }
       return coins;
    }


    public void draw(TextGraphics graphics) throws IOException {
        //Draws the Arena Background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        //Draws the Arena Walls
        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        //Draws the Coins
        for(Coin coin : coins){
            coin.draw(graphics);
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
            retrieveCoins();
        }
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if(wall.getPosition().equals(position)){ //If hero's coordinates are equal to wall coordinates he won't be able to move
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
