import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 * @author Ricky Yue
 * 
 * 
 *
 */

public class MapFrame extends JFrame implements ActionListener {
	
	MapPanel panel = new MapPanel ();


	public MapFrame () throws IOException{

		
		setTitle ("Canada Climate Analysis");
		setSize (1200, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		add(panel);		
		
		
		setVisible(true);	
	}

	private void add(Scanner map2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
