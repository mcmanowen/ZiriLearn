package com.ziri.scene;

import java.util.ArrayList;
import com.ziri.GameActivity;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.andengine.entity.particle.initializer.AlphaParticleInitializer;
import org.andengine.entity.particle.initializer.BlendFunctionParticleInitializer;
import org.andengine.entity.particle.initializer.ColorParticleInitializer;
import org.andengine.entity.particle.initializer.ExpireParticleInitializer;
import org.andengine.entity.particle.initializer.RotationParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
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
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.color.Color;

import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.speech.tts.TextToSpeech;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Manifold;

import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.ziri.base.BaseScene;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class BallGameScene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener {
	
	//---------------------------------------------
	// PREFERENCES
	//---------------------------------------------
			
	String prefLocalPlayer,prefVisitorPlayer,prefLanguage;
	boolean prefMusic,prefGameSounds,prefLearnSounds;
			
		
	
	// ===========================================================
	// Levels Constants
	// ===========================================================

	/*
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_WIDTH = "width";
	private static final String TAG_ENTITY_ATTRIBUTE_HEIGHT = "height";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";
	*/

	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	
	private int localIdProfile; 
	
	Body staticBody;
	Rectangle staticRect,leftNetleftBar,leftNetrightBar,rightNetleftBar,rightNetrightBar;
	

    
    boolean showStarsleft,showStarsright;
    //---------------------------------------------
    
    //-- variables ------------------------------------
    ArrayList<Body> bodyArray=new ArrayList<Body>();
    static int bodyCount=0;
    	
    	private int adsDuration=20;	
    	//private long startTime=0;
    	private int gameDuration=50;

	   	
    	//--init variables
    	int levelID=0;
    	int nbObjectsInScene;
		int mFaceCount;
		int addornotFace;
		int gamePageCounter;
		Text scoreAText;
		Text scoreBText;
		Text levelScoreText;
		//Text usernameAText;
		//Text usernameBText;
		Text timeText;
		Text levelCompleteText;
		Text quitOrNotText;
		int scoreA=0;
		int scoreB=0;
		HUD gameHUD;
		HUD quitgameHUD;
		HUD levelcompleteHUD;
		
		//-- init level variables
		int moduloAddFace;
		int nbFootBall1; 
		int nbFootBall2;
		int nbTennisBall;
		int nbVolleyBall;
		int nbBillardBall;
		int nbBeachBall;
		int nbTchoupiBall;
		int nbPitchougreenBall;
		int nbPitchoupurpleBall;
	 //-------------------------------------------------	
		
		private CameraScene pauseScene,winScene,goMenuScene;

		
		// text to speech ------------------------------------------------
		
		private TextToSpeech tts;
		private int localLanguage;
		//----------------------------------------------------------------
		
		//-- menu bar ----------------------------------------------------
		
		boolean muteOrNot,soundsOrNot;
	
		Sprite musicNotes,musicNotesMute,soundNotes,soundNotesMute;
	 	AnimatedSprite btMenu;
		AnimatedSprite btHome;
		AnimatedSprite btPause;
		//----------------------------------------------------------------
		
		//-- ated balls --------------------------------
		
		AnimatedSprite starsleft,starsright;
		AnimatedSprite beachball,tennisball,billardball,volleyball,
		football,football2,gymballgreen,gymballred,tchoupiball,hole01,netleft,netright;
		AnimatedSprite pitchouballgreen,pitchouballpurple;
		//--------------------------------------------------
		
		Body beachballBody,tennisballBody,billardballBody,volleyballBody,
		footballBody,football2Body,gymballgreenBody,gymballredBody,tchoupiballBody;
		
		//--------------------------------------------------
		
		
		//--  left and right foots positions
		float xleftPos=100;
		float yleftPos=camera.getHeight()/2;
		float xrightPos=700;
		float yrightPos=camera.getHeight()/2;
		
		PhysicsWorld physicsWorld;
		
		//---------------------------------------------
		// METHODS FROM SUPERCLASS
		//---------------------------------------------


	@Override
	public void createScene() {
		adsDuration=20;
		gameDuration=50;
		//--level variables
		/*
		level									level number
		showStars=false;  						stars twinkle when touching hole
		nbObjectsInScene = 15; 					number of objects in scene
		score=20000;							initial level score
		mFaceCount=0;							number of added faces (balls that explose)
    	addornotFace=0;							use to calculate frequency of null clicks to add face
       	//muteOrNot=false;   					mute is clicked or not
    	gamePageCounter = 100;					know if it is the first time scene access
		
    	setFullAlphaForDynamicBody = false;
        setHalfAlphaForDynamicBody = false;
        setFullAlphaForStaticBody = false;
        setHalfAlphaForStaticBody = false;	
		*/
		//------------------------------------------------------------------
		
		//startTime = System.currentTimeMillis();
		
		if (levelID==0){
		levelID+=1;							//level number
		
    	
		
		//-- init sound ---------------------------------------------------------------------
    	initSound();    	
    	//-----------------------------------------------------------------------------------
    	//-- init music ---------------------------------------------------------------------
    	initMusic();    	
    	//-----------------------------------------------------------------------------------
    	initLanguage();
    	//-----------------------------------------------------------------------------------

		initVariables(1,false,false,5,0,0,0,0,muteOrNot,soundsOrNot,100,false,false,false,false);
	
		initLevelVariables(6,1,1,0,1,0,1,1,5,5);
		
		}else{
			
		}
		
		
    	engine.registerUpdateHandler(new FPSLogger());
    	
    	
    	final float centerX = camera.getWidth() / 2;
		final float centerY = camera.getHeight() / 2;
    	
		//--pause scene ----------------------------------------------------------------------------------
    	
		pauseScene = new CameraScene(camera);
		// Make the 'PAUSED'-label centered on the camera.
		
		final AnimatedSprite pausedSprite = new AnimatedSprite(centerX, centerY, resourcesManager.btPauseTR, vbom){
				
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					
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
    	
    	//-------------------------------------------------------------------------------------------------
		
    	//--goMenu scene ----------------------------------------------------------------------------------
    	
		goMenuScene = new CameraScene(camera);
		// Make the 'WIN'-label centered on the camera. 
		final Sprite goMenuSprite = new Sprite(centerX, centerY, resourcesManager.emptyWindowTR, vbom);
		goMenuScene.attachChild(goMenuSprite);
		// Makes the paused Game look through. 
		goMenuScene.setBackgroundEnabled(false);
    	
		final TiledSprite goMenu;
		goMenu = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom){
				
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()){
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
		goMenuScene.attachChild(returnGame);

		goMenuScene.registerTouchArea(returnGame);
		
		quitgameHUD = new HUD();
		quitOrNotText = new Text(400,300, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
		quitOrNotText.setSkewCenter(0,0);    
		quitOrNotText.setText("");        
		//quitOrNotText.setText("Do You Really Want To Exit !"); 
		quitgameHUD.attachChild(quitOrNotText);         
        camera.setHUD(quitgameHUD); 
        goMenuScene.attachChild(quitgameHUD);
		
    	//---------------------------------------------------------------------------------------------
		
		/*
    	//--win scene ---------------------------------------------------------------------------------
    	
		winScene = new CameraScene(camera);
		// Make the 'WIN'-label centered on the camera. 
		final Sprite winSprite = new Sprite(centerX, centerY, resourcesManager.emptyWindowTR, vbom);
		winScene.attachChild(winSprite);
		// Makes the paused Game look through. 
		winScene.setBackgroundEnabled(false);
    	
		final TiledSprite initGame;
		initGame = new TiledSprite(400, 150, resourcesManager.nextLevelTR, vbom){
				
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()){
			case TouchEvent.ACTION_DOWN:
				this.setCurrentTileIndex(1);
			
				break;
			case TouchEvent.ACTION_UP:
				initScene();
				break;
			}
			return true;
      	}


		};
		initGame.setCurrentTileIndex(0);
		winScene.attachChild(initGame);

		winScene.registerTouchArea(initGame);
		
		levelcompleteHUD = new HUD();
		levelCompleteText = new Text(400,300, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
		levelCompleteText.setSkewCenter(0,0);    
		levelCompleteText.setText("");
		levelcompleteHUD.attachChild(levelCompleteText);         
        camera.setHUD(levelcompleteHUD); 
        winScene.attachChild(levelcompleteHUD);
    	//------------------------------------------------------------------------------------------
		*/
    	/*
		//-- level objects
    	int levelID=1;
    	final SimpleLevelLoader levelLoader = new SimpleLevelLoader(vbom);
    	levelLoader.loadLevelFromAsset(activity.getAssets(), "level/1.lvl");
    	
    	levelLoader.registerEntityLoader(new IEntityLoader() {
        
			@Override
			public String[] getEntityNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IEntity onLoadEntity(String pEntityName, IEntity pParent,
					Attributes pAttributes, IEntityLoaderData pEntityLoaderData)
					throws IOException {
				// TODO Auto-generated method stub
				
				final int x = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_X);
                final int y = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
                final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_WIDTH);
                final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_HEIGHT);
                final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_TYPE);

                addObj(this, x, y, width, height, type);
			
				return null;
			}
    	});
		
		protected void addObj(IEntityLoader iEntityLoader, int x, int y,
				int width, int height, String type) {
			// TODO Auto-generated method stub
				final FixtureDef boxFixtureDef = PhysicsFactory.
			    createFixtureDef(2f,0f, 0.9f);
				staticRect = new Rectangle(x, y, 10f, 30f,vbom);
				//staticRect.setUserData(staticBody);
	            staticRect.setColor(0f, 0f, 0.7f);
	            //staticRect.setAlpha(0.5f);
	            attachChild(staticRect);
	            staticBody = PhysicsFactory.createBoxBody(physicsWorld,staticRect,
	                    BodyType.StaticBody, boxFixtureDef);
	            //staticBody.setUserData(staticBody);
	            physicsWorld.registerPhysicsConnector(new PhysicsConnector(
	            		staticRect, staticBody));
		
		
		}
		
		*/
		
		//--------------------------------------------------------------------------------------------
    
       
        this.setBackground(new Background(0,125,58));
        this.setOnSceneTouchListener((IOnSceneTouchListener) this);
        this.setOnAreaTouchListener((IOnAreaTouchListener) this);

        //physicsWorld = new PhysicsWorld(new Vector2(.005f, SensorManager.GRAVITY_EARTH*.9f*-1), false);
        physicsWorld = new PhysicsWorld(new Vector2(0, 0), false);
        /*mGroundBody = this.physicsWorld.createBody(new BodyDef());
        mGroundBodyLeftFoot = this.physicsWorld.createBody(new BodyDef());
        mGroundBodyRightFoot = this.physicsWorld.createBody(new BodyDef());
        */
        setBackground();
        
        //setBackgroundmusic();
        
        createWalls();
        
        this.registerUpdateHandler(physicsWorld);
       
        gameHUD = new HUD();
        scoreAText = new Text(200,camera.getHeight()-25, resourcesManager.mBitmapFont, "Score: 012345678999999999999999999999", vbom);
        scoreAText.setSkewCenter(0,0);    
        //scoreAText.setText(""+scoreA);
        scoreAText.setText("zirikids");
        scoreBText = new Text(570,camera.getHeight()-25, resourcesManager.mBitmapFont, "Score: 012345678999999999999999999999", vbom);
        scoreBText.setSkewCenter(0,0);    
        //scoreBText.setText(""+scoreB);
        scoreBText.setText("ziritap");
        /*
        usernameAText = new Text(150,camera.getHeight()-25, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        usernameAText.setSkewCenter(0,0);    
        usernameAText.setText(((GameActivity) activity).getPreference("localplayer", "Guest"));
        
        usernameBText = new Text(600,camera.getHeight()-25, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        usernameBText.setSkewCenter(0,0);    
        usernameBText.setText(((GameActivity) activity).getPreference("visitorplayer", "Computer"));     
        */
        timeText = new Text(774,19, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        timeText.setSkewCenter(0,0);    
        timeText.setText("");  
        
        gameHUD.attachChild(scoreAText);
        gameHUD.attachChild(scoreBText);
        /*
        gameHUD.attachChild(usernameAText); 
        gameHUD.attachChild(usernameBText);
        */
        gameHUD.attachChild(timeText);
         
        camera.setHUD(gameHUD); 
        attachChild(gameHUD);
        
        
        

       /* 
        gameoverTimerHandler = new TimerHandler(1, true, new ITimerCallback() {

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
            	if(gameDuration>0){
                    gameDuration--;
                    timeText.setText(String.valueOf(gameDuration)); 
                    }else{
                    timeText.setText("0"); 	
                    }
                    
                    if(gameDuration<1){
                    	gameDuration=0;
                    	//replayOrNotText.setText("Game Over. Do You Want To Replay ?");
                    	unregisterUpdateHandler(gameoverTimerHandler);
                    	//gameOver();
                     }        
                  pTimerHandler.reset();
            }
        });

    registerUpdateHandler(gameoverTimerHandler);
        */
        /*
        this.registerUpdateHandler(new TimerHandler(1f, true,new ITimerCallback(){
        	@Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                    gameDuration--;
                    timeText.setText(String.valueOf(gameDuration));  
                    if(gameDuration<1){
                    gameDuration=0;	
                     unregisterUpdateHandler(pTimerHandler);
                     //GameOver();
                     }        
                   pTimerHandler.reset();
        	}
        }));
        */
       
	       this.registerUpdateHandler(new TimerHandler(1f, true,new ITimerCallback(){
	          	@Override
	              public void onTimePassed(TimerHandler pTimerHandler) {
	                      adsDuration--;
	                      //timeText.setText(String.valueOf(gameDuration));  
	                      if(adsDuration==0){
	                       unregisterUpdateHandler(pTimerHandler);
	                       //GameOver();
	                   	hideAds();
	                   
	                       }        
	                     pTimerHandler.reset();
	          	}
	          }));
        
        
        
	}


	


	private void setBackground() {
		// TODO Auto-generated method stub
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		//final VertexBufferObjectManager vertexBufferObjectManager = vbom;
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(camera.getWidth()/2, camera.getHeight()/2, resourcesManager.mParallaxLayerBack, vbom)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-1.0f, new Sprite(0, 400, resourcesManager.mParallaxLayerMid, vbom)));
		//autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-2.0f, new Sprite(0, resourcesManager.mParallaxLayerFront.getHeight()/2, resourcesManager.mParallaxLayerFront, vbom)));
		this.setBackground(autoParallaxBackground);
	}	
	
	private void setBackgroundmusic() {
		// TODO Auto-generated method stub
	if(muteOrNot==false){
		resourcesManager.mGangstaMusic.play();
	}else{
		resourcesManager.mGangstaMusic.pause();
	}
	}	
	
	private void createWalls() {
		// TODO Auto-generated method stub
		FixtureDef GROUND_FIX = PhysicsFactory.createFixtureDef(0.0f,0.0f,0.0f);
        Rectangle ground = new Rectangle(camera.getWidth()/2,0,camera.getWidth(),10,this.engine.getVertexBufferObjectManager());
        ground.setColor(new Color(15,50,0));
        
        Rectangle top = new Rectangle(camera.getWidth()/2,camera.getHeight(),camera.getWidth(),10,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(15,50,0));
        
        Rectangle top2 = new Rectangle((camera.getWidth()/2),camera.getHeight()-45,camera.getWidth()-10,1,this.engine.getVertexBufferObjectManager());
        top.setColor(new Color(15,50,0));
        
        Rectangle rightwall = new Rectangle(camera.getWidth(),camera.getHeight()/2,10,camera.getHeight(),this.engine.getVertexBufferObjectManager());
        rightwall.setColor(new Color(15,50,0));
        
        Rectangle leftwall = new Rectangle(0,camera.getHeight()/2,10,camera.getHeight(),this.engine.getVertexBufferObjectManager());
        leftwall.setColor(new Color(15,50,0));
        /*
        PhysicsFactory.createBoxBody(physicsWorld,ground, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,top, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,leftwall, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,rightwall, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,top2, BodyType.StaticBody,GROUND_FIX);
        */
        this.attachChild(ground);
        this.attachChild(top);
        this.attachChild(leftwall);
        this.attachChild(rightwall);
        this.attachChild(top2);
        
	}
/*
	private void addcontrol() {
		
		
		//-- ball controlled by joystick
		final float centerX = (camera.getWidth() - resourcesManager.football2TextureRegion.getWidth()) / 2;
		final float centerY = (camera.getHeight() - resourcesManager.football2TextureRegion.getHeight()) / 2;
		final Sprite face = new Sprite(centerX, centerY, resourcesManager.football2TextureRegion, vbom);
		final PhysicsHandler physicsHandler = new PhysicsHandler(face);
		face.registerUpdateHandler(physicsHandler);
		FixtureDef FOOTBALL2_FIXT_DEF = PhysicsFactory.createFixtureDef(10.0f,1.0f,0.0f);
        Body staticfootballBody = PhysicsFactory.createCircleBody(physicsWorld,face, BodyType.DynamicBody,FOOTBALL2_FIXT_DEF);
		face.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new ScaleModifier(10, 1, 1.1f), new ScaleModifier(10, 1.1f, 1))));

		this.attachChild(face);
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(face,staticfootballBody,false,true));

    	
    	//-- joystick
    	final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(0, camera.getHeight() - resourcesManager.mOnScreenControlBaseTextureRegion.getHeight(), this.camera, resourcesManager.mOnScreenControlBaseTextureRegion, resourcesManager.mOnScreenControlKnobTextureRegion, 0.1f, 200, vbom, new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				physicsHandler.setVelocity(pValueX * 150, pValueY * 150);
			}

			@Override
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				face.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f), new ScaleModifier(0.25f, 1.5f, 1)));
			}
		});
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();

		this.setChildScene(analogOnScreenControl);
		
	}
*/
	@Override
	public void populateScene() {

		
	       this.registerUpdateHandler(new TimerHandler(1f, true,new ITimerCallback(){
	          	@Override
	              public void onTimePassed(TimerHandler pTimerHandler) {
	                      adsDuration--;
	                      //timeText.setText(String.valueOf(gameDuration));  
	                      if(adsDuration==0){
	                       unregisterUpdateHandler(pTimerHandler);
	                       //GameOver();
	                   	hideAds();
	                   
	                       }        
	                     pTimerHandler.reset();
	          	}
	          }));
		//addcontrol();	
	
		
		//-- letters
		//Sprite letter = new Sprite(camera.getWidth()/8,camera.getHeight()/8,letterAyellowTextureRegion,this.mEngine.getVertexBufferObjectManager());
		//this.scene.attachChild(letter);	
		
		//--------------------------------------------------------------------------------------------
		
				
				
				
		
		//-- menu button -----------------------------------------------------------------------------
		Body btMenubody;
		FixtureDef BTMENU_FIXT_DEF = PhysicsFactory.createFixtureDef(1, 1f, 0.5f);
		
		btMenu = new AnimatedSprite(5+(resourcesManager.btMenuTR.getWidth()/2),camera.getHeight()-5-(resourcesManager.btMenuTR.getHeight()/2),resourcesManager.btMenuTR,engine.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					
					if (localLanguage==0){
					quitOrNotText.setText("Vous voulez quitter !"); 
					}else{
					quitOrNotText.setText("Do You Really Want To Exit !"); 	
					}
					
					setChildScene(goMenuScene, false, true, true);
					
					//onBackKeyPressed();
					
					return true;
	
				} else {
					return false;
				}
			}


		};
		
		//btMenubody = PhysicsFactory.createBoxBody(physicsWorld,btMenu, BodyType.StaticBody,BTMENU_FIXT_DEF);

      //icon.setPosition(camera);
      this.attachChild(btMenu);
      this.registerTouchArea(btMenu);
      
		//physicsWorld.registerPhysicsConnector(new PhysicsConnector(btMenu,btMenubody,false,false));
		//--------------------------------------------------------------------------------------------		
		
		//-- pause button ----------------------------------------------------------------------------
				Body btPausebody;
				FixtureDef BTPAUSE_FIXT_DEF = PhysicsFactory.createFixtureDef(1, 1f, 0.5f);
				//btPause = new AnimatedSprite(camera.getWidth()-25-resourcesManager.mGangstaMusicTextureRegion.getWidth()-resourcesManager.mSoundsTR.getWidth()-(resourcesManager.btPauseTR.getWidth()/2),camera.getHeight()-5-(resourcesManager.btPauseTR.getHeight()/2),resourcesManager.btPauseTR,engine.getVertexBufferObjectManager()){

				btPause = new AnimatedSprite(camera.getWidth()/2,camera.getHeight()-5-(resourcesManager.btPauseSmallTR.getHeight()/2),resourcesManager.btPauseSmallTR,engine.getVertexBufferObjectManager()){
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
						if(pSceneTouchEvent.isActionDown()) {
							// Completely remove all resources associated with this sprite. 
			
							//SceneManager.getInstance().loadMainPauseScene(engine);
							
							pauseScene();
							
							return true;
			
						} else {
							return false;
						}
					}


				};
				
				//btPausebody = PhysicsFactory.createBoxBody(physicsWorld,btPause, BodyType.StaticBody,BTPAUSE_FIXT_DEF);

				
		      this.attachChild(btPause);
		      this.registerTouchArea(btPause);
		      
			//physicsWorld.registerPhysicsConnector(new PhysicsConnector(btPause,btPausebody,false,false));
		//--------------------------------------------------------------------------------------------		
				
		
		//--  music toggle -------------------------------------------------------------------------------
		
		//Body musicNotesbody;
		//FixtureDef NOTES_FIXT_DEF = PhysicsFactory.createFixtureDef(1, 1f, 0.5f);
  		musicNotes = new Sprite(camera.getWidth()-5-resourcesManager.btMusicTR.getWidth()/2, camera.getHeight()-5-resourcesManager.btMusicTR.getHeight()/2, resourcesManager.btMusicTR, vbom){
      				
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()){
			case TouchEvent.ACTION_DOWN:
				
				
				/*
				if(resourcesManager.mGangstaMusic.isPlaying()) {
					resourcesManager.mGangstaMusic.pause();
					muteOrNot=true;
					
					
				} else {
					resourcesManager.mGangstaMusic.play();
					muteOrNot=false;
				}
				*/
				if(muteOrNot==true) {
					muteOrNot=false;
					resourcesManager.mGangstaMusic.play();
				} else {							
					muteOrNot=true;
					resourcesManager.mGangstaMusic.pause();
				}
				
				
			
				musicNotesMute.setVisible(muteOrNot);

				break;
			case TouchEvent.ACTION_UP:

				break;
			}
			return true;
      	}
		};
		//musicNotesbody = PhysicsFactory.createBoxBody(physicsWorld,musicNotes, BodyType.StaticBody,NOTES_FIXT_DEF);
		this.attachChild(musicNotes);
		this.registerTouchArea(musicNotes);
		
		//physicsWorld.registerPhysicsConnector(new PhysicsConnector(musicNotes,musicNotesbody,false,false));
		
		////////
  		musicNotesMute = new Sprite(camera.getWidth()-5-resourcesManager.btMusicMuteTR.getWidth()/2, camera.getHeight()-5-resourcesManager.btMusicMuteTR.getHeight()/2, resourcesManager.btMusicMuteTR, vbom);
		this.attachChild(musicNotesMute);
		musicNotesMute.setVisible(muteOrNot);
		////
		
		//--------------------------------------------------------------------------------------------		

		/*
		//--  sounds toggle -----------------------------------------------------------------------------
		
				//Body soundNotesbody;
				//FixtureDef SOUND_NOTES_FIXT_DEF = PhysicsFactory.createFixtureDef(1, 1f, 0.5f);
		  		soundNotes = new Sprite(camera.getWidth()-15-resourcesManager.btMusicTR.getWidth()-resourcesManager.btSoundsTR.getWidth()/2, camera.getHeight()-5-resourcesManager.btSoundsTR.getHeight()/2, resourcesManager.btSoundsTR, vbom){
		      				
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()){
					case TouchEvent.ACTION_DOWN:
						
						if(soundsOrNot==true) {
							soundsOrNot=false;							
						} else {							
							soundsOrNot=true;
						}			
						soundNotesMute.setVisible(soundsOrNot);

						break;
					case TouchEvent.ACTION_UP:

						break;
					}
					return true;
		      	}
				};
				//soundNotesbody = PhysicsFactory.createBoxBody(physicsWorld,soundNotes, BodyType.StaticBody,SOUND_NOTES_FIXT_DEF);
				this.attachChild(soundNotes);
				this.registerTouchArea(soundNotes);
				
				//physicsWorld.registerPhysicsConnector(new PhysicsConnector(soundNotes,soundNotesbody,false,false));
				
				////////
		  		soundNotesMute = new Sprite(camera.getWidth()-15-resourcesManager.btMusicTR.getWidth()-resourcesManager.btSoundsMuteTR.getWidth()/2, camera.getHeight()-5-resourcesManager.btSoundsMuteTR.getHeight()/2, resourcesManager.btSoundsMuteTR, vbom);
				this.attachChild(soundNotesMute);
				soundNotesMute.setVisible(soundsOrNot);
				////
		
		//--------------------------------------------------------------------------------------------		
		*/
		//final AnimatedSprite balls;
			
			//FixtureDef GREENGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8.0f, .9f, 0.5f);	
			//FixtureDef REDGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8.0f, .9f, 0.5f);				
			FixtureDef FOOTANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(5, .9f, 0.5f);				
			FixtureDef BEACHANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(1, .9f, 0.5f);				
			FixtureDef TENNISANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8, .9f, 0.8f);				
			FixtureDef BILLARDANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(13, .9f, 0.5f);				
			FixtureDef VOLLEYANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(5, .9f, 0.5f);				
			//FixtureDef TCHOUPIANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(10, .9f, 0.5f);	
			
			FixtureDef PITCHOUBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(10, .9f, 0.5f);
			/*		
			FixtureDef GREENGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);	
			FixtureDef REDGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);				
			FixtureDef FOOTANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);				
			FixtureDef BEACHANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);				
			FixtureDef TENNISANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.8f);				
			FixtureDef BILLARDANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);				
			FixtureDef VOLLEYANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);				
			FixtureDef TCHOUPIANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(0f, 1f, 0.5f);
			*/		
		 //////////////////////////////////////////////////////////////////////////////////////////////
			// kinematic rectangle
			/*
			FixtureDef BoxBodyFixtureDef =PhysicsFactory.createFixtureDef(20f, 0f, 0.5f);
			Rectangle kinematicRectangle = new Rectangle(600f, 100f,40f, 40f, vbom);
			kinematicRectangle.setColor(0.8f, 0.8f, 1.7f);
			attachChild(kinematicRectangle);
			Body kinematicBody = PhysicsFactory.createBoxBody(physicsWorld,kinematicRectangle, BodyType.KinematicBody, BoxBodyFixtureDef);
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(kinematicRectangle, kinematicBody));
			kinematicBody.setLinearVelocity(-2f, 1f);
			kinematicBody.setAngularVelocity((float) (12));
			*/
			
			/*
			final float maxMovementY = 50;
			FixtureDef BoxBodyFixtureDef =PhysicsFactory.createFixtureDef(1f, 0f, 0.5f);
			
			
			final Sprite rightkeeper = new Sprite(camera.getWidth()-67, camera.getHeight()/2, resourcesManager.keeperrightTR,vbom);       
			rightkeeper.setCullingEnabled(true);
			final Body rightkeeperbody = PhysicsFactory.createBoxBody(physicsWorld, rightkeeper, BodyType.KinematicBody, BoxBodyFixtureDef);
			rightkeeperbody.setLinearVelocity(0,-1 * 5);
			rightkeeperbody.setAngularVelocity((float) (12));      
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(rightkeeper, rightkeeperbody, true, false)
			{
			    @Override
			    public void onUpdate(float pSecondsElapsed)
			    {
			        super.onUpdate(pSecondsElapsed);
			               
			        if (rightkeeper.getY() <= 240 - maxMovementY)
			        {
			        	rightkeeperbody.setLinearVelocity(0,rightkeeperbody.getLinearVelocity().y * -1);
			        }
			        if (rightkeeper.getY() >= 240 + maxMovementY)
			        {
			        	rightkeeperbody.setLinearVelocity(0,rightkeeperbody.getLinearVelocity().y * -1);
			        }
			    }
			});
			attachChild(rightkeeper);
			
			final Sprite leftkeeper = new Sprite(67, camera.getHeight()/2, resourcesManager.keeperleftTR,vbom);       
			leftkeeper.setCullingEnabled(true);
			final Body leftkeeperbody = PhysicsFactory.createBoxBody(physicsWorld, leftkeeper, BodyType.KinematicBody, BoxBodyFixtureDef);
			leftkeeperbody.setLinearVelocity(0,-1 * 5);
			leftkeeperbody.setAngularVelocity((float) (12));      
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(leftkeeper, leftkeeperbody, true, false)
			{
			    @Override
			    public void onUpdate(float pSecondsElapsed)
			    {
			        super.onUpdate(pSecondsElapsed);
			               
			        if (leftkeeper.getY() <= 240 - maxMovementY)
			        {
			        	leftkeeperbody.setLinearVelocity(0,leftkeeperbody.getLinearVelocity().y * -1);
			        }
			        if (leftkeeper.getY() >= 240 + maxMovementY)
			        {
			        	leftkeeperbody.setLinearVelocity(0,leftkeeperbody.getLinearVelocity().y * -1);
			        }
			    }
			});
			attachChild(leftkeeper);
			
			*/
			
			/*
			addBall("foot",nbFootBall1,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.footballTR);
			addBall("foot2",nbFootBall2,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.football2TR);
			addBall("beach",nbBeachBall,football,footballBody,BEACHANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.beachballTR);
			addBall("billard",nbBillardBall,football,footballBody,BILLARDANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.billardballTR);
			addBall("tennis",nbTennisBall,football,footballBody,TENNISANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.tennisballTR);
			addBall("tchoupi",nbTchoupiBall,tchoupiball,tchoupiballBody,TCHOUPIANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.tchoupiballTR);
			addBall("volley",nbVolleyBall,volleyball,volleyballBody,VOLLEYANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.volleyballTR);
			
			addBall("pitchougreen",nbPitchougreenBall,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.pitchougreenTR);
			addBall("pitchoupurple",nbPitchoupurpleBall,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.pitchoupurpleTR);
			*/
			
			addBall(3,442,246,"monkey",1,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_003TR);
			addBall(4,280,280,"pig",1,football,footballBody,TENNISANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_004TR);
			addBall(5,90,95,"horse",1,football,footballBody,TENNISANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_005TR);
			addBall(6,591,98,"dog",1,volleyball,volleyballBody,VOLLEYANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_006TR);	
			addBall(7,160,200,"cow",1,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_007TR);
			addBall(8,46,395,"cat",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_008TR);
			//addBall(9,110,30,"pitchoupurple",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.pitchoupurpleTR);
			addBall(10,542,382,"bird",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_010TR);
			addBall(11,330,170,"pitchougreen",1,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.pitchougreenTR);
			addBall(12,390,360,"bird",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_012TR);
			addBall(13,750,370,"hen",1,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_013TR);
			addBall(14,409,70,"dolphin",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_014TR);
			addBall(15,722,80,"sheep",1,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_015TR);
			//addBall(16,705,247,"pitchoupurple",1,football,footballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.pitchoupurpleTR);
			addBall(17,705,247,"wolf",1,football,footballBody,BEACHANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_017TR);
			addBall(18,245,73,"rooster",1,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_018TR);
			addBall(19,590,222,"donkey",1,football,footballBody,BILLARDANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_019TR);
			addBall(20,180,395,"bird",1,volleyball,volleyballBody,PITCHOUBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.dimg_020TR);

			
           //this.setTouchAreaBindingOnActionDownEnabled(true);
           //this.setTouchAreaBindingOnActionMoveEnabled(true);
           //this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
           //this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
            
            
            
	}

	public boolean addBall(int idball,float xpos, float ypos,String typeBall,int nbBall,AnimatedSprite xAnimBall,Body xAnimBallBody,FixtureDef fixtureDef,BodyType bodyType,TiledTextureRegion tiledTextureRegion ){
		
		for (int i=0;i<nbBall;i++){
			/*
			float minX = 100.0f;
			float maxX = 700.0f;
			float minY = 130f;
			float maxY = 380.0f;
			//String faceName="faceBall_"+nbBall;
			Random rand = new Random();

			float finalX = rand.nextFloat() * (maxX - minX) + minX;
			float finalY = rand.nextFloat() * (maxY - minY) + minY;
			*/
			float finalX = xpos;
			float finalY = ypos;
			final int iidball=idball;
	      	//final AnimatedSprite xAnimBall;
	      	//final Body xAnimBallbody;
			final FixtureDef XANIMBALL_FIXT_DEF = fixtureDef;
			xAnimBall = new AnimatedSprite(finalX, finalY, tiledTextureRegion, vbom){
			
				
				@Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                       
					switch(pSceneTouchEvent.getAction()) {
                                case TouchEvent.ACTION_DOWN:
                                this.animate(200,3);
                                playTaptapSound(iidball);
                                        break;
                                case TouchEvent.ACTION_MOVE:
         
                                        break;
                                case TouchEvent.ACTION_UP:
                                	
                                        break;
                        }
                        return true;
						
				}
			};
				
			
				
				
				
				
			
			xAnimBallBody = PhysicsFactory.createCircleBody(physicsWorld, xAnimBall, bodyType, XANIMBALL_FIXT_DEF);
			xAnimBall.setUserData(xAnimBallBody);
			//xAnimBall.setScale(.6f);
			//xAnimBall.animate(200);
			xAnimBallBody.setUserData(xAnimBallBody);
			this.attachChild(xAnimBall);
			this.registerTouchArea(xAnimBall);
	

			//this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(xAnimBall, xAnimBallBody, true, true));
		}
		
		return true;
	}
/*
	public boolean addBall(String typeBall,int nbBall,AnimatedSprite xAnimBall,Body xAnimBallBody,FixtureDef fixtureDef,BodyType bodyType,TiledTextureRegion tiledTextureRegion ){
		
		for (int i=0;i<nbBall;i++){
			
			float minX = 100.0f;
			float maxX = 700.0f;
			float minY = 130f;
			float maxY = 380.0f;
			//String faceName="faceBall_"+nbBall;
			Random rand = new Random();

			float finalX = rand.nextFloat() * (maxX - minX) + minX;
			float finalY = rand.nextFloat() * (maxY - minY) + minY;
			
	      	//final AnimatedSprite xAnimBall;
	      	//final Body xAnimBallbody;
			final FixtureDef XANIMBALL_FIXT_DEF = fixtureDef;
			xAnimBall = new AnimatedSprite(finalX, finalY, tiledTextureRegion, vbom){
			
				
				@Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                       
					switch(pSceneTouchEvent.getAction()) {
                                case TouchEvent.ACTION_DOWN:
                                this.animate(200,3);
                                        break;
                                case TouchEvent.ACTION_MOVE:
         
                                        break;
                                case TouchEvent.ACTION_UP:
                                	
                                        break;
                        }
                        return true;
						
				}
			};
				
			
				
				
				
				
			
			xAnimBallBody = PhysicsFactory.createCircleBody(physicsWorld, xAnimBall, bodyType, XANIMBALL_FIXT_DEF);
			xAnimBall.setUserData(xAnimBallBody);
			//xAnimBall.setScale(.6f);
			//xAnimBall.animate(200);
			xAnimBallBody.setUserData(xAnimBallBody);
			this.attachChild(xAnimBall);
			this.registerTouchArea(xAnimBall);
	

			//this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(xAnimBall, xAnimBallBody, true, true));
		}
		
		return true;
	}
	*/
	private void goMenu() {
		
		try{
			if(muteOrNot==false){
			try{					
				resourcesManager.exitSound.play();					
			} catch (Exception ex){
				ex.printStackTrace();
			}
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
			
		//SceneManager.getInstance().loadMainMenuScene(engine);
		scoreAText.setText("");
		scoreBText.setText("");
		//usernameAText.setText("");
		//usernameBText.setText("");
		timeText.setText("");
		detachChild(gameHUD);
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		try{
			resourcesManager.mGangstaMusic.release();			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		resourcesManager.unloadBallGameResources();
		//disposeScene();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	private void returnGame() {
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		this.clearChildScene();
		
	}
	
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		if (localLanguage==0){
		quitOrNotText.setText("Vous voulez quitter !"); 
		}else{
		quitOrNotText.setText("Do You Really Want To Exit !"); 	
		}
		this.setChildScene(goMenuScene, false, true, true);
	
	}
	
	

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_BALLGAME;
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
		
		if (pSceneTouchEvent.isActionDown()){
		if (capturedSprite==null){
			
		//	playParticles(pTouchAreaLocalX,pTouchAreaLocalY,3);			
			
		}
		}
		return false;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		try{
			playParticles(pSceneTouchEvent.getX(),pSceneTouchEvent.getY(),3);	
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.gc();
		
		return false;
	}
	
	private void playParticles(float x,float y,float duration){
		
		//--particles-----------------------------------------------------------------------
		
		//final CircleOutlineParticleEmitter particleEmitter = new CircleOutlineParticleEmitter(400, 210, 30);
		final CircleParticleEmitter particleEmitter = new CircleParticleEmitter(x, y, 40);
		final SpriteParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter, 40, 60, 20, resourcesManager.mParticle24TR, vbom);
	
		particleSystem.addParticleInitializer(new ColorParticleInitializer<Sprite>(12, 43, 7));
		particleSystem.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0));
		particleSystem.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-2, 2, -20, -10));
		particleSystem.addParticleInitializer(new RotationParticleInitializer<Sprite>(0.0f, 360.0f));
		particleSystem.addParticleInitializer(new ExpireParticleInitializer<Sprite>(3));

		particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0, 1, 0.5f, 2.0f));
		particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(2, 3, 2f, 0f));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(0, 1, 12, 77, 43, 79, 7, 21));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(2, 3, 77, 99, 79, 48, 21, 52));
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0, 0.1f, 0, 1));
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(2, 3, 1, 0));

		this.attachChild(particleSystem);
		engine.registerUpdateHandler(new TimerHandler(duration, new ITimerCallback() 
		{
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
            	engine.unregisterUpdateHandler(pTimerHandler);
            	
            	particleSystem.detachSelf();
            	particleSystem.dispose();
            }
		}));

		//----------------------------------------------------------------------------------
		
		
		
	}


	@Override
	public void onPauseScene() {
		// TODO Auto-generated method stub
		if(engine.isRunning()) {
			this.setChildScene(pauseScene, false, true, true);
			try{				
				resourcesManager.mGangstaMusic.pause();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			engine.stop();
			
		} else {
			
			this.clearChildScene();
			
			engine.start();
		
			try{
				if (muteOrNot==true){
					resourcesManager.mGangstaMusic.pause();
				}else{
					resourcesManager.mGangstaMusic.play();
				}
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}

    
	//return true;
  
	}


	@Override
	public void onResumeScene() {
		
		try{
			if (muteOrNot==true){
				resourcesManager.mGangstaMusic.pause();
			}else{
				resourcesManager.mGangstaMusic.play();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		/*
		if (muteOrNot==true){
			resourcesManager.mGangstaMusic.pause();
		}else{
			resourcesManager.mGangstaMusic.play();
		}
		*/
	}
	public void pauseScene() {
		// TODO Auto-generated method stub
		
		this.setChildScene(pauseScene, false, true, true);
		try{				
			resourcesManager.mGangstaMusic.pause();
			//gameState = STATE_PAUSE;
		} catch (Exception ex){
				ex.printStackTrace();
		}
	
	}

	public void resumeScene() {
	// TODO Auto-generated method stub
			
		this.clearChildScene();
		//gameState = STATE_RUNNING;
		try{
			if (muteOrNot==true){
				resourcesManager.mGangstaMusic.pause();
			}else{
				resourcesManager.mGangstaMusic.play();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	
	}

	private void initScene() {
	
		try{
			

		scoreAText.setText("");
		scoreBText.setText("");
		//usernameAText.setText("");
		//usernameBText.setText("");
		timeText.setText("");
		detachChild(gameHUD);
		levelCompleteText.setText("");
		detachChild(levelcompleteHUD);
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		gameDuration=50;
		levelID+=1;
		
		//clearPhysicsWorld(physicsWorld);
	
		//clearScene();
		this.clearChildScene();
		this.detachChildren();
		this.reset();
		this.detachSelf();
		//System.gc();
	    
		initVariables(levelID,false,false,levelID*7,scoreA,scoreB,0,0,muteOrNot,soundsOrNot,100,false,false,false,false);
		initLevelVariables(3,levelID, levelID,levelID, levelID,levelID, levelID, levelID,levelID,levelID);
		createScene();
		populateScene();
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	

/*
	public void clearScene()
	{
		engine.runOnUpdateThread(new Runnable()
		{
			@Override
			public void run()
			{
				//onCleanScene();
				cleanEntities();
				clearTouchAreas();
				clearUpdateHandlers();
				System.gc();
			}
		});
	}
	
	public void cleanEntities()
	{	
		for (IEntity entity: myEntitiesList)
		{
			entity.clearEntityModifiers();
			entity.clearUpdateHandlers();
			entity.detachSelf();
			
			if (!entity.isDisposed())
			{
				entity.dispose();
			}
		}
		
		myEntitiesList.clear();
		myEntitiesList = null; 
	}
	*/
	protected void clearPhysicsWorld(PhysicsWorld physicsWorld)
	{
		
		Iterator<Joint> allMyJoints = physicsWorld.getJoints();
		while (allMyJoints.hasNext())
		{
			try
			{
				final Joint myCurrentJoint = allMyJoints.next();
				physicsWorld.destroyJoint(myCurrentJoint);
			} 
			catch (Exception localException)
			{
				//Debug.d("SPK - THE JOINT DOES NOT WANT TO DIE: " + localException);
			}
		}
		
		Iterator<Body> localIterator = physicsWorld.getBodies();
		while (true)
		{
			if (!localIterator.hasNext())
			{
				physicsWorld.clearForces();
				physicsWorld.clearPhysicsConnectors();
				physicsWorld.reset();
				//physicsWorld.dispose();
				//physicsWorld = null;
				return;
			}
			try
			{
				physicsWorld.destroyBody(localIterator.next());
			} 
			catch (Exception localException)
			{
				//Debug.d("SPK - THE BODY DOES NOT WANT TO DIE: " + localException);
			}
		}
		
		/*
		   if(dSprite.size()>0){
                 this.runOnUpdateThread(new Runnable(){
                     @Override
                     public void run() {
                       for(int i=0; i < dSprite.size(); i++){                  
                             try {
                                 final int myI = i;
                                         mScene.detachChild(dSprite.get(myI));                
                            } catch (Exception e) {
                                Debug.d("SPK - THE SPRITE DOES NOT WANT TO DIE: " + e);
                            }
                       }
                      }
                });
        }
        dSprite.clear();
        
		  if(dAnimatedSprite.size()>0){
                for(int i=0; i < dAnimatedSprite.size(); i++){
                        this.mScene.detachChild(dAnimatedSprite.get(i));
                }
        }
                // unload the contents of the ArrayList
        dAnimatedSprite.clear();
               
                // check if the ArrayList contains anything
        if(dShape.size()>0){
                for(int i=0; i < dShape.size(); i++){
                        this.mScene.detachChild(dShape.get(i));
                }
        }
                // unload the contents of the ArrayList
        dShape.clear();
	
		 */
	}
	
	
	private void initSound(){
	//-- init sound ---------------------------------------------------------------------
	try{
		
        prefGameSounds = ((GameActivity) activity).getPreference("gamesound", true);
		if (prefGameSounds==false){
			soundsOrNot=true;
		} else {
			soundsOrNot=false;
		}
    	}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------
	//-- init music ---------------------------------------------------------------------
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
	//-----------------------------------------------------------------------------------
	//-- init language ---------------------------------------------------------------------
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
	
	private void playTaptapSound(int i){
		if(muteOrNot==false){
		try{
		switch(i){
			case 1:
				resourcesManager.snd001.play();						
			break;
			case 2:
				resourcesManager.snd002.play();						
			break;
			case 3:
				resourcesManager.snd003.play();						
			break;
			case 4:
				resourcesManager.snd004.play();						
			break;			
			case 5:
				resourcesManager.snd005.play();						
			break;			
			case 6:
				resourcesManager.snd006.play();						
			break;
			case 7:
				resourcesManager.snd007.play();						
			break;
			case 8:
				resourcesManager.snd008.play();						
			break;
			case 9:
				resourcesManager.snd009.play();						
			break;
			case 10:
				resourcesManager.snd010.play();						
			break;
			case 11:
				resourcesManager.snd011.play();						
			break;
			case 12:
				resourcesManager.snd012.play();						
			break;
			case 13:
				resourcesManager.snd013.play();						
			break;
			case 14:
				resourcesManager.snd014.play();						
			break;
			case 15:
				resourcesManager.snd015.play();						
			break;
			case 16:
				resourcesManager.snd016.play();						
			break;
			case 17:
				resourcesManager.snd017.play();						
			break;
			case 18:
				resourcesManager.snd018.play();						
			break;
			case 19:
				resourcesManager.snd019.play();						
			break;
			case 20:
				resourcesManager.snd020.play();						
			break;
		}
		} catch (Exception ex){
						ex.printStackTrace();
					}
					}
	}
	private void initVariables(int ilevel,boolean ishowstarsleft,boolean ishowstarsright, int inbobjectsinscene, int iscoreA,int iscoreB, int imfacecount, int iaddornotface,
			boolean imuteornot,boolean isoundsOrNot, int igamepagecounter, boolean isetfullalphafordynamicbody, boolean isethalfalphafordynamicbody,
			boolean isetfullalphaforstaticbody, boolean isethalfalphaforstaticbody) {
		
		levelID=ilevel;
		showStarsleft=ishowstarsleft;
		showStarsright=ishowstarsright;
		nbObjectsInScene = inbobjectsinscene;
		scoreA=iscoreA;
		scoreB=iscoreB;
		mFaceCount=imfacecount;
    	addornotFace=iaddornotface;
       	muteOrNot=imuteornot;  
       	soundsOrNot=isoundsOrNot;  
    	gamePageCounter = igamepagecounter;
		
    	
        
        
	}	
	private void initLevelVariables(int imoduloaddface,int inbFootBall1, int inbFootball2, 
			int inbtennisball, int inbvolleyball,
			int inbbillardball, int inbbeachball, int inbtchoupiball, int nbpitchougreenball, int nbpitchoupurpleball) {
		
		moduloAddFace=imoduloaddface;
		nbFootBall1=inbFootBall1;
		nbFootBall2=inbFootball2;
		nbTennisBall=inbtennisball;
		nbVolleyBall=inbvolleyball;
		nbBillardBall=inbbillardball;
		nbBeachBall=inbbeachball;
		nbTchoupiBall=inbtchoupiball;
		nbPitchougreenBall=nbpitchougreenball;
		nbPitchoupurpleBall=nbpitchoupurpleball;
	
		
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
}
