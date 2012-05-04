package frangoudes.personal.hardware;

public class Screen {
	private int[][] screen;
	public final static byte WHITE = 1;
	public final static byte RED = 2;
	public final static byte GREEN = 3;
	public final static byte BLUE = 4;
	
	public Screen(int dim){
		screen = new int[dim][dim];
	}
	
	/**
	 * puts a "pixel" on the screen at location x,y
	 * @param x
	 * @param y
	 * @param col
	 */
	public void putPixel(int x, int y, int col){
		try{
			screen[y][x] = col;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e.toString());
		}
	}
	
	/**
	 * gets the value of a "pixel" on the screen at location x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPixel(int x, int y){
		try{
			return screen[y][x];
		}catch(ArrayIndexOutOfBoundsException e){
			return 0;
		}
	}

	/**
	 * @return the screen
	 */
	public int[][] getScreen() {
		return screen;
	}

	/**
	 * @param screen the screen to set
	 */
	public void setScreen(int[][] screen) {
		this.screen = screen;
	}
	
}
