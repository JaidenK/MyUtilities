package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;

public class DrawUtil {

	public static void drawRectAboutOrigin(Coord dimensions){
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);	// Shapes should be drawn counter-clockwise 
			glVertex2d(-dimensions.getX()/2, dimensions.getY()/2); // Top-left
			glVertex2d(-dimensions.getX()/2, -dimensions.getY()/2); // bottom-left
			glVertex2d(dimensions.getX()/2, -dimensions.getY()/2); // bottom-right
			glVertex2d(dimensions.getX()/2, dimensions.getY()/2); // Top-right
		glEnd();
	}
	
	public static void drawRectAboutOrigin(Coord dimensions, Texture texture){
		Coord[] coords = {new Coord(0,0),	// create an array of coords that covers the whole texture
						  new Coord(0,1),
						  new Coord(1,1),
						  new Coord(1,0)};
		drawRectAboutOrigin(dimensions, texture, coords);
	}
	
	public static void drawRectAboutOrigin(Coord dimensions, Texture texture, Coord[] texCoords){
		if(texCoords.length>=4){
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
			glBegin(GL_QUADS);	// Shapes should be drawn counter-clockwise
				glTexCoord2d(texCoords[0].getX(), texCoords[0].getY());
				glVertex2d(-dimensions.getX()/2, dimensions.getY()/2); // Top-left
				glTexCoord2d(texCoords[1].getX(), texCoords[1].getY());
				glVertex2d(-dimensions.getX()/2, -dimensions.getY()/2); // bottom-left
				glTexCoord2d(texCoords[2].getX(), texCoords[2].getY());
				glVertex2d(dimensions.getX()/2, -dimensions.getY()/2); // bottom-right
				glTexCoord2d(texCoords[3].getX(), texCoords[3].getY());
				glVertex2d(dimensions.getX()/2, dimensions.getY()/2); // Top-right
			glEnd();	
			glDisable(GL_TEXTURE_2D);
		}else{
			new ArrayIndexOutOfBoundsException("You are not passing enough Coords to draw a quadrilateral").printStackTrace();
			drawRectAboutOrigin(dimensions,texture);
		}
	}
	
	public static void drawRectPrismAboutOrigin(Coord3D dimensions,Color[] colors){
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);	// Shapes should be drawn counter-clockwise 
		
			setColor(colors[0]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Front
			setColor(colors[1]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			setColor(colors[2]);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			setColor(colors[3]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			setColor(colors[4]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); // Back
			setColor(colors[5]);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[6]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[7]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 

			setColor(colors[7]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); // Left
			setColor(colors[6]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[1]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			setColor(colors[0]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			setColor(colors[3]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Right
			setColor(colors[2]);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			setColor(colors[5]);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[4]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 

			setColor(colors[3]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Top
			setColor(colors[4]);
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[7]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[0]);
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			setColor(colors[1]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); // Bottom
			setColor(colors[6]);
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			setColor(colors[5]);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2);
			setColor(colors[2]); 
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			
		glEnd();
	}

	public static void drawRectPrismAboutOrigin(Coord3D dimensions, Color color){
		setColor(color);
		drawRectPrismAboutOrigin(dimensions);
	}
	
	public static void drawRectPrismAboutOrigin(Coord3D dimensions){
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);	// Shapes should be drawn counter-clockwise 
		
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Front
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); // Back
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 

			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); // Left
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Right
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 

			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); // Top
			glVertex3d(dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(-dimensions.getX()/2, dimensions.getY()/2, dimensions.getZ()/2); 

			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); // Bottom
			glVertex3d(-dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2); 
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, -dimensions.getZ()/2);
			glVertex3d(dimensions.getX()/2, -dimensions.getY()/2, dimensions.getZ()/2); 
			
		glEnd();
	}
	
	public static void setup2DMatrix(int width, int height){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-0.5*width, 0.5*width, -0.5*height, 0.5*height, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public static void translate(Coord pos){
		glTranslated(pos.getX(),pos.getY(),0);
	}
	public static void translate(Coord3D pos){
		glTranslated(pos.getX(),pos.getY(),pos.getZ());
	}
	
	public static void setup3DMatrix(int width, int height, float zNear, float zFar){
		glViewport(0,0,width,height);
		// Setup the Matrix
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(100, (float)width/height, zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public static void setColor(Color color){
		glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	public static void drawAxis(){
		glBegin(GL_LINES);
			setColor(Color.RED);
			glVertex3d(-1,0,0);
			glVertex3d(1,0,0);
			setColor(Color.GREEN);
			glVertex3d(0,-1,0);
			glVertex3d(0,1,0);
			setColor(Color.BLUE);
			glVertex3d(0,0,-1);
			glVertex3d(0,0,1);
		glEnd();
	}
	
	public static void drawPoint(Coord3D p){
		glVertex3d(p.getX(),p.getY(),p.getZ());
	}
	
}
