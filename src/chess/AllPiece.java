/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * @author thamthearawiboon
 */
public class AllPiece {
    
    private Rook r1,r2;
    private Knight kn1,kn2;
    private Bishop b1,b2;
    private Pawn p[];
    private Queen q;
    private King k; 
    public static int numberUser = 0;
    
    public AllPiece(String color){
        r1 = new Rook(color.substring(0, 1)+"R1",color+"_Rook.png",numberUser);
        r2 = new Rook(color.substring(0, 1)+"R2",color+"_Rook.png",numberUser);
        kn1 = new Knight(color.substring(0, 1)+"KN1",color+"_Knight.png",numberUser);
        kn2 = new Knight(color.substring(0, 1)+"KN2",color+"_Knight.png",numberUser);
        b1 = new Bishop(color.substring(0, 1)+"B1",color+"_Bishop.png",numberUser);
        b2 = new Bishop(color.substring(0, 1)+"B2",color+"_Bishop.png",numberUser);
        q = new Queen(color.substring(0, 1)+"Q",color+"_Queen.png",numberUser);
        p = new Pawn[8];
	for(int i=0;i<8;i++)
	{
		p[i]=new Pawn(color.substring(0, 1)+"P"+(i+1),color+"_Pawn.png",numberUser);
	}
        if(numberUser==0){
            k=new King(color.substring(0, 1)+"K",color+"_King.png",numberUser,7,3);
        }
        else{
            k=new King(color.substring(0, 1)+"K",color+"_King.png",numberUser,0,3);
        }
        numberUser++;
    }

    public Rook getR1() {
        return r1;
    }

    public Rook getR2() {
        return r2;
    }

    public Knight getKN1() {
        return kn1;
    }

    public Knight getKN2() {
        return kn2;
    }

    public Bishop getB1() {
        return b1;
    }

    public Bishop getB2() {
        return b2;
    }

    public Pawn[] getP() {
        return p;
    }

    public Queen getQ() {
        return q;
    }

    public King getK() {
        return k;
    }
}
