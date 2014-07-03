package king.jaiden.util;

public class Label extends InterfaceItem {

	private String label;
	private double letterAspectRatio;
	private Coord fontSize;
	private int textAlignment;
	
	public Label(String label,Coord letterSize){
		dimensions = new Coord(100,100);
		visible = true;
		color = Color.WHITE;
		size = size.FIXED;
		textAlignment = TextUtil.CENTER;
		this.label = label;
		this.letterAspectRatio = letterSize.getX()/letterSize.getY();
		resize();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
		
		if(image!=null){
			image.draw();
		}
		
		
		TextUtil.getInstance().setAlignment(textAlignment);
		TextUtil.getInstance().setTextSize(fontSize);
		TextUtil.getInstance().write(label, new Coord());
	}

	public int getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(int textAlignment) {
		this.textAlignment = textAlignment;
	}

}
