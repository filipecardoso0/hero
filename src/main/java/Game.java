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
    private int x = 10;
    private int y = 10;


    public Game(){
        try{
        TerminalSize terminalSize = new TerminalSize(1280, 720);
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

    public void draw() {
        try {
            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        createTerminal();
        draw();
        while (true){
            try{
            KeyStroke key = screen.readInput();
            processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                System.out.println("Primiu tecla -> Seta para cima");
                screen.setCharacter(x+10, y, TextCharacter.fromCharacter('X')[0]);
                break;
            case ArrowDown:
            {
                System.out.println("Primiu tecla -> Seta para baixo");
                screen.setCharacter(x-10, y, TextCharacter.fromCharacter('X')[0]);
            }
            case ArrowLeft:
            {
                System.out.println("Primiu tecla -> Seta para esquerda");
                screen.setCharacter(x, y-10, TextCharacter.fromCharacter('X')[0]);
            }
            case ArrowRight:
            {
                System.out.println("Primiu tecla -> Seta para direita");
                screen.setCharacter(x, y+10, TextCharacter.fromCharacter('X')[0]);
            }
            default:
                break;
        }
    }
}
