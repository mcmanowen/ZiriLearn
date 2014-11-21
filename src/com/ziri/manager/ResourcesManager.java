package com.ziri.manager;

import java.io.IOException;
import java.util.Locale;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.BitmapFont;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import com.ziri.GameActivity;

public class ResourcesManager implements OnInitListener {

	// ---------------------------------------------
	// PREFERENCES
	// ---------------------------------------------

	String prefProfile, prefGameTimer;

	String prefLocalPlayer, prefVisitorPlayer, prefLanguage;
	boolean prefMusic, prefGameSounds, prefLearnSounds;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES
	// ------------------------------------------------------------------------------------------------------------

	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public Engine engine;
	public BaseGameActivity activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES COMMON
	// ------------------------------------------------------------------------------------------------------------
	public int idProfile; // -- 0-2 or 2-4 or 4+ years
	public int idMenu; // -- lecons or games

	// --public Font font ------------------------------------------
	public BitmapFont mBitmapFont;

	// --parallax background ------------------------------------------

	private BitmapTextureAtlas mAutoParallaxBackgroundTexture;

	public ITextureRegion mParallaxLayerBack;
	public ITextureRegion mParallaxLayerMid;
	public ITextureRegion mParallaxLayerFront;

	public ITextureRegion mParallaxLayerBackBoard;
	// ----------------------------------------------------------------

	public BitmapTextureAtlas emptyWindowTA;
	public ITextureRegion emptyWindowTR;

	// -- control buttons ---------------------------------------------

	public BitmapTextureAtlas btLeftArrowTA;
	public TiledTextureRegion btLeftArrowTR;

	public BitmapTextureAtlas btRightArrowTA;
	public TiledTextureRegion btRightArrowTR;

	public BitmapTextureAtlas btRestartTA;
	public TiledTextureRegion btRestartTR;

	public BuildableBitmapTextureAtlas nextLevelTA;
	public ITiledTextureRegion nextLevelTR;

	public BuildableBitmapTextureAtlas btYesTA;
	public ITiledTextureRegion btYesTR;

	public BuildableBitmapTextureAtlas btNoTA;
	public ITiledTextureRegion btNoTR;

	public BuildableBitmapTextureAtlas btNextTA;
	public ITiledTextureRegion btNextTR;

	public BuildableBitmapTextureAtlas btPreviousTA;
	public ITiledTextureRegion btPreviousTR;

	// ------ menu bar
	// --------------------------------------------------------------------------------------------

	public BitmapTextureAtlas btPauseSmallTA;
	public TiledTextureRegion btPauseSmallTR;

	public BitmapTextureAtlas btPauseTA;
	public TiledTextureRegion btPauseTR;

	BitmapTextureAtlas btHomeTA;
	public TiledTextureRegion btHomeTR;

	BitmapTextureAtlas btHomeFreezeTA;
	public ITextureRegion btHomeFreezeTR;

	BitmapTextureAtlas btMenuTA;
	public TiledTextureRegion btMenuTR;

	private BitmapTextureAtlas btMusicTA;
	public ITextureRegion btMusicTR;

	private BitmapTextureAtlas btMusicMuteTA;
	public ITextureRegion btMusicMuteTR;

	private BitmapTextureAtlas btSoundsTA;
	public ITextureRegion btSoundsTR;

	private BitmapTextureAtlas btSoundsMuteTA;
	public ITextureRegion btSoundsMuteTR;

	public BuildableBitmapTextureAtlas btLangTA;
	public ITiledTextureRegion btLangTR;

	// ----------------------------------------------------------------

	// -- control joystick ---------------------------------------
	/*
	 * private BitmapTextureAtlas mOnScreenControlTexture; public ITextureRegion
	 * mOnScreenControlBaseTR; public ITextureRegion mOnScreenControlKnobTR;
	 */
	// -- sounds ----------------------------------------
	public Sound mExplosionSound;
	public Sound mMoveObjectSound;
	public Sound mCreateGymBallSound;
	public Sound mBravoSound;
	public Sound mBallinholeSound;
	public Sound mSelectSound;
	public Sound exitSound;
	public Sound boooSound;
	public Sound bravokidsSound;
	public Sound snd001,snd002,snd003,snd004,snd005,snd006,snd007,snd008,snd009,snd010;
	public Sound snd011,snd012,snd013,snd014,snd015,snd016,snd017,snd018,snd019,snd020;
	// music
	public Music mIntroMusic;
	public static Music mGangstaMusic;
	public static Music mMusicFingerFamily;
	public static Music mMusicSeventies;

	// ----------------------------------------------------------------------------------------------------------

	// text to speech ------------------------------------------------

	public TextToSpeech tts;

	// ----------------------------------------------------------------

	private BitmapTextureAtlas imTrueTA;
	public ITextureRegion imTrueTR;

	private BitmapTextureAtlas imFalseTA;
	public ITextureRegion imFalseTR;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES SPLASH
	// ------------------------------------------------------------------------------------------------------------

	public BitmapTextureAtlas splashTA;
	public ITextureRegion splashTR;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES PROFILE
	// ------------------------------------------------------------------------------------------------------------

	BitmapTextureAtlas btProfile02TA;
	public TiledTextureRegion btProfile02TR;

	BitmapTextureAtlas btProfile24TA;
	public TiledTextureRegion btProfile24TR;

	BitmapTextureAtlas btProfile4TA;
	public TiledTextureRegion btProfile4TR;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES MENU SCENE
	// ------------------------------------------------------------------------------------------------------------

	BitmapTextureAtlas btGameIconTA;
	public TiledTextureRegion btGameIconTR;
	
	BitmapTextureAtlas btGame24IconTA;
	public TiledTextureRegion btGame24IconTR;
	
	BitmapTextureAtlas btGame4IconTA;
	public TiledTextureRegion btGame4IconTR;

	BitmapTextureAtlas btAlphabetlearnIconTA;
	public TiledTextureRegion btAlphabetlearnIconTR;

	BitmapTextureAtlas btNumberslearnIconTA;
	public TiledTextureRegion btNumberslearnIconTR;

	BitmapTextureAtlas btAnimallearnIconTA;
	public TiledTextureRegion btAnimallearnIconTR;

	BitmapTextureAtlas btColorlearnIconTA;
	public TiledTextureRegion btColorlearnIconTR;

	BitmapTextureAtlas btShapelearnIconTA;
	public TiledTextureRegion btShapelearnIconTR;

	BitmapTextureAtlas btFlaglearnIconTA;
	public TiledTextureRegion btFlaglearnIconTR;

	BitmapTextureAtlas btFoodlearnIconTA;
	public TiledTextureRegion btFoodlearnIconTR;

	BitmapTextureAtlas btMusicalinstlearnIconTA;
	public TiledTextureRegion btMusicalinstlearnIconTR;
	
	BitmapTextureAtlas btTransportlearnIconTA;
	public TiledTextureRegion btTransportlearnIconTR;
	
	BitmapTextureAtlas btAlphabetplayIconTA;
	public TiledTextureRegion btAlphabetplayIconTR;

	BitmapTextureAtlas btNumbersplayIconTA;
	public TiledTextureRegion btNumbersplayIconTR;

	BitmapTextureAtlas btAnimalplayIconTA;
	public TiledTextureRegion btAnimalplayIconTR;

	BitmapTextureAtlas btColorplayIconTA;
	public TiledTextureRegion btColorplayIconTR;

	BitmapTextureAtlas btShapeplayIconTA;
	public TiledTextureRegion btShapeplayIconTR;

	BitmapTextureAtlas btFlagplayIconTA;
	public TiledTextureRegion btFlagplayIconTR;

	BitmapTextureAtlas btFoodplayIconTA;
	public TiledTextureRegion btFoodplayIconTR;

	BitmapTextureAtlas btMusicalinstplayIconTA;
	public TiledTextureRegion btMusicalinstplayIconTR;
	
	BitmapTextureAtlas btTransportplayIconTA;
	public TiledTextureRegion btTransportplayIconTR;

	BitmapTextureAtlas btOptionsIconTA;
	public TiledTextureRegion btOptionsIconTR;

	BitmapTextureAtlas btProfileIconTA;
	public TiledTextureRegion btProfileIconTR;

	BitmapTextureAtlas btPlayIconTA;
	public TiledTextureRegion btPlayIconTR;

	BitmapTextureAtlas btLearnIconTA;
	public TiledTextureRegion btLearnIconTR;



	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES ALPHABET
	// ------------------------------------------------------------------------------------------------------------

	// -- alphabets

	BitmapTextureAtlas letterATA, letterBTA, letterCTA, letterDTA, letterETA,
			letterFTA, letterGTA, letterHTA, letterITA, letterJTA, letterKTA,
			letterLTA, letterMTA, letterNTA, letterOTA, letterPTA, letterQTA,
			letterRTA, letterSTA, letterTTA, letterUTA, letterVTA, letterWTA,
			letterXTA, letterYTA, letterZTA;


	// --small letters
	public TiledTextureRegion letterAyellowTR, letterGyellowTR,
			letterMyellowTR, letterPyellowTR, letterSyellowTR, letterVyellowTR,
			letterBgreenTR, letterHgreenTR, letterJgreenTR, letterNgreenTR,
			letterRgreenTR, letterXgreenTR, letterCblueTR, letterIblueTR,
			letterTblueTR, letterYblueTR, letterDpurpleTR, letterKpurpleTR,
			letterQpurpleTR, letterEredTR, letterLredTR, letterOredTR,
			letterUredTR, letterZredTR, letterForangeTR, letterWorangeTR;

	// -------------------------------------
	BitmapTextureAtlas blackBoardTA;
	public ITextureRegion blackBoardTR;

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES NUMBER
	// ------------------------------------------------------------------------------------------------------------

	// -- numbers

	BitmapTextureAtlas number00TA, number01TA, number02TA, number03TA,
			number04TA, number05TA, number06TA, number07TA, number08TA,
			number09TA, number10TA;

	public TiledTextureRegion number01yellowTR, number06yellowTR,
			number02greenTR, number08greenTR, number04blueTR, number05purpleTR,
			number10purpleTR, number00redTR, number07redTR, number03orangeTR,
			number09orangeTR;


	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES IMAGES
	// ------------------------------------------------------------------------------------------------------------

	BitmapTextureAtlas img_001TA, img_002TA, img_003TA, img_004TA, img_005TA,
			img_006TA, img_007TA, img_008TA, img_009TA, img_010TA, img_011TA,
			img_012TA, img_013TA, img_014TA, img_015TA, img_016TA, img_017TA,
			img_018TA, img_019TA, img_020TA, img_021TA, img_022TA, img_023TA,
			img_024TA, img_025TA, img_026TA, img_027TA, img_028TA, img_029TA,
			img_030TA, img_031TA, img_032TA, img_033TA, img_034TA, img_035TA,
			img_036TA, img_037TA, img_038TA, img_039TA, img_040TA, img_041TA,
			img_042TA, img_043TA, img_044TA, img_045TA, img_046TA, img_047TA,
			img_048TA, img_049TA, img_050TA/*
											 * , img_051TA,img_052TA,img_053TA,
											 * img_054TA,
											 * img_055TA,img_056TA,img_057TA
											 * ,img_058TA, img_059TA,img_060TA,
											 * img_061TA
											 * ,img_062TA,img_063TA,img_064TA,
											 * img_065TA
											 * ,img_066TA,img_067TA,img_068TA,
											 * img_069TA,img_070TA,
											 * img_071TA,img_072TA
											 * ,img_073TA,img_074TA,
											 * img_075TA,img_076TA
											 * ,img_077TA,img_078TA,
											 * img_079TA,img_080TA,
											 * img_081TA,img_082TA
											 * ,img_083TA,img_084TA,
											 * img_085TA,img_086TA
											 * ,img_087TA,img_088TA,
											 * img_089TA,img_090TA,
											 * img_091TA,img_092TA
											 * ,img_093TA,img_094TA,
											 * img_095TA,img_096TA
											 * ,img_097TA,img_098TA,
											 * img_099TA,img_100TA
											 */
			;
	public ITextureRegion img_001TR, img_002TR, img_003TR, img_004TR,
			img_005TR, img_006TR, img_007TR, img_008TR, img_009TR, img_010TR,
			img_011TR, img_012TR, img_013TR, img_014TR, img_015TR, img_016TR,
			img_017TR, img_018TR, img_019TR, img_020TR, img_021TR, img_022TR,
			img_023TR, img_024TR, img_025TR, img_026TR, img_027TR, img_028TR,
			img_029TR, img_030TR, img_031TR, img_032TR, img_033TR, img_034TR,
			img_035TR, img_036TR, img_037TR, img_038TR, img_039TR, img_040TR,
			img_041TR, img_042TR, img_043TR, img_044TR, img_045TR, img_046TR,
			img_047TR, img_048TR, img_049TR, img_050TR/*
													 * ,
													 * img_051TR,img_052TR,img_053TR
													 * ,img_054TR,
													 * img_055TR,img_056TR
													 * ,img_057TR,img_058TR,
													 * img_059TR,img_060TR,
													 * img_061TR
													 * ,img_062TR,img_063TR
													 * ,img_064TR,
													 * img_065TR,img_066TR
													 * ,img_067TR,img_068TR,
													 * img_069TR,img_070TR,
													 * img_071TR
													 * ,img_072TR,img_073TR
													 * ,img_074TR,
													 * img_075TR,img_076TR
													 * ,img_077TR,img_078TR,
													 * img_079TR,img_080TR,
													 * img_081TR
													 * ,img_082TR,img_083TR
													 * ,img_084TR,
													 * img_085TR,img_086TR
													 * ,img_087TR,img_088TR,
													 * img_089TR,img_090TR,
													 * img_091TR
													 * ,img_092TR,img_093TR
													 * ,img_094TR,
													 * img_095TR,img_096TR
													 * ,img_097TR,img_098TR,
													 * img_099TR,img_100TR
													 */
			;

	// -------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES BALL
	// ------------------------------------------------------------------------------------------------------------

	// -- balls -----------------------------------------

	/*
	 * BitmapTextureAtlas footballTA; ITextureRegion footballTR;
	 */

	BitmapTextureAtlas beachballTA;
	public TiledTextureRegion beachballTR;
	BitmapTextureAtlas tennisballTA;
	public TiledTextureRegion tennisballTR;
	BitmapTextureAtlas billardballTA;
	public TiledTextureRegion billardballTR;
	BitmapTextureAtlas volleyballTA;
	public TiledTextureRegion volleyballTR;
	BitmapTextureAtlas football2TA;
	public TiledTextureRegion football2TR;
	BitmapTextureAtlas footballTA;
	public TiledTextureRegion footballTR;
	BitmapTextureAtlas gymballgreenTA;
	public TiledTextureRegion gymballgreenTR;
	BitmapTextureAtlas gymballredTA;
	public TiledTextureRegion gymballredTR;
	BitmapTextureAtlas tchoupiballTA;
	public TiledTextureRegion tchoupiballTR;
	BitmapTextureAtlas pitchougreenTA;
	public TiledTextureRegion pitchougreenTR;
	BitmapTextureAtlas pitchoupurpleTA;
	public TiledTextureRegion pitchoupurpleTR;

	// BitmapTextureAtlas hole01ballTA;
	// public TiledTextureRegion hole01ballTR;

	BitmapTextureAtlas netleftTA;
	public TiledTextureRegion netleftTR;

	BitmapTextureAtlas netrightTA;
	public TiledTextureRegion netrightTR;

	BitmapTextureAtlas starsTA;
	public TiledTextureRegion starsTR;

	// BitmapTextureAtlas littlestarTA;
	// public TiledTextureRegion littlestarTR;

	BitmapTextureAtlas keeperleftTA;
	public TiledTextureRegion keeperleftTR;

	BitmapTextureAtlas keeperrightTA;
	public TiledTextureRegion keeperrightTR;

	BitmapTextureAtlas foot01leftTA;
	public TiledTextureRegion foot01leftTR;

	BitmapTextureAtlas foot01rightTA;
	public TiledTextureRegion foot01rightTR;

	// --------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES BALLGAME ZIRITAP
	// ------------------------------------------------------------------------------------------------------------
	
	public TiledTextureRegion dimg_001TR, dimg_002TR, dimg_003TR, dimg_004TR,
	dimg_005TR, dimg_006TR, dimg_007TR, dimg_008TR, dimg_009TR, dimg_010TR,
	dimg_011TR, dimg_012TR, dimg_013TR, dimg_014TR, dimg_015TR, dimg_016TR,
	dimg_017TR, dimg_018TR, dimg_019TR, dimg_020TR;

	
	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES BALLGAME24
	// ------------------------------------------------------------------------------------------------------------
	
	public BitmapTextureAtlas mParticle24TA;
	public ITextureRegion mParticle24TR;
	
	
	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES COLOR
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES SHAPE
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES OBJECT
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES TRANSPORT
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES FOOD
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES MUSICAL INSTRUMENT
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES SOUND
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// VARIABLES FLAG
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// LOAD RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// LOAD GENERAL RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	private void loadPitchouBalls(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/ball/");
		
		pitchougreenTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		pitchougreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(pitchougreenTA, activity, "pitchou1-4.png",
						0, 0, 2, 2); // 64x32 tennisballTA.load();
		pitchougreenTA.load();
		
		pitchoupurpleTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		pitchoupurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(pitchoupurpleTA, activity, "pitchou2-4.png",
						0, 0, 2, 2); // 64x32 tennisballTA.load();
		pitchoupurpleTA.load();
		
	}
	
	private void unloadPitchouBalls(){
		pitchougreenTA.unload();
		pitchoupurpleTA.unload();		
	}
	
	// -- menuBar
	// -- 1.menu-2.home-3.homefreeze-4.pause-5.pausesmall-6.lang-7.music-
	// -- 8.musicmute-9.sound-10.soundmute
	public void loadMenuBarButtons(String bt1, String bt2, String bt3,
			String bt4, String bt5, String bt6, String bt7, String bt8,
			String bt9, String bt10) {

		BitmapTextureAtlasTextureRegionFactory
		.setAssetBasePath("gfx/icon/");
		if (!bt1.contentEquals("")) {
			
			btMenuTA = new BitmapTextureAtlas(activity.getTextureManager(),
					128, 64, TextureOptions.BILINEAR);
			btMenuTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btMenuTA, activity, "menu.png", 0, 0,
							2, 1);
			btMenuTA.load();
		}
		if (!bt2.contentEquals("")) {
			btHomeTA = new BitmapTextureAtlas(activity.getTextureManager(),
					128, 64, TextureOptions.BILINEAR);
			btHomeTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btHomeTA, activity, "home.png", 0, 0,
							2, 1);
			btHomeTA.load();
		}
		if (!bt3.contentEquals("")) {
			btHomeFreezeTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 64, 64,
					TextureOptions.BILINEAR);
			btHomeFreezeTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(btHomeFreezeTA, activity,
							"home_freeze.png", 0, 0);
			btHomeFreezeTA.load();
		}
		if (!bt4.contentEquals("")) {

			this.btPauseTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 256, 128,
					TextureOptions.BILINEAR);
			this.btPauseTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btPauseTA, activity, "bt_pause.png",
							0, 0, 2, 1);
			this.btPauseTA.load();
		}
		if (!bt5.contentEquals("")) {

			this.btPauseSmallTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 128, 64,
					TextureOptions.BILINEAR);
			this.btPauseSmallTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btPauseSmallTA, activity,
							"bt_pause_small.png", 0, 0, 2, 1);
			this.btPauseSmallTA.load();
		}
		if (!bt6.contentEquals("")) {
			btLangTA = new BuildableBitmapTextureAtlas(
					activity.getTextureManager(), 256, 64,
					TextureOptions.BILINEAR);
			btLangTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btLangTA, activity,
							"lang_fr_uk_big.png", 2, 1);

			try {
				this.btLangTA
						.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
								0, 1, 0));
				this.btLangTA.load();
			} catch (final TextureAtlasBuilderException e) {
				// Debug.e(e);
			}
		}
		if (!bt7.contentEquals("")) {

			btMusicTA = new BitmapTextureAtlas(activity.getTextureManager(),
					64, 64, TextureOptions.BILINEAR);
			btMusicTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
					btMusicTA, activity, "music.png", 0, 0);
			btMusicTA.load();
		}
		if (!bt8.contentEquals("")) {
			btMusicMuteTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 64, 64,
					TextureOptions.BILINEAR);
			btMusicMuteTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(btMusicMuteTA, activity, "notesMute.png",
							0, 0);
			btMusicMuteTA.load();
		}
		if (!bt9.contentEquals("")) {

			btSoundsTA = new BitmapTextureAtlas(activity.getTextureManager(),
					64, 64, TextureOptions.BILINEAR);
			btSoundsTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(btSoundsTA, activity, "sounds.png", 0, 0);
			btSoundsTA.load();
		}
		if (!bt10.contentEquals("")) {
			btSoundsMuteTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 64, 64,
					TextureOptions.BILINEAR);
			btSoundsMuteTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(btSoundsMuteTA, activity, "notesMute.png",
							0, 0);
			btSoundsMuteTA.load();
		}

	}

	public void unloadMenuBarButtons(String bt1, String bt2, String bt3,
			String bt4, String bt5, String bt6, String bt7, String bt8,
			String bt9, String bt10) {
		// -- 1.menu-2.home-3.homefreeze-4.pause-5.pausesmall-6.lang-7.music-
		// -- 8.musicmute-9.sound-10.soundmute

		if (!bt1.contentEquals("")) {
			btMenuTA.unload();
		}
		if (!bt2.contentEquals("")) {
			btHomeTA.unload();
		}
		if (!bt3.contentEquals("")) {
			btHomeFreezeTA.unload();
		}
		if (!bt4.contentEquals("")) {
			this.btPauseTA.unload();
		}
		if (!bt5.contentEquals("")) {
			this.btPauseSmallTA.unload();
		}
		if (!bt6.contentEquals("")) {
			this.btLangTA.unload();
		}
		if (!bt7.contentEquals("")) {
			btMusicTA.unload();
		}
		if (!bt8.contentEquals("")) {
			btMusicMuteTA.unload();
		}
		if (!bt9.contentEquals("")) {
			btSoundsTA.unload();
		}
		if (!bt10.contentEquals("")) {
			btSoundsMuteTA.unload();
		}

	}

	public void loadArrowButtons(String bta1, String bta2) {
		// -- control arrows
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/icon/");
		if (!bta1.contentEquals("")) {
			btRightArrowTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 256, 128,
					TextureOptions.BILINEAR);
			btRightArrowTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btRightArrowTA, activity,
							"right_arrow.png", 0, 0, 2, 1);
			btRightArrowTA.load();
		}
		if (!bta2.contentEquals("")) {
			btLeftArrowTA = new BitmapTextureAtlas(
					activity.getTextureManager(), 256, 128);
			btLeftArrowTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btLeftArrowTA, activity,
							"left_arrow.png", 0, 0, 2, 1);
			btLeftArrowTA.load();
		}

	}

	public void unloadArrowButtons(String bta1, String bta2) {

		if (!bta1.contentEquals("")) {
			btRightArrowTA.unload();
		}
		if (!bta2.contentEquals("")) {
			btLeftArrowTA.unload();
		}
	}

	public void loadTrueFalseImg() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/icon/");

		imTrueTA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128, TextureOptions.BILINEAR);
		imTrueTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				imTrueTA, activity, "imtrue.png", 0, 0);
		imTrueTA.load();

		imFalseTA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128, TextureOptions.BILINEAR);
		imFalseTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				imFalseTA, activity, "imfalse.png", 0, 0);
		imFalseTA.load();
	}

	public void unloadTrueFalseImg() {
		imTrueTA.unload();
		imFalseTA.unload();
	}

	public void loadexitWindowButtons(String wnd1, String wnd2, String wnd3,
			String wnd4) {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/icon/");

		if (!wnd1.contentEquals("")) {
			emptyWindowTA = new BitmapTextureAtlas(
					this.activity.getTextureManager(), 1024, 512,
					TextureOptions.BILINEAR);
			emptyWindowTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(emptyWindowTA, this.activity,
							"emptywindow_grey.png", 0, 0);
			emptyWindowTA.load();
		}
		if (!wnd2.contentEquals("")) {
			btYesTA = new BuildableBitmapTextureAtlas(
					activity.getTextureManager(), 256, 128,
					TextureOptions.BILINEAR);
			btYesTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btYesTA, activity, "bt_yes.png", 1, 2);

			try {
				this.btYesTA
						.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
								0, 1, 0));
				this.btYesTA.load();
			} catch (final TextureAtlasBuilderException e) {
				// Debug.e(e);
			}

		}
		if (!wnd3.contentEquals("")) {
			btNoTA = new BuildableBitmapTextureAtlas(
					activity.getTextureManager(), 256, 128,
					TextureOptions.BILINEAR);
			btNoTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(btNoTA, activity, "bt_no.png", 1, 2);

			try {
				this.btNoTA
						.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
								0, 1, 0));
				this.btNoTA.load();
			} catch (final TextureAtlasBuilderException e) {
				// Debug.e(e);
			}

		}
		if (!wnd4.contentEquals("")) {

			nextLevelTA = new BuildableBitmapTextureAtlas(
					activity.getTextureManager(), 256, 128,
					TextureOptions.BILINEAR);
			nextLevelTR = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(nextLevelTA, activity, "bt_next.png",
							1, 2);

			try {
				this.nextLevelTA
						.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
								0, 1, 0));
				this.nextLevelTA.load();
			} catch (final TextureAtlasBuilderException e) {
				// Debug.e(e);
			}
		}
	}

	public void unloadexitWindowButtons(String wnd1, String wnd2, String wnd3,
			String wnd4) {

		if (!wnd1.contentEquals("")) {
			emptyWindowTA.unload();
		}
		if (!wnd2.contentEquals("")) {

			this.btYesTA.unload();
		}
		if (!wnd3.contentEquals("")) {

			this.btNoTA.unload();
		}
		if (!wnd4.contentEquals("")) {

			this.nextLevelTA.unload();
		}
	}

	private void loadStarParticles(){
		// -- particles		
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/ball/");

				mParticle24TA = new BitmapTextureAtlas(activity.getTextureManager(), 32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
				mParticle24TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mParticle24TA, activity, "particle_point.png", 0, 0);
				mParticle24TA.load();		
	}
	
	private void unloadStarParticles(){
		mParticle24TA.unload();		
	}
	
	
	public void loadFonts() {

		mBitmapFont = new BitmapFont(activity.getTextureManager(),
				activity.getAssets(), "font/BitmapFont.fnt");
		mBitmapFont.load();
		/*
		 * FontFactory.setAssetBasePath("gfx/font/"); final ITexture
		 * finalfont=new BitmapTextureAtlas(activity.getTextureManager(), 210,
		 * 216, TextureOptions.BILINEAR_PREMULTIPLYALPHA); font =
		 * FontFactory.createStrokeFromAsset
		 * (activity.getFontManager(),finalfont, activity.getAssets(),
		 * "BitmapFont.fnt", 70, true,10,10,10); font.load();
		 */
	}

	public void unloadFonts() {
		mBitmapFont.unloadTextures();
		mBitmapFont.unload();
	}

	private void loadSounds(String snd1, String snd2, String snd3, String snd4,
			String snd5, String snd6, String snd7, String snd8, String snd9) {
		// --
		// mExplosionSound,mMoveObjectSound,mCreateGymBallSound,mBravoSound,
		//mBallinholeSound,mSelectSound,exitSound,boosound,bravokidsSound

		SoundFactory.setAssetBasePath("mfx/");

		try {
			if (!snd1.contentEquals("")) {
				mExplosionSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "explosion.ogg");
			}
			if (!snd2.contentEquals("")) {
				mMoveObjectSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "moveballs.wav");
			}
			if (!snd3.contentEquals("")) {
				mCreateGymBallSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "goal.wav");
			}
			if (!snd4.contentEquals("")) {
				mBravoSound = SoundFactory
						.createSoundFromAsset(engine.getSoundManager(),
								activity, "bravo_applaud.mp3");
			}
			if (!snd5.contentEquals("")) {
				mBallinholeSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "balldrop.wav");
			}
			if (!snd6.contentEquals("")) {
				mSelectSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd001.wav");
			}
			if (!snd7.contentEquals("")) {
				exitSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "exitsound.wav");
			}
			if (!snd8.contentEquals("")) {
				boooSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "booo.wav");
			}
			if (!snd9.contentEquals("")) {
				bravokidsSound = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "kidsyeahapplaud.wav");
			}

		} catch (final IOException e) {
			Debug.e(e);
		}
	}

	private void unloadSounds(String snd1, String snd2, String snd3,
			String snd4, String snd5, String snd6, String snd7, String snd8, String snd9) {

		if (!snd1.contentEquals("")) {
			mExplosionSound.release();
		}
		if (!snd2.contentEquals("")) {
			mMoveObjectSound.release();
		}
		if (!snd3.contentEquals("")) {
			mCreateGymBallSound.release();
		}
		if (!snd4.contentEquals("")) {
			mBravoSound.release();
		}
		if (!snd5.contentEquals("")) {
			mBallinholeSound.release();
		}
		if (!snd6.contentEquals("")) {
			mSelectSound.release();
		}
		if (!snd7.contentEquals("")) {
			exitSound.release();
		}
		if (!snd8.contentEquals("")) {
			boooSound.release();
		}
		if (!snd9.contentEquals("")) {
			bravokidsSound.release();
		}

	}

	private void loadtaptapSounds(String snd1, String snd2, String snd3, String snd4,
			String snd5, String snd6, String snd7, String snd8,String snd9, String snd10, 
			String snd11, String snd12,String snd13, String snd14, String snd15, String snd16,
			String snd17, String snd18, String snd19, String snd20) {


		SoundFactory.setAssetBasePath("mfx/");

		try {
			

			if (!snd1.contentEquals("")) {
				snd001 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd001.wav");
			}
			if (!snd2.contentEquals("")) {
				snd002 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd002.wav");
			}
			if (!snd3.contentEquals("")) {
				snd003 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd003.mp3");
			}
			if (!snd4.contentEquals("")) {
				snd004 = SoundFactory.createSoundFromAsset
						(engine.getSoundManager(),activity, "snd004.mp3");
			}
			if (!snd5.contentEquals("")) {
				snd005 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd005.mp3");
			}
			if (!snd6.contentEquals("")) {
				snd006 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd006.mp3");
			}
			if (!snd7.contentEquals("")) {
				snd007 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd007.mp3");
			}
			if (!snd8.contentEquals("")) {
				snd008 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd008.mp3");
			}
			if (!snd9.contentEquals("")) {
				snd009 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd009.mp3");
			}
			if (!snd10.contentEquals("")) {
				snd010 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd010.mp3");
			}
			if (!snd11.contentEquals("")) {
				snd011 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd011.mp3");
			}
			if (!snd12.contentEquals("")) {
				snd012 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd012.mp3");
			}
			if (!snd13.contentEquals("")) {
				snd013 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd013.mp3");
			}
			if (!snd14.contentEquals("")) {
				snd014 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd014.mp3");
			}
			if (!snd15.contentEquals("")) {
				snd015 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd015.mp3");
			}
			if (!snd16.contentEquals("")) {
				snd016 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd016.mp3");
			}
			if (!snd17.contentEquals("")) {
				snd017 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd017.mp3");
			}
			if (!snd18.contentEquals("")) {
				snd018 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd018.mp3");
			}
			if (!snd19.contentEquals("")) {
				snd019 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd019.mp3");
			}
			if (!snd20.contentEquals("")) {
				snd020 = SoundFactory.createSoundFromAsset(
						engine.getSoundManager(), activity, "snd020.mp3");
			}

		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	private void unloadtaptapSounds(String snd1, String snd2, String snd3,
			String snd4, String snd5, String snd6, String snd7, String snd8,String snd9, 
			String snd10, String snd11,String snd12, String snd13, String snd14, 
			String snd15, String snd16, String snd17, String snd18, 
			String snd19, String snd20) {

		if (!snd1.contentEquals("")) {
			snd001.release();
		}
		if (!snd2.contentEquals("")) {
			snd002.release();
		}
		if (!snd3.contentEquals("")) {
			snd003.release();
		}
		if (!snd4.contentEquals("")) {
			snd004.release();
		}
		if (!snd5.contentEquals("")) {
			snd005.release();
		}
		if (!snd6.contentEquals("")) {
			snd006.release();
		}
		if (!snd7.contentEquals("")) {
			snd007.release();
		}
		if (!snd8.contentEquals("")) {
			snd008.release();
		}
		if (!snd9.contentEquals("")) {
			snd009.release();
		}
		if (!snd10.contentEquals("")) {
			snd010.release();
		}
		if (!snd11.contentEquals("")) {
			snd011.release();
		}
		if (!snd12.contentEquals("")) {
			snd012.release();
		}
		if (!snd13.contentEquals("")) {
			snd013.release();
		}
		if (!snd14.contentEquals("")) {
			snd014.release();
		}
		if (!snd15.contentEquals("")) {
			snd015.release();
		}
		if (!snd16.contentEquals("")) {
			snd016.release();
		}
		if (!snd17.contentEquals("")) {
			snd017.release();
		}
		if (!snd18.contentEquals("")) {
			snd018.release();
		}
		if (!snd19.contentEquals("")) {
			snd019.release();
		}
		if (!snd20.contentEquals("")) {
			snd020.release();
		}

	}
	
	private void loadMusics(String music1, String music2, String music3, String music4) {

		MusicFactory.setAssetBasePath("mfx/");

		try {

			if (!music1.contentEquals("")) {
				mIntroMusic = MusicFactory.createMusicFromAsset(
						engine.getMusicManager(), activity, "intro.mp3");
				this.mIntroMusic.setLooping(false);
			}

			if (!music2.contentEquals("")) {
				mMusicFingerFamily = MusicFactory.createMusicFromAsset(
						engine.getMusicManager(), activity,
						"m_finger_family.mp3");
				this.mMusicFingerFamily.setLooping(true);
			}

			if (!music3.contentEquals("")) {
				mGangstaMusic = MusicFactory.createMusicFromAsset(
						engine.getMusicManager(), activity,
						"m_hardestgangsta.mp3");
				this.mGangstaMusic.setLooping(true);
			}
			
			if (!music4.contentEquals("")) {
				mMusicSeventies = MusicFactory.createMusicFromAsset(
						engine.getMusicManager(), activity,
						"m_seventies.mp3");
				this.mMusicSeventies.setLooping(true);
			}
			

		} catch (final IOException e) {
			Debug.e(e);
		}
	}

	private void unloadMusics(String music1, String music2, String music3, String music4) {

		if (!music1.contentEquals("")) {
			mIntroMusic.release();
		}

		if (!music2.contentEquals("")) {
			mMusicFingerFamily.release();
		}

		if (!music3.contentEquals("")) {
			mGangstaMusic.release();
		}
		
		if (!music4.contentEquals("")) {
			mMusicSeventies.release();
		}

	}

	public void loadBackground(int pX, int pY, String bgBack,
			String bgFrontTop, String bgFrontMid, int pbX, int pbY,
			String bgBoard) {

		// -- background
		// ---------------------------------------------------------------
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/image/");

		mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), pX, pY);
		if (!bgBack.contentEquals("")) {
			mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(mAutoParallaxBackgroundTexture, activity,
							bgBack, 0, 188);
		}
		if (!bgFrontTop.contentEquals("")) {
			mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(mAutoParallaxBackgroundTexture, activity,
							bgFrontTop, 0, 0);
		}
		if (!bgFrontMid.contentEquals("")) {
			mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(mAutoParallaxBackgroundTexture, activity,
							bgFrontMid, 0, 669);
		}
		mAutoParallaxBackgroundTexture.load();

		if (!bgBoard.contentEquals("")) {
			blackBoardTA = new BitmapTextureAtlas(
					this.activity.getTextureManager(), pbX, pbY,
					TextureOptions.BILINEAR);
			blackBoardTR = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(blackBoardTA, this.activity, bgBoard, 0, 0);
			blackBoardTA.load();
		}
	}

	private void unloadBackground(String bgboard) {
		mAutoParallaxBackgroundTexture.unload();
		if (!bgboard.contentEquals("")) {
			blackBoardTA.unload();
		}

	}

	// -------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// LOAD SPLASH RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	public void loadSplashResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
		splashTA = new BitmapTextureAtlas(this.activity.getTextureManager(),
				512, 512, TextureOptions.BILINEAR);
		splashTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTA, this.activity, "splash.png", 0, 0);
		splashTA.load();
		// loadIntroSound();
		loadMusics("OK", "OK", "","OK");
		

	}

	public void unloadSplashResources() {

		splashTA.unload();

	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD LOADING PAGE RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	public void loadLoadingResources() {

		loadFonts();

	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD PROFILE RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	public void loadProfileResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/profile/");
		btProfile02TA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btProfile02TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btProfile02TA, activity, "profile02.png",
						0, 0, 2, 1);
		btProfile02TA.load();

		btProfile24TA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btProfile24TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btProfile24TA, activity, "profile24.png",
						0, 0, 2, 1);
		btProfile24TA.load();

		btProfile4TA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btProfile4TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btProfile4TA, activity, "profile4.png",
						0, 0, 2, 1);
		btProfile4TA.load();

		loadFonts();
		loadSounds("", "", "", "", "", "OK", "","","");
		loadMenuBarButtons("", "", "", "", "", "OK", "", "", "","");
		loadexitWindowButtons("OK", "OK", "OK", "");

	}

	public void unloadProfileResources() {

		btProfile02TA.unload();
		btProfile24TA.unload();
		btProfile4TA.unload();
		unloadFonts();
		unloadSounds("", "", "", "", "", "OK", "","","");
		unloadMenuBarButtons("", "", "", "", "", "OK", "", "", "","");
		unloadexitWindowButtons("OK", "OK", "OK", "");

	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD MAIN MENU RESOURCES
	// ------------------------------------------------------------------------------------------------------------
	
	public void loadGeneralMainMenuResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		/*
		btGameIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btGameIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btGameIconTA, activity, "menu_game.png",
						0, 0, 2, 1);
		btGameIconTA.load();
		*/
		
		btOptionsIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btOptionsIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btOptionsIconTA, activity,
						"menu_options.png", 0, 0, 2, 1);
		btOptionsIconTA.load();

		btProfileIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btProfileIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btProfileIconTA, activity,
						"menu_profile.png", 0, 0, 2, 1);
		btProfileIconTA.load();

		btPlayIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btPlayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btPlayIconTA, activity, "menu_play.png",
						0, 0, 2, 1);
		btPlayIconTA.load();

		btLearnIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btLearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btLearnIconTA, activity,
						"menu_learn.png", 0, 0, 2, 1);
		btLearnIconTA.load();

		loadFonts();
		loadSounds("", "", "", "", "", "OK", "","","");
		loadexitWindowButtons("OK", "OK", "OK", "");

		loadBackground(1024, 1024, "bg_menu.jpg", "", "", 0, 0, "");
		
	}

	public void unloadGeneralMainMenuResources() {
		//btGameIconTA.unload();
		btOptionsIconTA.unload();
		btProfileIconTA.unload();
		btPlayIconTA.unload();
		btLearnIconTA.unload();
		unloadFonts();
		unloadSounds("", "", "", "", "", "OK", "","","");
		unloadexitWindowButtons("OK", "OK", "OK", "");
		unloadBackground("");			
	}
	
	public void loadMainMenuResources() {
		loadGeneralMainMenuResources();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");

		btAlphabetlearnIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btAlphabetlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAlphabetlearnIconTA, activity,
						"menu_alphabet_learn.png", 0, 0, 2, 1);
		btAlphabetlearnIconTA.load();

		btNumberslearnIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btNumberslearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btNumberslearnIconTA, activity,
						"menu_numbers_learn.png", 0, 0, 2, 1);
		btNumberslearnIconTA.load();

		btColorlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btColorlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btColorlearnIconTA, activity,
						"menu_colors_learn.png", 0, 0, 2, 1);
		btColorlearnIconTA.load();
		
		btAlphabetplayIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btAlphabetplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAlphabetplayIconTA, activity,
						"menu_alphabet_play.png", 0, 0, 2, 1);
		btAlphabetplayIconTA.load();

		btNumbersplayIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btNumbersplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btNumbersplayIconTA, activity,
						"menu_numbers_play.png", 0, 0, 2, 1);
		btNumbersplayIconTA.load();

		btColorplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btColorplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btColorplayIconTA, activity,
						"menu_colors_play.png", 0, 0, 2, 1);
		btColorplayIconTA.load();
		
		btGameIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btGameIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btGameIconTA, activity, "menu_game.png",
						0, 0, 2, 1);
		btGameIconTA.load();
	
	}

	public void unloadMainMenuResources() {
		unloadGeneralMainMenuResources();

		btAlphabetlearnIconTA.unload();
		btNumberslearnIconTA.unload();
		btColorlearnIconTA.unload();
		btAlphabetplayIconTA.unload();
		btNumbersplayIconTA.unload();
		btColorplayIconTA.unload();
		btGameIconTA.unload();

	}

	public void loadMainMenu24Resources() {
		loadGeneralMainMenuResources();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");

		btAlphabetlearnIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btAlphabetlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAlphabetlearnIconTA, activity,
						"menu_alphabet_learn.png", 0, 0, 2, 1);
		btAlphabetlearnIconTA.load();

		btNumberslearnIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btNumberslearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btNumberslearnIconTA, activity,
						"menu_numbers_learn.png", 0, 0, 2, 1);
		btNumberslearnIconTA.load();

		btColorlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btColorlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btColorlearnIconTA, activity,
						"menu_colors_learn.png", 0, 0, 2, 1);
		btColorlearnIconTA.load();

		btAnimallearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btAnimallearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAnimallearnIconTA, activity,
						"menu_animal_learn.png", 0, 0, 2, 1);
		btAnimallearnIconTA.load();

		btMusicalinstlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btMusicalinstlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btMusicalinstlearnIconTA, activity,
						"menu_musicalinst_learn.png", 0, 0, 2, 1);
		btMusicalinstlearnIconTA.load();

		btFoodlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFoodlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFoodlearnIconTA, activity,
						"menu_food_learn.png", 0, 0, 2, 1);
		btFoodlearnIconTA.load();
		
		////
		
		btAlphabetplayIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btAlphabetplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAlphabetplayIconTA, activity,
						"menu_alphabet_play.png", 0, 0, 2, 1);
		btAlphabetplayIconTA.load();

		btNumbersplayIconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btNumbersplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btNumbersplayIconTA, activity,
						"menu_numbers_play.png", 0, 0, 2, 1);
		btNumbersplayIconTA.load();

		btColorplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btColorplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btColorplayIconTA, activity,
						"menu_colors_play.png", 0, 0, 2, 1);
		btColorplayIconTA.load();

		btAnimalplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btAnimalplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAnimalplayIconTA, activity,
						"menu_animal_play.png", 0, 0, 2, 1);
		btAnimalplayIconTA.load();

		btMusicalinstplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btMusicalinstplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btMusicalinstplayIconTA, activity,
						"menu_musicalinst_play.png", 0, 0, 2, 1);
		btMusicalinstplayIconTA.load();

		btFoodplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFoodplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFoodplayIconTA, activity,
						"menu_food_play.png", 0, 0, 2, 1);
		btFoodplayIconTA.load();
		
		btGame24IconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btGame24IconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btGame24IconTA, activity, "menu_game24.png",
						0, 0, 2, 1);
		btGame24IconTA.load();

	}

	public void unloadMainMenu24Resources() {
		unloadGeneralMainMenuResources();

		btAlphabetlearnIconTA.unload();
		btNumberslearnIconTA.unload();
		btColorlearnIconTA.unload();
		btAnimallearnIconTA.unload();
		btMusicalinstlearnIconTA.unload();
		btFoodlearnIconTA.unload();		
		
		
		btAlphabetplayIconTA.unload();
		btNumbersplayIconTA.unload();
		btColorplayIconTA.unload();
		btAnimalplayIconTA.unload();
		btMusicalinstplayIconTA.unload();
		btFoodplayIconTA.unload();	
		
		btGame24IconTA.unload();

	}

	public void loadMainMenu4Resources() {
		loadGeneralMainMenuResources();

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		btAnimallearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btAnimallearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAnimallearnIconTA, activity,
						"menu_animal_learn.png", 0, 0, 2, 1);
		btAnimallearnIconTA.load();

		btMusicalinstlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btMusicalinstlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btMusicalinstlearnIconTA, activity,
						"menu_musicalinst_learn.png", 0, 0, 2, 1);
		btMusicalinstlearnIconTA.load();

		btFoodlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFoodlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFoodlearnIconTA, activity,
						"menu_food_learn.png", 0, 0, 2, 1);
		btFoodlearnIconTA.load();

		btShapelearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btShapelearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btShapelearnIconTA, activity,
						"menu_shapes_learn.png", 0, 0, 2, 1);
		btShapelearnIconTA.load();

		btFlaglearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFlaglearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFlaglearnIconTA, activity,
						"menu_flags_learn.png", 0, 0, 2, 1);
		btFlaglearnIconTA.load();

		btTransportlearnIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btTransportlearnIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btTransportlearnIconTA, activity,
						"menu_transport_learn.png", 0, 0, 2, 1);
		btTransportlearnIconTA.load();
		
		///
		
		btAnimalplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btAnimalplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btAnimalplayIconTA, activity,
						"menu_animal_play.png", 0, 0, 2, 1);
		btAnimalplayIconTA.load();

		btMusicalinstplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btMusicalinstplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btMusicalinstplayIconTA, activity,
						"menu_musicalinst_play.png", 0, 0, 2, 1);
		btMusicalinstplayIconTA.load();

		btFoodplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFoodplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFoodplayIconTA, activity,
						"menu_food_play.png", 0, 0, 2, 1);
		btFoodplayIconTA.load();

		btShapeplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btShapeplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btShapeplayIconTA, activity,
						"menu_shapes_play.png", 0, 0, 2, 1);
		btShapeplayIconTA.load();

		btFlagplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btFlagplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btFlagplayIconTA, activity,
						"menu_flags_play.png", 0, 0, 2, 1);
		btFlagplayIconTA.load();

		btTransportplayIconTA = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		btTransportplayIconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btTransportplayIconTA, activity,
						"menu_transport_play.png", 0, 0, 2, 1);
		btTransportplayIconTA.load();
		
		btGame4IconTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		btGame4IconTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(btGame4IconTA, activity, "menu_game4.png",
						0, 0, 2, 1);
		btGame4IconTA.load();

	}

	public void unloadMainMenu4Resources() {
		unloadGeneralMainMenuResources();

		btAnimallearnIconTA.unload();
		btMusicalinstlearnIconTA.unload();
		btFoodlearnIconTA.unload();		
		btShapelearnIconTA.unload();
		btFlaglearnIconTA.unload();
		btTransportlearnIconTA.unload();
		
		btAnimalplayIconTA.unload();
		btMusicalinstplayIconTA.unload();
		btFoodplayIconTA.unload();		
		btShapeplayIconTA.unload();
		btFlagplayIconTA.unload();
		btTransportplayIconTA.unload();
		
		btGame4IconTA.unload();

	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD BALL GAME RESOURCES
	// ------------------------------------------------------------------------------------------------------------
	// ziritap game
	public void loadBallGameResources() {

		loadtaptapSounds("","","OK","OK","OK","OK","OK","OK","","OK","OK","OK","OK","OK",
				"OK","OK","OK","OK","OK","OK");
		
		loadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		loadMusics("", "", "OK","");

		String bgBack = "farm.png";
		String bgFrontTop = "";
		String bgFrontMid = "parallax_background_layer_mid.png";
		String bgBoard = "";
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);

		loadBallGameGfx();
		loadPitchouBalls();
		loadZiritapImagesResources();
		loadFonts();
		tts = new TextToSpeech(activity, this);
		loadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK", "OK",
				"OK");
		// -- 1.menu-2.home-3.homefreeze-4.pause-5.pausesmall-6.lang-7.music-
		// -- 8.musicmute-9.sound-10.soundmute

		loadexitWindowButtons("OK", "OK", "OK", "OK");
		loadStarParticles();
	}

	public void unloadBallGameResources() {
		unloadtaptapSounds("","","OK","","OK","OK","OK","OK","","OK","OK", 
				"OK","OK","OK","OK","OK","OK","OK","OK","OK");
		unloadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		unloadMusics("", "", "OK","");

		unloadBackground("");

		unloadBallGameGfx();
		unloadPitchouBalls();
		unloadZiritapImagesResources();
		unloadexitWindowButtons("OK", "OK", "OK", "OK");

		// mOnScreenControlTexture.unload();
		unloadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK",
				"OK", "OK");

		unloadFonts();
		unloadStarParticles();
	}
	
	private void loadZiritapImagesResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/ziritap/");
		/*
		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();
		
		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();*/

		//monkey
		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		dimg_003TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_003TA, activity,
						"img_003.png", 0, 0, 2, 2);
		img_003TA.load();
		//pig
		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_004TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_004TA, activity,
						"img_004.png", 0, 0, 2, 2);
		img_004TA.load();
		//horse
		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_005TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_005TA, activity,
						"img_005.png", 0, 0, 2, 2);
		img_005TA.load();
		//dog
		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_006TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_006TA, activity,
						"img_006.png", 0, 0, 2, 2);
		img_006TA.load();
		//cow
		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		dimg_007TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_007TA, activity,
						"img_007.png", 0, 0, 2, 2);
		img_007TA.load();
		
		//cat
		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_008TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_008TA, activity,
						"img_008.png", 0, 0, 2, 2);
		img_008TA.load();
		/*
		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();
		 */
		//bird
		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_010TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_010TA, activity,
						"img_010.png", 0, 0, 2, 2);
		img_010TA.load();
		/*
		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();*/
		//bird
		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		dimg_012TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_012TA, activity,
						"img_012.png", 0, 0, 2, 2);
		img_012TA.load();
		//hen
		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		dimg_013TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_013TA, activity,
						"img_013.png", 0, 0, 2, 2);
		img_013TA.load();
		//dolphin
		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_014TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_014TA, activity,
						"img_014.png", 0, 0, 2, 2);
		img_014TA.load();
		//sheep
		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_015TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_015TA, activity,
						"img_015.png", 0, 0, 2, 2);
		img_015TA.load();
		/*
		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();
		*/
		//wolf
		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		dimg_017TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_017TA, activity,
						"img_017.png", 0, 0, 2, 2);
		img_017TA.load();
		
		//rooster
		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_018TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_018TA, activity,
						"img_018.png", 0, 0, 2, 2);
		img_018TA.load();

		//donkey
		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				512);
		dimg_019TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_019TA, activity,
						"img_019.png", 0, 0, 2, 2);
		img_019TA.load();
		
		//bird
		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		dimg_020TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(img_020TA, activity,
						"img_020.png", 0, 0, 2, 2);
		img_020TA.load();
/*
		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();

		img_023TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_023TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_023TA, activity, "img_023.png", 0, 0);
		img_023TA.load();

		img_024TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_024TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_024TA, activity, "img_024.png", 0, 0);
		img_024TA.load();

		img_025TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_025TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_025TA, activity, "img_025.png", 0, 0);
		img_025TA.load();

		img_026TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_026TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_026TA, activity, "img_026.png", 0, 0);
		img_026TA.load();

		img_027TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_027TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_027TA, activity, "img_027.png", 0, 0);
		img_027TA.load();

		img_028TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_028TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_028TA, activity, "img_028.png", 0, 0);
		img_028TA.load();

		img_029TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_029TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_029TA, activity, "img_029.png", 0, 0);
		img_029TA.load();

		img_030TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_030TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_030TA, activity, "img_030.png", 0, 0);
		img_030TA.load();

		img_031TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_031TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_031TA, activity, "img_031.png", 0, 0);
		img_031TA.load();

		img_032TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_032TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_032TA, activity, "img_032.png", 0, 0);
		img_032TA.load();

		img_033TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_033TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_033TA, activity, "img_033.png", 0, 0);
		img_033TA.load();

		img_034TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_034TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_034TA, activity, "img_034.png", 0, 0);
		img_034TA.load();

		img_035TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_035TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_035TA, activity, "img_035.png", 0, 0);
		img_035TA.load();

		img_036TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_036TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_036TA, activity, "img_036.png", 0, 0);
		img_036TA.load();

		img_037TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_037TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_037TA, activity, "img_037.png", 0, 0);
		img_037TA.load();

		img_038TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_038TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_038TA, activity, "img_038.png", 0, 0);
		img_038TA.load();

		img_039TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_039TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_039TA, activity, "img_039.png", 0, 0);
		img_039TA.load();

		img_040TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_040TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_040TA, activity, "img_040.png", 0, 0);
		img_040TA.load();

		img_041TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_041TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_041TA, activity, "img_041.png", 0, 0);
		img_041TA.load();

		img_042TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_042TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_042TA, activity, "img_042.png", 0, 0);
		img_042TA.load();

		img_043TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_043TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_043TA, activity, "img_043.png", 0, 0);
		img_043TA.load();

		img_044TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_044TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_044TA, activity, "img_044.png", 0, 0);
		img_044TA.load();

		img_045TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_045TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_045TA, activity, "img_045.png", 0, 0);
		img_045TA.load();

		img_046TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_046TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_046TA, activity, "img_046.png", 0, 0);
		img_046TA.load();

		img_047TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_047TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_047TA, activity, "img_047.png", 0, 0);
		img_047TA.load();

		img_048TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_048TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_048TA, activity, "img_048.png", 0, 0);
		img_048TA.load();

		img_049TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_049TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_049TA, activity, "img_049.png", 0, 0);
		img_049TA.load();*/
	}

	private void unloadZiritapImagesResources() {

		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		/*img_021TA.unload();
		img_022TA.unload();
		img_023TA.unload();
		img_024TA.unload();
		img_025TA.unload();
		img_026TA.unload();
		img_027TA.unload();
		img_028TA.unload();
		img_029TA.unload();
		img_030TA.unload();
		img_031TA.unload();
		img_032TA.unload();
		img_033TA.unload();
		img_034TA.unload();
		img_035TA.unload();
		img_036TA.unload();
		img_037TA.unload();
		img_038TA.unload();
		img_039TA.unload();
		img_040TA.unload();
		img_041TA.unload();
		img_042TA.unload();
		img_043TA.unload();
		img_044TA.unload();
		img_045TA.unload();
		img_046TA.unload();
		img_047TA.unload();
		img_048TA.unload();
		img_049TA.unload();*/
	}	

	public void loadBallGame24Resources() {

		loadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		loadMusics("", "", "OK","");

		String bgBack = "parallax_background_layer_back.png";
		String bgFrontTop = "";
		String bgFrontMid = "parallax_background_layer_mid.png";
		String bgBoard = "";
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);

		loadBallGameGfx();

		loadFonts();

		tts = new TextToSpeech(activity, this);
		loadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK", "OK",
				"OK");
		// -- 1.menu-2.home-3.homefreeze-4.pause-5.pausesmall-6.lang-7.music-
		// -- 8.musicmute-9.sound-10.soundmute

		loadexitWindowButtons("OK", "OK", "OK", "OK");
		
		loadStarParticles();
		
	}
	


	public void unloadBallGame24Resources() {
		unloadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		unloadMusics("", "", "OK","");

		unloadBackground("");

		unloadBallGameGfx();

		unloadexitWindowButtons("OK", "OK", "OK", "OK");

		unloadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK",
				"OK", "OK");

		unloadFonts();
		unloadStarParticles();
	}

	public void loadBallGame4Resources() {

		loadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		loadMusics("", "", "OK","");

		String bgBack = "stadium.png";
		String bgFrontTop = "";
		String bgFrontMid = "";
		String bgBoard = "";
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);

		loadBallGameGfx();

		loadFonts();

		tts = new TextToSpeech(activity, this);
		loadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK", "OK",
				"OK");

		loadexitWindowButtons("OK", "OK", "OK", "OK");
		loadStarParticles();
	}

	public void unloadBallGame4Resources() {
		unloadSounds("OK", "OK", "OK", "OK", "OK", "OK", "OK","","OK");
		unloadMusics("", "", "OK","");

		unloadBackground("");

		unloadBallGameGfx();

		unloadexitWindowButtons("OK", "OK", "OK", "OK");

		unloadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "", "OK", "OK",
				"OK", "OK");

		unloadFonts();
		unloadStarParticles();
	}

	private void loadBallGameGfx() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/ball/");
		/*
		 * footballTA = new BitmapTextureAtlas(getTextureManager(),128,128);
		 * footballTR =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(footballTA
		 * ,GameActivity.this,"ball_foot_001.png",0,0,2,1); footballTA.load();
		 */
		// /////////////////////////////////////////////////////

		gymballgreenTA = new BitmapTextureAtlas(activity.getTextureManager(),
				128, 128);
		gymballgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(gymballgreenTA, activity,
						"gymballgreen.png", 0, 0, 2, 2); // 64x32
		gymballgreenTA.load();

		gymballredTA = new BitmapTextureAtlas(activity.getTextureManager(),
				128, 128);
		gymballredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(gymballredTA, activity, "gymballred.png",
						0, 0, 2, 2); // 64x32
		gymballredTA.load();

		footballTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		footballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(footballTA, activity, "football1.png", 0,
						0, 2, 2); // 64x32
		footballTA.load();

		football2TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		football2TR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(football2TA, activity, "football2.png",
						0, 0, 2, 2); // 64x32
		football2TA.load();

		tennisballTA = new BitmapTextureAtlas(activity.getTextureManager(),
				128, 128);
		tennisballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(tennisballTA, activity, "tennisball.png",
						0, 0, 2, 2); // 64x32 tennisballTA.load();
		tennisballTA.load();

		billardballTA = new BitmapTextureAtlas(activity.getTextureManager(),
				64, 64);
		billardballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(billardballTA, activity,
						"billardball.png", 0, 0, 2, 2); // 64x32
														// tennisballTA.load();
		billardballTA.load();

		volleyballTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		volleyballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(volleyballTA, activity, "volleyball.png",
						0, 0, 2, 2); // 64x32 tennisballTA.load();
		volleyballTA.load();

		beachballTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				512);
		beachballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(beachballTA, activity, "beachball.png",
						0, 0, 2, 4); // 64x32 tennisballTA.load();
		beachballTA.load();

		tchoupiballTA = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		tchoupiballTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(tchoupiballTA, activity,
						"tchoupiball.png", 0, 0, 2, 2); // 64x32
														// tennisballTA.load();
		tchoupiballTA.load();
		/*
		 * hole01ballTA = new
		 * BitmapTextureAtlas(activity.getTextureManager(),64,128); hole01ballTR
		 * =
		 * BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(hole01ballTA
		 * , activity, "hole_80_01.png", 0, 0, 2, 1); // 64x32
		 * tennisballTA.load(); hole01ballTA.load();
		 */
		netleftTA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		netleftTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(netleftTA, activity, "net_left.png", 0,
						0, 2, 1); // 64x32 tennisballTA.load();
		netleftTA.load();

		netrightTA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		netrightTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(netrightTA, activity, "net_right.png", 0,
						0, 2, 1); // 64x32 tennisballTA.load();
		netrightTA.load();

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shape/");

		// --stars

		starsTA = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256);
		starsTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
				starsTA, activity, "stars.png", 0, 0, 2, 2); // 64x32
																// tennisballTA.load();
		starsTA.load();
		/*
		 * littlestarTA = new
		 * BitmapTextureAtlas(activity.getTextureManager(),64,64); littlestarTR
		 * =
		 * BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(littlestarTA
		 * , activity, "little_star.png", 0, 0, 2, 1); // 64x32
		 * tennisballTA.load(); littlestarTA.load();
		 */

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/sport/");
		// -- keeper
		keeperleftTA = new BitmapTextureAtlas(activity.getTextureManager(), 64,
				64);
		keeperleftTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(keeperleftTA, activity,
						"keeper_left.png", 0, 0, 2, 1); // 64x32
														// tennisballTA.load();
		keeperleftTA.load();

		keeperrightTA = new BitmapTextureAtlas(activity.getTextureManager(),
				64, 64);
		keeperrightTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(keeperrightTA, activity,
						"keeper_right.png", 0, 0, 2, 1); // 64x32
															// tennisballTA.load();
		keeperrightTA.load();

		foot01leftTA = new BitmapTextureAtlas(activity.getTextureManager(),
				128, 256);
		foot01leftTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(foot01leftTA, activity,
						"player_left.png", 0, 0, 1, 2); // 64x32
															// tennisballTA.load();
		foot01leftTA.load();

		foot01rightTA = new BitmapTextureAtlas(activity.getTextureManager(),
				128, 256);
		foot01rightTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(foot01rightTA, activity,
						"player_right.png", 0, 0, 1, 2); // 64x32
																// tennisballTA.load();
		foot01rightTA.load();

		// -- control joystick
		/*
		 * this.mOnScreenControlTexture = new
		 * BitmapTextureAtlas(activity.getTextureManager(), 256, 128,
		 * TextureOptions.BILINEAR); this.mOnScreenControlBaseTR =
		 * BitmapTextureAtlasTextureRegionFactory
		 * .createFromAsset(this.mOnScreenControlTexture, activity,
		 * "onscreen_control_base.png", 0, 0); this.mOnScreenControlKnobTR =
		 * BitmapTextureAtlasTextureRegionFactory
		 * .createFromAsset(this.mOnScreenControlTexture, activity,
		 * "onscreen_control_knob.png", 128, 0);
		 * this.mOnScreenControlTexture.load();
		 */

		// -- another way to laod all texture atlas in the same time
		// this.engine.getTextureManager().loadTexture(this.footballTA,this.football2TA,...);
	}

	private void unloadBallGameGfx() {

		gymballgreenTA.load();
		gymballredTA.load();
		footballTA.load();
		football2TA.load();
		tennisballTA.load();
		billardballTA.load();
		volleyballTA.load();
		beachballTA.load();
		tchoupiballTA.load();
		netleftTA.load();
		netrightTA.load();
		starsTA.load();

		keeperleftTA.unload();
		keeperrightTA.unload();
		foot01leftTA.unload();
		foot01rightTA.unload();

	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD ALPHABET RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	public void loadAlphabetLearnResources() {

		BitmapTextureAtlasTextureRegionFactory
				.setAssetBasePath("gfx/alphabet/");
		// -- letters

		letterATA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterBTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterCTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterDTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterETA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterFTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterGTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterHTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterJTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterKTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterLTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterMTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterNTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterOTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterPTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterQTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterRTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterSTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterTTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterUTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterVTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterWTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterXTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterYTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		letterZTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);

		letterITA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);

		letterAyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterATA, activity,
						"letter_a_yellow.png", 0, 0, 2, 1);
		letterGyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterGTA, activity,
						"letter_g_yellow.png", 0, 0, 2, 1);
		letterMyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterMTA, activity,
						"letter_m_yellow.png", 0, 0, 2, 1);
		letterPyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterPTA, activity,
						"letter_p_yellow.png", 0, 0, 2, 1);
		letterSyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterSTA, activity,
						"letter_s_yellow.png", 0, 0, 2, 1);
		letterVyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterVTA, activity,
						"letter_v_yellow.png", 0, 0, 2, 1);

		letterBgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterBTA, activity,
						"letter_b_green.png", 0, 0, 2, 1);
		letterHgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterHTA, activity,
						"letter_h_green.png", 0, 0, 2, 1);
		letterJgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterJTA, activity,
						"letter_j_green.png", 0, 0, 2, 1);
		letterNgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterNTA, activity,
						"letter_n_green.png", 0, 0, 2, 1);
		letterRgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterRTA, activity,
						"letter_r_green.png", 0, 0, 2, 1);
		letterXgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterXTA, activity,
						"letter_x_green.png", 0, 0, 2, 1);

		letterCblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterCTA, activity, "letter_c_blue.png",
						0, 0, 2, 1);
		letterIblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterITA, activity, "letter_i_blue.png",
						0, 0, 2, 1);
		letterTblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterTTA, activity, "letter_t_blue.png",
						0, 0, 2, 1);
		letterYblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterYTA, activity, "letter_y_blue.png",
						0, 0, 2, 1);

		letterDpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterDTA, activity,
						"letter_d_purple.png", 0, 0, 2, 1);
		letterKpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterKTA, activity,
						"letter_k_purple.png", 0, 0, 2, 1);
		letterQpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterQTA, activity,
						"letter_q_purple.png", 0, 0, 2, 1);

		letterEredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterETA, activity, "letter_e_red.png",
						0, 0, 2, 1);
		letterLredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterLTA, activity, "letter_l_red.png",
						0, 0, 2, 1);
		letterOredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterOTA, activity, "letter_o_red.png",
						0, 0, 2, 1);
		letterUredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterUTA, activity, "letter_u_red.png",
						0, 0, 2, 1);
		letterZredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterZTA, activity, "letter_z_red.png",
						0, 0, 2, 1);

		letterForangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterFTA, activity,
						"letter_f_orange.png", 0, 0, 2, 1);
		letterWorangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterWTA, activity,
						"letter_w_orange.png", 0, 0, 2, 1);

		letterAyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterATA, activity,
						"letter_a_yellow.png", 0, 0, 2, 1);
		letterGyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterGTA, activity,
						"letter_g_yellow.png", 0, 0, 2, 1);
		letterMyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterMTA, activity,
						"letter_m_yellow.png", 0, 0, 2, 1);
		letterPyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterPTA, activity,
						"letter_p_yellow.png", 0, 0, 2, 1);
		letterSyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterSTA, activity,
						"letter_s_yellow.png", 0, 0, 2, 1);
		letterVyellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterVTA, activity,
						"letter_v_yellow.png", 0, 0, 2, 1);

		letterBgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterBTA, activity,
						"letter_b_green.png", 0, 0, 2, 1);
		letterHgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterHTA, activity,
						"letter_h_green.png", 0, 0, 2, 1);
		letterJgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterJTA, activity,
						"letter_j_green.png", 0, 0, 2, 1);
		letterNgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterNTA, activity,
						"letter_n_green.png", 0, 0, 2, 1);
		letterRgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterRTA, activity,
						"letter_r_green.png", 0, 0, 2, 1);
		letterXgreenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterXTA, activity,
						"letter_x_green.png", 0, 0, 2, 1);

		letterCblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterCTA, activity, "letter_c_blue.png",
						0, 0, 2, 1);
		letterIblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterITA, activity, "letter_i_blue.png",
						0, 0, 2, 1);
		letterTblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterTTA, activity, "letter_t_blue.png",
						0, 0, 2, 1);
		letterYblueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterYTA, activity, "letter_y_blue.png",
						0, 0, 2, 1);

		letterDpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterDTA, activity,
						"letter_d_purple.png", 0, 0, 2, 1);
		letterKpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterKTA, activity,
						"letter_k_purple.png", 0, 0, 2, 1);
		letterQpurpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterQTA, activity,
						"letter_q_purple.png", 0, 0, 2, 1);

		letterEredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterETA, activity, "letter_e_red.png",
						0, 0, 2, 1);
		letterLredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterLTA, activity, "letter_l_red.png",
						0, 0, 2, 1);
		letterOredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterOTA, activity, "letter_o_red.png",
						0, 0, 2, 1);
		letterUredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterUTA, activity, "letter_u_red.png",
						0, 0, 2, 1);
		letterZredTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterZTA, activity, "letter_z_red.png",
						0, 0, 2, 1);

		letterForangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterFTA, activity,
						"letter_f_orange.png", 0, 0, 2, 1);
		letterWorangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(letterWTA, activity,
						"letter_w_orange.png", 0, 0, 2, 1);

		// engine.getTextureManager().loadTexture(this.letterTA,this.letterThinTA,this.letterWideTextureAtlas);
		letterATA.load();
		letterBTA.load();
		letterCTA.load();
		letterDTA.load();
		letterETA.load();
		letterFTA.load();
		letterGTA.load();
		letterHTA.load();
		letterJTA.load();
		letterKTA.load();
		letterLTA.load();
		letterMTA.load();
		letterNTA.load();
		letterOTA.load();
		letterPTA.load();
		letterQTA.load();
		letterRTA.load();
		letterSTA.load();
		letterTTA.load();
		letterUTA.load();
		letterVTA.load();
		letterXTA.load();
		letterYTA.load();
		letterZTA.load();
		letterITA.load();
		letterWTA.load();

		// -----------------------------------------------------------------------------

		loadGeneralLearnResources("background_alphabet_back.png","","","blackboard_800_400.png");
		/*
		loadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");
		loadArrowButtons("OK", "OK");
		loadSounds("", "", "", "", "", "OK", "","");
		
		String bgBack = "background_alphabet_back.png";
		String bgFrontTop = "";
		String bgFrontMid = "";
		String bgBoard = "blackboard_800_400.png";
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);
		// -------------------------------------------------------------------------------
		loadFonts();
		// -- text to speech
		tts = new TextToSpeech(activity, this);
		*/
		// -------------------------------------------------------------------------------
	}

	public void unloadAlphabetLearnResources() {

		letterATA.unload();
		letterBTA.unload();
		letterCTA.unload();
		letterDTA.unload();
		letterETA.unload();
		letterFTA.unload();
		letterGTA.unload();
		letterHTA.unload();
		letterITA.unload();
		letterJTA.unload();
		letterKTA.unload();
		letterLTA.unload();
		letterMTA.unload();
		letterNTA.unload();
		letterOTA.unload();
		letterPTA.unload();
		letterQTA.unload();
		letterRTA.unload();
		letterSTA.unload();
		letterTTA.unload();
		letterUTA.unload();
		letterVTA.unload();
		letterWTA.unload();
		letterXTA.unload();
		letterYTA.unload();
		letterZTA.unload();

		unloadGeneralLearnResources();
		/*
		unloadBackground("OK");
		unloadArrowButtons("OK", "OK");

		unloadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");
		*/
	}

	// ------------------------------------------------------------------------------------------------------------
	// LOAD NUMBER RESOURCES
	// ------------------------------------------------------------------------------------------------------------

	public void loadNumberLearnResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/number/");
		// -- numbers
		number00TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number01TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		number02TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number03TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number04TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number05TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number06TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number07TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number08TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number09TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		number10TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);

		number01yellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number01TA, activity,
						"number_01_yellow.png", 0, 0, 2, 1);
		number06yellowTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number06TA, activity,
						"number_06_yellow.png", 0, 0, 2, 1);

		number02greenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number02TA, activity,
						"number_02_green.png", 0, 0, 2, 1);
		number08greenTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number08TA, activity,
						"number_08_green.png", 0, 0, 2, 1);

		number04blueTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number04TA, activity,
						"number_04_blue.png", 0, 0, 2, 1);

		number05purpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number05TA, activity,
						"number_05_purple.png", 0, 0, 2, 1);
		number10purpleTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number10TA, activity,
						"number_10_purple.png", 0, 0, 2, 1);

		number00redTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number00TA, activity,
						"number_00_red.png", 0, 0, 2, 1);
		number07redTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number07TA, activity,
						"number_07_red.png", 0, 0, 2, 1);

		number03orangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number03TA, activity,
						"number_03_orange.png", 0, 0, 2, 1);
		number09orangeTR = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(number09TA, activity,
						"number_09_orange.png", 0, 0, 2, 1);

		number00TA.load();
		number01TA.load();
		number02TA.load();
		number03TA.load();
		number04TA.load();
		number05TA.load();
		number06TA.load();
		number07TA.load();
		number08TA.load();
		number09TA.load();
		number10TA.load();

		loadGeneralLearnResources("background_alphabet_back.png","","","blackboard_800_400.png");

		/*
		loadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");

		loadArrowButtons("OK", "OK");

		loadSounds("", "", "", "", "", "OK", "","");

		loadFonts();

		String bgBack = "background_alphabet_back.png";
		String bgFrontTop = "";
		String bgFrontMid = "";
		String bgBoard = "blackboard_800_400.png";
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);
		// -----------------------------------------------------------------------------

		// -- text to speech
		tts = new TextToSpeech(activity, this);

		// -----------------------------------------------------------------------------
		*/
	}

	public void unloadNumberLearnResources() {

		number00TA.unload();
		number01TA.unload();
		number02TA.unload();
		number03TA.unload();
		number04TA.unload();
		number05TA.unload();
		number06TA.unload();
		number07TA.unload();
		number08TA.unload();
		number09TA.unload();
		number10TA.unload();
		unloadGeneralLearnResources();
		/*
		unloadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");

		unloadArrowButtons("OK", "OK");
		unloadBackground("OK");
		unloadFonts();
		*/
	}

	//---------------------------------------------------------------------------------------
	//	GENERAL LEARN
	//---------------------------------------------------------------------------------------

	public void loadGeneralLearnResources(String ibgBack,String ibgFrontTop,String ibgFrontMid,String ibgBoard) {
		loadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");
		loadArrowButtons("OK", "OK");
		loadSounds("", "", "", "", "", "OK", "","","");
		loadFonts();

		String bgBack = ibgBack;
		String bgFrontTop = ibgFrontTop;
		String bgFrontMid = ibgFrontMid;
		String bgBoard = ibgBoard;
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);
		tts = new TextToSpeech(activity, this);		
	}	
	
	public void unloadGeneralLearnResources() {	
		unloadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");		
		unloadArrowButtons("OK", "OK");
		unloadSounds("", "", "", "", "", "", "","","");
		unloadFonts();
		unloadBackground("");		
	}
	
	//---------------------------------------------------------------------------------------
	//	ANIMAL LEARN
	//---------------------------------------------------------------------------------------
	
	public void loadAnimalLearnResources() {

		/*
		 * //-- animated butterfly_001TA = new
		 * BitmapTextureAtlas(activity.getTextureManager(),512,128);
		 * butterfly_001TR =
		 * BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset
		 * (butterfly_001TA,activity,"butterfly_001.png",0,0,2,1);
		 * butterfly_001TA.load();
		 */

		loadAnimalImagesResources();
		loadGeneralLearnResources("background_back.png","","","");

	}

	private void loadAnimalImagesResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/animal/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();

		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();

		img_023TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_023TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_023TA, activity, "img_023.png", 0, 0);
		img_023TA.load();

		img_024TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_024TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_024TA, activity, "img_024.png", 0, 0);
		img_024TA.load();

		img_025TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_025TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_025TA, activity, "img_025.png", 0, 0);
		img_025TA.load();

		img_026TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_026TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_026TA, activity, "img_026.png", 0, 0);
		img_026TA.load();

		img_027TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_027TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_027TA, activity, "img_027.png", 0, 0);
		img_027TA.load();

		img_028TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_028TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_028TA, activity, "img_028.png", 0, 0);
		img_028TA.load();

		img_029TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_029TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_029TA, activity, "img_029.png", 0, 0);
		img_029TA.load();

		img_030TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_030TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_030TA, activity, "img_030.png", 0, 0);
		img_030TA.load();

		img_031TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_031TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_031TA, activity, "img_031.png", 0, 0);
		img_031TA.load();

		img_032TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_032TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_032TA, activity, "img_032.png", 0, 0);
		img_032TA.load();

		img_033TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_033TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_033TA, activity, "img_033.png", 0, 0);
		img_033TA.load();

		img_034TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_034TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_034TA, activity, "img_034.png", 0, 0);
		img_034TA.load();

		img_035TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_035TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_035TA, activity, "img_035.png", 0, 0);
		img_035TA.load();

		img_036TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_036TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_036TA, activity, "img_036.png", 0, 0);
		img_036TA.load();

		img_037TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_037TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_037TA, activity, "img_037.png", 0, 0);
		img_037TA.load();

		img_038TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_038TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_038TA, activity, "img_038.png", 0, 0);
		img_038TA.load();

		img_039TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_039TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_039TA, activity, "img_039.png", 0, 0);
		img_039TA.load();

		img_040TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_040TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_040TA, activity, "img_040.png", 0, 0);
		img_040TA.load();

		img_041TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_041TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_041TA, activity, "img_041.png", 0, 0);
		img_041TA.load();

		img_042TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_042TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_042TA, activity, "img_042.png", 0, 0);
		img_042TA.load();

		img_043TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_043TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_043TA, activity, "img_043.png", 0, 0);
		img_043TA.load();

		img_044TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_044TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_044TA, activity, "img_044.png", 0, 0);
		img_044TA.load();

		img_045TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_045TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_045TA, activity, "img_045.png", 0, 0);
		img_045TA.load();

		img_046TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_046TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_046TA, activity, "img_046.png", 0, 0);
		img_046TA.load();

		img_047TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_047TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_047TA, activity, "img_047.png", 0, 0);
		img_047TA.load();

		img_048TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_048TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_048TA, activity, "img_048.png", 0, 0);
		img_048TA.load();

		img_049TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_049TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_049TA, activity, "img_049.png", 0, 0);
		img_049TA.load();
	}

	private void unloadAnimalImagesResources() {

		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		img_021TA.unload();
		img_022TA.unload();
		img_023TA.unload();
		img_024TA.unload();
		img_025TA.unload();
		img_026TA.unload();
		img_027TA.unload();
		img_028TA.unload();
		img_029TA.unload();
		img_030TA.unload();
		img_031TA.unload();
		img_032TA.unload();
		img_033TA.unload();
		img_034TA.unload();
		img_035TA.unload();
		img_036TA.unload();
		img_037TA.unload();
		img_038TA.unload();
		img_039TA.unload();
		img_040TA.unload();
		img_041TA.unload();
		img_042TA.unload();
		img_043TA.unload();
		img_044TA.unload();
		img_045TA.unload();
		img_046TA.unload();
		img_047TA.unload();
		img_048TA.unload();
		img_049TA.unload();
	}	
	public void unloadAnimalLearnResources() {
		
		unloadAnimalImagesResources();
		unloadGeneralLearnResources();
		/*
		unloadBackground("");
		unloadArrowButtons("OK", "OK");
		unloadMenuBarButtons("OK", "OK", "OK", "", "", "OK", "", "", "OK", "OK");
		unloadFonts();
		*/
	}

	//---------------------------------------------------------------------------------------
	//	COLOR LEARN
	//---------------------------------------------------------------------------------------

	public void loadColorLearnResources() {

		loadColorImagesResources();
		loadGeneralLearnResources("background_back.png","","","");

	}

	private void loadColorImagesResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/color/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();
	}

	private void unloadColorImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();		
	}

	public void unloadColorLearnResources() {
	
		unloadColorImagesResources();
		unloadGeneralLearnResources();

	}

	//---------------------------------------------------------------------------------------
	//	SHAPE LEARN
	//---------------------------------------------------------------------------------------

	public void loadShapeLearnResources() {

		loadShapeImagesResources();
		loadGeneralLearnResources("background_back.png","","","");

	}

	private void loadShapeImagesResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shape/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();
	}

	private void unloadShapeImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
	}

	public void unloadShapeLearnResources() {
	
		unloadShapeImagesResources();
		unloadGeneralLearnResources();

	}
	//---------------------------------------------------------------------------------------
	//	OBJECT LEARN
	//---------------------------------------------------------------------------------------

	public void loadObjectLearnResources() {

	}

	public void unloadObjectLearnResources() {

	}

	//---------------------------------------------------------------------------------------
	//	TRANSPORT LEARN
	//---------------------------------------------------------------------------------------


	public void loadTransportLearnResources() {

		loadTransportImagesResources();
		loadGeneralLearnResources("background_back_white.png","","","");

	}

	private void loadTransportImagesResources() {
		BitmapTextureAtlasTextureRegionFactory
				.setAssetBasePath("gfx/transport/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();
	}

	private void unloadTransportImagesResources(){
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
	}
	public void unloadTransportLearnResources() {
		

		unloadTransportImagesResources();
		unloadGeneralLearnResources();

	}
	//---------------------------------------------------------------------------------------
	//	FLAG LEARN
	//---------------------------------------------------------------------------------------

	public void loadFlagLearnResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/flag/");

		loadFlagImagesResources();
		loadGeneralLearnResources("background_back.png","","","");

	}

	private void loadFlagImagesResources() {
		BitmapTextureAtlasTextureRegionFactory
		.setAssetBasePath("gfx/flag/");
		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();

		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();

		img_023TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_023TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_023TA, activity, "img_023.png", 0, 0);
		img_023TA.load();

		img_024TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_024TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_024TA, activity, "img_024.png", 0, 0);
		img_024TA.load();

		img_025TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_025TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_025TA, activity, "img_025.png", 0, 0);
		img_025TA.load();

		img_026TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_026TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_026TA, activity, "img_026.png", 0, 0);
		img_026TA.load();

		img_027TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_027TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_027TA, activity, "img_027.png", 0, 0);
		img_027TA.load();

		img_028TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_028TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_028TA, activity, "img_028.png", 0, 0);
		img_028TA.load();

		img_029TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_029TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_029TA, activity, "img_029.png", 0, 0);
		img_029TA.load();

		img_030TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_030TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_030TA, activity, "img_030.png", 0, 0);
		img_030TA.load();

		img_031TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_031TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_031TA, activity, "img_031.png", 0, 0);
		img_031TA.load();

		img_032TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_032TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_032TA, activity, "img_032.png", 0, 0);
		img_032TA.load();

		img_033TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_033TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_033TA, activity, "img_033.png", 0, 0);
		img_033TA.load();

		img_034TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_034TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_034TA, activity, "img_034.png", 0, 0);
		img_034TA.load();

		img_035TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_035TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_035TA, activity, "img_035.png", 0, 0);
		img_035TA.load();

		img_036TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_036TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_036TA, activity, "img_036.png", 0, 0);
		img_036TA.load();

		img_037TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_037TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_037TA, activity, "img_037.png", 0, 0);
		img_037TA.load();

		img_038TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_038TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_038TA, activity, "img_038.png", 0, 0);
		img_038TA.load();

		img_039TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_039TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_039TA, activity, "img_039.png", 0, 0);
		img_039TA.load();

		img_040TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_040TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_040TA, activity, "img_040.png", 0, 0);
		img_040TA.load();

		img_041TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_041TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_041TA, activity, "img_041.png", 0, 0);
		img_041TA.load();

		img_042TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_042TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_042TA, activity, "img_042.png", 0, 0);
		img_042TA.load();

		img_043TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_043TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_043TA, activity, "img_043.png", 0, 0);
		img_043TA.load();

		img_044TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_044TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_044TA, activity, "img_044.png", 0, 0);
		img_044TA.load();

		img_045TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_045TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_045TA, activity, "img_045.png", 0, 0);
		img_045TA.load();

		img_046TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_046TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_046TA, activity, "img_046.png", 0, 0);
		img_046TA.load();

		img_047TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_047TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_047TA, activity, "img_047.png", 0, 0);
		img_047TA.load();

		img_048TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_048TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_048TA, activity, "img_048.png", 0, 0);
		img_048TA.load();

		img_049TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_049TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_049TA, activity, "img_049.png", 0, 0);
		img_049TA.load();

		img_050TA = new BitmapTextureAtlas(activity.getTextureManager(), 512,
				256);
		img_050TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_050TA, activity, "img_050.png", 0, 0);
		img_050TA.load();

	}

	private void unloadFlagImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		img_021TA.unload();
		img_022TA.unload();
		img_023TA.unload();
		img_024TA.unload();
		img_025TA.unload();
		img_026TA.unload();
		img_027TA.unload();
		img_028TA.unload();
		img_029TA.unload();
		img_030TA.unload();
		img_031TA.unload();
		img_032TA.unload();
		img_033TA.unload();
		img_034TA.unload();
		img_035TA.unload();
		img_036TA.unload();
		img_037TA.unload();
		img_038TA.unload();
		img_039TA.unload();
		img_040TA.unload();
		img_041TA.unload();
		img_042TA.unload();
		img_043TA.unload();
		img_044TA.unload();
		img_045TA.unload();
		img_046TA.unload();
		img_047TA.unload();
		img_048TA.unload();
		img_049TA.unload();
		img_050TA.unload();		
		
	}
	public void unloadFlagLearnResources() {

		unloadFlagImagesResources();
		unloadGeneralLearnResources();
		
	}
	
	//---------------------------------------------------------------------------------------
	//	FOOD LEARN
	//---------------------------------------------------------------------------------------

	public void loadFoodLearnResources() {

		loadFoodImagesResources();
		loadGeneralLearnResources("background_back_white.png","","","");

	}

	private void loadFoodImagesResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/food/");
		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();

		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();

		img_023TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_023TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_023TA, activity, "img_023.png", 0, 0);
		img_023TA.load();

		img_024TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_024TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_024TA, activity, "img_024.png", 0, 0);
		img_024TA.load();

		img_025TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_025TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_025TA, activity, "img_025.png", 0, 0);
		img_025TA.load();

		img_026TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				128);
		img_026TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_026TA, activity, "img_026.png", 0, 0);
		img_026TA.load();

		img_027TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_027TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_027TA, activity, "img_027.png", 0, 0);
		img_027TA.load();

		img_028TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_028TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_028TA, activity, "img_028.png", 0, 0);
		img_028TA.load();
		
		
	}

	private void unloadFoodImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		img_021TA.unload();
		img_022TA.unload();
		img_023TA.unload();
		img_024TA.unload();
		img_025TA.unload();
		img_026TA.unload();
		img_027TA.unload();
		img_028TA.unload();
	}	
	public void unloadFoodLearnResources() {

		/*
		 * img_029TA.unload(); img_030TA.unload(); img_031TA.unload();
		 * img_032TA.unload(); img_033TA.unload(); img_034TA.unload();
		 * img_035TA.unload(); img_036TA.unload(); img_037TA.unload();
		 * img_038TA.unload(); img_039TA.unload(); img_040TA.unload();
		 * img_041TA.unload(); img_042TA.unload(); img_043TA.unload();
		 * img_044TA.unload(); img_045TA.unload(); img_046TA.unload();
		 * img_047TA.unload(); img_048TA.unload();
		 */
		unloadFoodImagesResources();
		unloadGeneralLearnResources();

	}

	//---------------------------------------------------------------------------------------
	//	MUSICAL INSTRUMENTS LEARN
	//---------------------------------------------------------------------------------------

	public void loadMusicinstrLearnResources() {

		loadMusicinstrImagesResources();
		loadGeneralLearnResources("background_back.png","","","");

	}

	private void loadMusicinstrImagesResources() {
		BitmapTextureAtlasTextureRegionFactory
				.setAssetBasePath("gfx/musicalinst/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();

		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();
	}


	private void unloadMusicinstrImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		img_021TA.unload();
		img_022TA.unload();
		
	}
	public void unloadMusicinstrLearnResources() {


		unloadMusicinstrImagesResources();
		unloadGeneralLearnResources();

	}
	//---------------------------------------------------------------------------------------
	//	CAPITALS LEARN
	//---------------------------------------------------------------------------------------
	public void loadCapitalLearnResources() {

	}
	public void unloadCapitalLearnResources() {

	}


	
	
	//---------------------------------------------------------------------------------------
	//	SOUNDS LEARN
	//---------------------------------------------------------------------------------------

	
	public void loadSoundsLearnResources() {

	}
	public void unloadSoundsLearnResources() {

	}
	
//---------------------------------------------------------------------------------------
//								GAMES
//---------------------------------------------------------------------------------------

	public void loadGeneralGameResources(String ibgBack,String ibgFrontTop,String ibgFrontMid,String ibgBoard) {
		loadTrueFalseImg();
		loadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK",
				"OK", "OK");
		loadArrowButtons("OK", "");
		loadSounds("", "", "", "OK", "", "OK", "OK","OK","OK");
		loadFonts();

		String bgBack = ibgBack;
		String bgFrontTop = ibgFrontTop;
		String bgFrontMid = ibgFrontMid;
		String bgBoard = ibgBoard;
		loadBackground(1024, 1024, bgBack, bgFrontTop, bgFrontMid, 1024, 512,
				bgBoard);
		// -- text to speech
		tts = new TextToSpeech(activity, this);
		loadexitWindowButtons("OK", "OK", "OK", "OK");
		
	}
	public void unloadGeneralGameResources() {
		unloadTrueFalseImg();
		unloadMenuBarButtons("OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK",
				"OK", "OK");
		unloadArrowButtons("OK", "");
		unloadSounds("", "", "", "OK", "", "", "OK","OK","OK");
		unloadFonts();		
		unloadBackground("OK");
		unloadexitWindowButtons("OK", "OK", "OK", "OK");	
		
	}
	
	public void loadAnimalGameResources() {

		loadAnimalImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	
	}

	public void unloadAnimalGameResources() {
		unloadGeneralGameResources();
		unloadAnimalImagesResources();
	
	}
	
	public void loadAlphabetGameResources() {

		loadAlphabetImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}
	
	public void loadAlphabetImagesResources() {
	
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/alphabet/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();

		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();

		img_012TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_012TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_012TA, activity, "img_012.png", 0, 0);
		img_012TA.load();

		img_013TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_013TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_013TA, activity, "img_013.png", 0, 0);
		img_013TA.load();

		img_014TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_014TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_014TA, activity, "img_014.png", 0, 0);
		img_014TA.load();

		img_015TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_015TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_015TA, activity, "img_015.png", 0, 0);
		img_015TA.load();

		img_016TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_016TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_016TA, activity, "img_016.png", 0, 0);
		img_016TA.load();

		img_017TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_017TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_017TA, activity, "img_017.png", 0, 0);
		img_017TA.load();

		img_018TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_018TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_018TA, activity, "img_018.png", 0, 0);
		img_018TA.load();

		img_019TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_019TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_019TA, activity, "img_019.png", 0, 0);
		img_019TA.load();

		img_020TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_020TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_020TA, activity, "img_020.png", 0, 0);
		img_020TA.load();

		img_021TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_021TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_021TA, activity, "img_021.png", 0, 0);
		img_021TA.load();

		img_022TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_022TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_022TA, activity, "img_022.png", 0, 0);
		img_022TA.load();

		img_023TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_023TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_023TA, activity, "img_023.png", 0, 0);
		img_023TA.load();

		img_024TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_024TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_024TA, activity, "img_024.png", 0, 0);
		img_024TA.load();

		img_025TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_025TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_025TA, activity, "img_025.png", 0, 0);
		img_025TA.load();

		img_026TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				256);
		img_026TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_026TA, activity, "img_026.png", 0, 0);
		img_026TA.load();
		
	}	
	
	public void unloadAlphabetImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();
		img_011TA.unload();
		img_012TA.unload();
		img_013TA.unload();
		img_014TA.unload();
		img_015TA.unload();
		img_016TA.unload();
		img_017TA.unload();
		img_018TA.unload();
		img_019TA.unload();
		img_020TA.unload();
		img_021TA.unload();
		img_022TA.unload();
		img_023TA.unload();
		img_024TA.unload();
		img_025TA.unload();
		img_026TA.unload();			
	}		
	
	public void unloadAlphabetGameResources() {

		unloadAlphabetImagesResources();
		unloadGeneralGameResources();

	}

	public void loadNumberGameResources() {

		loadNumberImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}

	public void loadNumberImagesResources() {
	
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/number/");

		img_001TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_001TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_001TA, activity, "img_001.png", 0, 0);
		img_001TA.load();

		img_002TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_002TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_002TA, activity, "img_002.png", 0, 0);
		img_002TA.load();

		img_003TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_003TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_003TA, activity, "img_003.png", 0, 0);
		img_003TA.load();

		img_004TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_004TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_004TA, activity, "img_004.png", 0, 0);
		img_004TA.load();

		img_005TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_005TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_005TA, activity, "img_005.png", 0, 0);
		img_005TA.load();

		img_006TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_006TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_006TA, activity, "img_006.png", 0, 0);
		img_006TA.load();

		img_007TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_007TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_007TA, activity, "img_007.png", 0, 0);
		img_007TA.load();

		img_008TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_008TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_008TA, activity, "img_008.png", 0, 0);
		img_008TA.load();

		img_009TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_009TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_009TA, activity, "img_009.png", 0, 0);
		img_009TA.load();

		img_010TA = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128);
		img_010TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_010TA, activity, "img_010.png", 0, 0);
		img_010TA.load();
		
		img_011TA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);
		img_011TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				img_011TA, activity, "img_011.png", 0, 0);
		img_011TA.load();
		
	}	
	
	public void unloadNumberImagesResources() {
		img_001TA.unload();
		img_002TA.unload();
		img_003TA.unload();
		img_004TA.unload();
		img_005TA.unload();
		img_006TA.unload();
		img_007TA.unload();
		img_008TA.unload();
		img_009TA.unload();
		img_010TA.unload();	
		img_011TA.unload();
	}	
	
	public void unloadNumberGameResources() {

		unloadNumberImagesResources();
		unloadGeneralGameResources();

	}

	public void loadColorGameResources() {

		loadColorImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}

	public void unloadColorGameResources() {

		unloadGeneralGameResources();
		unloadColorImagesResources();

	}


	public void loadShapeGameResources() {

		loadShapeImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}
	
	public void unloadShapeGameResources() {

		unloadShapeImagesResources();
		unloadGeneralGameResources();

	}
	
	public void loadObjectGameResources() {

	}

	public void unloadObjectGameResources() {

	}


	public void loadTransportGameResources() {
		loadTransportImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}

	public void unloadTransportGameResources() {
		unloadTransportImagesResources();
		unloadGeneralGameResources();
	}



	

	public void loadFoodGameResources() {
		loadFoodImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofswhite.png");
	}

	public void unloadFoodGameResources() {
		unloadFoodImagesResources();
		unloadGeneralGameResources();
	}
	
	public void loadMusicinstrGameResources() {
		loadMusicinstrImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}

	public void unloadMusicinstrGameResources() {
		unloadMusicinstrImagesResources();
		unloadGeneralGameResources();
	}





	public void loadSoundsGameResources() {

	}
	
	public void unloadSoundsGameResources() {

	}

	
	public void loadFlagGameResources() {
		loadFlagImagesResources();
		loadGeneralGameResources("bglearn.png","","","4tofsblack.png");
	}

	public void unloadFlagGameResources() {
		unloadFlagImagesResources();
		unloadGeneralGameResources();
	}


	
	public void loadCapitalGameResources() {

	}
	
	public void unloadCapitalGameResources() {

	}






	public void pauseMusic() {
		try {

			if (ResourcesManager.mMusicFingerFamily.isPlaying()) {
				ResourcesManager.mMusicFingerFamily.release();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	public static void prepareManager(Engine engine, GameActivity activity,
			Camera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}

	// ---------------------------------------------
	// GETTERS AND SETTERS
	// ---------------------------------------------

	public static ResourcesManager getInstance() {
		return INSTANCE;
	}

	// ------------------------------------------------------------------------------------------------------------
	// INIT LANGUAGE
	// ------------------------------------------------------------------------------------------------------------

	public int initLanguage() {
		// -------------------------------------------------------------------
		prefLanguage = ((GameActivity) activity).getPreference("language", "1");
		if (prefLanguage.contentEquals("1")) {
			// resourcesManager.tts.setLanguage(Locale.FRENCH);
			return 0;
		} else if (prefLanguage.contentEquals("2")) {
			// resourcesManager.tts.setLanguage(Locale.ENGLISH);
			return 1;
		} else {
			return 0;
		}
	}

	// -----------------------------------------------------------------------------------

	public boolean initSounds() {
		// ---------------------------------------------------------------------
		try {

			prefGameSounds = ((GameActivity) activity).getPreference(
					"gamesound", true);
			if (prefGameSounds == false) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// -----------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------
	// INIT PROFILE
	// ------------------------------------------------------------------------------------------------------------

	public void setProfile(int idprofile) {
		idProfile = idprofile;
	}

	public int getProfile() {
		return idProfile;
	}

	public void setIdMenu(int idmenu) {
		idMenu = idmenu;
	}

	public int getIdMenu() {
		return idMenu;
	}

	@Override
	public void onInit(int status) {

		if (status != TextToSpeech.ERROR) {

			try {
				prefLanguage = ((GameActivity) activity).getPreference(
						"language", "1");
				if (prefLanguage.contentEquals("1")) {
					tts.setLanguage(Locale.FRENCH);
				} else if (prefLanguage.contentEquals("2")) {
					tts.setLanguage(Locale.ENGLISH);
				} else {
					tts.setLanguage(Locale.FRENCH);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

}
