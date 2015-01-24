package com.william.runingtext.object;

import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.william.runingtext.exception.TextNotFoundException;

public class TextObject {
	boolean needCreateStaticLayout = true;
	StaticLayout sl;
	int[] locations = new int[]{0,0};
	TextPaint paintText = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
	String text = "";
	
	public TextObject(){
		
	}
	
	public void setColor(int color) throws NullPointerException{
		paintText.setColor(color);
	}
	
	public void setSize(float size) throws NullPointerException{
		paintText.setTextSize(size);
	}
	
	public void setFackBold(boolean f) throws NullPointerException{
		paintText.setFakeBoldText(f);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public int getTextWidth(){
		if(paintText==null){
			return 0;
		}
		return (int)paintText.measureText(text);
	}
	
	public int getTextHeight(){
		if(sl==null){
			return 0;
		}
		return sl.getHeight();
	}
	
	public void setStaticLayout(StaticLayout l){
		sl = l;
		needCreateStaticLayout = false;
	}
	
	public TextObject setX(int x){
		locations[0] = x;
		return this;
	}
	
	public TextObject setY(int y){
		locations[1] = y;
		return this;
	}
	
	public int getX(){
		return locations[0];
	}
	
	public int getY(){
		return locations[1];
	}
	
	public void init() throws TextNotFoundException,NullPointerException{
		if(text==null){
			throw new TextNotFoundException("text not found!");
		}
		if(paintText==null){
			throw new NullPointerException("paint is null");
		}
		if(needCreateStaticLayout){
			sl = new StaticLayout(text,paintText,(int)paintText.measureText(text),Layout.Alignment.ALIGN_NORMAL,1,0,false);
		}else{
			if(sl==null){
				throw new TextNotFoundException("staticlayout is null");
			}
		}
		
	}
	
	public void draw(Canvas canvas){
		if(canvas==null){
			Log.e(TextObject.class.getSimpleName(), "draw text canvas null");
			return;
		}
		if(!checkCanDraw()){
			Log.e(TextObject.class.getSimpleName(), "draw text can't draw");
			return;
		}
		try{
			try{
				canvas.save();
				canvas.translate(locations[0], locations[1]);
				sl.draw(canvas);
			}finally{
				canvas.restore();
			}
		}catch(Exception e){
			
		}
	}
	
	public boolean checkCanDraw(){
		if(sl==null || text==null){
			return false;
		}
		return true;
	}
	
	
}
