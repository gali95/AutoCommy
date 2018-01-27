package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

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
				drawableObjs.sort(new Comparator<ACObject>() {
					@Override
					public int compare(ACObject o1, ACObject o2) {
						double result = o1.getDrawable().getDrawOrder() - o2.getDrawable().getDrawOrder();
						if(result > -1 && result < 1 && result != 0)
						{
							if(result < 0)
							{
								result = -1;
							}
							else
							{
								result = 1;
							}
						}
						return (int)result;
					}
				});
				ListIterator<ACObject> it = drawableObjs.listIterator();
				ArrayList<ACObject> sameOrderDrawableObjs = new ArrayList<>();
				double actualDrawOrder=0;
				boolean firstTimeSetActualDrawOrder = true;
				while(it.hasNext())
				{
					ACObject actual = it.next();
					if(firstTimeSetActualDrawOrder == false) {
						if (actual.getDrawable().getDrawOrder() != actualDrawOrder) {
							int squareSize = (int) (Math.ceil((Math.sqrt(sameOrderDrawableObjs.size()))));
							double partialWidth = (double) width / squareSize;
							double partialHeight = (double) height / squareSize;
							int actX, actY;
							for (int i = 0; i < sameOrderDrawableObjs.size(); i++) {
								actX = i % squareSize;
								actY = i / squareSize;
								g.drawImage(sameOrderDrawableObjs.get(i).GetImage(), (int) (actX * partialWidth), (int) (actY * partialHeight), (int) partialWidth, (int) partialHeight, null);
							}
							sameOrderDrawableObjs.clear();
						}
					}
					else
					{
						firstTimeSetActualDrawOrder = false;
					}
					actualDrawOrder = actual.getDrawable().getDrawOrder();
					sameOrderDrawableObjs.add(actual);
					it.remove();
				}
				int squareSize = (int) (Math.ceil((Math.sqrt(sameOrderDrawableObjs.size()))));
				double partialWidth = (double) width / squareSize;
				double partialHeight = (double) height / squareSize;
				int actX, actY;
				for (int i = 0; i < sameOrderDrawableObjs.size(); i++) {
					actX = i % squareSize;
					actY = i / squareSize;
					g.drawImage(sameOrderDrawableObjs.get(i).GetImage(), (int) (actX * partialWidth), (int) (actY * partialHeight), (int) partialWidth, (int) partialHeight, null);
				}
				sameOrderDrawableObjs.clear();

			}
						
		}
		else
		{
			g.drawImage(noSourceImg, 0, 0, null);
		}
	}
	
}
