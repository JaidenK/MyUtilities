package king.jaiden.util;

import java.util.ArrayList;

public class MatchParentWidth extends Size {

	@Override
	public void resize(InterfaceItem parent, InterfaceItem itemToResize,
			ArrayList<InterfaceItem> children) {
		if(parent!=null){
			Coord dimensions = parent.getDimensions().copy();
			dimensions.setY(itemToResize.getDimensions().getY());
			itemToResize.setDimensions(dimensions);
		}

	}

}
