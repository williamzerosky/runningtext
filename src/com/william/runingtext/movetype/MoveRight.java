package com.william.runingtext.movetype;

import com.william.runingtext.object.TextObject;

public class MoveRight extends MoveType{

	public MoveRight(TextObject textObject) {
		super(textObject);

	}
	
	@Override
	public void move(int width, int height, int span) {
		int textwidth = textObject.getTextWidth();
		int nowLocationX = textObject.getX();
		int newLocationX = nowLocationX+span;
		if(newLocationX >= width){
			newLocationX = 0-textwidth;
			callMoveEnd();
		}
		textObject.setX(newLocationX);
		
	}

	@Override
	public void reset(int width, int height, int span) {
		int textwidth = textObject.getTextWidth();
		textObject.setX(0-textwidth);
		int textheight = textObject.getTextHeight();
		textObject.setY(height/2-textheight/2);
	}

}
