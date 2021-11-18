import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public class Wall {

    Position position;

    Wall(int x, int y){
        position = new Position(0,0); //We need to create a position Object and give it some values otherwise the code will break once we have a null Position x & y coordinates
        this.position.setX(x);
        this.position.setY(y);
    }


    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Z");
    }

    public Position getPosition(){
        return position;
    }

}
