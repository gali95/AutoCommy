package logic.base.interfaces;

import java.awt.Image;

public interface ACObject {

	public void SetImage(Image img);
	public Image GetImage();
	public void NextTurn(SquareMesh currentMap);
	public int[] getPosition();
	public void setPosition(int[] pos);
	
}
