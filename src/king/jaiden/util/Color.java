package king.jaiden.util;

public class Color {
	
	private double red,
				   green,
				   blue,
				   alpha;
	
	public static final Color RED = new Color(1,0,0),
							  GREEN = new Color(0,1,0),
							  BLUE = new Color(0,0,1),
							  WHITE = new Color(1,1,1),
							  BLACK = new Color(0,0,0);
	
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
}
