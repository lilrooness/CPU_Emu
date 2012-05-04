package frangoudes.personal.hardware;

import frangoudes.personal.cpu.BusSystem;
import frangoudes.personal.cpu.Memory;

public class ScreenDriver {
	private int[] screenBuffer;
	private Screen screen;
	private int screenDim;
	
	public ScreenDriver(){
		screenDim = 10;
		screenBuffer = new int[screenDim*screenDim];
		screen = new Screen(screenDim);
	}
	
	/**
	 * Copies the contents of the buffer to the screens array
	 */
	public void refresh(){
		screenBuffer = Memory.getInclusiveBuffer(0, 10);
		for(int i=0; i<screenBuffer.length; i++){
			for(int j=0; j<screenDim; j++){
				screen.getScreen()[i/screenDim][j] = screenBuffer[i]; 
			}
		}
	}

	/**
	 * @return the screenBuffer
	 */
	public int[] getScreenBuffer() {
		return screenBuffer;
	}

	/**
	 * @param screenBuffer the screenBuffer to set
	 */
	public void setScreenBuffer(int[] screenBuffer) {
		this.screenBuffer = screenBuffer;
	}

	/**
	 * @return the screen
	 */
	public Screen getScreen() {
		return screen;
	}

	/**
	 * @param screen the screen to set
	 */
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	/**
	 * @return the screenDim
	 */
	public int getScreenDim() {
		return screenDim;
	}
}
