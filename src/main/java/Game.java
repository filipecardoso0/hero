import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {
    private Screen screen;

    public Screen getScreen(){
        return screen;
    }

    Arena arena = new Arena (100,40);

    public Game(){
        try{
            TerminalSize terminalSize = new TerminalSize(200, 60);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTerminal() {
        try{
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTerminal() {
        try{
            screen.close(); // screen must be started
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics()); //Calls the function responsible to draw the objects into the arena
        screen.refresh();
    }

    public void run() {
        createTerminal();
        while (true){
            try{
                draw(); //Function that draws the objects on the screen
                KeyStroke key = screen.readInput(); //Reads the Key input
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')   { //Verifies if it was 'q'
                    closeTerminal();
                }
                if (key.getKeyType() == KeyType.EOF) { //Verifies if EOF got reached
                    break;
                }
                if(arena.verifyMonsterCollisions()){
                    closeTerminal();
                }
                processKey(key); //If EOF wasn't reached or 'q' was not pressed then it processes the key
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException{
        arena.processKey(key);
    }


}