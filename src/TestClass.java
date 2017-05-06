import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

public class TestClass {
	static JFrame jf = new JFrame();
	public static void main(String [] args) throws IOException{
		
    	LoadingClass test = new LoadingClass();

    	jf.setBackground(Color.black);
    	jf.setSize(1200,900);
    	jf.setTitle("CCA");
    	jf.setVisible(true);
    	jf.add(test);

	}
}
