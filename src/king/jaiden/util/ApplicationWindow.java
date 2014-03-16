package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
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
	protected double guiScale;
	protected float	 zNear = 0.1f,
					 zFar = 10000;
	protected boolean isFullscreen,
					  isPaused;
	protected String windowTitle;
	private ArrayList<InterfaceItem> registeredMouseListeners;
	
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
	}
	public void tick(){
		currentTick++;
	}
	public void draw(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
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
