import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//@Author Bibin Koshy

public class Board implements ActionListener
{
	
	public static void main(String[] args)
	{
		Board Checkers = new Board();
	}	
        //setting up the window
		private Square[][] b; 
		private JFrame f = new JFrame();
        private JPanel p = new JPanel();
		private GridLayout g = new GridLayout(8,8);
		private boolean move;
		private Square selected;
		private ImageIcon selectedSquare = new ImageIcon("selected.png");
		private ImageIcon WhiteSquare = new ImageIcon("empty.png");
		private ImageIcon RedPiece = new ImageIcon("red.png");
		private ImageIcon WhitePiece = new ImageIcon("white.png");
		private int row = 0;
		private int column = 0;

	public Board() //constructor
	{
		//setting up the board and buttons
		b = new Square[8][8];
		selected = new Square(1,-1);
		p.setLayout(g);
		move = false;
					   
		AddPieces();
					   
		f.setVisible(true); 																											//displays the whole GUI
		f.setTitle("Checkers!"); 																										//setting the title
		f.setResizable(false); 																											//fixed window
		f.setSize(800,800); 																											//dimension of the frame
		f.add(p);
		f.setContentPane(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

		public void actionPerformed(ActionEvent a)
		{
			Object event = a.getSource(); 																								//getting information on the clicked JButton
			
			for (int i=0; i < 8; i++) 																									//going through each row and column to find out where the JButton is located
				{
					for (int j=0; j < 8; j++)
						{
							if (event == b[i][j].getButton() && move == false) 															//first click
							{
								if (b[i][j].getPiece() == 2) 																			//White Piece
								{
									selected = b[i][j]; 																				//the selected square
									row = i;
									column = j;

									if (j != 7 && i != 0) 																				//prevents clicking on a square that is not on the board
									{
										if (selected.canMoveTo(b[i-1][j+1])) 															//if the square diagonally left is a valid square to move
										{
											b[i-1][j+1].getButton().setIcon(selectedSquare); 											//change the piece to selected image
										}
									}
									if (j != 0 &&  i != 0) 																				//prevents clicking on a square that is not on the board
									{
										if (selected.canMoveTo(b[i-1][j-1])) 															//if the square diagonally right is a valid square to move
										{
											b[i-1][j-1].getButton().setIcon(selectedSquare); 											//change the piece to selected image
										}
									}
									if (j !=  6 && j != 7 && i != 0 && i != 1) 
									{
										if (b[i-1][j+1].getButton().getIcon().equals(RedPiece)) 										//if the square diagonally left is a red piece
										{
											if (selected.canMoveTo(b[i-2][j+2])) 														//if it can move to the next diagonal square
											{
											b[i-2][j+2].getButton().setIcon(selectedSquare); 											//select that square
											}
										}
									} 
									if (j !=  0 && j != 1 && i != 0 && i != 1)
									{
										if ((b[i-1][j-1].getButton().getIcon().equals(RedPiece)) && (selected.canMoveTo(b[i-2][j-2]))) 	//if the square diagonally right is a red piece and if it can move to the next diagonal square
										{
											b[i-2][j-2].getButton().setIcon(selectedSquare); 											//select that square
										}
									} 
									move = true;
								}
								else if (b[i][j].getPiece() == 3) 																		//Red Piece
								{
									selected = b[i][j];
									row = i;
									column = j;
									
									if (j != 7 && i != 7) 																				 //prevents clicking on a square that is not on the board
									{
										if (selected.canMoveTo(b[i+1][j+1])) 															 //if the square diagonally left is a valid square to move
										{
											b[i+1][j+1].getButton().setIcon(selectedSquare); 											 //change the piece to selected image
										}
									}
									if (j != 0 && i != 7) 																				 //prevents clicking on a square that is not on the board
									{
										if (selected.canMoveTo(b[i+1][j-1])) 															 //if the square diagonally right is a valid square to move
										{
											b[i+1][j-1].getButton().setIcon(selectedSquare); 											 //change the piece to selected image
										}
									} 
									if (j !=  0 && j != 1 && i != 7 && i != 7)
									{
										if (b[i+1][j-1].getButton().getIcon().equals(WhitePiece)) 										 //if the square diagonally left is a white piece
										{
											if (selected.canMoveTo(b[i+2][j-2])) 														 //if it can move to the next diagonal square
											{
											b[i+2][j-2].getButton().setIcon(selectedSquare); 											 //select that square
											}
										}
									} 
									if (j !=  6 && j != 7 && i != 6 && i != 7)
									{
										if ((b[i+1][j+1].getButton().getIcon().equals(WhitePiece)) && (selected.canMoveTo(b[i+2][j+2])))//if the square diagonally right is a white piece and if it can move to the next diagonal square
										{
											b[i+2][j+2].getButton().setIcon(selectedSquare);                                            //select that square
										}
									} 
									move = true; 
								}
							}
							else if (event == b[i][j].getButton() && move == true) 														//second click
								{
									if (selected.getPiece() == 2) 																		//white piece
									{
										if (((row == i + 1) && (column == j + 1)) || ((row == i + 1) && (column == j - 1))) 			//moves one square diagonally left/right
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										}
										if ((row == i + 2) && (column == j - 2)) 														//jump
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										b[i+1][j-1].setSquareWhite(); 																    //change the piece in the middle to be empty
										}
										if ((row == i + 2) && (column == j + 2)) 														//jump
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										b[i+1][j+1].setSquareWhite(); 																	//change the piece in the middle to be empty
										}
									}
									else if (selected.getPiece() == 3)  																//red piece
									{
										if (((row == i - 1) && (column == j - 1)) || ((row == i - 1) && (column == j + 1))) 			//moves one square diagonally left/right
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										}
										if (((row == i - 2) && (column == j - 2)) || ((row == i - 2) && (column == j + 2))) 			//jump
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										b[i-1][j+1].setSquareWhite(); 																	//change the piece in the middle to be empty
										}
										if (((row == i - 2) && (column == j - 2)) || ((row == i - 2) && (column == j + 2))) 			//jump
										{
										System.out.println("move");
										selected.moveTo(b[i][j]);
										b[i-1][j-1].setSquareWhite(); 																	//change the piece in the middle to be empty
										}
									}
									move = false; 																						//reseting back to the first cick
						
																																		//going through the loop to look for selected squares and deselecting them
									for (int k=0; k < 8; k++)
										{
											for (int l=0; l < 8; l++)
											{
												if(b[k][l].getButton().getIcon().equals(selectedSquare))
												{
													b[k][l].getButton().setIcon(WhiteSquare);
												}
											}
										} 
								}				
						}
				}			
		}
		//setting up the board and pieces
		public void AddPieces()
		{
			for(int i=0;i<8;i++)
			{
				for (int j=0;j<8;j++)
				{
					
						if ((i+j) % 2 == 0) 																							//if even black squares
						{
									 b[i][j] = new Square(i, j);
									 b[i][j].setSquareBlack();
									 p.add(b[i][j].getButton());
						} 
							
						else if ((i+j) % 2 != 0) 																						//if odd white squares
						{
									b[i][j] = new Square(i,j);
									b[i][j].setSquareWhite();
									p.add(b[i][j].getButton());
						}	  
						                                                                                   
					    if ((j %2 != 0 && i == 0) || (j % 2 == 0 && i == 1) || (j %2 != 0 && i == 2) ) 									//first three rows - odd squares
					    {
									b[i][j].setPieceRed(); 																				//red pieces
									p.add(b[i][j].getButton());
					    }
						if ((j %2 == 0 && i == 5) || (j % 2 != 0 && i == 6) || (j %2 == 0 && i == 7)) 									//last three rows - even squares
						{
									b[i][j].setPieceWhite(); 																			//white pieces
									p.add(b[i][j].getButton());
						}     
						b[i][j].getButton().addActionListener(this); 																	//adding a action listener for all buttons
				}
                       						
			}	
		}   	 
} 
				