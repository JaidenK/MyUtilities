package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

public class Vector3D extends Coord3D implements Drawable{
	private double xRot = 0, yRot = 0, zRot = 0, headScale = 0;
	public Vector3D(double x,double y, double z){
		super(x,y,z);
		xRot = getRotation(y,Math.abs(z));
		yRot = -getRotation(x,z); 
		zRot = -getRotation(x,y);
	}
	public double getxRot() {
		return xRot;
	}
	public double getyRot() {
		return yRot;
	}
	public double getzRot() {
		return zRot;
	}
	public double getHeadScale() {
		return headScale;
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
		headScale = 0.2;//getRadius()*0.1;
		glTranslated(getX(),getY(),getZ());
		glRotated(yRot,0,0,1);
		glRotated(xRot,0,1,0);
		glRotated(zRot,1,0,0);
	}
	public void drawCone(){
		glScaled(headScale,headScale,headScale);
		glBegin(GL_TRIANGLES);
		int dTheta = 10;
		for(int i = 0; i<=360; i+=dTheta){
			if(i%3==0)
				DrawUtil.setColor(Color.RED);
			else
				DrawUtil.setColor(Color.BLUE);
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
	public static double getRotation(double x, double y){
		double theta = 0;
		Coord coord = new Coord(x,y);
		if(coord.getRadius()!=0)
			theta = Math.toDegrees(Math.acos(y / coord.getRadius()));
		if(x>0)
			theta = - theta;
		return theta;
	}
}
