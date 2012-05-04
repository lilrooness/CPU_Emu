package frangoudes.personal.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ScreenWindow extends JFrame{
	
	public ScreenWindow(int width, int height){
		this.setSize(width + 10, height + 10);
		this.add(new ScreenPanel(width, height));
		this.setVisible(true);
	}
}
