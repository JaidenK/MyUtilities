package king.jaiden.util;

import java.util.ArrayList;

public class MatchParent extends Size {

	@Override
	public void resize(InterfaceItem parent, InterfaceItem itemToResize,
			ArrayList<InterfaceItem> children) {
		if(parent!=null){
			itemToResize.setDimensions(parent.getDimensions().copy());
		}

	}

}
