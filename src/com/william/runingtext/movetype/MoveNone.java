package com.william.runingtext.movetype;

import com.william.runingtext.object.TextObject;

public class MoveNone extends MoveType{

	public MoveNone(TextObject textObject) {
		super(textObject);
		
	}

	@Override
	public void move(int width, int height, int span) {
		
		
	}

	@Override
	public void reset(int width, int height, int span) {
		textObject.setX(width/2-textObject.getTextWidth()/2);
		textObject.setY(height/2-textObject.getTextHeight()/2);
	}

}
