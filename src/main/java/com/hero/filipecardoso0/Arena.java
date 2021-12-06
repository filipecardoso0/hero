package com.hero.filipecardoso0;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.hero.filipecardoso0.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width, height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();

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

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>(); //Creates a new coins ArrayList object
        for (int i = 0; i < 6; i++)
            //Makes it impossible for the coin to spawn on top of the hero
            if(!(((random.nextInt(width - 2) + 1) == hero.getPosition().getX()) && ((random.nextInt(height - 2) + 1 == hero.getPosition().getY())))){
                monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }
        //Dont know how I can disable a monster from spawning on top of another one but they are not spawning on top of each other

        return monsters;
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

        //Draws the Monsters
        for(Monster monster : monsters){
            monster.draw(graphics);
        }

        /* A Way to make the game bigger
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        */

        //Draws Character
        hero.draw(graphics);
    }

    private void moveHero(Position position) {
        if (canEntityMove(position)){
            hero.setPosition(position);
            retrieveCoins();
            moveMonsters();
        }
    }

    private void moveMonsters(){
        for(Monster monster : monsters){
            Position monsterpos = monster.move(); //The position we want to move the monster to
            if(canEntityMove(monsterpos)){
                monster.setPosition(monsterpos);
            }
        }
    }

    private boolean canEntityMove(Position position) {
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

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.position.equals(hero.position)){
                System.out.println("GAME OVER! YOU GOT EATEN BY A MONSTER");
                return  true;
            }
        }

        return false;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
            {
                moveHero(hero.moveDown());
                break;
            }
            case ArrowLeft:
            {
                moveHero(hero.moveLeft());
                break;
            }
            case ArrowRight:
            {
                moveHero(hero.moveRight());
                break;
            }
            default:
                System.out.println("Pressed Key -> Not Assigned");
                break;
        }
    }
}
