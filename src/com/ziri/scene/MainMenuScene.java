package com.ziri.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;


import com.ziri.GameActivity;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {

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
		
	    //--texts
	    static final String[] textsFr = {"0-2 ans : leçons","0-2 ans : jeux","Options","Profil","Jeux","Leçons","Alphabets", "Chiffres","Couleurs","ziritap"};
	    //static final String[] textsFrRead = {"Choisissez Un Profile SVP !","0-2 ans","2-4 ans", "+4 ans"};
	    static final String[] textsEn = {"0-2 years : learn","0-2 years : games","Options","Profile","Games","Learn","Alphabets", "Numbers","Colors","ziritap"};
		
		private int learnOrPlay = 0;
	
		//-- exit scene variables
		private CameraScene exitScene;
		private Text quitOrNotText;
		private HUD quitgameHUD;
		//---------------------------------------------
		
		private MenuScene menuChildScene;
		private MenuScene menuPlayChildScene;

		private final int MENU_ALPHABETLEARN = 0;
		private final int MENU_NUMBERLEARN = 1;
		private final int MENU_BALLGAME = 2;
		private final int MENU_ANIMALLEARN = 3;
		private final int MENU_MUSICALINSTLEARN = 4;
		private final int MENU_COLORLEARN = 5;
		private final int MENU_SHAPELEARN = 6;
		private final int MENU_FLAGLEARN = 7;
		private final int MENU_FOODLEARN = 8;	
		
		private final int MENU_ALPHABETPLAY = 9;
		private final int MENU_NUMBERPLAY = 10;
		private final int MENU_ANIMALPLAY = 11;
		private final int MENU_MUSICALINSTPLAY = 12;
		private final int MENU_COLORPLAY = 13;
		private final int MENU_SHAPEPLAY = 14;
		private final int MENU_FLAGPLAY = 15;
		private final int MENU_FOODPLAY = 16;	
		
		private final int MENU_OPTIONS = 17;
		private final int MENU_PROFILE = 18;
		private final int MENU_PLAY = 19;
		
		//---------------------------------------------
		// PROFILES
		//---------------------------------------------
		private final int PROFILE02 = 0;
		private final int PROFILE24 = 1;
		private final int PROFILE4 = 2;
		
		private int localIdProfile; 
		
		static final float[] profile02TextPos = {150,330,150,172,150,16,250,330,250,172,250,16};
		static final float[] profile24TextPos = {100,330,100,172,100,16,280,330,280,172,280,16,460,330,460,172,460,16,670,330,670,172,670,16};
		static final float[] profile4TextPos = {100,330,100,172,100,16,280,330,280,172,280,16,460,330,460,172,460,16,670,330,670,172,670,16};
		
		static final float[] profile02IconPos = {150,408,150,250,150,92,250,408,250,250,250,92};
		static final float[] profile24IconPos = {100,408,100,250,100,92,280,408,280,250,280,92,460,408,460,250,460,92,670,408,670,250,670,92};
		static final float[] profile4IconPos = {100,408,100,250,100,92,280,408,280,250,280,92,460,408,460,250,460,92,670,408,670,250,670,92};
		
		//-- text variables
		
		private final int TXT_TITLELEARN = 0;
		private final int TXT_TITLEPLAY = 1;
		private final int TXT_OPTION = 2;
		private final int TXT_PROFILE = 3;
		private final int TXT_PLAY = 4;
		private final int TXT_LEARN = 5;		
		private final int TXT_ALPHABET = 6;
		private final int TXT_NUMBER = 7;
		private final int TXT_COLOR = 8;
		private final int TXT_GAME = 9;
		
		//---------------------------------------------
		// METHODS FROM SUPERCLASS
		//---------------------------------------------
	
	
	@Override
	public void createScene() {
		showAds();
		localLanguage = resourcesManager.initLanguage();
		soundsOrNot = resourcesManager.initSounds();
		
		localIdProfile=resourcesManager.getProfile();
		
		//initLanguage();
		playMusic();
		
		final float centerX = camera.getWidth() / 2;
		final float centerY = camera.getHeight() / 2;
		
		//-- quit or not scene --------------------------------------------------------------------------------
    	
				exitScene = new CameraScene(camera);
				// Make the 'WIN'-label centered on the camera. 
				final Sprite exitSprite = new Sprite(centerX, centerY, resourcesManager.emptyWindowTR, vbom);
				exitScene.attachChild(exitSprite);
				// Makes the paused Game look through. 
				exitScene.setBackgroundEnabled(false);
		    	
				final TiledSprite btexit;
				btexit = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom){
						
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()){
					case TouchEvent.ACTION_DOWN:
						this.setCurrentTileIndex(1);
					
						break;
					case TouchEvent.ACTION_UP:
						this.setCurrentTileIndex(0);
						System.exit(0);
						break;
					}
					return true;
		      	}


				};
				btexit.setCurrentTileIndex(0);
				exitScene.attachChild(btexit);
				exitScene.registerTouchArea(btexit);
		
				
				final TiledSprite returnGame;
				returnGame = new TiledSprite(300, 150, resourcesManager.btNoTR, vbom){
						
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()){
					case TouchEvent.ACTION_DOWN:
						this.setCurrentTileIndex(1);
					
						break;
					case TouchEvent.ACTION_UP:
						this.setCurrentTileIndex(0);
						returnGame();
						break;
					}
					return true;
		      	}


				};
				returnGame.setCurrentTileIndex(0);
				exitScene.attachChild(returnGame);
				exitScene.registerTouchArea(returnGame);
				
				quitgameHUD = new HUD();
				quitOrNotText = new Text(400,300, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
				quitOrNotText.setSkewCenter(0,0);    
				quitOrNotText.setText("");        
				//quitOrNotText.setText("Do You Really Want To Exit !"); 
				quitgameHUD.attachChild(quitOrNotText);         
		        camera.setHUD(quitgameHUD); 
		        exitScene.attachChild(quitgameHUD);
		//--------------------------------------------------------------------------------------------------------		
		

		this.setBackground(new Background(1,20,20));
		
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom)));
		this.setBackground(autoParallaxBackground);
		
		if (resourcesManager.getIdMenu()==1){
			learnOrPlay=1;
		}else {
			learnOrPlay=0;
		}
		
		if(learnOrPlay==0){
			
			createMenuChildScene();
		}else{
			
			createMenuPlayChildScene();
		}		
		//createMenuChildScene();
		//createMenuPlayChildScene();
	}

	private void createMenuChildScene() {
		
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0, 0);
		////
		/*
		String checkProfile="";
		localIdProfile=resourcesManager.getProfile();
		
		switch(localIdProfile){
		case 0:
			checkProfile="0-2";
			break;
		case 1:
			checkProfile="2-4";
			break;
		case 2:
			checkProfile="4+";
			break;
				
		}
		
		final Text bitmapTextTest = new Text(120, 460, resourcesManager.mBitmapFont, "prof:"+checkProfile, vbom);		
		menuChildScene.attachChild(bitmapTextTest);
		*/
		////
		final Text bitmapTextProfileTitle = new Text(290, 451, resourcesManager.mBitmapFont, getText(TXT_TITLELEARN), vbom);		
		menuChildScene.attachChild(bitmapTextProfileTitle);
		
		final Text bitmapTextAlphabetLearn = new Text(100, 192, resourcesManager.mBitmapFont, getText(TXT_ALPHABET), vbom);		
		menuChildScene.attachChild(bitmapTextAlphabetLearn);
						
		final Text bitmapTextNumberLearn = new Text(290, 192, resourcesManager.mBitmapFont, getText(TXT_NUMBER), vbom);
		menuChildScene.attachChild(bitmapTextNumberLearn);
		
		final Text bitmapTextColorLearn = new Text(480, 192, resourcesManager.mBitmapFont, getText(TXT_COLOR), vbom);		
		menuChildScene.attachChild(bitmapTextColorLearn);
		/*
		final Text bitmapTextGameLearn = new Text(100, 16, resourcesManager.mBitmapFont, "Game", vbom);		
		menuChildScene.attachChild(bitmapTextGameLearn);
		
		final Text bitmapTextAnimalLearn = new Text(280, 330, resourcesManager.mBitmapFont, "Animals", vbom);		
		menuChildScene.attachChild(bitmapTextAnimalLearn);
		
		final Text bitmapTextMusicalinstLearn = new Text(280, 172, resourcesManager.mBitmapFont, "Music", vbom);		
		menuChildScene.attachChild(bitmapTextMusicalinstLearn);
	
		final Text bitmapTextShapeLearn = new Text(460, 330, resourcesManager.mBitmapFont, "Shapes", vbom);		
		menuChildScene.attachChild(bitmapTextShapeLearn);
		
		final Text bitmapTextFlagLearn = new Text(460, 172, resourcesManager.mBitmapFont, "Flags", vbom);		
		menuChildScene.attachChild(bitmapTextFlagLearn);
		
		final Text bitmapTextFoodLearn = new Text(460, 16, resourcesManager.mBitmapFont, "Food", vbom);		
		menuChildScene.attachChild(bitmapTextFoodLearn);		
		*/		
		final Text bitmapTextOptions = new Text(700, 350, resourcesManager.mBitmapFont, getText(TXT_OPTION), vbom);		
		menuChildScene.attachChild(bitmapTextOptions);
		
		final Text bitmapTextProfile = new Text(700, 192, resourcesManager.mBitmapFont, getText(TXT_PROFILE), vbom);		
		menuChildScene.attachChild(bitmapTextProfile);
		
		final Text bitmapTextPlay = new Text(700, 36, resourcesManager.mBitmapFont, getText(TXT_PLAY), vbom);		
		menuChildScene.attachChild(bitmapTextPlay);
		
		
		bitmapTextProfileTitle.setColor(Color.GREEN);
		bitmapTextAlphabetLearn.setColor(Color.RED);
		bitmapTextNumberLearn.setColor(Color.YELLOW);
		bitmapTextColorLearn.setColor(Color.GREEN);
		/*bitmapTextGameLearn.setColor(Color.GREEN);
		bitmapTextAnimalLearn.setColor(Color.GREEN);
		bitmapTextMusicalinstLearn.setColor(Color.RED);*/
		
		/*bitmapTextShapeLearn.setColor(Color.YELLOW);
		bitmapTextFlagLearn.setColor(Color.GREEN);
		bitmapTextFoodLearn.setColor(Color.RED);*/
		bitmapTextOptions.setColor(Color.CYAN);
		bitmapTextProfile.setColor(Color.CYAN);
		bitmapTextPlay.setColor(Color.CYAN);
		
		final IMenuItem alphabetlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_ALPHABETLEARN, resourcesManager.btAlphabetlearnIconTR, vbom), 1f, .8f);
		final IMenuItem numberlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_NUMBERLEARN, resourcesManager.btNumberslearnIconTR, vbom), 1f, .8f);
		final IMenuItem colorlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_COLORLEARN, resourcesManager.btColorlearnIconTR, vbom), 1f, .8f);
		/*final IMenuItem ballgameMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BALLGAME, resourcesManager.btGameIconTR, vbom), 1f, .8f);
		final IMenuItem animallearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_ANIMALLEARN, resourcesManager.btAnimallearnIconTR, vbom), 1f, .8f);
		final IMenuItem musicalinstlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_MUSICALINSTLEARN, resourcesManager.btMusicalinstlearnIconTR, vbom), 1f, .8f);
		final IMenuItem shapelearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SHAPELEARN, resourcesManager.btShapelearnIconTR, vbom), 1f, .8f);
		final IMenuItem flaglearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_FLAGLEARN, resourcesManager.btFlaglearnIconTR, vbom), 1f, .8f);
		final IMenuItem foodlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_FOODLEARN, resourcesManager.btFoodlearnIconTR, vbom), 1f, .8f);
		*/
		final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.btOptionsIconTR, vbom), 1f, .8f);
		final IMenuItem profileMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PROFILE, resourcesManager.btProfileIconTR, vbom), 1f, .8f);
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.btPlayIconTR, vbom), 1f, .8f);

		menuChildScene.addMenuItem(alphabetlearnMenuItem);
		menuChildScene.addMenuItem(numberlearnMenuItem);
		menuChildScene.addMenuItem(colorlearnMenuItem);
		/*menuChildScene.addMenuItem(ballgameMenuItem);
		menuChildScene.addMenuItem(animallearnMenuItem);		
		menuChildScene.addMenuItem(musicalinstlearnMenuItem);
		menuChildScene.addMenuItem(shapelearnMenuItem);
		menuChildScene.addMenuItem(flaglearnMenuItem);
		menuChildScene.addMenuItem(foodlearnMenuItem);*/
		menuChildScene.addMenuItem(optionsMenuItem);
		menuChildScene.addMenuItem(profileMenuItem);
		menuChildScene.addMenuItem(playMenuItem);
		
		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);
		
		
		alphabetlearnMenuItem.setPosition(100, 270);
		numberlearnMenuItem.setPosition(290, 270);
		colorlearnMenuItem.setPosition(480, 270);
		/*ballgameMenuItem.setPosition(100, 92);
		animallearnMenuItem.setPosition(280, 408);
		musicalinstlearnMenuItem.setPosition(280, 250);
		shapelearnMenuItem.setPosition(460, 408);
		flaglearnMenuItem.setPosition(460, 250);
		foodlearnMenuItem.setPosition(460, 92);	*/	
		optionsMenuItem.setPosition(700, 428);
		profileMenuItem.setPosition(700, 270);
		playMenuItem.setPosition(700, 112);
		
		menuChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuChildScene);
	}
	
	private void createMenuPlayChildScene() {
		// TODO Auto-generated method stub
		//menuPlayChildScene.setBackground(new Background(240,120,90));
		
		menuPlayChildScene = new MenuScene(camera);
		menuPlayChildScene.setPosition(0, 0);
		
		final Text bitmapTextProfileTitle = new Text(290, 451, resourcesManager.mBitmapFont, getText(TXT_TITLEPLAY), vbom);		
		menuPlayChildScene.attachChild(bitmapTextProfileTitle);
		
		final Text bitmapTextAlphabetLearn = new Text(100, 250, resourcesManager.mBitmapFont, getText(TXT_ALPHABET), vbom);		
		menuPlayChildScene.attachChild(bitmapTextAlphabetLearn);
						
		final Text bitmapTextNumberLearn = new Text(290, 250, resourcesManager.mBitmapFont, getText(TXT_NUMBER), vbom);
		menuPlayChildScene.attachChild(bitmapTextNumberLearn);
		
		final Text bitmapTextColorLearn = new Text(480, 250, resourcesManager.mBitmapFont, getText(TXT_COLOR), vbom);		
		menuPlayChildScene.attachChild(bitmapTextColorLearn);
		
		final Text bitmapTextGameLearn = new Text(290, 64, resourcesManager.mBitmapFont, getText(TXT_GAME), vbom);		
		menuPlayChildScene.attachChild(bitmapTextGameLearn);
		/*
		final Text bitmapTextAnimalLearn = new Text(280, 330, resourcesManager.mBitmapFont, "Animals", vbom);		
		menuPlayChildScene.attachChild(bitmapTextAnimalLearn);
		
		final Text bitmapTextMusicalinstLearn = new Text(280, 172, resourcesManager.mBitmapFont, "Music", vbom);		
		menuPlayChildScene.attachChild(bitmapTextMusicalinstLearn);
		
		final Text bitmapTextShapeLearn = new Text(460, 330, resourcesManager.mBitmapFont, "Shapes", vbom);		
		menuPlayChildScene.attachChild(bitmapTextShapeLearn);
		
		final Text bitmapTextFlagLearn = new Text(460, 172, resourcesManager.mBitmapFont, "Flags", vbom);		
		menuPlayChildScene.attachChild(bitmapTextFlagLearn);
		
		final Text bitmapTextFoodLearn = new Text(460, 16, resourcesManager.mBitmapFont, "Food", vbom);		
		menuPlayChildScene.attachChild(bitmapTextFoodLearn);		
		*/		
		final Text bitmapTextOptions = new Text(700, 350, resourcesManager.mBitmapFont, getText(TXT_OPTION), vbom);		
		menuPlayChildScene.attachChild(bitmapTextOptions);
		
		final Text bitmapTextProfile = new Text(700, 192, resourcesManager.mBitmapFont, getText(TXT_PROFILE), vbom);		
		menuPlayChildScene.attachChild(bitmapTextProfile);
		
		final Text bitmapTextPlay = new Text(700, 36, resourcesManager.mBitmapFont, getText(TXT_LEARN), vbom);		
		menuPlayChildScene.attachChild(bitmapTextPlay);
		
		bitmapTextProfileTitle.setColor(Color.YELLOW);
		bitmapTextAlphabetLearn.setColor(Color.RED);
		bitmapTextNumberLearn.setColor(Color.YELLOW);
		bitmapTextColorLearn.setColor(Color.GREEN);
		bitmapTextGameLearn.setColor(Color.GREEN);
		/*bitmapTextAnimalLearn.setColor(Color.GREEN);
		bitmapTextMusicalinstLearn.setColor(Color.RED);*/
		
		/*bitmapTextShapeLearn.setColor(Color.YELLOW);
		bitmapTextFlagLearn.setColor(Color.GREEN);
		bitmapTextFoodLearn.setColor(Color.RED);*/
		bitmapTextOptions.setColor(Color.CYAN);
		bitmapTextProfile.setColor(Color.CYAN);
		bitmapTextPlay.setColor(Color.CYAN);
		
		
		final IMenuItem alphabetlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_ALPHABETPLAY, resourcesManager.btAlphabetplayIconTR, vbom), 1f, .8f);
		final IMenuItem numberlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_NUMBERPLAY, resourcesManager.btNumbersplayIconTR, vbom), 1f, .8f);
		final IMenuItem colorlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_COLORPLAY, resourcesManager.btColorplayIconTR, vbom), 1f, .8f);
		final IMenuItem ballgameMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BALLGAME, resourcesManager.btGameIconTR, vbom), 1f, .8f);
		/*final IMenuItem animallearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_ANIMALPLAY, resourcesManager.btAnimallearnIconTR, vbom), 1f, .8f);
		final IMenuItem musicalinstlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_MUSICALINSTPLAY, resourcesManager.btMusicalinstlearnIconTR, vbom), 1f, .8f);
		final IMenuItem shapelearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SHAPEPLAY, resourcesManager.btShapelearnIconTR, vbom), 1f, .8f);
		final IMenuItem flaglearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_FLAGPLAY, resourcesManager.btFlaglearnIconTR, vbom), 1f, .8f);
		final IMenuItem foodlearnMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_FOODPLAY, resourcesManager.btFoodlearnIconTR, vbom), 1f, .8f);
		*/final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.btOptionsIconTR, vbom), 1f, .8f);
		final IMenuItem profileMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PROFILE, resourcesManager.btProfileIconTR, vbom), 1f, .8f);
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.btLearnIconTR, vbom), 1f, .8f);

		menuPlayChildScene.addMenuItem(alphabetlearnMenuItem);
		menuPlayChildScene.addMenuItem(numberlearnMenuItem);
		menuPlayChildScene.addMenuItem(colorlearnMenuItem);
		menuPlayChildScene.addMenuItem(ballgameMenuItem);
		/*menuPlayChildScene.addMenuItem(animallearnMenuItem);		
		menuPlayChildScene.addMenuItem(musicalinstlearnMenuItem);
		menuPlayChildScene.addMenuItem(shapelearnMenuItem);
		menuPlayChildScene.addMenuItem(flaglearnMenuItem);
		menuPlayChildScene.addMenuItem(foodlearnMenuItem);*/
		menuPlayChildScene.addMenuItem(optionsMenuItem);
		menuPlayChildScene.addMenuItem(profileMenuItem);
		menuPlayChildScene.addMenuItem(playMenuItem);
		
		menuPlayChildScene.buildAnimations();
		menuPlayChildScene.setBackgroundEnabled(false);
		
		
		alphabetlearnMenuItem.setPosition(100, 328);
		numberlearnMenuItem.setPosition(290, 328);
		colorlearnMenuItem.setPosition(480, 328);
		ballgameMenuItem.setPosition(290, 140);
		/*animallearnMenuItem.setPosition(280, 408);
		musicalinstlearnMenuItem.setPosition(280, 250);
		shapelearnMenuItem.setPosition(460, 408);
		flaglearnMenuItem.setPosition(460, 250);
		foodlearnMenuItem.setPosition(460, 92);		*/
		optionsMenuItem.setPosition(700, 428);
		profileMenuItem.setPosition(700, 270);
		playMenuItem.setPosition(700, 112);
		
		menuPlayChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuPlayChildScene);
	}
	
	private void returnGame() {
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		this.clearChildScene();
		
		if(learnOrPlay==0){
			createMenuChildScene();
			setChildScene(menuChildScene);
		}else{
			createMenuPlayChildScene();
			setChildScene(menuPlayChildScene);
		}
		//setChildScene(menuChildScene);
		
	}
	
	
	@Override
	public void onBackKeyPressed() {

		if (localLanguage==0){
			quitOrNotText.setText("Vous voulez vraiment quitter !"); 
			}else{
			quitOrNotText.setText("Do You Really Want To Exit !"); 	
			}
		this.setChildScene(exitScene, false, true, true);
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MAINMENU;
	}

	@Override
	public void disposeScene() {
		this.detachSelf();
		this.dispose();
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID())
		{
			//-- LEARN SCENES --------------------------------------------
		
			case MENU_ALPHABETLEARN:
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(0);
				SceneManager.getInstance().loadAlphabetLearnScene(engine);
				return true;
			case MENU_NUMBERLEARN:
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(0);
				SceneManager.getInstance().loadNumberLearnScene(engine);
				return true;
			case MENU_COLORLEARN:	
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(0);
				SceneManager.getInstance().loadColorLearnScene(engine);
				return true;
			case MENU_BALLGAME:
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(1);
				SceneManager.getInstance().loadBallGameScene(engine);				
				return true;
			case MENU_ANIMALLEARN:
				//playSelectSound();
				//SceneManager.getInstance().loadAnimalLearnScene(engine);					
				return true;
			case MENU_MUSICALINSTLEARN:
				//playSelectSound();
				//SceneManager.getInstance().loadColorLearnScene(engine);
				return true;
			
			case MENU_SHAPELEARN:				
				//SceneManager.getInstance().loadColorLearnScene(engine);
				return true;
			case MENU_FLAGLEARN:				
				//SceneManager.getInstance().loadColorLearnScene(engine);
				return true;
			case MENU_FOODLEARN:				
				//SceneManager.getInstance().loadColorLearnScene(engine);
				return true;

			//-- PLAY SCENES --------------------------------------------
				
			case MENU_ALPHABETPLAY:
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(1);
				SceneManager.getInstance().loadAlphabetGameScene(engine);
				return true;
			case MENU_NUMBERPLAY:
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(1);
				SceneManager.getInstance().loadNumberGameScene(engine);
				return true;
			case MENU_COLORPLAY:				
				playSelectSound();
				pauseMusic();
				resourcesManager.setIdMenu(1);
				SceneManager.getInstance().loadColorGameScene(engine);
				return true;
			case MENU_ANIMALPLAY:
				//SceneManager.getInstance().loadAnimalPlayScene(engine);					
				return true;
			
			case MENU_SHAPEPLAY:				
				//SceneManager.getInstance().loadColorPlayScene(engine);
				return true;
			case MENU_FLAGPLAY:				
				//SceneManager.getInstance().loadColorPlayScene(engine);
				return true;
			case MENU_FOODPLAY:				
				//SceneManager.getInstance().loadColorPlayScene(engine);
				return true;
			case MENU_MUSICALINSTPLAY:				
				//SceneManager.getInstance().loadColorPlayScene(engine);
				return true;
				
			//-- COMMON SCENES --------------------------------------------
				
			case MENU_OPTIONS:	
				playSelectSound();
				((GameActivity) activity).openPrefs();
				return true;
			case MENU_PROFILE:
				playSelectSound();
				pauseMusic();
				SceneManager.getInstance().loadProfileScene(engine);
				return true;
			case MENU_PLAY:	
				playSelectSound();
				//SceneManager.getInstance().loadColorLearnScene(engine);
				if(learnOrPlay==0){
					resourcesManager.setIdMenu(1);
					learnOrPlay=1;
					detachChild(menuChildScene);
					this.clearChildScene();
					//resourcesManager.setIdMenu(15);
					createMenuPlayChildScene();
				}else{
					resourcesManager.setIdMenu(0);
					learnOrPlay=0;
					detachChild(menuPlayChildScene);
					this.clearChildScene();
					//resourcesManager.setIdMenu(10);
					createMenuChildScene();
					//setChildScene(menuChildScene);
				}
				return true;
			default:
				return false;
		}

	}
	
	/*
	private void initLanguage(){
		//-- init language -------------------------------------------------------------------
		prefLanguage = ((GameActivity) activity).getPreference("language", "1");
		if (prefLanguage.contentEquals("1")){
			//resourcesManager.tts.setLanguage(Locale.FRENCH);
			localLanguage=0;
		} else if (prefLanguage.contentEquals("2")){
			//resourcesManager.tts.setLanguage(Locale.ENGLISH);	
			localLanguage=1;
		} 
		}
		//-----------------------------------------------------------------------------------
	*/
	private String getText(int txtNum){
		String txt="";
		
		if (localLanguage==0){
			txt=textsFr[txtNum];
		}else{
			txt=textsEn[txtNum];
		}
		
		return txt;
	}

	private void playSelectSound(){
		try{
				if(!soundsOrNot){	
				resourcesManager.mSelectSound.play();
				}
				}catch(Exception ex){
					ex.printStackTrace();                    				
				}
	}
	
	private void playMusic(){
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
	
	private void pauseMusic(){
		try{	

			//ResourcesManager.mMusicFingerFamily.pause();
			ResourcesManager.mMusicSeventies.pause();
			
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private void showInterstitialAds(){		

		activity.runOnUiThread(new Runnable() {
		    public void run() {
		    	((GameActivity) activity).displayInterstitial();
		    }
		});
	}
	
	private void hideAds(){		

		activity.runOnUiThread(new Runnable() {
		    public void run() {		    		
		    	((GameActivity) activity).hideAd();
		    }
		});
	}
	private void showAds(){		

		activity.runOnUiThread(new Runnable() {
		    public void run() {		    		
		    	((GameActivity) activity).showAd();	
		    }
		});	
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
