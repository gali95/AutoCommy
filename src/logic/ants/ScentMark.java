package logic.ants;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.ACObject;
import logic.base.interfaces.Square;
import logic.base.interfaces.SquareMesh;

public class ScentMark extends ACObjectImpl {

	public enum Direction
	{
		NORTH(0,-1,0),
		EAST(90,0,1),
		WEST(270,0,-1),
		SOUTH(180,1,0);
		
		private int degrees;
		private int posX,posY;
		
		private Direction(int deg,int posX,int posY)
		{
			degrees = deg;
			this.posX = posX;
			this.posY = posY;
		}
		
		public int getDegrees()
		{
			return degrees;
		}
		public int getX()
		{
			return posX;
		}
		public int getY()
		{
			return posY;
		}
		public Direction getOpposedDir()
		{
			switch (this) {
			case NORTH:
				return SOUTH;

			case SOUTH:
				return NORTH;
				
			case EAST:
				return WEST;
				
			case WEST:
				return EAST;
				
			default:
				return null;
			}
		}
	}
	
	public ScentMark(ScentMark other)
	{
		super();
		this.dir = other.dir;
		this.tag = other.tag;
		this.currentStrenght = other.currentStrenght;
		this.strenghtDiffPerTurn = other.strenghtDiffPerTurn;
		this.strenghtToMaxDraw = other.strenghtToMaxDraw;
		this.arrowCol = other.arrowCol;
		graph = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}
	
	private String tag;
	private double currentStrenght,strenghtDiffPerTurn,strenghtToMaxDraw;
	private BufferedImage graph;
	private Direction dir;
	private Color arrowCol;
	
	
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public double getCurrentStrenght() {
		return currentStrenght;
	}

	public void setCurrentStrenght(double currentStrenght) {
		this.currentStrenght = currentStrenght;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public ScentMark(double start,double max,double timeDiff,String name,Direction dir, Color arrowCol)
	{
		super();
		
		currentStrenght = start;
		strenghtDiffPerTurn = timeDiff;
		strenghtToMaxDraw = max;
		tag = name;
		this.dir = dir;
		this.arrowCol = arrowCol;
		
		graph = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}
	
	@Override
	public void SetImage(Image img) {
		return;
	}

	@Override
	public Image GetImage() {
		Graphics2D g = (Graphics2D)graph.createGraphics();
		g.setComposite(AlphaComposite.Clear); 
		g.fillRect(0, 0, 100, 100); 
		g.setComposite(AlphaComposite.SrcOver);
		int alphaVal = (int)(((double)currentStrenght/strenghtToMaxDraw)*255);
		
		if(alphaVal>255)
		{
			alphaVal = 255;
		}
		if(alphaVal<0)
		{
			alphaVal = 0;
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,((float)alphaVal)/255));
		g.rotate(Math.toRadians(dir.getDegrees()),50,50);
		g.setColor(new Color(arrowCol.getRed(),arrowCol.getGreen(),arrowCol.getBlue(),alphaVal));
		g.fillRect(40, 40, 20, 40);
		int[] xPoints = {20,50,80};
		int[] yPoints = {50,20,50};
		g.fillPolygon(xPoints, yPoints, 3);
		
		return graph;
	}

	@Override
	public void NextTurn(SquareMesh currentMap) {
		
		currentStrenght += strenghtDiffPerTurn;
		if(currentStrenght<=0)
		{
			currentMap.RemoveACObj(this);
		}
		
	}

	public boolean IsSame(ScentMark other)
	{
		return (this.tag.equals(other.tag) && this.dir.equals(other.dir));
	}
	
	public void InsertItselfCopy(SquareMesh map, int xLoc, int yLoc)
	{
		ArrayList<ScentMark> scents = map.GetEveryACOBj(ScentMark.class, xLoc, yLoc);
		for(int i=0;i<scents.size();i++)
		{
			ScentMark sel = scents.get(i);
			if(this.IsSame(sel))
			{
				sel.setCurrentStrenght(sel.getCurrentStrenght()+currentStrenght);
				return;
			}
		}
		ScentMark inserted = new ScentMark(this);
		map.InsertACObj(inserted, xLoc, yLoc);
	}
}
