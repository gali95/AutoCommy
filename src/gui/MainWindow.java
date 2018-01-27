package gui;

import javax.swing.JFrame;

import logic.server.Server;
import stoper.Stoper;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame{

    public Server serv;

	public MainWindow()
	{
		super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serv = new Server();
        serv.TestSettings();
        SquareMesh sm = new SquareMesh();
        sm.SetLogicMesh(serv.getMesh());
        serv.setToRefresh(sm);
        add(sm);
        setSize(sm.getSize());
        setVisible(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
	}
	
	public static void main(String[] args)
	{
		Stoper.Start("main");
		MainWindow main = new MainWindow();
	}

    KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(final KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                System.out.println(e);
                serv.passKeyboardCode(e.getKeyCode());
            }
            // Pass the KeyEvent to the next KeyEventDispatcher in the chain
            return false;
        }
    };


}
