package logic.server.test;

import logic.ants.Ant;
import logic.ants.Home;
import logic.base.interfaces.SquareMesh;
import logic.graKsawerego.AntQueen;
import logic.graKsawerego.GraMesh;
import logic.graKsawerego.spn;

public class StubLogic {

	public static SquareMesh get()
	{
		GraMesh ret = new GraMesh();
		ret.ResetMesh(20, 20);
		//ret.InsertACObj(new ScentMark(100,100,-1,"DOM",Direction.WEST, new Color(145,41,201)), 4, 3);
		for(int i=0;i<5;i++)
		ret.InsertACObj(new Ant(), 3, 9);
		//
		ret.InsertACObj(new Home() , 3, 9);
		ret.InsertACObj(new AntQueen(), 3, 9);
		spn gracz = new spn();
		ret.InsertACObj(gracz , 17, 9);
		ret.setPlayer(gracz);
		return ret;
	}
	
}
