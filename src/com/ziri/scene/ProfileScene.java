package com.ziri.scene;

import java.util.Locale;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
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

import android.content.Intent;
import android.net.Uri;

import com.ziri.GameActivity;
import com.ziri.base.BaseScene;
import com.ziri.manager.ResourcesManager;
import com.ziri.manager.SceneManager;
import com.ziri.manager.SceneManager.SceneType;

public class ProfileScene extends BaseScene implements IOnMenuItemClickListener {

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
    static final String[] textsFr = {"Choisissez Un Profile SVP !","0-2 ans","2-4 ans", "+4 ans","Langage par defaut"};
    //static final String[] textsFrRead = {"Choisissez Un Profile SVP !","0-2 ans","2-4 ans", "+4 ans"};
    static final String[] textsEn = {"Choose Profile Please !","0-2 years","2-4 years", "+4 years","Default Language"};
    
    //-- language button
    TiledSprite changLang;
    Text bitmapTextChooseLanguage;
    
    Text bitmapTextChooseProfile,bitmapTextProfile02,bitmapTextProfile24,bitmapTextProfile4;
	
	// -- exit scene variables
	private CameraScene exitScene;
	private Text quitOrNotText;
	private HUD quitgameHUD;
	// ---------------------------------------------

	private MenuScene menuChildScene;

	private final int MENU_PROFILE02 = 0;
	private final int MENU_PROFILE24 = 1;
	private final int MENU_PROFILE4 = 2;
	
	//-- text variables
	
	private final int TXT_TITLE = 0;
	private final int TXT_PROFILE02 = 1;
	private final int TXT_PROFILE24 = 2;
	private final int TXT_PROFILE4 = 3;
	private final int TXT_LANGUAGE = 4;

	// ---------------------------------------------
	// METHODS FROM SUPERCLASS
	// ---------------------------------------------

	@Override
	public void createScene() {
		showAds();
		resourcesManager.setIdMenu(0);
		//initLanguage(); 
		//initSounds();
		localLanguage = resourcesManager.initLanguage();
		soundsOrNot = resourcesManager.initSounds();
		
		final float centerX = camera.getWidth() / 2;
		final float centerY = camera.getHeight() / 2;
		// --quit or not scene

		exitScene = new CameraScene(camera);
		// Make the 'WIN'-label centered on the camera.
		final Sprite exitSprite = new Sprite(centerX, centerY,
				resourcesManager.emptyWindowTR, vbom);
		exitScene.attachChild(exitSprite);
		// Makes the paused Game look through.
		exitScene.setBackgroundEnabled(false);

		final TiledSprite btexit;
		btexit = new TiledSprite(500, 150, resourcesManager.btYesTR, vbom) {

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
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
		exitScene.attachChild(returnGame);
		exitScene.registerTouchArea(returnGame);

		quitgameHUD = new HUD();
		quitOrNotText = new Text(400, 300, resourcesManager.mBitmapFont,
				"abcdefghijklmnopqrstuvwxyzabcdefgh", vbom);
		quitOrNotText.setSkewCenter(0, 0);
		quitOrNotText.setText("");
		// quitOrNotText.setText("Do You Really Want To Exit !");
		quitgameHUD.attachChild(quitOrNotText);
		camera.setHUD(quitgameHUD);
		exitScene.attachChild(quitgameHUD);
		// -----------------------------------------------------------------------------------------

		this.setBackground(new Background(1, 20, 20));

		bitmapTextChooseProfile = new Text(400, 420,
				resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh"/*getText(TXT_TITLE)*/, vbom);
		this.attachChild(bitmapTextChooseProfile);
		bitmapTextChooseProfile.setColor(Color.CYAN);

		bitmapTextProfile02 = new Text(150, 200,
				resourcesManager.mBitmapFont,  "abcdefghijklmnopqrstuvwxyzabcdefgh"/*getText(TXT_PROFILE02)*/, vbom);
		this.attachChild(bitmapTextProfile02);
		bitmapTextProfile02.setColor(Color.YELLOW);

		bitmapTextProfile24 = new Text(400, 200,
				resourcesManager.mBitmapFont,  "abcdefghijklmnopqrstuvwxyzabcdefgh"/*getText(TXT_PROFILE24)*/, vbom);
		this.attachChild(bitmapTextProfile24);
		bitmapTextProfile24.setColor(Color.RED);

		bitmapTextProfile4 = new Text(650, 200,
				resourcesManager.mBitmapFont,  "abcdefghijklmnopqrstuvwxyzabcdefgh"/*getText(TXT_PROFILE4)*/, vbom);
		this.attachChild(bitmapTextProfile4);
		bitmapTextProfile4.setColor(Color.GREEN);
		
		bitmapTextChooseLanguage = new Text(400, 120,
				resourcesManager.mBitmapFont, "abcdefghijklmnopqrstuvwxyzabcdefgh"/*getText(TXT_LANGUAGE)*/, vbom);
		this.attachChild(bitmapTextChooseLanguage);
		bitmapTextChooseProfile.setColor(Color.BLUE);
		
		updateTexts();
		playMusic();
		
		createMenuChildScene();
	}

	private void createMenuChildScene() {
		// TODO Auto-generated method stub
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0, 0);

		final IMenuItem profile02MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(MENU_PROFILE02,
						resourcesManager.btProfile02TR, vbom), 1f, .8f);
		final IMenuItem profile24MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(MENU_PROFILE24,
						resourcesManager.btProfile24TR, vbom), 1f, .8f);
		final IMenuItem profile4MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(MENU_PROFILE4,
						resourcesManager.btProfile4TR, vbom), 1f, .8f);

		menuChildScene.addMenuItem(profile02MenuItem);
		menuChildScene.addMenuItem(profile24MenuItem);
		menuChildScene.addMenuItem(profile4MenuItem);

		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);

		profile02MenuItem.setPosition(150, 280);
		profile24MenuItem.setPosition(400, 280);
		profile4MenuItem.setPosition(650, 280);

		menuChildScene.setOnMenuItemClickListener(this);
		
		//-- change language button -----------------------------------------------------------------------------
		
		changLang = new TiledSprite(400,60, resourcesManager.btLangTR, vbom){
				
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
				updateTexts();
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


		setChildScene(menuChildScene);
	}

	private void returnGame() {
		quitOrNotText.setText("");
		detachChild(quitgameHUD);
		this.clearChildScene();
		setChildScene(menuChildScene);

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
		// TODO Auto-generated method stub
		return SceneType.SCENE_PROFILE;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		this.detachSelf();
	this.dispose();
}

@Override
public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
		float pMenuItemLocalX, float pMenuItemLocalY) {
	// TODO Auto-generated method stub
	switch(pMenuItem.getID())
	{
		case MENU_PROFILE02:
			resourcesManager.setProfile(0);
			playSelectSound();			
			//resourcesManager.setIdMenu(10);
			resourcesManager.setIdMenu(0);
			SceneManager.getInstance().loadMainMenuScene(engine);
			//SceneManager.getInstance().loadMainMenuScene(engine);			
			return true;
		case MENU_PROFILE24:
			resourcesManager.setProfile(1);
			playSelectSound();			
			//resourcesManager.setIdMenu(20);
			resourcesManager.setIdMenu(0);
			SceneManager.getInstance().loadMainMenu24Scene(engine);

			return true;
		case MENU_PROFILE4:
			resourcesManager.setProfile(2);
			playSelectSound();			
			//resourcesManager.setIdMenu(30);
			resourcesManager.setIdMenu(0);
			SceneManager.getInstance().loadMainMenu4Scene(engine);
			//getFullVersion();
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

private void initSounds(){
	//-- init music ---------------------------------------------------------------------
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

private void updateTexts(){
		
	//if (localLanguage==0){
	
		bitmapTextChooseProfile.setText(getText(0));
		bitmapTextProfile02.setText(getText(1));
		bitmapTextProfile24.setText(getText(2));
		bitmapTextProfile4.setText(getText(3));
		bitmapTextChooseLanguage.setText(getText(4));
		
	//}else{
		
	//}
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

private void changeLanguage(String newLanguage){
	
	 // if we want to modify the default value (consume lot of resources)
	if (newLanguage.contentEquals("FR")){
		((GameActivity) activity).setStringPreferences("language", "1");
	} else if (newLanguage.contentEquals("UK")){
		((GameActivity) activity).setStringPreferences("language", "2");
	}
	
	/*
	prefLanguage = ((GameActivity) activity).getPreference("language", "1");
	if (prefLanguage.contentEquals("1")){
		resourcesManager.tts.setLanguage(Locale.FRENCH);
	} else if (prefLanguage.contentEquals("2")){
		resourcesManager.tts.setLanguage(Locale.ENGLISH);	
	}
	*/
	
	
	
	/*
	if (newLanguage.contentEquals("FR")){
		resourcesManager.tts.setLanguage(Locale.FRENCH);
		localLanguage=0;
	} else if (newLanguage.contentEquals("UK")){
		resourcesManager.tts.setLanguage(Locale.ENGLISH);	
		localLanguage=1;
	}
	*/
	
}
/*
private void pauseMusic(){
	try{	

		if(resourcesManager.mMusicFingerFamily.isPlaying()){
		resourcesManager.mMusicFingerFamily.release();
		}
	} catch (Exception ex){
		ex.printStackTrace();
	}
}
*/
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

private void getFullVersion(){
	
		try {		    		
	    	((GameActivity) activity).getFullVersion();	
	    }catch (Exception ex){
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