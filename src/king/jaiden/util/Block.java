package king.jaiden.util;

public class Block {
	public static Block AIR = new Block(false,Color.CLEAR),
						GRASS = new Block(false,Color.GREEN),
						DIRT = new Block(false,Color.BROWN);
	
	boolean solid;
	Color color;
	public Block(boolean solid, Color color){
		this.solid = solid;
		this.color = color;
	}
	public static Block getBlockFromId(int id){
		switch(id){
		case 0:
			return AIR;
		case 1:
			return GRASS;
		case 2:
			return DIRT;
		}
		return null;
	}
	public void draw(){
		if(color!=Color.CLEAR)
			DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1,1), color);
	}
}
