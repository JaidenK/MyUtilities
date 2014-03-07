package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

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
	
	public static void setColor(Color color){
		glColor4d(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
	}
	
}
