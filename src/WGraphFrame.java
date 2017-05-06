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

public class WGraphFrame extends JFrame  {


	WGraphPanel panel;
	public String selectedYear;

	String name;
	double [][] dataSet;

	public WGraphFrame (String name , double [][] dataSet){

		setName (name);
		setDataSet (dataSet);

		setTitle ("Wind Speed");
		setSize (1300, 1000);

		panel = new WGraphPanel(name, dataSet);
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