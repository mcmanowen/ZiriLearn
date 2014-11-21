package com.ziri.scene;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;

import com.ziri.base.BaseScene;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager.SceneType;

public class SplashScene extends BaseScene {
	
	//---------------------------------------------
	// PREFERENCES
	//---------------------------------------------
	
	String prefLocalPlayer,prefVisitorPlayer,prefLanguage;
	boolean prefMusic,prefGameSounds,prefLearnSounds;
	
	
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	int localLanguage;
	boolean soundsOrNot;
	
	Sprite icon;
	
	@Override
	public void createScene() {
		
		soundsOrNot = resourcesManager.initSounds();
		
		playIntroMusic();
		this.setBackground(new Background(1,1,1));
		icon = new Sprite(0,0,resourcesManager.splashTR,engine.getVertexBufferObjectManager());
        icon.setPosition(camera.getWidth()/2,camera.getHeight()/2);
        attachChild(icon);
        
        this.registerUpdateHandler(new TimerHandler(2f, true,new ITimerCallback(){
        	@Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                   playMusic();  
                   pTimerHandler.reset();
        	}
        }));
        
        
		
	}
	private void playIntroMusic(){
		try{				
			resourcesManager.mIntroMusic.play();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public void playMusic(){
		try{	
			
			if(!soundsOrNot){
			/*
			if(!ResourcesManager.mMusicFingerFamily.isPlaying()){	
				//resourcesManager.mMusicFingerFamily.release();
				ResourcesManager.mMusicFingerFamily.play();
			}
			*/
			if(!ResourcesManager.mMusicSeventies.isPlaying()){	
				//resourcesManager.mMusicFingerFamily.release();
				ResourcesManager.mMusicSeventies.play();
			}
			
			
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene() {
		
		icon.detachSelf();
		icon.dispose();
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void populateScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPauseScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResumeScene() {
		// TODO Auto-generated method stub
		
	}

}
