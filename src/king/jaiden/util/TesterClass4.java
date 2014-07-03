package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

public class TesterClass4 extends ApplicationWindow {
	VoxelModel model;
	public TesterClass4(IntCoord windowDimensions, int fov, String windowTitle, boolean isFullscreen, int matrixMode){
		super(windowDimensions,fov,windowTitle,isFullscreen,matrixMode);
	}	
	public void init() {
		// TODO Auto-generated method stub
		model = new VoxelModel(new Sprite("res/images/voxelEarth.png",new IntCoord(8,8),64,Sprite.HORIZONTAL));
		glClearColor(1, 1, 1, 1);
		dst = -50;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TesterClass4(new IntCoord(1280,720),70,"Joe clone",false,ApplicationWindow.THREE_DIMENSIONAL);
	}
	
	public void draw(){
		super.draw();
		glRotated(45,1,1,0);
		glRotated(currentTick,0,0,1);
		glTranslated(-8,8,-8);
		model.draw();
	}

}
