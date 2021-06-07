/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * @author Acer
 */
public class BoardChess {
    
        public static Cell boardState[][];
        public static AllPiece white;
        public static AllPiece black;
        
	//public ArrayList<Cell> destinationlist = new ArrayList<Cell>();
    public static  void makeBoardChess() {
        
        white = new AllPiece("White");
        black = new AllPiece("Black");
           
    }
    public static King getKing(int color)
	{
		if (color == white.getK().getcolor())
			return white.getK();
		else
			return black.getK();
	}
    
    public static void createState(Main m,JPanel board ){
        pieces.Piece P;
        boardState = new Cell[8][8];
        AllPiece first;
        AllPiece second;
        if(white.getK().getcolor()==0){
            first = white;
            second = black;
        }
        else{
            first = black;
            second = white;
        }
    		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	
				P = null;
                                if(i==7&&j==0)
					P= first.getR1();
				else if(i==7&&j==7)
					P= first.getR2();
				else if(i==7&&j==1)
					P= first.getKN1();
				else if (i==7&&j==6)
					P= first.getKN2();
				else if(i==7&&j==2)
					P= first.getB1();
				else if(i==7&&j==5)
					P= first.getB2();
				else if(i==7&&j==3)
					P= first.getK();
				else if(i==7&&j==4)
					P= first.getQ();
				else if(i==6)
					P= first.getP()[j];
                                
                                else if(i==0&&j==0)
					P= second.getR1();
				else if(i==0&&j==7)
					P= second.getR2();
                                else if(i==0&&j==1)
					P= second.getKN1();
				else if (i==0&&j==6)
					P= second.getKN2();
                                else if(i==0&&j==3)
					P= second.getK();
				else if(i==0&&j==4)
					P= second.getQ();
                                else if(i==0&&j==2)
					P= second.getB1();
				else if (i==0&&j==5)
					P= second.getB2();
                                else if(i==1)
                                        P= second.getP()[j];
				Cell cell=new Cell(i,j,P);
				cell.addMouseListener(m);
				board.add(cell);
				boardState[i][j]=cell;
			}
    }
    public static boolean checkmate(int color )
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{
    			if (boardState[i][j].getpiece()!=null && boardState[i][j].getpiece().getcolor()==color)
    			{
    				dlist.clear();
    				dlist=boardState[i][j].getpiece().move(boardState, i, j);
    				dlist= incheckfilter(dlist,boardState[i][j],color);
    				if(dlist.size()!=0)
    					return false;
    			}
    		}
    	}
    	return true;
    }
    
    
    public static boolean checkLifeKing(int chance){
        return boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck();
    }
    
    
    public static boolean willkingbeindanger(Cell fromcell,Cell tocell, int chance)
    {
        Cell newboardstate[][] = new Cell[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {    try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}

        if(newboardstate[tocell.x][tocell.y].getpiece()!=null)
            newboardstate[tocell.x][tocell.y].removePiece();

        newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
        if(newboardstate[tocell.x][tocell.y].getpiece() instanceof King)
        {
            ((King)(newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
            ((King)(newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
        }
        newboardstate[fromcell.x][fromcell.y].removePiece();
        if (((King)(newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate)==true)
            return true;
        else
            return false;
    }
    
    
    private static ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
    		x=BoardChess.getKing(color).getx();
    		y=BoardChess.getKing(color).gety();
    		if(newboardstate[tempc.x][tempc.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    
    public static void cleandestinations(ArrayList<Cell> destlist)      //Function to clear the last move's destinations
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removepossibledestination();
    }
    
    
    public static void highlightdestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setpossibledestination();
    }
    
    
    public static ArrayList<Cell> filterdestination (ArrayList<Cell> destlist, Cell fromcell, int chance)
    {
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Cell newboardstate[][] = new Cell[8][8];
        ListIterator<Cell> it = destlist.listIterator();
        int x,y;
        while (it.hasNext())
        {
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {    try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}

            Cell tempc = it.next();
            if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
                newboardstate[tempc.x][tempc.y].removePiece();
            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
            x=getKing(chance).getx();
            y=getKing(chance).gety();
            if(newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King)
            {
                ((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
                ((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
                x=tempc.x;
                y=tempc.y;
            }
            newboardstate[fromcell.x][fromcell.y].removePiece();
            if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
                newlist.add(tempc);
        }
        return newlist;
    }
    
}
