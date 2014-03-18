package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

public class Vector3D extends Coord3D implements Drawable{
	private double theta = 0, phi = 0, headScale = 0;
	public Vector3D(double x,double y, double z){
		super(x,y,z);
		theta = Math.acos(y/getRadius());
		theta = 90;
		if(z!=0)
			phi = Math.atan(x/z);
		else
			phi = 90;
		System.out.println(theta+" "+phi);
	}
	public Vector3D cross(Vector3D b){
		double i = getY()*b.getZ() - getZ()*b.getY();
		double j = getX()*b.getZ() - getZ()*b.getX();
		double k = getX()*b.getY() - getY()*b.getZ();
		return new Vector3D(i,j,k);
	}
	public void drawLine(){
		glBegin(GL_LINES);
			glVertex3d(0,0,0);
			glVertex3d(getX(),getY(),getZ());
		glEnd();
	}
	public void transAndRotate(){
		headScale = getRadius()*0.01;
//		glTranslated(Math.random(),Math.random(),Math.random());
		glTranslated(getX(),getY(),getZ());
//		glRotated(Math.random()*360,0,0,1);
//		glRotated(Math.random()*360,0,1,0);
		glRotated(theta,0,0,1);
		glRotated(phi,0,1,0);
	}
	public void drawCone(){
//		glScaled(headScale,headScale,headScale);
		glBegin(GL_TRIANGLES);
		int dTheta = 10;
		for(int i = 0; i<=360; i+=dTheta){
			glVertex3d(Math.cos(Math.toRadians(i)),Math.sin(Math.toRadians(i)),0);
			glVertex3d(Math.cos(Math.toRadians(i+dTheta)),Math.sin(Math.toRadians(i+dTheta)),0);
			glVertex3d(0,0,2);
			glVertex3d(Math.cos(Math.toRadians(i)),Math.sin(Math.toRadians(i)),0);
			glVertex3d(0,0,0);
			glVertex3d(Math.cos(Math.toRadians(i+dTheta)),Math.sin(Math.toRadians(i+dTheta)),0);
		}
		glEnd();
	}
	public void draw(){
		drawLine();
		glPushMatrix();
		transAndRotate();
		drawCone();
		glPopMatrix();
	}
}
