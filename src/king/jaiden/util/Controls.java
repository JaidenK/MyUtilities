package king.jaiden.util;

import static org.lwjgl.input.Keyboard.*;

public class Controls {

	private int rotateButton = 0;
	private int panLeft = KEY_A;
	private int panRight = KEY_D;
	private int panForward = KEY_W;
	private int panUp = KEY_Q;
	private int panDown = KEY_E;
	public int getPanUp() {
		return panUp;
	}

	public void setPanUp(int panUp) {
		this.panUp = panUp;
	}

	public int getPanDown() {
		return panDown;
	}

	public void setPanDown(int panDown) {
		this.panDown = panDown;
	}

	public int getPanLeft() {
		return panLeft;
	}

	public void setPanLeft(int panLeft) {
		this.panLeft = panLeft;
	}

	public int getPanRight() {
		return panRight;
	}

	public void setPanRight(int panRight) {
		this.panRight = panRight;
	}

	public int getPanForward() {
		return panForward;
	}

	public void setPanForward(int panForward) {
		this.panForward = panForward;
	}

	public int getPanBackward() {
		return panBackward;
	}

	public void setPanBackward(int panBackward) {
		this.panBackward = panBackward;
	}

	private int panBackward = KEY_S;

	public int getRotateButton() {
		return rotateButton;
	}

	public void setRotateButton(int rotateButton) {
		this.rotateButton = rotateButton;
	}

}
