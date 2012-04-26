package frangoudes.personal.test;

import frangoudes.personal.cpu.Control;
import frangoudes.personal.asm.Assem;
import frangoudes.personal.cpu.IS;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//String[] program = {IS.MOV_A+"12", IS.MOV_B_A, IS.MOV_A+"00", "F000"};
		String[] assem = {"mov a $10", "mov b a"};
		Assem as = new Assem(assem);
		
		for(int i=0; i<assem.length; i++){
			System.out.println(as.assembleLine());
		}
		//Control c = new Control(program);
		//c.run();
	}

}
