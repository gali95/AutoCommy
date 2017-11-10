package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.base.interfaces.ACObject;

public class FittingSquare extends Square {

	public static int alphaVal;
	private BufferedImage graph;
	
	public FittingSquare()
	{
		graph = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}
	
	static
	{
		alphaVal = 15;
	}
	
	@Override
	protected  void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(source != null)
		{
			g.drawImage(backgroundImg, 0, 0, null);
			
			ArrayList<ACObject> drawableObjs = source.GetDrawableACObjs();
			if(drawableObjs.size() > 0)
			{
				int squareSize = (int)(Math.ceil((Math.sqrt(drawableObjs.size()))));
				double partialWidth = (double)width / squareSize;
				double partialHeight = (double)height / squareSize;
				int actX,actY;
				for(int i=0;i<drawableObjs.size();i++)
				{
					actX = i % squareSize;
					actY = i / squareSize;
					g.drawImage(drawableObjs.get(i).GetImage(), (int)(actX*partialWidth), (int)(actY*partialHeight), (int)partialWidth, (int)partialHeight, null);
					
				}
			}
						
		}
		else
		{
			g.drawImage(noSourceImg, 0, 0, null);
		}
	}
	
}
