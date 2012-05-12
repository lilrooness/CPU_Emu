package frangoudes.personal.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ScreenWindow extends JFrame{
	private JButton displayBuffer;
	private ScreenPanel panel;
	
	public ScreenWindow(int width, int height){
		super("Screen IO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(width + 10, height + 10);
		this.setSize(400, 400);
		this.setLayout(new FlowLayout());
		panel = new ScreenPanel(width, height);
		displayBuffer = new JButton("PrintBuffer");
		this.add(panel);
		displayBuffer.addActionListener(new Handler());
		this.setVisible(true);
	}
	
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == displayBuffer){
				for(int i=0; i<=panel.getDriver().getScreenDim(); i++){
					for(int j=0; j<=panel.getDriver().getScreenDim(); j++){
						System.out.print(panel.getDriver().getScreen().getPixel(j, i));
					}
					System.out.println("");
				}
			}
		}
	}
}
