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
import org.andengine.entity.shape.IShape;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;


import android.speech.tts.TextToSpeech;

import com.ziri.GameActivity;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager.SceneType;

public class AlphabetLearnScene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener  {
	
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
	boolean muteOrNot;
	boolean freezeOrNot;
	int localLanguage;
    //-- alphabets positions
    float letterAx,letterAy,letterBx,letterBy,letterCx,letterCy,letterDx,letterDy,letterEx,letterEy,letterFx,letterFy,
    letterGx,letterGy,letterHx,letterHy,letterIx,letterIy,letterJx,letterJy,letterKx,letterKy,letterLx,letterLy,
    letterMx,letterMy,letterNx,letterNy,letterOx,letterOy,letterPx,letterPy,letterQx,letterQy,letterRx,letterRy,
    letterSx,letterSy,letterTx,letterTy,letterUx,letterUy,letterVx,letterVy,letterWx,letterWy,letterXx,letterXy,
    letterYx,letterYy,letterZx,letterZy;
    
    //-----------------------------------------------------------
    //--text to speech numbers
    static final String[] textAlphabetsFr = {"a","b","c", "d","e","f","g","h","i","j","k","l","m", "n","o","p","q","r","s","t","u","v","w", "x","y","z"};
    static final String[] textAlphabetsReadFr = {"a","b","c", "d","eux","f","g","h","i","j","k","l","m", "n","o","p","q","r","s","t","u","v","double v", "x","igrek","z"};
    static final String[] textAlphabetsReadEn = {"a","b","c", "d","e","f","g","h","i","j","k","l","m", "n","o","p","q","r","s","t","u","v","w", "x","why","z"};
    
   
    //-- sprite alphabet
	Sprite letterA,letterB,letterC,letterD,letterE,letterF,letterG,letterH,
	letterI,letterJ,letterK,letterL,letterM,letterN,letterO,letterP,letterQ,
	letterR,letterS,letterT,letterU,letterV,letterW,letterX,letterY,letterZ;
	
	//-- control buttons 
	public boolean leftArrowVisible;
	public boolean rightArrowVisible;
	float leftArrowX,leftArrowY,rightArrowX,rightArrowY;
	int nextPage,backPage;
	
	//-- sprite arrows -----------------------------------------------
	   
	AnimatedSprite leftArrow, rightArrow;
	
	
	//-- menu bar ----------------------------------------------------
	
	Sprite notes,notesMute,homeFreeze;
 	AnimatedSprite btMenu;
	AnimatedSprite btHome;
	TiledSprite changLang;
	//----------------------------------------------------------------
	
	Sprite blackBoard;
	AutoParallaxBackground alphabetautoParallaxHomeBackground,alphabetautoParallaxNoHomeBackground;
	ParallaxEntity backgoroundHomeParallax;
	ParallaxEntity backgroundNoHomeParallax;
	//---------------------------------------------
	// METHODS FROM SUPERCLASS
	//---------------------------------------------
	
	
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		
		this.setBackground(new Background(1,1,1));
    	this.setOnSceneTouchListener((IOnSceneTouchListener) this);
    	this.setOnAreaTouchListener((IOnAreaTouchListener) this);
    	
    	//-- init language ------------------------------------------------------------------
		
		initLanguage();
	
		//-----------------------------------------------------------------------------------
    	/*
    	//-- init language
    	try{
    	prefLanguage = ((GameActivity) activity).getPreference("language", "1");
		if (prefLanguage.contentEquals("1")){
			resourcesManager.tts.setLanguage(Locale.FRENCH);
		} else if (prefLanguage.contentEquals("2")){
			resourcesManager.tts.setLanguage(Locale.ENGLISH);	
		}
    	}catch(Exception ex){
			ex.printStackTrace();
		}
    	*/
    	
    	PageCounter = 100;
    	
    	//-- init sounds -------------------------------------------------------------------
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
    	//-----------------------------------------------------------------------------------
    	
    	freezeOrNot=false;
    	
    	leftArrowVisible = rightArrowVisible = false;
    	
    	letterAx=70f;
        letterAy=(camera.getHeight()-40)*.78f;
        letterBx=168f;
        letterBy=(camera.getHeight()-40)*.78f;
        letterCx=259f;
        letterCy=(camera.getHeight()-40)*.78f;
        letterDx=352f;
        letterDy=(camera.getHeight()-40)*.78f;
        letterEx=438f;
        letterEy=(camera.getHeight()-40)*.78f;
        letterFx=520f;
        letterFy=(camera.getHeight()-40)*.78f;
        letterGx=602f;
        letterGy=(camera.getHeight()-40)*.78f;
        letterHx=696f;
        letterHy=(camera.getHeight()-40)*.78f;
        letterIx=765f;
        letterIy=(camera.getHeight()-40)*.78f;
        
        letterJx=47f;
        letterJy=(camera.getHeight()-40)*.53f;
        letterKx=133f;
        letterKy=(camera.getHeight()-40)*.53f;
        letterLx=219f;
        letterLy=(camera.getHeight()-40)*.53f;
        letterMx=308f;
        letterMy=(camera.getHeight()-40)*.53f;
        letterNx=402f;
        letterNy=(camera.getHeight()-40)*.53f;
        letterOx=491f;
        letterOy=(camera.getHeight()-40)*.53f;
        letterPx=577f;
        letterPy=(camera.getHeight()-40)*.53f;
        letterQx=656f;
        letterQy=(camera.getHeight()-40)*.53f;
        letterRx=746f;
        letterRy=(camera.getHeight()-40)*.53f;
        
        letterSx=57f;
        letterSy=(camera.getHeight()-40)*.28f;
        letterTx=143f;
        letterTy=(camera.getHeight()-40)*.28f;
        letterUx=232f;
        letterUy=(camera.getHeight()-40)*.28f;
        letterVx=326f;
        letterVy=(camera.getHeight()-40)*.28f;
        letterWx=438f;
        letterWy=(camera.getHeight()-40)*.28f;
        letterXx=546f;
        letterXy=(camera.getHeight()-40)*.28f;
        letterYx=647f;
        letterYy=(camera.getHeight()-40)*.28f;
        letterZx=738f;
        letterZy=(camera.getHeight()-40)*.28f;
        
    	
        backgoroundHomeParallax = new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom));    
    	alphabetautoParallaxHomeBackground = new AutoParallaxBackground(0, 0, 0, 5);
		//final VertexBufferObjectManager vertexBufferObjectManager = resourcesManager.activity.getVertexBufferObjectManager();
    	
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-1.0f, new Sprite(0, 80, this.mAlphabetParallaxLayerMid, vertexBufferObjectManager)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, camera.getHeight() - this.mAlphabetParallaxLayerFront.getHeight(), this.mAlphabetParallaxLayerFront, vertexBufferObjectManager)));
    	alphabetautoParallaxHomeBackground.attachParallaxEntity(backgoroundHomeParallax);
		
		this.setBackground(alphabetautoParallaxHomeBackground);
		
		/*
        backgroundNoHomeParallax = new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom));
		alphabetautoParallaxNoHomeBackground = new AutoParallaxBackground(0, 0, 0, 5);
		alphabetautoParallaxNoHomeBackground.attachParallaxEntity(backgroundNoHomeParallax);
		*/
		blackBoard = new Sprite(camera.getWidth()/2,(camera.getHeight()-40)*.5f,resourcesManager.blackBoardTR,vbom);
		this.attachChild(blackBoard);	
		
		        
        Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight()-20,camera.getWidth(),40,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(1,10,20));
        attachChild(top);
        
        final Text titleText = new Text(camera.getWidth()/4, 455, resourcesManager.mBitmapFont, "ZiriKids", vbom);
        titleText.setColor(Color.BLUE);
        this.attachChild(titleText);
		
		populateAlphabet();
		
		
	}

	private void populateAlphabet() {
		// TODO Auto-generated method stub
		
		letterA = new AnimatedSprite(letterAx,letterAy,resourcesManager.letterAyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterB = new AnimatedSprite(letterBx,letterBy,resourcesManager.letterBgreenTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterC = new AnimatedSprite(letterCx,letterCy,resourcesManager.letterCblueTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterD = new AnimatedSprite(letterDx,letterDy,resourcesManager.letterDpurpleTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterE = new AnimatedSprite(letterEx,letterEy,resourcesManager.letterEredTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterF = new AnimatedSprite(letterFx,letterFy,resourcesManager.letterForangeTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterG = new AnimatedSprite(letterGx,letterGy,resourcesManager.letterGyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterH = new AnimatedSprite(letterHx,letterHy,resourcesManager.letterHgreenTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterI = new AnimatedSprite(letterIx,letterIy,resourcesManager.letterIblueTR,resourcesManager.activity.getVertexBufferObjectManager());
		
		letterJ = new AnimatedSprite(letterJx,letterJy,resourcesManager.letterJgreenTR,resourcesManager.activity.getVertexBufferObjectManager());		
		letterK = new AnimatedSprite(letterKx,letterKy,resourcesManager.letterKpurpleTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterL = new AnimatedSprite(letterLx,letterLy,resourcesManager.letterLredTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterM = new AnimatedSprite(letterMx,letterMy,resourcesManager.letterMyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterN = new AnimatedSprite(letterNx,letterNy,resourcesManager.letterNgreenTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterO = new AnimatedSprite(letterOx,letterOy,resourcesManager.letterOredTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterP = new AnimatedSprite(letterPx,letterPy,resourcesManager.letterPyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterQ = new AnimatedSprite(letterQx,letterQy,resourcesManager.letterQpurpleTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterR = new AnimatedSprite(letterRx,letterRy,resourcesManager.letterRgreenTR,resourcesManager.activity.getVertexBufferObjectManager());
		
		letterS = new AnimatedSprite(letterSx,letterSy,resourcesManager.letterSyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterT = new AnimatedSprite(letterTx,letterTy,resourcesManager.letterTblueTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterU = new AnimatedSprite(letterUx,letterUy,resourcesManager.letterUredTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterV = new AnimatedSprite(letterVx,letterVy,resourcesManager.letterVyellowTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterW = new AnimatedSprite(letterWx,letterWy,resourcesManager.letterWorangeTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterX = new AnimatedSprite(letterXx,letterXy,resourcesManager.letterXgreenTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterY = new AnimatedSprite(letterYx,letterYy,resourcesManager.letterYblueTR,resourcesManager.activity.getVertexBufferObjectManager());
		letterZ = new AnimatedSprite(letterZx,letterZy,resourcesManager.letterZredTR,resourcesManager.activity.getVertexBufferObjectManager());
		
		
		this.attachChild(letterA);	
		this.attachChild(letterB);	
		this.attachChild(letterC);
		this.attachChild(letterD);
		this.attachChild(letterE);
		this.attachChild(letterF);
		this.attachChild(letterG);
		this.attachChild(letterH);	
		this.attachChild(letterI);
		this.attachChild(letterJ);
		this.attachChild(letterK);
		this.attachChild(letterL);
		this.attachChild(letterM);
		this.attachChild(letterN);	
		this.attachChild(letterO);
		this.attachChild(letterP);
		this.attachChild(letterQ);
		this.attachChild(letterR);
		this.attachChild(letterS);
		this.attachChild(letterT);	
		this.attachChild(letterU);
		this.attachChild(letterV);
		this.attachChild(letterW);
		this.attachChild(letterX);
		this.attachChild(letterY);
		this.attachChild(letterZ);
		
		this.registerTouchArea(letterA);	
		this.registerTouchArea(letterB);	
		this.registerTouchArea(letterC);
		this.registerTouchArea(letterD);
		this.registerTouchArea(letterE);
		this.registerTouchArea(letterF);
		this.registerTouchArea(letterG);
		this.registerTouchArea(letterH);	
		this.registerTouchArea(letterI);
		this.registerTouchArea(letterJ);
		this.registerTouchArea(letterK);
		this.registerTouchArea(letterL);
		this.registerTouchArea(letterM);
		this.registerTouchArea(letterN);	
		this.registerTouchArea(letterO);
		this.registerTouchArea(letterP);
		this.registerTouchArea(letterQ);
		this.registerTouchArea(letterR);
		this.registerTouchArea(letterS);
		this.registerTouchArea(letterT);	
		this.registerTouchArea(letterU);
		this.registerTouchArea(letterV);
		this.registerTouchArea(letterW);
		this.registerTouchArea(letterX);
		this.registerTouchArea(letterY);
		this.registerTouchArea(letterZ);
		

		
		//-- menu button
		btMenu = new AnimatedSprite(resourcesManager.btMenuTR.getWidth()/2,camera.getHeight()-(resourcesManager.btMenuTR.getHeight()/2),resourcesManager.btMenuTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					// Completely remove all resources associated with this sprite.    
					playSelectSound();
					onBackKeyPressed();
					/*
					SceneManager.getInstance().loadMainMenuScene(engine);
					resourcesManager.unloadAlphabetLearnResources();
			        PageCounter = 100;
					*/
					return true;
	
				} else {
					return false;
				}
			}


		};
		
        //icon.setPosition(camera);
        this.attachChild(btMenu);
        this.registerTouchArea(btMenu);
        
        btHome = new AnimatedSprite(camera.getWidth()/2,camera.getHeight()-(resourcesManager.btHomeTR.getHeight()/2),resourcesManager.btHomeTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					// Completely remove all resources associated with this sprite. 
					playSelectSound();
					
					blackBoard.setVisible(true);
					try{
					if (PageCounter==50  || PageCounter==100){
					
					if(freezeOrNot==true) {
	  					
	  					freezeOrNot=false;
	  					
	  				} else {
	  					freezeOrNot=true;
	  				}
	  				
	  				homeFreeze.setVisible(freezeOrNot);
	  				
					}else{
					
					PageCounter = 50;
					freezeOrNot=false;
					
					hideAlphabets();
					displayAlphabets(PageCounter);
					
			        
			        
					}
					return false;
	
				 
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return false;
				}
				return false;
				}
			
				
		};
        //icon.setPosition(camera);

		this.attachChild(btHome);
		this.registerTouchArea(btHome);
		
  		////////
  		homeFreeze = new Sprite(camera.getWidth()/2,camera.getHeight()-(resourcesManager.btHomeFreezeTR.getHeight()/2),resourcesManager.btHomeFreezeTR,engine.getVertexBufferObjectManager());
  		this.attachChild(homeFreeze);
  		homeFreeze.setVisible(freezeOrNot);
  		////
		
		//-- background music toggle
		

  		notes = new Sprite(camera.getWidth()-resourcesManager.btSoundsTR.getWidth()/2, camera.getHeight()-resourcesManager.btSoundsTR.getHeight()/2, resourcesManager.btSoundsTR, vbom){
        				
  			@Override
  			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
  			switch(pSceneTouchEvent.getAction()){
  			case TouchEvent.ACTION_DOWN:
  				
  				if(muteOrNot==true) {
  					
  					muteOrNot=false;
  					
  				} else {
  					muteOrNot=true;
  				}
  				
  				notesMute.setVisible(muteOrNot);
  				playSelectSound();

  				break;
  			case TouchEvent.ACTION_UP:

  				break;
  			}
  			return true;
        	}
  		};
  		this.attachChild(notes);
  		this.registerTouchArea(notes);
  		
  		////////
  		notesMute = new Sprite(camera.getWidth()-resourcesManager.btSoundsMuteTR.getWidth()/2, camera.getHeight()-resourcesManager.btSoundsMuteTR.getHeight()/2, resourcesManager.btSoundsMuteTR, vbom);
  		this.attachChild(notesMute);
  		notesMute.setVisible(muteOrNot);
  		////
        
  		

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
				//readAlphabet();
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
		
  		
  		
  		
        //-- left & right arrows
        leftArrowX = 5+resourcesManager.btLeftArrowTR.getWidth()/2;
        leftArrowY = ((camera.getHeight()-40)/2);
        rightArrowX = camera.getWidth()-5-resourcesManager.btRightArrowTR.getWidth()/2;
        rightArrowY =((camera.getHeight()-40)/2);
        
        leftArrow = new AnimatedSprite(leftArrowX,leftArrowY, resourcesManager.btLeftArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
        rightArrow = new AnimatedSprite(rightArrowX,rightArrowY, resourcesManager.btRightArrowTR, resourcesManager.activity.getVertexBufferObjectManager());
		
        this.attachChild(leftArrow);
        this.attachChild(rightArrow);
        
        
        if(PageCounter==50 || PageCounter==100){
        	hideArrows();
        }else {
        	showArrows();
        }
		
	}

	
	private void hideAlphabets() {
		// TODO Auto-generated method stub
		letterA.setVisible(false);
		letterB.setVisible(false);
		letterC.setVisible(false);
		letterD.setVisible(false);
		letterE.setVisible(false);
		letterF.setVisible(false);
		letterG.setVisible(false);
		letterH.setVisible(false);
		letterI.setVisible(false);
		letterJ.setVisible(false);
		letterK.setVisible(false);
		letterL.setVisible(false);
		letterM.setVisible(false);
		letterN.setVisible(false);
		letterO.setVisible(false);
		letterP.setVisible(false);
		letterQ.setVisible(false);
		letterR.setVisible(false);
		letterS.setVisible(false);
		letterT.setVisible(false);
		letterU.setVisible(false);
		letterV.setVisible(false);
		letterW.setVisible(false);
		letterX.setVisible(false);
		letterY.setVisible(false);
		letterZ.setVisible(false);
		
		this.unregisterTouchArea(letterA);
		this.unregisterTouchArea(letterB);
		this.unregisterTouchArea(letterC);
		this.unregisterTouchArea(letterD);
		this.unregisterTouchArea(letterE);
		this.unregisterTouchArea(letterF);
		this.unregisterTouchArea(letterG);
		this.unregisterTouchArea(letterH);
		this.unregisterTouchArea(letterI);
		this.unregisterTouchArea(letterJ);
		this.unregisterTouchArea(letterK);
		this.unregisterTouchArea(letterL);
		this.unregisterTouchArea(letterM);
		this.unregisterTouchArea(letterN);
		this.unregisterTouchArea(letterO);
		this.unregisterTouchArea(letterP);
		this.unregisterTouchArea(letterQ);
		this.unregisterTouchArea(letterR);
		this.unregisterTouchArea(letterS);
		this.unregisterTouchArea(letterT);
		this.unregisterTouchArea(letterU);
		this.unregisterTouchArea(letterV);
		this.unregisterTouchArea(letterW);
		this.unregisterTouchArea(letterX);
		this.unregisterTouchArea(letterY);
		this.unregisterTouchArea(letterZ);
		
		
	}
	private void displayAlphabets(int num) {
		// TODO Auto-generated method stub
		switch(num){
			
		case 0:
			letterA.setPosition(camera.getWidth()/2, (camera.getHeight()-40)/2);
			letterA.setVisible(true);
			this.registerTouchArea(letterA);
			PageCounter=0;
			showArrows();
			nextPage=1;
			backPage=25;
			readAlphabet(PageCounter);
			break;
		case 1:
			letterB.setPosition(camera.getWidth()/2, (camera.getHeight()-40)/2);
			letterB.setVisible(true);
			this.registerTouchArea(letterB);
			PageCounter=1;
			showArrows();
			nextPage=2;
			backPage=0;
			readAlphabet(PageCounter);
			break;
		case 2:
			letterC.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterC.setVisible(true);
			this.registerTouchArea(letterC);
			PageCounter=2;
			showArrows();
			nextPage=3;
			backPage=1;
			readAlphabet(PageCounter);
			break;
		case 3:
			letterD.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterD.setVisible(true);
			this.registerTouchArea(letterD);
			PageCounter=3;
			showArrows();
			nextPage=4;
			backPage=2;
			readAlphabet(PageCounter);
			break;
		case 4:
			letterE.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterE.setVisible(true);
			this.registerTouchArea(letterE);
			PageCounter=4;
			showArrows();
			nextPage=5;
			backPage=3;
			readAlphabet(PageCounter);
			break;
		case 5:
			
			letterF.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterF.setVisible(true);
			this.registerTouchArea(letterF);
			PageCounter=5;
			showArrows();
			nextPage=6;
			backPage=4;
			readAlphabet(PageCounter);
			break;
		case 6:
			letterG.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterG.setVisible(true);
			this.registerTouchArea(letterG);
			PageCounter=6;
			showArrows();
			nextPage=7;
			backPage=5;
			readAlphabet(PageCounter);
			break;
		case 7:
			letterH.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterH.setVisible(true);
			this.registerTouchArea(letterH);
			PageCounter=7;
			readAlphabet(PageCounter);
			showArrows();
			nextPage=8;
			backPage=6;
			break;
		case 8:
			letterI.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterI.setVisible(true);
			this.registerTouchArea(letterI);
			PageCounter=8;
			showArrows();
			nextPage=9;
			backPage=7;
			readAlphabet(PageCounter);
			break;
		case 9:
			letterJ.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterJ.setVisible(true);
			this.registerTouchArea(letterJ);
			PageCounter=9;
			showArrows();
			nextPage=10;
			backPage=8;
			readAlphabet(PageCounter);
			break;
		case 10:
			letterK.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterK.setVisible(true);
			this.registerTouchArea(letterK);
			PageCounter=10;
			showArrows();
			nextPage=11;
			backPage=9;
			readAlphabet(PageCounter);
			break;
		case 11:
			letterL.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterL.setVisible(true);
			this.registerTouchArea(letterL);
			PageCounter=11;
			showArrows();
			nextPage=12;
			backPage=10;
			readAlphabet(PageCounter);
			break;
		case 12:
			letterM.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterM.setVisible(true);
			this.registerTouchArea(letterM);
			PageCounter=12;
			showArrows();
			nextPage=13;
			backPage=11;
			readAlphabet(PageCounter);
			break;
		case 13:
			letterN.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterN.setVisible(true);
			this.registerTouchArea(letterN);
			PageCounter=13;
			showArrows();
			nextPage=14;
			backPage=12;
			readAlphabet(PageCounter);
			break;
		case 14:
			letterO.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterO.setVisible(true);
			this.registerTouchArea(letterO);
			PageCounter=14;
			showArrows();
			nextPage=15;
			backPage=13;
			readAlphabet(PageCounter);
			break;
		case 15:
			letterP.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterP.setVisible(true);
			this.registerTouchArea(letterP);
			PageCounter=15;
			showArrows();
			nextPage=16;
			backPage=14;
			readAlphabet(PageCounter);
			break;
		case 16:
			letterQ.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterQ.setVisible(true);
			this.registerTouchArea(letterQ);
			PageCounter=16;
			showArrows();
			nextPage=17;
			backPage=15;
			readAlphabet(PageCounter);
			break;
		case 17:
			letterR.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterR.setVisible(true);
			this.registerTouchArea(letterR);
			PageCounter=17;
			showArrows();
			nextPage=18;
			backPage=16;
			readAlphabet(PageCounter);
			break;
		case 18:
			letterS.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterS.setVisible(true);
			this.registerTouchArea(letterS);
			PageCounter=18;
			showArrows();
			nextPage=19;
			backPage=17;
			readAlphabet(PageCounter);
			break;
		case 19:
			letterT.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterT.setVisible(true);
			this.registerTouchArea(letterT);
			PageCounter=19;
			showArrows();
			nextPage=20;
			backPage=18;
			readAlphabet(PageCounter);
			break;
		case 20:
			letterU.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterU.setVisible(true);
			this.registerTouchArea(letterU);
			PageCounter=20;
			showArrows();
			nextPage=21;
			backPage=19;
			readAlphabet(PageCounter);
			break;
		case 21:
			letterV.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterV.setVisible(true);
			this.registerTouchArea(letterV);
			PageCounter=21;
			showArrows();
			nextPage=22;
			backPage=20;
			readAlphabet(PageCounter);
			break;
		case 22:
			letterW.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterW.setVisible(true);
			this.registerTouchArea(letterW);
			PageCounter=22;
			showArrows();
			nextPage=23;
			backPage=21;
			readAlphabet(PageCounter);
			break;
		case 23:
			letterX.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterX.setVisible(true);
			this.registerTouchArea(letterX);
			PageCounter=23;
			showArrows();
			nextPage=24;
			backPage=22;
			readAlphabet(PageCounter);
			
			break;
		case 24:
			letterY.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterY.setVisible(true);
			this.registerTouchArea(letterY);
			PageCounter=24;
			showArrows();
			nextPage=25;
			backPage=23;
			readAlphabet(PageCounter);
			
			break;
		case 25:
			letterZ.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			letterZ.setVisible(true);
			this.registerTouchArea(letterZ);
			PageCounter=25;
			showArrows();
			nextPage=0;
			backPage=24;
			readAlphabet(PageCounter);
			
			break;
		
		case 50:
			letterA.setPosition(letterAx, letterAy);
			letterB.setPosition(letterBx, letterBy);
			letterC.setPosition(letterCx, letterCy);
			letterD.setPosition(letterDx, letterDy);
			letterE.setPosition(letterEx, letterEy);
			letterF.setPosition(letterFx, letterFy);
			letterG.setPosition(letterGx, letterGy);
			letterH.setPosition(letterHx, letterHy);
			letterI.setPosition(letterIx, letterIy);
			letterJ.setPosition(letterJx, letterJy);
			letterK.setPosition(letterKx, letterKy);
			letterL.setPosition(letterLx, letterLy);
			letterM.setPosition(letterMx, letterMy);
			letterN.setPosition(letterNx, letterNy);
			letterO.setPosition(letterOx, letterOy);
			letterP.setPosition(letterPx, letterPy);
			letterQ.setPosition(letterQx, letterQy);
			letterR.setPosition(letterRx, letterRy);
			letterS.setPosition(letterSx, letterSy);
			letterT.setPosition(letterTx, letterTy);
			letterU.setPosition(letterUx, letterUy);
			letterV.setPosition(letterVx, letterVy);
			letterW.setPosition(letterWx, letterWy);
			letterX.setPosition(letterXx, letterXy);
			letterY.setPosition(letterYx, letterYy);
			letterZ.setPosition(letterZx, letterZy);
			


			letterA.setVisible(true);
			this.registerTouchArea(letterA);
			letterB.setVisible(true);
			this.registerTouchArea(letterB);
			letterC.setVisible(true);
			this.registerTouchArea(letterC);
			letterD.setVisible(true);
			this.registerTouchArea(letterD);
			letterE.setVisible(true);
			this.registerTouchArea(letterE);
			letterF.setVisible(true);
			this.registerTouchArea(letterF);
			letterG.setVisible(true);
			this.registerTouchArea(letterG);
			letterH.setVisible(true);
			this.registerTouchArea(letterH);
			letterI.setVisible(true);
			this.registerTouchArea(letterI);
			letterJ.setVisible(true);
			this.registerTouchArea(letterJ);
			letterK.setVisible(true);
			this.registerTouchArea(letterK);
			letterL.setVisible(true);
			this.registerTouchArea(letterL);
			letterM.setVisible(true);
			this.registerTouchArea(letterM);
			letterN.setVisible(true);
			this.registerTouchArea(letterN);
			letterO.setVisible(true);
			this.registerTouchArea(letterO);
			letterP.setVisible(true);
			this.registerTouchArea(letterP);
			letterQ.setVisible(true);
			this.registerTouchArea(letterQ);
			letterR.setVisible(true);
			this.registerTouchArea(letterR);
			letterS.setVisible(true);
			this.registerTouchArea(letterS);
			letterT.setVisible(true);
			this.registerTouchArea(letterT);
			letterU.setVisible(true);
			this.registerTouchArea(letterU);
			letterV.setVisible(true);
			this.registerTouchArea(letterV);
			letterW.setVisible(true);
			this.registerTouchArea(letterW);
			letterX.setVisible(true);
			this.registerTouchArea(letterX);
			letterY.setVisible(true);
			this.registerTouchArea(letterY);
			letterZ.setVisible(true);
			this.registerTouchArea(letterZ);
			
			PageCounter=50;
			freezeOrNot=false;
			homeFreeze.setVisible(freezeOrNot);
			
			hideArrows();

			
			break;
		
		
		}
		
				
	}
	
	private void hideArrows() {
		// TODO Auto-generated method stub
		this.detachChild(leftArrow);
        this.detachChild(rightArrow);
		leftArrowVisible=false;
		rightArrowVisible=false;
		leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		unregisterTouchArea(leftArrow);
		unregisterTouchArea(rightArrow);
		
	}
	private void showArrows() {
		// TODO Auto-generated method stub
		this.attachChild(leftArrow);
        this.attachChild(rightArrow);
		leftArrowVisible=true;
		rightArrowVisible=true;
		leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		registerTouchArea(leftArrow);
		registerTouchArea(rightArrow);
		
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
		//SceneManager.getInstance().loadMainMenuScene(engine);
		resourcesManager.unloadAlphabetLearnResources();
        PageCounter = 100;
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_ALPHABETLEARN;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		this.detachSelf();
		this.dispose();
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
		final IShape face = (IShape) pTouchArea;

		AnimatedSprite capturedSprite = (AnimatedSprite) pTouchArea;
		
		try{
    	if(capturedSprite!=null && PageCounter!=100){	
    		

    		if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterAyellowTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
    				readAlphabet(0);	
    				}else{
    				blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(0);
    				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterBgreenTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(1);	
        				}else{
        			blackBoard.setVisible(false);		
    				hideAlphabets();
    				displayAlphabets(1);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterCblueTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(2);	
        				}else{
        			blackBoard.setVisible(false);		
    				hideAlphabets();
    				displayAlphabets(2);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
        	
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterDpurpleTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(3);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(3);
        				}
    				
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterEredTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(4);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(4);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterForangeTR){
				
				
				try{
	   				if(freezeOrNot==true){
	    				readAlphabet(5);	
	    				}else{
	    					blackBoard.setVisible(false);
					hideAlphabets();
    				displayAlphabets(5);
	    				}
				}catch(Exception ex){
					ex.printStackTrace();
				}
	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterGyellowTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(6);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();	
    				displayAlphabets(6);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterHgreenTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(7);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();	
    				displayAlphabets(7);
        				}
    				
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterIblueTR){
    	   			
    			try{
       				if(freezeOrNot==true){
       					
       					readAlphabet(8);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();	
    				displayAlphabets(8);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterJgreenTR){
    	 		try{
       				if(freezeOrNot==true){
       					
       					readAlphabet(9);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();	
    				displayAlphabets(9);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterKpurpleTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(10);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(10);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		

    		
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterLredTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(11);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(11);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterMyellowTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(12);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(12);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterNgreenTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(13);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(13);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterOredTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(14);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(14);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterPyellowTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(15);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(15);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterQpurpleTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(16);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(16);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterRgreenTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(17);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(17);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterSyellowTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(18);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(18);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterTblueTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(19);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(19);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterUredTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(20);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(20);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterVyellowTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(21);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(21);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterWorangeTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(22);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(22);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterXgreenTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(23);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(23);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}

    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterYblueTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(24);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(24);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.letterZredTR){
    			
    			
    			try{
       				if(freezeOrNot==true){
        				readAlphabet(25);	
        				}else{
        					blackBoard.setVisible(false);
    				hideAlphabets();
    				displayAlphabets(25);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		
    		
    		
    		
    		
    	else if(capturedSprite.getTiledTextureRegion() == resourcesManager.btLeftArrowTR){
    		
    		if(pSceneTouchEvent.isActionDown()) {
				// Completely remove all resources associated with this sprite. 
    			try{
    			hideAlphabets(); 
				//displayNumbers(backPage);
    			if(PageCounter==0){
    				PageCounter=25;
				}else{
					PageCounter=PageCounter-1;
				}					
    				displayAlphabets(PageCounter);
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    			return true;
			} else {
				return false;
			}

		}else if(capturedSprite.getTiledTextureRegion() == resourcesManager.btRightArrowTR){
			
			if(pSceneTouchEvent.isActionDown()) {
				// Completely remove all resources associated with this sprite. 
				try{
				hideAlphabets(); 
				//displayNumbers(nextPage);
				if(PageCounter==25){
					PageCounter=0;
				}else{
					PageCounter=PageCounter+1;
				}					
    				displayAlphabets(PageCounter);
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
		}catch(Exception ex){
			ex.printStackTrace();
		}
    
	    if (PageCounter==100){
	    	PageCounter=50;
	    }    	

		
		return true;
	}

	private void readAlphabet(int numAlphabet) {
		// TODO Auto-generated method stub
		if(muteOrNot==false){
		try{
			
			if (localLanguage==0){
			resourcesManager.tts.speak(textAlphabetsReadFr[numAlphabet],TextToSpeech.QUEUE_FLUSH,null);
			}else{
			resourcesManager.tts.speak(textAlphabetsReadEn[numAlphabet],TextToSpeech.QUEUE_FLUSH,null);
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			}
		
		}else{}
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub

		if (PageCounter!=50 && PageCounter!=100){
			try{
			readAlphabet(PageCounter);
			}
			catch(Exception ex){
				ex.printStackTrace();
				}
			
		}

		return false;
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
	//-- init language -------------------------------------------------------------------
	prefLanguage = ((GameActivity) activity).getPreference("language", "1");
	if (prefLanguage.contentEquals("1")){
		resourcesManager.tts.setLanguage(Locale.FRENCH);
		localLanguage=0;
	} else if (prefLanguage.contentEquals("2")){
		resourcesManager.tts.setLanguage(Locale.ENGLISH);	
		localLanguage=1;
	} 
	}
	//-----------------------------------------------------------------------------------
	
	
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

