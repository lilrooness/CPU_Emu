package frangoudes.personal.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import frangoudes.personal.hardware.Screen;
import frangoudes.personal.hardware.ScreenDriver;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel implements Runnable{

	private ScreenDriver driver;
	private Thread loop;
	private int width;
	private int height;
	
	public ScreenPanel(int width, int height){
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		driver = new ScreenDriver();
		loop = new Thread(this);
		loop.start();
		this.setVisible(true);
	}
	
	@Override
	public void run() {
		while(true){
			driver.refresh();
			repaint();
		}
	}

	@Override
	public void paint(Graphics g){
		for(int i=0; i<driver.getScreenDim(); i++){
			for(int j=0; j<driver.getScreenDim(); j++){
				switch(driver.getScreen().getScreen()[i][j]){
				case Screen.WHITE:{
					g.setColor(Color.WHITE);
				};break;
				case Screen.RED:{
					g.setColor(Color.RED);
				};break;
				case Screen.GREEN:{
					g.setColor(Color.GREEN);
				};break;
				case Screen.BLUE:{
					g.setColor(Color.BLUE);
				};break;
				default:{g.setColor(Color.BLACK);}break;
				}
				g.fillRect(j*10, i*10, 10, 10);
			}
		}
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the driver
	 */
	public ScreenDriver getDriver() {
		return driver;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
}
