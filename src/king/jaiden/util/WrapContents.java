package king.jaiden.util;

import java.util.ArrayList;

public class WrapContents extends Size {

	@Override
	public void resize(InterfaceItem parent, InterfaceItem itemToResize,
			ArrayList<InterfaceItem> children) {
		// TODO Auto-generated method stub
		double maxWidth = 0;
		double height = itemToResize.getPadding()*2;
		if(children!=null){
			for(InterfaceItem child : children){
				if(child.getDimensions().getX()>maxWidth)
					maxWidth = child.getDimensions().getX();
				height+=child.getDimensions().getY();
			}
			height += itemToResize.getPadding()*(children.size()-1);
		}
		maxWidth += itemToResize.getPadding()*2;
		System.out.println(maxWidth+" "+height);
		itemToResize.setDimensions(new Coord(maxWidth,height));
	}

}
