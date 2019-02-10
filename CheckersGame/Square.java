import javax.swing.*;
import java.awt.*;
/**
*Square class represents a single square on the board
*It holds the information on the position of the square and the piece it contains
*It also holds the infoamtion on which JButton is in which square
*@Author Bibin Koshy
**/

public class Square 
{
	//instance variables
	private int row, col;
	private JButton button;
	private ImageIcon WhiteSquare = new ImageIcon("empty.png");
	private ImageIcon BlackSquare = new ImageIcon("empty2.png");
	private ImageIcon RedPiece = new ImageIcon("red.png");
	private ImageIcon WhitePiece = new ImageIcon("white.png");
	private ImageIcon selectedSquare = new ImageIcon("selected.png");
	private int piece;
	
	public int GetRow()
	{
		return row;
	}
	
	public int GetCol()
	{
		return col;
	}
	
	public void setRow(int rRow)
	{
		row = rRow;
	}
	
	public void setCol(int rCol)
	{
		col = rCol;
	}
	

	public void setSquareWhite() 									//empty square
	{
		button.setIcon(WhiteSquare);
		piece = 0;
	}
	
	public void setSquareBlack() 								    //empty square
	{
		button.setIcon(BlackSquare);
		piece = 1;
	}
	
	public void setPieceWhite() 									//white piece
	{
		button.setIcon(WhitePiece);
		piece = 2;
	}
	
	public void setPieceRed() 										//red piece
	{
		button.setIcon(RedPiece);
		piece = 3;
		
	}
	
	public JButton getButton()
	{
		return button;
	}
	
	/**
	* Returns what piece is currently at the square
	* @return Integer: 0= white square empty, 1= black square empty, 2= white piece, 3= red piece
	**/
	public int getPiece()
	{
		return piece;
	}
	/**
	*constructor that creates a new square on the board
	*@param (rRow) the row position of the square
	*@param (rCol) the column position of the square
	**/
	
	public Square (int rRow, int rCol)
	{
		button = new JButton();
		row = rRow;
		col = rCol;
		piece = 0; 													 //empty square
	}
	/**
	*This moves a piece from one square to another
	*@param (bSquare) The square where the piece is to be moved to
	**/
	public void moveTo (Square bSquare)
	{
		if (canMoveTo(bSquare))
		{
			if (piece == 2) 										 //if it's a white piece, fill the new square with a white piece
			{
				bSquare.setPieceWhite();
			}
			else if (piece == 3) 									 //if it's a red piece, fill the new square with a red piece
			{
				bSquare.setPieceRed();
			}
			this.setSquareWhite();
		}
	}
	/**
	*Checks the legal moves of the piece
	*@param (bSquare) The square where the piece is to be moved to
	*true if the piece moved to is legal otherwise false
	**/
	public boolean canMoveTo(Square bSquare)
	{
		if(this == bSquare) 										 //source(start) and destination squares are the same
		{
			return false;
		}
		if (bSquare.getPiece() > 1) 								 //if there is a piece at the destination square
		{
			return false;
		}
		else if (bSquare.getPiece() == 1) 							 //if it is a black square
		{
			return false;
		}
		return true;
	}
}

