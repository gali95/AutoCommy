package logic.server.test;

import logic.ants.Ant;
import logic.ants.Home;
import logic.base.defaultImpl.SquareMeshImpl;
import logic.base.interfaces.SquareMesh;

public class StubLogic {

	public static SquareMesh get()
	{
		SquareMesh ret = new SquareMeshImpl();
		ret.ResetMesh(20, 20);
		//ret.InsertACObj(new ScentMark(100,100,-1,"DOM",Direction.WEST, new Color(145,41,201)), 4, 3);
		for(int i=0;i<5;i++)
		ret.InsertACObj(new Ant(), 9, 9);
		//
		ret.InsertACObj(new Home(), 9, 9);
		return ret;
	}
	
}
