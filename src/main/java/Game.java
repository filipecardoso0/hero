import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;


    public Game(){
        try{
        TerminalSize terminalSize = new TerminalSize(1280, 720);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
            System.out.println("Teste1");

        } catch (IOException e) {
            System.out.println("Teste2");

            e.printStackTrace();
        }
    }

    public void createTerminal() {
        try{
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            System.out.println("Teste3");

            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();

        } catch (IOException e) {
            System.out.println("Teste4");

            e.printStackTrace();
        }
    }

    public void draw() {
        try {
            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
            System.out.println("Teste5");

        } catch (IOException e) {
            System.out.println("Teste6");
            e.printStackTrace();
        }
    }

    public void run(){
        createTerminal();
        draw();
    }
}
