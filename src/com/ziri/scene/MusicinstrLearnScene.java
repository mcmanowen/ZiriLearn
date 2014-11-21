package com.ziri.scene;

import java.util.Locale;

import org.andengine.entity.primitive.Rectangle;
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
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import android.speech.tts.TextToSpeech;

import com.ziri.GameActivity;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class MusicinstrLearnScene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener  {
	
	//---------------------------------------------
	// PREFERENCES
	//---------------------------------------------
	
	String prefLocalPlayer,prefVisitorPlayer,prefLanguage;
	boolean prefMusic,prefGameSounds,prefLearnSounds;
	
	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	private int localIdProfile; 
	int PageCounter;
	int imgNumber;
	boolean muteOrNot;
	Text imgNameTextFr;
	int localLanguage;
    //-----------------------------------------------------------
    //--text to speech numbers
    static final String[] textImgsFr = {"arpa","guitare","castagnettes", "basse","contrebasse","cymbales","ocarina","phonographe","batterie","flute","metronome","tam tam","cor d'harmonie", "harpe","maracas","clarinette","guitare électrique","saxophone","triangle","trombone","trompette","violon","giraffe"};
    static final String[] textImgsFrRead = {"arpa","guitare","castaniettes", "basse","contrebasse","cymbales","ocarina","phonograph","batterie","flute","metronome","tamme tamme","cor d'harmonie", "harpe","maracasse","clarinette","guitare éléctrique","saxophone","triangle","trombone","trompette","violon","giraffe"};
    static final String[] textImgsEn = {"arpa","guitare","castanets", "bass","contrabass","cymbals","ocarina","phonograph","drum kit","flute","metronome","tam tam","horn", "harpe","maracas","clarinet","electric guitare","saxophone","triangle","trombone","trumpet","violin","giraffe"};


    //-- sprite imgs
	Sprite
	
	img_001,img_002,img_003,img_004,
    img_005,img_006,img_007,img_008,
    img_009,img_010,
    img_011,img_012,img_013,img_014,
    img_015,img_016,img_017,img_018,
    img_019,img_020,
    img_021,img_022/*,img_023,img_024,
    img_025,img_026,img_027,img_028,
    img_029,img_030,
    img_031,img_032,img_033,img_034,
    img_035,img_036,img_037,img_038,
    img_039,img_040,
    img_041,img_042,img_043,img_044,
    img_045,img_046,img_047,img_048,
    img_049,img_050,
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

    

	//-- control buttons
	public boolean leftArrowVisible;
	public boolean rightArrowVisible;
	float leftArrowX,leftArrowY,rightArrowX,rightArrowY;
	int nextPage,backPage;

	//-- sprite arrows -----------------------------------------------

	AnimatedSprite leftArrow, rightArrow;
	

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
		// TODO Auto-generated method stub

		this.setBackground(new Background(1,1,1));
    	this.setOnSceneTouchListener((IOnSceneTouchListener) this);
    	this.setOnAreaTouchListener((IOnAreaTouchListener) this);

    	PageCounter = 100;
    	
    	//-- init language ------------------------------------------------------------------
    	    		
    		initLanguage();
    	
    	//-----------------------------------------------------------------------------------

    	
    	//-- init sounds --------------------------------------------------------------------
    	
    		initSound();
    	/*	
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
    	*/
    	//----------------------------------------------------------------------------------
    	

    	leftArrowVisible = rightArrowVisible = true;
    	
    	autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-1.0f, new Sprite(0, 80, this.mAlphabetParallaxLayerMid, vertexBufferObjectManager)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, camera.getHeight() - this.mAlphabetParallaxLayerFront.getHeight(), this.mAlphabetParallaxLayerFront, vertexBufferObjectManager)));
		this.setBackground(autoParallaxBackground);
		
		//blackBoard = new Sprite(camera.getWidth()/2,(camera.getHeight()-40)*.5f,resourcesManager.blackBoardTR,vbom);
		//this.attachChild(blackBoard);
		/*
    	Rectangle top = new Rectangle(camera.getWidth()/2,(camera.getHeight()/2)-20,camera.getWidth(),camera.getHeight()-40,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(26,175,239));
        attachChild(top);
        
		Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight()-20,camera.getWidth(),40,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(1,10,20));
        attachChild(top);
        */
        Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight()-20,camera.getWidth(),40,vbom);
        top.setColor(new Color(1,10,20));
        attachChild(top);
		
        final Text titleText = new Text(camera.getWidth()/4, 455, resourcesManager.mBitmapFont, "ZiriKids", vbom);
        titleText.setColor(Color.BLUE);
        this.attachChild(titleText);
		
        imgNameTextFr = new Text(camera.getWidth()/2, 80, resourcesManager.mBitmapFont, "nnnnnnnnnnnnnnnnnnnnnn", vbom);
        imgNameTextFr.setColor(Color.BLUE);
        
        imgNameTextFr.setHorizontalAlign(HorizontalAlign.CENTER);
		this.attachChild(imgNameTextFr);
		try{
		imgNameTextFr.setText("");
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
		img_012	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_012TR, vbom); 
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
		/*img_023	 = new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.img_023TR, vbom); 
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
		this.attachChild(img_012);
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
		/*this.attachChild(img_023);
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
					displayImg(0);

			        PageCounter = 0;
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
				readimgName();
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
  				playSelectSound();
  				if(muteOrNot==true) {
  					playSelectSound();
  					muteOrNot=false;
  					
  				} else {
  					muteOrNot=true;
  				}
  				
  				notesMute.setVisible(muteOrNot);
  				

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
  		leftArrowX = 5+resourcesManager.btLeftArrowTR.getWidth()/2;
        leftArrowY = ((camera.getHeight()-40)/2);
        rightArrowX = camera.getWidth()-5-resourcesManager.btRightArrowTR.getWidth()/2;
        rightArrowY =((camera.getHeight()-40)/2);
        
        leftArrow = new AnimatedSprite(leftArrowX,leftArrowY, resourcesManager.btLeftArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
        rightArrow = new AnimatedSprite(rightArrowX,rightArrowY, resourcesManager.btRightArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
		
        this.attachChild(leftArrow);
        this.attachChild(rightArrow);

       showArrows();

       hideImg();
       displayImg(0);
	}
	
	
	private void hideImg() {
		// TODO Auto-generated method stub
		 
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
		img_012.setVisible(false);
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
		/*img_023.setVisible(false);
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
		*/
	}
	private void displayImg(int num) {

		switch(num){

		case 0:
			img_001.setVisible(true);
			PageCounter=0;
			imgNumber=0;
			
			readimgName();
			break;
		case 1:
			img_002.setVisible(true);
			PageCounter=1;
			imgNumber=1;
			
			readimgName();
			break;
		case 2:
			img_003.setVisible(true);
			PageCounter=2;
			imgNumber=2;
			
			readimgName();
			break;
		case 3:
			img_004.setVisible(true);
			PageCounter=3;
			imgNumber=3;
			
			readimgName();
			break;
		case 4:
			img_005.setVisible(true);
			PageCounter=4;
			imgNumber=4;
			readimgName();
			break;
		case 5:

			img_006.setVisible(true);
			PageCounter=5;
			imgNumber=5;
			readimgName();
			break;
		case 6:
			img_007.setVisible(true);
			PageCounter=6;
			imgNumber=6;
			readimgName();
			break;
		case 7:
			img_008.setVisible(true);
			PageCounter=7;
			imgNumber=7;
			readimgName();
			break;
		case 8:
			img_009.setVisible(true);
			PageCounter=8;
			imgNumber=8;
			readimgName();
			break;
		case 9:
			img_010.setVisible(true);
			PageCounter=9;
			imgNumber=9;
			readimgName();
			break;
		case 10:
			img_011.setVisible(true);
			PageCounter=10;
			imgNumber=10;
			readimgName();
			break;
		case 11:
			img_012.setVisible(true);
			PageCounter=11;
			imgNumber=11;
			readimgName();
			break;
		case 12:
			img_013.setVisible(true);
			PageCounter=12;
			imgNumber=12;
			readimgName();
			break;
		case 13:
			img_014.setVisible(true);
			PageCounter=13;
			imgNumber=13;
			readimgName();
			break;
		case 14:
			img_015.setVisible(true);
			PageCounter=14;
			imgNumber=14;
			readimgName();
			break;
		case 15:
			img_016.setVisible(true);
			PageCounter=15;
			imgNumber=15;
			readimgName();
			break;
		case 16:
			img_017.setVisible(true);
			PageCounter=16;
			imgNumber=16;
			readimgName();
			break;
		case 17:
			img_018.setVisible(true);
			PageCounter=17;
			imgNumber=17;
			readimgName();
			break;
		case 18:
			img_019.setVisible(true);
			PageCounter=18;
			imgNumber=18;
			readimgName();
			break;
		case 19:
			img_020.setVisible(true);
			PageCounter=19;
			imgNumber=19;
			readimgName();
			break;
		case 20:
			img_021.setVisible(true);
			PageCounter=20;
			imgNumber=20;
			readimgName();
			break;
		case 21:
			img_022.setVisible(true);
			PageCounter=21;
			imgNumber=21;
			readimgName();
			break;
		/*case 22:
			img_023.setVisible(true);
			PageCounter=22;
			imgNumber=22;
			readimgName();
			break;
		case 23:
			img_024.setVisible(true);
			PageCounter=23;
			imgNumber=23;
			readimgName();
			break;
		case 24:
			img_025.setVisible(true);
			PageCounter=24;
			imgNumber=24;
			
			readimgName();
			break;
		case 25:
			img_026.setVisible(true);
			PageCounter=25;
			imgNumber=25;
			
			readimgName();
			break;
		case 26:
			img_027.setVisible(true);
			PageCounter=26;
			imgNumber=26;
			
			readimgName();
			break;
		case 27:
			img_028.setVisible(true);
			PageCounter=27;
			imgNumber=27;
			readimgName();
			break;
		case 28:
			img_029.setVisible(true);
			PageCounter=28;
			imgNumber=28;
			readimgName();
			break;
		case 29:
			img_030.setVisible(true);
			PageCounter=29;
			imgNumber=29;
			readimgName();
			break;
		case 30:
			img_031.setVisible(true);
			PageCounter=30;
			imgNumber=30;
			readimgName();
			break;
		case 31:
			img_032.setVisible(true);
			PageCounter=31;
			imgNumber=31;
			readimgName();
			break;

		case 32:

			img_033.setVisible(true);
			PageCounter=32;
			imgNumber=32;
			readimgName();
			break;
		case 33:
			img_034.setVisible(true);
			PageCounter=33;
			imgNumber=33;
			readimgName();
			break;
		case 34:
			img_035.setVisible(true);
			PageCounter=34;
			imgNumber=34;
			readimgName();
			break;
		case 35:
			img_036.setVisible(true);
			PageCounter=35;
			imgNumber=35;
			readimgName();
			break;
		case 36:
			img_037.setVisible(true);
			PageCounter=36;
			imgNumber=36;
			readimgName();
			break;
		case 37:
			img_038.setVisible(true);
			PageCounter=37;
			imgNumber=37;
			readimgName();
			break;
		case 38:
			img_039.setVisible(true);
			PageCounter=38;
			imgNumber=38;
			readimgName();
			break;

		case 39:
			img_040.setVisible(true);
			PageCounter=39;
			imgNumber=39;
			readimgName();
			break;
		case 40:
			img_041.setVisible(true);
			PageCounter=40;
			imgNumber=40;
			readimgName();
			break;
		case 41:
			img_042.setVisible(true);
			PageCounter=41;
			imgNumber=41;
			readimgName();
			break;
		case 42:
			img_043.setVisible(true);
			PageCounter=42;
			imgNumber=42;
			readimgName();
			break;
		*/
		}


	}


	private void showArrows() {

		leftArrowVisible=true;
		rightArrowVisible=true;
		leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		this.registerTouchArea(leftArrow);
		this.registerTouchArea(rightArrow);

	}


	@Override
	public void onBackKeyPressed() {
		unregisterTouchArea(leftArrow);
		unregisterTouchArea(rightArrow);
		unregisterTouchArea(btHome);
		unregisterTouchArea(btMenu);
		unregisterTouchArea(notes);
		
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
	
		resourcesManager.unloadMusicinstrLearnResources();
        PageCounter = 100;
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_ANIMALLEARN;
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

        if(capturedSprite.getTiledTextureRegion() == resourcesManager.btLeftArrowTR){

    		if(pSceneTouchEvent.isActionDown()) {
    			try{
    			hideImg();
    			
    			if(PageCounter==0){
    				PageCounter=21;
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

		}else if(capturedSprite.getTiledTextureRegion() == resourcesManager.btRightArrowTR){

			if(pSceneTouchEvent.isActionDown()) {
				try{
					hideImg();
				if(PageCounter==21){
					PageCounter=0;
				}else{
					PageCounter=PageCounter+1;
				}
				displayImg(PageCounter);
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
    		
    		//readimgName();
    	}
    	
	    if (PageCounter==100){
	    	PageCounter=0;
	    }    	
    	
		return true;

	}

	private void readimgName() {
		/*
		 * if we want to modify the default value (consume lot of resources)
		try{
			if (prefLanguage.contentEquals("1")){
			imgNameTextFr.setText(textImgsFr[imgNumber]);
			}else{
			imgNameTextFr.setText(textImgsEn[imgNumber]);
			}
			
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
		if(muteOrNot==false){
			if (prefLanguage.contentEquals("1")){
			resourcesManager.tts.speak(textImgsFrRead[imgNumber],TextToSpeech.QUEUE_FLUSH,null);
			}else{
			resourcesManager.tts.speak(textImgsEn[imgNumber],TextToSpeech.QUEUE_FLUSH,null);
			}
		}else{}
		*/
		try{
			if (localLanguage==0){
			imgNameTextFr.setText(textImgsFr[imgNumber]);
			}else if (localLanguage==1){
			imgNameTextFr.setText(textImgsEn[imgNumber]);
			}
			
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
		if(muteOrNot==false){
			if (localLanguage==0){
			resourcesManager.tts.speak(textImgsFrRead[imgNumber],TextToSpeech.QUEUE_FLUSH,null);
			}else if(localLanguage==1){
			resourcesManager.tts.speak(textImgsEn[imgNumber],TextToSpeech.QUEUE_FLUSH,null);
			}
		}else{}
		
		
		
	}
	
	private void changeLanguage(String newLanguage){
		/*
		 * if we want to modify the default value (consume lot of resources)
		if (newLanguage.contentEquals("FR")){
			((GameActivity) activity).setStringPreferences("language", "1");
		} else if (newLanguage.contentEquals("UK")){
			((GameActivity) activity).setStringPreferences("language", "2");
		}
		
		prefLanguage = ((GameActivity) activity).getPreference("language", "1");
		if (prefLanguage.contentEquals("1")){
			resourcesManager.tts.setLanguage(Locale.FRENCH);
		} else if (prefLanguage.contentEquals("2")){
			resourcesManager.tts.setLanguage(Locale.ENGLISH);	
		}
		*/
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
	
	public void playSelectSound(){
		try{
				if(!muteOrNot){	
				resourcesManager.mSelectSound.play();
				}
				}catch(Exception ex){
					ex.printStackTrace();                    				
				}
	}
	
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		
		if (PageCounter!=100){
			
			readimgName();
		}
		
		return false;
	}

	@Override
	public void onPauseScene() {
		
	}

	@Override
	public void onResumeScene() {
		
	}


}