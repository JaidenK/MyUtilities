package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

public abstract class ApplicationWindow {
	public static final int DEFAULT_WIDTH = 640,
							DEFAULT_HEIGHT = 480,
							DEFAULT_FOV = 70,
							TWO_DIMENSIONAL = 0,
							THREE_DIMENSIONAL = 1;
	
	protected IntCoord windowDimensions;
	protected int currentTick,
				  fov,
				  matrixMode;
	protected double guiScale,
					 xRot,
					 yRot,
					 xPan,
					 yPan,
					 zPan,
					 dst,
					 dstMod,
					 panMod;
	protected float	 zNear = 0.1f,
					 zFar = 10000;
	protected boolean isFullscreen,
					  isPaused;
	protected String windowTitle;
	private ArrayList<InterfaceItem> registeredMouseListeners;
	protected Controls controls;
	
	public ApplicationWindow(){
		this(new IntCoord(DEFAULT_WIDTH,DEFAULT_HEIGHT));
	}
	
	public ApplicationWindow(IntCoord windowDimensions){
		this(windowDimensions,DEFAULT_FOV,"Game",false,TWO_DIMENSIONAL);
	}
	
	public ApplicationWindow(IntCoord windowDimensions, int fov, String windowTitle, boolean isFullscreen, int matrixMode){
		this.windowDimensions = windowDimensions;
		this.fov = fov;
		this.windowTitle = windowTitle;
		this.isFullscreen = isFullscreen;
		this.matrixMode = matrixMode;
		currentTick = 0;
		guiScale = 1;
		isPaused = false;
		xRot = 0;
		yRot = 0;
		dst = 0;
		dstMod = 10;
		xPan = 0;
		yPan = 0;
		zPan = 0;
		panMod = 10;
		controls = new Controls();
		
		setupDisplay();

		setupMatrix();
		
		enableTests();
		
		initializeListeners();
		
		init();
		
		renderLoop();
		
		Display.destroy();
	}
	
	private void initializeListeners(){
		registeredMouseListeners = new ArrayList<InterfaceItem>();
	}
	
	private void setupDisplay(){
		try {
			DisplayMode displayMode = null;
	        if(!isFullscreen){
	        	displayMode = new DisplayMode((int)windowDimensions.getX(),(int)windowDimensions.getY());
	        }else{
		        DisplayMode[] modes = Display.getAvailableDisplayModes();
		        for (int i = 0; i < modes.length; i++)
		        {
		        	if (modes[i].getWidth() == (int)windowDimensions.getX()
		            && modes[i].getHeight() == (int)windowDimensions.getY()
		            && modes[i].isFullscreenCapable())
		        		{
		            	 	displayMode = modes[i];
		        		}
		        }
	        }
			Display.setDisplayMode(displayMode);
			Display.setFullscreen(isFullscreen);
			Display.create();
			Display.setTitle(windowTitle);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void setupMatrix(){
		if(matrixMode==THREE_DIMENSIONAL){
			setup3DMatrix();
		}else{
			setup2DMatrix();
		}
	}
	protected void setup2DMatrix(){
		DrawUtil.setup2DMatrix((int)windowDimensions.getX(),(int)windowDimensions.getY());
	}
	protected void setup3DMatrix(){
		DrawUtil.setup3DMatrix((int)windowDimensions.getX(),(int)windowDimensions.getY(),zNear,zFar);
	}
	private void renderLoop(){
		while (!Display.isCloseRequested()){
			input();
			tick();
			draw();
			
			Display.update();
			Display.sync(60);
		}
	}
	
	public abstract void init();
	public void input(){
		for(InterfaceItem interfaceItem : registeredMouseListeners){
			interfaceItem.testForMouseEvents();
		}
		if(Mouse.isButtonDown(controls.getRotateButton())){
			yRot += Mouse.getDX();
			xRot -= Mouse.getDY();
		}
		dst += Mouse.getDWheel()/dstMod;
		if(Keyboard.isKeyDown(controls.getPanLeft()))
			xPan+=1/panMod;
		if(Keyboard.isKeyDown(controls.getPanRight()))
			xPan-=1/panMod;
		if(Keyboard.isKeyDown(controls.getPanForward()))
			zPan+=1/panMod;
		if(Keyboard.isKeyDown(controls.getPanBackward()))
			zPan-=1/panMod;
		if(Keyboard.isKeyDown(controls.getPanUp()))
			yPan+=1/panMod;
		if(Keyboard.isKeyDown(controls.getPanDown()))
			yPan-=1/panMod;
	}
	public void tick(){
		currentTick++;
	}
	public void draw(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		glTranslated(xPan,yPan,zPan);
		glTranslated(0,0,dst);
		glRotated(xRot,1,0,0);
		glRotated(yRot,0,1,0);
	}
	public void enableTests(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		if(matrixMode==THREE_DIMENSIONAL){
			glEnable(GL_DEPTH_TEST);	//DEPTH_TEST
			glDepthFunc(GL_LESS);
		}else{
			glDisable(GL_DEPTH_TEST);
		}
		
		glEnable(GL_CULL_FACE);		//GL_CULL_FACE
	}
	public void pause(){
		isPaused=true;
	}
	public void unpause(){
		isPaused=false;
	}
	public void registerMouseListener(InterfaceItem item){
		registeredMouseListeners.add(item);
	}
}
