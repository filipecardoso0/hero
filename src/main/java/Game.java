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

    Hero hero = new Hero(10, 10);

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
            screen.close(); // screens must be started
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(int x, int y) throws IOException{
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    public void run() {
        createTerminal();
        while (true){
            try{
                draw(hero.position.getX(), hero.position.getY());
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException{
        switch (key.getKeyType()) {
            case ArrowUp:
                System.out.println("Pressed Key -> Arrow Up");
                moveHero(hero.moveUp());
                draw(hero.position.getX(), hero.position.getY());
                break;
            case ArrowDown:
            {
                System.out.println("Pressed Key -> Arrow Down");
                moveHero(hero.moveDown());
                draw(hero.position.getX(), hero.position.getY());
                break;
            }
            case ArrowLeft:
            {
                System.out.println("Pressed Key -> Left Arrow");
                moveHero(hero.moveLeft());
                draw(hero.position.getX(), hero.position.getY());
                break;
            }
            case ArrowRight:
            {
                System.out.println("Pressed Key -> Right Arrow");
                moveHero(hero.moveRight());
                draw(hero.position.getX(), hero.position.getY());
                break;
            }
            default:
                System.out.println("Pressed Key -> Not Assigned");
                break;
        }

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')   {
            closeTerminal();
        }

    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }

}