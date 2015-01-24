package com.william.runingtext;

import android.app.Activity;
import android.os.Bundle;

import com.william.runingtext.layout.MainLayout;

public class MainActivity extends Activity {

	MainLayout ml;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ml = new MainLayout(this);
        setContentView(ml);
        
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	ml.release();
    }
}
