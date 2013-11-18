package com.naxa.cleancity;

import android.os.Bundle; 
import android.view.Gravity; 
import android.view.View; 
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.google.android.maps.MapActivity; 
import com.google.android.maps.MapController;
import com.google.android.maps.MapView; 
import com.google.android.maps.MyLocationOverlay; 

public class MainActivity extends MapActivity{ 
	private FrameLayout frame; 
	private MapView map; 
	private MapController controller;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initMapView();
		initZoomControls();
		initMyLocation();
	}

	@Override
	protected boolean isRouteDisplayed(){
		return false;
	}
	
	private void initMapView(){
		frame = (FrameLayout) findViewById(R.id.frame);
		map = (MapView) findViewById(R.id.map);
		controller = map.getController();
		map.setSatellite(true);
	}
	
	private void initZoomControls(){ 
		View zoomControls=map.getZoomControls(); 
		FrameLayout.LayoutParams p=new FrameLayout.LayoutParams( 
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 
				Gravity.BOTTOM+Gravity.CENTER_HORIZONTAL); 
		frame.addView(zoomControls,p);
	}
	
	private void initMyLocation(){
		final MyLocationOverlay overlay = new MyLocationOverlay(this,map);
		overlay.enableMyLocation();
		overlay.enableCompass();
		overlay.runOnFirstFix(new Runnable(){
			public void run(){
				controller.setZoom(8);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		map.getOverlays().add(overlay);
	}
}
