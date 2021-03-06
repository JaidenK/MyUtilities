package king.jaiden.util;

import java.io.File;
import java.io.FileInputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import static org.lwjgl.opengl.GL11.*;

public class TextUtil {

	public static final int RIGHT = 0,
							LEFT = 1,
							CENTER = 2;
	
	private int alignment;
	
	private Sprite letters,
				   numbersAndSymbols;
	
	private static TextUtil instance;
	
	private String alphabet,symbolSheet;
	
	private Coord letterSize;
	
	private TextUtil(){		
		alignment = RIGHT;
		letters = initSprites(0);
		letters.setTotalSprites(52);
		numbersAndSymbols = initSprites(1);
		numbersAndSymbols.setTotalSprites(41);
		alphabet="abcdefghijklmnopqrstuvwxyz";
		symbolSheet="1234567890!@#$%^&*()[]{}',.\"<>;:-_/?=+\\| ";
		letterSize = new Coord(20,40);
	}
	
	private void shiftMatrixForAlignment(int wordLength, double letterWidth){
		if(alignment==LEFT)
			return;
		if(alignment==CENTER){
			glTranslated(-0.5*wordLength*letterWidth+letterWidth*0.5,0,0);
			return;
		}if(alignment==RIGHT){
			glTranslated(-wordLength*letterWidth,0,0);
			return;
		}
	}
	
	private Sprite initSprites(int fileNumber){
			return new Sprite("res/images/alpha"+fileNumber+".png", 
				  	  					  new IntCoord(8,8), 52, Sprite.HORIZONTAL);

	}
	
	public static TextUtil getInstance(){
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
	
	public void write(String word, Coord position){
		glPushMatrix();
		glTranslated(position.getX(),position.getY(),0);
		shiftMatrixForAlignment(word.length(),letterSize.getX());
		for(int i = 0; i < word.length(); i++){
			write(word.charAt(i),position,letterSize);
			glTranslated(letterSize.getX(),0,0);
		}
		glPopMatrix();
	}
	
	private void write(char letter, Coord position, Coord letterSize){

		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		Sprite charSprites = getSpriteForChar(letter);
		
		charSprites.setDimensions(letterSize);
		
		int alphaIndex = getAlphaIndex(letter);//this is the index in the alphabet that the letter represents
		
		charSprites.setCurrentSprite(alphaIndex);
		charSprites.draw();
		
//		DrawUtil.setColor(new Color(1,1,1,.2));
//		DrawUtil.drawRectAboutOrigin(letterSize);
//		DrawUtil.setColor(Color.WHITE);
	}
	
	private int getAlphaIndex(char letter){
		if(Character.isLetter(letter))
			if(Character.isUpperCase(letter))
				return alphabet.toUpperCase().indexOf(letter);
			else
				return alphabet.indexOf(letter)+26;
		else
			return symbolSheet.indexOf(letter);
	}
	
	private Sprite getSpriteForChar(char letter){
		if(Character.isLetter(letter))
			return letters;
		else
			return numbersAndSymbols;
	}

	public Coord getTextSize() {
		return letterSize;
	}

	public void setTextSize(Coord textSize) {
		this.letterSize = textSize;
	}
	
	
	
}
