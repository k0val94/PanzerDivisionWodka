
//Anton Koval
//Projekt Arbeit 2013
//Panzer Division: Wodka
//HBFIT 12
package PDW;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuT extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Image cover;
	public JButton onevsgegner=new JButton();  
	public JButton twovsgegner=new JButton(); 
	public JButton onevsone=new JButton(); 
	public JButton beenden=new JButton(); 
	ImageIcon iicover=new ImageIcon(this.getClass().getResource("Cover.png"));
	private JPanel menu=new JPanel();
	
	
	

	
	public MenuT(){
		super("Panzer Division Wodka"); //headline
		// cover
		cover = iicover.getImage();
		
		  
		
		ActionListener act_1vsgg = new ActionListener(){     //ActionListener für Ereignisse
       		public void actionPerformed( ActionEvent e){
       			
       			BoardT BoardVar = new BoardT();
      
       			BoardVar.anzspieler = 1;
       			BoardVar.anzstuetzpunkt = 1;
       			BoardVar.onevsone = false;
       			BoardVar.init();
       			
   			}
   		};
   		
	
   		ActionListener act_2vsgg = new ActionListener(){     //ActionListener für Ereignisse
   	       	
       		public void actionPerformed( ActionEvent e){
       			
       			BoardT BoardVar = new BoardT();

       			BoardVar.anzspieler = 2;
       			BoardVar.anzstuetzpunkt = 1;
       			BoardVar.onevsone = false;
       			BoardVar.init();
   			}
   		};
		
   		ActionListener act_1vs1 = new ActionListener(){     //ActionListener für Ereignisse
   	       	
       		public void actionPerformed( ActionEvent e){
       			
       			BoardT BoardVar = new BoardT();
   
       			BoardVar.anzspieler = 2;
       			BoardVar.anzstuetzpunkt = 2;
       			BoardVar.onevsone = true;
       			BoardVar.init();
   			}
   		};
   		ActionListener act_beenden = new ActionListener(){     //ActionListener für Ereignisse
   	       	
       		public void actionPerformed( ActionEvent e){
       			
       			System.exit(0);
   			}
   		};
		this.menu.add(this.onevsgegner);
		this.menu.add(this.twovsgegner);
		this.menu.add(this.onevsone);
		this.menu.add(this.beenden);
		
		this.add(this.menu);
		this.menu.setLayout(null); //damit die Butten verschoben werden können
		
		// Fenster
	
	    this.setResizable(false); 
	    this.setVisible(true);
	    this.menu.setBackground(Color.black);
	    this.setSize(832,832);
	    this.setLocationRelativeTo(null);	 //zentral
	    //Button
	  
	    this.onevsgegner.setBounds(832/2-290/2,200,290,60);
	    this.onevsgegner.addActionListener(act_1vsgg);
	    onevsgegner.setIcon(new ImageIcon(this.getClass().getResource("1b.png")));
	    this.twovsgegner.setBounds(832/2-290/2,270,290,60);
	    this.twovsgegner.addActionListener(act_2vsgg);
	    twovsgegner.setIcon(new ImageIcon(this.getClass().getResource("2b.png")));
	    this.onevsone.setBounds(832/2-290/2,340,290,60);
	    this.onevsone.addActionListener(act_1vs1);
	    onevsone.setIcon(new ImageIcon(this.getClass().getResource("3b.png")));
	    this.beenden.setBounds(832/2-290/2,410,290,60);
	    this.beenden.addActionListener(act_beenden);
	    beenden.setIcon(new ImageIcon(this.getClass().getResource("4b.png")));
	}
	    	

	public void paint(Graphics g){
		
		g.drawImage(cover,0,25,this);
		
	
	}

	public static void main(String argv[]){
		
		new MenuT();
		
	}
	
}
