package king.jaiden.util;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MyMouseEvent extends MouseEvent{

	InterfaceItem source;
	
	public MyMouseEvent(InterfaceItem source) {
		super(null, 0, 0, 0, 0, 0, 1, false);
		
	}

	public InterfaceItem getSource(){
		return source;
	}

	
}
