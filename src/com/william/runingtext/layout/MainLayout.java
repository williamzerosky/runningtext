package com.william.runingtext.layout;

import java.util.LinkedList;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.william.runingtext.interfaceview.IRelease;
import com.william.runingtext.interfaceview.MeasureView;
import com.william.runingtext.movetype.MoveLeft;
import com.william.runingtext.movetype.MoveType;
import com.william.runingtext.movetype.listener.MoveEndListener;
import com.william.runingtext.object.ScreenInfoObject;
import com.william.runingtext.view.RunningTextView;

public class MainLayout extends RelativeLayout implements MeasureView,IRelease{
	ScreenInfoObject sio;
	RunningTextView runningText;
	LinkedList<String> textList = new LinkedList<String>();
	int hello = 0;
	
	public void setNextString(){
		if(runningText==null){
			return;
		}
		String text = textList.pop();
		if(text==null){
			return;
		}
		runningText.setText(text);
		textList.add("hello "+(++hello));
	}
	
	public MainLayout(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		sio = new ScreenInfoObject(this);
		runningText = new RunningTextView(context);
		this.addView(runningText);
		MoveType moveType = new MoveLeft(runningText.getTextObject());
		moveType.setMoveEndListener(moveEndListener);
		runningText.setMoveType(moveType);
		runningText.setVisibility(View.GONE);
		
		textList.add("hello "+(++hello));
	}
	
	@Override
	public void initView(int width, int height) {
		RelativeLayout.LayoutParams rrl = new RelativeLayout.LayoutParams(
				width,
				(int)(300*height/1920)
				);
		runningText.setLayoutParams(rrl);
		runningText.setVisibility(View.VISIBLE);
		runningText.start();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		sio.setScreenInfo(widthMeasureSpec, heightMeasureSpec);	
	}

	@Override
	public void release() {
		for(int i=0;i<getChildCount();i++){
			View child = this.getChildAt(i);
			if(child!=null && child instanceof IRelease){
				((IRelease)child).release();
			}
		}
	}

	MoveEndListener moveEndListener = new MoveEndListener(){

		@Override
		public void moveEnd() {
			setNextString();
		}
		
	};



}
