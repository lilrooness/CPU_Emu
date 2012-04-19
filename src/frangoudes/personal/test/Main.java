package frangoudes.personal.test;

import frangoudes.personal.cpu.Control;
import frangoudes.personal.cpu.IS;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] program = {IS.MOV_A+"12", IS.MOV_B_A, IS.MOV_A+"00", "F000"};
		Control c = new Control(program);
		c.run();
	}

}
