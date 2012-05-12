package frangoudes.personal.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frangoudes.personal.cpu.Control;

/**
 * Controls the GUI and interfaces with the CPU back end
 * @author Joe
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable{
	
	private JTextField a;
	private JTextField b;
	private JTextField pc;
	private JTextField opc;
	private JTextArea code;
	private JButton run;
	private JButton step;
	private JButton stop;
	private JButton load;
	private JButton file;
	private GridBagConstraints c;
	private Thread loop;
	private boolean stopped = true;
	private int top = 1;
	
	
	String[] program;
	Control cpu;
	
	/**
	 * Initializes all components and component positions
	 * @param width
	 * @param height
	 */
	public Window(int width, int height){
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("16 Bit CPU");
		loop = new Thread(this);
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.gridx =1;
		c.gridy =0;
		c.weightx = 1;
		opc = new JTextField(10);
		opc.setEditable(false);
		this.add(opc, c);
		c.gridx = 0;
		this.add(new JLabel("OPC:"), c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		pc = new JTextField(10);
		pc.setEditable(false);
		this.add(pc, c);
		c.gridx = 2;
		this.add(new JLabel("PC:"), c);
		
		c.gridx = 4;
		c.gridy = 2;
		c.weightx = 1;
		load = new JButton("load");
		load.addActionListener(new Handler());
		this.add(load, c);
		
		c.gridx = 1;
		c.gridy = 0+top;
		c.weightx = 1;
		c.gridwidth = 1;
		a = new JTextField(10);
		a.setEditable(false);
		this.add(a, c);
		c.gridx = 0;
		this.add(new JLabel("A Reg:"), c);
		
		c.gridx = 3;
		c.gridy = 0+top;
		c.weightx = 1;
		b = new JTextField(10);
		b.setEditable(false);
		this.add(b, c);
		c.gridx = 2;
		this.add(new JLabel("B Reg:"), c);
		
		c.gridx = 0;
		c.gridy = 1+top;
		c.weightx = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		run = new JButton("Run");
		run.addActionListener(new Handler());
		this.add(run, c);
		
		c.gridx = 2;
		c.gridy = 1+top;
		c.weightx = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		stop = new JButton("Stop");
		stop.addActionListener(new Handler());
		this.add(stop, c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.VERTICAL;
		step = new JButton("Step");
		step.addActionListener(new Handler());
		this.add(step, c);
		
		c.gridx = 0;
		c.gridy = top+3;
		c.weightx = 1;
		c.gridheight = 2;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		code = new JTextArea(10, 10);
		JScrollPane sp = new JScrollPane(code);
		this.add(sp, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		file = new JButton("From File");
		file.addActionListener(new Handler());
		this.add(file, c);
		
		loop.start();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Runs the program opcodes in a loop until end of program or if the user
	 * presses the "Stop" button
	 */
	@Override
	public void run() {
		while(true){
			try{
				while((!cpu.isHalted()) && (!stopped)){
					cpu.step();
					this.a.setText(cpu.getCpu().getBus().A.getH());
					this.b.setText(cpu.getCpu().getBus().B.getH());
					this.pc.setText(cpu.getCpu().getBus().pc.getH());
					this.opc.setText(cpu.getCpu().getOpcode());
				}
			}catch(NullPointerException e){
				stopped = true;
			}
		}
	}
	
	/**
	 * Runs next opcode in the program
	 */
	public void step(){
		try{
			cpu.step();
			this.a.setText(cpu.getCpu().getBus().A.getH());
			this.b.setText(cpu.getCpu().getBus().B.getH());
			this.pc.setText(cpu.getCpu().getBus().pc.getH());
			this.opc.setText(cpu.getCpu().getOpcode());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "No Program Loaded");
		}
		
	}
	
	/**
	 * gets the opcodes from the TextArea and
	 * converts them into an array of opcodes
	 * @return
	 */
	public String[] makeProgram(){
		String[] program = new String[code.getLineCount()];
		String line = code.getText();
		program = line.split("\n");
		
		for(int i=0; i<program.length; i++){
			program[i].trim();
		}
		
		return program;
	}
	
	/**
	 * reads in opcodes from a file into a ArrayList and then returns it
	 * @param filename
	 * @return
	 */
	public ArrayList<String> loadProgram(String filename){
		ArrayList<String> opcodes = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
			String code;
			while((code = in.readLine())!=null){
				opcodes.add(code);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not locate file: "+filename);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Trouble reading file: "+filename);
		}
		
		return opcodes;
	}
	
	/**
	 * Handles all events triggered by button presses
	 * @author Joe
	 *
	 */
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == run){
				stopped = false;
				code.setEditable(false);
				code.setBackground(new Color(0.5f, 0.5f, 1));
			}else if(e.getSource() == stop){
				stopped = true;
				code.setEditable(true);
				code.setBackground(Color.WHITE);
			}else if(e.getSource() == step){
				if(!stopped){
					JOptionPane.showMessageDialog(null, "Cannot step while running");
				}else{
					step();
				}
			}else if(e.getSource() == load){
				cpu = new Control(makeProgram());
			}else if(e.getSource() == file){
				JFileChooser f= new JFileChooser();
				int value =f.showOpenDialog(null);
				if(value == JFileChooser.APPROVE_OPTION){
					ArrayList<String> opcodes = loadProgram(f.getSelectedFile().getAbsolutePath());
					for(int i=0; i<opcodes.size(); i++){
						code.append(opcodes.get(i)+"\n");
					}
				}
				
			}
		}
		
	}
}
