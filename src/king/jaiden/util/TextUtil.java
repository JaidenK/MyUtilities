package king.jaiden.util;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;

public class TextUtil {

	public static final int RIGHT = 0,
							LEFT = 1,
							CENTER = 2;
	
	private int alignment;
	
	private Sprite lowercaseLetters,
				   uppercaseLetters,
				   numbers,
				   symbols;
	
	private TextUtil instance;
	
	private TextUtil(){
		//TODO These are all incorrect.  This needs to be cleaned up.  Fix it.
		
		setAlignment(0);
		try{
			lowercaseLetters = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha0.png"))), 
				 					  	  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
			uppercaseLetters = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha1.png"))), 
					  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
			numbers = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha2.png"))), 
						 new IntCoord(6,6), 26, Sprite.HORIZONTAL);
			symbols = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha3.png"))), 
						 new IntCoord(6,6), 26, Sprite.HORIZONTAL);
		}catch(Exception e){
			e.printStackTrace();
			try{
				lowercaseLetters = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/missingImage.png"))), 
					 					  	  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
				uppercaseLetters = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/missingImage.png"))), 
						  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
				numbers = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/missingImage.png"))), 
								 new IntCoord(6,6), 26, Sprite.HORIZONTAL);
				symbols = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/missingImage.png"))), 
					 			 new IntCoord(6,6), 26, Sprite.HORIZONTAL);
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public TextUtil getInstance(){
		if(instance==null)
			instance = new TextUtil();
		return instance;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}
	
	
	
}
