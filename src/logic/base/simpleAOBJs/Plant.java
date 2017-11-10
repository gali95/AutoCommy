package logic.base.simpleAOBJs;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.ACObject;
import logic.base.interfaces.Square;
import logic.base.interfaces.SquareMesh;
import stoper.Stoper;

public class Plant extends ACObjectImpl{

	Image img;
	int actualSeed,seedsToClone,randomAdditionMax,randomAdditionMin;
	
	public Plant()
	{
		super();
		try {
			img = ImageIO.read(new File("plant.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		seedsToClone = 5;
		randomAdditionMin = 0;
		randomAdditionMax = 2;
		
	}
	
	@Override
	public void SetImage(Image img) {
		return;
		
	}

	@Override
	public Image GetImage() {
		return img;
	}

	@Override
	public void NextTurn(SquareMesh currentMap) {
		
		int existingNeighbours = 0;
		int[][] locationArray = { {1,0},
								  {-1,0},
								  {0,1},
								  {0,-1} };
		boolean[] taken = {false,false,false,false};
		ArrayList<Plant> getIt;
		
		Stoper.Start("Plant - CheckOtherSquaresInfo");
		for(int i=0;i<4;i++)
		{
			if((getIt=currentMap.GetEveryACOBj(Plant.class, locationArray[i][0]+position[0], locationArray[i][1]+position[1]))!=null&&(getIt.size() > 0))
			{
				existingNeighbours++;
				taken[i] = true;
			}
		}
		Stoper.Stop("Plant - CheckOtherSquaresInfo");
		
		if(existingNeighbours==4) return;
		
		Random rand = new Random();
		actualSeed += (4-existingNeighbours)+rand.nextInt(randomAdditionMax-randomAdditionMin)+randomAdditionMin;
		
		if(actualSeed >= seedsToClone)
		{
			actualSeed -= seedsToClone;
			boolean breakWhile = false;
			while(!breakWhile)
			{
				int randee = rand.nextInt(4);
				if(!taken[randee])
				{
					Stoper.Start("Plant - SpawningNew");
					currentMap.InsertACObj(new Plant(), locationArray[randee][0]+position[0], locationArray[randee][1]+position[1]);
					Stoper.Stop("Plant - SpawningNew");
					breakWhile = true;
				}
			}
		}
		
		
	}
	

	
	

}
