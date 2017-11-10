package logic.ants;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.SquareMesh;

public class Home extends ACObjectImpl {

	private Image img;
	
	public Home()
	{
		super();
		try {
			img = ImageIO.read(new File("home.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	}

}
