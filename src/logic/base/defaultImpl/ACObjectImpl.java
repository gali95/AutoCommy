package logic.base.defaultImpl;

import java.awt.Image;

import logic.base.interfaces.ACObject;
import logic.base.interfaces.SquareMesh;

public abstract class ACObjectImpl implements ACObject{

	protected int[] position;

	@Override
	public int[] getPosition() {
		return position;
	}

	@Override
	public void setPosition(int[] pos) {
		if(pos==null) return;
		if(pos.length != 2) return;
		if(pos[0] < 0) return;
		if(pos[1] < 0) return;
		position = pos;	
	}

}
