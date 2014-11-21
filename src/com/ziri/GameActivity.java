package com.ziri;


import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;

import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.ziri.manager.Preferences;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;
import com.google.android.gms.ads.AdSize;


public class GameActivity extends BaseGameActivity {
	//-- advertisements
	FrameLayout frameLayout;
	private AdView adView;
	AdRequest adRequest;
	private static final String AD_UNIT_ID = "ca-app-pub-9350701062949422/3961944992";
	private static final String AD_UNIT_ID_interstitial = "ca-app-pub-9350701062949422/7198052191";
	public InterstitialAd interstitial;
	AdRequest adRequestinterstitial;
    private long lastTimeAdShown=System.currentTimeMillis();
    private long lastTimeAdFail=System.currentTimeMillis();
	//----------------------------------------------------------------------------------
	
	SharedPreferences mPrefs;
    Camera camera;
    protected final static int CAMERA_WIDTH = 800;
    protected final static int CAMERA_HEIGHT = 480;
    
    EngineOptions options;
	/*
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) 
	{
		return new LimitedFPSEngine(pEngineOptions, 60);
	}
	*/
   
    

	@Override
	public EngineOptions onCreateEngineOptions() {
		
        camera = new Camera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
        options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,new RatioResolutionPolicy(CAMERA_WIDTH,CAMERA_HEIGHT),camera);
        options.getRenderOptions().setDithering(true);
        options.getAudioOptions().setNeedsSound(true);
        options.getAudioOptions().setNeedsMusic(true);
        //options.getRenderOptions().setMultiSampling(true);
        options.getTouchOptions().setNeedsMultiTouch(true);
        options.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        
        /*
		if(MultiTouch.isSupported(this)) {
			if(MultiTouch.isSupportedDistinct(this)) {
				Toast.makeText(this, "MultiTouch detected --> Both controls will work properly!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "MultiTouch detected, but your device has problems distinguishing between fingers.\n\nControls are placed at different vertical locations.", Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(this, "Sorry your device does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)\n\nControls are placed at different vertical locations.", Toast.LENGTH_LONG).show();
		}
		*/
        
        return options;
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
	    {
	    	
	    	if (SceneManager.getInstance().getCurrentScene().getSceneType().toString()=="SCENE_MAINMENU"){
	    		SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    		//System.exit(0);
	    	}
	    	else {
	    		SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    	}

	    	dimSoftButtonsIfPossible();
	    }
	    
	    
	    else if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
			
	    	if (SceneManager.getInstance().getCurrentScene().getSceneType().toString()=="SCENE_MAINMENU"){
	    		
	    		openPrefs();
	    		
	    	}
	    	else if (SceneManager.getInstance().getCurrentScene().getSceneType().toString()=="SCENE_BALLGAME"){
	    		//-- TODO a enlever mettre pause au nivea de la page
	    		openPrefs();
	    		//SceneManager.getInstance().getCurrentScene().onPauseScene();
	    	}
	    	else {
	    		SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    	}
	    	
	    	dimSoftButtonsIfPossible();
			
		} else if(keyCode == KeyEvent.KEYCODE_HOME && event.getAction() == KeyEvent.ACTION_DOWN) {
			
			//-- TODO set scene exit or not
			/*if (SceneManager.getInstance().getCurrentScene().getSceneType().toString()=="SCENE_BALLGAME"){
	    		SceneManager.getInstance().getCurrentScene().onPauseScene();
	    	}*/
			
			this.finish();
			//return true;
		}
		
		else {
			return super.onKeyDown(keyCode, event);
		}
	    
	    return false; 
	}
	
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback) {
		ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());

		pOnCreateResourcesCallback.onCreateResourcesFinished();
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) {
	
		
		mEngine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
		{
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
            	mEngine.unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().createProfileScene();
            }
		}));
		
		runOnUiThread(new Runnable() {
		      
		      @Override
		      public void run() {
		        dimSoftButtonsIfPossible();
		      }
		    });
		
		 pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	@SuppressLint("NewApi")
	  private void dimSoftButtonsIfPossible() {
	    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	    if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
	      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
	    } 
	  }
	
	@Override
	protected void onDestroy()
		{
		if (adView != null) {
		      adView.destroy();
		   }
		super.onDestroy();
		System.exit(0);	
		}	
	@Override
	protected synchronized void onResume() {
			super.onResume();
			//System.gc();
			try{
				if (adView != null) {
				      adView.resume();
				    }
				if (this.isGameLoaded()){
					SceneManager.getInstance().getCurrentScene().onResumeScene();
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			

			
			runOnUiThread(new Runnable() {
			      
			      @Override
			      public void run() {
			    	
			        dimSoftButtonsIfPossible();
			      }
			    });
		}


	@Override
	protected void onPause() {
			super.onPause();
			
			try{
			
			if (adView != null) {
				      adView.pause();
			}
			if (this.isGameLoaded()){
				SceneManager.getInstance().getCurrentScene().onPauseScene();
			}
	
		}catch(Exception ex){
				ex.printStackTrace();
			}
	
		}
	
	
	//-- preferences and options --------------------------------------------------------------
	
	public void openPrefs(){
		Intent myIntent = new Intent(GameActivity.this, Preferences.class);
		startActivity(myIntent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}
	public String getPreference(final String preferenceName, final String defaultValue) {
		return mPrefs.getString(preferenceName, defaultValue);
	}
	public void setStringPreferences(final String preferenceName, final String preferenceValue){
	
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(preferenceName, preferenceValue).apply();
		//editor.commit();
		//return true;
	}
	public void setBooleanPreferences(final String preferenceName, final Boolean preferenceValue){
		
		//SharedPreferences settings = getSharedPreferences(PreferenceManager.getDefaultSharedPreferences(this).toString(), MODE_PRIVATE);
		//SharedPreferences settings = getSharedPreferences("prefs.xml", MODE_PRIVATE);
		
		SharedPreferences.Editor editor = mPrefs.edit();		
		editor.putBoolean(preferenceName, preferenceValue).apply();
		//editor.commit();
		//return true;
	}
	
	//-----------------------------------------------------------------------------------------


	public boolean getPreference(String preferenceName, boolean b) {
		return mPrefs.getBoolean(preferenceName, b);
	}
	
	 @Override
	    protected void onSetContentView() {
		 	
	        final RelativeLayout relativeLayout = new RelativeLayout(this);
	        final FrameLayout.LayoutParams relativeLayoutLayoutParams = new FrameLayout.LayoutParams(
	                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.ALIGN_BOTTOM);
	        
	        this.mRenderSurfaceView = new RenderSurfaceView(this);
	        this.mRenderSurfaceView.setRenderer(this.mEngine, this);


	        final android.widget.RelativeLayout.LayoutParams surfaceViewLayoutParams = new RelativeLayout.LayoutParams(BaseGameActivity.createSurfaceViewLayoutParams());
	        surfaceViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
	        surfaceViewLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
	      
	        relativeLayout.addView(this.mRenderSurfaceView, surfaceViewLayoutParams);


	        frameLayout = new FrameLayout(this);
	       
	        //-- interstitial ads -------------------------------------------------------------------------
	        adView = new AdView(this);
	        adView.setAdSize(AdSize.SMART_BANNER);
	        adView.setAdUnitId(AD_UNIT_ID);
	        adView.refreshDrawableState();
	        frameLayout.addView(adView);
	      
	        relativeLayout.addView(frameLayout);
	        
			/*
	        AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB")
	        .build();
	        */
	        AdRequest adRequest = new AdRequest.Builder().build();
	        
	        // Start loading the ad in the background.
	        //for trial version	        
	        adView.loadAd(adRequest);
	        //----------------------------------------------------------------------------------------------
    
	        
	        //-- interstitial ads -------------------------------------------------------------------------
	        
	        adRequestinterstitial = new AdRequest.Builder().build();
	        //AdRequest adRequestinterstitial = new AdRequest.Builder().build();
	        interstitial = new InterstitialAd(this);
	        interstitial.setAdUnitId(AD_UNIT_ID_interstitial);
	        interstitial.loadAd(adRequestinterstitial);
	        
	        
	        interstitial.setAdListener(new AdListener() {
	        	@Override
	            public void onAdLoaded() {

	            }

	            @Override
	            public void onAdFailedToLoad(int errorCode) {
	            	long timestamp = System.currentTimeMillis();
	                if(timestamp > lastTimeAdFail + 120*1000)
	                {
	                	try{
	                	adRequestinterstitial = new AdRequest.Builder().build();	                  
	          	  		interstitial.setAdUnitId(AD_UNIT_ID_interstitial);
	          	  		interstitial.loadAd(adRequestinterstitial);
	                	}catch(Exception ex){ex.printStackTrace();}
	                }

	            }

	            @Override
	            public void onAdClosed () 
	            {
	            		try{
	            		adRequestinterstitial = new AdRequest.Builder().build();
	            		interstitial.setAdUnitId(AD_UNIT_ID_interstitial);   	        
	            		interstitial.loadAd(adRequestinterstitial);
	            		}catch(Exception ex){ex.printStackTrace();}
	            }
	          });
	        //----------------------------------------------------------------------------------------------

	        this.setContentView(relativeLayout, relativeLayoutLayoutParams);

	 }
   	 public void displayInterstitial() {
   		  
   		 if (interstitial.isLoaded()) {
		      interstitial.show();
		    }
  
		  } 
   	public void hideAd() {
   		adView.setVisibility(View.GONE);
   	}
   	public void showAd() {

   		adView.setVisibility(View.VISIBLE);
   	}
   	
   	public void getFullVersion(){
   		if (isPlayStoreInstalled()){
   		try{
   		Intent intent = new Intent(Intent.ACTION_VIEW);
   		intent.setData(Uri.parse("market://details?id=com.ziri"));
   		startActivity(intent);
   		}catch(Exception ex){
   			ex.printStackTrace();
   		}
   	}else{
   		
   	}
   		
   	}
   	
	private Boolean isPlayStoreInstalled() {
		PackageManager pacman = this.getPackageManager();
		try {
			pacman.getApplicationInfo("com.android.vending", 0);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}
}
