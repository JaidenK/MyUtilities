package king.jaiden.util;

public class Vector3D extends Coord3D {
	public Vector3D(double x,double y, double z){
		super(x,y,z);
	}
	public Vector3D cross(Vector3D b){
		double i = getY()*b.getZ() - getZ()*b.getY();
		double j = getX()*b.getZ() - getZ()*b.getX();
		double k = getX()*b.getY() - getY()*b.getZ();
		return new Vector3D(i,j,k);
	}

}
