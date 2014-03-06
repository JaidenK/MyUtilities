package king.jaiden.util;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;
import static org.lwjgl.opengl.GL11.*;

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
	
	private String alphabet,symbolSheet;
	
	private TextUtil(){		
		alignment = RIGHT;
		initSprites(lowercaseLetters,0);
		initSprites(uppercaseLetters,1);
		initSprites(numbers,2);
		initSprites(symbols,3);
		alphabet="abcdefghijklmnopqrstuv";
		symbolSheet="!@#$%^&*()[]{}',.\"<>;:-_/?=+\\|";
	}
	
	private void initSprites(Sprite sprite, int fileNumber){
		try{
			sprite = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha"+fileNumber+".png"))), 
				  	  					  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
		}catch(Exception e){
			e.printStackTrace();
			try{
				sprite = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/missingImage.png"))), 
					  	  new IntCoord(6,6), 26, Sprite.HORIZONTAL);
			}catch(Exception e2){
				e2.printStackTrace();
				System.exit(1);
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
	
	public void write(String word, Coord position, Coord letterSize){
		// Only does right-aligned
		glPushMatrix();
		glTranslated(position.getX(),position.getY(),0);
		for(int i = 0; i < word.length(); i++){
			write(word.charAt(i),position,letterSize);
			position.setX(position.getX()+letterSize.getX());
		}
		glPopMatrix();
	}
	
	private void write(char letter, Coord position, Coord letterSize){
		Sprite charSprites = getSpriteForChar(letter);
		charSprites.setHeight(letterSize.getY());
		charSprites.setWidth(letterSize.getX());
		
		int alphaIndex = getAlphaIndex(letter);//this is the index in the alphabet that the letter represents
		
		charSprites.setCurrentSprite(alphaIndex);
		charSprites.draw();
	}
	
	private int getAlphaIndex(char letter){
		if(Character.isLetter(letter))
			if(Character.isUpperCase(letter))
				return alphabet.toUpperCase().indexOf(letter);
			else
				return alphabet.indexOf(letter);
		else
			if(Character.isDigit(letter))
				return (Integer.parseInt(letter+""))-1;
			else 
				return symbolSheet.indexOf(letter);
	}
	
	private Sprite getSpriteForChar(char letter){
		if(Character.isLetter(letter))
			if(Character.isUpperCase(letter))
				return uppercaseLetters;
			else
				return lowercaseLetters;
		else
			if(Character.isDigit(letter))
				return numbers;
			else 
				return symbols;
	}
	
	
	
}
