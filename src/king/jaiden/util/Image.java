package king.jaiden.util;

import org.newdawn.slick.opengl.Texture;

public class Image implements Drawable {
	
	protected double width,
				   height;
	protected Coord[] texCoords;
	protected Texture texture;
	
	
	
	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public Coord[] getTexCoords() {
		return texCoords;
	}



	public boolean setTexCoords(Coord[] texCoords) {
		if(texCoords.length<4)
			return false;
		this.texCoords = texCoords;
		return true;
	}



	public Texture getTexture() {
		return texture;
	}



	public void setTexture(Texture texture) {
		this.texture = texture;
	}



	@Override
	public void draw() {

		DrawUtil.drawRectAboutOrigin(width, height, texture, texCoords);
		
	}
	
}
