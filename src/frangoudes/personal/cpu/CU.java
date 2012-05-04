package frangoudes.personal.cpu;

public class CU {

	private BusSystem bus;
	private String opcode = "0000";
	private int counter = 0;
	
	public CU(String[] program){
		bus = new BusSystem();
		bus.programMemory.setProgram(program);
	}
	
	/**
	 * fetches the current opcode from memory
	 */
	public void fetch(){
		opcode = bus.programMemory.fetch(bus.pc.getD());
		bus.pc.increment();
	}
	
	/**
	 * executes the current opcode and then increments the PC by 1
	 */
	public void execute(){
		counter = bus.pc.getD();
		String function = opcode.substring(0, 2);
		String value = opcode.substring(2);
		System.out.println("function: "+function);
		System.out.println("Parameter: "+value);
		
		int opNum = Integer.parseInt(function, 16);
		
		switch(opNum){
		case 1:{this.op01(value);}break;
		case 2:{this.op02(value);}break;
		case 3:{this.op03();}break;
		case 4:{this.op04();}break;
		case 5:{this.op05(value);}break;
		case 6:{this.op06(value);}break;
		case 7:{this.op07();}break;
		case 8:{this.op08();}break;
		case 9:{this.op09(value);}break;
		case 10:{this.op0A(value);}break;
		case 11:{this.op0B(value);}break;
		case 12:{this.op0C();}break;
		case 13:{this.op0D();}break;
		case 14:{this.op0E(value);}break;
		case 15:{this.op0F(value);}break;
		case 16:{this.op10();}break;
		case 17:{this.op11();}break;
		case 18:{this.op12(value);}break;
		case 19:{this.op13(value);}break;
		case 20:{this.op14();}break;
		case 21:{this.op15();}break;
		case 22:{this.op16();}break;
		case 23:{this.op17();}break;
		case 24:{this.op18();}break;
		case 25:{this.op19();}break;
		case 26:{this.op1A();}break;
		case 27:{this.op1B();}break;
		}
	}

	/*------------------------------OPCODE DECLARATIONS--------------------------------------*/
	/*---------------------------------------------------------------------------------------*/
	/**
	 * opcode 01
	 * move value into A
	 * @param value
	 */
	public void op01(String value){
		mov(bus.A, value);
	}

	/**
	 * @return the bus
	 */
	public BusSystem getBus() {
		return bus;
	}

	/**
	 * opcode 02
	 * move value into B
	 * @param value
	 */
	public void op02(String value){
		mov(bus.B, value);
	}

	/**
	 * opcode 03
	 * move value in B into A
	 */
	public void op03(){
		mov(bus.A, bus.B);
	}

	/**
	 * opcode 04
	 * move value in A into B
	 */
	public void op04(){
		mov(bus.B, bus.A);
	}

	/**
	 * opcode 05
	 * move value in memory address defined by hex String into A
	 * @param val
	 */
	public void op05(String val){
		mov(bus.mar, val);
		bus.mdr.getAddress();
		bus.mdr.getData();
		mov(bus.A, bus.mdr);
	}

	/**
	 * opcode 06
	 * move value in memory address defined by hex String into B
	 * @param val
	 */
	public void op06(String val){
		mov(bus.mar, val);
		bus.mdr.getAddress();
		bus.mdr.getData();
		mov(bus.B, bus.mdr);
	}
	
	/**
	 * move value in A register to pc register
	 */
	public void op07(){
		mov(bus.pc, bus.A);
	}
	
	/**
	 * move value in B register to pc register
	 */
	public void op08(){
		mov(bus.pc, bus.B);
	}
	
	/**
	 * move hex value into pc register
	 * @param value
	 */
	public void op09(String value){
		mov(bus.pc, value);
	}
	
	/**
	 * move value in A register into memory address defined by hex String
	 * @param address
	 */
	public void op0A(String address){
		mov(bus.mar, address);
		mov(bus.mdr, bus.A);
		bus.mdr.putData();
	}
	
	/**
	 * move value in B register into memory address defined by hex String
	 * @param address
	 */
	public void op0B(String address){
		mov(bus.mar, address);
		mov(bus.mdr, bus.B);
		bus.mdr.putData();
	}
	
	/**
	 * Adds the value of A and B and stores result in A
	 */
	public void op0C(){
		short a = bus.A.getD();
		short b = bus.B.getD();
		mov(bus.A, (short)(a+b));
	}
	
	/**
	 * Adds the value of A and B and stores result in B
	 */
	public void op0D(){
		short a = bus.A.getD();
		short b = bus.B.getD();
		mov(bus.B, (short)(a+b));
	}
	
	/**
	 * Adds the value of A to parsed value and stores result in A
	 */
	public void op0E(String value){
		short a = bus.A.getD();
		short b = Short.parseShort(value, 16);
		mov(bus.A, (short)(a+b));
	}
	
	/**
	 * Adds the value of B to parsed value and stores result in B
	 * @param value
	 */
	public void op0F(String value){
		short a = bus.B.getD();
		short b = Short.parseShort(value, 16);
		mov(bus.B, (short)(a+b));
	}
	
	/**
	 * subtracts the value of B from A and stores result in A
	 */
	public void op10(){
		int a = bus.A.getD();
		int b = bus.B.getD();
		mov(bus.A, (short)(a-b));
	}
	
	/**
	 * subtracts the value of A from B and stores the result in B
	 */
	public void op11(){
		int a = bus.A.getD();
		int b = bus.B.getD();
		mov(bus.B, (short)(b-a));
	}
	
	/**
	 * Subtracts the hex value parsed from the value in A and stores result in A
	 * @param value
	 */
	public void op12(String value){
		short b = Short.parseShort(value, 16);
		short a = bus.A.getD();
		mov(bus.A,(short)(a-b));
	}
	
	/**
	 * Subtracts the hex value parsed from the value in B and stores result in B
	 * @param value
	 */
	public void op13(String value){
		short b = Short.parseShort(value, 16);
		short a = bus.B.getD();
		mov(bus.B,(short)(a-b));
	}
	
	/**
	 * Moves the value of the PC into the A register
	 */
	public void op14(){
		mov(bus.A, bus.pc);
	}
	
	/**
	 * Moves the value of the PC into the B register
	 */
	public void op15(){
		mov(bus.B, bus.pc);
	}
	
	/**
	 * pushes value of PC onto the stack
	 */
	public void op16(){
		bus.stack.push(bus.pc.getH());
	}
	
	/**
	 * pushes the value of the A register onto the stack
	 */
	public void op17(){
		bus.stack.push(bus.A.getH());
	}
	
	/**
	 * pushes the value of the B register onto the stack
	 */
	public void op18(){
		bus.stack.push(bus.B.getH());
	}
	
	/**
	 * pops value from stack into the PC
	 */
	public void op19(){
		mov(bus.pc, bus.stack.pop());
	}
	
	/**
	 * pops value from stack into the A register
	 */
	public void op1A(){
		mov(bus.A, bus.stack.pop());
	}
	
	/**
	 * pops value from stack into the B register
	 */
	public void op1B(){
		mov(bus.B, bus.stack.pop());
	}
	
	/*---------------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------------*/
	
	
	/*------------------------------BASIC OPERATIONS-----------------------------------------*/
	/*---------------------------------------------------------------------------------------*/
	/**
	 * Moves value from one register to the other
	 * @param dest
	 * @param source
	 */
	public void mov(Register dest, Register source){
		dest.mov(source.getD());
	}
	
	/**
	 * moves a decimal value to a register
	 * @param dest
	 * @param value
	 */
	public void mov(Register dest, short value){
		dest.mov(value);
	}
	
	/**
	 * moves a hex value to a register
	 * @param dest
	 * @param value
	 */
	public void mov(Register dest, String value){
		dest.mov(value);
	}
	
	/*---------------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------------*/	

	/**
	 * @return the counter
	 */
	public int getCounter(){
		return counter;
	}

	/**
	 * @return the opcode
	 */
	public String getOpcode() {
		return opcode;
	}
}
