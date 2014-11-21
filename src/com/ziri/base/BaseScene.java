package com.ziri.base;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.ziri.manager.ResourcesManager;

import android.app.Activity;
import com.ziri.manager.SceneManager.SceneType;

public abstract class BaseScene extends Scene
{
	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	
	protected Engine engine;
	protected Activity activity;
	protected ResourcesManager resourcesManager;
	protected VertexBufferObjectManager vbom;
	protected Camera camera;
	
	//---------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------
	
	public BaseScene()
	{
		this.resourcesManager = ResourcesManager.getInstance();
		this.engine = resourcesManager.engine;
		this.activity = resourcesManager.activity;
		this.vbom = resourcesManager.vbom;
		this.camera = resourcesManager.camera;
		createScene();
		populateScene();
	}
	
	//---------------------------------------------
	// ABSTRACTION
	//---------------------------------------------
	
	public abstract void createScene();
	
	public abstract void populateScene();
	
	public abstract void onBackKeyPressed();
	
	public abstract SceneType getSceneType();
	
	public abstract void disposeScene();
	
	public abstract void onPauseScene();
	
	public abstract void onResumeScene();	

}