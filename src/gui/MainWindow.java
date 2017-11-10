package gui;

import javax.swing.JFrame;

import logic.server.Server;
import stoper.Stoper;

public class MainWindow extends JFrame{
	
	public MainWindow()
	{
		super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Server serv = new Server();
        serv.TestSettings();
        SquareMesh sm = new SquareMesh();
        sm.SetLogicMesh(serv.getMesh());
        serv.setToRefresh(sm);
        add(sm);
        setSize(sm.getSize());
        setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Stoper.Start("main");
		MainWindow main = new MainWindow();
	}

}
