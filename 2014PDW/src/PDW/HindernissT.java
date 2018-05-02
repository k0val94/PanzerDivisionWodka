//Anton Koval
//Projekt Arbeit 2013
//Panzer Division: Wodka
//HBFIT 12

package PDW;

import java.awt.Image;

import javax.swing.ImageIcon;

public class HindernissT {
	public int x;
	public int y;
	public Image Hinderniss;
	public int typ;
	
	public HindernissT() {
		
	}
	
	public HindernissT(String str,int zahl)
	{
		ImageIcon iihinderniss=new ImageIcon(this.getClass().getResource(str));
		Hinderniss=iihinderniss.getImage();
		typ=zahl;
	}
	
	public void setzeHinderniss(int x, int y,int Size)
	{
	    this.x = x;
        this.y = y;
	}
	
	
}
