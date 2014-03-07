package king.jaiden.util;

public class Label extends InterfaceItem {

	private String label;
	private double letterAspectRatio;
	private Coord fontSize;
	
	public Label(String label,Coord letterSize){
		dimensions = new Coord(100,100);
		visible = true;
		color = Color.WHITE;
		size = size.FIXED;
		this.label = label;
		this.letterAspectRatio = letterSize.getX()/letterSize.getY();
		resize();
	}
	
	public void resize(){
		super.resize();
		double trueLetterHeight;
		double trueLetterWidth;
		if(letterAspectRatio*label.length()>dimensions.getX()/dimensions.getY()){
			trueLetterHeight = (1/letterAspectRatio)*dimensions.getX()/label.length();
			trueLetterWidth = letterAspectRatio*trueLetterHeight;
		}else{
			trueLetterWidth = letterAspectRatio*dimensions.getY();
			trueLetterHeight = dimensions.getY();
		}
		fontSize = new Coord(trueLetterWidth,trueLetterHeight);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		DrawUtil.setColor(color);
		TextUtil.getInstance().setAlignment(TextUtil.CENTER);
		TextUtil.getInstance().setTextSize(fontSize);
		TextUtil.getInstance().write(label, new Coord());
	}

}
