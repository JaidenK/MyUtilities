package king.jaiden.util;

import java.util.ArrayList;

public abstract class Size {
	public static final Size FIXED = new Fixed(),
							 MATCH_PARENT = new MatchParent(),
							 MATCH_PARENT_WIDTH = new MatchParentWidth(),
							 WRAP_CONTENTS = new WrapContents();

	public abstract void resize(InterfaceItem parent, InterfaceItem itemToResize, ArrayList<InterfaceItem> children);
	
}
