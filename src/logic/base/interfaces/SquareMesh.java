package logic.base.interfaces;

import java.util.ArrayList;

public interface SquareMesh {

	public int GetMeshHeight();
	public int GetMeshWidth();
	
	public void ResetMesh(int width,int height);
	public Square GetSquare(int x,int y);
	public void NextTurn();
	
	public <T> ArrayList<T> GetEveryACOBj(Class<T> target, int squareX, int squareY);
	public boolean InsertACObj(ACObject target,int x,int y);
	public boolean RemoveACObj(ACObject target);
	public boolean MoveACObj(ACObject target,int x,int y);
	
	/// to use by object in nextTurn
}

