package frangoudes.personal.cpu;

public class MAR extends Register {
	
	BusSystem bus;
	
	/**
	 * Initializes the MAR with a 16 bit hex value
	 * @param value
	 * @param bus
	 */
	public MAR(String value, BusSystem bus){
		super(value);
		this.bus = bus;
	}
	
	/**
	 * Initializes the MAR with a 16 bit short decimal value
	 * @param value
	 * @param bus
	 */
	public MAR(short value, BusSystem bus){
		super(value);
		this.bus = bus;
	}
}
