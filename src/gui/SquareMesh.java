package gui;

import logic.base.defaultImpl.SquareMeshImpl;
import logic.base.defaultImpl.test.FakeACObject;

import javax.swing.*;
import java.awt.*;

public class SquareMesh extends JPanel {

	private int numRows,numColumns;
	private int squareWidth,squareHeight;
	private Square[][] squares;
	private logic.base.interfaces.SquareMesh logicMesh;
	private int currentRow,currentColumn;
	
	public SquareMesh(){
		setLayout(new GridLayout(1, 1));
		
		TestSettings();
	}
	
	public void TestSettings()
	{
		TestGUISettings();
		//TestMockLogic();
	}
	
	public void TestMockLogic()
	{
		int times = 3;
		//prepare testing logic
		SquareMeshImpl testLogic = new SquareMeshImpl();
		testLogic.ResetMesh(10, 10);
		for(int i=0;i<times;i++)
			//testLogic.GetSquare(3, 5).AddACObject(new FakeACObject());
		//testLogic.GetSquare(3, 6).AddACObject(new FakeACObject());
		
		//setting GUI mesh parameters
		SetLogicMesh(testLogic);
		
	}
	public void TestGUISettings()
	{
		SetSquaresSize(50,50);
		SetNumSquares(20,20);
		setCurrentColumn(0);
		setCurrentRow(0);
	}
	
	public int getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
		SendLogicToSquares();
	}
	public int getCurrentColumn() {
		return currentColumn;
	}
	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
		SendLogicToSquares();
	}
	private void SetProperSize()
	{
		if(numColumns < 0) 
			return;
		if(numRows < 0) 
			return;
		if(squareWidth < 0) 
			return;
		if(squareHeight < 0) 
			return;
		setSize(new Dimension(numColumns*squareWidth,numRows*squareHeight));
	}
	
	
	public void SetNumSquares(int rows,int columns)
	{
		numRows = rows;
		numColumns = columns;
		CreateSquares();
		SetProperSize();
	}
	public void SetSquaresSize(int width,int height)
	{
		squareHeight = height;
		squareWidth = width;
		SetProperSize();
		UpdateSquaresSize();
	}
	private void UpdateSquaresSize()
	{
		if(squares == null) 
			return;
		Dimension w = new Dimension(squareWidth,squareHeight);
		for(Square[] sqColumn:squares)
		{
			for(Square sq:sqColumn)
			{
				sq.setPreferredSize(w);
				sq.setSquareWidth(squareWidth);
				sq.setSquareHeight(squareHeight);
			}
		}
	}
	private void RemoveSquares()
	{
		removeAll();
	}
	private void CreateSquares()
	{
		RemoveSquares();
		setLayout(new GridLayout(numRows, numColumns));
		squares = new Square[numColumns][numRows];
		for(int i=0;i<numColumns;i++)
		{
			for(int j=0;j<numRows;j++)
			{
				squares[i][j] = new FittingSquare();
				add(squares[i][j]);
			}
		}
		UpdateSquaresSize();
	}
	public void SetLogicMesh(logic.base.interfaces.SquareMesh logicMesh)
	{
		this.logicMesh = logicMesh;
		SendLogicToSquares();
	}
	public logic.base.interfaces.SquareMesh GetLogicMesh()
	{
		return logicMesh;
	}
	private void SendLogicToSquares()
	{
		if(logicMesh != null && squares != null)
		{
			for(int i=0;i<numColumns;i++)
			{
				for(int j=0;j<numRows;j++)
				{
					if(i+currentColumn < 0 || i+currentColumn>logicMesh.GetMeshWidth()-1)
					{
						squares[i][j].setSource(null);
					}
					else if(j+currentRow < 0 || j+currentRow>logicMesh.GetMeshHeight()-1)
					{
						squares[i][j].setSource(null);
					}
					else
					{
						squares[i][j].setSource(logicMesh.GetSquare(i+currentColumn, j+currentRow));
					}
					
				}
			}
		}
	}

	public void repaintSquares()
	{
		for(Square[] sqRow:squares)
		{
			for(Square sq:sqRow)
			{
				sq.repaint();
			}
		}
	}
}
