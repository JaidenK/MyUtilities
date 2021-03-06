package king.jaiden.util;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

public class Menu extends InterfaceItem {
	
	public ArrayList<Coord> childrenCoordinates;
	
	public Menu(){
		dimensions = new Coord(100,100);
		visible = false;
		color = Color.WHITE;
		size = size.FIXED;
		padding = 5;
		childrenItems = new ArrayList<InterfaceItem>();
		childrenCoordinates = new ArrayList<Coord>();
	}
	
	public void add(InterfaceItem child){
		childrenItems.add(child);
		child.setParentItem(this);
		child.resize();
		resize();
	}
	
	public void remove(InterfaceItem child){
		childrenItems.remove(child);
		resize();
	}
	
	public void remove(int index){
		childrenItems.remove(index);
		resize();
	}
	
	public void removeAll(){
		childrenItems = new ArrayList<InterfaceItem>();
	}
	
	public void draw(){
		glPushMatrix();
			if(position!=null){
				glTranslated(position.getX(),position.getY(),0);
			}
		
		
		if(!visible)
			return;

		DrawUtil.setColor(color);
		if(image==null)
			DrawUtil.drawRectAboutOrigin(dimensions);
		else
			image.draw();
		if(childrenItems!=null){
			double childrenHeight = 0;
			int countedChildren = 0;
			for(InterfaceItem child : childrenItems){
				if(child.getPosition()==null){
					childrenHeight += child.getDimensions().getY();
					countedChildren ++;
				}
			}
			childrenHeight += padding*(countedChildren-1);
			double halfChildrenHeight = childrenHeight/2;
			
			glPushMatrix();
			glTranslated(0,halfChildrenHeight,0);
			
			glTranslated(0,0,0.01);
			for(int i = 0; i<childrenItems.size(); i++){
				if(childrenItems.get(i).getPosition()==null)
					glTranslated(0,-childrenItems.get(i).getDimensions().getY()/2,0);
//				System.out.println(-childrenItems.get(i).getDimensions().getY()/2);
				childrenItems.get(i).draw();
				if(childrenItems.get(i).getPosition()==null)
					glTranslated(0,-childrenItems.get(i).getDimensions().getY()/2-padding,0);
			}
			glPopMatrix();
		}
		
		glPopMatrix();

	}
}
