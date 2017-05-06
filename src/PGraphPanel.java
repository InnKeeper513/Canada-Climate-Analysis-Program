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

/**
 * 
 * @author Ricky Yue
 * 
 * This class is a panel on where a precipitation graph will be drawn for 
 * a specific city (Whitehorse and Edmonton). 
 *
 */
public class PGraphPanel extends JPanel implements MouseListener, ItemListener {


	String name;
	double [][] rain;
	double [][] snow;
	int graphOption = 1;

	String selectedYear = "1993" ;
	int selectedYearInt = 1993;

	//For constant variables for chart layout
	public final int ORIGIN_X = 100;
	public final int ORIGIN_Y = 650;
	public final int X_AXIS_LENGTH = 750;
	public final int Y_AXIS_LENGTH = 600;
	public final int X_INTERVAL = 60;
	public final int Y_INTERVAL = 8;

	ImageIcon pointImage = new ImageIcon ("blueCircle.png");
	ImageIcon pointImage2 = new ImageIcon ("Red-Button.png");
	JLabel [] point  = new JLabel [12];  //snow
	JLabel [] point2  = new JLabel [12]; // rain

	DefaultTableModel model;
	JTable dataTable ;

	JComboBox year;
	JComboBox graphs;

	
	/**
	 * @author Ricky Yue
	 * 
	 * This constuctor method receive parameters passed on by
	 * the the PGraphFrame. It also instantiates the labels used 
	 * as scatter plots (points) on the graph. In addition, it 
	 * instantiates the combo boxes. 
	 * 
	 * @param name
	 * @param snow
	 * @param rain
	 * 
	 */
	public PGraphPanel (String name, double [][] snow, double [][] rain){

		setName(name);
		setRain(rain);
		setSnow(snow);
		setLayout(null);

		pointImage = new ImageIcon (pointImage.getImage().getScaledInstance (20, 20, java.awt.Image.SCALE_SMOOTH));
		pointImage2 = new ImageIcon (pointImage2.getImage().getScaledInstance (20, 20, java.awt.Image.SCALE_SMOOTH));

		for (int a = 0; a < 12; a ++){

			point[a] = new JLabel (pointImage);
			point2[a] = new JLabel (pointImage2);
			point[a].addMouseListener(this);
			point2[a].addMouseListener(this);
		}

		String [] yearNumber={"1993", "1994", "1995", "1996", "1997","1998", 
				"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"};

		year = new JComboBox (yearNumber);
		year.addItemListener(this);
		year.setBounds(700, 12, 200, 25);
		add (year);

		String[] graphType ={"Line Graph", "Bar Graph"};

		graphs= new JComboBox (graphType);
		graphs.addItemListener (this);
		graphs.setBounds(1050, 78, 100, 35);
		add (graphs);
	}

	/**
	 * @author ricky_000
	 * 
	 * This method calls the drawLineGraph method, drawBarGraph method
	 * and makeTable method
	 * .
	 */
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

	/**
	 * @author Ricky Yue
	 * 
	 * This method takes in the Graphics2D and the year of the
	 * weather data to be graphed. It uses these parameters 
	 * to create a line graph, reflecting the snow and rain fall 
	 * amount per month for each year.
	 * 
	 * @param g2
	 * @param year
	 */
	public void drawLineGraph (Graphics2D g2, int year){

		for  (int n = 1 ; n<= 12; n ++){

			if (n < 12){
				g2.setColor (Color.BLACK);
				g2.drawLine(ORIGIN_X + n * X_INTERVAL, (int) (ORIGIN_Y - (Y_INTERVAL/4) * rain[year][n]),
						ORIGIN_X + (n+1) * X_INTERVAL,(int) (ORIGIN_Y - (Y_INTERVAL/4) * rain[year][n + 1] ));

				g2.setColor(Color.BLUE);
				g2.drawLine(ORIGIN_X + n * X_INTERVAL, (int) (ORIGIN_Y - (Y_INTERVAL/4) * snow[year][n]),
						ORIGIN_X + (n+1) * X_INTERVAL,(int) (ORIGIN_Y - (Y_INTERVAL/4)* snow[year][n + 1]));
			}

			point[n-1].setBounds(ORIGIN_X -5 + n * X_INTERVAL, (int) (ORIGIN_Y - (Y_INTERVAL/4) * snow[year][n] -3), 10, 10);
			add(point[n-1]);

			point2[n-1].setBounds(ORIGIN_X -5 + n * X_INTERVAL, (int) (ORIGIN_Y - (Y_INTERVAL/4)* rain[year][n] -3), 10, 10);
			add(point2[n-1]);
			g2.setStroke(new BasicStroke (3));

		}

	}
	
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method takes in the Graphics2D and the year of the
	 * weather data to be graphed. It uses these parameters 
	 * to create a bar graph, reflecting the snow and rain fall 
	 * amount per month for each year.
	 * 
	 * @param g2
	 * @param year
	 */
	public void drawBarGraph (Graphics2D g2, int year ){

		for  (int n = 1; n <= 12; n++){

			g2.setColor(Color.BLUE);
			g2.setStroke(new BasicStroke (4));

			g2.drawRect(ORIGIN_X + X_INTERVAL * n -X_INTERVAL/3 +2,  (int) (ORIGIN_Y -snow [year][n] * (Y_INTERVAL/4)) , X_INTERVAL/3 -3, (int)(snow [year][n] * (Y_INTERVAL/4)));
			g2.fillRect(ORIGIN_X + X_INTERVAL * n -X_INTERVAL/3 +2,  (int) (ORIGIN_Y -snow [year][n] * (Y_INTERVAL/4)) , X_INTERVAL/3 -3, (int)(snow [year][n] * (Y_INTERVAL/4)));

			g2.setColor(Color.RED);
			g2.drawRect(ORIGIN_X + X_INTERVAL * n + 3 ,  (int) (ORIGIN_Y -rain [year][n] * (Y_INTERVAL/4)) , X_INTERVAL/3 -4, (int)(rain [year][n] * (Y_INTERVAL/4)));
			g2.fillRect(ORIGIN_X + X_INTERVAL * n + 3,  (int) (ORIGIN_Y -rain [year][n] * (Y_INTERVAL/4)) , X_INTERVAL/3 -4, (int)(rain [year][n] * (Y_INTERVAL/4)));

		}


	}

	
	/**
	 * * @author Ricky Yue
	 * 
	 * This method takes in the year of the weather data to be graphed.
	 * The method organizes the snow and rain fall amount per month
	 * for each year into table. Then, it adds the table on the panel
	 * beside the precipitation graph
	 * 
	 * @param year
	 */
	public void makeTable (int year){

		String[] columnNames = {"MONTH","RAINFALL(cm)", "SNOWFALL(cm)"};

		String row1 = String.valueOf(rain[year][1]);
		String row2 = String.valueOf(rain[year][2]);
		String row3 = String.valueOf(rain[year][3]);
		String row4 = String.valueOf(rain[year][4]);
		String row5 = String.valueOf(rain[year][5]);
		String row6 = String.valueOf(rain[year][6]);
		String row7 = String.valueOf(rain[year][7]);
		String row8 = String.valueOf(rain[year][8]);
		String row9 = String.valueOf(rain[year][9]);
		String row10 = String.valueOf(rain[year][10]);
		String row11 = String.valueOf(rain[year][11]);
		String row12 = String.valueOf(rain[year][12]);

		String rowb1 = String.valueOf(snow[year][1]);
		String rowb2 = String.valueOf(snow[year][2]);
		String rowb3 = String.valueOf(snow[year][3]);
		String rowb4 = String.valueOf(snow[year][4]);
		String rowb5 = String.valueOf(snow[year][5]);
		String rowb6 = String.valueOf(snow[year][6]);
		String rowb7 = String.valueOf(snow[year][7]);
		String rowb8 = String.valueOf(snow[year][8]);
		String rowb9 = String.valueOf(snow[year][9]);
		String rowb10 = String.valueOf(snow[year][10]);
		String rowb11= String.valueOf(snow[year][11]);
		String rowb12 = String.valueOf(snow[year][12]);

		String [][] data = new String [][] {{"MONTH", "RAINFALL(cm)", "SNOWFALL(cm)"},
			{"1",row1,rowb1 }, {"2",row2,rowb2 },{"3",row3,rowb3 },{"4",row4,rowb4 },
			{"5",row5,rowb5 },{"6",row6,rowb6}, {"7",row7,rowb7 },{"8",row8,rowb8 }, 
			{"9",row9,rowb9 },{"10",row10,rowb10 },{"11",row11,rowb11 },{"12",row12,rowb12 } };

			model = new DefaultTableModel (data, columnNames);

			if(dataTable!=null){
				remove(dataTable);
			}

			dataTable = new JTable (model);
			dataTable.setBounds(900, 225, 300, 210);
			add(dataTable);	
	}

	/**
	 * @author Ricky Yue
	 * 
	 * This method takes in the Graphics2D component. Then it uses to 
	 * outline the grids, axises, labels, and legends
	 * 
	 * @param g2
	 */
	
	public void makeGraphFrame (Graphics2D g2){

		g2.setStroke (new BasicStroke (3));
		g2.drawLine (ORIGIN_X, ORIGIN_Y, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y);
		g2.drawLine (100, ORIGIN_Y - Y_AXIS_LENGTH , 100, ORIGIN_Y);
		g2.setStroke (new BasicStroke (2));


		//creating the x axis intervals
		for  (int a = 1; a <= 12; a ++){
			g2.drawLine (ORIGIN_X +X_INTERVAL * a, ORIGIN_Y,ORIGIN_X + X_INTERVAL * a, ORIGIN_Y -5);
			g2.drawString(String.valueOf(a),ORIGIN_X +X_INTERVAL * a , ORIGIN_Y +20);
		}


		//Creating the y axis intervals
		for  (int a  = 0; a < 76; a ++){

			if (a%5 == 0){
				g2.drawLine(ORIGIN_X - 10, ORIGIN_Y - Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y - Y_INTERVAL * a );
				g2.drawString(String.valueOf(a * 4),ORIGIN_X -30 , ORIGIN_Y - Y_INTERVAL * a + 5);
			}
			else 
				g2.drawLine(ORIGIN_X - 5, ORIGIN_Y - Y_INTERVAL * a, ORIGIN_X, ORIGIN_Y - Y_INTERVAL * a );
		}



		//Drawing the dotted lines for pin pointing each point ----------------------------------------------------------------------------------------------------------------
		g2.setStroke(dottedLine(g2));
		g2.setColor(Color.LIGHT_GRAY);

		for (int a = 1; a <= 12; a ++){
			g2.drawLine(ORIGIN_X + X_INTERVAL * a, ORIGIN_Y - Y_AXIS_LENGTH, ORIGIN_X + X_INTERVAL * a, ORIGIN_Y);	
		}

		for (int a = 1; a < 76; a++){
			g2.drawLine(ORIGIN_X, ORIGIN_Y - a *Y_INTERVAL, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y -  a * Y_INTERVAL);
		}

		//Outlining the graph grid
		//Outlining the graph grid
		g2.setStroke((new BasicStroke (2)));
		g2.setColor(Color.BLACK);
		g2.drawLine(ORIGIN_X, ORIGIN_Y - Y_AXIS_LENGTH, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y - Y_AXIS_LENGTH);
		g2.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y);
		g2.drawLine(ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y -Y_AXIS_LENGTH, ORIGIN_X + X_AXIS_LENGTH, ORIGIN_Y);


		//Axis Label

		g2.setFont(new Font("Calibri", Font.BOLD, 20));
		g2.drawString("Month", 420, 690);
		g2.drawString("P", 40, ORIGIN_Y - 420);
		g2.drawString("r", 40, ORIGIN_Y - 400);
		g2.drawString("e", 40, ORIGIN_Y - 380);
		g2.drawString("c", 40, ORIGIN_Y - 360);
		g2.drawString("i", 40, ORIGIN_Y - 340);
		g2.drawString("p", 40, ORIGIN_Y - 320) ;
		g2.drawString("i", 40, ORIGIN_Y - 300);
		g2.drawString("t", 40, ORIGIN_Y - 280);
		g2.drawString("a", 40, ORIGIN_Y - 260);
		g2.drawString("t", 40, ORIGIN_Y - 240);
		g2.drawString("i", 40, ORIGIN_Y - 220);
		g2.drawString("o", 40, ORIGIN_Y - 200);
		g2.drawString("n", 40, ORIGIN_Y - 180);
		g2.drawString("(cm)", 40, ORIGIN_Y - 160);

		g2.setFont(new Font("Calibri", Font.ITALIC, 25));
		g2.drawString(getName () + " Precipitation Graph for Year: ", 250, 30);

		g2.setFont(new Font("Calibri", Font.BOLD, 20));
		g2.drawString("Graph Type: ", 900, 100);
		g2.drawString("Data Table" , 900, 200);


		//Graph legends
		g2.setColor(Color.RED);
		g2.fillOval(900, 500, 20, 20);
		g2.drawString("RAIN FALL",930, 520);

		g2.setColor(Color.BLUE);
		g2.fillOval(900, 570, 20, 20);
		g2.drawString("SNOW FALL",930, 590);

	}


	/**
	 * @author Ricky Yue 
	 * 
	 * This method takes in the Graphics2D component. Its sole 
	 * purpose is to convert solid lines into dotted/dashed lines 
	 * through changing the stroke type.
	 * 
	 * @param g2
	 * @return new BasicStroke created
	 */
	public Stroke dottedLine(Graphics2D g2) {
		final float dash[] = {3.0f};
		final BasicStroke dotted =
				new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		return dotted;	
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the rain
	 */
	public double[][] getRain() {
		return rain;
	}
	/**
	 * @param rain the rain to set
	 */
	public void setRain(double[][] rain) {
		this.rain = rain;
	}
	/**
	 * @return the snow
	 */
	public double[][] getSnow() {
		return snow;
	}
	/**
	 * @param snow the snow to set
	 */
	public void setSnow(double[][] snow) {
		this.snow = snow;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method checks if mouse is clicked on this panel.
	 * It is currently unused.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method checks if the cursor is on top of any 
	 * scatter plot points (labels) on this panel. Then it reveals 
	 * the information represented by each point in float window.
	 * 
	 */
	@Override
	public void mouseEntered(MouseEvent m) {
		String yearString = "" ; 
		String yearString2 = "";
		for(int i=0; i<=11;i++){
			if ( point[i] == m.getSource())
				yearString = String.valueOf(snow[selectedYearInt][i + 1 ]+ " cm");

			if (point2[i] == m.getSource())
				yearString2 = String.valueOf(rain[selectedYearInt][i + 1 ]+ " cm");

			point[i].setToolTipText(yearString);
			point2[i].setToolTipText(yearString2);
		}

	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * @author Ricky Yue
	 * 
	 * This method checks if the items inside the combo boxes are
	 * clicked, then triggers an action.
	 * 
	 */
	@Override
	public void itemStateChanged(ItemEvent i) {

		if (i.getStateChange() == ItemEvent.SELECTED){	
			selectedYear = (String) year.getSelectedItem();
			selectedYearInt = Integer.parseInt(selectedYear);

			for (int a = 0; a < 12; a++){
				remove (point[a]);
				remove (point2[a]);
			}

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

			for (int a = 0; a < 12; a ++){
				remove (point[a]);
				remove (point2[a]);
			}

			repaint();
		}

	}

}
