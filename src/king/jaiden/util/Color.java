package king.jaiden.util;

public class Color {
	
	private double red,
				   green,
				   blue,
				   alpha;
	
	public static final Color RED = new Color(1,0,0),
			  				  ORANGE = new Color(1,0.5,0),
			  				  YELLOW = new Color(1,1,0),
			  				  GREEN = new Color(0,1,0),
			  				  CYAN = new Color(0,1,1),
			  				  BLUE = new Color(0,0,1),
							  VIOLET = new Color(0.4,0.2,0.6),
							  WHITE = new Color(1,1,1),
							  BLACK = new Color(0,0,0),
							  GRAY = new Color(0.5,0.5,0.5),
	  					      CLEAR = new Color(1,1,1,0),
	  					      BROWN = new Color(0.4,0.05882352941176,0),
	  					      COPPER_ORANGE = new Color(0.85098039215686,0.56470588235294,0.34509803921569);
				
	
	public static final Color[] RAINBOW_1 = {RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,VIOLET,WHITE};
	
	public double getRed() {
		return red;
	}

	public void setRed(double red) {
		this.red = red;
	}

	public double getGreen() {
		return green;
	}

	public void setGreen(double green) {
		this.green = green;
	}

	public double getBlue() {
		return blue;
	}

	public void setBlue(double blue) {
		this.blue = blue;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public Color(double red, double green, double blue){
		this(red,green,blue,1);
	}

	public Color(double red, double green, double blue, double alpha){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public void setColor(double red, double green, double blue){
		setColor(red,green,blue,1);
	}

	public void setColor(double red, double green, double blue, double alpha){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public static Color getRandomColor(){
		return new Color(Math.random(),Math.random(),Math.random(),1);
	}
	
	public static Color getDirtColor(double radius){
		return new Color((155d/255)+(Math.random()-0.5)*radius,
						 (118d/255)+(Math.random()-0.5)*radius,
						 (83d/255)+(Math.random()-0.5)*radius);
	}
}
