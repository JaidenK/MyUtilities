package king.jaiden.util;

public class TextUtil {

	public static final int RIGHT = 0,
							LEFT = 1,
							CENTER = 2;
	
	private int alignment;
	
	private Sprite letters,
				   numbers,
				   symbols;
	
	private TextUtil instance;
	
	private TextUtil(){
		setAlignment(0);
		letters = new Sprite();
		numbers = new Sprite();
		symbols = new Sprite();
	}
	
	public TextUtil getInstance(){
		if(instance==null)
			instance = new TextUtil();
		return instance;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}
	
	
	
}
