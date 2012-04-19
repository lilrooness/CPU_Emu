package frangoudes.personal.cpu;

public class Register {
	short value;
	
	/**
	 * Initializes the register with a 16 bit hex string
	 * @param hex
	 */
	public Register(String value){
		this.value = Short.parseShort(value, 16);
	}
	
	/**
	 * Initializes the register with a 16 bit short decimal value
	 * @param value
	 */
	public Register(short value){
		this.value = value;
	}
	
	/**
	 * moves a 16 bit hex String into the register
	 * @param val
	 */
	public void mov(String val){
		value = Short.parseShort(val, 16);
	}
	
	/**
	 * moves a 16 bit short decimal value into the register
	 * @param value
	 */
	public void mov(short value){
		this.value = value;
	}
	
	/**
	 * returns a 16 bit hex String value of the register
	 * @return
	 */
	public String getH(){
		return Integer.toHexString(value);
	}
	
	/**
	 * returns a 16 bit short decimal value of the register
	 * @return
	 */
	public short getD(){
		return value;
	}
}
