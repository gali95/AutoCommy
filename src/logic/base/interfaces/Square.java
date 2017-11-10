package logic.base.interfaces;

import java.util.ArrayList;

public interface Square {

	public boolean AddACObject(ACObject obj);
	public int GetACObjectCount();
	public ACObject GetACObject(int index);
	public ACObject[] GetACObjects();
	public boolean ContainsACObject(ACObject obj);
	public boolean RemoveACObject(ACObject obj);
	public boolean RemoveACObject(int index);
	public int GetDrawableACObjectCount();
	public ArrayList<ACObject> GetDrawableACObjs();
	
	
}
