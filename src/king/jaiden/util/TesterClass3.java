package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

public class TesterClass3 extends ApplicationWindow {
	private Color[] colors;
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
	}
	public void draw(){
		super.draw();
		glTranslated(0,0,-30);
		glRotated(currentTick,1,1,0);
		
		DrawUtil.drawRectPrismAboutOrigin(new Coord3D(10,10,10),colors);
		setup2DMatrix();
		DrawUtil.drawRectAboutOrigin(new Coord(100,50));
		setup3DMatrix();
	}
	public static void main(String[] args) {
		new TesterClass3(new IntCoord(1680,1050), 70, "Tester 3", true, ApplicationWindow.THREE_DIMENSIONAL);
	}

}
