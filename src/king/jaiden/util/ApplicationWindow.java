package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

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
	
	public ApplicationWindow(){
		this(new IntCoord(DEFAULT_WIDTH,DEFAULT_HEIGHT));
	}
	
	public ApplicationWindow(IntCoord windowDimensions){
		this(windowDimensions,DEFAULT_FOV,"",false,TWO_DIMENSIONAL);
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
		
		init();
		
		renderLoop();
		
		Display.destroy();
	}
	
	private void setupDisplay(){
		try {
			DisplayMode displayMode = null;
	        if(!isFullscreen){
	        	System.out.println(windowDimensions.getX());
	        	displayMode = new DisplayMode((int)windowDimensions.getX(),(int)windowDimensions.getY());
	        	displayMode = new DisplayMode(DEFAULT_WIDTH,DEFAULT_HEIGHT);
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
			setup2DMatrix((int)windowDimensions.getX(),(int)windowDimensions.getY());
		}
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
	public abstract void input();
	public void tick(){
		currentTick++;
	}
	public void draw(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	public void setup3DMatrix(){
		glViewport(0,0,(int)windowDimensions.getX(),(int)windowDimensions.getY());
		// Setup the Matrix
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(100, (float)(windowDimensions.getX()/windowDimensions.getY()), zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	public void setup2DMatrix(int w, int h){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, w, 0, h, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	public void enableTests(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glEnable(GL_DEPTH_TEST);	//DEPTH_TEST
		glDepthFunc(GL_LESS);
		
		glEnable(GL_CULL_FACE);		//GL_CULL_FACE
	}
	public void pause(){
		isPaused=true;
	}
	public void unpause(){
		isPaused=false;
	}
}
