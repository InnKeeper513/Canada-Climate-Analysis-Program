import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuListener;
import javax.swing.*;


/**
 * 
 * @author Ricky Yue
 * 
 * This class creates a frame to hold the precipitation panel.
 * It contains methods that stores the specific details of an objected 
 * of this class. 
 * 
 */

public class PGraphFrame extends JFrame {
	
	PGraphPanel precipitationPanel;
	
	public String selectedYear;
	String name;
	double [][] snow;
	double [][] rain;
	
	/**
	 * @author Ricky Yue
	 * 
	 * This constructor method is created to instantiate a specific 
	 * object of this class
	 * 
	 * @param name
	 * @param snow
	 * @param rain
	 * 
	 */
	public PGraphFrame (String name, double [][] snow, double [][] rain){
		setName (name);
		setSnow (snow);
		setRain (rain);
		
		setTitle ("Precipitation");
		setSize (1300, 1000);
		
		precipitationPanel = new PGraphPanel (name, snow, rain);
		add(precipitationPanel);
		
		setVisible (true);
	}
	
	
	/**
	 * @author Ricky Yue
	 * 
	 * @return the name of the city
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * @param set the city name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * @return the snow fall amount 
	 */
	public double[][] getSnow() {
		return snow;
	}
	
	/** 
	 * @author Ricky Yue
	 * 
	 * @param set the snow fall of the city
	 */
	
	public void setSnow(double[][] snow) {
		this.snow = snow;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * @return the rain fall amount
	 */
	public double[][] getRain() {
		return rain;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * @param set the rainfall of the city
	 */
	public void setRain(double[][] rain) {
		this.rain = rain;
	}

}
