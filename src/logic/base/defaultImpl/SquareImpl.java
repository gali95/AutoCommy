package logic.base.defaultImpl;

import java.util.ArrayList;

import logic.base.classes.Drawable;
import logic.base.interfaces.ACObject;
import logic.base.interfaces.Square;

public class SquareImpl implements Square{

	ArrayList<ACObject> content;
	SquareMeshImpl up;
	int xOnMesh,yOnMesh;
	
	public SquareImpl(SquareMeshImpl up,int xOnMesh,int yOnMesh)
	{
		content = new ArrayList<ACObject>();
		this.up = up;
		this.xOnMesh = xOnMesh;
		this.yOnMesh = yOnMesh;
	}
	
	@Override
	public boolean AddACObject(ACObject obj) {
		if(ContainsACObject(obj)) return false;
		if(!up.AddToExistingACObjs(obj, xOnMesh, yOnMesh)) return false;
		content.add(obj);
		int[] newPos = {xOnMesh, yOnMesh};
		obj.setPosition(newPos);
		
		return true;
	}

	@Override
	public int GetACObjectCount() {
		return content.size();
	}
	
	@Override
	public int GetDrawableACObjectCount() {
		int ret=0;
		for(ACObject obj:content)
		{
			if(obj.GetImage()!=null)
			{
				ret++;
			}
		}
		return ret;
	}

	@Override
	public ACObject GetACObject(int index) {
		return content.get(index);
	}

	@Override
	public boolean ContainsACObject(ACObject obj) {
		return content.contains(obj);
	}

	@Override
	public boolean RemoveACObject(ACObject obj) {
		if(!ContainsACObject(obj)) return false;
		content.remove(obj);
		up.RemoveFromExistingACObjs(obj);
		return true;
	}

	@Override
	public boolean RemoveACObject(int index) {
		if(index<0 || index>content.size()-1) return false;
		up.RemoveFromExistingACObjs(content.get(index));
		content.remove(content.get(index));
		return true;
	}

	@Override
	public ArrayList<ACObject> GetDrawableACObjs() {
		ArrayList<ACObject> returnArr = new ArrayList<ACObject>();
		for(ACObject obj:content)
		{
			if(obj.getDrawable().getInteractionType() != Drawable.DrawableType.UNDRAWABLE && obj.GetImage() != null) returnArr.add(obj);
		}
		return returnArr;
	}
	
	@Override
	public ACObject[] GetACObjects()
	{
		ACObject[] ret = new ACObject[content.size()];
		return content.toArray(ret);
	}

	
	
}
