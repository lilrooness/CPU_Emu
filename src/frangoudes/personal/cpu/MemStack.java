package frangoudes.personal.cpu;

import java.util.EmptyStackException;
import java.util.Stack;

public class MemStack {
	private Stack<String> stack;
	
	/**
	 * constructs a stack
	 */
	public MemStack(){
		stack = new Stack<String>();
	}
	
	/**
	 * pushes a value to stack
	 * @param value
	 */
	public void push(String value){
		stack.push(value);
	}
	
	/**
	 * pops a value from stack and returns it, if the stack
	 * @return
	 */
	public String pop(){
		try{
			return stack.pop();
		}catch(EmptyStackException e){
			return "00";
		}
	}
}
