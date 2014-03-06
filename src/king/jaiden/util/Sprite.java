package king.jaiden.util;

import org.newdawn.slick.opengl.Texture;

public class Sprite extends Image {
	
	private IntCoord spritesPerRowAndColumn;
	private int currentSprite,
				totalSprites,
				order;
	public static final int VERTICAL = 0,
							HORIZONTAL = 1;
	
	public Sprite(Texture texture, IntCoord spritesPerRowAndColumn, int totalSprites, int order){
		this.spritesPerRowAndColumn = spritesPerRowAndColumn;
		this.texture = texture;
		this.currentSprite = 0;
		this.totalSprites = totalSprites;
		this.order = order;
	}
	
	public void nextSprite(){
		currentSprite++;
		validateCurrentSprite();
		updateTexCoords();
	}
	
	private void validateCurrentSprite(){
		if(currentSprite>=totalSprites)
			currentSprite=0;
	}
	
	private void updateTexCoords(){

		//TODO This only accounts for horizontally ordered Sprites
		
		double colsPerSprite = 1d / spritesPerRowAndColumn.getX();
		double rowsPerSprite = 1d / spritesPerRowAndColumn.getY();
		double x = (currentSprite % spritesPerRowAndColumn.getX()) * rowsPerSprite;
		double y = Math.floor(currentSprite/spritesPerRowAndColumn.getX()) * rowsPerSprite;

		texCoords = new Coord[4];
		texCoords[0] = new Coord(x,y); // Top Left
		texCoords[1] = new Coord(x,y+rowsPerSprite); // Bottom left
		texCoords[2] = new Coord(x+colsPerSprite,y+rowsPerSprite); // Bottom right
		texCoords[3] = new Coord(x+colsPerSprite,y); // Top Right
		
				
	}

	public IntCoord getSpritesPerRowAndColumn() {
		return spritesPerRowAndColumn;
	}

	public void setSpritesPerRowAndColumn(IntCoord spritesPerRowAndColumn) {
		this.spritesPerRowAndColumn = spritesPerRowAndColumn;
	}

	public int getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(int currentSprite) {
		this.currentSprite = currentSprite;
		validateCurrentSprite();
		updateTexCoords();
	}

	public int getTotalSprites() {
		return totalSprites;
	}

	public void setTotalSprites(int totalSprites) {
		this.totalSprites = totalSprites;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
