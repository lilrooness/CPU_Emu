package frangoudes.personal.cpu;

public class Memory {
	
	private static short[] array = new short[1024];
	private String[] program;

	
	/**
	 * returns the value (as a hex String) at 
	 * the memory address defined by the hex String address
	 * @param address
	 * @return
	 */
	public static String getH(String address){
		int index = Integer.parseInt(address, 16);
		return Integer.toHexString(array[index]);
	}
	
	/**
	 * returns the value (as a hex String) at
	 * the memory address defined by the integer value
	 * @param address
	 * @return
	 */
	public static String getH(int address){
		return Integer.toHexString(array[address]);
	}
	
	/**
	 * returns the decimal value at the
	 * memory address defined by the hex String
	 * @param address
	 * @return
	 */
	public static short getD(String address){
		int index = Integer.parseInt(address, 16);
		return array[index];
	}
	
	/**
	 * returns the decimal value at the
	 * memory address defined by the Integer value
	 * @param address
	 * @return
	 */
	public static short getD(int address){
		return array[address];
	}
	
	public static void put(int address, short value){
		array[address] = value;
	}
	
	public static void put(String address, String value){
		array[Integer.parseInt(address, 16)] = Short.parseShort(value, 16);
	}
	
	public static void put(String address, short value){
		array[Integer.parseInt(address, 16)] = value;
	}
	
	public static void put(int address, String value){
		array[address] = Short.parseShort(value, 16);
	}
	
	public String fetch(int counter){
		return program[counter];
	}

	/**
	 * @return the program
	 */
	public String[] getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(String[] program) {
		this.program = program;
	}
	
	public static void setMemSize(short size){
		array = new short[size];
	}
	
	/**
	 * returns a section of memory between two decimal 
	 * values (useful for buffering IO to hardware)
	 * @param from
	 * @param to
	 * @return
	 */
	public static int[] getInclusiveBuffer(int from, int to){
		int[] buffer = new int[(to - from)];
		for(int i=from; i<to; i++){
			buffer[i-from] = array[i];
		}
		return buffer;
	}
	
}
