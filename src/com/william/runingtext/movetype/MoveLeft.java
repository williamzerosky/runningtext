package com.william.runingtext.movetype;

import com.william.runingtext.object.TextObject;

public class MoveLeft extends MoveType{

	public MoveLeft(TextObject textObject) {
		super(textObject);

	}

	@Override
	public void move(int width, int height, int span) {
		int textwidth = textObject.getTextWidth();
		int nowLocationX = textObject.getX();
		int newLocationX = nowLocationX-span;
		if(newLocationX <= -textwidth){
			newLocationX = width;
			callMoveEnd();
		}
		textObject.setX(newLocationX);
		
	}

	@Override
	public void reset(int width, int height, int span) {
		int textheight = textObject.getTextHeight();
		textObject.setX(width);
		textObject.setY(height/2-textheight/2);
	}
}
