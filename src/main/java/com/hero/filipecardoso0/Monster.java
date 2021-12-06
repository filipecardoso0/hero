package com.hero.filipecardoso0;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.hero.filipecardoso0.Position;

import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position move(){
        Random random = new Random();

        if(random.nextInt(4) == 1){
            return new Position(position.getX()+1, position.getY());
        }
        else if(random.nextInt(4) == 2){
            return new Position(position.getX()-1, position.getY());
        }
        else if(random.nextInt(4) == 3){
            return new Position(position.getX(), position.getY()+1);
        }
        else{
            return new Position(position.getX(), position.getY()-1);
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}