package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.input.Keyboard.*;

public class TesterClass3 extends ApplicationWindow {
	private Color[] colors;
	private Coord3D[] cubes;
	private int c;
	private boolean lines;
	public TesterClass3(IntCoord intCoord, int i, String string, boolean b,
			int threeDimensional) {
		super(intCoord,i,string,b,threeDimensional);
	}
	@Override
	public void init() {
		colors = new Color[8];
		colors[0] = Color.RED;
		colors[1] = Color.ORANGE;
		colors[2] = Color.YELLOW;
		colors[3] = Color.GREEN;
		colors[4] = Color.CYAN;
		colors[5] = Color.BLUE;
		colors[6] = Color.VIOLET;
		colors[7] = Color.WHITE;
		TextUtil.getInstance().setAlignment(TextUtil.RIGHT);
		cubes = new Coord3D[6];
		cubes[0] = new Coord3D();
		cubes[1] = new Coord3D();
		cubes[2] = new Coord3D();
		cubes[3] = new Coord3D();
		cubes[4] = new Coord3D();
		cubes[5] = new Coord3D();
		c = 60;
		lines = false;
	}
	public void tick(){
		super.tick();
		cubes[1].setX(Math.cos(Math.toRadians(currentTick*c))*10);
		cubes[1].setY(Math.sin(Math.toRadians(currentTick*c))*10);
		
		cubes[2].setX(Math.cos(Math.toRadians(currentTick*c+57))*10);
		cubes[2].setZ(Math.sin(Math.toRadians(currentTick*c+57))*10);

		cubes[3].setY(Math.cos(Math.toRadians(currentTick*c+30))*10);
		cubes[3].setZ(Math.sin(Math.toRadians(currentTick*c+30))*10);
		
		cubes[4].setX(Math.cos(Math.toRadians(currentTick*c+30))*10);
		cubes[4].setY(Math.sin(Math.toRadians(currentTick*c+30))*10);
		cubes[4].setZ(Math.sin(Math.toRadians(currentTick*c+30))*10);
		
		cubes[5].setY(Math.cos(Math.toRadians(currentTick*c+30))*10);
		cubes[5].setZ(Math.sin(Math.toRadians(currentTick*c+30))*10);
		cubes[5].setX(Math.sin(Math.toRadians(currentTick*c+30))*10);
	}
	public void input(){
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(KEY_UP))
				c += 2;
			else if(Keyboard.isKeyDown(KEY_DOWN))
				c -= 2;
			if(Keyboard.isKeyDown(KEY_SPACE))
				if(lines)
					lines = false;
				else
					lines = true;
		System.out.println(c);
		}
		
	}
	public void draw(){
		super.draw();
		glTranslated(0,0,-30);
		glRotated(currentTick/1d,1,1,0);
		for(Coord3D pos: cubes){
			glPushMatrix();
				DrawUtil.translate(pos);
				glRotated(currentTick,1,1,0);
				DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1,1),colors);
			glPopMatrix();
		}
		DrawUtil.setColor(Color.WHITE);
		if(lines)
			drawWires();
		setup2DMatrix();
		glTranslatef((1680-50)/2,-500,0);
		TextUtil.getInstance().write("Space and Arrow Keys", new Coord());
		setup3DMatrix();
	}
	public void drawWires(){
		glBegin(GL_LINE_STRIP);
		for(int i = 0; i <= 360; i += 5){
			glVertex3d(Math.cos(Math.toRadians(i))*10,Math.sin(Math.toRadians(i))*10,0);
		}
		glEnd();
		glBegin(GL_LINE_STRIP);
		for(int i = 0; i <= 360; i += 5){
			glVertex3d(Math.cos(Math.toRadians(i))*10,0,Math.sin(Math.toRadians(i))*10);
		}
		glEnd();
		glBegin(GL_LINE_STRIP);
		for(int i = 0; i <= 360; i += 5){
			glVertex3d(0,Math.cos(Math.toRadians(i))*10,Math.sin(Math.toRadians(i))*10);
		}
		glEnd();
	}
	public static void main(String[] args) {
		new TesterClass3(new IntCoord(1680,1050), 70, "Tester 3", true, ApplicationWindow.THREE_DIMENSIONAL);
	}

}
