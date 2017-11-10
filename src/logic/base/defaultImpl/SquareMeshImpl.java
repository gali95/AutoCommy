package logic.base.defaultImpl;

import logic.base.interfaces.ACObject;
import logic.base.interfaces.Square;
import logic.base.interfaces.SquareMesh;
import stoper.Stoper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class SquareMeshImpl implements SquareMesh{
	
	private int currentACObjX,currentACObjY;

	private static final String ArrayList = null;
	
	private ArrayList<ACObject> currentlyExisting; 
	private int height,width;
	private Square[][] mesh;
	
	public SquareMeshImpl()
	{
		currentlyExisting = new ArrayList<ACObject>();
	}
	
	public boolean AddToExistingACObjs(ACObject newOne,int x,int y)
	{
		if(MeshContains(newOne)) return false;
		currentlyExisting.add(newOne);
		return true;
	}
	
	public boolean RemoveFromExistingACObjs(ACObject toRemove)
	{
		if(!MeshContains(toRemove)) return false;
		currentlyExisting.remove(toRemove);
		return true;
	}
	
	@Override
	public int GetMeshHeight() {
		return height;
	}

	@Override
	public int GetMeshWidth() {
		return width;
	}

	@Override
	public void ResetMesh(int width, int height) {
		mesh = new Square[width][height];
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				mesh[i][j] = new SquareImpl(this,i,j);
			}
		}
		this.width = width;
		this.height = height;
		
	}

	@Override
	public Square GetSquare(int x, int y) {
		// index out of range
		return mesh[x][y];
	}

	@Override
	public void NextTurn() {
		
		Stoper.Start("NextTurn PreparingAllObjArrayList");
		ArrayList<ACObject> randomizedOrder = (java.util.ArrayList<ACObject>) currentlyExisting.clone();
		Stoper.Stop("NextTurn PreparingAllObjArrayList");
		
		Stoper.Start("NextTurn ShufflingList");
		Collections.shuffle(randomizedOrder);
		Stoper.Stop("NextTurn ShufflingList");
		
		Stoper.Start("NextTurn NextTurnOnObjs");
		for(ACObject obj:randomizedOrder)
		{
			int[] pos = obj.getPosition();
			currentACObjX = pos[0];
			currentACObjY = pos[1];
			obj.NextTurn(this);
		}
		Stoper.Stop("NextTurn NextTurnOnObjs");
	}

	@Override
	public <T> ArrayList<T> GetEveryACOBj(Class<T> target, int squareX, int squareY) {
		
		int locX = squareX;
		int locY = squareY;
		if(locY < 0) 
		{
			return null;
		}
		if(locX < 0)
		{
			return null;
		}
		if(locX >= width)
		{
			return null;
		}
		if(locY >= height)
		{
			return null;
		}
		
		ArrayList<T> returnArr = new ArrayList<T>();
		
		Square targetSq = GetSquare(locX, locY);
		for(int i=0;i<targetSq.GetACObjectCount();i++)
		{
			if(targetSq.GetACObject(i).getClass() == target)
			{
				returnArr.add((T)targetSq.GetACObject(i));
			}
		}

		return returnArr;
	}

	private boolean MeshContains(ACObject target)
	{
		return currentlyExisting.contains(target);
	}
	
	@Override
	public boolean InsertACObj(ACObject target, int x, int y) {
		if(mesh == null) return false;
		if(MeshContains(target)) return false;
		
		//x+=currentACObjX;
		//y+=currentACObjY;
		
		if(y < 0) 
		{
			return false;
		}
		if(x < 0)
		{
			return false;
		}
		if(x >= width)
		{
			return false;
		}
		if(y >= height)
		{
			return false;
		}
		
		GetSquare(x, y).AddACObject(target);
		return true;
		
	}

	@Override
	public boolean RemoveACObj(ACObject target) {
		//if(!MeshContains(target)) return false;   not needed as long as Square checks it in RemoveACObject()
		int[] pos = target.getPosition();
		return mesh[pos[0]][pos[1]].RemoveACObject(target);		
	}

	@Override
	public boolean MoveACObj(ACObject target, int x, int y) {
		
		if(y < 0) 
		{
			return false;
		}
		if(x < 0)
		{
			return false;
		}
		if(x >= width)
		{
			return false;
		}
		if(y >= height)
		{
			return false;
		}
		
		if(RemoveACObj(target))
		{
			return InsertACObj(target, x, y);
		}
		else
		{
			return false;
		}
	}

}
