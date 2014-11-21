package com.ziri.scene;

import org.andengine.entity.text.Text;

import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager.SceneType;

public class LoadingScene extends BaseScene {

	@Override
	public void createScene() {
		
		final Text bitmapTextLoading = new Text(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mBitmapFont, "Loading...", vbom);
		
		this.attachChild(bitmapTextLoading);
		
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene() {
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void populateScene() {
		
	}

	@Override
	public void onPauseScene() {
		
	}

	@Override
	public void onResumeScene() {
		
	}

}
