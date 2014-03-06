package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

public class DrawUtil {

	public static void drawRectAboutOrigin(double width, double height){
		glBegin(GL_QUADS);	// Shapes should be drawn counter-clockwise 
			glVertex2d(-width/2, height/2); // Top-left
			glVertex2d(-width/2, -height/2); // bottom-left
			glVertex2d(width/2, -height/2); // bottom-right
			glVertex2d(-width/2, height/2); // Top-right
		glEnd();
	}
	
}
