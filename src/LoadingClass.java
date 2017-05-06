import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;


public class LoadingClass extends JPanel implements ActionListener
{
	Timer tm = new Timer(100,this);
	JLabel loading = new JLabel();
	JLabel text = new JLabel();
	JLabel start = new JLabel();
	
	ImageIcon pointImage = new ImageIcon ("StartButton.png");
	
	int x = 100;
	int movex = 10;
	int number = 0;
	int count = 0;

	public LoadingClass(){
		setLayout(null);
		tm.start();
		loading = new JLabel();
		text = new JLabel();
		//setLayout(null);

		loading.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 50));
		pointImage = new ImageIcon (pointImage.getImage().getScaledInstance (200,200, java.awt.Image.SCALE_SMOOTH));

		start = new JLabel(pointImage);
		
		start.setBounds(450,300,200,200);
		
	
		
		add(loading);        
		add(text);
		
		setVisible(true);
		
		start.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
					try {
						
						TestClass tc = new TestClass();
						MapFrame mf = new MapFrame();
						tc.jf.dispose();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
	}

	public void paintComponent(Graphics g){

		super.paintComponents(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, 1200, 900);
		
		g.setColor(Color.black);
		g.fillOval(350,200,300,300);
		
		g.setColor(Color.black);
		g.fillOval(350,200,250,150);
		
		
		g.setColor(Color.green);
		g.fillOval(350,200,400,400);
	
		g.setColor(Color.black);
		g.fillOval(400, 250, 300, 300);
		
		g.setColor(Color.WHITE);
		g.fillArc(400, 250, 300, 300, 0, x);
			
		g.setColor(Color.green);
		g.fillOval(425,275,250,250);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		count++;

		text.setText("Testing");
		loading.setText("Loading " + number + " %");
		number+=2;
		
		if (number == 102)
		{tm.stop();
		x = 360;
			
			add(start);}
		
		loading.setBounds(800,700,500,200);
			x += 5;


		repaint();
	}

}