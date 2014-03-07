package king.jaiden.util;

public class Coord {
	
	private double x,
				   y;
	
	public Coord(){
		
		this(0,0);
		
	}
	
	public Coord(double x, double y){
		
		this.x = x;
		this.y = y;
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void subtract(Coord coord){
		
		add(new Coord(-coord.getX(),-coord.getY()));
		
	}
	
	public void add(Coord coord){
		
		x += coord.getX();
		y += coord.getY();
		
	}
	
	public double getRadius(){
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
	}
	
	public boolean equals(Coord coord){
		
		return (x == coord.getX() && y == coord.getY());
		
	}
	
	public int compareTo(Coord coord){
		
		if(getRadius() < coord.getRadius())
			return -1;
		else if(getRadius() == coord.getRadius())
			return 0;
		else if(getRadius() > coord.getRadius())
			return 1;
		else 
			return 0;
		
	}
	
	public Coord copy(){
		return new Coord(x,y);
	}
		
}
