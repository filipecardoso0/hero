import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {

    private int x, y;

    Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Hero(){
        Hero hero = new Hero(10, 10);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int u) {
        this.x = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp(){
        this.y = this.y -1;
        System.out.println(y);
    }

    public void moveDown(){
        this.y = this.y +1;
        System.out.println(y);
    }

    public void moveRight(){
        this.x = this.x +1;
    }

    public void moveLeft(){
        this.x = this.x -1;
    }

    public void draw(Screen screen) throws IOException {
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }
}
