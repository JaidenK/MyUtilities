package king.jaiden.util;
public abstract class Animation {
	protected int i = 0;
	protected int timeLimit = 0;
	protected ApplicationWindow game;
	public Animation(int timeLimit,ApplicationWindow game){
		// Every time a new Animation object is created it will immediately start playing.
		this.timeLimit = timeLimit;
		this.game = game;
		game.cutscene = true;
	}
	public abstract void input();
	public void draw(){
		// increment the counter, and then end the cutscene when it's over
		i++;
		if(i>=timeLimit&&i!=-1){
			game.cutscene = false;
		}
	}
}
