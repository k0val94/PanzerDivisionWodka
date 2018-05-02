//Anton Koval
//Projekt Arbeit 2013
//Panzer Division: Wodka
//HBFIT 12

package PDW;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GegnerT {
	
		BoardT board;
		public int x;
		public int y;
		public Image Gegner;
		public int typ;
		public String str = "GegnerTankdown0.png";
		public GegnerT() {
			
		}
		
		public GegnerT(String str,int zahl)
		{
			

			ImageIcon iigegner=new ImageIcon(this.getClass().getResource(this.str));
			Gegner=iigegner.getImage();
			typ=zahl;
			
		}
		
		public void setzeItem(int x, int y)
		{
			
	        this.x = x;
	        this.y = y;

		}
		
		public void setzeString(String str)
		{
			
			this.str = str;
			ImageIcon iigegner=new ImageIcon(this.getClass().getResource(this.str));
			Gegner=iigegner.getImage();

		}
}
