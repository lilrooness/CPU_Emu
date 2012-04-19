package frangoudes.personal.cpu;

import javax.swing.JOptionPane;

public class Control {
	
	private CU cpu;
	private String[] program;
	private boolean isHalted = false;
	
	public Control(String[] program){
		this.program = program;
		cpu = new CU(program);
	}
	
			
	public void run(){
		while(!cpu.getOpcode().equalsIgnoreCase("F000") || cpu.getCounter() < program.length){
			cpu.fetch();
			cpu.execute();
		}
		isHalted = true;
	}
	
	public void step(){

		if(!cpu.getOpcode().equalsIgnoreCase("F000") || cpu.getCounter() < program.length){
			try{
				cpu.fetch();
				cpu.execute();
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Reached end of program without a halt command \"F000\"");
				isHalted = true;
			}
		}else{
			isHalted = true;
		}
	}


	/**
	 * @return the isHalted
	 */
	public boolean isHalted() {
		return isHalted;
	}


	/**
	 * @return the cpu
	 */
	public CU getCpu() {
		return cpu;
	}
}