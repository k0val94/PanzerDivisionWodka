//Anton Koval
//Projekt Arbeit 2013
//Panzer Division: Wodka
//HBFIT 12



package PDW;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class BoardT extends JFrame implements ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private BufferedImage offImg;
	private Graphics bufferedgraphics;
	private final int ALL_PX = 1664;

	//
	public int anzstuetzpunkt = 2;
	public int anzspieler = 2;
	public boolean onevsone = true;
	//
	public int hilfanzspieler = 0;
	public int statleiste = 25;
	private int anzziegel = 400;
	public int anzgegner = 1;
	public int win = 0;
	private int anzbusch = 500;
	private int anzfluss = 200;
	private int anzstahlwand = 200;
	private int anzeis = 50;
	private int level = 1;

	private int dotsize = 20; // statvariable für Item Spawner
	private int itemsize = 20;
	private int itemzahl = 0;
	private int zufallrichtug[] = new int[anzgegner];
	private int hilfzufallrichtug[] = new int[anzgegner];
	public Timer timer;
	// Bilder Tank
	public Image tankup[] = new Image[anzspieler];
	public Image tankdown[] = new Image[anzspieler];
	public Image tankleft[] = new Image[anzspieler];
	public Image tankright[] = new Image[anzspieler];
	public Image symbolgegner[] = new Image[anzgegner];

	public Image stuetzpunkt;
	public Image stuetzpunkt2;
	public Image bildgameover;
	public Image inforand;
	public Image picpause;
	// Bilder
	// Schuss
	public Image schuss[] = new Image[anzspieler];
	public Image gegnerschuss[] = new Image[anzgegner];
	public Image picleben[] = new Image[anzspieler * 3];

	private JPanel pnl = new JPanel();
	ImageIcon iitankup = new ImageIcon(this.getClass()
			.getResource("Tankup.png"));
	ImageIcon iitankdown = new ImageIcon(this.getClass().getResource(
			"Tankdown.png"));
	ImageIcon iitankleft = new ImageIcon(this.getClass().getResource(
			"Tankleft.png"));
	ImageIcon iitankright = new ImageIcon(this.getClass().getResource(
			"Tankright.png"));

	ImageIcon iitankup2 = new ImageIcon(this.getClass().getResource(
			"Tankup2.png"));
	ImageIcon iitankdown2 = new ImageIcon(this.getClass().getResource(
			"Tankdown2.png"));
	ImageIcon iitankleft2 = new ImageIcon(this.getClass().getResource(
			"Tankleft2.png"));
	ImageIcon iitankright2 = new ImageIcon(this.getClass().getResource(
			"Tankright2.png"));

	ImageIcon iischuss = new ImageIcon(this.getClass()
			.getResource("Schuss.png"));
	ImageIcon iistuetzpunkt = new ImageIcon(this.getClass().getResource(
			"Stuetzpunkt.png"));
	ImageIcon iistuetzpunkt2 = new ImageIcon(this.getClass().getResource(
			"Stuetzpunkt2.png"));
	ImageIcon iigameover = new ImageIcon(this.getClass().getResource(
			"Gameover.png"));
	ImageIcon iiinforand = new ImageIcon(this.getClass().getResource(
			"InfoRand.png"));
	ImageIcon iisymbolgegner = new ImageIcon(this.getClass().getResource(
			"SymbolGegner.png"));
	ImageIcon iipicleben = new ImageIcon(this.getClass().getResource(
			"Leben.png"));
	ImageIcon iipause = new ImageIcon(this.getClass().getResource("pause.png"));
	public boolean up[] = new boolean[anzspieler];
	public boolean left[] = new boolean[anzspieler];
	public boolean right[] = new boolean[anzspieler];
	public boolean down[] = new boolean[anzspieler];
	public boolean uphilf[] = new boolean[anzspieler];
	public boolean lefthilf[] = new boolean[anzspieler];
	public boolean righthilf[] = new boolean[anzspieler];
	public boolean downhilf[] = new boolean[anzspieler];

	public int gegerhilfcontakt[] = new int[anzgegner];
	public boolean gegnerup[] = new boolean[anzgegner];
	public boolean gegnerleft[] = new boolean[anzgegner];
	public boolean gegnerright[] = new boolean[anzgegner];
	public boolean gegnerdown[] = new boolean[anzgegner];

	public boolean hinrechts[] = new boolean[anzgegner];
	public boolean hinlinks[] = new boolean[anzgegner];
	public boolean hinoben[] = new boolean[anzgegner];
	public boolean hinunten[] = new boolean[anzgegner];

	public boolean upschuss = false;
	public boolean downschuss = false;
	public boolean rightschuss = false;
	public boolean leftschuss = false;
	public boolean aufeis = false;
	public boolean hilfinit[] = new boolean[anzspieler];
	public boolean gameover = false;
	public boolean ingame = false;
	public boolean replay = false;
	public boolean p1win = false;
	public boolean p2win = false;
	public boolean pause = true;
	public int anzahlwin[] = new int[anzspieler];

	public boolean ifschuss[] = new boolean[anzspieler];
	public boolean helpifschussup[] = new boolean[anzspieler];
	public boolean helpifschussdown[] = new boolean[anzspieler];
	public boolean helpifschussleft[] = new boolean[anzspieler];
	public boolean helpifschussright[] = new boolean[anzspieler];
	public boolean stop[] = new boolean[anzspieler];
	public boolean gamestarted = false;

	public boolean ggifschuss[] = new boolean[anzgegner];
	public boolean gghelpifschussup[] = new boolean[anzgegner];
	public boolean gghelpifschussdown[] = new boolean[anzgegner];
	public boolean gghelpifschussleft[] = new boolean[anzgegner];
	public boolean gghelpifschussright[] = new boolean[anzgegner];

	public int ggspeed = 4;
	// für zeit
	public long zstVorher;
	public long zstNachher;
	// items
	public boolean schiff[] = new boolean[anzspieler];
	public boolean hartemun[] = new boolean[anzspieler];
	public boolean schnell[] = new boolean[anzspieler];
	public boolean item_leben[] = new boolean[anzspieler];

	private int nirvana = -1000;

	private int schussspeed = 20;
	private int rand = 832;
	public int sek[] = new int[anzspieler];

	private int tankx[] = new int[anzspieler];
	private int tanky[] = new int[anzspieler];

	private int speed[] = new int[anzspieler];
	private int schussx[] = new int[anzspieler];
	private int schussy[] = new int[anzspieler];
	private int leben[] = new int[anzspieler];
	private int kills[] = new int[anzspieler];
	private int punkte[] = new int[anzspieler];

	private int gegnerschussx[] = new int[anzgegner];
	private int gegnerschussy[] = new int[anzgegner];
	private int ggschusszahl[] = new int[anzgegner];
	private int hilfproofnirvana[] = new int[anzgegner]; //für Gegner Spwaner
	private int stuetzpunktx[] = new int[anzstuetzpunkt];
	private int stuetzpunkty[] = new int[anzstuetzpunkt];

	private int ggspawnhilf = 0;
	private int symbolgegnerx[] = new int[anzgegner];
	private int symbolgegnery[] = new int[anzgegner];

	private int ziegelfeld[] = new int[anzziegel];
	private int stahlfeld[] = new int[anzstahlwand];
	private int gegnerfeld[] = new int[anzgegner];
	private int delay = 8; // verzögerung des timers
	private TAdapter kl = new TAdapter();// KeyListener
	// Hindernisse
	public HindernissT[] ziegelmauer;
	public HindernissT[] busch;
	public HindernissT[] fluss;
	public HindernissT[] stahlwand;
	public HindernissT[] eis;

	public ItemT Item_hartemun;
	public ItemT Item_schiff;
	public ItemT Item_schnell;
	public ItemT Item_leben;

	public GegnerT[] gegner;

	
	private JPanel menu = new JPanel();

	public int mode = 0;

	public JFrame frm_fehler = new JFrame("Fehler!");
	{
		frm_fehler.setSize(180, 120);
		frm_fehler.setResizable(false);
		frm_fehler.setLocationRelativeTo(null);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Konstrucktor
	
	public BoardT() {
		super("Panzer Division Wodka");
		this.add(pnl);

		

		this.add(this.menu);
		this.menu.setLayout(null); // damit die Butten verschoben werden können

		frm_fehler.setVisible(false);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(rand + 200, rand + statleiste);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
		this.pnl.setBackground(Color.black);

		// verschiedebe Mods
		anzspieler = 2;
		anzstuetzpunkt = 2;
		onevsone = true;
		addKeyListener(kl);
		// Timer
		timer = new Timer(delay, this);
		timer.start();

		offImg = (BufferedImage) createImage(rand + 200, rand + statleiste);
		bufferedgraphics = offImg.getGraphics();

	}

	// Initialisierung HAUPT

	public void init() {

		setFocusable(true);

		initGame(); 
		initHindernissfeld();
		initZiegel();
		initBusch();
		initFluss();
		initStahlwand();
		initEis();
		setzeItem();
		initGegner();
		initSpieler();
		initStuetzpunkt();
		initSchuss();
		initGegnerSchuss();
		initGegnerhilfcontakt();
		initLeben();
	}
	//Sound für Schuss
	public void soundSchuss() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		// AudioInputStream myInputStream = AudioSystem.getAudioInputStream(new
		// File("shot.wav"));

		AudioInputStream myInputStream = AudioSystem.getAudioInputStream(this
				.getClass().getResource("shot.wav"));
		AudioFormat myAudioFormat = myInputStream.getFormat();
		int groesse = (int) (myAudioFormat.getFrameSize() * myInputStream
				.getFrameLength());
		byte[] mySound = new byte[groesse];
		DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat,
				groesse);
		myInputStream.read(mySound, 0, groesse);

		Clip myClip = (Clip) AudioSystem.getLine(myInfo);
		myClip.open(myAudioFormat, mySound, 0, groesse);
		myClip.start();

	}
	//Sound für fahren (nicht benutzt)
	public void soundFahr() throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {

		AudioInputStream myInputStream = AudioSystem.getAudioInputStream(this
				.getClass().getResource("fahr.wav"));
		AudioFormat myAudioFormat = myInputStream.getFormat();
		int groesse = (int) (myAudioFormat.getFrameSize() * myInputStream
				.getFrameLength());
		byte[] mySound = new byte[groesse];
		DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat,
				groesse);
		myInputStream.read(mySound, 0, groesse);

		Clip myClip = (Clip) AudioSystem.getLine(myInfo);
		myClip.open(myAudioFormat, mySound, 0, groesse);
		myClip.start();

	}
// Initialisierungen ////////////////////////////////////
	public void ingameinitSchuss(int spieler) {

		if ((helpifschussup[spieler] == false)
				&& (helpifschussdown[spieler] == false)
				&& (helpifschussright[spieler] == false)
				&& (helpifschussleft[spieler] == false)) {

			schussx[spieler] = tankx[spieler] + 23;
			schussy[spieler] = tanky[spieler] + 23;

		}

	}
	
	public void initSchuss() {

		for (int i = 0; i < anzspieler; i++) {

			if ((helpifschussup[i] == false) && (helpifschussdown[i] == false)
					&& (helpifschussright[i] == false)
					&& (helpifschussleft[i] == false)) {

				schussx[i] = tankx[i] + 23;
				schussy[i] = tanky[i] + 23;

			}

		}

	}

	public void ingameinitGegnerSchuss(int zgegner) {

		if ((gghelpifschussup[zgegner] == false)
				&& (gghelpifschussdown[zgegner] == false)
				&& (gghelpifschussright[zgegner] == false)
				&& (gghelpifschussleft[zgegner] == false)) {

			gegnerschussx[zgegner] = gegner[zgegner].x + 23;
			gegnerschussy[zgegner] = gegner[zgegner].y + 23;

		}

	}

	public void initGegnerSchuss() {

		for (int i = 0; i < anzgegner; i++) {

			if ((gghelpifschussup[i] == false)
					&& (gghelpifschussdown[i] == false)
					&& (gghelpifschussright[i] == false)
					&& (gghelpifschussleft[i] == false)) {

				gegnerschussx[i] = gegner[i].x + 23;
				gegnerschussy[i] = gegner[i].y + 23;
			}

		}

	}
	
	public void ingameinitSpieler(int spieler) {

		up[spieler] = false;
		left[spieler] = false;
		right[spieler] = false;
		down[spieler] = false;
		uphilf[spieler] = true;
		lefthilf[spieler] = false;
		righthilf[spieler] = false;
		downhilf[spieler] = false;

		if ((onevsone == true) && (spieler == 1)) {
			downhilf[1] = true;
			uphilf[1] = false;
		}

		if (spieler == 0) {
			tankx[spieler] = ((rand / 2) - 25) + 160;
			tanky[spieler] = statleiste + 768;
		}
		if (onevsone == false) {
			if (spieler == 1) {
				tankx[spieler] = ((rand / 2) - 25) - 160;
				tanky[spieler] = statleiste + 768;
			}
		}
		if ((onevsone == true) && (spieler == 1)) {
			tanky[spieler] = statleiste + 10;
			tankx[spieler] = ((rand / 2) - 25) - 160;
		}
	}

	public void initStuetzpunkt() {

		stuetzpunktx[0] = rand / 2 - 32;
		stuetzpunkty[0] = statleiste + rand - 64;
		if ((anzspieler == 2) && (anzstuetzpunkt == 2)) {
			stuetzpunktx[1] = rand / 2 - 32;
			stuetzpunkty[1] = statleiste;

		}

	}

	public void initSpieler() {

		for (int i = 0; i < anzspieler; i++) {

			speed[i] = 7;
			leben[i] = 3;
			kills[i] = 0;
			if (gamestarted == false) {
				punkte[i] = 0;
				anzahlwin[i] = 0;
			}

		}
		gamestarted = true;

		for (int i = 0; i < anzspieler; i++) {

			up[i] = false;
			left[i] = false;
			right[i] = false;
			down[i] = false;
			uphilf[i] = true;
			lefthilf[i] = false;
			righthilf[i] = false;
			downhilf[i] = false;

		}
		if (onevsone == true) {
			downhilf[1] = true;
			uphilf[1] = false;
		}

		tankx[0] = ((rand / 2) - 25) + 160; // 556
		tanky[0] = statleiste + 768; // 768

		if (anzspieler == 2) {
			if (onevsone == false) {
				tankx[1] = ((rand / 2) - 25) - 160;
				tanky[1] = statleiste + 768;
			}
		}
		if (onevsone == true) {
			tankx[1] = ((rand / 2) - 25) - 160;
			tanky[1] = statleiste + 10;

		}

	}

	public void initZeit() {

		zstVorher = System.currentTimeMillis();
	}

	// Initialisierung 2
	public void initGame() {
		ingame = true;

		for (int i = 0; i < anzgegner; i++) {
			symbolgegner[i] = iisymbolgegner.getImage();
		}

		// Rotation Tank (Image)

		tankup[0] = iitankup.getImage();
		tankdown[0] = iitankdown.getImage();
		tankleft[0] = iitankleft.getImage();
		tankright[0] = iitankright.getImage();

		if (anzspieler == 2) {

			tankup[1] = iitankup2.getImage();
			tankdown[1] = iitankdown2.getImage();
			tankleft[1] = iitankleft2.getImage();
			tankright[1] = iitankright2.getImage();
		}

		stuetzpunkt = iistuetzpunkt.getImage();
		stuetzpunkt2 = iistuetzpunkt2.getImage();
		bildgameover = iigameover.getImage();
		inforand = iiinforand.getImage();
		picpause = iipause.getImage();

		for (int i = 0; i < anzspieler; i++) {

			schuss[i] = iischuss.getImage();

		}

		for (int i = 0; i < anzgegner; i++) {

			gegnerschuss[i] = iischuss.getImage();
		}

		for (int i = 0; i < anzspieler; i++) {
			schiff[i] = false;
			hartemun[i] = false;
			schnell[i] = false;
			schnell[i] = false;
			sek[i] = 0;
		}
		// anzspieler = menu.anzspieler;
		if (onevsone == true) {
			anzspieler = 2;
		}

		// init Hindernisse
		// ArrayList<HindernissT[]> hliste;
		// hliste = new ArrayList<HindernissT[]>();
		// hliste.add(ziegelmauer);
		// hliste.add(busch);
		// hliste.add(fluss);
		// hliste.add(stahlwand);
		// hliste.add(eis);
		//
		// ArrayList<ItemT> iliste;
		// iliste = new ArrayList<ItemT>();
		// iliste.add(Item_hartemun);
		// iliste.add(Item_schiff);
		// iliste.add(Item_schnell);
		// iliste.add(Item_leben);

		ziegelmauer = new HindernissT[anzziegel];
		busch = new HindernissT[anzbusch];
		fluss = new HindernissT[anzfluss];
		stahlwand = new HindernissT[anzstahlwand];
		eis = new HindernissT[anzeis];

		gegner = new GegnerT[anzgegner];

	}

	public void initLeben() {

		for (int i = 0; i < 3; i++) {
			picleben[i] = iipicleben.getImage();
		}
		if (anzspieler == 2) {
			for (int i = 3; i < 6; i++) {
				picleben[i] = iipicleben.getImage();
			}

		}

	}

	public void setzeItem() {

		do {
			itemzahl = (int) (Math.random() * 100);

		} while ((itemzahl != 1) && (itemzahl != 2) && (itemzahl != 3)
				&& (itemzahl != 4));

		Item_hartemun = new ItemT("HarteMun.png", 1);
		Item_schiff = new ItemT("Schiff.png", 2);
		Item_schnell = new ItemT("Schnell.png", 3);
		Item_leben = new ItemT("Item_Leben.png", 4);
		if (itemzahl == 1) {

			Item_hartemun.setzeItem(rand / dotsize, rand / dotsize,itemsize);
		}
		if (itemzahl == 2) {

			Item_schiff.setzeItem(rand / dotsize, rand / dotsize,itemsize);
		}
		if (itemzahl == 3) {

			Item_schnell.setzeItem(rand / dotsize, rand / dotsize, itemsize);
		}
		if (itemzahl == 4) {

			Item_leben.setzeItem(rand / dotsize, rand / dotsize,	itemsize);
		}

	}

	public void initHindernissfeld() {
		for (int i = 0; i < anzziegel; i++) {

			ziegelfeld[i] = 1;

		}
		for (int i = 0; i < anzstahlwand; i++) {

			stahlfeld[i] = 1;

		}
	}

	public void initGegnerhilfcontakt() {
		for (int i = 0; i < anzgegner; i++) {

			gegerhilfcontakt[i] = 100;

		}

	}

	public void initGegner() {

		if (anzgegner > 13) {

			anzgegner = 13;
		}

		for (int i = 0; i < anzgegner; i++) {

			gegner[i] = new GegnerT("GegnerTankdown0.png", 1);

		}

		if ((level == 1) || (level == 2) || (level == 3)) {
			for (int i = 0; i < anzgegner; i++) {
				if (onevsone == false) {
					if (i < 3) {
						gegner[i].x = 10 + i * 382;
						gegner[i].y = statleiste + 10;
						gegnerfeld[i] = 1;

					} else {
						gegner[i].x = nirvana;
						gegner[i].y = nirvana;
						gegnerfeld[i] = 2;
					}

					gghelpifschussdown[i] = false;
					gghelpifschussup[i] = false;
					gghelpifschussleft[i] = false;
					gghelpifschussright[i] = false;

					symbolgegnerx[i] = 832 + 20;
					symbolgegnery[i] = 60 * i + 35;

				}
			}
		}
	}

	public void initEis() {
		for (int i = 0; i < anzeis; i++) {

			eis[i] = new HindernissT("Eis.png", 3);

		}

		if (onevsone == false) {

			if (level == 1) {
				for (int i = 0; i < 4; i++) {
					int j = i;

					eis[i].x = 12 * 32 + j * 32;
					eis[i].y = statleiste + 32 * 10;

				}
				for (int i = 4; i < 8; i++) {
					int j = i - 4;

					eis[i].x = 12 * 32 + j * 32;
					eis[i].y = statleiste + 32 * 11;

				}

				for (int i = 8; i < 12; i++) {
					int j = i - 8;

					eis[i].x = 0 * 32 + j * 32;
					eis[i].y = statleiste + 32 * 10;

				}
				for (int i = 12; i < 16; i++) {
					int j = i - 12;

					eis[i].x = 0 * 32 + j * 32;
					eis[i].y = statleiste + 32 * 11;

				}

			}
			if (level == 2) {

				// for (int i=0;i < 2;i++){
				// int j = i;
				//
				//
				// eis[i].x = 12*32+j*32;
				// eis[i].y = statleiste+32*10;
				//
				// }
			}

		}
		if (onevsone == true) {

		}
	}

	public void initStahlwand() {
		for (int i = 0; i < anzstahlwand; i++) {

			stahlwand[i] = new HindernissT("Stahlwand.png", 3);

		}

		if (onevsone == false) {

			if (level == 1) {
				for (int i = 0; i < 4; i++) {
					int j = i;
					stahlwand[i].x = 4 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 6;

				}

				for (int i = 4; i < 10; i++) {
					int j = i - 4;
					stahlwand[i].x = 16 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 15;

				}
			}

			if (level == 2) {

				for (int i = 0; i < 4; i++) {
					int j = i;
					stahlwand[i].x = 0 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 8;

				}
				for (int i = 4; i < 8; i++) {
					int j = i - 4;
					stahlwand[i].x = 4 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 12;

				}
				for (int i = 8; i < 12; i++) {
					int j = i - 8;
					stahlwand[i].x = 8 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 16;

				}
				for (int i = 12; i < 16; i++) {
					int j = i - 12;
					stahlwand[i].x = 22 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 8;

				}
				for (int i = 16; i < 20; i++) {
					int j = i - 16;
					stahlwand[i].x = 18 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 12;

				}
				for (int i = 20; i < 24; i++) {
					int j = i - 20;
					stahlwand[i].x = 14 * 32 + j * 32;
					stahlwand[i].y = statleiste + 32 * 16;

				}

			}
		}

		if (onevsone == true) {

			for (int i = 0; i < 10; i++) {
				int j = i;
				stahlwand[i].x = 8 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 6;

			}
			for (int i = 10; i < 20; i++) {
				int j = i - 10;
				stahlwand[i].x = 8 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 7;

			}

			for (int i = 20; i < 30; i++) {
				int j = i - 20;
				stahlwand[i].x = 8 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 18;

			}
			for (int i = 30; i < 40; i++) {
				int j = i - 30;
				stahlwand[i].x = 8 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 19;

			}

			for (int i = 40; i < 45; i++) {
				int j = i - 40;
				stahlwand[i].x = 6 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 15;

			}
			for (int i = 45; i < 50; i++) {
				int j = i - 45;
				stahlwand[i].x = 15 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 15;

			}

			for (int i = 50; i < 55; i++) {
				int j = i - 50;
				stahlwand[i].x = 6 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 10;

			}
			for (int i = 55; i < 60; i++) {
				int j = i - 55;
				stahlwand[i].x = 15 * 32 + j * 32;
				stahlwand[i].y = statleiste + 32 * 10;

			}

		}

	}

	public void initFluss() { // Abhängigkeit erlaubt da Zusammenhang immer
								// besteht
		for (int i = 0; i < anzfluss; i++) {

			fluss[i] = new HindernissT("Fluss.png", 3);

		}

		// if (onevsone == false){
		//
		// if (level == 1){
		// for (int i=0;i < 8;i++){
		// int j = i;
		//
		// fluss[i].x = 4*32+j*32;
		// fluss[i].y = statleiste+32*10;
		//
		// }
		// for (int i=8;i < 16;i++){
		//
		// int j = i-8;
		//
		// fluss[i].x = 4*32+j*32;
		// fluss[i].y = statleiste+32*11;
		//
		// }
		//
		// for (int i=16;i < 22;i++){
		// int j = i-16;
		//
		// fluss[i].x = 16*32+j*32;
		// fluss[i].y = statleiste+32*10;
		//
		// }
		// for (int i=22;i < 28;i++){
		//
		// int j = i-22;
		//
		// fluss[i].x = 16*32+j*32;
		// fluss[i].y = statleiste+32*11;
		//
		// }
		//
		// // if (level == 2){
		// // for (int i=0;i < 8;i++){
		// // int j = i;
		// //
		// // fluss[i].x = 4*32+j*32;
		// // fluss[i].y = statleiste+32*10;
		// //
		// // }
		// // }
		//
		// }
		// }
		// if (onevsone == true){
		// for (int i=0;i < 4;i++){
		// int j = i;
		// fluss[i].x = 0*32+j*32;
		// fluss[i].y = statleiste+32*12;
		//
		// }
		// for (int i=4;i < 8;i++){
		// int j = i-4;
		// fluss[i].x = 0*32+j*32;
		// fluss[i].y = statleiste+32*13;
		//
		// }
		// for (int i=8;i < 12;i++){
		// int j = i-8;
		// fluss[i].x = 22*32+j*32;
		// fluss[i].y = statleiste+32*12;
		//
		// }
		// for (int i=12;i < 16;i++){
		// int j = i-12;
		// fluss[i].x = 22*32+j*32;
		// fluss[i].y = statleiste+32*13;
		//
		// }
		//
		// }
	}

	public void initBusch() {

		for (int i = 0; i < anzbusch; i++) {

			busch[i] = new HindernissT("Busch.png", 2);

		}

		if (onevsone == false) {

			if (level == 1) {
				for (int i = 0; i < 10; i++) {
					int j = i;
					busch[i].x = 0 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 10; i < 20; i++) {
					int j = i - 10;
					busch[i].x = 1 * 32;
					busch[i].y = statleiste + j * 32;
				}
				for (int i = 20; i < 30; i++) {
					int j = i - 20;
					busch[i].x = 2 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 30; i < 40; i++) {
					int j = i - 30;
					busch[i].x = 3 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 40; i < 74; i++) {
					int j = i - 40;
					busch[i].x = 25 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 74; i < 108; i++) {
					int j = i - 74;
					busch[i].x = 24 * 32;
					busch[i].y = statleiste + j * 32;
				}
				for (int i = 108; i < 142; i++) {
					int j = i - 108;
					busch[i].x = 23 * 32;
					busch[i].y = statleiste + j * 32;
				}
				for (int i = 142; i < 176; i++) {
					int j = i - 142;
					busch[i].x = 22 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 176; i < 190; i++) {
					int j = i - 176 + 12;
					busch[i].x = 0 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 190; i < 214; i++) {
					int j = i - 190 + 12;
					busch[i].x = 1 * 32;
					busch[i].y = statleiste + j * 32;
				}
				for (int i = 214; i < 238; i++) {
					int j = i - 214 + 12;
					busch[i].x = 2 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 238; i < 262; i++) {
					int j = i - 238 + 12;
					busch[i].x = 3 * 32;
					busch[i].y = statleiste + j * 32;
				}

				for (int i = 262; i < 280; i++) {
					int j = i - 262;

					busch[i].x = 4 * 32 + j * 32;
					busch[i].y = statleiste + 32 * 0;

				}
				for (int i = 280; i < 298; i++) {
					int j = i - 280;

					busch[i].x = 4 * 32 + j * 32;
					busch[i].y = statleiste + 32 * 1;

				}

			}
			if (level == 2) {
				for (int i = 0; i < 10; i++) {
					int j = i;
					busch[i].x = j * 32;
					busch[i].y = statleiste + 25 * 32;

				}
				for (int i = 10; i < 20; i++) {
					int j = i - 10;
					busch[i].x = j * 32;
					busch[i].y = statleiste + 24 * 32;

				}
				for (int i = 20; i < 30; i++) {
					int j = i - 20;
					busch[i].x = 16 * 32 + j * 32;
					busch[i].y = statleiste + 25 * 32;

				}
				for (int i = 30; i < 40; i++) {
					int j = i - 30;
					busch[i].x = 16 * 32 + j * 32;
					busch[i].y = statleiste + 24 * 32;

				}

			}

		}
		if (onevsone == true) {

			for (int i = 0; i < 14; i++) {
				int j = i;
				busch[i].x = 6 * 32 + j * 32;
				busch[i].y = statleiste + 32 * 11;

			}
			for (int i = 14; i < 28; i++) {
				int j = i - 14;
				busch[i].x = 6 * 32 + j * 32;
				busch[i].y = statleiste + 32 * 12;

			}
			for (int i = 28; i < 42; i++) {
				int j = i - 28;
				busch[i].x = 6 * 32 + j * 32;
				busch[i].y = statleiste + 32 * 13;

			}
			for (int i = 42; i < 56; i++) {
				int j = i - 42;
				busch[i].x = 6 * 32 + j * 32;
				busch[i].y = statleiste + 32 * 14;

			}

		}
	}

	public void initZiegel() { // Einzelne Koordinaten weil Abhängigkeit die
								// Zestörung beeinflusst

		for (int i = 0; i < anzziegel; i++) {

			ziegelmauer[i] = new HindernissT("Ziegel.png", 1);
		}

		if ((level < 3)) {
			if (anzziegel >= 1) {

				ziegelmauer[0].x = 320;
				ziegelmauer[0].y = statleiste + 768;

				if (anzziegel >= 2) {
					ziegelmauer[1].x = 320 + 1 * 32;
					ziegelmauer[1].y = statleiste + 768;

					if (anzziegel >= 3) {
						ziegelmauer[2].x = 320;
						ziegelmauer[2].y = statleiste + 768 + 1 * 32;

						if (anzziegel >= 4) {

							ziegelmauer[3].x = 320 + 1 * 32;
							ziegelmauer[3].y = statleiste + 768 + 1 * 32;
						}
					}
				}
			}

			if (anzziegel >= 5) {

				ziegelmauer[4].x = 320;
				ziegelmauer[4].y = statleiste + 704;

				if (anzziegel >= 6) {
					ziegelmauer[5].x = 320 + 1 * 32;
					ziegelmauer[5].y = statleiste + 704;

					if (anzziegel >= 7) {
						ziegelmauer[6].x = 320;
						ziegelmauer[6].y = statleiste + 704 + 1 * 32;

						if (anzziegel >= 8) {

							ziegelmauer[7].x = 320 + 1 * 32;
							ziegelmauer[7].y = statleiste + 704 + 1 * 32;
						}
					}
				}
			}

			if (anzziegel >= 9) {

				ziegelmauer[8].x = 384;
				ziegelmauer[8].y = statleiste + 704;

				if (anzziegel >= 10) {
					ziegelmauer[9].x = 384 + 1 * 32;
					ziegelmauer[9].y = statleiste + 704;

					if (anzziegel >= 11) {
						ziegelmauer[10].x = 384;
						ziegelmauer[10].y = statleiste + 704 + 1 * 32;

						if (anzziegel >= 12) {

							ziegelmauer[11].x = 384 + 1 * 32;
							ziegelmauer[11].y = statleiste + 704 + 1 * 32;
						}
					}

				}

			}

			if (anzziegel >= 13) {

				ziegelmauer[12].x = 448;
				ziegelmauer[12].y = statleiste + 704;

				if (anzziegel >= 14) {
					ziegelmauer[13].x = 448 + 1 * 32;
					ziegelmauer[13].y = statleiste + 704;

					if (anzziegel >= 15) {
						ziegelmauer[14].x = 448;
						ziegelmauer[14].y = statleiste + 704 + 1 * 32;

						if (anzziegel >= 16) {

							ziegelmauer[15].x = 448 + 1 * 32;
							ziegelmauer[15].y = statleiste + 704 + 1 * 32;
						}
					}

				}

			}

			if (anzziegel >= 17) {

				ziegelmauer[16].x = 448;
				ziegelmauer[16].y = statleiste + 768;

				if (anzziegel >= 18) {
					ziegelmauer[17].x = 448 + 1 * 32;
					ziegelmauer[17].y = statleiste + 768;

					if (anzziegel >= 19) {
						ziegelmauer[18].x = 448;
						ziegelmauer[18].y = statleiste + 768 + 1 * 32;

						if (anzziegel >= 20) {

							ziegelmauer[19].x = 448 + 1 * 32;
							ziegelmauer[19].y = statleiste + 768 + 1 * 32;
						}
					}

				}

			}
		}
		if (level == 2) {

			int k = 22;

			for (int i = 21; i < 43; i++) {
				int j = i - 21;

				ziegelmauer[i].x = 32 * (j + 2);
				ziegelmauer[i].y = statleiste + 0 * 32 + (j + 2) * 32;

			}
			for (int i = 43; i < 65; i++) {
				int j = i - 43;

				k--;

				ziegelmauer[i].x = 32 * (k + 2);
				ziegelmauer[i].y = statleiste + 0 * 32 + (j + 2) * 32;

			}

		}
		if (onevsone == true) {

			if (anzziegel >= 21) {

				ziegelmauer[20].x = 320;
				ziegelmauer[20].y = statleiste + 0;

				if (anzziegel >= 22) {
					ziegelmauer[21].x = 320 + 1 * 32;
					ziegelmauer[21].y = statleiste + 0;

					if (anzziegel >= 23) {
						ziegelmauer[22].x = 320;
						ziegelmauer[22].y = statleiste + 0 + 1 * 32;

						if (anzziegel >= 24) {

							ziegelmauer[23].x = 320 + 1 * 32;
							ziegelmauer[23].y = statleiste + 0 + 1 * 32;
						}
					}
				}
			}

			if (anzziegel >= 25) {

				ziegelmauer[24].x = 320;
				ziegelmauer[24].y = statleiste + 64;

				if (anzziegel >= 26) {
					ziegelmauer[25].x = 320 + 1 * 32;
					ziegelmauer[25].y = statleiste + 64;

					if (anzziegel >= 7) {
						ziegelmauer[26].x = 320;
						ziegelmauer[26].y = statleiste + 64 + 1 * 32;

						if (anzziegel >= 28) {

							ziegelmauer[27].x = 320 + 1 * 32;
							ziegelmauer[27].y = statleiste + 64 + 1 * 32;
						}
					}
				}
			}

			if (anzziegel >= 29) {

				ziegelmauer[28].x = 384;
				ziegelmauer[28].y = statleiste + 64;

				if (anzziegel >= 30) {
					ziegelmauer[29].x = 384 + 1 * 32;
					ziegelmauer[29].y = statleiste + 64;

					if (anzziegel >= 31) {
						ziegelmauer[30].x = 384;
						ziegelmauer[30].y = statleiste + 64 + 1 * 32;

						if (anzziegel >= 32) {

							ziegelmauer[31].x = 384 + 1 * 32;
							ziegelmauer[31].y = statleiste + 64 + 1 * 32;
						}
					}

				}

			}

			if (anzziegel >= 33) {

				ziegelmauer[32].x = 448;
				ziegelmauer[32].y = statleiste + 64;

				if (anzziegel >= 34) {
					ziegelmauer[33].x = 448 + 1 * 32;
					ziegelmauer[33].y = statleiste + 64;

					if (anzziegel >= 35) {
						ziegelmauer[34].x = 448;
						ziegelmauer[34].y = statleiste + 64 + 1 * 32;

						if (anzziegel >= 36) {

							ziegelmauer[35].x = 448 + 1 * 32;
							ziegelmauer[35].y = statleiste + 64 + 1 * 32;
						}
					}

				}

			}

			if (anzziegel >= 37) {

				ziegelmauer[36].x = 448;
				ziegelmauer[36].y = statleiste + 0;

				if (anzziegel >= 38) {
					ziegelmauer[37].x = 448 + 1 * 32;
					ziegelmauer[37].y = statleiste + 0;

					if (anzziegel >= 39) {
						ziegelmauer[38].x = 448;
						ziegelmauer[38].y = statleiste + 0 + 1 * 32;

						if (anzziegel >= 40) {

							ziegelmauer[39].x = 448 + 1 * 32;
							ziegelmauer[39].y = statleiste + 0 + 1 * 32;
						}
					}

				}

			}

			for (int i = 40; i < 66; i++) {
				int j = i - 40;
				ziegelmauer[i].x = 32 * 4;
				ziegelmauer[i].y = statleiste + 0 * 32 + j * 32;

			}
			for (int i = 66; i < 92; i++) {
				int j = i - 66;
				ziegelmauer[i].x = 32 * 5;
				ziegelmauer[i].y = statleiste + 0 * 32 + j * 32;

			}
			for (int i = 92; i < 118; i++) {
				int j = i - 92;
				ziegelmauer[i].x = 32 * 20;
				ziegelmauer[i].y = statleiste + 0 * 32 + j * 32;

			}
			for (int i = 118; i < 144; i++) {
				int j = i - 118;
				ziegelmauer[i].x = 32 * 21;
				ziegelmauer[i].y = statleiste + 0 * 32 + j * 32;

			}

			for (int i = 144; i < 148; i++) {
				int j = i - 144;
				ziegelmauer[i].x = 22 * 32 + j * 32;
				ziegelmauer[i].y = statleiste + 32 * 14;

			}
			for (int i = 148; i < 152; i++) {
				int j = i - 148;
				ziegelmauer[i].x = 22 * 32 + j * 32;
				ziegelmauer[i].y = statleiste + 32 * 11;

			}
			for (int i = 152; i < 156; i++) {
				int j = i - 152;
				ziegelmauer[i].x = 0 * 32 + j * 32;
				ziegelmauer[i].y = statleiste + 32 * 14;

			}
			for (int i = 156; i < 160; i++) {
				int j = i - 156;
				ziegelmauer[i].x = 0 * 32 + j * 32;
				ziegelmauer[i].y = statleiste + 32 * 11;

			}
		}
	}

	
	// Initialisierungen //////////////////////////////////// ENDE

	// AUF "R" neustart
	public void replay() {

		// if (ingame == false){
		if (replay == true) {

			p2win = false;
			p1win = false;
			try {
				init();
			} finally {

				// System.out.println("Fehler");
				// frm_fehler.setVisible(true);
			}

			replay = false;

		}
		// }

	}

	public void actionPerformed(ActionEvent e) {

		moveschuss();
		repaint();

	}

	// paint --> Alles graphische + Prozeduren die mit dem Timer laufen
	public void paint(Graphics g) {

		// bufferedgraphics.clearRect(0, 0, rand+200, rand+statleiste);
		// bufferedgraphics.setColor(Color.white);
		bufferedgraphics.fillRect(0, 0, rand + 200, rand + statleiste);
		prooflevel();
		proofgegner();
		proofstahlwand();
		proofziegel();
		spawnGegner();
		contaktitem();
		contaktrand();
		contaktGegner();
		contaktGegnerschuss();
		contaktziegel();
		contaktstahlwand();
		contakteis();
		contaktMitspielerschuss();
		moveGegnerschuss();
		moveGegner();
		contaktRandgegner();
		contaktStuetzpunktgegner();
		contaktStahlwandgegner();
		contaktFlussgegner();
		contaktZiegelgegner();
		contaktSpielergegner();
		obSchussDesGegner();
		schussDesGegner();
		contaktGegnerHindernissschuss();
		contaktMitspieler();
		contaktSpielerSchussVonGegner();
		contaktStuetzpunktschuss();
		contaktStuetzpunktGegnerschuss();
		proofgameover();
		proofwin();
		contaktStuetzpunkt();
		spawnitem();
		schuss();

		bufferedgraphics.drawImage(inforand, rand, statleiste, this);
		bufferedgraphics.setFont(new Font("Arial Black", Font.PLAIN, 19));
		bufferedgraphics.drawString("PL1 |win:" + anzahlwin[0] + "",
				(rand + 67), 60 + statleiste);

		bufferedgraphics.drawString("" + punkte[0] + "P", (rand + 67),
				135 + statleiste);

		if (anzspieler == 2) {
			bufferedgraphics.drawString("PL2 |win:" + anzahlwin[1] + "",
					(rand + 67), 180 + statleiste);
			bufferedgraphics.drawString("" + punkte[1] + "P", (rand + 67),
					255 + statleiste);
		}

		bufferedgraphics.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bufferedgraphics.drawString("© Anton Koval", (rand + 90), rand
				+ statleiste - 10);
		if (onevsone == true) {
			String str = "";
			if (p2win) {
				str = "PL2 WIN";
			}

			else if (p1win) {
				str = "PL1 WIN";

			} else {
				str = "";

			}

			bufferedgraphics.setFont(new Font("Arial Black", Font.PLAIN, 24));
			bufferedgraphics.drawString(str, (rand + 67), 320 + statleiste);

		}

		

		for (int i = 0; i < leben[0]; i++) {
			int j = i;
			bufferedgraphics.drawImage(picleben[i], (rand + 67) + j * 40,
					70 + statleiste, this);
		}
		if (anzspieler == 2) {
			for (int i = 3; i < leben[1] + 3; i++) {
				int j = i - 3;
				bufferedgraphics.drawImage(picleben[i], (rand + 67) + j * 40,
						190 + statleiste, this);
			}
		}

		for (int i = 0; i < anzgegner; i++) {
			bufferedgraphics.drawImage(symbolgegner[i], symbolgegnerx[i],
					statleiste + symbolgegnery[i], this);

		}

		// Hindernisse Paint
		for (int i = 0; i < anzziegel; i++) {
			bufferedgraphics.drawImage(ziegelmauer[i].Hinderniss,
					ziegelmauer[i].x, ziegelmauer[i].y, this);

		}
		for (int i = 0; i < anzfluss; i++) {
			bufferedgraphics.drawImage(fluss[i].Hinderniss, fluss[i].x,
					fluss[i].y, this);

		}
		for (int i = 0; i < anzstahlwand; i++) {
			bufferedgraphics.drawImage(stahlwand[i].Hinderniss, stahlwand[i].x,
					stahlwand[i].y, this);

		}
		for (int i = 0; i < anzeis; i++) {
			bufferedgraphics.drawImage(eis[i].Hinderniss, eis[i].x, eis[i].y,
					this);

		}

		// bufferedgraphics.d

		for (int i = 0; i < anzspieler; i++) {

			if ((up[i]) || (uphilf[i])) {

				bufferedgraphics.drawImage(schuss[i], schussx[i], schussy[i],
						this);
				bufferedgraphics.drawImage(tankup[i], tankx[i], tanky[i], this);

				uphilf[i] = true;
				downhilf[i] = false;
				lefthilf[i] = false;
				righthilf[i] = false;
			}

			if ((down[i]) || (downhilf[i])) {

				bufferedgraphics.drawImage(schuss[i], schussx[i], schussy[i],
						this);
				bufferedgraphics.drawImage(tankdown[i], tankx[i], tanky[i],
						this);

				downhilf[i] = true;
				uphilf[i] = false;
				lefthilf[i] = false;
				righthilf[i] = false;

			}

			if ((left[i]) || (lefthilf[i])) {

				bufferedgraphics.drawImage(schuss[i], schussx[i], schussy[i],
						this);
				bufferedgraphics.drawImage(tankleft[i], tankx[i], tanky[i],
						this);

				lefthilf[i] = true;
				righthilf[i] = false;
				downhilf[i] = false;
				uphilf[i] = false;
			}
			if ((right[i]) || (righthilf[i])) {

				bufferedgraphics.drawImage(schuss[i], schussx[i], schussy[i],
						this);
				bufferedgraphics.drawImage(tankright[i], tankx[i], tanky[i],
						this);

				righthilf[i] = true;
				uphilf[i] = false;
				downhilf[i] = false;
				lefthilf[i] = false;
			}

		}

		// Busch (Panzer wird vom Spieler nicht gesehen)
		for (int i = 0; i < anzgegner; i++) {
			bufferedgraphics.drawImage(gegnerschuss[i], gegnerschussx[i],
					gegnerschussy[i], this);
			bufferedgraphics.drawImage(gegner[i].Gegner, gegner[i].x,
					gegner[i].y, this);

		}

		for (int i = 0; i < anzbusch; i++) {
			bufferedgraphics.drawImage(busch[i].Hinderniss, busch[i].x,
					busch[i].y, this);

		}

		if (itemzahl == 1) {
			bufferedgraphics.drawImage(Item_hartemun.Item, Item_hartemun.x,
					Item_hartemun.y, this);
		} else if (itemzahl == 2) {
			bufferedgraphics.drawImage(Item_schiff.Item, Item_schiff.x,
					Item_schiff.y, this);
		} else if (itemzahl == 3) {
			bufferedgraphics.drawImage(Item_schnell.Item, Item_schnell.x,
					Item_schnell.y, this);
		} else if (itemzahl == 4) {
			bufferedgraphics.drawImage(Item_leben.Item, Item_leben.x,
					Item_leben.y, this);
		} else {

		}

		bufferedgraphics.drawImage(stuetzpunkt, stuetzpunktx[0],
				stuetzpunkty[0], this);
		if ((anzstuetzpunkt == 2) && (anzspieler == 2)) {
			bufferedgraphics.drawImage(stuetzpunkt2, stuetzpunktx[1],
					stuetzpunkty[1], this);
		}

		if (gameover == true) {
			bufferedgraphics.drawImage(bildgameover, 0, 0, this);
		}

		if (pause == true) {
			// bufferedgraphics.drawImage(picpause, rand/2-135,
			// rand/2-135+statleiste,this);

		}

		g.drawImage(offImg, 0, 0, this);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

	public void spawnitem() {
		// Lässt Item wo andes spawnen (zufällig)
		int zahl = (int) (Math.random() * 10000);

		if (zahl == 1) {
			setzeItem();
		}

	}

	// Leben erhöhung
	public void plusLeben(int spieler) {
	
		if ((leben[spieler] < 3) && (item_leben[spieler] == true)) {
	
			leben[spieler] = leben[spieler] + 1;
			item_leben[spieler] = false;
		}
	
	}

	public void contaktitem() {
		for (int j = 0; j < anzspieler; j++) {

			contaktfluss(j);
			contaktHindernissschuss(j);
			move(j);

			if ((schiff[j] == true) || (hartemun[j] == true)
					|| (schnell[j] == true)) {
				if (hilfinit[j] != true) {
					initZeit();
				}
				hilfinit[j] = true;
				zstNachher = System.currentTimeMillis();
				sek[j] = (int) ((zstNachher - zstVorher) / 1000);

			}

			if (sek[j] == 20) {

				schiff[j] = false;
				hartemun[j] = false;
				schnell[j] = false;
				sek[j] = 0;
				hilfinit[j] = false;
			}

			switch (itemzahl) {

			case 1:
				for (int i = 0; i < anzspieler; i++) {

					if ((tankx[i] >= Item_hartemun.x - 50)
							&& (tankx[i] <= Item_hartemun.x + 20)
							&& (tanky[i] <= (Item_hartemun.y + 20))
							&& (tanky[i] >= (Item_hartemun.y - 50) && (right[i]))) {

						setzeItem();
						hartemun[j] = true;
						schiff[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;
					}
					if ((tankx[i] <= Item_hartemun.x + 20)
							&& (tankx[i] >= Item_hartemun.x - 50)
							&& (tanky[i] <= Item_hartemun.y + 20)
							&& (tanky[i] >= (Item_hartemun.y - 50) && (left[i]))) {

						setzeItem();
						hartemun[j] = true;
						schiff[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;
					}

					if ((tanky[i] <= Item_hartemun.y + 20)
							&& (tankx[i] >= Item_hartemun.x - 50)
							&& (tankx[i] <= Item_hartemun.x + 20)
							&& (tanky[i] >= (Item_hartemun.y - 50) && (up[i]))) {

						setzeItem();
						hartemun[j] = true;
						schiff[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;
					}

					if ((tanky[i] >= Item_hartemun.y - 50)
							&& (tankx[i] >= Item_hartemun.x - 50)
							&& (tankx[i] <= Item_hartemun.x + 20)
							&& (tanky[i] <= (Item_hartemun.y + 20)) && (down[i])) {

						setzeItem();
						hartemun[j] = true;
						schiff[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;
					}
				}
				break;
			case 2:
				for (int i = 0; i < anzspieler; i++) {

					if ((tankx[i] >= Item_schiff.x - 50)
							&& (tankx[i] <= Item_schiff.x + 20)
							&& (tanky[i] <= (Item_schiff.y + 20))
							&& (tanky[i] >= (Item_schiff.y - 50) && (right[i]))) {

						setzeItem();
						schiff[j] = true;
						hartemun[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;

					}
					if ((tankx[i] <= Item_schiff.x + 20)
							&& (tankx[i] >= Item_schiff.x - 50)
							&& (tanky[i] <= Item_schiff.y + 20)
							&& (tanky[i] >= (Item_schiff.y - 50) && (left[i]))) {

						setzeItem();
						schiff[j] = true;
						hartemun[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;

					}

					if ((tanky[i] <= Item_schiff.y + 20)
							&& (tankx[i] >= Item_schiff.x - 50)
							&& (tankx[i] <= Item_schiff.x + 20)
							&& (tanky[i] >= (Item_schiff.y - 50) && (up[i]))) {

						setzeItem();
						schiff[j] = true;
						hartemun[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;

					}

					if ((tanky[i] >= Item_schiff.y - 50)
							&& (tankx[i] >= Item_schiff.x - 50)
							&& (tankx[i] <= Item_schiff.x + 20)
							&& (tanky[i] <= (Item_schiff.y + 20)) && (down[i])) {

						setzeItem();
						schiff[j] = true;
						hartemun[j] = false;
						schnell[j] = false;

						sek[j] = 0;
						hilfinit[j] = false;
					}

				}
				break;
			case 3:
				for (int i = 0; i < anzspieler; i++) {

					if ((tankx[i] >= Item_schnell.x - 50)
							&& (tankx[i] <= Item_schnell.x + 20)
							&& (tanky[i] <= (Item_schnell.y + 20))
							&& (tanky[i] >= (Item_schnell.y - 50) && (right[i]))) {

						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = true;

						sek[j] = 0;
						hilfinit[j] = false;

					}
					if ((tankx[i] <= Item_schnell.x + 20)
							&& (tankx[i] >= Item_schnell.x - 50)
							&& (tanky[i] <= Item_schnell.y + 20)
							&& (tanky[i] >= (Item_schnell.y - 50) && (left[i]))) {

						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = true;

						sek[j] = 0;
						hilfinit[j] = false;

					}

					if ((tanky[i] <= Item_schnell.y + 20)
							&& (tankx[i] >= Item_schnell.x - 50)
							&& (tankx[i] <= Item_schnell.x + 20)
							&& (tanky[i] >= (Item_schnell.y - 50) && (up[i]))) {
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = true;

						sek[j] = 0;
						hilfinit[j] = false;

					}

					if ((tanky[i] >= Item_schnell.y - 50)
							&& (tankx[i] >= Item_schnell.x - 50)
							&& (tankx[i] <= Item_schnell.x + 20)
							&& (tanky[i] <= (Item_schnell.y + 20)) && (down[i])) {
						Item_schnell.setzeItem(rand / dotsize, rand / dotsize, itemsize);
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = true;

						sek[j] = 0;
						hilfinit[j] = false;
					}

				}
			case 4:
				for (int i = 0; i < anzspieler; i++) {

					if ((tankx[i] >= Item_leben.x - 50)
							&& (tankx[i] <= Item_leben.x + 20)
							&& (tanky[i] <= (Item_leben.y + 20))
							&& (tanky[i] >= (Item_leben.y - 50) && (right[i]))) {
						Item_leben.setzeItem(rand / dotsize, rand / dotsize, itemsize);
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = false;
						item_leben[j] = true;
						hilfinit[j] = false;
						plusLeben(i);

					}
					if ((tankx[i] <= Item_leben.x + 20)
							&& (tankx[i] >= Item_leben.x - 50)
							&& (tanky[i] <= Item_leben.y + 20)
							&& (tanky[i] >= (Item_leben.y - 50) && (left[i]))) {
						Item_leben.setzeItem(rand / dotsize, rand / dotsize, itemsize);
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = false;
						item_leben[j] = true;
						hilfinit[j] = false;
						plusLeben(i);

					}

					if ((tanky[i] <= Item_leben.y + 20)
							&& (tankx[i] >= Item_leben.x - 50)
							&& (tankx[i] <= Item_leben.x + 20)
							&& (tanky[i] >= (Item_leben.y - 50) && (up[i]))) {
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = false;
						item_leben[j] = true;
						hilfinit[j] = false;
						plusLeben(i);

					}

					if ((tanky[i] >= Item_leben.y - 50)
							&& (tankx[i] >= Item_leben.x - 50)
							&& (tankx[i] <= Item_leben.x + 20)
							&& (tanky[i] <= (Item_leben.y + 20)) && (down[i])) {
						setzeItem();
						schiff[j] = false;
						hartemun[j] = false;
						schnell[j] = false;
						item_leben[j] = true;
						hilfinit[j] = false;
						plusLeben(i);
					}

				}
				break;

			}

		}
	}
	// Wenn gegen Wand, dann speed negativieren = Stehen
	public void contaktrand() {
		for (int i = 0; i < anzspieler; i++) {

			if ((tankx[i] >= rand - 50) && (right[i])) {
				tankx[i] = tankx[i] - speed[i];

				ingameinitSchuss(i);

			}

			else if ((tankx[i] <= 0) && (left[i])) {
				tankx[i] = tankx[i] + speed[i];

				ingameinitSchuss(i);

			} else if ((tanky[i] >= rand + statleiste - 50) && (down[i])) {
				tanky[i] = tanky[i] - speed[i];

				ingameinitSchuss(i);

			} else if ((tanky[i] <= statleiste) && (up[i])) {
				tanky[i] = tanky[i] + speed[i];

				ingameinitSchuss(i);
				

			}

		}

	}
	// Contakt mit dem Gegner
	public void contaktSpielergegner() {
		for (int j = 0; j < anzspieler; j++) {
			for (int i = 0; i < anzgegner; i++) {

				if ((gegner[i].x >= tankx[j] - 50)
						&& (gegner[i].x <= tankx[j] + 50)
						&& (gegner[i].y <= (tanky[j] + 50))
						&& (gegner[i].y >= (tanky[j] - 50) && (gegnerright[i]))) {
					gegner[i].x = gegner[i].x - ggspeed;

					gegerhilfcontakt[i] = 1;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].x <= tankx[j] + 50)
						&& (gegner[i].x >= tankx[j] - 50)
						&& (gegner[i].y <= tanky[j] + 50)
						&& (gegner[i].y >= (tanky[j] - 50) && (gegnerleft[i]))) {
					gegner[i].x = gegner[i].x + ggspeed;

					gegerhilfcontakt[i] = 1;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y <= tanky[j] + 50)
						&& (gegner[i].x >= tankx[j] - 50)
						&& (gegner[i].x <= tankx[j] + 50)
						&& (gegner[i].y >= (tanky[j] - 50) && (gegnerright[i]))) {
					gegner[i].y = gegner[i].y + ggspeed;

					gegerhilfcontakt[i] = 1;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y >= tanky[j] - 50)
						&& (gegner[i].x >= tankx[j] - 50)
						&& (gegner[i].x <= tankx[j] + 50)
						&& (gegner[i].y <= (tanky[j] + 50)) && (gegnerdown[i])) {
					gegner[i].y = gegner[i].y - ggspeed;

					gegerhilfcontakt[i] = 1;
					ingameinitGegnerSchuss(i);

				}
				if (gegerhilfcontakt[i] == 1) {

					initGegnerhilfcontakt();

				}

			}
		}
	}

	public void contaktZiegelgegner() {
		for (int j = 0; j < anzziegel; j++) {
			for (int i = 0; i < anzgegner; i++) {

				if ((gegner[i].x >= ziegelmauer[j].x - 50)
						&& (gegner[i].x <= ziegelmauer[j].x + 32)
						&& (gegner[i].y <= (ziegelmauer[j].y + 32))
						&& (gegner[i].y >= (ziegelmauer[j].y - 50) && (gegnerright[i]))) {

					gegner[i].x = gegner[i].x - ggspeed;
					hinrechts[i] = true;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = false;
					ingameinitGegnerSchuss(i);

				} else if ((gegner[i].x <= ziegelmauer[j].x + 32)
						&& (gegner[i].x >= ziegelmauer[j].x - 50)
						&& (gegner[i].y <= ziegelmauer[j].y + 32)
						&& (gegner[i].y >= (ziegelmauer[j].y - 50) && (gegnerleft[i]))) {

					gegner[i].x = gegner[i].x + ggspeed;
					hinrechts[i] = false;
					hinlinks[i] = true;
					hinunten[i] = false;
					hinoben[i] = false;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y <= ziegelmauer[j].y + 32)
						&& (gegner[i].x >= ziegelmauer[j].x - 50)
						&& (gegner[i].x <= ziegelmauer[j].x + 32)
						&& (gegner[i].y >= (ziegelmauer[j].y - 50) && (gegnerup[i]))) {

					gegner[i].y = gegner[i].y + ggspeed;
					ingameinitGegnerSchuss(i);
					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = true;
					hinoben[i] = false;
				}

				else if ((gegner[i].y >= ziegelmauer[j].y - 50)
						&& (gegner[i].x >= ziegelmauer[j].x - 50)
						&& (gegner[i].x <= ziegelmauer[j].x + 32)
						&& (gegner[i].y <= (ziegelmauer[j].y + 32))
						&& (gegnerdown[i])) {

					gegner[i].y = gegner[i].y - ggspeed;
					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = true;
					ingameinitGegnerSchuss(i);
				} else {

					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = false;
				}

			}
		}
	}

	public void contaktStuetzpunktgegner() {
		for (int j = 0; j < anzstuetzpunkt; j++) {
			for (int i = 0; i < anzgegner; i++) {

				if ((gegner[i].x >= stuetzpunktx[j] - 50)
						&& (gegner[i].x <= stuetzpunktx[j] + 64)
						&& (gegner[i].y <= (stuetzpunkty[j] + 64))
						&& (gegner[i].y >= (stuetzpunkty[j] - 50) && (gegnerright[i]))) {
					gegner[i].x = gegner[i].x - ggspeed;
					ingameinitGegnerSchuss(i);

				} else if ((gegner[i].x <= stuetzpunktx[j] + 64)
						&& (gegner[i].x >= stuetzpunktx[j] - 50)
						&& (gegner[i].y <= stuetzpunkty[j] + 64)
						&& (gegner[i].y >= (stuetzpunkty[j] - 50) && (gegnerleft[i]))) {
					gegner[i].x = gegner[i].x + ggspeed;

					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y <= stuetzpunkty[j] + 64)
						&& (gegner[i].x >= stuetzpunktx[j] - 50)
						&& (gegner[i].x <= stuetzpunktx[j] + 64)
						&& (gegner[i].y >= (stuetzpunkty[j] - 50) && (gegnerright[i]))) {
					gegner[i].y = gegner[i].y + ggspeed;

					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y >= stuetzpunkty[j] - 50)
						&& (gegner[i].x >= stuetzpunktx[j] - 50)
						&& (gegner[i].x <= stuetzpunktx[j] + 64)
						&& (gegner[i].y <= (stuetzpunkty[j] + 64))
						&& (gegnerdown[i])) {
					gegner[i].y = gegner[i].y - ggspeed;

					ingameinitGegnerSchuss(i);

				}
			}
		}
	}

	public void contaktFlussgegner() {
		for (int j = 0; j < anzfluss; j++) {
			for (int i = 0; i < anzgegner; i++) {

				if ((gegner[i].x >= fluss[j].x - 50)
						&& (gegner[i].x <= fluss[j].x + 32)
						&& (gegner[i].y <= (fluss[j].y + 32))
						&& (gegner[i].y >= (fluss[j].y - 50) && (gegnerright[i]))) {

					gegner[i].x = gegner[i].x - ggspeed;

					ingameinitGegnerSchuss(i);

				} else if ((gegner[i].x <= fluss[j].x + 32)
						&& (gegner[i].x >= fluss[j].x - 50)
						&& (gegner[i].y <= fluss[j].y + 32)
						&& (gegner[i].y >= (fluss[j].y - 50) && (gegnerleft[i]))) {

					gegner[i].x = gegner[i].x + ggspeed;

					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y <= fluss[j].y + 32)
						&& (gegner[i].x >= fluss[j].x - 50)
						&& (gegner[i].x <= fluss[j].x + 32)
						&& (gegner[i].y >= (fluss[j].y - 50) && (gegnerup[i]))) {

					gegner[i].y = gegner[i].y + ggspeed;

					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y >= fluss[j].y - 50)
						&& (gegner[i].x >= fluss[j].x - 50)
						&& (gegner[i].x <= fluss[j].x + 32)
						&& (gegner[i].y <= (fluss[j].y + 32))
						&& (gegnerdown[i])) {

					gegner[i].y = gegner[i].y - ggspeed;

					ingameinitGegnerSchuss(i);

				}
			}
		}
	}

	public void contaktStahlwandgegner() {
		for (int i = 0; i < anzgegner; i++) {

			for (int j = 0; j < anzstahlwand; j++) {
				if ((gegner[i].x >= stahlwand[j].x - 50)
						&& (gegner[i].x <= stahlwand[j].x + 32)
						&& (gegner[i].y <= (stahlwand[j].y + 32))
						&& (gegner[i].y >= (stahlwand[j].y - 50) && (gegnerright[i]))) {
					gegner[i].x = gegner[i].x - ggspeed;
					hinrechts[i] = true;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = false;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].x <= stahlwand[j].x + 32)
						&& (gegner[i].x >= stahlwand[j].x - 50)
						&& (gegner[i].y <= stahlwand[j].y + 32)
						&& (gegner[i].y >= (stahlwand[j].y - 50) && (gegnerleft[i]))) {
					gegner[i].x = gegner[i].x + ggspeed;
					hinrechts[i] = false;
					hinlinks[i] = true;
					hinunten[i] = false;
					hinoben[i] = false;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y <= stahlwand[j].y + 32)
						&& (gegner[i].x >= stahlwand[j].x - 50)
						&& (gegner[i].x <= stahlwand[j].x + 32)
						&& (gegner[i].y >= (stahlwand[j].y - 50) && (gegnerup[i]))) {
					gegner[i].y = gegner[i].y + ggspeed;
					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = true;
					hinoben[i] = false;
					ingameinitGegnerSchuss(i);

				}

				else if ((gegner[i].y >= stahlwand[j].y - 50)
						&& (gegner[i].x >= stahlwand[j].x - 50)
						&& (gegner[i].x <= stahlwand[j].x + 32)
						&& (gegner[i].y <= (stahlwand[j].y + 32))
						&& (gegnerdown[i])) {
					gegner[i].y = gegner[i].y - ggspeed;
					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = true;
					ingameinitGegnerSchuss(i);

				} else {
					hinrechts[i] = false;
					hinlinks[i] = false;
					hinunten[i] = false;
					hinoben[i] = false;

				}
			}
		}

	}

	public void contaktRandgegner() {
		// Rand
		;
		for (int i = 0; i < anzgegner; i++) {

			if ((gegner[i].x >= rand - 50) && (gegnerright[i])) {
				gegner[i].x = gegner[i].x - ggspeed;
				// Rechter Rand
				ingameinitGegnerSchuss(i);
				hinrechts[i] = true;
				hinlinks[i] = false;
				hinunten[i] = false;
				hinoben[i] = false;
			}

			else if ((gegner[i].x <= 0) && (gegnerleft[i])) {
				// Linker Rand
				gegner[i].x = gegner[i].x + ggspeed;
				hinrechts[i] = false;
				hinlinks[i] = true;
				hinunten[i] = false;
				hinoben[i] = false;
				ingameinitGegnerSchuss(i);

			} else if ((gegner[i].y >= statleiste + rand - 50)
					&& (gegnerdown[i])) {
				//
				gegner[i].y = gegner[i].y - ggspeed;
				hinrechts[i] = false;
				hinlinks[i] = false;
				hinunten[i] = true;
				hinoben[i] = false;
				ingameinitGegnerSchuss(i);

			} else if ((gegner[i].y <= statleiste) && (gegnerup[i])) {
				gegner[i].y = gegner[i].y + ggspeed;
				hinrechts[i] = false;
				hinlinks[i] = false;
				hinunten[i] = false;
				hinoben[i] = true;
				ingameinitGegnerSchuss(i);

			} else {
				hinrechts[i] = false;
				hinlinks[i] = false;
				hinunten[i] = false;
				hinoben[i] = false;
			}

		}

	}

	public void contaktGegnerHindernissschuss() {

		for (int j = 0; j < anzgegner; j++) {

			for (int i = 0; i < anzziegel; i++) {

				if ((gegnerschussy[j] <= ziegelmauer[i].y + 32)
						&& (gegnerschussx[j] >= ziegelmauer[i].x - 4)
						&& (gegnerschussx[j] <= ziegelmauer[i].x + 32)
						&& (gegnerschussy[j] >= (ziegelmauer[i].y - 4))) {

					collisionGegnerschuss(j);
					ingameinitGegnerSchuss(j);

					ziegelfeld[i] = 0;
				}
			}

			for (int i = 0; i < anzstahlwand; i++) {

				if ((gegnerschussy[j] <= stahlwand[i].y + 32)
						&& (gegnerschussx[j] >= stahlwand[i].x - 4)
						&& (gegnerschussx[j] <= stahlwand[i].x + 32)
						&& (gegnerschussy[j] >= (stahlwand[i].y - 4))) {
					collisionGegnerschuss(j);
					ingameinitGegnerSchuss(j);
					// if (hartemun == true){
					// stahlfeld[i] = 0;
					// }
				}
			}

			for (int i = 0; i < anzgegner; i++) {
				if ((gegnerschussx[i] >= rand - 4)) {
					ingameinitGegnerSchuss(i);
					collisionGegnerschuss(i);

				}

				else if ((gegnerschussx[i] <= 0)) {
					ingameinitGegnerSchuss(i);
					collisionGegnerschuss(i);
				} else if ((gegnerschussy[i] >= rand - 4)) {
					ingameinitGegnerSchuss(i);
					collisionGegnerschuss(i);

				} else if ((gegnerschussy[i] <= statleiste)) {
					ingameinitGegnerSchuss(i);
					collisionGegnerschuss(i);
				}

			}

		}
	}

	public void collisionschuss(int spieler) {

		helpifschussup[spieler] = false;
		helpifschussdown[spieler] = false;
		helpifschussleft[spieler] = false;
		helpifschussright[spieler] = false;

	}

	public void collisionGegnerschuss(int zgegner) {

		gghelpifschussup[zgegner] = false;
		gghelpifschussdown[zgegner] = false;
		gghelpifschussleft[zgegner] = false;
		gghelpifschussright[zgegner] = false;

	}

	public void schuss() {

		for (int i = 0; i < anzspieler; i++) {

			if (helpifschussup[i] == true) {

				schussy[i] = schussy[i] - schussspeed;

			}
			if (helpifschussdown[i] == true) {

				schussy[i] = schussy[i] + schussspeed;

			}
			if (helpifschussleft[i] == true) {

				schussx[i] = schussx[i] - schussspeed;

			}
			if (helpifschussright[i] == true) {

				schussx[i] = schussx[i] + schussspeed;

			}

		}

	}

	public void schussDesGegner() {

		for (int i = 0; i < anzgegner; i++) {

			if (gghelpifschussup[i] == true) {

				gegnerschussy[i] = gegnerschussy[i] - schussspeed;

			}
			if (gghelpifschussdown[i] == true) {

				gegnerschussy[i] = gegnerschussy[i] + schussspeed;

			}
			if (gghelpifschussleft[i] == true) {

				gegnerschussx[i] = gegnerschussx[i] - schussspeed;

			}
			if (gghelpifschussright[i] == true) {

				gegnerschussx[i] = gegnerschussx[i] + schussspeed;

			}
		}

	}

	public void obSchussDesGegner() {

		for (int i = 0; i < anzgegner; i++) {

			ggschusszahl[i] = 0;
			ggschusszahl[i] = (int) (Math.random() * 100);

			if (ggschusszahl[i] == 1) {

				if ((gghelpifschussup[i] == false)
						&& (gghelpifschussdown[i] == false)
						&& (gghelpifschussright[i] == false)
						&& (gghelpifschussleft[i] == false)) {

					ggifschuss[i] = true;

					if ((ggifschuss[i] == true) && ((gegnerup[i] == true))) {

						gghelpifschussup[i] = true;
						gghelpifschussdown[i] = false;
						gghelpifschussleft[i] = false;
						gghelpifschussright[i] = false;
					}
					if ((ggifschuss[i] == true) && ((gegnerdown[i] == true))) {

						gghelpifschussup[i] = false;
						gghelpifschussdown[i] = true;
						gghelpifschussleft[i] = false;
						gghelpifschussright[i] = false;
					}
					if ((ggifschuss[i] == true) && ((gegnerleft[i] == true))) {

						gghelpifschussup[i] = false;
						gghelpifschussdown[i] = false;
						gghelpifschussleft[i] = true;
						gghelpifschussright[i] = false;
					}
					if ((ggifschuss[i] == true) && ((gegnerright[i] == true))) {

						gghelpifschussup[i] = false;
						gghelpifschussdown[i] = false;
						gghelpifschussleft[i] = false;
						gghelpifschussright[i] = true;
						;
					}

					// System.out.println("  "+gghelpifschussup[1]+"  "+gghelpifschussdown[1]+"  "+gghelpifschussleft[1]+"  "+gghelpifschussright[1]+"  ");

				}

			}
		}

	}

	public void contaktSpielerSchussVonGegner() {
		// gegner trifft Spieler

		for (int i = 0; i < anzgegner; i++) {

			if ((gegnerschussy[i] <= tanky[0] + 50)
					&& (gegnerschussx[i] >= tankx[0] - 4)
					&& (gegnerschussx[i] <= tankx[0] + 50)
					&& (gegnerschussy[i] >= (tanky[0] - 4))) {

				collisionGegnerschuss(i);
				ingameinitGegnerSchuss(i);
				if (gameover == false) {
					leben[0]--;
				}

				ingameinitSpieler(0);
				ingameinitSchuss(0);

			}

			if (anzspieler == 2) {

				if ((gegnerschussy[i] <= tanky[1] + 50)
						&& (gegnerschussx[i] >= tankx[1] - 4)
						&& (gegnerschussx[i] <= tankx[1] + 50)
						&& (gegnerschussy[i] >= (tanky[1] - 4))) {

					collisionGegnerschuss(i);
					ingameinitGegnerSchuss(i);
					if (gameover == false) {
						leben[1]--;
					}

					ingameinitSpieler(1);
					ingameinitSchuss(1);
				}

			}

		}

	}

	public void contaktMitspielerschuss() {

		if (anzspieler == 2) {

			if ((schussy[0] <= tanky[1] + 50) && (schussx[0] >= tankx[1] - 4)
					&& (schussx[0] <= tankx[1] + 50)
					&& (schussy[0] >= (tanky[1] - 4))) {

				collisionschuss(0);
				ingameinitSchuss(0);
				if ((onevsone == false) && (ingame == true)) {
					punkte[0] = punkte[0] - 1;
				}
				if ((onevsone == true) && (ingame == true)) {
					punkte[0] = punkte[0] + 1;
				}
				
				if (ingame == true){
					ingameinitSpieler(1);
					ingameinitSchuss(1);
					leben[1]--;
				
				
				
					if (leben[1] == 0) {
						anzahlwin[0]++;
					}
				}
			} else if ((schussy[1] <= tanky[0] + 50)
					&& (schussx[1] >= tankx[0] - 4)
					&& (schussx[1] <= tankx[0] + 50)
					&& (schussy[1] >= (tanky[0] - 4))) {

				collisionschuss(1);
				ingameinitSchuss(1);
				if ((onevsone == false) && (ingame == true)) {
					punkte[1] = punkte[1] - 1;
				}
				if ((onevsone == true) && (ingame == true)) {
					punkte[1] = punkte[1] + 1;
				}
				if (ingame == true){
					ingameinitSpieler(0);
					ingameinitSchuss(0);
					leben[0]--;
				
				

				
					if (leben[0] == 0) {
						anzahlwin[1]++;
					}
				}
			}

		}

	}

	public void contaktGegnerschuss() {

		for (int i = 0; i < anzgegner; i++) {
			for (int j = 0; j < anzspieler; j++) {

				if ((schussy[j] <= gegner[i].y + 50)
						&& (schussx[j] >= gegner[i].x - 4)
						&& (schussx[j] <= gegner[i].x + 50)
						&& (schussy[j] >= (gegner[i].y - 4))) {

					gegnerfeld[i] = 0;
					win++;
					kills[j]++;
					collisionschuss(j);
					ingameinitSchuss(j);
					punkte[j] = kills[j] * 200;
				}

			}

		}

	}

	public void contaktStuetzpunktGegnerschuss() { // Gegner schießt auf
													// Stuetzpunkt --> Gameover
		for (int i = 0; i < anzstuetzpunkt; i++) {

			for (int j = 0; j < anzgegner; j++) {

				if ((gegnerschussy[j] <= stuetzpunkty[i] + 64)
						&& (gegnerschussx[j] >= stuetzpunktx[i] - 4)
						&& (gegnerschussx[j] <= stuetzpunktx[i] + 64)
						&& (gegnerschussy[j] >= (stuetzpunkty[i] - 4))) {
					gameover = true;
					ingameinitGegnerSchuss(j);
					// Thread.sleep(500);

					// timer.stop();

				}
			}
		}
	}

	public void contaktStuetzpunktschuss() { // Spieler schießt auf Stuetzpunkt
												// --> Gameover
		// boolean hilf = true;
		for (int i = 0; i < anzstuetzpunkt; i++) {

			for (int j = 0; j < anzspieler; j++) {

				if ((schussy[j] <= stuetzpunkty[i] + 64)
						&& (schussx[j] >= stuetzpunktx[i] - 4)
						&& (schussx[j] <= stuetzpunktx[i] + 64)
						&& (schussy[j] >= (stuetzpunkty[i] - 4))) {
					gameover = true;
					collisionschuss(j);
					ingameinitSchuss(j);

					// if (hilf == true){
					if (ingame) {

						if (i == 0) {
							punkte[1] = punkte[1] + 3;
							p2win = true;
							anzahlwin[1]++;
						}
						if (i == 1) {
							punkte[0] = punkte[0] + 3;
							p1win = true;
							anzahlwin[0]++;
						}
					}
					ingame = false;
					// }
					// hilf = false;

					// timer.stop();

				}
			}
		}
	}

	public void contaktGegner() {

		for (int j = 0; j < anzgegner; j++) {

			for (int i = 0; i < anzspieler; i++) {

				if ((tankx[i] >= gegner[j].x - 50)
						&& (tankx[i] <= gegner[j].x + 50)
						&& (tanky[i] <= (gegner[j].y + 50))
						&& (tanky[i] >= (gegner[j].y - 50) && (right[i]))) {
					tankx[i] = tankx[i] - speed[i];
					collisionschuss(i);
					ingameinitSchuss(i);

					
				} else if ((tankx[i] <= gegner[j].x + 50)
						&& (tankx[i] >= gegner[j].x - 50)
						&& (tanky[i] <= gegner[j].y + 50)
						&& (tanky[i] >= (gegner[j].y - 50) && (left[i]))) {
					tankx[i] = tankx[i] + speed[i];
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

				else if ((tanky[i] <= gegner[j].y + 50)
						&& (tankx[i] >= gegner[j].x - 50)
						&& (tankx[i] <= gegner[j].x + 50)
						&& (tanky[i] >= (gegner[j].y - 50) && (up[i]))) {
					tanky[i] = tanky[i] + speed[i];
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

				else if ((tanky[i] >= gegner[j].y - 50)
						&& (tankx[i] >= gegner[j].x - 50)
						&& (tankx[i] <= gegner[j].x + 50)
						&& (tanky[i] <= (gegner[j].y + 50)) && (down[i])) {
					tanky[i] = tanky[i] - speed[i];
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

			}

		}

	}

	public void contaktStuetzpunkt() {

		for (int j = 0; j < anzstuetzpunkt; j++) {
			for (int i = 0; i < anzspieler; i++) {

				if ((tankx[i] >= stuetzpunktx[j] - 50)
						&& (tankx[i] <= stuetzpunktx[j] + 64)
						&& (tanky[i] <= (stuetzpunkty[j] + 64))
						&& (tanky[i] >= (stuetzpunkty[j] - 50) && (right[i]))) {
					tankx[i] = tankx[i] - speed[i];
					stop[i] = false;
					collisionschuss(i);
					ingameinitSchuss(i);

					

				} else if ((tankx[i] <= stuetzpunktx[j] + 64)
						&& (tankx[i] >= stuetzpunktx[j] - 50)
						&& (tanky[i] <= stuetzpunkty[j] + 64)
						&& (tanky[i] >= (stuetzpunkty[j] - 50) && (left[i]))) {
					tankx[i] = tankx[i] + speed[i];

					stop[i] = false;
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

				else if ((tanky[i] <= stuetzpunkty[j] + 64)
						&& (tankx[i] >= stuetzpunktx[j] - 50)
						&& (tankx[i] <= stuetzpunktx[j] + 64)
						&& (tanky[i] >= (stuetzpunkty[j] - 50) && (up[i]))) {
					tanky[i] = tanky[i] + speed[i];

					stop[i] = false;
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

				else if ((tanky[i] >= stuetzpunkty[j] - 50)
						&& (tankx[i] >= stuetzpunktx[j] - 50)
						&& (tankx[i] <= stuetzpunktx[j] + 64)
						&& (tanky[i] <= (stuetzpunkty[j] + 64)) && (down[i])) {
					tanky[i] = tanky[i] - speed[i];
					stop[i] = false;
					collisionschuss(i);
					ingameinitSchuss(i);

					
				}

			}

		}

	}

	public void contaktHindernissschuss(int spieler) {

		for (int i = 0; i < anzziegel; i++) {

			if ((schussy[spieler] <= ziegelmauer[i].y + 32)
					&& (schussx[spieler] >= ziegelmauer[i].x - 4)
					&& (schussx[spieler] <= ziegelmauer[i].x + 32)
					&& (schussy[spieler] >= (ziegelmauer[i].y - 4))) {

				collisionschuss(spieler);
				ingameinitSchuss(spieler);
				ziegelfeld[i] = 0;
			}
		}

		for (int i = 0; i < anzstahlwand; i++) {

			if ((schussy[spieler] <= stahlwand[i].y + 32)
					&& (schussx[spieler] >= stahlwand[i].x - 4)
					&& (schussx[spieler] <= stahlwand[i].x + 32)
					&& (schussy[spieler] >= (stahlwand[i].y - 4))) {
				collisionschuss(spieler);
				ingameinitSchuss(spieler);
				if (hartemun[spieler] == true) {
					stahlfeld[i] = 0;
				}

			}

		}

		if ((schussx[spieler] >= rand - 4)) {

			collisionschuss(spieler);
			ingameinitSchuss(spieler);
			

		}

		if ((schussx[spieler] <= 0)) {
			collisionschuss(spieler);
			ingameinitSchuss(spieler);
			
		}
		if ((schussy[spieler] >= rand - 4)) {

			collisionschuss(spieler);
			ingameinitSchuss(spieler);
			

		}
		if ((schussy[spieler] <= statleiste)) {
			collisionschuss(spieler);
			ingameinitSchuss(spieler);
		
		}

	}

	public void contakteis() {

		for (int j = 0; j < anzeis; j++) {
			for (int i = 0; i < anzspieler; i++) {

				if ((tankx[i] >= eis[j].x - 50) && (tankx[i] <= eis[j].x + 32)
						&& (tanky[i] <= (eis[j].y + 32))
						&& (tanky[i] >= (eis[j].y - 50) && (right[i]))) {
					// tankx[i] = tankx[i] + speed;
					aufeis = true;

				}
				if ((tankx[i] <= eis[j].x + 32) && (tankx[i] >= eis[j].x - 50)
						&& (tanky[i] <= eis[j].y + 32)
						&& (tanky[i] >= (eis[j].y - 50) && (left[i]))) {
					// tankx[i] = tankx[i] - speed;
					aufeis = true;

				}

				if ((tanky[i] <= eis[j].y + 32) && (tankx[i] >= eis[j].x - 50)
						&& (tankx[i] <= eis[j].x + 32)
						&& (tanky[i] >= (eis[j].y - 50) && (up[i]))) {
					// tanky[i] = tanky[i] - speed;
					aufeis = true;

				}

				if ((tanky[i] >= eis[j].y - 50) && (tankx[i] >= eis[j].x - 50)
						&& (tankx[i] <= eis[j].x + 32)
						&& (tanky[i] <= (eis[j].y + 32)) && (down[i])) {
					// tanky[i] = tanky[i] + speed;
					aufeis = true;

				} else {

					aufeis = false;
				}

			}

		}

	}

	public void contaktstahlwand() {

		for (int j = 0; j < anzstahlwand; j++) {
			for (int i = 0; i < anzspieler; i++) {

				if ((tankx[i] >= stahlwand[j].x - 50)
						&& (tankx[i] <= stahlwand[j].x + 32)
						&& (tanky[i] <= (stahlwand[j].y + 32))
						&& (tanky[i] >= (stahlwand[j].y - 50) && (right[i]))) {
					tankx[i] = tankx[i] - speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				}

				else if ((tankx[i] <= stahlwand[j].x + 32)
						&& (tankx[i] >= stahlwand[j].x - 50)
						&& (tanky[i] <= stahlwand[j].y + 32)
						&& (tanky[i] >= (stahlwand[j].y - 50) && (left[i]))) {
					tankx[i] = tankx[i] + speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				}

				else if ((tanky[i] <= stahlwand[j].y + 32)
						&& (tankx[i] >= stahlwand[j].x - 50)
						&& (tankx[i] <= stahlwand[j].x + 32)
						&& (tanky[i] >= (stahlwand[j].y - 50) && (up[i]))) {
					tanky[i] = tanky[i] + speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				}

				else if ((tanky[i] >= stahlwand[j].y - 50)
						&& (tankx[i] >= stahlwand[j].x - 50)
						&& (tankx[i] <= stahlwand[j].x + 32)
						&& (tanky[i] <= (stahlwand[j].y + 32)) && (down[i])) {
					tanky[i] = tanky[i] - speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				} else {
					stop[i] = false;
				}

			}

		}

	}

	public void contaktfluss(int spieler) {

		for (int j = 0; j < anzfluss; j++) {
			// Bleibt hängen, liegt an der Varialenübergabe

			if ((tankx[spieler] >= fluss[j].x - 50)
					&& (tankx[spieler] <= fluss[j].x + 32)
					&& (tanky[spieler] <= (fluss[j].y + 32))
					&& (tanky[spieler] >= (fluss[j].y - 50) && (right[spieler]))) {

				if (schiff[spieler] == false) {

					tankx[spieler] = tankx[spieler] + speed[spieler];
					stop[spieler] = true;
					ingameinitSchuss(spieler);

				}

			} else if ((tankx[spieler] <= fluss[j].x + 32)
					&& (tankx[spieler] >= fluss[j].x - 50)
					&& (tanky[spieler] <= fluss[j].y + 32)
					&& (tanky[spieler] >= (fluss[j].y - 50) && (left[spieler]))) {

				if (schiff[spieler] == false) {
					tankx[spieler] = tankx[spieler] + speed[spieler];
					stop[spieler] = true;
					ingameinitSchuss(spieler);

				}

			}

			else if ((tanky[spieler] <= fluss[j].y + 32)
					&& (tankx[spieler] >= fluss[j].x - 50)
					&& (tankx[spieler] <= fluss[j].x + 32)
					&& (tanky[spieler] >= (fluss[j].y - 50) && (up[spieler]))) {

				if (schiff[spieler] == false) {

					tanky[spieler] = tanky[spieler] + speed[spieler];
					stop[spieler] = true;
					ingameinitSchuss(spieler);

				}

			}

			else if ((tanky[spieler] >= fluss[j].y - 50)
					&& (tankx[spieler] >= fluss[j].x - 50)
					&& (tankx[spieler] <= fluss[j].x + 32)
					&& (tanky[spieler] <= (fluss[j].y + 32)) && (down[spieler])) {
				if (schiff[spieler] == false) {

					tanky[spieler] = tanky[spieler] - speed[spieler];
					stop[spieler] = true;
					ingameinitSchuss(spieler);

				}

			} else {
				stop[spieler] = false;
			}

		}

	}

	public void contaktMitspieler() {

		if (anzspieler == 2) {

			if ((tankx[0] >= tankx[1] - 50) && (tankx[0] <= tankx[1] + 50)
					&& (tanky[0] <= tanky[1] + 50)
					&& (tanky[0] >= (tanky[1] - 50) && (right[0]))) {
				tankx[0] = tankx[0] - speed[0];
				ingameinitSchuss(0);
				stop[0] = true;
				collisionschuss(0);

			} else if ((tankx[0] <= tankx[1] + 50)
					&& (tankx[0] >= tankx[1] - 50)
					&& (tanky[0] <= tanky[1] + 50)
					&& (tanky[0] >= (tanky[1] - 50) && (left[0]))) {
				tankx[0] = tankx[0] + speed[0];

				ingameinitSchuss(0);
				stop[0] = true;
				collisionschuss(0);
			}

			else if ((tanky[0] <= tanky[1] + 50) && (tankx[0] >= tankx[1] - 50)
					&& (tankx[0] <= tankx[1] + 50)
					&& (tanky[0] >= (tanky[1] - 50) && (up[0]))) {
				tanky[0] = tanky[0] + speed[0];

				ingameinitSchuss(0);
				stop[0] = true;
				collisionschuss(0);
			}

			else if ((tanky[0] >= tanky[1] - 50) && (tankx[0] >= tankx[1] - 50)
					&& (tankx[0] <= tankx[1] + 50)
					&& (tanky[0] <= (tanky[1] + 50)) && (down[0])) {
				tanky[0] = tanky[0] - speed[0];

				ingameinitSchuss(0);
				stop[0] = true;
				collisionschuss(0);
			} else {

				stop[0] = false;
			}
			if ((tankx[1] >= tankx[0] - 50) && (tankx[1] <= tankx[0] + 50)
					&& (tanky[1] <= tanky[0] + 50)
					&& (tanky[1] >= (tanky[0] - 50) && (right[1]))) {
				tankx[1] = tankx[1] - speed[1];
				ingameinitSchuss(1);
				stop[1] = true;
				collisionschuss(1);

			} else if ((tankx[1] <= tankx[0] + 50)
					&& (tankx[1] >= tankx[0] - 50)
					&& (tanky[1] <= tanky[0] + 50)
					&& (tanky[1] >= (tanky[0] - 50) && (left[1]))) {
				tankx[1] = tankx[1] + speed[1];

				ingameinitSchuss(1);
				stop[1] = true;
				collisionschuss(1);
			}

			else if ((tanky[1] <= tanky[0] + 50) && (tankx[1] >= tankx[0] - 50)
					&& (tankx[1] <= tankx[0] + 50)
					&& (tanky[1] >= (tanky[0] - 50) && (up[1]))) {
				tanky[1] = tanky[1] + speed[1];

				ingameinitSchuss(1);
				stop[1] = true;
				collisionschuss(1);
			}

			else if ((tanky[1] >= tanky[0] - 50) && (tankx[1] >= tankx[0] - 50)
					&& (tankx[1] <= tankx[0] + 50)
					&& (tanky[1] <= (tanky[0] + 50)) && (down[1])) {
				tanky[1] = tanky[1] - speed[1];

				ingameinitSchuss(1);
				stop[1] = true;
				collisionschuss(1);
			} else {

				stop[1] = false;
			}

		}

	}

	public void contaktziegel() {

		for (int j = 0; j < anzziegel; j++) {
			for (int i = 0; i < anzspieler; i++) {

				if ((tankx[i] >= ziegelmauer[j].x - 50)
						&& (tankx[i] <= ziegelmauer[j].x + 32)
						&& (tanky[i] <= (ziegelmauer[j].y + 32))
						&& (tanky[i] >= (ziegelmauer[j].y - 50) && (right[i]))) {
					tankx[i] = tankx[i] - speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				} else if ((tankx[i] <= ziegelmauer[j].x + 32)
						&& (tankx[i] >= ziegelmauer[j].x - 50)
						&& (tanky[i] <= ziegelmauer[j].y + 32)
						&& (tanky[i] >= (ziegelmauer[j].y - 50) && (left[i]))) {
					tankx[i] = tankx[i] + speed[i];

					stop[i] = true;
					ingameinitSchuss(i);

				}

				else if ((tanky[i] <= ziegelmauer[j].y + 32)
						&& (tankx[i] >= ziegelmauer[j].x - 50)
						&& (tankx[i] <= ziegelmauer[j].x + 32)
						&& (tanky[i] >= (ziegelmauer[j].y - 50) && (up[i]))) {
					tanky[i] = tanky[i] + speed[i];

					stop[i] = true;
					ingameinitSchuss(i);

				}

				else if ((tanky[i] >= ziegelmauer[j].y - 50)
						&& (tankx[i] >= ziegelmauer[j].x - 50)
						&& (tankx[i] <= ziegelmauer[j].x + 32)
						&& (tanky[i] <= (ziegelmauer[j].y + 32)) && (down[i])) {
					tanky[i] = tanky[i] - speed[i];
					stop[i] = true;
					ingameinitSchuss(i);

				} else {

					stop[i] = false;
				}

			}

		}

	}

	public void spawnGegner() {

		for (int i = 0; i < anzgegner; i++) {

			if ((gegnerfeld[i] == 2)) {
				gegner[i].x = nirvana;
				gegner[i].y = nirvana;

			}

			hilfproofnirvana[i] = (int) (Math.random() * 500);

			if ((hilfproofnirvana[i] == 1) && (gegnerfeld[i] == 2)) {

				gegnerfeld[i] = 1;

				gegner[i].x = 10 + ggspawnhilf * 382;
				gegner[i].y = 25;

				ggspawnhilf = ggspawnhilf + 1;

				if (ggspawnhilf == 3) {

					ggspawnhilf = 0;
				}
			}
		}

	}

	public void proofwin() {

		if ((win == anzgegner) && (onevsone == false)) {

			level++; // unerklärlicher Fehler der hochzählung

			init();

		}
	}

	public void proofgameover() {

		if ((leben[0] == 0)) {
			p2win = true;
			gameover = true;
			// timer.stop();
			ingame = false;
			for (int i = 0; i < anzspieler; i++) {
				schnell[i] = false;
				schiff[i] = false;
				hartemun[i] = false;
				sek[i] = 0;
				hilfinit[i] = false;
			}

		}

		if (anzspieler == 2) {
			if (leben[1] == 0) {
				p1win = true;
				gameover = true;
				// timer.stop();
				ingame = false;
				for (int i = 0; i < anzspieler; i++) {
					schnell[i] = false;
					schiff[i] = false;
					hartemun[i] = false;
					sek[i] = 0;
					hilfinit[i] = false;
				}
			}
		}

	}

	public void proofgegner() {

		for (int i = 0; i < anzgegner; i++) {
			if (gegnerfeld[i] == 0) {

				gegner[i].x = nirvana;
				gegner[i].y = nirvana;

				symbolgegnerx[i] = nirvana;
				symbolgegnery[i] = nirvana;

			}

		}

	}

	public void proofziegel() {

		for (int i = 0; i < anzziegel; i++) {
			if (ziegelfeld[i] == 0) {

				ziegelmauer[i].x = nirvana;
				ziegelmauer[i].y = nirvana;

			}

		}

	}

	public void prooflevel() {

		if (win == anzgegner) {

			level++;
			win = 0;
			init();
		}
	}

	public void proofstahlwand() {

		for (int i = 0; i < anzstahlwand; i++) {
			if (stahlfeld[i] == 0) {

				stahlwand[i].x = nirvana;
				stahlwand[i].y = nirvana;

			}

		}

	}

	public void move(int spieler) {

		if (stop[spieler] == false) {
			if (up[spieler]) {

				tanky[spieler] = tanky[spieler] - speed[spieler];

			}
			if (left[spieler]) {
				tankx[spieler] = tankx[spieler] - speed[spieler];

			}

			if (right[spieler]) {
				tankx[spieler] = tankx[spieler] + speed[spieler];

			}

			if (down[spieler]) {
				tanky[spieler] = tanky[spieler] + speed[spieler];

			}

		}
		//Item Schnell
		if (schnell[spieler] == true) {
			speed[spieler] = 12;
		} else {
			speed[spieler] = 7;
		}

	}

	public void moveschuss() {

		for (int i = 0; i < anzspieler; i++) {

			if ((helpifschussup[i] == false) && (helpifschussdown[i] == false)
					&& (helpifschussright[i] == false)
					&& (helpifschussleft[i] == false)) {

				if (up[i]) {

					schussy[i] = schussy[i] - speed[i];

				}
				if (left[i]) {
					schussx[i] = schussx[i] - speed[i];

				}

				if (right[i]) {
					schussx[i] = schussx[i] + speed[i];

				}

				if (down[i]) {
					schussy[i] = schussy[i] + speed[i];

				}

			}

		}
	}

	public void moveGegnerschuss() {

		for (int i = 0; i < anzgegner; i++) {

			if ((gghelpifschussup[i] == false)
					&& (gghelpifschussdown[i] == false)
					&& (gghelpifschussright[i] == false)
					&& (gghelpifschussleft[i] == false)) {

				if (gegnerup[i]) {

					gegnerschussy[i] = gegnerschussy[i] - ggspeed;

				}
				if (gegnerleft[i]) {
					gegnerschussx[i] = gegnerschussx[i] - ggspeed;

				}

				if (gegnerright[i]) {
					gegnerschussx[i] = gegnerschussx[i] + ggspeed;

				}

				if (gegnerdown[i]) {
					gegnerschussy[i] = gegnerschussy[i] + ggspeed;

				}
			}

		}

	}

	public void moveGegner() {

		for (int i = 0; i < anzgegner; i++) {

			zufallrichtug[i] = (int) (Math.random() * 400);

			if (zufallrichtug[i] == 1) {
				hilfzufallrichtug[i] = 1; // rechts

			}
			if (zufallrichtug[i] == 2) {
				hilfzufallrichtug[i] = 2; // unten
			}
			if (zufallrichtug[i] == 3) {
				hilfzufallrichtug[i] = 3; // links
			}
			if (zufallrichtug[i] == 4) {
				hilfzufallrichtug[i] = 4; // oben
			}
// KI nicht Fertig
//			if (hinlinks[i] == true) {
//				int hilf = 0;
//
//				do {
//					hilf = (int) (Math.random() * 400);
//				} while ((hilf != 2) && (hilf != 1) && (hilf != 4));
//				hilfzufallrichtug[i] = hilf;
//
//			}
//			if (hinrechts[i] == true) {
//				int hilf = 0;
//				do {
//					hilf = (int) (Math.random() * 400);
//				} while ((hilf != 2) && (hilf != 3) && (hilf != 4));
//				hilfzufallrichtug[i] = hilf;
//
//			}
//			if (hinunten[i] == true) {
//				int hilf = 0;
//				do {
//					hilf = (int) (Math.random() * 400);
//				} while ((hilf != 1) && (hilf != 3) && (hilf != 4));
//				hilfzufallrichtug[i] = hilf;
//			}
//			if (hinoben[i] == true) {
//
//				int hilf = 0;
//				do {
//					hilf = (int) (Math.random() * 400);
//				} while ((hilf != 1) && (hilf != 3) && (hilf != 2));
//				hilfzufallrichtug[i] = hilf;
//
//			}

			// for (int j = 0;j < anzspieler;j++){
			// if ((tankx[j] == gegner[i].x)&&(tanky[j] < gegner[i].y)){
			//
			// hilfzufallrichtug[i] = 4; //oben
			//
			// }
			//
			// }
			// for (int j = 0;j < anzspieler;j++){
			// if ((tankx[j] == gegner[i].x)&&(tanky[j] > gegner[i].y)){
			//
			// hilfzufallrichtug[i] = 2;
			//
			// }
			//
			// }
			// for (int j = 0;j < anzspieler;j++){
			// if ((tanky[j] == gegner[i].y)&&(tankx[j] > gegner[i].x)){
			//
			// hilfzufallrichtug[i] = 1;
			//
			// }
			//
			// }
			// for (int j = 0;j < anzspieler;j++){
			// if ((tanky[j] == gegner[i].y)&&(tankx[j] < gegner[i].x)){
			//
			// hilfzufallrichtug[i] = 3;
			//
			// }
			//
			// }

			if (hilfzufallrichtug[i] == 1) {
				gegner[i].x = gegner[i].x + ggspeed;

				gegnerup[i] = false;
				gegnerdown[i] = false;
				gegnerleft[i] = false;
				gegnerright[i] = true;

			} else if (hilfzufallrichtug[i] == 2) {
				gegner[i].y = gegner[i].y + ggspeed;

				gegnerup[i] = false;
				gegnerdown[i] = true;
				gegnerleft[i] = false;
				gegnerright[i] = false;

			} else if (hilfzufallrichtug[i] == 3) {
				gegner[i].x = gegner[i].x - ggspeed;

				gegnerup[i] = false;
				gegnerdown[i] = false;
				gegnerleft[i] = true;
				gegnerright[i] = false;

			} else if (hilfzufallrichtug[i] == 4) {
				gegner[i].y = gegner[i].y - ggspeed;

				gegnerup[i] = true;
				gegnerdown[i] = false;
				gegnerleft[i] = false;
				gegnerright[i] = false;

			}

			if (gegnerup[i]) {
				gegner[i].setzeString("GegnerTankup0.png");

			} else if (gegnerdown[i]) {
				gegner[i].setzeString("GegnerTankdown0.png");

			} else if (gegnerleft[i]) {
				gegner[i].setzeString("GegnerTankleft0.png");

			} else if (gegnerright[i]) {
				gegner[i].setzeString("GegnerTankright0.png");

			}

		}
	}

	// Einlesen über die Pfeiltasten etc.
	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			// try {
			// soundFahr();
			// } catch (UnsupportedAudioFileException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// } catch (LineUnavailableException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

			if ((key == KeyEvent.VK_RIGHT)) {
				right[0] = true;
				up[0] = false;
				down[0] = false;
				if ((key == KeyEvent.VK_RIGHT && (left[0]))) {
					right[0] = true;
					up[0] = false;
					down[0] = false;
					left[0] = false;

				}

			}

			if ((key == KeyEvent.VK_UP)) {

				up[0] = true;
				right[0] = false;
				left[0] = false;
				if ((key == KeyEvent.VK_UP) && (down[0])) {
					up[0] = true;
					right[0] = false;
					left[0] = false;
					down[0] = false;

				}

			}

			if ((key == KeyEvent.VK_DOWN)) {

				down[0] = true;
				right[0] = false;
				left[0] = false;
				if ((key == KeyEvent.VK_DOWN) && (up[0])) {
					down[0] = true;
					right[0] = false;
					left[0] = false;
					up[0] = false;

				}

			}
			if ((key == KeyEvent.VK_LEFT)) {
				left[0] = true;
				up[0] = false;
				down[0] = false;
				if ((key == KeyEvent.VK_LEFT) && (right[0])) {
					left[0] = true;
					up[0] = false;
					down[0] = false;
					right[0] = false;
				}

			}

			if ((key == KeyEvent.VK_NUMPAD0)) {
				if ((helpifschussup[0] == false)
						&& (helpifschussdown[0] == false)
						&& (helpifschussright[0] == false)
						&& (helpifschussleft[0] == false)) {
					ifschuss[0] = true;
					// Sound
					try {
						soundSchuss();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if ((ifschuss[0] == true)
							&& ((up[0] == true) || (uphilf[0] == true))) {

						helpifschussup[0] = true;
						helpifschussdown[0] = false;
						helpifschussleft[0] = false;
						helpifschussright[0] = false;
					}
					if ((ifschuss[0] == true)
							&& ((down[0] == true) || (downhilf[0] == true))) {

						helpifschussup[0] = false;
						helpifschussdown[0] = true;
						helpifschussleft[0] = false;
						helpifschussright[0] = false;
					}
					if ((ifschuss[0] == true)
							&& ((left[0] == true) || (lefthilf[0] == true))) {

						helpifschussup[0] = false;
						helpifschussdown[0] = false;
						helpifschussleft[0] = true;
						helpifschussright[0] = false;
					}
					if ((ifschuss[0] == true)
							&& ((right[0] == true) || (righthilf[0] == true))) {

						helpifschussup[0] = false;
						helpifschussdown[0] = false;
						helpifschussleft[0] = false;
						helpifschussright[0] = true;
						;
					}
				}
			}
			// //////////////////// Spieler 2

			if ((key == KeyEvent.VK_D)) {
				right[1] = true;
				up[1] = false;
				down[1] = false;
				if ((key == KeyEvent.VK_D && (left[1]))) {
					right[1] = true;
					up[1] = false;
					down[1] = false;
					left[1] = false;
				}
			}

			if ((key == KeyEvent.VK_W)) {

				up[1] = true;
				right[1] = false;
				left[1] = false;
				if ((key == KeyEvent.VK_W) && (down[1])) {
					up[1] = true;
					right[1] = false;
					left[1] = false;
					down[1] = false;
				}
			}

			if ((key == KeyEvent.VK_S)) {

				down[1] = true;
				right[1] = false;
				left[1] = false;
				if ((key == KeyEvent.VK_S) && (up[1])) {
					down[1] = true;
					right[1] = false;
					left[1] = false;
					up[1] = false;

				}
			}
			if ((key == KeyEvent.VK_A)) {
				left[1] = true;
				up[1] = false;
				down[1] = false;
				if ((key == KeyEvent.VK_A) && (right[1])) {
					left[1] = true;
					up[1] = false;
					down[1] = false;
					right[1] = false;
				}
			}
			// ////// Sonst Tasten
			if ((key == KeyEvent.VK_R)) {
				gameover = false;
				replay = true;
				replay();
			}

			if (key == KeyEvent.VK_P) {
				if (timer.isRunning()) {
					pause = true;
					timer.stop();

				} else {

					pause = false;

					timer.start();

				}
			}

			if ((key == KeyEvent.VK_SPACE)) {
				if ((helpifschussup[1] == false)
						&& (helpifschussdown[1] == false)
						&& (helpifschussright[1] == false)
						&& (helpifschussleft[1] == false)) {

					ifschuss[1] = true;

					try {
						soundSchuss();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if ((ifschuss[1] == true)
							&& ((up[1] == true) || (uphilf[1] == true))) {

						helpifschussup[1] = true;
						helpifschussdown[1] = false;
						helpifschussleft[1] = false;
						helpifschussright[1] = false;
					}
					if ((ifschuss[1] == true)
							&& ((down[1] == true) || (downhilf[1] == true))) {

						helpifschussup[1] = false;
						helpifschussdown[1] = true;
						helpifschussleft[1] = false;
						helpifschussright[1] = false;
					}
					if ((ifschuss[1] == true)
							&& ((left[1] == true) || (lefthilf[1] == true))) {

						helpifschussup[1] = false;
						helpifschussdown[1] = false;
						helpifschussleft[1] = true;
						helpifschussright[1] = false;
					}
					if ((ifschuss[1] == true)
							&& ((right[1] == true) || (righthilf[1] == true))) {

						helpifschussup[1] = false;
						helpifschussdown[1] = false;
						helpifschussleft[1] = false;
						helpifschussright[1] = true;
						;
					}
				}
			}
		}

		// Wenn Taste losgelassen wird stehenbleiben
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_RIGHT)) {
				right[0] = false;
			}
			if ((key == KeyEvent.VK_LEFT)) {
				left[0] = false;
			}
			if ((key == KeyEvent.VK_UP)) {
				up[0] = false;
			}
			if ((key == KeyEvent.VK_DOWN)) {
				down[0] = false;
			}

			if (anzspieler == 2) {

				if ((key == KeyEvent.VK_D)) {
					right[1] = false;
				}
				if ((key == KeyEvent.VK_A)) {
					left[1] = false;
				}
				if ((key == KeyEvent.VK_W)) {
					up[1] = false;
				}
				if ((key == KeyEvent.VK_S)) {
					down[1] = false;
				}

			}

			if ((key == KeyEvent.VK_SPACE)) {
				ifschuss[1] = false;
			}
			if ((key == KeyEvent.VK_NUMPAD0)) {
				ifschuss[0] = false;
			}

		}

	}

	public static void main(String argv[]) {
		new BoardT();
	}

}
