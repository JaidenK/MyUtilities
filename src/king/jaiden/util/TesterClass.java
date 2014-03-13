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
		}catch(Exception e){
			e.printStackTrace();
		}

		sprite = new Sprite("res/images/running.png", 
					  new IntCoord(5,6), 26, Sprite.HORIZONTAL);
		
		sprite.setCurrentSprite(0);
		sprite.setDimensions(new Coord(160, 130));
		
		Coord letterRatio = new Coord(5,10);
		
		menu = new Menu();
		menu.setColor(new Color(0.5,0.3,0.9));
		menu.setSize(Size.FIXED);
		menu.setDimensions(new Coord(250,500));
		menu.setVisible(true);
		
		Label label = new Label("Hello World",letterRatio);
		label.setDimensions(new Coord(200,40));
		label.setSize(Size.MATCH_PARENT_WIDTH);
		label.setColor(Color.RED);

		Label label2 = new Label("Hello World",letterRatio);
		label2.setDimensions(new Coord(100,20));
		label2.setSize(Size.MATCH_PARENT_WIDTH);
		label2.setColor(Color.RED);
		
		Label label3 = new Label("AXYZ",letterRatio);
		label3.setDimensions(new Coord(100,70));
		label3.setSize(Size.MATCH_PARENT_WIDTH);
		label3.setColor(Color.RED);
		
		menu.add(label);
		menu.add(label2);
		menu.add(new Label("Text",letterRatio));
		menu.add(label3);
		
		
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}
	
	public void draw() {
		super.draw();
		glLoadIdentity();
		DrawUtil.setColor(color);
		
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
