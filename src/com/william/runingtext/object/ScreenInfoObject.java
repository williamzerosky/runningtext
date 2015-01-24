package com.william.runingtext.object;

import com.william.runingtext.interfaceview.MeasureView;

import android.view.View;
import android.view.View.MeasureSpec;

public class ScreenInfoObject {
	MeasureView view;
	public ScreenInfoObject(MeasureView view){
		this.view = view;
	}
	
	public interface ScreenInfoChangeCallback{
		void onInfoChanged(int width,int height);
	}
	public int width = 0;
	public int height = 0;
	ScreenInfoChangeCallback infoChangeObbserver = new ScreenInfoChangeCallback() {
		
		@Override
		public void onInfoChanged(int width, int height) {
			if(view!=null){
				view.initView(width, height);
			}
		}
	};
	
	public void setScreenInfo(int widthMeasureSpec,int heightMeasureSpec){
		int tempWidth = MeasureSpec.getSize(widthMeasureSpec);
		int tempHeight = MeasureSpec.getSize(heightMeasureSpec);
		if(width!= tempWidth || height != tempHeight){
			width = tempWidth;
			height = tempHeight;
			if(infoChangeObbserver!=null){
				infoChangeObbserver.onInfoChanged(width, height);
			}
		}
	}
}
