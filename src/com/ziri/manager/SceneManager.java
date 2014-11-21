package com.ziri.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import android.os.AsyncTask;
import android.os.Build;


import com.ziri.scene.AlphabetGameScene;
import com.ziri.scene.AnimalGameScene;
import com.ziri.scene.AnimalLearnScene;
import com.ziri.scene.BallGame24Scene;
import com.ziri.scene.BallGame4Scene;
import com.ziri.scene.ColorGameScene;
import com.ziri.scene.ColorLearnScene;
import com.ziri.scene.FlagGameScene;
import com.ziri.scene.FlagLearnScene;
import com.ziri.scene.FoodGameScene;
import com.ziri.scene.FoodLearnScene;
import com.ziri.scene.LoadingScene;
import com.ziri.scene.MainMenu24Scene;
import com.ziri.scene.MainMenu4Scene;
import com.ziri.scene.MainMenuScene;
import com.ziri.scene.AlphabetLearnScene;
import com.ziri.scene.BallGameScene;
import com.ziri.scene.MusicinstrGameScene;
import com.ziri.scene.MusicinstrLearnScene;
import com.ziri.scene.NumberGameScene;
import com.ziri.scene.NumberLearnScene;
import com.ziri.scene.ProfileScene;
import com.ziri.scene.ShapeGameScene;
import com.ziri.scene.ShapeLearnScene;
import com.ziri.scene.SplashScene;
import com.ziri.scene.TransportGameScene;
import com.ziri.scene.TransportLearnScene;
import com.ziri.base.BaseScene;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;


public class SceneManager {

	
		//---------------------------------------------
		// SCENES
		//---------------------------------------------
		
		private BaseScene splashScene;
		private BaseScene loadingScene;
		private BaseScene profileScene;
		private BaseScene mainmenuScene;
		private BaseScene mainmenu24Scene;
		private BaseScene mainmenu4Scene;
		private BaseScene ballgameScene;
		private BaseScene ballgame24Scene;
		private BaseScene ballgame4Scene;
		private BaseScene alphabetgameScene;
		private BaseScene alphabetlearnScene;
		private BaseScene numbergameScene;
		private BaseScene numberlearnScene;
		private BaseScene animalgameScene;
		private BaseScene animallearnScene;
		private BaseScene colorgameScene;
		private BaseScene colorlearnScene;
		private BaseScene shapegameScene;
		private BaseScene shapelearnScene;
		private BaseScene objectgameScene;
		private BaseScene objectlearnScene;
		private BaseScene transportgameScene;
		private BaseScene transportlearnScene;
		private BaseScene foodgameScene;
		private BaseScene foodlearnScene;
		private BaseScene musicinstrgameScene;
		private BaseScene musicinstrlearnScene;
		private BaseScene soundsgameScene;
		private BaseScene soundslearnScene;
		private BaseScene flaggameScene;
		private BaseScene flaglearnScene;
		private BaseScene capitalgameScene;
		private BaseScene capitallearnScene;

		
		//---------------------------------------------
		// VARIABLES
		//---------------------------------------------
		
		private static final SceneManager INSTANCE = new SceneManager();
		
		private SceneType currentSceneType = SceneType.SCENE_SPLASH;
		
		private BaseScene currentScene;
		
		private Engine engine = ResourcesManager.getInstance().engine;
		
		public enum SceneType
		{
			SCENE_SPLASH,			
			SCENE_LOADING,
			SCENE_PROFILE,
			SCENE_MAINMENU,
			SCENE_MAINMENU24,
			SCENE_MAINMENU4,
			SCENE_BALLGAME,
			SCENE_BALLGAME24,
			SCENE_BALLGAME4,
			SCENE_ALPHABETGAME,
			SCENE_ALPHABETLEARN,
			SCENE_NUMBERGAME,
			SCENE_NUMBERLEARN,
			SCENE_ANIMALGAME,
			SCENE_ANIMALLEARN,
			SCENE_COLORGAME,
			SCENE_COLORLEARN,
			SCENE_SHAPEGAME,
			SCENE_SHAPELEARN,
			SCENE_OBJECTGAME,
			SCENE_OBJECTLEARN,
			SCENE_TRANSPORTGAME,
			SCENE_TRANSPORTLEARN,
			SCENE_FOODGAME,
			SCENE_FOODLEARN,
			SCENE_MUSICINSTRGAME,
			SCENE_MUSICINSTRLEARN,
			SCENE_SOUNDSGAME,
			SCENE_SOUNDSLEARN,
			SCENE_FLAGGAME,
			SCENE_FLAGLEARN,
			SCENE_CAPITALGAME,
			SCENE_CAPITALLEARN
			
		}
		
		//---------------------------------------------
		// CLASS LOGIC
		//---------------------------------------------
		
		public void setScene(BaseScene scene)
		{
			engine.setScene(scene);
			currentScene = scene;
			currentSceneType = scene.getSceneType();
		}
		
		public void setScene(SceneType sceneType)
		{
			switch (sceneType)
			{
				case SCENE_MAINMENU:
					setScene(mainmenuScene);
					break;
				case SCENE_MAINMENU24:
					setScene(mainmenu24Scene);
					break;
				case SCENE_MAINMENU4:
					setScene(mainmenu4Scene);
					break;
				case SCENE_SPLASH:
					setScene(splashScene);
					break;
				case SCENE_LOADING:
					setScene(loadingScene);
					break;
				case SCENE_PROFILE:
					setScene(profileScene);
					break;
				case SCENE_BALLGAME:
					setScene(ballgameScene);
					break;
				case SCENE_BALLGAME24:
					setScene(ballgame24Scene);
					break;
				case SCENE_BALLGAME4:
					setScene(ballgame4Scene);
					break;
				case SCENE_ALPHABETGAME:
					setScene(alphabetgameScene);
					break;
				case SCENE_ALPHABETLEARN:
					setScene(alphabetlearnScene);
					break;
				case SCENE_NUMBERGAME:
					setScene(numbergameScene);
					break;
				case SCENE_NUMBERLEARN:
					setScene(numberlearnScene);
					break;
				case SCENE_ANIMALGAME:
					setScene(animalgameScene);
					break;
				case SCENE_ANIMALLEARN:
					setScene(animallearnScene);
					break;
				case SCENE_COLORGAME:
					setScene(colorgameScene);
					break;
				case SCENE_COLORLEARN:
					setScene(colorlearnScene);
					break;
				case SCENE_SHAPEGAME:
					setScene(shapegameScene);
					break;
				case SCENE_SHAPELEARN:
					setScene(shapelearnScene);
					break;
				case SCENE_OBJECTGAME:
					setScene(objectgameScene);
					break;
				case SCENE_OBJECTLEARN:
					setScene(objectlearnScene);
					break;
				case SCENE_TRANSPORTGAME:
					setScene(transportgameScene);
					break;
				case SCENE_TRANSPORTLEARN:
					setScene(transportlearnScene);
					break;
				case SCENE_FOODGAME:
					setScene(foodgameScene);
					break;
				case SCENE_FOODLEARN:
					setScene(foodlearnScene);
					break;
				case SCENE_MUSICINSTRGAME:
					setScene(musicinstrgameScene);
					break;
				case SCENE_MUSICINSTRLEARN:
					setScene(musicinstrlearnScene);
					break;
				case SCENE_SOUNDSGAME:
					setScene(soundsgameScene);
					break;
				case SCENE_SOUNDSLEARN:
					setScene(soundslearnScene);
					break;
				case SCENE_FLAGGAME:
					setScene(flaggameScene);
					break;
				case SCENE_FLAGLEARN:
					setScene(flaglearnScene);
					break;
				case SCENE_CAPITALGAME:
					setScene(capitalgameScene);
					break;
				case SCENE_CAPITALLEARN:
					setScene(capitallearnScene);
					break;
									
				default:
					break;
			}
		}
		
	
		//---------------------------------------------
		// CREATE SCENES
		//---------------------------------------------		
		

		public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
		{
			ResourcesManager.getInstance().loadSplashResources();
			splashScene = new SplashScene();
			currentScene = splashScene;
			pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
		}
	
		private void disposeSplashScene()
		{
			ResourcesManager.getInstance().unloadSplashResources();
			splashScene.disposeScene();
			splashScene = null;
		}
		
		
		public void createProfileScene()
		{
			ResourcesManager.getInstance().loadProfileResources();
			profileScene = new ProfileScene();
			ResourcesManager.getInstance().loadLoadingResources();
			loadingScene = new LoadingScene();
	        SceneManager.getInstance().setScene(profileScene);
	        disposeSplashScene();
		}
		
		public void loadProfileScene(final Engine engine)
		{
			setScene(loadingScene);
			
			try{
			getCurrentScene().dispose();
			
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
    		
			engine.registerUpdateHandler(new TimerHandler(0.01f, new ITimerCallback() 
			{
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	engine.unregisterUpdateHandler(pTimerHandler);
	            	ResourcesManager.getInstance().loadProfileResources();
	            	profileScene = new ProfileScene();
	        		setScene(profileScene);
	            }
			}));
		}
		
		private void disposeProfileScene()
		{
			ResourcesManager.getInstance().unloadProfileResources();
			profileScene.disposeScene();
			profileScene = null;
		}
		/*
		public void createMainMenuScene()
		{
			ResourcesManager.getInstance().loadMainMenuResources();
			mainmenuScene = new MainMenuScene();
			ResourcesManager.getInstance().loadLoadingResources();
			loadingScene = new LoadingScene();
	        SceneManager.getInstance().setScene(mainmenuScene);
	        disposeProfileScene();
	        //disposeSplashScene();
	        
		}
		
		public void loadMainMenuScene(final Engine engine)
		{
			setScene(loadingScene);
			
			try{
			getCurrentScene().dispose();
			//ballgameScene.disposeScene();
			//-- to do dispose all other scenes
			
			//-----------------------------------
			ResourcesManager.getInstance().unloadAlphabetLearnResources();
			ResourcesManager.getInstance().unloadAlphabetGameResources();
			ResourcesManager.getInstance().unloadNumberLearnResources();
			ResourcesManager.getInstance().unloadNumberGameResources();
			ResourcesManager.getInstance().unloadBallGameResources();
			ResourcesManager.getInstance().unloadColorLearnResources();
			ResourcesManager.getInstance().unloadColorGameResources();
			ResourcesManager.getInstance().unloadShapeLearnResources();
			ResourcesManager.getInstance().unloadShapeGameResources();
			ResourcesManager.getInstance().unloadAnimalLearnResources();
			ResourcesManager.getInstance().unloadAnimalGameResources();
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			
			engine.registerUpdateHandler(new TimerHandler(0.01f, new ITimerCallback() 
			{
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	engine.unregisterUpdateHandler(pTimerHandler);
	            	ResourcesManager.getInstance().loadMainMenuResources();
	        		setScene(mainmenuScene);
	            }
			}));
		}
		*/
		public void loadMainMenuScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			ResourcesManager.getInstance().unloadProfileResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadMainMenuResources();
	        		mainmenuScene = new MainMenuScene();
	        		setScene(mainmenuScene);	        
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadMainMenuResources();
		            	mainmenuScene = new MainMenuScene();
		        		setScene(mainmenuScene);
		            }
				}));
			
			
			}
		}
		
		public void loadMainMenu24Scene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			ResourcesManager.getInstance().unloadProfileResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadMainMenu24Resources();
	        		mainmenu24Scene = new MainMenu24Scene();
	        		setScene(mainmenu24Scene);	        
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadMainMenu24Resources();
		            	mainmenu24Scene = new MainMenu24Scene();
		        		setScene(mainmenu24Scene);
		            }
				}));
			
			
			}
		}
		
		public void loadMainMenu4Scene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			ResourcesManager.getInstance().unloadProfileResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadMainMenu4Resources();
	        		mainmenu4Scene = new MainMenu4Scene();
	        		setScene(mainmenu4Scene);	        
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadMainMenu4Resources();
		            	mainmenu4Scene = new MainMenu4Scene();
		        		setScene(mainmenu4Scene);
		            }
				}));
			
			
			}
		}
		
		
		public void loadBallGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadBallGameResources();
	        		ballgameScene = new BallGameScene();
	        		setScene(ballgameScene);
	        		//ballgameScene.createScene();
	        		//ballgameScene.populateScene();
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadBallGameResources();
		        		ballgameScene = new BallGameScene();
		        		setScene(ballgameScene);
		        		//ballgameScene.createScene();
		        		//ballgameScene.populateScene();
		            }
				}));
			
			
			}
		}

		public void loadBallGame24Scene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadBallGame24Resources();
	        		ballgame24Scene = new BallGame24Scene();
	        		setScene(ballgame24Scene);
	        		//ballgameScene.createScene();
	        		//ballgameScene.populateScene();
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadBallGame24Resources();
		        		ballgame24Scene = new BallGame24Scene();
		        		setScene(ballgame24Scene);
		        		//ballgameScene.createScene();
		        		//ballgameScene.populateScene();
		            }
				}));
			
			
			}
			
		}
		
		public void loadBallGame4Scene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadBallGame4Resources();
	        		ballgame4Scene = new BallGame4Scene();
	        		setScene(ballgame4Scene);
	        		//ballgameScene.createScene();
	        		//ballgameScene.populateScene();
	        		
	        		return null;
			      }
			    }.execute();
			    
			}else{
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadBallGame4Resources();
		        		ballgame4Scene = new BallGame4Scene();
		        		setScene(ballgame4Scene);
		        		//ballgameScene.createScene();
		        		//ballgameScene.populateScene();
		            }
				}));
			
			
			}
		
			
		}
		
		
		public void loadAlphabetLearnScene(final Engine engine)
		{
			
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
				new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadAlphabetLearnResources();
	        		alphabetlearnScene = new AlphabetLearnScene();
	        		setScene(alphabetlearnScene);

	        		return null;
			      }
			    }.execute();
				
				
			}else{
			
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	engine.unregisterUpdateHandler(pTimerHandler);
	            	ResourcesManager.getInstance().loadAlphabetLearnResources();
	        		alphabetlearnScene = new AlphabetLearnScene();
	        		setScene(alphabetlearnScene);
	            }
			}));
				
			}
    
    		
		}
		
		public void loadAlphabetGameScene(final Engine engine)
		{
		
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			
				new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			    	  
	            	ResourcesManager.getInstance().loadAlphabetGameResources();
	        		alphabetgameScene = new AlphabetGameScene();
	        		setScene(alphabetgameScene);

	        		return null;
			      }
			    }.execute();
				
				
			}else{
			
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	engine.unregisterUpdateHandler(pTimerHandler);
	            	ResourcesManager.getInstance().loadAlphabetGameResources();
	        		alphabetgameScene = new AlphabetGameScene();
	        		setScene(alphabetgameScene);
	            }
			}));
				
			}
    
			
		}
		
		public void loadNumberLearnScene(final Engine engine)
		{
			
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadNumberLearnResources();
	        		numberlearnScene = new NumberLearnScene();
	        		setScene(numberlearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadNumberLearnResources();
		        		numberlearnScene = new NumberLearnScene();
		        		setScene(numberlearnScene);
		            }
				}));
			}
		}
		
		public void loadNumberGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadNumberGameResources();
	        		numbergameScene = new NumberGameScene();
	        		setScene(numbergameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadNumberGameResources();
		        		numbergameScene = new NumberGameScene();
		        		setScene(numbergameScene);
		            }
				}));
			}
		}
		
		
		public void loadAnimalLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			/*
			try{
			new startAnimalScene().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			*/
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
		
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			        //long timestamp = System.currentTimeMillis();
			        // TODO later load common resources here
			        ResourcesManager.getInstance().loadAnimalLearnResources();
			        animallearnScene = new AnimalLearnScene();
			        
			        
			        //animallearnScene.createScene();
	        		//animallearnScene.populateScene();
	        		setScene(animallearnScene);
	        		
			        // we want to show the splash at least SPLASH_DURATION miliseconds
			        
			      //  long elapsed = System.currentTimeMillis() - timestamp;
			      //  if (elapsed < 3) {
			      //    try {
			      //      Thread.sleep(3 - elapsed);
			      //    } catch (InterruptedException e) {
			           // Debug.w("This should not happen");
			      //    }
			      //  }
			        
			        
		    		//loadingScene.dispose();
		    		//ResourcesManager.getInstance().unloadLoadingResources();
			        return null;
			      }
			    }.execute();
			   
			}else{
				
				
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadAnimalLearnResources();
		        		animallearnScene = new AnimalLearnScene();
		        		setScene(animallearnScene);
		        		//animallearnScene.createScene();
		        		//animallearnScene.populateScene();
		        		
		            }
				}));
				
			}
			   
			
		}
		
		
		
		
		
		
		public void loadAnimalGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenu24Resources();
			unloadMenuResources();
			/*
			try{
			new startAnimalScene().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			*/
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
		
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			        //long timestamp = System.currentTimeMillis();
			        // TODO later load common resources here
			        ResourcesManager.getInstance().loadAnimalGameResources();
			        animalgameScene = new AnimalGameScene();
			        
			        
			        //animallearnScene.createScene();
	        		//animallearnScene.populateScene();
	        		setScene(animalgameScene);
	        		
			        // we want to show the splash at least SPLASH_DURATION miliseconds
			        
			      //  long elapsed = System.currentTimeMillis() - timestamp;
			      //  if (elapsed < 3) {
			      //    try {
			      //      Thread.sleep(3 - elapsed);
			      //    } catch (InterruptedException e) {
			           // Debug.w("This should not happen");
			      //    }
			      //  }
			        
			        
		    		//loadingScene.dispose();
		    		//ResourcesManager.getInstance().unloadLoadingResources();
			        return null;
			      }
			    }.execute();
			   
			}else{
				
				
				engine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadAnimalGameResources();
		        		animalgameScene = new AnimalGameScene();
		        		setScene(animalgameScene);
		        		//animallearnScene.createScene();
		        		//animallearnScene.populateScene();
		        		
		            }
				}));
				
			}
			   
			
		}
		
		
		public void loadColorLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadColorLearnResources();
	        		colorlearnScene = new ColorLearnScene();
	        		setScene(colorlearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadColorLearnResources();
		        		colorlearnScene = new ColorLearnScene();
		        		setScene(colorlearnScene);
		            }
				}));
			}
			
		}
		
		public void loadColorGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadColorGameResources();
	        		colorgameScene = new ColorGameScene();
	        		setScene(colorgameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadColorGameResources();
		        		colorgameScene = new ColorGameScene();
		        		setScene(colorgameScene);
		            }
				}));
			}
		}
		
		public void loadShapeLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadShapeLearnResources();
	        		shapelearnScene = new ShapeLearnScene();
	        		setScene(shapelearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadShapeLearnResources();
		        		shapelearnScene = new ShapeLearnScene();
		        		setScene(shapelearnScene);
		            }
				}));
			}
		}
		
	
		public void loadShapeGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadShapeGameResources();
	        		shapegameScene = new ShapeGameScene();
	        		setScene(shapegameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadShapeGameResources();
		        		shapegameScene = new ShapeGameScene();
		        		setScene(shapegameScene);
		            }
				}));
			}
		}
		
		public void loadObjectLearnScene(final Engine engine)
		{
			
		}
		
	
		public void loadObjectGameScene(final Engine engine)
		{
			
		}
		
		public void loadTransportLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadTransportLearnResources();
	        		transportlearnScene = new TransportLearnScene();
	        		setScene(transportlearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadTransportLearnResources();
		        		transportlearnScene = new TransportLearnScene();
		        		setScene(transportlearnScene);
		            }
				}));
			}
		}
		
	
		public void loadTransportGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenu4Resources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadTransportGameResources();
	        		transportgameScene = new TransportGameScene();
	        		setScene(transportgameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadTransportGameResources();
		        		transportgameScene = new TransportGameScene();
		        		setScene(transportgameScene);
		            }
				}));
			}
		}
		
		public void loadFoodLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadFoodLearnResources();
	        		foodlearnScene = new FoodLearnScene();
	        		setScene(foodlearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadFoodLearnResources();
		        		foodlearnScene = new FoodLearnScene();
		        		setScene(foodlearnScene);
		            }
				}));
			}	
		}
		
	
		public void loadFoodGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenu4Resources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadFoodGameResources();
	        		foodgameScene = new FoodGameScene();
	        		setScene(foodgameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadFoodGameResources();
		        		foodgameScene = new FoodGameScene();
		        		setScene(foodgameScene);
		            }
				}));
			}	
		}
		
		public void loadMusicinstrLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadMusicinstrLearnResources();
	        		musicinstrlearnScene = new MusicinstrLearnScene();
	        		setScene(musicinstrlearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadMusicinstrLearnResources();
		        		musicinstrlearnScene = new MusicinstrLearnScene();
		        		setScene(musicinstrlearnScene);
		            }
				}));
			}
		}
		
	
		public void loadMusicinstrGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenu4Resources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadMusicinstrGameResources();
	        		musicinstrgameScene = new MusicinstrGameScene();
	        		setScene(musicinstrgameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadMusicinstrGameResources();
		        		musicinstrgameScene = new MusicinstrGameScene();
		        		setScene(musicinstrgameScene);
		            }
				}));
			}
		}
		
		public void loadSoundsLearnScene(final Engine engine)
		{
			
		}
		
	
		public void loadSoundsGameScene(final Engine engine)
		{
			
		}
		
		public void loadFlagLearnScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenuResources();
			unloadMenuResources();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadFlagLearnResources();
	        		flaglearnScene = new FlagLearnScene();
	        		setScene(flaglearnScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadFlagLearnResources();
		        		flaglearnScene = new FlagLearnScene();
		        		setScene(flaglearnScene);
		            }
				}));
			}	
		}
		
	
		public void loadFlagGameScene(final Engine engine)
		{
			ResourcesManager.getInstance().loadLoadingResources();
			LoadingScene loadingScene = new LoadingScene();
			setScene(loadingScene);
			//loadingScene.createScene();
			//ResourcesManager.getInstance().unloadMainMenu4Resources();
			unloadMenuResources();
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				
			
			new AsyncTask<Void, Void, Void>() {

			      @Override
			      protected Void doInBackground(Void... params) {
			
	            	ResourcesManager.getInstance().loadFlagGameResources();
	        		flaggameScene = new FlagGameScene();
	        		setScene(flaggameScene);
	        		
	        		return null;
			      }
			    }.execute();
			}else{
				engine.registerUpdateHandler(new TimerHandler(0.2f, new ITimerCallback() 
				{
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		            	engine.unregisterUpdateHandler(pTimerHandler);
		            	ResourcesManager.getInstance().loadFlagGameResources();
		        		flaggameScene = new FlagGameScene();
		        		setScene(flaggameScene);
		            }
				}));
			}	
		}
		
		public void loadCapitalLearnScene(final Engine engine)
		{
			
		}
		
	
		public void loadCapitalGameScene(final Engine engine)
		{
			
		}
		
		
		
		private void unloadMenuResources(){
			
			/*
			int idmenu=resourcesManager.getIdMenu();
			
			if (idmenu==10 || idmenu==15){
				ResourcesManager.getInstance().unloadMainMenuResources();
			}else if (idmenu==20 || idmenu==25){
				ResourcesManager.getInstance().unloadMainMenu24Resources();
			}else if (idmenu==30 || idmenu==35){
				ResourcesManager.getInstance().unloadMainMenu4Resources();
			}else{}
			*/
			
			try{
				ResourcesManager.getInstance().unloadMainMenuResources();			
				ResourcesManager.getInstance().unloadMainMenu24Resources();			
				ResourcesManager.getInstance().unloadMainMenu4Resources();				
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		
		
		//---------------------------------------------
		// GETTERS AND SETTERS
		//---------------------------------------------

		
		public static SceneManager getInstance()
		{
			return INSTANCE;
		}
		
		public SceneType getCurrentSceneType()
		{
			return currentSceneType;
		}
		
		public BaseScene getCurrentScene()
		{
			return currentScene;
		}
		
}
