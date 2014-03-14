package king.jaiden.util;

public class Coord3D extends Coord {
	private double z;

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public Coord3D(){
		this(0,0,0);
	}
	
	public Coord3D(double componentMaxDiameter){
		this((Math.random()-0.5)*componentMaxDiameter,
			 (Math.random()-0.5)*componentMaxDiameter,
			 (Math.random()-0.5)*componentMaxDiameter);
	}
	
	public Coord3D(double x, double y, double z){
		super(x,y);
		this.z = z;
	}
	
	public void subtract(Coord3D coord){
		
		add(new Coord3D(-coord.getX(),-coord.getY(),-coord.getZ()));
		
	}
	
	public void add(Coord3D coord){
		
		this.setX(this.getX() + coord.getX());
		this.setY(this.getY() + coord.getY());
		this.setZ(this.getZ() + coord.getZ());
		
	}
	
	public double getRadius(){
		
		return Math.sqrt(Math.pow(this.getZ(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
		
	}
	
	public boolean equals(Coord3D coord){
		
		return (this.getX() == coord.getX() && this.getY() == coord.getY() && z == coord.getZ() );
		
	}
	public Coord3D copy(){
		return new Coord3D(this.getX(),this.getY(),this.getZ());
	}
	
}
