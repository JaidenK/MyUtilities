package king.jaiden.util;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class World {
	ArrayList<Entity> entities;
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public ArrayList<Mob> getMobs() {
		return mobs;
	}

	public void setMobs(ArrayList<Mob> mobs) {
		this.mobs = mobs;
	}

	public Block[][][] getTerrain() {
		return terrain;
	}

	public void setTerrain(Block[][][] terrain) {
		this.terrain = terrain;
	}

	ArrayList<Mob> mobs;
	Block[][][] terrain;
	public World(){
		entities = new ArrayList<Entity>();
		mobs = new ArrayList<Mob>();
		terrain = new Block[1][1][1];
	}
	
	public void newWorld(int layers, int rows, int columns){
		String levelData = layers+" "+rows+" "+columns+" ";
		for(int i = 0; i < layers*rows*columns; i++){
			levelData += "0 ";
		}
		loadTerrain(levelData);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public boolean removeEntity(Entity e){
		return entities.remove(e);
	}
	
	public void addMob(Mob m){
		mobs.add(m);
	}

	public boolean removeMob(Mob e){
		return mobs.remove(e);
	}
	
	public boolean loadTerrain(String levelData){
		try {
			String[] dataArray = levelData.split(" ");
			
			int levelLayers = Integer.parseInt(dataArray[0]);
			int levelRows = Integer.parseInt(dataArray[1]);
			int levelColumns = Integer.parseInt(dataArray[2]);
			
			terrain = new Block[levelLayers][levelRows][levelColumns];
			
			try{
				for(int l = 0; l < levelLayers; l++){
					for(int r = 0; r < levelRows; r++){
						for(int c = 0; c < levelColumns; c++){
							int blockIdIndex = l*levelRows*levelColumns + r*levelColumns + c + 3;
							System.out.println(blockIdIndex);
							terrain[l][r][c] = Block.getBlockFromId(Integer.parseInt(dataArray[blockIdIndex]));
						}
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				System.out.println("This level is missing block data.");
			}
			
			System.out.println("Level opened sucessfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("This level is critically malformed.");
			return false;
		}
	}
	
	public void generateTerrain(){
		String levelData = "10 10 10 ";
		for(int i = 0; i < 1000; i++){
			levelData += (int)(Math.random()*3)+" ";
		}
		loadTerrain(levelData);
	}
	
	public void draw(){
		glPushMatrix();
		for(int l = 0; l < terrain.length; l++){
			for(int r = 0; r < terrain[l].length; r++){
				for(int c = 0; c < terrain[l][r].length; c++){
					if(terrain[l][r][c] != null)
						terrain[l][r][c].draw();
					glTranslated(1,0,0);
				}
				glTranslated(-terrain[l][r].length,1,0);
			}
			glTranslated(0,-terrain[l].length,1);
		}
		glPopMatrix();
	}
}
