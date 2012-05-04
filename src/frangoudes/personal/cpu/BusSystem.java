package frangoudes.personal.cpu;

import frangoudes.personal.hardware.ScreenDriver;

public class BusSystem {
	
	public MDR mdr;
	public MAR mar;
	public static Memory memory;
	public Memory programMemory;
	public PC pc;
	public Register A;
	public Register B;
	public ALU alu;
	public MemStack stack;
	public ScreenDriver sDrive;
	
	public static int T =0;

	/**
	 * Initializes the bus system
	 */
	public BusSystem(){
		mar = new MAR((short)0, this);
		mdr = new MDR((short)0, this);
		pc = new PC((short)0);
		A = new Register((short)0);
		B = new Register((short)0);
		stack = new MemStack();
		programMemory = new Memory();
		Memory.setMemSize((short)1024);
	}
}