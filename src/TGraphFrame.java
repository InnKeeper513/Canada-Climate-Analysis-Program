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



public class TGraphFrame extends JFrame  { 


	TGraphPanel panel;

	
	public String selectedYear;
	
	String name;
	double [][] dataSet;
	
	
	
	
	public TGraphFrame (String name , double [][] dataSet){
		
		setName (name);
		setDataSet (dataSet);
	
		
		setTitle ("Temperature");
		setSize (1300, 1000);
		
		
		panel = new TGraphPanel(name, dataSet);
		add(panel);
		
		setVisible (true);
	
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		name = name;
	}
	



	
	public double[][] getDataSet() {
		return dataSet;
	}



	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(double[][] dataSet) {
		this.dataSet = dataSet;
	}

	
	
}
