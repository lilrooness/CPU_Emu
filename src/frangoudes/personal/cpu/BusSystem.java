package frangoudes.personal.cpu;

public class BusSystem {
	
	public MDR mdr;
	public MAR mar;
	public Memory memory;
	public PC pc;
	public Register A;
	public Register B;
	public ALU alu;

	/**
	 * Initializes the bus system
	 */
	public BusSystem(){
		mar = new MAR((short)0, this);
		mdr = new MDR((short)0, this);
		memory = new Memory(1024);
		pc = new PC((short)0);
		A = new Register((short)0);
		B = new Register((short)0);
	}
}