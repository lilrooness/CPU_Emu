package frangoudes.personal.cpu;

public class MDR extends Register {
	
	BusSystem bus;
	
	/**
	 * Initializes the MDR with a 16 bit hex value
	 * @param value
	 * @param bus
	 */
	public MDR(String value, BusSystem bus){
		super(value);
		this.bus = bus;
	}
	
	/**
	 * initializes the MAR with a 16 bit short decimal value
	 * @param value
	 * @param bus
	 */
	public MDR(short value, BusSystem bus){
		super(value);
		this.bus = bus;
	}
	
	/**
	 * Gets the address stored in the MAR
	 */
	public void getAddress(){
		this.mov(bus.mar.getH());
	}
	
	/**
	 * Gets Data from memory at address
	 */
	public void getData(){
		this.mov(Memory.getH(value));
	}
	
	/**
	 * Puts Data into memory at address
	 */
	public void putData(){
		Memory.put(bus.mar.getD(), value);
	}
}
