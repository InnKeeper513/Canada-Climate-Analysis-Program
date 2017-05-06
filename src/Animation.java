import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Timer;


/**
 * 
 * @author Jerry Tang
 * 
 * This is class creates an loading animation before the program starts
 * and creates an animation when a city label is clicked, transitioning 
 * to the graph panels
 * 
 */

public class Animation extends JPanel implements Runnable, ActionListener{

	private ImageIcon gif;
	private BufferedImage currentImage;
	Timer time = new Timer(2000,this);
	private Thread t;

	/**
	 * @author Jerry 
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
		
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public Animation(){
		time.start();
		
		gif  = new ImageIcon("image//1.gif");
		currentImage = toBufferedImage(toBufferedImage(gif.getImage()).getScaledInstance(1200, 900, Image.SCALE_DEFAULT));

		setSize(1200, 900);

		setVisible(true);

		running = true;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {

		while(running){
			currentImage =  toBufferedImage(toBufferedImage(gif.getImage()).getScaledInstance(1200, 900, Image.SCALE_DEFAULT));
			repaint();
			try {
				t.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void actionPerformed(ActionEvent e) {
		time.stop();
		t.stop();
		
		MapPanel mp = new MapPanel();
		mp.jf.dispose();        
		
		if(mp.type == "temperature"){
		 CityAvTemp temp = new CityAvTemp (mp.city, mp.dataSet);
		 new TGraphFrame (temp.getName(), temp.getMonthlyTemp());
		}
		if(mp.type == "precipitation"){
			CityPrecipitation cp = new CityPrecipitation(mp.city,mp.dataSet,mp.dataSet2);
			new PGraphFrame(cp.getName(),cp.getSnow(),cp.getRain());
			}
		if(mp.type == "windspeed"){
			CityAvWindSpd windspeed = new CityAvWindSpd (mp.city, mp.dataSet);
			new WGraphFrame (windspeed.getName(), windspeed.getMonthlyWindSpd());
		}
	}
	private boolean running = false;

	public void paintComponent(Graphics g){
		//super.paintComponents(g);
		g.drawImage(currentImage,0,0,null);
	}


}
		