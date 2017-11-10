package logic.server;

import javax.swing.JPanel;

import logic.base.interfaces.SquareMesh;
import logic.server.test.StubLogic;
import stoper.Stoper;
import gui.*;

public class Server implements Runnable{

	private SquareMesh mesh;
	private double roundTime,remainingRoundTime;
	private ServerState state;
	private boolean canRun;
	private boolean keepServering;
	private JPanel toRefresh;
	
	private int timeStep = 10;
	
	public Server()
	{
		state = ServerState.PRE_STARTED;
		canRun = false;
		keepServering = false;
		timeStep = 10;
	}
	
	
	
	public JPanel getToRefresh() {
		return toRefresh;
	}



	public void setToRefresh(JPanel toRefresh) {
		this.toRefresh = toRefresh;
	}



	public SquareMesh getMesh() {
		return mesh;
	}
	public void setMesh(SquareMesh mesh) {
		this.mesh = mesh;
	}
	public double getRoundTime() {
		return roundTime;
	}
	public void setRoundTime(double roundTime) {
		this.roundTime = roundTime;
	}
	public double getRemainingRoundTime() {
		return remainingRoundTime;
	}
	public void setRemainingRoundTime(int remainingRoundTime) {
		this.remainingRoundTime = remainingRoundTime;
	}
	
	public ServerState getState() {
		return state;
	}
	
	public boolean StartServer()
	{
		
		if(state != ServerState.PRE_STARTED) return false;
		if(mesh == null) return false;
		if(roundTime <= 0) return false;
		canRun = true;
		keepServering = true;
		(new Thread(this)).start();
		return true;
	}
	
	private void ServerThreadFunction()
	{
		long pre=0,after=0;
		this.state = ServerState.RUNNING;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(keepServering)
		{
			remainingRoundTime = roundTime - (double)(after-pre)/1000000000;
			while(remainingRoundTime > 0)
			{
				try {
					Thread.sleep(timeStep);
					if(this.state==ServerState.RUNNING)
					{
						remainingRoundTime -= ((double)timeStep * 0.001);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			Stoper.Start("NextTurn");
			pre = System.nanoTime();
			mesh.NextTurn();
			Stoper.Stop("NextTurn");
			if(toRefresh != null)
			{
				((gui.SquareMesh)toRefresh).repaint();
			}
			//System.out.println(Stoper.GetAllFlatValsPrintable());
			Stoper.ResetAll();
			after = System.nanoTime();
		}
		
	}
	
	public boolean PauseServer(boolean state)
	{
		if(this.state == ServerState.PRE_STARTED) return false;
		if(state)
		{
			this.state = ServerState.PAUSED;
		}
		else
		{
			this.state = ServerState.RUNNING;
		}	
		return true;
	}
	public void StopServer()
	{
		keepServering = false;
	}

	@Override
	public void run() {
		if(!canRun) return;
		ServerThreadFunction();
		
	}
	
	public void TestSettings()
	{
		setRoundTime(0.1);
		setMesh(StubLogic.get());
		StartServer();
	}
	
}
