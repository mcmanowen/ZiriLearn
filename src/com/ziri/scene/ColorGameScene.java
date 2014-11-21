package com.ziri.scene;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import android.speech.tts.TextToSpeech;

import com.ziri.GameActivity;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class ColorGameScene extends BaseScene  implements IOnSceneTouchListener, IOnAreaTouchListener  {
	
	//---------------------------------------------
	// PREFERENCES
	//---------------------------------------------
	
	String prefLocalPlayer,prefVisitorPlayer,prefLanguage;
	boolean prefMusic,prefGameSounds,prefLearnSounds;
	
	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	
	private int localIdProfile; 
	private int pageCounter=0;
	private int adsDuration=60;
	private boolean muteOrNot,musicOrNot;
	//private Text imgNameTextFr;
	private Text titleText;	
	private int localLanguage;
    //-----------------------------------------------------------
	
	//--init variables

	private int maxGamePages=8; 
	private int levelDuration=10;
	private int levelID=0;
	private int img1,img2,img3,img4,img0,img5; //4 selected images + number between 1 and 4 + the img corresponding to the number selected
	
	Text totalScoreText;	
	Text usernameAText;	
	Text timeText;
	Text gameCompleteText;
	Text replayText;
	Text quitOrNotText;
	int levelScore=40;
	int totalScore=0;
	HUD gameHUD;
	HUD quitgameHUD;
	HUD gamecompleteHUD;
	
	//-- init level variables
	
	ArrayList<Integer> clickedImg;
	boolean answeredOrNot;
	//-------------------------------------------------	
	
	private CameraScene winScene,goMenuScene,pauseScene;

    //--text to speech numbers
	static final String[] textImgsFr = {"rouge","bleu","marron", "vert","gris","orange","rose","violet","noir","blanc","jaune"};
	static final String[] textImgsFrRead = {"rouge","bleu","marron", "vert","gris","orange","rose","violet","noir","blanc","jaune"};
	static final String[] textImgsEn = {"red","blue","brown", "green","grey","orange","pink","purple","black","white","yellow"};

   
    //-- sprite imgs
	Sprite
	
	img_001,img_002,img_003,img_004,
    img_005,img_006,img_007,img_008,
    img_009,img_010,
    img_011/*,img_012,img_013,img_014,
    img_015,img_016,img_017,img_018,
    img_019,img_020,
    img_021,img_022,img_023,img_024,
    img_025,img_026,img_027,img_028,
    img_029,img_030,
    img_031,img_032,img_033,img_034,
    img_035,img_036,img_037,img_038,
    img_039,img_040,
    img_041,img_042,img_043,img_044,
    img_045,img_046,img_047,img_048,
    img_049/*,img_050,
    img_051,img_052,img_053,img_054,
    img_055,img_056,img_057,img_058,
    img_059,img_060,
    img_061,img_062,img_063,img_064,
    img_065,img_066,img_067,img_068,
    img_069,img_070,
    img_071,img_072,img_073,img_074,
    img_075,img_076,img_077,img_078,
    img_079,img_080,
    img_081,img_082,img_083,img_084,
    img_085,img_086,img_087,img_088,
    img_089,img_090,
    img_091,img_092,img_093,img_094,
    img_095,img_096,img_097,img_098,
    img_099,img_100*/
    ;

	Sprite imTrue1,imTrue2,imTrue3,imTrue4,imFalse1,imFalse2,imFalse3,imFalse4;
	
	
	//-- control buttons
	//private boolean leftArrowVisible;
	private boolean rightArrowVisible;
	float leftArrowX,leftArrowY,rightArrowX,rightArrowY;
	//int nextPage,backPage;

	//-- sprite arrows -----------------------------------------------

	AnimatedSprite rightArrow;
	//AnimatedSprite leftArrow;

	//-- menu bar ----------------------------------------------------

	Sprite notes,notesMute;
 	AnimatedSprite btMenu;
	AnimatedSprite btHome;
	TiledSprite changLang;
	//----------------------------------------------------------------

	//-- control sounds  -----------------------------------------------

	Sprite imgSound,imgName;

	//----------------------------------------------------------------
	
	//--  background -------------------------------------------------
			Sprite blackBoard;
			AutoParallaxBackground autoParallaxBackground;
	//----------------------------------------------------------------


	//---------------------------------------------
	// METHODS FROM SUPERCLASS
	//---------------------------------------------


	@Override
	public void createScene() {
		hideAds();
		levelScore=40;
		answeredOrNot=false;
		final float centerX = camera.getWidth() / 2;
		final float centerY = camera.getHeight() / 2;
		// --pause scene
		// ----------------------------------------------------------------------------------
		/*
		pauseScene = new CameraScene(camera);
		// Make the 'PAUSED'-label centered on the camera.

		final AnimatedSprite pausedSprite = new AnimatedSprite(centerX,
				centerY, resourcesManager.btPauseTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {

					resumeScene();

					return true;

				} else {
					return false;
				}
			}

		};

		pauseScene.attachChild(pausedSprite);
		pauseScene.registerTouchArea(pausedSprite);
		// Makes the paused Game look through.
		pauseScene.setBackgroundEnabled(false);
		*/
		// -------------------------------------------------------------------------------------------------

		// --goMenu scene
		// ----------------------------------------------------------------------------------

		goMenuScene = new CameraScene(camera);
		// Make the 'WIN'-label centered on the camera.
		final Sprite goMenuSprite = new Sprite(centerX, centerY,
				resourcesManager.emptyWindowTR, vbom);
		goMenuScene.attachChild(goMenuSprite);
		// Makes the paused Game look through.
		goMenuScene.setBackgroundEnabled(false);

		final TiledSprite goMenu;
		goMenu = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setCurrentTileIndex(1);

					break;
				case TouchEvent.ACTION_UP:
					this.setCurrentTileIndex(0);
					goMenu();
					break;
				}
				return true;
			}

		};
		goMenu.setCurrentTileIndex(0);
		goMenuScene.attachChild(goMenu);

		goMenuScene.registerTouchArea(goMenu);

		final TiledSprite returnGame;
		returnGame = new TiledSprite(300, 150, resourcesManager.btNoTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
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
		goMenuScene.attachChild(returnGame);

		goMenuScene.registerTouchArea(returnGame);

		quitgameHUD = new HUD();
		quitOrNotText = new Text(400, 300, resourcesManager.mBitmapFont,
				"abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
		quitOrNotText.setSkewCenter(0, 0);
		quitOrNotText.setText("");
		// quitOrNotText.setText("Do You Really Want To Exit !");
		quitgameHUD.attachChild(quitOrNotText);
		camera.setHUD(quitgameHUD);
		goMenuScene.attachChild(quitgameHUD);

		// ---------------------------------------------------------------------------------------------

		// --win scene
		// ---------------------------------------------------------------------------------

		winScene = new CameraScene(camera);
		// Make the 'WIN'-label centered on the camera.
		final Sprite winSprite = new Sprite(centerX, centerY,
				resourcesManager.emptyWindowTR, vbom);
		winScene.attachChild(winSprite);
		// Makes the paused Game look through.
		winScene.setBackgroundEnabled(false);

		final TiledSprite initGame;
		initGame = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setCurrentTileIndex(1);

					break;
				case TouchEvent.ACTION_UP:
					showInterstitialAds();
					
					gameCompleteText.setText("");
					replayText.setText("");
					clearChildScene();
					initVariables(1,0,muteOrNot,musicOrNot,0);
					initScene();
					break;
				}
				return true;
			}

		};
		initGame.setCurrentTileIndex(0);
		winScene.attachChild(initGame);
		winScene.registerTouchArea(initGame);
		
		
		final TiledSprite quitGame;
		quitGame = new TiledSprite(300, 150, resourcesManager.btNoTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setCurrentTileIndex(1);

					break;
				case TouchEvent.ACTION_UP:
					gameCompleteText.setText("");
					replayText.setText("");
					goMenu();
					break;
				}
				return true;
			}

		};
		quitGame.setCurrentTileIndex(0);
		winScene.attachChild(quitGame);
		winScene.registerTouchArea(quitGame);
		
		
		gamecompleteHUD = new HUD();
		
		gameCompleteText = new Text(400, 370, resourcesManager.mBitmapFont,
				"abcdefghijklmnopqrstuvwxyz", vbom);
		gameCompleteText.setSkewCenter(0, 0);
		gameCompleteText.setText("");
		gamecompleteHUD.attachChild(gameCompleteText);

		replayText = new Text(400, 240, resourcesManager.mBitmapFont,
				"abcdefghijklmnopqrstuvwxyz", vbom);
		replayText.setSkewCenter(0, 0);
		replayText.setText("");
		gamecompleteHUD.attachChild(replayText);
		
		
		camera.setHUD(gamecompleteHUD);
		winScene.attachChild(gamecompleteHUD);
		// ------------------------------------------------------------------------------------------	

		clickedImg = new ArrayList<Integer>();
		
		if (levelID==0){
			levelID+=1;							//level number
			
	    	initSound();    	
	    	initMusic();    	
	    	initLanguage();
	    	
			initVariables(1,0,muteOrNot,musicOrNot,0);
	
			}else{
				
			}
		
		int [] fourSelected = getFour();
		initLevelVariables(fourSelected[0],fourSelected[1],fourSelected[2],fourSelected[3],fourSelected[4],fourSelected[5]);
		
		
		this.setBackground(new Background(1,1,1));
    	this.setOnSceneTouchListener((IOnSceneTouchListener) this);
    	this.setOnAreaTouchListener((IOnAreaTouchListener) this);


    	//leftArrowVisible = false;
    	rightArrowVisible = false;
    	
    	autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-1.0f, new Sprite(0, 80, this.mAlphabetParallaxLayerMid, vertexBufferObjectManager)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, camera.getHeight() - this.mAlphabetParallaxLayerFront.getHeight(), this.mAlphabetParallaxLayerFront, vertexBufferObjectManager)));
		this.setBackground(autoParallaxBackground);
		
		blackBoard = new Sprite(370,215,resourcesManager.blackBoardTR,vbom);
		this.attachChild(blackBoard);

        Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight()-20,camera.getWidth(),40,vbom);
        top.setColor(new Color(1,10,20));
        attachChild(top);
		
        titleText = new Text(camera.getWidth()/4, 455, resourcesManager.mBitmapFont, levelID+":nnnnnnnnnnnnnnnnnnnnnnnnnnnn", vbom);
        titleText.setColor(Color.BLUE);
        this.attachChild(titleText);
        try{
        	titleText.setText("score:"+totalScore);
    		}catch(ArrayIndexOutOfBoundsException ex){
    			ex.printStackTrace();
    		}
	}

	@Override
	public void populateScene() {

		img_001	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_001TR, vbom); 
		img_002	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_002TR, vbom); 
		img_003	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_003TR, vbom); 
		img_004	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_004TR, vbom); 
		img_005	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_005TR, vbom); 
		img_006	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_006TR, vbom); 
		img_007	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_007TR, vbom); 
		img_008	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_008TR, vbom); 
		img_009	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_009TR, vbom); 
		img_010	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_010TR, vbom); 
		img_011	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_011TR, vbom); 
		/*img_012	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_012TR, vbom); 
		img_013	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_013TR, vbom); 
		img_014	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_014TR, vbom); 
		img_015	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_015TR, vbom); 
		img_016	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_016TR, vbom); 
		img_017	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_017TR, vbom); 
		img_018	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_018TR, vbom); 
		img_019	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_019TR, vbom); 
		img_020	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_020TR, vbom); 
		img_021	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_021TR, vbom); 
		img_022	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_022TR, vbom); 
		img_023	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_023TR, vbom); 
		img_024	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_024TR, vbom); 
		img_025	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_025TR, vbom); 
		img_026	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_026TR, vbom); 
		img_027	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_027TR, vbom); 
		img_028	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_028TR, vbom); 
		img_029	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_029TR, vbom); 
		img_030	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_030TR, vbom); 
		img_031	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_031TR, vbom); 
		img_032	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_032TR, vbom); 
		img_033	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_033TR, vbom); 
		img_034	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_034TR, vbom); 
		img_035	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_035TR, vbom); 
		img_036	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_036TR, vbom); 
		img_037	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_037TR, vbom); 
		img_038	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_038TR, vbom); 
		img_039	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_039TR, vbom); 
		img_040	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_040TR, vbom); 
		img_041	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_041TR, vbom); 
		img_042	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_042TR, vbom); 
		img_043	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_043TR, vbom); 
		img_044	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_044TR, vbom); 
		img_045	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_045TR, vbom); 
		img_046	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_046TR, vbom); 
		img_047	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_047TR, vbom); 
		img_048	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_048TR, vbom); 
		img_049	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_049TR, vbom); 
		*/
	
		
		this.attachChild(img_001);
		this.attachChild(img_002);
		this.attachChild(img_003);
		this.attachChild(img_004);
		this.attachChild(img_005);
		this.attachChild(img_006);
		this.attachChild(img_007);
		this.attachChild(img_008);
		this.attachChild(img_009);
		this.attachChild(img_010);
		this.attachChild(img_011);
		/*this.attachChild(img_012);
		this.attachChild(img_013);
		this.attachChild(img_014);
		this.attachChild(img_015);
		this.attachChild(img_016);
		this.attachChild(img_017);
		this.attachChild(img_018);
		this.attachChild(img_019);
		this.attachChild(img_020);
		this.attachChild(img_021);
		this.attachChild(img_022);
		this.attachChild(img_023);
		this.attachChild(img_024);
		this.attachChild(img_025);
		this.attachChild(img_026);
		this.attachChild(img_027);
		this.attachChild(img_028);
		this.attachChild(img_029);
		this.attachChild(img_030);
		this.attachChild(img_031);
		this.attachChild(img_032);
		this.attachChild(img_033);
		this.attachChild(img_034);
		this.attachChild(img_035);
		this.attachChild(img_036);
		this.attachChild(img_037);
		this.attachChild(img_038);
		this.attachChild(img_039);
		this.attachChild(img_040);
		this.attachChild(img_041);
		this.attachChild(img_042);
		this.attachChild(img_043);
		this.attachChild(img_044);
		this.attachChild(img_045);
		this.attachChild(img_046);
		this.attachChild(img_047);
		this.attachChild(img_048);
		this.attachChild(img_049);
		*/
		/*
		Sprite[] imgs = new Sprite[49];
		imgs[0] = img_001;
		imgs[1] = img_002;
		imgs[2] = img_003;
		imgs[3] = img_004;
		,img_002,img_003,img_004,
			    img_005,img_006,img_007,img_008,img_009,img_010,
			    img_011,img_012,img_013,img_014,
			    img_015,img_016,img_017,img_018,
			    img_019,img_020,img_021,img_022,img_023,img_024,
			    img_025,img_026,img_027,img_028,
			    img_029,img_030,img_031,img_032,img_033,img_034,
			    img_035,img_036,img_037,img_038,
			    img_039,img_040, img_041,img_042,img_043,img_044,
			    img_045,img_046,img_047,img_048,img_049];
*/
		//-- menu button ---------------------------------------------------------------------------------------
		btMenu = new AnimatedSprite(resourcesManager.btMenuTR.getWidth()/2,camera.getHeight()-(resourcesManager.btMenuTR.getHeight()/2),resourcesManager.btMenuTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					playSelectSound();
					onBackKeyPressed();

					return true;

				} else {
					return false;
				}
			}
		};

       this.attachChild(btMenu);
       this.registerTouchArea(btMenu);
	   //-----------------------------------------------------------------------------------------------------
 
       //-- home button -----------------------------------------------------------------------------------------
       btHome = new AnimatedSprite(camera.getWidth()/2,camera.getHeight()-(resourcesManager.btHomeTR.getHeight()/2),resourcesManager.btHomeTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					// Completely remove all resources associated with this sprite.
					playSelectSound();
					hideImg();
					hideArrows();
					hideTrueFalse();
					initVariables(1,0,muteOrNot,musicOrNot,0);
					initScene();
					//displayImg(0);

			        pageCounter = 0;
					return true;

				} else {
					return false;
				}
			}
		};

		this.attachChild(btHome);
		this.registerTouchArea(btHome);
		//-----------------------------------------------------------------------------------------------------

		
		//-- change language button -----------------------------------------------------------------------------
				
		changLang = new TiledSprite(600, camera.getHeight()-(resourcesManager.btLangTR.getHeight()/2), resourcesManager.btLangTR, vbom){
				
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()){
			case TouchEvent.ACTION_DOWN:
				
			
				break;
			case TouchEvent.ACTION_UP:
				playSelectSound();
				if(localLanguage==0){
					changeLanguage("UK");
					this.setCurrentTileIndex(1);
					localLanguage=1;
				}else if (localLanguage==1) {
					changeLanguage("FR");
					this.setCurrentTileIndex(0);
					localLanguage=0;
				}
				//readimgName();
				if (pageCounter!=800 && answeredOrNot==false){
					readQuestion(img5-1);
				}
				break;
			}
			return true;
			}
		};
		
		if(localLanguage==0){			
			changLang.setCurrentTileIndex(0);
		}else{		
			changLang.setCurrentTileIndex(1);
		}
		
		this.attachChild(changLang);
		this.registerTouchArea(changLang);
		//-----------------------------------------------------------------------------------------------------
		
		
		//-- background music toggle --------------------------------------------------------------------------		

  		notes = new Sprite(camera.getWidth()-resourcesManager.btSoundsTR.getWidth()/2, camera.getHeight()-resourcesManager.btSoundsTR.getHeight()/2, resourcesManager.btSoundsTR, vbom){
        				
  			@Override
  			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
  			switch(pSceneTouchEvent.getAction()){
  			case TouchEvent.ACTION_DOWN:

  				if (pageCounter!=800 && answeredOrNot==false){
  				readQuestion(img5-1);
  				}
  				break;
  			case TouchEvent.ACTION_UP:

  				break;
  			}
  			return true;
        	}
  		};
  		this.attachChild(notes);
  		this.registerTouchArea(notes);
  		
  		//-- mute notes ------------------------------------------------------------------------------------------
  		notesMute = new Sprite(camera.getWidth()-resourcesManager.btSoundsMuteTR.getWidth()/2, camera.getHeight()-resourcesManager.btSoundsMuteTR.getHeight()/2, resourcesManager.btSoundsMuteTR, vbom);
  		this.attachChild(notesMute);
  		notesMute.setVisible(muteOrNot);
  		//--------------------------------------------------------------------------------------------------------

  		//-- left & right arrows
  		//leftArrowX = 5+resourcesManager.btLeftArrowTR.getWidth()/2;
        //leftArrowY = ((camera.getHeight()-40)/2);
        rightArrowX = camera.getWidth()-5-resourcesManager.btRightArrowTR.getWidth()/2;
        rightArrowY =((camera.getHeight()-40)/2);
        
        //leftArrow = new AnimatedSprite(leftArrowX,leftArrowY, resourcesManager.btLeftArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
        rightArrow = new AnimatedSprite(rightArrowX,rightArrowY, resourcesManager.btRightArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
		
        //this.attachChild(leftArrow);
        this.attachChild(rightArrow);

       hideArrows();

       hideImg();

       displayImg(img1,img2,img3,img4,img5);

       
       imTrue1	 = new Sprite(197, 330, resourcesManager.imTrueTR, vbom); 
       imTrue2	 = new Sprite(528, 330, resourcesManager.imTrueTR, vbom); 
       imTrue3	 = new Sprite(197, 112, resourcesManager.imTrueTR, vbom); 
       imTrue4	 = new Sprite(528, 112, resourcesManager.imTrueTR, vbom); 
       imFalse1 = new Sprite(197, 330, resourcesManager.imFalseTR, vbom); 
       imFalse2 = new Sprite(528, 330, resourcesManager.imFalseTR, vbom); 
       imFalse3 = new Sprite(197, 112, resourcesManager.imFalseTR, vbom); 
       imFalse4 = new Sprite(528, 112, resourcesManager.imFalseTR, vbom); 
       this.attachChild(imTrue1);
       this.attachChild(imTrue2);
       this.attachChild(imTrue3);
       this.attachChild(imTrue4);
       this.attachChild(imFalse1);
       this.attachChild(imFalse2);
       this.attachChild(imFalse3);
       this.attachChild(imFalse4);
       hideTrueFalse();
       readQuestion(img5-1);      
       
       this.registerUpdateHandler(new TimerHandler(1f, true,new ITimerCallback(){
       	@Override
           public void onTimePassed(TimerHandler pTimerHandler) {
                   adsDuration--;
                   //timeText.setText(String.valueOf(gameDuration));  
                   if(adsDuration==0){
                    unregisterUpdateHandler(pTimerHandler);
                    //GameOver();
                	showInterstitialAds();
                	adsDuration=60;
                    }        
                  pTimerHandler.reset();
       	}
       }));
	}
	
	
	@Override
	public void onBackKeyPressed() {
		try {
			gameCompleteText.setText(""); 
			replayText.setText(""); 
			detachChild(gamecompleteHUD);
		}catch (Exception ex){
			ex.printStackTrace();			
		}		
		if (localLanguage==0){
		quitOrNotText.setText("Vous voulez vraiment quitter !"); 
		}else{
		quitOrNotText.setText("Do You Really Want To Exit !"); 	
		}
		this.setChildScene(goMenuScene, false, true, true);
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_ANIMALGAME;
	}

	
	
	
	@Override
	public void disposeScene() {
		this.detachSelf();
		this.dispose();
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {

		//final IShape face = (IShape) pTouchArea;

		AnimatedSprite capturedSprite = (AnimatedSprite) pTouchArea;

    	if(capturedSprite!=null){

       /* if(capturedSprite.getTiledTextureRegion() == resourcesManager.btLeftArrowTR){

    		if(pSceneTouchEvent.isActionDown()) {
    			try{
    			hideImg();
    			
    			if(PageCounter==0){
    				PageCounter=48;
				}else{
					PageCounter=PageCounter-1;
				}
    				displayImg(PageCounter);
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    			return true;
			} else {
				return false;
			}

		}else*/ if(capturedSprite.getTiledTextureRegion() == resourcesManager.btRightArrowTR){

			if(pSceneTouchEvent.isActionDown()) {
				try{
					pageCounter+=1;
					hideImg();
					hideTrueFalse();
					unregisterTouchArea(rightArrow);
					hideArrows();
					initScene();
					/*
				if(PageCounter==48){
					PageCounter=0;
				}else{
					PageCounter=PageCounter+1;
					
				}*/
				//displayImg(PageCounter);
				}catch(Exception ex){
    				ex.printStackTrace();
    			}
				return true;

			} else {
				return false;
			}

		}
		else{

    	}

    	}
    	else{
    		
    		//readQuestion(img5-1);
    	}
    	
    	/*
	    if (pageCounter==100){
	    	pageCounter=0;
	    }    	
    	*/
		return true;

	}
	
	

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		
		switch(pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_UP:
			
		if (pageCounter!=800 && answeredOrNot==false){
			int c=0;
			float ca=pSceneTouchEvent.getX();
			float cb=pSceneTouchEvent.getY();
			c=getCheckedImg(ca,cb);

			if (c==0){
			//readQuestion(img5-1);
			}else{			
				checkAnswer(c);
			}
		}
		
		return false;
		}
		return false;
	}

	@Override
	public void onPauseScene() {
		
	}

	@Override
	public void onResumeScene() {
		
	}
	

	private void hideImg() {
		 
		img_001.setVisible(false);
		img_002.setVisible(false);
		img_003.setVisible(false);
		img_004.setVisible(false);
		img_005.setVisible(false);
		img_006.setVisible(false);
		img_007.setVisible(false);
		img_008.setVisible(false);
		img_009.setVisible(false);
		img_010.setVisible(false);
		img_011.setVisible(false);
		/*img_012.setVisible(false);
		img_013.setVisible(false);
		img_014.setVisible(false);
		img_015.setVisible(false);
		img_016.setVisible(false);
		img_017.setVisible(false);
		img_018.setVisible(false);
		img_019.setVisible(false);
		img_020.setVisible(false);
		img_021.setVisible(false);
		img_022.setVisible(false);
		img_023.setVisible(false);
		img_024.setVisible(false);
		img_025.setVisible(false);
		img_026.setVisible(false);
		img_027.setVisible(false);
		img_028.setVisible(false);
		img_029.setVisible(false);
		img_030.setVisible(false);
		img_031.setVisible(false);
		img_032.setVisible(false);
		img_033.setVisible(false);
		img_034.setVisible(false);
		img_035.setVisible(false);
		img_036.setVisible(false);
		img_037.setVisible(false);
		img_038.setVisible(false);
		img_039.setVisible(false);
		img_040.setVisible(false);
		img_041.setVisible(false);
		img_042.setVisible(false);
		img_043.setVisible(false);		 
		img_044.setVisible(false);
		img_045.setVisible(false);
		img_046.setVisible(false);
		img_047.setVisible(false);
		img_048.setVisible(false);
		img_049.setVisible(false);	
		*/
	}
	
	private int [] getFour(){
				
		int rnd;
		int selectedrnd;
		
		  Random rando=new Random();
		  int[] randNo = new int[6];
		  ArrayList<Integer> numbers = new ArrayList<Integer>();
		  for (int k=0 ; k< 4;k++)
		  {
		   rnd = rando.nextInt(10) ;
		   
		   if(k==0)
		   {
		    randNo[0] = rnd+1;
		    numbers.add(randNo[0]);
		   }
		   else
		   {
		    while(numbers.contains(Integer.valueOf(rnd+1)))	
		    {
		     rnd = rando.nextInt(10);
		    }
		    randNo[k] = rnd+1;
		    numbers.add(randNo[k]);
		   }
		  }
		
		//random number from 1 to 4
		Random selectrand = new Random();
		selectedrnd = selectrand.nextInt(4) ;
		
		int selectednumber=numbers.get(selectedrnd);
		randNo[4] = selectedrnd;
		randNo[5] = selectednumber;
	
		return randNo;
	}
	
	
	private void displayImg(int iimg1,int iimg2,int iimg3,int iimg4,int iimg5){
		
		Sprite s1 =  getImg(iimg1-1);
		s1.setPosition(207, 317);
		s1.setVisible(true);
		Sprite s2 =  getImg(iimg2-1);
		s2.setPosition(533, 317);
		s2.setVisible(true);
		Sprite s3 =  getImg(iimg3-1);
		s3.setPosition(207, 112);
		s3.setVisible(true);
		Sprite s4 =  getImg(iimg4-1);
		s4.setPosition(533, 112);
		s4.setVisible(true);
		
				
	} 
	
	private Sprite getImg(int num) {
	Sprite a=null;
	switch(num){
		
	case 0:			
		a= img_001;			
		break;
	case 1:			
		a= img_002;
		break;		
	case 2:		
		a= img_003;
		break;
	case 3:
		a= img_004;
		break;
	case 4:
		a= img_005;
		break;
	case 5:
		a= img_006;
		break;
	case 6:
		a= img_007;
		break;
	case 7:
		a= img_008;
		break;
	case 8:
		a= img_009;
		break;
	case 9:
		a= img_010;
		break;
	case 10:
		a= img_011;
		break;
	/*	
	case 11:
		a= img_012;
		break;
	case 12:
		a= img_013;
		break;
	case 13:
		a= img_014;
		break;
	case 14:
		a= img_015;
		break;
	case 15:
		a= img_016;
		break;
	case 16:
		a= img_017;
		break;
	case 17:
		a= img_018;
		break;
	case 18:
		a= img_019;
		break;
	case 19:
		a= img_020;
		break;
	case 20:
		a= img_021;
		break;
	case 21:
		a= img_022;
		break;
	case 22:
		a= img_023;
		break;
	case 23:
		a= img_024;
		break;
	case 24:
		a= img_025;
		break;
	case 25:
		a= img_026;
		break;
	case 26:
		a= img_027;
		break;
	case 27:
		a= img_028;
		break;
	case 28:
		a= img_029;
		break;
	case 29:
		a= img_030;
		break;
	case 30:
		a= img_031;
		break;
	case 31:
		a= img_032;
		break;
	case 32:
		a= img_033;
		break;
	case 33:
		a= img_034;
		break;
	case 34:
		a= img_035;
		break;
	case 35:
		a= img_036;
		break;
	case 36:
		a= img_037;
		break;
	case 37:
		a= img_038;
		break;
	case 38:
		a= img_039;
		break;
	case 39:
		a= img_040;
		break;
	case 40:
		a= img_041;
		break;
	case 41:
		a= img_042;
		break;
	case 42:
		a= img_043;
		break;
	case 43:
		a= img_044;
		break;
	case 44:
		a= img_045;
		break;
	case 45:
		a= img_046;
		break;
	case 46:
		a= img_047;
		break;
	case 47:
		a= img_048;
		break;
	case 48:
		a= img_049;
		break;
		*/
	}
		
		return a;
	}
	

	private void showArrows() {

		//leftArrowVisible=true;
		rightArrowVisible=true;
		//leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		//this.registerTouchArea(leftArrow);
		this.registerTouchArea(rightArrow);

	}
	
	private void hideArrows() {
		// TODO Auto-generated method stub
		//this.detachChild(leftArrow);
        //this.detachChild(rightArrow);
		//leftArrowVisible=false;
		rightArrowVisible=false;
		//leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		//unregisterTouchArea(leftArrow);
		unregisterTouchArea(rightArrow);
		
	}

	private void goMenu() {
		
		try{
			
			//unregisterTouchArea(leftArrow);
			unregisterTouchArea(rightArrow);
			unregisterTouchArea(btHome);
			unregisterTouchArea(btMenu);
			unregisterTouchArea(notes);
			
			try{					
				resourcesManager.exitSound.play();					
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
			localIdProfile=resourcesManager.getProfile();
			switch(localIdProfile){
			case 0:
				SceneManager.getInstance().loadMainMenuScene(engine);
				break;
			case 1:
				SceneManager.getInstance().loadMainMenu24Scene(engine);
				break;
			case 2:
				SceneManager.getInstance().loadMainMenu4Scene(engine);
				break;
			}	
			
		totalScoreText.setText("");		
		usernameAText.setText("");		
		timeText.setText("");
		quitOrNotText.setText("");
		detachChild(gameHUD);		
		detachChild(quitgameHUD);
		detachChild(gamecompleteHUD);
		pageCounter = 100;
		resourcesManager.unloadColorGameResources();
		//disposeScene();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	private void returnGame() {
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		this.clearChildScene();
		if (pageCounter<maxGamePages){	
		}else{		
		//clearChildScene();
		initVariables(1,0,muteOrNot,musicOrNot,0);
		initScene();	
		}
		
	}

	private void pauseScene() {
		
		this.setChildScene(pauseScene, false, true, true);
		try{				
			//resourcesManager.mMusic.pause();
			//gameState = STATE_PAUSE;
		} catch (Exception ex){
				ex.printStackTrace();
		}
	
	}

	private void resumeScene() {

		this.clearChildScene();
		//gameState = STATE_RUNNING;
		try{
			if (muteOrNot==true){
				//resourcesManager.mMusic.pause();
			}else{
				//resourcesManager.mMusic.play();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}	
	}

	
	private void readQuestion(int simg){
		
		if(muteOrNot==false){
			if (localLanguage==0){
			resourcesManager.tts.speak("trouvé la couleur, "+textImgsFrRead[simg],TextToSpeech.QUEUE_FLUSH,null);
			}else if(localLanguage==1){
			resourcesManager.tts.speak("where is color, "+textImgsEn[simg],TextToSpeech.QUEUE_FLUSH,null);
			}
		}else{}
	}

	private void changeLanguage(String newLanguage){

		if (newLanguage.contentEquals("FR")){
			resourcesManager.tts.setLanguage(Locale.FRENCH);
			localLanguage=0;
		} else if (newLanguage.contentEquals("UK")){
			resourcesManager.tts.setLanguage(Locale.ENGLISH);	
			localLanguage=1;
		}
		
		
	}
	
	private void initLanguage(){
	prefLanguage = ((GameActivity) activity).getPreference("language", "1");
	if (prefLanguage.contentEquals("1")){
		resourcesManager.tts.setLanguage(Locale.FRENCH);
		localLanguage=0;
	} else if (prefLanguage.contentEquals("2")){
		resourcesManager.tts.setLanguage(Locale.ENGLISH);	
		localLanguage=1;
	} 
	}
	
	private void initSound(){
	try{
		
        prefLearnSounds = ((GameActivity) activity).getPreference("learnsound", true);
		if (prefLearnSounds==false){
			muteOrNot=true;
		} else {
			muteOrNot=false;
		}
    	}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void initMusic(){
		try{
			
	        prefMusic = ((GameActivity) activity).getPreference("gamemusic", true);
			if (prefMusic==false){
				muteOrNot=true;
			} else {
				muteOrNot=false;
			}
	    	}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	
	private void playSelectSound(){
		try{
				if(!muteOrNot){	
				resourcesManager.mSelectSound.play();
				}
				}catch(Exception ex){
					ex.printStackTrace();                    				
				}
	}
	
	
	private int getCheckedImg(float a,float b) {
		int c=0;
		
		if (a>47 && a<347){
			
			if (b>22 && b<202){
				c=3;
			}else if (b>240 && b<420){
				c=1;
			}else{c=0;
			}
		}else if (a>378 && a<678){
			
			if (b>22 && b<202){
				c=4;
			}else if (b>240 && b<420){
				c=2;
			}else{
				c=0;
			}
			}else{
				c=0;
			}
		
		return c;
	}
	
	private void checkAnswer(int c) {
		
			if (c == img0+1) {

				answerP("bravo","good");
				
				clickedImg.clear();
				totalScore+=levelScore;
				titleText.setText("score:"+totalScore);
				showArrows();
				answeredOrNot=true;
				switch(c){
				case 1:
					imTrue1.setVisible(true);
					break;
				case 2:
					imTrue2.setVisible(true);
					break;
				case 3:
					imTrue3.setVisible(true);
					break;
				case 4:
					imTrue4.setVisible(true);
					break;
				}
			} else {

				answerP("non","no");
				
				if (!clickedImg.contains(c)){
					clickedImg.add(c);
				
				if(levelScore>9){
				levelScore-=10;
				//titleText.setText("score:"+totalScore);
				}else{
					
				}
				switch(c){
				case 1:
					imFalse1.setVisible(true);
					break;
				case 2:
					imFalse2.setVisible(true);
					break;
				case 3:
					imFalse3.setVisible(true);
					break;
				case 4:
					imFalse4.setVisible(true);
					break;
				}
				}else{}
			}	
			
			
		
	}
	
	private void hideTrueFalse() {
		imTrue1.setVisible(false);
		imTrue2.setVisible(false);
		imTrue3.setVisible(false);
		imTrue4.setVisible(false);
		imFalse1.setVisible(false);
		imFalse2.setVisible(false);
		imFalse3.setVisible(false);
		imFalse4.setVisible(false);
	}
	
	private void answerP(String iresponsefr, String iresponseuk){
		if (muteOrNot == false) {
			if (localLanguage == 0) {
				resourcesManager.tts.speak(iresponsefr, TextToSpeech.QUEUE_FLUSH,
						null);
			} else if (localLanguage == 1) {
				resourcesManager.tts.speak(iresponseuk, TextToSpeech.QUEUE_FLUSH,
						null);

			} else {
			}
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
	private void initVariables(int ilevel, int itotalscore,
			boolean imuteornot,boolean imusicOrNot, int ipagecounter) {
		
		levelID=ilevel;
		totalScore=itotalscore;		
       	muteOrNot=imuteornot;  
       	musicOrNot=imusicOrNot;  
    	pageCounter = ipagecounter;
        
        
	}	
	
	private void initLevelVariables(int iimg1,int iimg2, int iimg3, 
			int iimg4, int iimg0, int iimg5) {
		
		img1 = iimg1;
		img2 = iimg2;
		img3 = iimg3;
		img4 = iimg4;
		img0 = iimg0;
		img5 = iimg5;
		
	}

	private void initScene() {	
		try{
		levelID+=1;

		if (pageCounter<maxGamePages){
		initVariables(levelID,totalScore,muteOrNot,musicOrNot,pageCounter);
		
		int [] fourSelecteds = getFour();
		initLevelVariables(fourSelecteds[0],fourSelecteds[1],fourSelecteds[2],fourSelecteds[3],fourSelecteds[4],fourSelecteds[5]);
		
		createScene();
		populateScene();
		
		if (this.pageCounter % 2 == 1){
			hideAds();
		}else{
			showAds();
		}
		
		}else{
			
			if (totalScore>255){
				try{					
					resourcesManager.bravokidsSound.play();					
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}else if (totalScore<255){
				try{					
					resourcesManager.boooSound.play();					
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
			
			//go to win scene
			if (localLanguage==0){
			gameCompleteText.setText("votre score est :"+totalScore);
			replayText.setText("voulez-vous rejouer ?");
			}else{
			gameCompleteText.setText("your score is :"+totalScore);	
			replayText.setText("do you want to replay ?");
			}
			this.setChildScene(winScene, false, true, true);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


}
