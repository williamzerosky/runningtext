package com.william.runingtext.movetype;

import android.os.Handler;

import com.william.runingtext.movetype.listener.MoveEndListener;
import com.william.runingtext.object.TextObject;

public abstract class MoveType {
	TextObject textObject;
	MoveEndListener moveEndListener;
	public void setMoveEndListener(MoveEndListener l){
		this.moveEndListener = l;
	}
	
	public void callMoveEnd(){
		if(moveEndListener!=null){
			moveEndListener.moveEnd();
		}
	}
	
	public MoveType(TextObject textObject){
		this.textObject = textObject;
	}
	public abstract void move(int width,int height,int span);
	public abstract void reset(int width,int height,int span);
}
