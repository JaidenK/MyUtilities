package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

public class Vector3D extends Coord3D implements Drawable{
	public Vector3D(double x,double y, double z){
		super(x,y,z);
	}
	public Vector3D cross(Vector3D b){
		double i = getY()*b.getZ() - getZ()*b.getY();
		double j = getX()*b.getZ() - getZ()*b.getX();
		double k = getX()*b.getY() - getY()*b.getZ();
		return new Vector3D(i,j,k);
	}
	public void draw(){
		glBegin(GL_LINES);
			glVertex3d(0,0,0);
			glVertex3d(getX(),getY(),getZ());
		glEnd();
	}
}
