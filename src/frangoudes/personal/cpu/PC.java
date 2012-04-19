package frangoudes.personal.cpu;

public class PC extends Register {
	
	public PC(String value){
		super(value);
	}
	
	public PC(short value){
		super(value);
	}
	
	/**
	 * increments the value held in the PC register
	 */
	public void increment(){
		this.mov((short)(this.getD()+1));
	}
}
