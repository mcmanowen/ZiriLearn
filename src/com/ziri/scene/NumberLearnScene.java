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
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;


import android.speech.tts.TextToSpeech;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ziri.GameActivity;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class NumberLearnScene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener {

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
		//-- numbers positions
		float number00x,number00y,number01x,number01y,number02x,number02y,number03x,number03y,
		number04x,number04y,number05x,number05y,number06x,number06y,number07x,number07y,
		number08x,number08y,number09x,number09y,number10x,number10y;
  

		//-----------------------------------------------------------
		//--text to speech numbers
		static final String[] textNumbersFr = {"0","1","2", "3","4","5","6","7","8","9","10"};
		//static final String[] textNumbersEn = {"zero","one","two", "three","four","five","six","seven","eight","nine","ten"};
    
	   
		//-- sprite numbers
		AnimatedSprite number00,number01,number02,number03,number04,number05,number06,number07,number08,number09,number10;
		AnimatedSprite number00big,number01big,number02big,number03big,number04big,number05big,number06big,number07big,number08big,number09big,number10big;
	    		
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
		
		//--  background -------------------------------------------------
		Sprite blackBoard;
		AutoParallaxBackground alphabetautoParallaxBackground;
		
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
		

    	//-- init sounds ---------------------------------------------------------------------
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
    	nextPage=1;
    	backPage=10;
    	
    	
    	PageCounter = 100;
        number00x=124f;
        number00y=(camera.getHeight()-40)*.73f;
        number01x=217f;
        number01y=(camera.getHeight()-40)*.73f;
        number02x=313f;
        number02y=(camera.getHeight()-40)*.73f;
        number03x=428f;
        number03y=(camera.getHeight()-40)*.73f;
        number04x=542f;
        number04y=(camera.getHeight()-40)*.73f;
        number05x=657f;
        number05y=(camera.getHeight()-40)*.73f;
        number06x=144f;
        number06y=(camera.getHeight()-40)*.38f;
        number07x=259f;
        number07y=(camera.getHeight()-40)*.38f;
        number08x=374f;
        number08y=(camera.getHeight()-40)*.38f;
        number09x=489f;
        number09y=(camera.getHeight()-40)*.38f;
        number10x=622f;
        number10y=(camera.getHeight()-40)*.38f;
    	

    	alphabetautoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		final VertexBufferObjectManager vertexBufferObjectManager = resourcesManager.activity.getVertexBufferObjectManager();
		alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vertexBufferObjectManager)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-1.0f, new Sprite(0, 80, this.mAlphabetParallaxLayerMid, vertexBufferObjectManager)));
		//alphabetautoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, camera.getHeight() - this.mAlphabetParallaxLayerFront.getHeight(), this.mAlphabetParallaxLayerFront, vertexBufferObjectManager)));
		this.setBackground(alphabetautoParallaxBackground);
		
		blackBoard = new Sprite(camera.getWidth()/2,(camera.getHeight()-40)*.5f,resourcesManager.blackBoardTR,vbom);
		this.attachChild(blackBoard);	
		
		Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight()-20,camera.getWidth(),40,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(1,10,20));
        attachChild(top);
        
        final Text titleText = new Text(camera.getWidth()/4, 455, resourcesManager.mBitmapFont, "ZiriKids", vbom);
        titleText.setColor(Color.BLUE);
        this.attachChild(titleText);
    	
    	populateNumber();
    	
    	
	}

	private void populateNumber() {
		// TODO Auto-generated method stub
		
		number00 = new AnimatedSprite(number00x,number00y, resourcesManager.number00redTR, resourcesManager.activity.getVertexBufferObjectManager());
		number01 = new AnimatedSprite(number01x,number01y, resourcesManager.number01yellowTR, resourcesManager.activity.getVertexBufferObjectManager());
		number02 = new AnimatedSprite(number02x,number02y, resourcesManager.number02greenTR, resourcesManager.activity.getVertexBufferObjectManager());
		number03 = new AnimatedSprite(number03x,number03y, resourcesManager.number03orangeTR, resourcesManager.activity.getVertexBufferObjectManager());
		number04 = new AnimatedSprite(number04x,number04y, resourcesManager.number04blueTR, resourcesManager.activity.getVertexBufferObjectManager());
		number05 = new AnimatedSprite(number05x,number05y, resourcesManager.number05purpleTR, resourcesManager.activity.getVertexBufferObjectManager());
		number06 = new AnimatedSprite(number06x,number06y, resourcesManager.number06yellowTR, resourcesManager.activity.getVertexBufferObjectManager());
		number07 = new AnimatedSprite(number07x,number07y, resourcesManager.number07redTR, resourcesManager.activity.getVertexBufferObjectManager());
		number08 = new AnimatedSprite(number08x,number08y, resourcesManager.number08greenTR, resourcesManager.activity.getVertexBufferObjectManager());
		number09 = new AnimatedSprite(number09x,number09y, resourcesManager.number09orangeTR, resourcesManager.activity.getVertexBufferObjectManager());
		number10 = new AnimatedSprite(number10x,number10y, resourcesManager.number10purpleTR, resourcesManager.activity.getVertexBufferObjectManager());
		
		
		this.attachChild(number00);
		this.attachChild(number01);	
		this.attachChild(number02);	
		this.attachChild(number03);
		this.attachChild(number04);
		this.attachChild(number05);
		this.attachChild(number06);
		this.attachChild(number07);
		this.attachChild(number08);	
		this.attachChild(number09);
		this.attachChild(number10);
		
		this.registerTouchArea(number00);
		this.registerTouchArea(number01);
		this.registerTouchArea(number02);
		this.registerTouchArea(number03);
		this.registerTouchArea(number04);
		this.registerTouchArea(number05);
		this.registerTouchArea(number06);
		this.registerTouchArea(number07);
		this.registerTouchArea(number08);
		this.registerTouchArea(number09);
		this.registerTouchArea(number10);
		
	
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
					resourcesManager.unloadNumberLearnResources();
			        PageCounter = 100;
			        */
					return true;
	
				} else {
					return false;
				}
			}

	
		};
		
        btHome = new AnimatedSprite(camera.getWidth()/2,camera.getHeight()-(resourcesManager.btHomeTR.getHeight()/2),resourcesManager.btHomeTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					
					//setBackground(alphabetautoParallaxBackground2);
					playSelectSound();
					
					blackBoard.setVisible(true);
					
					if (PageCounter==50 || PageCounter==100 ){
						
						if(freezeOrNot==true) {
		  					
		  					freezeOrNot=false;
		  					
		  				} else {
		  					freezeOrNot=true;
		  				}
		  				
		  				homeFreeze.setVisible(freezeOrNot);
		  				
						}else{
							hideNumbers();
							displayNumbers(50);
			        
							PageCounter = 50;
						}
					return true;
	
				} else {
					return false;
				}
			}	
		};
        //icon.setPosition(camera);
		
		this.attachChild(btMenu);
        this.registerTouchArea(btMenu);
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
    				//readimgName();
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
       
        
        leftArrow.setVisible(leftArrowVisible);
        rightArrow.setVisible(rightArrowVisible);
        
       
		
		
	}

	
	private void hideNumbers() {
		// TODO Auto-generated method stub
		number00.setVisible(false);
		number01.setVisible(false);
		number02.setVisible(false);
		number03.setVisible(false);
		number04.setVisible(false);
		number05.setVisible(false);
		number06.setVisible(false);
		number07.setVisible(false);
		number08.setVisible(false);
		number09.setVisible(false);
		number10.setVisible(false);
		this.unregisterTouchArea(number00);
		this.unregisterTouchArea(number01);
		this.unregisterTouchArea(number02);
		this.unregisterTouchArea(number03);
		this.unregisterTouchArea(number04);
		this.unregisterTouchArea(number05);
		this.unregisterTouchArea(number06);
		this.unregisterTouchArea(number07);
		this.unregisterTouchArea(number08);
		this.unregisterTouchArea(number09);
		this.unregisterTouchArea(number10);
		
	}
	private void displayNumbers(int num) {
		// TODO Auto-generated method stub
		switch(num){
			
		case 0:
			number00.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number00.setVisible(true);
			this.registerTouchArea(number00);
			PageCounter=0;
			showArrows();
			nextPage=1;
			backPage=10;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[0],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 1:
			number01.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number01.setVisible(true);
			this.registerTouchArea(number01);
			PageCounter=1;
			showArrows();
			nextPage=2;
			backPage=0;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[1],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 2:
			number02.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number02.setVisible(true);
			this.registerTouchArea(number02);
			PageCounter=2;
			showArrows();
			nextPage=3;
			backPage=1;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[2],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 3:
			number03.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number03.setVisible(true);
			this.registerTouchArea(number03);
			PageCounter=3;
			showArrows();
			nextPage=4;
			backPage=2;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[3],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 4:
			number04.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number04.setVisible(true);
			this.registerTouchArea(number04);
			PageCounter=4;
			showArrows();
			nextPage=5;
			backPage=3;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[4],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 5:
			
			number05.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number05.setVisible(true);
			this.registerTouchArea(number05);
			PageCounter=5;
			showArrows();
			nextPage=6;
			backPage=4;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[5],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 6:
			number06.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number06.setVisible(true);
			this.registerTouchArea(number06);
			PageCounter=6;
			showArrows();
			nextPage=7;
			backPage=5;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[6],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 7:
			number07.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number07.setVisible(true);
			this.registerTouchArea(number07);
			PageCounter=7;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[7],TextToSpeech.QUEUE_FLUSH,null);
			showArrows();
			nextPage=8;
			backPage=6;
			break;
		case 8:
			number08.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number08.setVisible(true);
			this.registerTouchArea(number08);
			PageCounter=8;
			showArrows();
			nextPage=9;
			backPage=7;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[8],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 9:
			number09.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number09.setVisible(true);
			this.registerTouchArea(number09);
			PageCounter=9;
			showArrows();
			nextPage=10;
			backPage=8;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[9],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 10:
			number10.setPosition(camera.getWidth()/2, ((camera.getHeight()-40)/2));
			number10.setVisible(true);
			this.registerTouchArea(number10);
			PageCounter=10;
			showArrows();
			nextPage=0;
			backPage=9;
			readNumber(PageCounter);
			//resourcesManager.tts.speak(textNumbersFr[10],TextToSpeech.QUEUE_FLUSH,null);
			break;
		case 50:
			number00.setPosition(number00x, number00y);
			number01.setPosition(number01x, number01y);
			number02.setPosition(number02x, number02y);
			number03.setPosition(number03x, number03y);
			number04.setPosition(number04x, number04y);
			number05.setPosition(number05x, number05y);
			number06.setPosition(number06x, number06y);
			number07.setPosition(number07x, number07y);
			number08.setPosition(number08x, number08y);
			number09.setPosition(number09x, number09y);
			number10.setPosition(number10x, number10y);

			number00.setVisible(true);
			this.registerTouchArea(number00);
			number01.setVisible(true);
			this.registerTouchArea(number01);
			number02.setVisible(true);
			this.registerTouchArea(number02);
			number03.setVisible(true);
			this.registerTouchArea(number03);
			number04.setVisible(true);
			this.registerTouchArea(number04);
			number05.setVisible(true);
			this.registerTouchArea(number05);
			number06.setVisible(true);
			this.registerTouchArea(number06);
			number07.setVisible(true);
			this.registerTouchArea(number07);
			number08.setVisible(true);
			this.registerTouchArea(number08);
			number09.setVisible(true);
			this.registerTouchArea(number09);
			number10.setVisible(true);
			this.registerTouchArea(number10);
			
			PageCounter=50;
			freezeOrNot=false;
			
			leftArrowVisible=rightArrowVisible=false;
			leftArrow.setVisible(leftArrowVisible);
			rightArrow.setVisible(rightArrowVisible);
			
			this.unregisterTouchArea(leftArrow);
			this.unregisterTouchArea(rightArrow);
			
			
			break;
		
		
		}
		
				
	}

	private void showArrows() {
		// TODO Auto-generated method stub
		leftArrowVisible=true;
		rightArrowVisible=true;
		leftArrow.setVisible(leftArrowVisible);
		rightArrow.setVisible(rightArrowVisible);

		this.registerTouchArea(leftArrow);
		this.registerTouchArea(rightArrow);
		
	}
	
	
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
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
		resourcesManager.unloadNumberLearnResources();
        PageCounter = 100;
        
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_NUMBERLEARN;
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
	
    	if(capturedSprite!=null && PageCounter!=100){	
        	
	    	
    		if(capturedSprite.getTiledTextureRegion() == resourcesManager.number00redTR){
    			
    			//this.setBackground(alphabetautoParallaxBackground);
    			try{
    				if(freezeOrNot==true){
        				readNumber(0);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				
    				displayNumbers(0);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number01yellowTR){
    			
    			//this.setBackground(alphabetautoParallaxBackground);
    			try{
    				if(freezeOrNot==true){
        				readNumber(1);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				displayNumbers(1);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number02greenTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(2);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				displayNumbers(2);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
        	
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number03orangeTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(3);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				displayNumbers(3);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number04blueTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(4);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				displayNumbers(4);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number05purpleTR){
				
				
				try{
					if(freezeOrNot==true){
        				readNumber(5);	
        				}else{
        					blackBoard.setVisible(false);
					hideNumbers();
    				displayNumbers(5);
        				}
				}catch(Exception ex){
					ex.printStackTrace();
				}
	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number06yellowTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(6);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();	
    				displayNumbers(6);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number07redTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(7);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();	
    				displayNumbers(7);
        				}
    				
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number08greenTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(8);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();	
    				displayNumbers(8);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number09orangeTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(9);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();	
    				displayNumbers(9);
        				}
    			
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    		else if(capturedSprite.getTiledTextureRegion() == resourcesManager.number10purpleTR){
    			
    			
    			try{
    				if(freezeOrNot==true){
        				readNumber(10);	
        				}else{
        					blackBoard.setVisible(false);
    				hideNumbers();
    				displayNumbers(10);
        				}
    			}catch(Exception ex){
    				ex.printStackTrace();
    			}
    	
    		}
    	else if(capturedSprite.getTiledTextureRegion() == resourcesManager.btLeftArrowTR){
    		
    		if(pSceneTouchEvent.isActionDown()) {
				// Completely remove all resources associated with this sprite. 
    			try{
    			hideNumbers(); 
				//displayNumbers(backPage);
    			if(PageCounter==0){
    				PageCounter=10;
				}else{
					PageCounter=PageCounter-1;
				}					
    				displayNumbers(PageCounter);
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
				hideNumbers(); 
				//displayNumbers(nextPage);
				if(PageCounter==10){
					PageCounter=0;
				}else{
					PageCounter=PageCounter+1;
				}					
    				displayNumbers(PageCounter);
				}catch(Exception ex){
    				ex.printStackTrace();
    			}
				return true;

			} else {
				return false;
			}

		}
    	}
	else{
    			
    	}
	    if (PageCounter==100){
	    	PageCounter=50;
	    } 
		
		return true;
	}
/*
	private void readNumber() {
		// TODO Auto-generated method stub
		if(muteOrNot==false){
		resourcesManager.tts.speak(textNumbersFr[PageCounter],TextToSpeech.QUEUE_FLUSH,null);
		}else{}
	}
*/
	private void readNumber(int numNumber) {
		// TODO Auto-generated method stub
		if(muteOrNot==false){
			try{
		resourcesManager.tts.speak(textNumbersFr[numNumber],TextToSpeech.QUEUE_FLUSH,null);
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
			readNumber(PageCounter);
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
