package king.jaiden.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Image implements Drawable {
	
	private static final String FILE_NOT_FOUND_PATH = "res/images/missingImage.png";
	protected Coord dimensions;
	protected Coord[] texCoords;
	protected Texture texture;
	
	public Image(String path){
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(FILE_NOT_FOUND_PATH)));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Coord getDimensions() {
		return dimensions;
	}



	public void setDimensions(Coord dimensions) {
		this.dimensions = dimensions;
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
		if(texCoords==null)
			DrawUtil.drawRectAboutOrigin(dimensions, texture);
		else
			DrawUtil.drawRectAboutOrigin(dimensions, texture, texCoords);
		
	}
	
}
