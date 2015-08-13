package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Graphics;


public class GUI_Statistic extends JFrame {

	
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI_Statistic() {

		      super("Statistik");
		      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		      setSize(800, 400);
		      setLocationRelativeTo(null);
		      
		      add(new Sheet());
		      
		      setVisible(true);
		   }
		}

		class Sheet extends JPanel {
		   /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			   
			   //Zeit Achse
			   	g.drawLine(20, 320, 700, 320);
			   	
			   	//Preis Achse
		        g.drawLine(20, 30, 20, 320);
		        
		        //Achsenbeschriftung
		        g.setColor(Color.GREEN);
		        g.drawString("Zeit [t]", 660, 335);
		        g.drawString("Preis [€]", 10, 10);
		        
		        //beschriftung der Achse

		        
		        
		        //Punkte zeichnen
		        g.setColor(Color.BLACK);
		        g.drawLine(200, 30, 30, 200);
		        g.drawLine(200, 200, 30, 30);
		        
		        
		        
		   }
		   

}
