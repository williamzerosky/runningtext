package com.william.runingtext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.william.runingtext.exception.TextNotFoundException;
import com.william.runingtext.interfaceview.IRelease;
import com.william.runingtext.interfaceview.MeasureView;
import com.william.runingtext.movetype.MoveLeft;
import com.william.runingtext.movetype.MoveType;
import com.william.runingtext.object.ScreenInfoObject;
import com.william.runingtext.object.TextObject;

public class RunningTextView extends View implements MeasureView,IRelease{
	private String TAG = RunningTextView.class.getSimpleName();
	public final static int MOVE_INVALIDATE = 0;
	public final static int MOVE_END = 1;
	ScreenInfoObject sio;
	TextObject textObject = new TextObject();
	String text ="Hello World !!";
	int span = 30; // move span per frame
	int fps = 10; // frame per second
	int sec = 1000; //second millis
	MoveType moveType;
	boolean startFlag = false;
	boolean isPause = false;
	Thread runningThread;
	
	public RunningTextView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		sio = new ScreenInfoObject(this);
		try{
			textObject.setColor(Color.BLACK);
			textObject.setSize(30);
			textObject.setFackBold(true);
		}catch(NullPointerException e){
			
		}
		setText(text);
		
		runningThread = new Thread(runMove);
	}
	
	public void setText(String t){
		if(t==null){
			t = "";
		}
		this.text = t;
		textObject.setText(t);
		try{
			textObject.init();
		}catch(TextNotFoundException e){
			
		}catch(NullPointerException e){
			
		}
		if(moveType!=null){
			moveType.reset(sio.width, sio.height, span);
		}
	}
	
	public TextObject getTextObject(){
		return textObject;
	}
	
	public String getText(){
		return text;
	}
	
	public void setMoveType(MoveType m){
		this.moveType = m;
	}

	@Override
	public void initView(int width, int height) {
		textObject.setX(width);
		textObject.setY(height/2-textObject.getTextHeight()/2);
		if(moveType!=null){
			moveType.reset(width, height, span);
		}
	}
	
	public void resetLocation(){
		textObject.setX(sio.width);
	}
	
	public void moveLocation(){

		if(moveType!=null){
			moveType.move(sio.width, sio.height, span);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		sio.setScreenInfo(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void release() {
		textObject = null;
		if(runningThread!=null){
			startFlag = false;
			runningThread.interrupt();
			runningThread = null;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		try{
			textObject.draw(canvas);
		}catch(NullPointerException e){}
	}
	
	public void start(){
		if(!startFlag){
			startFlag = true;
			if(runningThread==null){
				runningThread = new Thread(runMove);
			}
			runningThread.start();
		}
	}
	
	public void pause(){
		isPause = true;
	}
	
	public void resume(){
		isPause = false;
	}
	
	public void stop(){
		startFlag = false;
	}
	
	Runnable runMove = new Runnable(){

		@Override
		public void run() {
			while(startFlag){
				try{
					try{
						long startTime = System.currentTimeMillis();
						if(!isPause){
							moveLocation();
							postInvalidate();
						}
						long stopTime = System.currentTimeMillis();
						long disTime = stopTime - startTime;
						int disSpan = (int) ((fps/sec)-disTime);
						if(disSpan>0){
							Thread.sleep(disSpan);
						}
						Thread.sleep(100);
					}finally{
						
					}
				}catch(Exception e){
					startFlag = false;
				}
			}
		}
		
	};
	

}
