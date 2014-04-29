package king.jaiden.util;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
public class VoxelModel {
	
	Sprite sprite;
	Color[][][] voxels;
	int pixelsPerSpriteRow;
	int pixelsPerSpriteCol;
	public VoxelModel(Sprite s){
		sprite = s;
		sprite.setCurrentSprite(0);
		sprite.setDimensions(new Coord(160, 130));
		byte[] textureData =  sprite.getTexture().getTextureData();
		
		int imageHeight = sprite.getTexture().getTextureHeight();
		int imageWidth = sprite.getTexture().getTextureWidth();
		pixelsPerSpriteRow = imageWidth / (int)sprite.getSpritesPerRowAndColumn().getX();
		pixelsPerSpriteCol = imageHeight / (int)sprite.getSpritesPerRowAndColumn().getY();
		System.out.println(pixelsPerSpriteRow);
		System.out.println(pixelsPerSpriteCol);
		voxels = new Color[sprite.getTotalSprites()][pixelsPerSpriteRow][pixelsPerSpriteCol];
		for(int i = 0; i < textureData.length; i+=4){
			int pixelNumber = i/4;
			int row = (pixelNumber/imageWidth)%pixelsPerSpriteCol;
			int col = (pixelNumber%imageWidth)%pixelsPerSpriteRow;
			
			int spriteCol = (pixelNumber%imageWidth)/pixelsPerSpriteRow;
			int spriteRow = ((pixelNumber/imageWidth))/pixelsPerSpriteCol;
			
			int depth = spriteRow * (int)sprite.getSpritesPerRowAndColumn().getX() + spriteCol;
			System.out.println(					textureData[i]+" "+
												textureData[i+1]+" "+
												textureData[i+2]+" "+
												textureData[i+3]);
			
			if(textureData[i+3]==-1){ 
				voxels[depth][row][col] = new Color(Math.abs(textureData[i])/255d,
													Math.abs(textureData[i+1])/255d,
													Math.abs(textureData[i+2])/255d,
													1);
			}else{
				voxels[depth][row][col] = new Color(Math.abs(textureData[i])/100,
													Math.abs(textureData[i+1])/100d,
													Math.abs(textureData[i+2])/100d,
													Math.abs(textureData[i+3])/255d);
			}
		}
	}
	
	public void draw(){
		glPushMatrix();
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		for(int depth = 0; depth<voxels.length; depth++){
			for(int row = 0; row<voxels[depth].length; row++){
				for(int col = 0; col<voxels[depth][row].length; col++){
					DrawUtil.drawRectPrismAboutOrigin(new Coord3D(1,1,1),voxels[depth][row][col]);
					glTranslatef(1,0,0);
				}
				glTranslatef(-pixelsPerSpriteRow,0,0);
				glTranslatef(0,-1,0);
			}
			glTranslatef(0,pixelsPerSpriteCol,0);
			glTranslatef(0,0,1);
		}
		glPopMatrix();
	}
}
