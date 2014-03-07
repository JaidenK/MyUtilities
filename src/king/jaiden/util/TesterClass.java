package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TesterClass extends ApplicationWindow{
	Color color;
	Texture texture;
	
	Sprite sprite;
	
	Menu menu;
	
	public TesterClass(IntCoord intCoord) {
		super(intCoord);
	}

	public static void main(String[] args){
		new TesterClass(new IntCoord(1280,720));
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		glDisable(GL_DEPTH_TEST);
		
		color = Color.RED;
		try{
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/alpha0.png")));
			sprite = new Sprite(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/running.png"))), 
  					  new IntCoord(5,6), 26, Sprite.HORIZONTAL);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sprite.setCurrentSprite(0);
		sprite.setHeight(160);
		sprite.setWidth(130);
		
		menu = new Menu();
		menu.setColor(new Color(0.5,0.3,0.9));
		menu.setSize(Size.FIXED);
		menu.setDimensions(new Coord(150,250));
		menu.setVisible(true);
		
		Label label = new Label("Hello World",new Coord(5,10));
		label.setDimensions(new Coord(100,20));
		label.setSize(Size.MATCH_PARENT);
		
		menu.add(label);
		
		
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}
	
	public void draw() {
		super.draw();
		glLoadIdentity();
		DrawUtil.setColor(color);
		glTranslated(windowDimensions.getX()/2,windowDimensions.getY()/2,0);
		
		glPushMatrix();
		glTranslated(-Math.cos(Math.toRadians(currentTick))*100,Math.sin(Math.toRadians(currentTick))*100,0);
		DrawUtil.drawRectAboutOrigin(new Coord(100, 50));
		glPopMatrix();
		
		glPushMatrix();
		DrawUtil.setColor(Color.WHITE);
		glTranslated(Math.cos(Math.toRadians(currentTick))*100,0,0);
		DrawUtil.drawRectAboutOrigin(new Coord(100, 100), texture);
		glPopMatrix();
		
		sprite.draw();
		if(currentTick%2==0)
			sprite.nextSprite();
		
		
		Coord textSize = new Coord(12,20);
		TextUtil.getInstance().setTextSize(textSize);
	
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
		TextUtil.getInstance().write("the quick brown fox jumps over the lazy dog.", new Coord(0,160));
		TextUtil.getInstance().write("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG.", new Coord(0,120));
		TextUtil.getInstance().write("1234567890!@#$%^&*()[]{}',.\"<>;:-_/?=+\\|", new Coord(0,190));
		
		glTranslated(200,-100,0);
		menu.draw();
	}
}
