package king.jaiden.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TesterClass2 extends ApplicationWindow{
	private Menu menu;
	
	public TesterClass2(IntCoord coord) {
		super(coord);
	}

	public static void main(String[] args){
		new TesterClass2(new IntCoord(1280,720));
	}

	@Override
	public void init() {
		setMenuBegin();
		registerMouseListener(menu);
		
		
		Label label = new Label("Click to begin...",new Coord(10,20));
		label.setDimensions(new Coord(1,50));
		label.setSize(Size.MATCH_PARENT_WIDTH);
		
		
		Image logoImage = new Image("res/images/logo.png");
		
		Label logo = new Label("",new Coord(1,1));
		logo.setDimensions(new Coord(500,500));
		logo.setImage(logoImage);
		
		menu.add(logo);
		menu.add(label);
	}
	
	public void setMenuBegin(){
		menu = new Menu();
		menu.setColor(Color.CLEAR);
		menu.setDimensions(new Coord(windowDimensions.getX(),windowDimensions.getY()));
		menu.setVisible(true);
		menu.onClick(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menu2();
			}
			
		});
	}
	
	public void menu2(){
		menu.removeAll();
		Label label = new Label("Click to exit.",new Coord(10,20));
		label.setSize(Size.MATCH_PARENT);
		menu.add(label);
		label.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
	}
	
	public void input(){
		super.input();
	}
	
	public void draw(){
		super.draw();
		menu.draw();
	}
}
