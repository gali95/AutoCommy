package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Square extends JPanel{

	protected Image backgroundImg,noSourceImg;
	protected logic.base.interfaces.Square source;
	protected int width,height;
	
	Square()
	{
		try {
			backgroundImg = ImageIO.read(new File("noCont.png"));
			noSourceImg = ImageIO.read(new File("noSrc.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Square(logic.base.interfaces.Square source)
	{
		this.source = source;
		try {
			backgroundImg = ImageIO.read(new File("noSrc.png"));
			noSourceImg = ImageIO.read(new File("noSrc.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int getSquareWidth() {
		return width;
	}

	public void setSquareWidth(int width) {
		this.width = width;
	}

	public int getSquareHeight() {
		return height;
	}

	public void setSquareHeight(int height) {
		this.height = height;
	}

	public Image getBackgroundImg() {
		return backgroundImg;
	}
	public void setBackgroundImg(Image backgroundImg) {
		this.backgroundImg = backgroundImg;
	}


	public logic.base.interfaces.Square getSource() {
		return source;
	}
	public void setSource(logic.base.interfaces.Square source) {
		this.source = source;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(source != null)
		{
			g.drawImage(backgroundImg, 0, 0, null);
			if(source.GetACObjectCount() > 0)
			{
				for(int i=0;i<source.GetACObjectCount();i++)
				{
					g.drawImage(source.GetACObject(i).GetImage(), 0, 0, null);
				}
			}		
		}
		else
		{
			g.drawImage(noSourceImg, 0, 0, null);
		}
	}
	
}
