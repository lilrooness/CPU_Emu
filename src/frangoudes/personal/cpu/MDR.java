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
	
	public void getAddress(){
		this.mov(bus.mar.getH());
	}
	
	public void getData(){
		this.mov(BusSystem.memory.getH(value));
	}
	
	public void putData(){
		BusSystem.memory.put(bus.mar.getD(), value);
	}
}
