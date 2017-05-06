import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Ricky Yue
 * 
 * This class creates a panel that holds the main interface. It contains
 * a map with required cities labeled. Users can clicked on them to 
 * see more detailed weather information regarding a specific city.
 *
 */

public class MapPanel extends JPanel implements MouseListener{


	ImageIcon map = new ImageIcon ("map.png");
	JLabel mapImage = new JLabel ();

	public static String city;
	public static String type;

	JLabel toronto = new JLabel();
	JLabel edmonton = new JLabel();
	JLabel whitehorse = new JLabel();
	JLabel newFoundLand = new JLabel();
	JLabel iqaluit = new JLabel();
	JLabel victoria = new JLabel();
	JLabel regina = new JLabel();
	JLabel halifax = new JLabel();

	static JFrame jf = new JFrame();    	

	public final int rainOption = 13;
	public final int snowOption = 15;
	public final int tempOption = 7;
	public final int windSpdOption = 23 ;

	public  static double [][] dataSet ;
	public  static double [][] dataSet2 ; //to differentiate rain and snow

	ImageIcon pointImage = new ImageIcon ("132.png");
	
	

	/**
	 * @author Ricky Yue
	 * 
	 * This constructor method that reads in a map and paint it onto the panel.
	 * It locates each label on the map based on the geographic location of 
	 * each city.
	 */
	public MapPanel(){


		jf.setBackground(Color.black);
		jf.setSize(1200,900);
		jf.setTitle("CCA");

		setLayout(null);

		setSize (1200, 900);

		//Resizing the image 
		Image reSizedMap = map.getImage().getScaledInstance (1200, 700, java.awt.Image.SCALE_SMOOTH);
		pointImage = new ImageIcon (pointImage.getImage().getScaledInstance (50, 80, java.awt.Image.SCALE_SMOOTH));

		toronto = new JLabel(pointImage);
		edmonton = new JLabel(pointImage);
		whitehorse = new JLabel(pointImage);
		newFoundLand = new JLabel(pointImage);
		iqaluit = new JLabel(pointImage);
		victoria = new JLabel(pointImage);
		regina = new JLabel(pointImage);
		halifax = new JLabel(pointImage);


		map = new ImageIcon (reSizedMap);
		mapImage = new JLabel (map);


		add(toronto);
		add(edmonton);
		add(whitehorse);
		add(newFoundLand);
		add(iqaluit);
		add(victoria);
		add(regina);
		add(halifax);
		//Buttons

		//toronto.addActionListener(this);

		mapImage.setBounds(0, 0, 1200, 700);

		edmonton.setBounds(225,350,50,80);
		toronto.setBounds(770, 620 ,50,80);
		whitehorse.setBounds(100,100,50,80);
		newFoundLand.setBounds(975, 310, 50, 80);
		iqaluit.setBounds(450, 125, 50, 80);
		victoria.setBounds(100, 350, 50, 80);
		regina.setBounds(350, 450, 50, 80);
		halifax.setBounds(1050,480 , 50, 80);
		/////////////////////////////////////////////////
		toronto.addMouseListener(this);
		edmonton.addMouseListener(this);
		iqaluit.addMouseListener(this);
		regina.addMouseListener(this);
		newFoundLand.addMouseListener(this);
		halifax.addMouseListener(this);
		victoria.addMouseListener(this);
		whitehorse.addMouseListener(this);

		mapImage.setLayout (null);
		add(mapImage);

		setVisible(true);	

	}
	//
	public static double[][] readData(String fileName, int option ){
		double[][] tempData = new double[5000][20];

		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			while (true) {
				String line = reader.readLine();
				if (line == null) break;

				String[] fields = line.split(",");

				if (fields[option].length() >= 3)
					tempData[Integer.parseInt(fields[1].substring(1, fields[1].length() - 1))]
							[Integer.parseInt(fields[2].substring(1, fields[2].length() - 1))] =
							Double.parseDouble(fields[option].substring(1, fields[option].length() - 1));
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}

		return tempData;
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method checks if any labeled is clicked 
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent m) {
		if (m.getSource() == toronto ){
			dataSet = readData ("doc/Toronto.csv", tempOption);		
			Animation animation = new Animation();

			city = "Toronto";
			type = "temperature";

			jf.add(animation);			    	
			jf.setVisible(true);

		}

		else if (m.getSource() == edmonton){
			
			dataSet = readData ("doc/edmonton.csv", snowOption);
			dataSet2 = readData ("doc/edmonton.csv", rainOption);
			
			Animation animation = new Animation();
			city = "Edmonton";
			type = "precipitation";
			jf.add(animation);			    	
			jf.setVisible(true);

		}

		else if (m.getSource() == halifax){
			dataSet = readData ("doc/halifax.csv", windSpdOption);		
			Animation animation = new Animation();
			city = "Halifax";
			type = "windspeed";
			jf.add(animation);
			jf.setVisible(true);

		}
		else if (m.getSource() == iqaluit){
			
			dataSet = readData ("doc/iqaluit.csv", tempOption);		
			Animation animation = new Animation();
			city = "Iqaluit";
			type = "temperature";
			jf.add(animation);
			jf.setVisible(true);


		}
		else if (m.getSource() == regina){
			dataSet = readData ("doc/regina.csv", windSpdOption);		
			Animation animation = new Animation();
			city = "Regina";
			type = "windspeed";
			jf.add(animation);
			jf.setVisible(true);
		}
		else if (m.getSource() == newFoundLand){
			dataSet = readData ("doc/St-Johns.csv", tempOption);		
			Animation animation = new Animation();
			city = "St. John's";
			type = "temperature";
			jf.add(animation);
			jf.setVisible(true);

		}
		else if (m.getSource() == victoria){

			dataSet = readData ("doc/victoria.csv", tempOption);		
			Animation animation = new Animation();
			city = "Victoria";
			type = "temperature";
			jf.add(animation);
			jf.setVisible(true);

		}
		else if (m.getSource() == whitehorse){
			
			dataSet = readData ("doc/whiteHorse.csv", snowOption);
			dataSet2 = readData ("doc/whiteHorse.csv", rainOption);
			Animation animation = new Animation();
			city = "Whitehorse";
			type = "precipitation";
			jf.add(animation);
			jf.setVisible(true);
		}

		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 
	 * @author Ricky Yue
	 * 
	 * This method checks if the cursor is on top of any label. 
	 * If the cursor is on one of the labels, the method will 
	 * create a float window displaying the name of the corresponding city.
	 */
	@Override
	public void mouseEntered(MouseEvent m) {
		if (m.getSource() == toronto ){
			toronto.setToolTipText("Toronto Monthly Temeprature");
		}

		else if (m.getSource() == edmonton){
			edmonton.setToolTipText("Edmonton Monthly Precipitation");
		}
		else if (m.getSource() == halifax){
			halifax.setToolTipText("Halifax Monthly  Max Wind Speed");
		}
		else if (m.getSource() == iqaluit){
			
			iqaluit.setToolTipText("Iqaluit Monthly Temeprature");
		}
		else if (m.getSource() == regina){
			regina.setToolTipText("Regina Monthly  Max Wind Speed");
		}
		else if (m.getSource() == newFoundLand){
			newFoundLand.setToolTipText("NewFoundLand Monthly Temeprature");
		}
		else if (m.getSource() == victoria){
			victoria.setToolTipText("Victoria Monthly Temeprature");
		}
		else if (m.getSource() == whitehorse){
			whitehorse.setToolTipText("Whitehorse Monthly Precipitation");
		}
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author Ricky Yue
	 * 
	 * This method is currently not used.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
