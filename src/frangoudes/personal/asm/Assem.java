package frangoudes.personal.asm;

import frangoudes.personal.cpu.IS;

/**
 * converts an assembly language program 
 * into an array of hex opcodes
 * @author Joe
 *
 */
public class Assem {
	
	private String[] program;
	private int index;
	
	public Assem(String[] program){
		this.program = program;
		index = 0;
	}
	
	/**
	 * Returns an array of hex String opcodes
	 * for the program
	 * @return
	 */
	public String[] assembleAll(){
		String[] opcodes = new String[program.length];
		for(int i=0; i<program.length; i++){
			opcodes[i] = assembleLine();
		}
		return null;
	}
	
	/**
	 * returns a hex String opcode for the current line
	 * @return
	 */
	public String assembleLine(){
		String[] line;
		String opcode = null;
		line = program[index].split(" ");
		
		if(line.length == 3){
			if(line[0].equalsIgnoreCase("mov")){
				opcode = checkMovs(line);
			}
		}else{
			opcode = "F000";
		}
		
		index++;
		return opcode;
	}
	
	/**
	 * Compiles any MOV line statement
	 * @param line
	 * @return
	 */
	public String checkMovs(String[] line){
		String opcode = null;
		//MOV A *
		if(line[1].equalsIgnoreCase("a")){
			if(line[2].equalsIgnoreCase("b")){
				opcode = IS.MOV_A_B;
			}else if(line[2].startsWith("$")){
				opcode = IS.MOVI_A_$+line[2].substring(1);
			}else{
				opcode = IS.MOV_A+line[2];
			}
		//MOV B *
		}else if(line[1].equalsIgnoreCase("b")){
			if(line[2].equalsIgnoreCase("a")){
				opcode = IS.MOV_B_A;
			}else if(line[2].startsWith("$")){
				opcode = IS.MOVI_B_$+line[2].substring(1);
			}else{
				opcode = IS.MOV_B+line[2];
			}
		//MOV PC *
		}else if(line[1].equalsIgnoreCase("pc")){
			if(line[2].equalsIgnoreCase("a")){
				opcode = IS.MOV_PC_A;
			}else if(line[2].equalsIgnoreCase("b")){
				opcode = IS.MOV_PC_B;
			}else{
				opcode = IS.MOV_PC+line[2];
			}
		//MOV $LOCATION *
		}else if(line[1].startsWith("$")){
			if(line[2].equalsIgnoreCase("a")){
				opcode = IS.MOVO_A_$+line[1].substring(1);
			}else if(line[2].equalsIgnoreCase("b")){
				opcode = IS.MOVO_B_$+line[1].substring(1);
			}
		}else{
			opcode = "FFFF";
		}
		return opcode;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}
