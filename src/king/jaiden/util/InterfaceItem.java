package king.jaiden.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class InterfaceItem {
	protected Coord dimensions;
	protected Size size;
	protected boolean visible;
	protected Color color;
	protected Image image;
	protected double padding;
	protected InterfaceItem parentItem;
	protected ArrayList<InterfaceItem> childrenItems;
	protected ActionListener actionListener;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public double getPadding() {
		return padding;
	}
	public void setPadding(double padding) {
		this.padding = padding;
		resize();
	}
	public InterfaceItem getParentItem() {
		return parentItem;
	}
	public void setParentItem(InterfaceItem parentItem) {
		this.parentItem = parentItem;
		resize();
	}
//	public ArrayList<InterfaceItem> getChildrenItems() {
//		return childrenItems;
//	}
//	public void setChildrenItems(ArrayList<InterfaceItem> childrenItems) {
//		this.childrenItems = childrenItems;
//	}
	public void addActionListener(ActionListener actionListener){
		this.actionListener = actionListener;
	}
	public void fireActionPerformed(){
		actionListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
	}
	public void resize(){
		size.resize(parentItem, this, childrenItems);
	}
	public abstract void draw();
	public Coord getDimensions() {
		return dimensions;
	}
	public void setDimensions(Coord dimensions) {
		this.dimensions = dimensions;
	}
	public void setSize(Size size) {
		this.size = size;
		resize();
	}
}
