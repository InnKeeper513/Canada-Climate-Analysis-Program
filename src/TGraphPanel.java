import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TGraphPanel  extends JPanel implements MouseListener, ItemListener{

	String name;
	double [][] dataSet;
	int graphOption =1;

	String selectedYear = "1993" ;
	int selectedYearInt = 1993;

	//For constant variables for chart layout
	public final int ORIGIN_X = 100;
	public final int ORIGIN_Y = 350;
	public final int X_AXIS_LENGTH = 750;
	public final int Y_AXIS_LENGTH = 300;
	public final int X_INTERVAL = 60;
	public final int Y_INTERVAL = 8;



	ImageIcon pointImage = new ImageIcon ("Red-Button.png");
	JLabel [] point = new JLabel [12]; 

	DefaultTableModel model;
	JTable dataTable ;

	JComboBox year;
	JComboBox graphs;

	public TGraphPanel (String name, double [][] dataSet){


		setName (name);
		setDataSet (dataSet);
		setLayout(null);

		pointImage = new ImageIcon (pointImage.getImage().getScaledInstance (20, 20, java.awt.Image.SCALE_SMOOTH));

		for (int i = 0; i < 12; i ++)	{
			point[i] = new JLabel ( pointImage);
			point[i].addMouseListener(this);
		}
		//---------------------------------------------------------------------------


		//----------------------------------------------------------------------------

		String[] yearNumber ={"1993", "1994", "1995", "1996", "1997","1998", 
				"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"};

		year = new JComboBox (yearNumber);
		year.addItemListener (this);

		year.setBounds(700, 12, 200, 25);
		add (year);



		String[] graphType ={"Line Graph", "Bar Graph"};

		graphs= new JComboBox (graphType);
		graphs.addItemListener (this);

		graphs.setBounds(1050, 78, 100, 35);
		add (graphs);

		//-------------------------------------------------------------

	}


	public void paintComponent (Graphics g){

		super.paintComponents(g);

		Graphics2D g2 = (Graphics2D)g;

		makeGraphFrame(g2);


		if  (graphOption == 1)
			drawLineGraph(g2, selectedYearInt);
		else if (graphOption == 2)
			drawBarGraph (g2, selectedYearInt);


		makeTable (selectedYearInt);

	}



	public void drawLineGraph (Graphics2D g2, int year){

		g2.setColor (Color.BLACK);

		for  (int n = 1 ; n<= 12; n ++){

			if (n < 12){
				g2.drawLine(ORIGIN_X + n * X_INTERVAL, (int) (ORIGIN_Y - Y_INTERVAL * dataSet[year][n] + 2),
						ORIGIN_X + (n+1) * X_INTERVAL,(int) (ORIGIN_Y - Y_INTERVAL * dataSet[year][n + 1] + 2 ));
			}

			point[n-1].setBounds(ORIGIN_X -5 + n * X_INTERVAL, (int) (ORIGIN_Y - Y_INTERVAL * dataSet[year][n]), 10, 10);
			add(point[n-1]);

			//g2.fillOval(ORIGIN_X -5 + n * X_INTERVAL, (int) (ORIGIN_Y + Y_INTERVAL * dataSet[year][n]), 10 , 10);
			//g2.drawOval(ORIGIN_X -5 + n * X_INTERVAL, (int) (ORIGIN_Y + Y_INTERVAL * dataSet[year][n]), 10 , 10);
		}

	}

	public void drawBarGraph (Graphics2D g2, int year ){

		for  (int n = 1; n <= 12; n++){
			g2.setColor((Color.ORANGE));
			g2.setStroke(new BasicStroke (4));

			if (dataSet[year][n] < 0){
				g2.drawRect(ORIGIN_X + X_INTERVAL * n- X_INTERVAL/2,  ORIGIN_Y , X_INTERVAL,  Math.abs((int)(dataSet [year][n] * Y_INTERVAL)));
				g2.setColor(Color.pink);
				g2.fillRect(ORIGIN_X + X_INTERVAL * n- X_INTERVAL/2,  ORIGIN_Y , X_INTERVAL,  Math.abs((int)(dataSet [year][n] * Y_INTERVAL)));
			}
			else {
				g2.drawRect(ORIGIN_X + X_INTERVAL * n -X_INTERVAL/2,  ORIGIN_Y -Math.abs((int)(dataSet [year][n] * Y_INTERVAL)) , X_INTERVAL,  Math.abs((int)(dataSet [year][n] * Y_INTERVAL)));
				g2.setColor(Color.pink);

				g2.fillRect(ORIGIN_X + X_INTERVAL * n -X_INTERVAL/2,  ORIGIN_Y -Math.abs((int)(dataSet [year][n] * Y_INTERVAL)) , X_INTERVAL,  Math.abs((int)(dataSet [year][n] * Y_INTERVAL)));
			}
		}
	}	

	public void makeTable (int year){

		String[] columnNames = {"MONTH", "TEMPERATURE (°C)"};

		String row1 = String.valueOf(dataSet[year][1]);
		String row2 = String.valueOf(dataSet[year][2]);
		String row3 = String.valueOf(dataSet[year][3]);
		String row4 = String.valueOf(dataSet[year][4]);
		String row5 = String.valueOf(dataSet[year][5]);
		String row6 = String.valueOf(dataSet[year][6]);
		String row7 = String.valueOf(dataSet[year][7]);
		String row8 = String.valueOf(dataSet[year][8]);
		String row9 = String.valueOf(dataSet[year][9]);
		String row10 = String.valueOf(dataSet[year][10]);
		String row11= String.valueOf(dataSet[year][11]);
		String row12 = String.valueOf(dataSet[year][12]);

		String [][] data = new String[][] {
			{"MONTH", "TEMPERATURE (°C)"},
			{"1",row1 }, {"2",row2 },{"3",row3 },{"4",row4 },{"5",row5 },{"6",row6},{"7",row7 },{"8",row8 },
			{"9",row9 },{"10",row10 },{"11",row11 },{"12",row12 } 

		} ;

		model = new DefaultTableModel (data, columnNames);

		if(dataTable!=null){
			remove(dataTable);
		}

		dataTable = new JTable (model);
		dataTable.setBounds(900, 225, 300, 210);
		add(dataTable);
	}

	public void makeGraphFrame(Graphics2D g2){

		//graph the axis

		g2.setStroke (new BasicStroke (3));

		g2.drawLine (ORIGIN_X, ORIGIN_Y, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y);
		g2.drawLine (100, ORIGIN_Y - Y_AXIS_LENGTH , 100, ORIGIN_Y);
		g2.drawLine (100, ORIGIN_Y,100, Y_AXIS_LENGTH + ORIGIN_Y);
		g2.setStroke (new BasicStroke (2));


		//creating the x axis intervals
		for  (int a = 1; a <= 12; a ++){
			g2.drawLine (ORIGIN_X +X_INTERVAL * a, ORIGIN_Y,ORIGIN_X + X_INTERVAL * a, ORIGIN_Y -5);
			g2.drawString(String.valueOf(a),ORIGIN_X +X_INTERVAL * a , ORIGIN_Y +20);
		}


		//Creating the y axis intervals
		for  (int a  = 0; a < 38; a ++){

			if (a%5 == 0){
				g2.drawLine(ORIGIN_X - 10, ORIGIN_Y + Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y + Y_INTERVAL * a );
				g2.drawString(String.valueOf(-a),ORIGIN_X -30 , ORIGIN_Y + Y_INTERVAL * a + 5);
			}
			else 
				g2.drawLine(ORIGIN_X - 5, ORIGIN_Y + Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y + Y_INTERVAL * a );
		}


		//The second half of y axis intervals below x axis
		for  (int a  = 0; a < 38; a ++){

			if (a%5 == 0){
				g2.drawLine(ORIGIN_X - 10, ORIGIN_Y - Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y - Y_INTERVAL * a );
				g2.drawString(String.valueOf(a),ORIGIN_X -30 , ORIGIN_Y - Y_INTERVAL * a + 5);
			}
			else 
				g2.drawLine(ORIGIN_X - 5, ORIGIN_Y - Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y -Y_INTERVAL * a );
		}


		//Drawing the dotted lines for pin pointing each point ----------------------------------------------------------------------------------------------------------------
		g2.setStroke(dottedLine(g2));
		g2.setColor(Color.LIGHT_GRAY);

		for (int a = 1; a <= 12; a ++){
			g2.drawLine(ORIGIN_X + X_INTERVAL * a, ORIGIN_Y - Y_AXIS_LENGTH, ORIGIN_X + X_INTERVAL * a, ORIGIN_Y + Y_AXIS_LENGTH);	
		}

		for (int a = 1; a < 38; a++){
			g2.drawLine(ORIGIN_X, ORIGIN_Y - a *Y_INTERVAL, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y -  a * Y_INTERVAL);
			g2.drawLine(ORIGIN_X, ORIGIN_Y + a *Y_INTERVAL, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y +  a * Y_INTERVAL);
		}

		//Outlining the graph grid
		//Outlining the graph grid
		g2.setStroke((new BasicStroke (2)));
		g2.setColor(Color.BLACK);
		g2.drawLine(ORIGIN_X, ORIGIN_Y - Y_AXIS_LENGTH, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y - Y_AXIS_LENGTH);
		g2.drawLine(ORIGIN_X, ORIGIN_Y + Y_AXIS_LENGTH, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y + Y_AXIS_LENGTH);
		g2.drawLine(ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y -Y_AXIS_LENGTH, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y + Y_AXIS_LENGTH);

	
		
		g2.setFont(new Font("Calibri", Font.BOLD, 20));
		g2.drawString("Month", 420, 670);
		g2.drawString("T", 40, ORIGIN_Y - 100);
		g2.drawString("e", 40, ORIGIN_Y - 80);
		g2.drawString("m", 40, ORIGIN_Y - 60);
		g2.drawString("p", 40, ORIGIN_Y - 40);
		g2.drawString("e", 40, ORIGIN_Y - 20);
		g2.drawString("r", 40, ORIGIN_Y);
		g2.drawString("a", 40, ORIGIN_Y + 20);
		g2.drawString("t", 40, ORIGIN_Y + 40);
		g2.drawString("u", 40, ORIGIN_Y + 60);
		g2.drawString("r", 40, ORIGIN_Y + 80);
		g2.drawString("e", 40, ORIGIN_Y + 100);
		g2.drawString("(°C)", 35, ORIGIN_Y + 120);
		
		

		g2.setFont(new Font("Calibri", Font.ITALIC, 25));
		g2.drawString(getName () + " Temperature Graph for Year: ", 290, 30);
		
		
	

		g2.setFont(new Font("Calibri", Font.BOLD, 20));
		g2.drawString("Graph Type: ", 900, 100);
		g2.drawString("Data Table" , 900, 200);

	}

	public Stroke dottedLine (Graphics2D g2){

		final float dash[] = {3.0f};
		final BasicStroke dotted =
				new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		return dotted;

	}




	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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


	@Override
	public void mouseClicked(MouseEvent m) {


		// TODO Auto-generated method stub

	}



	@Override
	public void mouseEntered(MouseEvent m) {


		String yearString = "" ; 

		for(int i=0; i<=11;i++){
			if ( point[i] == m.getSource())
				yearString = String.valueOf(dataSet[selectedYearInt][i + 1 ]+ " °C");
			point[i].setToolTipText(yearString);
		}
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void itemStateChanged(ItemEvent i) {
		// TODO Auto-generated method stub


		if (i.getStateChange() == ItemEvent.SELECTED){	
			selectedYear = (String) year.getSelectedItem();
			selectedYearInt = Integer.parseInt(selectedYear);
			for (int a = 0; a < 12; a++)
				remove (point[a]);
			repaint();
		}

		if (i.getStateChange() == ItemEvent.SELECTED){	
			String selected;
			selected= (String) graphs.getSelectedItem();


			if (selected == "Line Graph")
				graphOption = 1;
			else if ((selected == "Bar Graph")){
				graphOption = 2;
			}

			for (int a = 0; a < 12; a ++)
				remove (point[a]);

			repaint();
		}

	}


}
