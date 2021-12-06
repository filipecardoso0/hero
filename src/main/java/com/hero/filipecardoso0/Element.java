package com.hero.filipecardoso0;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.hero.filipecardoso0.Position;

public abstract class Element {

    protected Position position;

    public Element(int x, int y){
        position = new Position(x, y);
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);
}
