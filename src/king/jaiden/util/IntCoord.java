package king.jaiden.util;

public class IntCoord extends Coord {
	
	// This class was designed lazily.  It doesn't mesh consistently with 
	// the normal Coords if you add a Coord to an IntCoord.  Adding an 
	// IntCoord to a Coord should work just fine.
	
	private int x,
				y;
	
	public void subtract(Coord coord){
		
		add(new Coord(Math.round(-coord.getX()),Math.round(-coord.getY())));
		
	}
	
	public void add(Coord coord){
		
		x += (int)coord.getX();
		y += (int)coord.getY();
		
	}
	
	public double getRadius(){
		
		return (int)(Math.round(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
		
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
}
