package king.jaiden.util;

public class Block {
	public static Block AIR = new Block(0,false,Color.CLEAR),
						GRASS = new Block(1,false,Color.GREEN),
						DIRT = new Block(2,false,Color.BROWN);
	
	private boolean solid;
	private Color color;
	private int blockId;
	public int getBlockId() {
		return blockId;
	}
	public Block(int id, boolean solid, Color color){
		this.solid = solid;
		this.color = color;
		blockId = id;
	}
	public static Block getBlockFromId(int id){
		switch(id){
		case 0:
			return AIR;
		case 1:
			return GRASS;
		case 2:
			return DIRT;
		default:
			return AIR;
		}
	}
	public void draw(){
		if(color!=Color.CLEAR)
			DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1,1), color);
	}
}
