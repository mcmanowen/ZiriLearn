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
import org.andengine.entity.particle.emitter.CircleOutlineParticleEmitter;
import org.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.AccelerationParticleInitializer;
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
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class BallGame24Scene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener {
	
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
	private int adsDuration=20;
	private int localIdProfile; 
	
	Body staticBody;
	Rectangle staticRect,leftNetleftBar,leftNetrightBar,rightNetleftBar,rightNetrightBar;
	
	//-- collisions -------------------------------
	boolean setFullAlphaForDynamicBody = false;
    boolean setHalfAlphaForDynamicBody = false;
    boolean setFullAlphaForStaticBody = false;
    boolean setHalfAlphaForStaticBody = false;
    
    boolean showStarsleft,showStarsright;
    //---------------------------------------------
    
    //-- variables ------------------------------------
    ArrayList<Body> bodyArray=new ArrayList<Body>();
    static int bodyCount=0;
    	
    	//private long startTime=0;
    	private int gameDuration=30;
    	private static final int STATE_RUNNING = 1;
    	private static final int STATE_NEXTLEVEL = 2;
    	private static final int STATE_PAUSE = 2;
    	private static final int STATE_ENDED = 4;
    	private volatile int gameState=0;
	   	
    	//--init variables
    	int levelID=0;
    	int nbObjectsInScene;
		int mFaceCount;
		int addornotFace;
		int gamePageCounter;
		Text scoreAText;
		//Text scoreBText;
		Text levelScoreText;
		Text usernameAText;
		//Text usernameBText;
		Text timeText;
		Text levelCompleteText;
		Text quitOrNotText;
		Text gameoverText;
		Text finalscoreText;
		Text replayOrNotText;
		int scoreA=0;
		int highestscoretoday=0;
		//int scoreB=0;
		HUD gameHUD;
		HUD quitgameHUD;
		HUD gameoverHUD;
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
	 //-------------------------------------------------	
		
		private CameraScene pauseScene,winScene,goMenuScene,gameOverScene;

		
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
		//Sprite leftFoot,rightFoot;
		//AnimatedSprite leftFoot,rightFoot;
		AnimatedSprite starsleft,starsright;
		AnimatedSprite beachball,tennisball,billardball,volleyball,
		football,football2,gymballgreen,gymballred,tchoupiball,hole01,netleft,netright;
		//--------------------------------------------------
		//Body leftFootbody,rightFootbody;
		Body beachballBody,tennisballBody,billardballBody,volleyballBody,
		footballBody,football2Body,gymballgreenBody,gymballredBody,tchoupiballBody;
		//Body hole01body,netleftbody,netrightbody;
		//--------------------------------------------------
		
		//-- left and right foot touch flag
		boolean isTouchedFlagRight=false;
		boolean isTouchedFlagLeft=false;
		//--  left and right foots positions
		float xleftPos=100;
		float yleftPos=camera.getHeight()/2;
		float xrightPos=700;
		float yrightPos=camera.getHeight()/2;
		
		
		//-- mouse joint -----------------------------------
		private MouseJointDef mouseJointDef;
		private MouseJoint mMouseJointActive;
		private Body mGroundBody=null;
		/*
		private MouseJointDef mouseJointDefLeftFoot;
		private MouseJoint mMouseJointActiveLeftFoot;
		private Body mGroundBodyLeftFoot;
		
		private MouseJointDef mouseJointDefRightFoot;
		private MouseJoint mMouseJointActiveRightFoot;
		private Body mGroundBodyRightFoot;
		*/
		PhysicsWorld physicsWorld;
		
		private TimerHandler gameoverTimerHandler;
		//---------------------------------------------
		// METHODS FROM SUPERCLASS
		//---------------------------------------------


	@Override
	public void createScene() {
		adsDuration=20;
		gameDuration=30;
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
		gameState = STATE_RUNNING;
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

		initVariables(1,false,false,7,0,0,0,muteOrNot,soundsOrNot,100,false,false,false,false);
	
		initLevelVariables(6,1,1,1,1,1,1,1);
		
		}else{
			
		}
		mouseJointDef=null;
		mMouseJointActive=null;
		/*
		mouseJointDefLeftFoot=null;
		mMouseJointActiveLeftFoot=null;
		mouseJointDefRightFoot=null;
		mMouseJointActiveRightFoot=null;
		mGroundBodyLeftFoot=null;
		mGroundBodyRightFoot=null;
		*/
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
		
      //--game over scene ----------------------------------------------------------------------------------
    	
      		gameOverScene = new CameraScene(camera);
      		// Make the 'WIN'-label centered on the camera. 
      		final Sprite replaySprite = new Sprite(centerX, centerY, resourcesManager.emptyWindowTR, vbom);
      		gameOverScene.attachChild(replaySprite);
      		// Makes the paused Game look through. 
      		gameOverScene.setBackgroundEnabled(false);
          	
      		final TiledSprite replayYes;
      		replayYes = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom){
      				
      			@Override
      			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
      			switch(pSceneTouchEvent.getAction()){
      			case TouchEvent.ACTION_DOWN:
      				this.setCurrentTileIndex(1);
      				
      				break;
      			case TouchEvent.ACTION_UP:
      				this.setCurrentTileIndex(0);
      				levelID=0;
      				scoreA=0;
      				initScene();
      				break;
      			}
      			return true;
            	}


      		};
      		replayYes.setCurrentTileIndex(0);
      		gameOverScene.attachChild(replayYes);

      		gameOverScene.registerTouchArea(replayYes);
      		
      		
      		final TiledSprite replayNo;
      		replayNo = new TiledSprite(300, 150, resourcesManager.btNoTR, vbom){
      				
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
      		replayNo.setCurrentTileIndex(0);
      		gameOverScene.attachChild(replayNo);

      		gameOverScene.registerTouchArea(replayNo);
      		
      		gameoverHUD = new HUD();
     		
      		gameoverText = new Text(400,380, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
      		gameoverText.setSkewCenter(0,0);    
      		gameoverText.setText("");  
      		
      		finalscoreText = new Text(400,310, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
      		finalscoreText.setSkewCenter(0,0);    
      		finalscoreText.setText("");  
      		
      		replayOrNotText = new Text(400,240, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
      		replayOrNotText.setSkewCenter(0,0);    
      		replayOrNotText.setText(""); 
      		
      		//quitOrNotText.setText("Do You Really Want To Exit !");
      		gameoverHUD.attachChild(gameoverText);
      		gameoverHUD.attachChild(finalscoreText);
      		gameoverHUD.attachChild(replayOrNotText);         
            camera.setHUD(gameoverHUD); 
            gameOverScene.attachChild(gameoverHUD);
      		
          	//---------------------------------------------------------------------------------------------
      		
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
        //--with gravity
        //physicsWorld = new PhysicsWorld(new Vector2(.5f, SensorManager.GRAVITY_EARTH*-1), false);
        physicsWorld = new PhysicsWorld(new Vector2(.005f, SensorManager.GRAVITY_EARTH*.9f*-1), false);
        //--no gravity
        //physicsWorld = new PhysicsWorld(new Vector2(0, 0), false);
        
        /*mGroundBody = this.physicsWorld.createBody(new BodyDef());
        mGroundBodyLeftFoot = this.physicsWorld.createBody(new BodyDef());
        mGroundBodyRightFoot = this.physicsWorld.createBody(new BodyDef());
        */
        setBackground();
        
        setBackgroundmusic();
        
        createWalls();
        
        this.registerUpdateHandler(physicsWorld);
       
        gameHUD = new HUD();
        scoreAText = new Text(330,camera.getHeight()-25, resourcesManager.mBitmapFont, "Score: 012345678999999999999999999999", vbom);
        scoreAText.setSkewCenter(0,0);    
        scoreAText.setText(""+scoreA);
        /*
        scoreBText = new Text(460,camera.getHeight()-25, resourcesManager.mBitmapFont, "Score: 012345678999999999999999999999", vbom);
        scoreBText.setSkewCenter(0,0);    
        scoreBText.setText(""+scoreB);
        */
        usernameAText = new Text(150,camera.getHeight()-25, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        usernameAText.setSkewCenter(0,0);    
        usernameAText.setText(((GameActivity) activity).getPreference("localplayer", "Guest"));
        /*
        usernameBText = new Text(600,camera.getHeight()-25, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        usernameBText.setSkewCenter(0,0);    
        usernameBText.setText(((GameActivity) activity).getPreference("visitorplayer", "Computer"));     
        */
        timeText = new Text(600,camera.getHeight()-25, resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyz", vbom);
        timeText.setSkewCenter(0,0);    
        timeText.setText("");  
        
        gameHUD.attachChild(scoreAText);
        //gameHUD.attachChild(scoreBText);
        gameHUD.attachChild(usernameAText); 
        //gameHUD.attachChild(usernameBText);
        gameHUD.attachChild(timeText);
         
        camera.setHUD(gameHUD); 
        attachChild(gameHUD);
        
        
        /*
        this.registerUpdateHandler(new IUpdateHandler() {
        	 
            @Override
            public void onUpdate(float pSecondsElapsed) {
                // TODO Auto-generated method stub
                 
                if(setFullAlphaForDynamicBody) {
                	//staticRect.setColor(Color.RED);

                    setFullAlphaForDynamicBody = false;
                 } else if(setHalfAlphaForDynamicBody) {
                    //staticRect.setColor(Color.BLACK);
                    
                    setHalfAlphaForDynamicBody = false;
                 }
                  
                if(setFullAlphaForStaticBody) {
                    //staticRect.setColor(Color.GREEN);
                    
                	if(bodyArray.size()>0)
                	{
                	for(int i=0;i<bodyArray.size();i++) {
                	try {
                	final int myid=i;
                	//Debug.e("ID = "+myid+"+BODY SIZE="+bodyArray.size()+" the BODY ="+bodyArray.get(myid).getUserData());
                	
                	//final AnimatedSprite as=(AnimatedSprite) bodyArray.get(myid).getUserData();
   
                	//final Body body = bodyArray.get(myid);
                	//physicsWorld.destroyBody(body);
    				//body.setActive(false);
                	
                	//final Object as=body.getUserData();
                	
                	/*
                	engine.runOnUpdateThread(new Runnable() {
            			@Override
            			public void run() {
			
            	            try {
            	            
            				//physicsWorld.destroyBody(body);
            				//body.setActive(false);
            				
            				//removeFace((AnimatedSprite) as);
            				
            				}catch(Exception ex){
    		                	ex.printStackTrace();
    		                }
            					
            			}
            			});
                	////////*
                	
                	
                	} catch (Exception e) {
                	e.printStackTrace();
                	}
                	 
                	}
                	bodyArray.clear();
                	}
                	 
                    
                    setFullAlphaForStaticBody = false;
                 } else if(setHalfAlphaForStaticBody) {
                   
                    	//staticRect.setColor(Color.YELLOW);
                    setHalfAlphaForStaticBody = false;
                    }
                     

            }

            @Override
            public void reset() {
                // TODO Auto-generated method stub
                 
            }          
        
        });
        */
       // gameoverTimerHandler
        
     

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
                            	if (scoreA > highestscoretoday){
                            		try{					
                        				resourcesManager.bravokidsSound.play();					
                        			} catch (Exception ex){
                        				ex.printStackTrace();
                        			}
                            		highestscoretoday=scoreA;
                            		finalscoreText.setText("new record : "+scoreA+" points");
                            	}else {
                            	finalscoreText.setText("Your score is : "+scoreA+" points");	
                            	}
                            	gameoverText.setText("Game Over");                            	
                            	replayOrNotText.setText("Do You Want To Replay ?");
                            	unregisterUpdateHandler(gameoverTimerHandler);
                            	gameOver();
                             }        
                          pTimerHandler.reset();
                    }
                });

            registerUpdateHandler(gameoverTimerHandler);
        
        /*
        this.registerUpdateHandler(new TimerHandler(1f, true,new ITimerCallback(){
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
                    	replayOrNotText.setText("Game Over. Do You Want To Replay ?");
                    	unregisterUpdateHandler(pTimerHandler);
                    	gameOver();
                     }        
                  pTimerHandler.reset();
        	}
        }));
        */
     
        
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

        PhysicsFactory.createBoxBody(physicsWorld,ground, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,top, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,leftwall, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,rightwall, BodyType.StaticBody,GROUND_FIX);
        PhysicsFactory.createBoxBody(physicsWorld,top2, BodyType.StaticBody,GROUND_FIX);
        
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
					
					quitOrNotText.setText("Do You Really Want To Exit !"); 
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
				//btPause = new AnimatedSprite(camera.getWidth()-25-resourcesManager.mMusicTextureRegion.getWidth()-resourcesManager.mSoundsTR.getWidth()-(resourcesManager.btPauseTR.getWidth()/2),camera.getHeight()-5-(resourcesManager.btPauseTR.getHeight()/2),resourcesManager.btPauseTR,engine.getVertexBufferObjectManager()){

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
				if(resourcesManager.mMusic.isPlaying()) {
					resourcesManager.mMusic.pause();
					muteOrNot=true;
					
					
				} else {
					resourcesManager.mMusic.play();
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

		//final AnimatedSprite balls;
			
			FixtureDef GREENGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8.0f, 1f, 0f);	
			FixtureDef REDGYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8.0f, 1f, 0f);				
			FixtureDef FOOTANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(10, 1f, 0f);				
			FixtureDef BEACHANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(1, 1f, 0f);				
			FixtureDef TENNISANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(2, 1f, 0f);				
			FixtureDef BILLARDANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(5, 1f, 0f);				
			FixtureDef VOLLEYANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8, 1f, 0f);				
			FixtureDef TCHOUPIANIMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(10, 1f, 0f);	
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
			
			//-- left and right foots
			/*
			final FixtureDef BoxBodyFixtureLeftFoot =PhysicsFactory.createFixtureDef(10f, 0.5f, 0.5f);

				leftFoot = new AnimatedSprite(100, camera.getHeight()/2, resourcesManager.foot01leftTR,vbom){
								
				
				@Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
							switch(pSceneTouchEvent.getAction()) {
                                case TouchEvent.ACTION_DOWN:
                                	isTouchedFlagLeft = true;
                                	xleftPos=pTouchAreaLocalX;
                                	yleftPos=pTouchAreaLocalY;                                	
                                	
                                	try{
                     					if(!soundsOrNot){	
                     					resourcesManager.mMoveObjectSound.play();
                     					}
                     					}catch(Exception ex){
                     						ex.printStackTrace();                    				
                     					}
 
                                        break;
                                case TouchEvent.ACTION_MOVE:
                                	//isTouchedFlagLeft = true;
                                
                                 	
                                        break;
                                case TouchEvent.ACTION_UP:
                                	isTouchedFlagLeft = false;  
                                	
                                	
                                	//if(mMouseJointActiveLeftFoot != null) {
                     				//	physicsWorld.destroyJoint(mMouseJointActiveLeftFoot);
                     				//	mMouseJointActiveLeftFoot = null;
                     				//}
                     				
                                        break;
                        }
                        return false;
                }
	
				@Override
                protected void preDraw(GLState pGLState, Camera pCamera) 
                {
                   super.preDraw(pGLState, pCamera);
                   pGLState.enableDither();
                }
				
			};       
			
			
			leftFoot.setCullingEnabled(true);
			leftFootbody = PhysicsFactory.createBoxBody(physicsWorld, leftFoot, BodyType.KinematicBody, BoxBodyFixtureLeftFoot);
			
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(leftFoot, leftFootbody, true, false)
			{
			    @Override
			    public void onUpdate(float pSecondsElapsed)
			    {
			        super.onUpdate(pSecondsElapsed);

			        if (!isTouchedFlagLeft)
			        {
			        	//leftFootbody.setLinearVelocity(0,0);
			        }
			        
			        
			    }
			});
			
			leftFoot.setUserData(leftFootbody);
			//leftFoot.ate(200);
			leftFootbody.setUserData(leftFootbody);
			attachChild(leftFoot);
			registerTouchArea(leftFoot);
			*/
			
			/*
			final FixtureDef BoxBodyFixtureRightFoot =PhysicsFactory.createFixtureDef(10f, 0f, 0.5f);
			//FixtureDef BoxBodyFixtureRightFoot =PhysicsFactory.createFixtureDef(20f, 0f, 0.5f);

				rightFoot = new AnimatedSprite(700, camera.getHeight()/2, resourcesManager.foot01rightTR,vbom){
								
				
				@Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
							switch(pSceneTouchEvent.getAction()) {
                                case TouchEvent.ACTION_DOWN:
                                	isTouchedFlagRight = true;
                                	xrightPos=pTouchAreaLocalX;
                                	yrightPos=pTouchAreaLocalY;                                	
                                	
                                	try{
                     					if(!soundsOrNot){	
                     					resourcesManager.mMoveObjectSound.play();
                     					}
                     					}catch(Exception ex){
                     						ex.printStackTrace();                    				
                     					}
 
                                        break;
                                case TouchEvent.ACTION_MOVE:
                                	//isTouchedFlagRight = true;
                                 	
                                	try{
                                	physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(this).setUpdatePosition(false);
                                	}catch(Exception ex ){
                                		ex.printStackTrace();
                                	}
                                	
                                	if(mMouseJointActiveRightFoot != null) {
                    					physicsWorld.destroyJoint(mMouseJointActiveRightFoot);
                    					mMouseJointActiveRightFoot = null;
                    					}
                            
                                	
                                        break;
                        }
                        return false;
                }
			
				@Override
                protected void onManagedUpdate(float pSecondsElapsed)
                {		
                    if (isTouchedFlagRight){
                    	
                    	//BoxBodyFixtureRightFoot.density=10;
                    	// this part is replaced by this.mUpdatePosition=true in physicsworldconnector
                    	try{
                            
                            physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(this).setUpdatePosition(false);
        				
                        	}catch(Exception ex ){
                    			ex.printStackTrace();
                    		}
                    	
                    	if(mMouseJointActiveRightFoot == null) {
                             
         					try{   
         						         							
         					mMouseJointActiveRightFoot = createMouseJointRightFoot(this, xrightPos, yrightPos);
         					
         					}catch(Exception ex){
         						ex.printStackTrace();                    				
         					}
                     	  }else{
                     		 
         				}
         				
                    }else {
                    	
                    	//BoxBodyFixtureRightFoot.density=0;
                    	
                    	try{
                            
                            physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(this).setUpdatePosition(true);
        				
                        	}catch(Exception ex ){
                    			ex.printStackTrace();
                    		}
                    	
                    	if(mMouseJointActiveRightFoot != null) {
         					physicsWorld.destroyJoint(mMouseJointActiveRightFoot);
         					physicsWorld.destroyBody(mGroundBodyRightFoot);
         					mMouseJointActiveRightFoot = null;
         					}
                    }   
                    super.onManagedUpdate(pSecondsElapsed);
                }
				
				
				
				@Override
                protected void preDraw(GLState pGLState, Camera pCamera) 
                {
                   super.preDraw(pGLState, pCamera);
                   pGLState.enableDither();
                }
				
			};       
			
			
			rightFoot.setCullingEnabled(true);
			rightFootbody = PhysicsFactory.createBoxBody(physicsWorld, rightFoot, BodyType.KinematicBody, BoxBodyFixtureRightFoot);
			
			//rightFootbody.applyForce(new Vector2(0,SensorManager.GRAVITY_EARTH), new Vector2(rightFootbody.getWorldCenter()));
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(rightFoot, rightFootbody, true, false)
			{
			    @Override
			    public void onUpdate(float pSecondsElapsed)
			    {
			        super.onUpdate(pSecondsElapsed);
			               
			      
			       if (isTouchedFlagRight)
			        {			        	
			    	   //this.mUpdatePosition=true;
			        	//rightFootbody.setLinearVelocity(-10,0);
			        }else{
			        	
			        	//this.mUpdatePosition=false;
			        }
			      
			        
			        
			    }
			});
			rightFoot.setUserData(rightFootbody);
			//rightFoot.ate(200);
			rightFootbody.setUserData(rightFootbody);
			attachChild(rightFoot);
			registerTouchArea(rightFoot);
			*/
			
			
			
			
			
			
			addBall("foot",nbFootBall1,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.footballTR);
			addBall("foot2",nbFootBall2,football,footballBody,FOOTANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.football2TR);
			addBall("beach",nbBeachBall,football,footballBody,BEACHANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.beachballTR);
			addBall("billard",nbBillardBall,football,footballBody,BILLARDANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.billardballTR);
			addBall("tennis",nbTennisBall,football,footballBody,TENNISANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.tennisballTR);
			addBall("tchoupi",nbTchoupiBall,tchoupiball,tchoupiballBody,TCHOUPIANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.tchoupiballTR);
			addBall("volley",nbVolleyBall,volleyball,volleyballBody,VOLLEYANIMBALL_FIXT_DEF,BodyType.DynamicBody,resourcesManager.volleyballTR);

			
		    // net bars fixture def
		    /*
		    final FixtureDef boxFixtureDef = PhysicsFactory.
		    createFixtureDef(2f,0f, 0.9f);

            
			final FixtureDef NET_FIXTURE_DEF = PhysicsFactory.createFixtureDef(0f,0f, 0f);
			// net right 
                        
            netright = new AnimatedSprite((camera.getWidth()-5-resourcesManager.netrightTR.getWidth()/2),camera.getHeight()/2,resourcesManager.netrightTR,engine.getVertexBufferObjectManager());
    		
            netrightbody = PhysicsFactory.createBoxBody(physicsWorld,netright, BodyType.StaticBody,NET_FIXTURE_DEF);

            netright.setUserData(netrightbody);
            netright.animate(200);
            netrightbody.setUserData(netrightbody);
			this.attachChild(netright);
			this.registerTouchArea(netright);
            */
            //---------------------------------------------------------------------------------------------------
       
			
			// right net left bar
		    
		    /*
			rightNetleftBar = new Rectangle(camera.getWidth()-5-resourcesManager.netrightTR.getWidth()/2, (camera.getHeight()/2)-(resourcesManager.netrightTR.getHeight()/2), resourcesManager.netrightTR.getWidth(), 2,vbom);
			//staticRect.setUserData(staticBody);
			rightNetleftBar.setColor(0f, 0f, 0.7f);
            //staticRect.setAlpha(0.5f);
            attachChild(rightNetleftBar);
            staticBody = PhysicsFactory.createBoxBody(physicsWorld,rightNetleftBar,
                    BodyType.StaticBody, boxFixtureDef);
            //staticBody.setUserData(staticBody);
            physicsWorld.registerPhysicsConnector(new PhysicsConnector(
            		rightNetleftBar, staticBody));
            
		    // left net right bar
		    
		    
			rightNetrightBar = new Rectangle(camera.getWidth()-5-resourcesManager.netrightTR.getWidth()/2, (camera.getHeight()/2)+(resourcesManager.netrightTR.getHeight()/2), resourcesManager.netrightTR.getWidth(), 2,vbom);
			//staticRect.setUserData(staticBody);
			rightNetrightBar.setColor(0f, 0f, 0.7f);
            //staticRect.setAlpha(0.5f);
            attachChild(rightNetrightBar);
            staticBody = PhysicsFactory.createBoxBody(physicsWorld,rightNetrightBar,
                    BodyType.StaticBody, boxFixtureDef);
            //staticBody.setUserData(staticBody);
            physicsWorld.registerPhysicsConnector(new PhysicsConnector(
            		rightNetrightBar, staticBody));
			
            
            
         // net left 
           // final FixtureDef NET_FIXTURE_DEF = PhysicsFactory.createFixtureDef(0f,0f, 0f);
                        
            netleft = new AnimatedSprite((5+resourcesManager.netleftTR.getWidth()/2),camera.getHeight()/2,resourcesManager.netleftTR,engine.getVertexBufferObjectManager());
    		
            netleftbody = PhysicsFactory.createCircleBody(physicsWorld,netleft, BodyType.StaticBody,NET_FIXTURE_DEF);

            netleft.setUserData(netleftbody);
            netleft.animate(200);
            netleftbody.setUserData(netleftbody);
			this.attachChild(netleft);
			this.registerTouchArea(netleft);
            
            //---------------------------------------------------------------------------------------------------
       
			
			// right net left bar
		    
		    
			leftNetleftBar = new Rectangle(5+resourcesManager.netleftTR.getWidth()/2, (camera.getHeight()/2)+(resourcesManager.netleftTR.getHeight()/2), resourcesManager.netleftTR.getWidth(), 2,vbom);
			//staticRect.setUserData(staticBody);
			leftNetleftBar.setColor(0f, 0f, 0.7f);
            //staticRect.setAlpha(0.5f);
            attachChild(leftNetleftBar);
            staticBody = PhysicsFactory.createBoxBody(physicsWorld,leftNetleftBar,
                    BodyType.StaticBody, boxFixtureDef);
            //staticBody.setUserData(staticBody);
            physicsWorld.registerPhysicsConnector(new PhysicsConnector(
            		leftNetleftBar, staticBody));
            
		    // left net right bar
		    
		    
			leftNetrightBar = new Rectangle(5+resourcesManager.netleftTR.getWidth()/2, (camera.getHeight()/2)-(resourcesManager.netleftTR.getHeight()/2), resourcesManager.netleftTR.getWidth(), 2,vbom);
			//staticRect.setUserData(staticBody);
			leftNetrightBar.setColor(0f, 0f, 0.7f);
            //staticRect.setAlpha(0.5f);
            attachChild(leftNetrightBar);
            staticBody = PhysicsFactory.createBoxBody(physicsWorld,leftNetrightBar,
                    BodyType.StaticBody, boxFixtureDef);
            //staticBody.setUserData(staticBody);
            physicsWorld.registerPhysicsConnector(new PhysicsConnector(
            		leftNetrightBar, staticBody));
			
            
            */
            //--------------------------------------------------------------------------------------------------
            
			// stars
  
                        
            starsleft = new AnimatedSprite((8+resourcesManager.netleftTR.getWidth()/2),camera.getHeight()/2,resourcesManager.starsTR,engine.getVertexBufferObjectManager());
            starsleft.animate(200);
            starsleft.setVisible(showStarsleft);
			this.attachChild(starsleft);
			
			starsright = new AnimatedSprite((camera.getWidth()-8-resourcesManager.netrightTR.getWidth()/2),camera.getHeight()/2,resourcesManager.starsTR,engine.getVertexBufferObjectManager());
			starsright.animate(200);
			starsright.setVisible(showStarsright);
			this.attachChild(starsright);
				

	            
            
            ///////////////////////////
            
            physicsWorld.setContactListener(new ContactListener(){
            	@Override
				public void beginContact(Contact contact) {

            		final Fixture x1 = contact.getFixtureA();
					final Fixture x2 = contact.getFixtureB();
					Body bodyA = x1.getBody();
					Body bodyB = x2.getBody();
   /*
					if(isBodyContacted(netleftbody,contact)){
				         
							showStarsleft=true;
							starsleft.setVisible(showStarsleft);
							setFullAlphaForStaticBody = true;
		            }else{
		                	showStarsleft=false;
		                	starsleft.setVisible(showStarsleft);
		            		setFullAlphaForDynamicBody = true;	
		              }
					if(isBodyContacted(netrightbody,contact)){
				         
						showStarsright=true;
						starsright.setVisible(showStarsright);
						setFullAlphaForStaticBody = true;
					}else{
	                	showStarsright=false;
	                	starsright.setVisible(showStarsright);
	            		setFullAlphaForDynamicBody = true;	
	              }
				  
					if(isBodyContacted(leftFootbody,contact)){
					  //leftFootbody.setLinearVelocity(contact.getWorldManifold().getNormal());
						if (contact.getFixtureA().getBody()==leftFootbody){
							showStarsleft=true;
							starsleft.setVisible(showStarsleft);
							contact.getFixtureB().getBody().applyLinearImpulse(contact.getWorldManifold().getNormal().mul(100), contact.getFixtureB().getBody().getWorldCenter());
						}else{
							showStarsleft=true;
							starsleft.setVisible(showStarsleft);
							contact.getFixtureA().getBody().applyLinearImpulse(contact.getWorldManifold().getNormal().mul(100), contact.getFixtureA().getBody().getWorldCenter());
						}
					}else{
						showStarsleft=false;
						starsleft.setVisible(showStarsleft);
					}
				  
					if(isBodyContacted(rightFootbody,contact)){
						  //leftFootbody.setLinearVelocity(contact.getWorldManifold().getNormal());
							if (contact.getFixtureA().getBody()==rightFootbody){
								showStarsright=true;
								starsright.setVisible(showStarsright);
								contact.getFixtureB().getBody().applyLinearImpulse(contact.getWorldManifold().getNormal().mul(-100), contact.getFixtureB().getBody().getWorldCenter());
							}else{
								showStarsright=true;
								starsright.setVisible(showStarsright);
								contact.getFixtureA().getBody().applyLinearImpulse(contact.getWorldManifold().getNormal().mul(-100), contact.getFixtureA().getBody().getWorldCenter());
							}
						}else{
							showStarsright=false;
							starsright.setVisible(showStarsright);
						}
            		*/
            		/*
            		if(contact.isTouching()){
		              if(areBodiesContacted(staticBody,xAnimBallbody,contact)){
				
						setFullAlphaForStaticBody = true;
            			bodyArray.add(bodyB);	
            			
	            		if(bodyA.equals(staticBody))
	            		{
	            			
	            		bodyArray.add(bodyB);	
            			setFullAlphaForStaticBody = true;		
	            			
	            		}

		                }
		             if(isBodyContacted(staticBody,contact)){
		         
		                setFullAlphaForDynamicBody = true;
		                }
		            }*/
            		
				}
				@Override
				public void endContact(Contact contact) {
					//leftFootbody.setLinearVelocity(0,0);
					// TODO Auto-generated method stub
					/*
					if(areBodiesContacted(staticBody,xAnimballbody,contact))
		                setHalfAlphaForStaticBody = true;
		            if(isBodyContacted(xAnimBallbody,contact))
		                setHalfAlphaForDynamicBody = true;
		                */
					//showStars=false;
					//stars.setVisible(showStars);
					
				}
				@Override
				public void preSolve(Contact contact, Manifold oldManifold) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void postSolve(Contact contact, ContactImpulse impulse) {
					// TODO Auto-generated method stub
					
				}
                 
                });
            

            
           //this.setTouchAreaBindingOnActionDownEnabled(true);
           //this.setTouchAreaBindingOnActionMoveEnabled(true);
           //this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
           //this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
            
            
            
	}
	public boolean isBodyContacted(Body pBody, Contact pContact)
    {
    if(pContact.getFixtureA().getBody().equals(pBody) ||
    pContact.getFixtureB().getBody().equals(pBody))
    return true;
    return false;
    }
     
     
    public boolean areBodiesContacted(Body pBody1, Body pBody2,Contact pContact)
            {
            if(pContact.getFixtureA().getBody().equals(pBody1) ||
            pContact.getFixtureB().getBody().equals(pBody1))
            if(pContact.getFixtureA().getBody().equals(pBody2) ||
            pContact.getFixtureB().getBody().equals(pBody2))
            return true;
            return false;
            }
    

	public boolean addBall(String typeBall,int nbBall,AnimatedSprite xAnimBall,Body xAnimBallBody,FixtureDef fixtureDef,BodyType bodyType,TiledTextureRegion tiledTextureRegion ){
		
		for (int i=0;i<nbBall;i++){
			
			float minX = 100.0f;
			float maxX = 750.0f;
			float minY = 70.0f;
			float maxY = 430.0f;
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
                                	removeFace(this);
                                	//resourcesManager.mBallinholeSound.play();
                                	updScoreA(1);
                                        break;
                                case TouchEvent.ACTION_MOVE:
                                	/*
                                	if(mMouseJointActive == null) {
                    					//this.mEngine.vibrate(100);
                    					//face.setScale(1.5f);
                    					try{
                    					if(!soundsOrNot){	
                    					resourcesManager.mMoveObjectSound.play();
                    					}
                    					mMouseJointActive = createMouseJoint(this, pTouchAreaLocalX, pTouchAreaLocalY);
                    					}catch(Exception ex){
                    						ex.printStackTrace();
                    					}
                    				}else{
                    				
                    				}
                    				*/
                                        break;
                                case TouchEvent.ACTION_UP:
                                	
                                        break;
                        }
                        return true;
                }
				
				
				
				
				/*
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) 
				{
					super.onManagedUpdate(pSecondsElapsed);

					if (netright.collidesWith(this))
					{
						int scoreAValue=0;
						
						try {
							
							if(mMouseJointActive != null && mouseJointDef.bodyB==(Body) this.getUserData()) {
								scoreAValue=20;
								physicsWorld.destroyJoint(mMouseJointActive);
								mMouseJointActive = null;
								
							}else {
								scoreAValue=10;
							}
	
							//physicsWorld.getPhysicsConnectorManager().findBodyByShape(this).setType(BodyType.StaticBody);
							//physicsWorld.destroyBody((Body) this.getUserData());
							
							this.setPosition((camera.getWidth()-8-resourcesManager.netleftTR.getWidth()/2),camera.getHeight()/2);
							
							//this.ate(10);
							//this.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new ScaleModifier(.5f, 1, 1.3f), new ScaleModifier(.5f, 1.3f, .5f))));
							
							showStarsright=true;
							starsright.setVisible(showStarsright);
							//this.ate(new long[] { 100, 100 }, 1, 2, true);
							//this.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2, 0, 360)));
							//Thread.sleep(1000);
							
							removeFace(this);
							
							try{
							if(!soundsOrNot){	
							resourcesManager.mBallinholeSound.play();
							}
							} catch (Exception ex){
								ex.printStackTrace();
							}
							
							updScoreA(scoreAValue);
							
						}catch(Exception ex){
		                	ex.printStackTrace();
		                }
						
					}
					
					if (netleft.collidesWith(this)){
						int scoreBValue=0;
						try {
							
							if(mMouseJointActive != null && mouseJointDef.bodyB==(Body) this.getUserData()) {
								scoreBValue=20;
								physicsWorld.destroyJoint(mMouseJointActive);
								mMouseJointActive = null;
								
							}else {
								scoreBValue=10;
							}
	
							//physicsWorld.getPhysicsConnectorManager().findBodyByShape(this).setType(BodyType.StaticBody);
							//physicsWorld.destroyBody((Body) this.getUserData());
							
							this.setPosition((8+resourcesManager.netrightTR.getWidth()/2),camera.getHeight()/2);
							
							//this.ate(10);
							//this.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new ScaleModifier(.5f, 1, 1.3f), new ScaleModifier(.5f, 1.3f, .5f))));
							
							showStarsleft=true;
							starsleft.setVisible(showStarsleft);
							//this.ate(new long[] { 100, 100 }, 1, 2, true);
							//this.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2, 0, 360)));
							//Thread.sleep(1000);
							
							removeFace(this);
							
							try{
							if(!soundsOrNot){	
							resourcesManager.mBallinholeSound.play();
							}
							} catch (Exception ex){
								ex.printStackTrace();
							}
							
							updScoreB(scoreBValue);
							
						}catch(Exception ex){
		                	ex.printStackTrace();
		                }
					}
				}*/
			};
			
			xAnimBallBody = PhysicsFactory.createCircleBody(physicsWorld, xAnimBall, bodyType, XANIMBALL_FIXT_DEF);
			xAnimBall.setUserData(xAnimBallBody);
			//xAnimBall.setScale(.6f);
			xAnimBall.animate(200);
			xAnimBallBody.setUserData(xAnimBallBody);
			this.attachChild(xAnimBall);
			this.registerTouchArea(xAnimBall);
	

			this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(xAnimBall, xAnimBallBody, true, true));
		}
		
		return true;
	}
	
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
		//scoreBText.setText("");
		usernameAText.setText("");
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
		resourcesManager.unloadBallGame24Resources();
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
		
		if (localLanguage==0){
			quitOrNotText.setText("Vous voulez vraiment quitter !"); 
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


	/*
	public MouseJoint createMouseJoint(final IShape pFace, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		final Body body = (Body) pFace.getUserData();
		//final MouseJointDef mouseJointDef = new MouseJointDef();
		mouseJointDef = new MouseJointDef();
		mGroundBody = this.physicsWorld.createBody(new BodyDef());
		
		final Vector2 localPoint = Vector2Pool.obtain((pTouchAreaLocalX) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, (pTouchAreaLocalY) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		this.mGroundBody.setTransform(localPoint, 0);
		
		//pFace.setScale(3f);
		
		mouseJointDef.bodyA = this.mGroundBody;
		mouseJointDef.bodyB = body;
		mouseJointDef.dampingRatio = 0.95f;
		mouseJointDef.frequencyHz = 30;
		mouseJointDef.maxForce = (200.0f * body.getMass());
		mouseJointDef.collideConnected = true;

		mouseJointDef.target.set(body.getWorldPoint(localPoint));
		Vector2Pool.recycle(localPoint);
		
		

		return (MouseJoint) this.physicsWorld.createJoint(mouseJointDef);
	}
	
	public MouseJoint createMouseJointLeftFoot(final IShape pFace, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		final Body body = (Body) pFace.getUserData();
		//final MouseJointDef mouseJointDef = new MouseJointDef();
		mouseJointDefLeftFoot = new MouseJointDef();		
        mGroundBodyLeftFoot = this.physicsWorld.createBody(new BodyDef());
		
		final Vector2 localPoint = Vector2Pool.obtain((pTouchAreaLocalX) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, (pTouchAreaLocalY) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		this.mGroundBodyLeftFoot.setTransform(localPoint, 0);
		
		//pFace.setScale(3f);
		
		mouseJointDefLeftFoot.bodyA = mGroundBodyLeftFoot;
		mouseJointDefLeftFoot.bodyB = body;
		mouseJointDefLeftFoot.dampingRatio = 0.2f;
		mouseJointDefLeftFoot.frequencyHz = 30;
		mouseJointDefLeftFoot.maxForce = (200.0f * body.getMass());
		mouseJointDefLeftFoot.collideConnected = true;

		mouseJointDefLeftFoot.target.set(body.getWorldPoint(localPoint));
		Vector2Pool.recycle(localPoint);

		return (MouseJoint) this.physicsWorld.createJoint(mouseJointDefLeftFoot);
	}
	
	public MouseJoint createMouseJointRightFoot(final IShape pFace, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		final Body body = (Body) pFace.getUserData();
		//final MouseJointDef mouseJointDef = new MouseJointDef();
		mouseJointDefRightFoot = new MouseJointDef();		
        mGroundBodyRightFoot = this.physicsWorld.createBody(new BodyDef());
        
		final Vector2 localPoint = Vector2Pool.obtain((pTouchAreaLocalX) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, (pTouchAreaLocalY) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		this.mGroundBodyRightFoot.setTransform(localPoint, 0);
		
		//pFace.setScale(3f);
		
		mouseJointDefRightFoot.bodyA = mGroundBodyRightFoot;
		mouseJointDefRightFoot.bodyB = body;
		mouseJointDefRightFoot.dampingRatio = 0f;
		mouseJointDefRightFoot.frequencyHz = 30;
		mouseJointDefRightFoot.maxForce = (200.0f * body.getMass());
		mouseJointDefRightFoot.collideConnected = true;

		mouseJointDefRightFoot.target.set(body.getWorldPoint(localPoint));
		Vector2Pool.recycle(localPoint);

		return (MouseJoint) this.physicsWorld.createJoint(mouseJointDefRightFoot);
	}
	*/
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
		final IShape face = (IShape) pTouchArea;

		AnimatedSprite capturedSprite = (AnimatedSprite) pTouchArea;
		
		if (pSceneTouchEvent.isActionDown()){
		
		if(capturedSprite!=null && gamePageCounter==50 ){
	    	
		    if((capturedSprite.getTiledTextureRegion() == resourcesManager.gymballredTR) || (capturedSprite.getTiledTextureRegion() == resourcesManager.gymballgreenTR) ){
				
				try{
					//capturedSprite.setScale(3.0f);
					
					
				removeFace(capturedSprite);
				if(!soundsOrNot){
				resourcesManager.mExplosionSound.play();
				}
				updScoreA(0);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		    
		    /*
		    else if ((capturedSprite.getTiledTextureRegion() == resourcesManager.foot01leftTR)){
				if(mMouseJointActive == null) {

					try{
					if(!soundsOrNot){	
					resourcesManager.mExplosionSound.play();
					}
					mMouseJointActive = this.createMouseJoint(face, pTouchAreaLocalX, pTouchAreaLocalY);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}	
		    	
		    }
		    
		     */
		    else{
			
			}
		    }
		}else if (pSceneTouchEvent.isActionUp()){
			/*
			if(capturedSprite==null && gamePageCounter==50 ){
		    	try{
		    	addFace(pTouchAreaLocalX, pTouchAreaLocalY);
		    	
		    	}catch(Exception ex){
				ex.printStackTrace();
		    	}
		    }
			
			else{
			}
		}*/
		    
		    if (gamePageCounter==100){
		    	gamePageCounter=50;
		    } 
		
		
		}
		return false;

	}
	/*
	private void addFace(final float pX, final float pY) {
		this.mFaceCount++;
		
		try{
			
		final AnimatedSprite gymball;
		final Body gymballbody;
		FixtureDef GYMBALL_FIXT_DEF = PhysicsFactory.createFixtureDef(8.0f, 1.0f, 0.5f);

		if(this.mFaceCount % 2 == 0) {
			gymball = new AnimatedSprite(pX, pY, resourcesManager.gymballredTextureRegion, vbom);

		} else {
			gymball = new AnimatedSprite(pX, pY, resourcesManager.gymballgreenTextureRegion, vbom);
		}
		gymballbody = PhysicsFactory.createCircleBody(physicsWorld, gymball, BodyType.DynamicBody, GYMBALL_FIXT_DEF);
		//gymball.setUserData(gymballbody);
		gymball.ate(200, true);

		this.registerTouchArea(gymball);
		this.attachChild(gymball);
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(gymball, gymballbody, true, true));
		//score+=10;
		//scoreText.setText("Score: "+score);
		
		nbObjectsInScene+=1;
		updScoreA(0);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//System.gc();
		
	}
	*/
	private void removeFace(final AnimatedSprite face) {
		
		try{
		final PhysicsConnector facePhysicsConnector = this.physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(face);
	
		physicsWorld.unregisterPhysicsConnector(facePhysicsConnector);
		physicsWorld.destroyBody(facePhysicsConnector.getBody());
		//face.detachSelf();
		
		engine.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
			
				unregisterTouchArea(face);
				detachChild(face);
				face.clearUpdateHandlers();
				
			}
			});
		
		//nbObjectsInScene-=1;
		face.dispose();
		if(!soundsOrNot){
		resourcesManager.mBallinholeSound.play();
		}
		nbObjectsInScene-=1;
		
		playParticles(face.getX(),face.getY(),3);	
	
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.gc();
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		try{
		if(this.physicsWorld != null) {
			switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					/*
					addornotFace++;
					if(this.addornotFace % moduloAddFace == 0){
						if(!soundsOrNot){
						resourcesManager.mCreateGymBallSound.play();
						}
					this.addFace(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
					}
					*/
					return true;
				case TouchEvent.ACTION_MOVE:
					/*
					//-- moving balls
					if(this.mMouseJointActive != null) {
						final Vector2 vec = Vector2Pool.obtain(pSceneTouchEvent.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, pSceneTouchEvent.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
						this.mMouseJointActive.setTarget(vec);
						Vector2Pool.recycle(vec);
					}
					
					
					if(this.mMouseJointActiveRightFoot != null) {
						final Vector2 vec = Vector2Pool.obtain(pSceneTouchEvent.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, pSceneTouchEvent.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
						if (pSceneTouchEvent.getX()>430 && pSceneTouchEvent.getX()<760 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<420)
						{
						this.mMouseJointActiveRightFoot.setTarget(vec);
						Vector2Pool.recycle(vec);
						}
					}
					
					if(this.mMouseJointActiveLeftFoot != null ) {
						final Vector2 vec = Vector2Pool.obtain(pSceneTouchEvent.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, pSceneTouchEvent.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
						if(pSceneTouchEvent.getX()>35 && pSceneTouchEvent.getX()<370 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<418){
						this.mMouseJointActiveLeftFoot.setTarget(vec);						
						Vector2Pool.recycle(vec);
						}
					}
					*/ 
					
					//-----------------------------------------------------------------------------------
					/*
					if(isTouchedFlagRight){
					if(pSceneTouchEvent.getX()>430 && pSceneTouchEvent.getX()<760 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<420){
						rightFoot.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
						}else {
							//isTouchedFlagRight=false;
						}
					}
					
					
					if(isTouchedFlagRight){
    					if(pSceneTouchEvent.getX()>430 && pSceneTouchEvent.getX()<760 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<420){
    						rightFootbody.setTransform(pSceneTouchEvent.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, pSceneTouchEvent.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,rightFootbody.getAngle());
    						//rightFootbody.setLinearVelocity(-2,0);	
    					}else {
    							//isTouchedFlagRight=false;
    						//rightFootbody.setLinearVelocity(0,0);
    						}
    					}else{
    						//rightFootbody.setLinearVelocity(0,0);
    					}
					*/
					/*
					if(isTouchedFlagLeft){	
						if(pSceneTouchEvent.getX()>35 && pSceneTouchEvent.getX()<370 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<418){
						leftFoot.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
						}else {
							//isTouchedFlagLeft=false;
						}
					}
					
					if(isTouchedFlagLeft){	
						if(pSceneTouchEvent.getX()>35 && pSceneTouchEvent.getX()<370 && pSceneTouchEvent.getY()>25 && pSceneTouchEvent.getY()<418){
						leftFootbody.setTransform(pSceneTouchEvent.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, pSceneTouchEvent.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,leftFootbody.getAngle());
						}else {
							//isTouchedFlagLeft=false;
						}
					}*/
					//-----------------------------------------------------------------------------------

					return true;
				case TouchEvent.ACTION_UP:
					/*
					if(!isTouchedFlagLeft){
					if(mMouseJointActiveLeftFoot != null) {
     					physicsWorld.destroyJoint(mMouseJointActiveLeftFoot);
     					mMouseJointActiveLeftFoot = null;
     					}
					}
					
					if(!isTouchedFlagRight){
					if(mMouseJointActiveRightFoot != null) {
     					physicsWorld.destroyJoint(mMouseJointActiveRightFoot);
     					mMouseJointActiveRightFoot = null;
     					}
					}
					*/
					return true;
			}
			return false;
		}else{
			//return false;
		}
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
		final SpriteParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter, 60, 60, 50, resourcesManager.mParticle24TR, vbom);
	
		particleSystem.addParticleInitializer(new ColorParticleInitializer<Sprite>(12, 43, 7));
		particleSystem.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0));
		particleSystem.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-2, 2, -20, -10));
		particleSystem.addParticleInitializer(new RotationParticleInitializer<Sprite>(0.0f, 360.0f));
		particleSystem.addParticleInitializer(new ExpireParticleInitializer<Sprite>(3));

		particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0, 1, 0.5f, 2.0f));
		particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(2, 3, 2f, 0f));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(0, 1, 30, 196, 109, 201, 17, 54));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(2, 3, 196, 252, 201, 122, 135, 132));
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
	
	private void updScoreA(int i) {
		
			scoreA+=i;
			if (scoreA<0){
				scoreA=0;
			}
			
			scoreAText.setText(""+scoreA);
			if(nbObjectsInScene==0){
				levelCompleteText.setText("Level Complete !");
				unregisterUpdateHandler(gameoverTimerHandler);
				onWinScene();
			}
	};
	/*
	private void updScoreB(int i) {
		
		scoreB+=i;
		if (scoreB<0){
			scoreB=0;
		}
		
		scoreBText.setText(""+scoreB);
		if(nbObjectsInScene==0){
			levelCompleteText.setText("Level Complete !");
			onWinScene();
		}
};
*/
	private void onWinScene() {
		// TODO Auto-generated method stub
		gameState = STATE_NEXTLEVEL;
		this.setChildScene(winScene, false, true, true);
		try{	
			
			resourcesManager.mGangstaMusic.pause();
			if(!soundsOrNot){
			resourcesManager.mBravoSound.play();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		//engine.stop();
	}
	private void gameOver() {
		// TODO Auto-generated method stub
		gameState = STATE_NEXTLEVEL;
		this.setChildScene(gameOverScene, false, true, true);
		try{	
			
			resourcesManager.mGangstaMusic.pause();
			if(!soundsOrNot){
			//resourcesManager.mBravoSound.play();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		//engine.stop();
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
			resourcesManager.mMusic.pause();
		}else{
			resourcesManager.mMusic.play();
		}
		*/
	}
	public void pauseScene() {
		// TODO Auto-generated method stub
		
		this.setChildScene(pauseScene, false, true, true);
		try{				
			resourcesManager.mGangstaMusic.pause();
			gameState = STATE_PAUSE;
		} catch (Exception ex){
				ex.printStackTrace();
		}
	
	}

	public void resumeScene() {
	// TODO Auto-generated method stub
			
		this.clearChildScene();
		gameState = STATE_RUNNING;
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
		//scoreBText.setText("");
		usernameAText.setText("");
		//usernameBText.setText("");
		timeText.setText("");
		detachChild(gameHUD);
		levelCompleteText.setText("");
		detachChild(levelcompleteHUD);
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		gameDuration=30;
		levelID+=1;
		
		//clearPhysicsWorld(physicsWorld);
	
		//clearScene();
		this.clearChildScene();
		this.detachChildren();
		this.reset();
		this.detachSelf();
		//System.gc();
	    
		int inbFootBall1=1;int inbFootball2=1;int inbtennisball=1; 
		int inbvolleyball=1;int inbbillardball=1;int inbbeachball=1;int inbtchoupiball=1;
		int totalballs=7;
		
		if(levelID<10){
		switch (levelID){
		case 1:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=1;inbFootball2=1;inbvolleyball=1;
			inbtennisball=1;inbbillardball=1;
			totalballs=7;
			break;
		case 2:
			inbtchoupiball=2;inbbeachball=2;inbFootBall1=2;inbFootball2=2;inbvolleyball=2;
			inbtennisball=2;inbbillardball=1;
			totalballs=13;
			break;
		case 3:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=2;inbFootball2=2;inbvolleyball=1;
			inbtennisball=3;inbbillardball=3;
			totalballs=13;
			break;
		case 4:
			inbtchoupiball=2;inbbeachball=2;inbFootBall1=2;inbFootball2=2;inbvolleyball=2;
			inbtennisball=3;inbbillardball=3;
			totalballs=16;
			break;
		case 5:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=1;inbFootball2=1;inbvolleyball=1;
			inbtennisball=4;inbbillardball=4;
			totalballs=13;
			break;
		case 6:
			inbtchoupiball=2;inbbeachball=1;inbFootBall1=1;inbFootball2=1;inbvolleyball=2;
			inbtennisball=5;inbbillardball=3;
			totalballs=15;
			break;
		case 7:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=2;inbFootball2=1;inbvolleyball=1;
			inbtennisball=5;inbbillardball=5;
			totalballs=16;
			break;
		case 8:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=1;inbFootball2=2;inbvolleyball=1;
			inbtennisball=6;inbbillardball=5;
			totalballs=17;
			break;
		case 9:
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=1;inbFootball2=1;inbvolleyball=1;
			inbtennisball=7;inbbillardball=5;
			totalballs=17;
			break;
		}

		}else{
			inbtchoupiball=1;inbbeachball=1;inbFootBall1=1;inbFootball2=1;inbvolleyball=1;
			inbtennisball=8;inbbillardball=7;
			totalballs=20;	
		}
		initVariables(levelID,false,false,totalballs,scoreA,0,0,muteOrNot,soundsOrNot,100,false,false,false,false);
		initLevelVariables(3,inbFootBall1, inbFootball2,inbtennisball, 
				inbvolleyball,inbbillardball, inbbeachball, inbtchoupiball);
		
		//initVariables(levelID,false,false,levelID*7,scoreA,0,0,muteOrNot,soundsOrNot,100,false,false,false,false);
		//initLevelVariables(3,levelID, levelID,levelID, levelID,levelID, levelID, levelID);
		
		/*int ilevel,boolean ishowstarsleft,boolean ishowstarsright, int inbobjectsinscene, 
		 int iscoreA, int imfacecount, int iaddornotface,boolean imuteornot,
		 boolean isoundsOrNot, int igamepagecounter, boolean isetfullalphafordynamicbody, 
		 boolean isethalfalphafordynamicbody,boolean isetfullalphaforstaticbody, 
		 boolean isethalfalphaforstaticbody*/
		
		/*int imoduloaddface,int inbFootBall1, int inbFootball2,int inbtennisball, 
		 int inbvolleyball,	int inbbillardball, int inbbeachball, int inbtchoupiball
		*/
		
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
	
	private void initVariables(int ilevel,boolean ishowstarsleft,boolean ishowstarsright, int inbobjectsinscene, int iscoreA, int imfacecount, int iaddornotface,
			boolean imuteornot,boolean isoundsOrNot, int igamepagecounter, boolean isetfullalphafordynamicbody, boolean isethalfalphafordynamicbody,
			boolean isetfullalphaforstaticbody, boolean isethalfalphaforstaticbody) {
		
		levelID=ilevel;
		showStarsleft=ishowstarsleft;
		showStarsright=ishowstarsright;
		nbObjectsInScene = inbobjectsinscene;
		scoreA=iscoreA;
		//scoreB=iscoreB;
		mFaceCount=imfacecount;
    	addornotFace=iaddornotface;
       	muteOrNot=imuteornot;  
       	soundsOrNot=isoundsOrNot;  
    	gamePageCounter = igamepagecounter;
		
    	setFullAlphaForDynamicBody = isetfullalphafordynamicbody;
        setHalfAlphaForDynamicBody = isethalfalphafordynamicbody;
        setFullAlphaForStaticBody = isetfullalphaforstaticbody;
        setHalfAlphaForStaticBody = isethalfalphaforstaticbody;
        
        
	}	
	private void initLevelVariables(int imoduloaddface,int inbFootBall1, int inbFootball2, 
			int inbtennisball, int inbvolleyball,
			int inbbillardball, int inbbeachball, int inbtchoupiball) {
		
		moduloAddFace=imoduloaddface;
		nbFootBall1=inbFootBall1;
		nbFootBall2=inbFootball2;
		nbTennisBall=inbtennisball;
		nbVolleyBall=inbvolleyball;
		nbBillardBall=inbbillardball;
		nbBeachBall=inbbeachball;
		nbTchoupiBall=inbtchoupiball;
	
		
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
