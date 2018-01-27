package logic.ants;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import logic.ants.ScentMark.Direction;
import logic.base.classes.ACOTimer;
import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.SquareMesh;

public class Ant extends ACObjectImpl{

	protected Image img;
	private ScentMark standardScent;
	private AntFoodSystem stomach;
	private int goHome;
	private double goHomeMax,goHomeMaxInc;
	private Direction prevDir;
	private double moveSpeed = 1;
	private double timeToMove;
	
	public Ant()
	{
		super();
		try {
			img = ImageIO.read(new File("ant.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		standardScent = new ScentMark(0.2,1,-0.0001,"I WAS HERE",Direction.NORTH,Color.CYAN);
		//stomach = new AntFoodSystem(maxAmmount, currentAmmount, hungerDecrease, minAfterShare)
		goHomeMax = 2;
		goHomeMaxInc = 0.2;
		goHome = (int)goHomeMax;
		prevDir = null;
		getDrawable().setDrawOrder(100);
		timeToMove = moveSpeed;
	}
	
	@Override
	public void SetImage(Image img) {
		return;
		
	}

	@Override
	public Image GetImage() {
		return img;
	}

	public void Move(SquareMesh map, Direction dir)
	{
		int prevX = position[0];
		int prevY = position[1];
		map.MoveACObj(this, position[0]+dir.getX(), position[1]+dir.getY());
		//if(prevDir != null)
		{
			ScentMark strongerHome=null;
			ArrayList<ScentMark> scents = map.GetEveryACOBj(ScentMark.class, position[0], position[1]);
			for(ScentMark sc: scents)
			{
				if(sc.getTag()=="I WAS HERE")
				{
					if(strongerHome==null)
					{
						strongerHome = sc;
					}
					else
					{
						if(sc.getCurrentStrenght() > strongerHome.getCurrentStrenght())
						{
							strongerHome = sc;
						}
					}
				}
			}
			if(strongerHome != null)
			{
				standardScent.setDir(strongerHome.getDir());
			}
			else
			{
				standardScent.setDir(dir);
			}	
			if(goHome > 0)
			{
				standardScent.InsertItselfCopy(map, position[0], position[1]);
			}
			
			
		}
		prevDir = dir;
	}
	
	@Override
	public void NextTurn(SquareMesh currentMap) {

		if(timeToMove < 0) {

			InHomeRoutine(currentMap);
			if (goHome <= 0) {
				GoToHome(currentMap, false);
			} else {
				Scout(currentMap);
			}
			//Move(currentMap,Direction.NORTH);
			goHome -= 1;
			timeToMove = moveSpeed;
		}
		timeToMove -= ACOTimer.getDeltaNextTurnTime();

	}

	public void InHomeRoutine(SquareMesh currentMap)
	{
		ArrayList<Home> around = currentMap.GetEveryACOBj(Home.class, position[0], position[1]);
		if(around==null || around.size()== 0)
		{
			return;
		}
		goHome = (int)goHomeMax;
		goHomeMax += goHomeMaxInc;
	}
	
	public void GoToHome(SquareMesh currentMap,boolean wider)
	{
		ScentMark strongerHome=null;
		ArrayList<ScentMark> scents = currentMap.GetEveryACOBj(ScentMark.class, position[0], position[1]);
		if(wider)
		{
			ArrayList<ScentMark> anotherScents;
			int[][] poschanges = { {1,0},{-1,0},{0,1},{0,-1} };
			for(int i=0;i<4;i++)
			{
				anotherScents = currentMap.GetEveryACOBj(ScentMark.class, position[0]+poschanges[i][0], position[1]+poschanges[i][1]);
				if(anotherScents != null)
				{
					scents.addAll(anotherScents);
				}
			}
		}
		for(ScentMark sc: scents)
		{
			if(sc.getTag()=="I WAS HERE")
			{
				if(strongerHome==null)
				{
					strongerHome = sc;
				}
				else
				{
					if(sc.getCurrentStrenght() > strongerHome.getCurrentStrenght())
					{
						strongerHome = sc;
					}
				}
			}
		}
		
		if(strongerHome == null)
		{
			if(wider)
			{
				Scout(currentMap);
			}
			else
			{
				GoToHome(currentMap,true);
			}
		}
		else
		{
			Move(currentMap,strongerHome.getDir().getOpposedDir());
		}
	}

	public void Scout(SquareMesh currentMap)
	{
		Random rand = new Random();
		int randomized = rand.nextInt(4);
		switch (randomized) {
		
		case 0:
			Move(currentMap,Direction.NORTH);
			break;
			
		case 1:
			Move(currentMap,Direction.SOUTH);
			break;
		
		case 2:
			Move(currentMap,Direction.EAST);
			break;
			
		case 3:
			Move(currentMap,Direction.WEST);
			break;

		default:
			break;
		}
	}
}
