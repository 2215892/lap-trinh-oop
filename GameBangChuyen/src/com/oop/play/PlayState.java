package com.oop.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import com.oop.data.ButtonImage;
import com.oop.data.OtherImage;
import com.oop.data.SaveFile;
import com.oop.data.SoundEffect;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.DrawLayer;
import com.oop.gamepanel.Drawable;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.gamepanel.Layer;
import com.oop.menu.MapSelect;
import com.oop.model.Direction;
import com.oop.model.GraphNode;
import com.oop.model.Helper;
import com.oop.model.ModelFactory;
import com.oop.model.ModelItem;
import com.oop.model.ModelMap;
import com.oop.model.ModelSwitch;
import com.oop.model.ModelTerminal;
import com.oop.play.objects.PlayBox;
import com.oop.play.objects.PlayFactory;
import com.oop.play.objects.PlayItem;
import com.oop.play.objects.PlayScore;
import com.oop.play.objects.PlaySquare;
import com.oop.play.objects.PlaySwitch;
import com.oop.play.objects.PlayTerminal;

/**
 * The Class PlayState.
 */
public class PlayState extends GameState {

	private static final int HEIGHT = 700;
	private static final int TIMER_DELAY = 30;

	private static final int WIDTH = 700;

	private int boxStep = 0;
	private Button btnContinue;
	private Button btnEndGame;
	private Button btnNextLevel;
	private Button btnPause;
	private Button btnReplay;
	private BufferedImage buffer;

	private int count = 0;

	private int highscore = -1;
	private boolean isFullBox = false;
	private ArrayList<PlayBox> listBoxs = new ArrayList<PlayBox>();
	private ArrayList<PlayFactory> listFactorys = new ArrayList<PlayFactory>();
	private ArrayList<Layer> listLayers;
	private ArrayList<PlayScore> listScores = new ArrayList<PlayScore>();

	private ArrayList<PlaySquare> listSquares = new ArrayList<PlaySquare>();
	private ArrayList<PlaySwitch> listSwitchs = new ArrayList<PlaySwitch>();
	private ArrayList<PlayTerminal> listTerminals = new ArrayList<PlayTerminal>();

	private ModelMap map;

	private int maxSecond, currentSecond;

	private int nextId = -1;

	private DrawLayer platformLayer, bgLayer, objLayer, bg2Layer;

	private SaveFile saveFile = SaveFile.create();
	private int score = 0;

	private Layer scoreLayer, menuLayer, pauseMenuLayer, gameOverMenuLayer;

	private Timer timer;

	/** The rnd. */
	Random rnd = new Random();

	/**
	 * Instantiates a new play state.
	 * 
	 * @param panel
	 *            the parent
	 * @param lastState
	 *            the last state
	 * @param map
	 *            the map
	 */
	public PlayState(final GamePanel panel, final GameState lastState,
			final ModelMap map) {

		super(panel, lastState);

		this.map = map;

		initialize();
		initFromModelMap(map);

		resume();

	}

	/**
	 * Instantiates a new play state.
	 * 
	 * @param panel
	 *            the panel
	 * @param lastState
	 *            the last state
	 * @param map
	 *            the map
	 * @param highscore
	 *            the highscore
	 * @param nextID
	 *            the next id
	 */
	public PlayState(final GamePanel panel, final GameState lastState,
			final ModelMap map, int highscore, int nextID) {
		this(panel, lastState, map);
		this.highscore = highscore;
		this.nextId = nextID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.GameState#gameRender(java.awt.Graphics)
	 */
	@Override
	public void gameRender(Graphics g) {
		/* sap xep lai layer Object */
		this.objLayer.sort();

		/* render cac layer */
		bgLayer.render();
		objLayer.render();
		bg2Layer.render();
		scoreLayer.render();

		/* Ve vao buffer */
		Graphics g1 = buffer.getGraphics();
		for (Layer l : this.listLayers) {
			g1.drawImage(l.getLayer(), 0, 0, null);

		}

		/* Ve len man hinh */
		g.drawImage(this.buffer, 0, 0, null);

		/* Viet chu, thong tin len man hinh */
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("TimesRoman", Font.PLAIN, 16);
		g2.setFont(font);
		g2.drawString(" Score: " + Integer.toString(score) + "         Time: "
				+ secondToString(currentSecond), 10, 25);
		if (highscore != -1) {
			g2.drawString(" High Score: ", 350, 25);
			font = new Font("TimesRoman", Font.BOLD, 18);
			g2.setFont(font);
			if (highscore >= score) {
				g2.setColor(Color.GRAY);
				g2.drawString(Integer.toString(highscore), 450, 25);
			} else {
				g2.setColor(Color.GREEN);
				g2.drawString(Integer.toString(score), 450, 25);
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// khong dung den

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// khong dung den

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// khong dung den

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		Drawable clicked = null;
		Point p = new Point(arg0.getX(), arg0.getY());

		int size = this.listLayers.size();
		for (int i = size - 1; i >= 0; --i) {
			clicked = this.listLayers.get(i).getClickedObj(p);
			if (clicked != null)
				break;
		}
		if (clicked != null) {
			if (clicked.getClass().equals(PlaySwitch.class))
				switchClickedHandle((PlaySwitch) clicked);
			if (clicked.getClass().equals(Button.class))
				buttonClickedHandle((Button) clicked);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// khong dung den

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.GameState#update()
	 */
	@Override
	public void update() {
		++count;
		currentSecond = maxSecond - count * TIMER_DELAY / 1000;

		if (currentSecond == 0 || (isFullBox && listBoxs.size() == 0)) {
			onGameEnd();
		}

		/* update game state */
		updateSwitchs();
		updateSquares();
		updateBoxs();
		removeOldScore();

		/* Neu chua tao du box thi tao them */
		if (!isFullBox) {

			if (boxStep % 64 == 0) {
				makeNewBox();

			}
			boxStep++;
		}

	}

	private void addBox(PlayBox box) {
		objLayer.addDrawable(box);
		listBoxs.add(box);
	}
	private void addScore(PlayScore score) {
		this.score += score.getScore();
		this.listScores.add(score);
		this.scoreLayer.addDrawable(score);
	}

	private void arrivalHandle(ArrayList<PlayBox> notMovingBoxs) {

		for (PlayBox box : notMovingBoxs) {
			/* Lay doi tuong tai vi tri cua box o layer background */
			Drawable drawable = this.bgLayer.getClickedObj(box.getLocation());

			if (drawable != null) {

				/* Neu tim thay terminal ben duoi box */
				if (drawable.getClass().equals(PlayTerminal.class)) {
					PlayTerminal terminal = (PlayTerminal) drawable;

					if (terminal.boxArrived(box)) {
						/* Neu box den cung mau */
						SoundEffect.RIGHTBOX.play();
						/* Neu terminal dang cho tao box */
						if (terminal.isWaiting()) {
							/*
							 * cho terminal cung mau da duoc tao box sang trang
							 * thai cho
							 */
							for (PlayTerminal t : listTerminals) {
								if (t.getColor() == box.getColor()
										&& !t.isWaiting()) {
									t.setWaiting(true);
									break;
								}
							}
						} else
							terminal.setWaiting(true);

						/* Hien diem */
						addScore(new PlayScore(terminal.getPosition(), 5000));

					} else {
						SoundEffect.WRONGBOX.play();

						/*
						 * Neu den nham terminal thi chuyen terminal duoc tao
						 * box ay sang trang thai cho
						 */
						for (PlayTerminal t : listTerminals) {
							if (t.getColor() == box.getColor()
									&& !t.isWaiting()) {
								t.setWaiting(true);
								break;
							}
						}

						/* Hien diem */
						addScore(new PlayScore(terminal.getPosition(), -5000));
					}

					removeBox(box);
					isFullBox = false;
				}
			}
		}
	}

	private void buttonClickedHandle(Button clicked) {
		if (clicked != null)
			SoundEffect.BUTTONCLICK.play();
		if (clicked == btnPause) {
			pause();
			pauseMenuLayer.show();
			pauseMenuLayer.render();
			parent.repaint();
		} else if (clicked == btnContinue) {
			pauseMenuLayer.hide();
			resume();
		} else if (clicked == btnEndGame) {
			parent.setState(lastState);
		} else if (clicked == btnNextLevel) {
			ModelMap map = ModelMap.createMap(Helper.getCurrentDirectory()
					+ "map//" + nextId);
			if (map != null) {
				int id = nextId;
				if (nextId < MapSelect.LEVEL_COUNT)
					nextId++;
				else
					nextId = -1;
				PlayState playState = new PlayState(parent, lastState, map,
						saveFile.getHighscore(id), nextId);
				changeState(playState);
			}
		} else if (clicked == btnReplay) {
			int id = 0;
			if (nextId == -1)
				id = MapSelect.LEVEL_COUNT;
			else
				id = nextId - 1;

			if (map != null) {
				PlayState playState = null;
				if (highscore != -1)
					playState = new PlayState(parent, lastState, map,
							saveFile.getHighscore(id), nextId);
				else
					playState = new PlayState(parent, lastState, map);
				changeState(playState);
			}
		}

	}

	private void collisionHandle(final ArrayList<PlayBox> notMovingBoxs) {
		ArrayList<PlayBox> collidedBoxs = new ArrayList<PlayBox>();
		int size = notMovingBoxs.size();
		for (int i = 0; i < size; ++i) {
			for (int j = i + 1; j < size; ++j) {
				PlayBox boxA = notMovingBoxs.get(i);
				PlayBox boxB = notMovingBoxs.get(j);
				if (boxA.getLocation().equals(boxB.getLocation())) {
					collidedBoxs.add(boxA);
					collidedBoxs.add(boxB);
					addScore(new PlayScore(boxA.getPosition(), -10000));
				}

			}
		}

		for (PlayBox i : collidedBoxs) {

			removeBox(i);

			/* cho mot terminal cung mau doi box */
			for (PlayTerminal terminal : listTerminals) {
				if (terminal.getColor() == i.getColor()
						&& !terminal.isWaiting()) {
					terminal.setWaiting(true);
					break;
				}
			}

			isFullBox = false;
		}

	}
	private GraphNode getNeighbor(final Point position, final Direction d) {
		Point newPoint = position;
		GraphNode node;
		do {
			newPoint = goDirection(newPoint, d);
			node = getNode(newPoint);
			if (node == null) { /* Tao square */
				this.listSquares.add(new PlaySquare(d, newPoint));

			} else
				return node;

		} while (true);
	}

	private GraphNode getNode(final Point p) {
		for (PlaySwitch i : this.listSwitchs) {
			if (i.getPosition().equals(p))
				return i;
		}
		for (PlayTerminal i : this.listTerminals) {
			if (i.getPosition().equals(p))
				return i;
		}

		return null;

	}

	private Point goDirection(final Point oldPosition, Direction d) {
		switch (d) {
			case RIGHT :
				return new Point(oldPosition.x, oldPosition.y + 1);
			case LEFT :
				return new Point(oldPosition.x, oldPosition.y - 1);
			case DOWN :
				return new Point(oldPosition.x + 1, oldPosition.y);
			case UP :
				return new Point(oldPosition.x - 1, oldPosition.y);
			default :
				return null;
		}
	}

	private void initFactory() {
		for (PlayFactory factory : this.listFactorys) {
			for (PlaySwitch pSwitch : this.listSwitchs) {
				pSwitch.setFlag(0);
			}

			GraphNode neighbor = getNeighbor(factory.getPosition(),
					factory.getDirection());
			if (neighbor.getClass().equals(PlaySwitch.class)) {
				initFactory((PlaySwitch) neighbor, factory);
			} else
				factory.addTerminal((PlayTerminal) neighbor);
		}
	}

	private void initFactory(PlaySwitch pSwitch, PlayFactory factory) {
		if (pSwitch.getFlag() == 1)
			return;
		pSwitch.setFlag(1);
		for (Direction d : pSwitch.getListDirection()) {
			GraphNode temp = getNeighbor(pSwitch.getPosition(), d);
			if (temp.getClass().equals(PlaySwitch.class)) {
				initFactory((PlaySwitch) temp, factory);
			} else
				factory.addTerminal((PlayTerminal) temp);
		}
	}

	private void initFromFactory(final PlayFactory factory) {
		GraphNode neighbor = getNeighbor(factory.getPosition(),
				factory.getDirection());
		factory.addNeighbor(neighbor);
		if (neighbor.getClass().equals(PlaySwitch.class)) {
			((PlaySwitch) neighbor).addInput(factory.getDirection());
			initSquareFromSwitch((PlaySwitch) neighbor);
		}
	}

	private void initFromModelMap(final ModelMap map) {
		this.maxSecond = map.getSecond() + map.getMinute() * 60;

		/* them factory tu map vao listFactory */
		for (ModelFactory factory : map.getListFactory()) {
			this.listFactorys.add(new PlayFactory(factory));
		}

		/* them terminal tu map vao listTerminal */
		for (ModelTerminal terminal : map.getListTerminal()) {
			this.listTerminals.add(new PlayTerminal(terminal));
		}

		/* them switch vao listSwitch */
		for (ModelSwitch mSwitch : map.getListSwitch()) {
			this.listSwitchs.add(new PlaySwitch(mSwitch));
		}

		/* init cac item tu listItem trong map */
		initItems(map.getListItem());

		/* init duong trung gian va hinh thanh graph */
		initSquare();

		/* lap danh sach ca terminal co the toi tu factory */
		initFactory();

		/* dua cac doi tuong vao bgLayer */
		for (PlayFactory factory : this.listFactorys) {
			this.bgLayer.addDrawable(factory);
		}
		for (PlaySquare square : this.listSquares) {
			this.bgLayer.addDrawable(square);
		}
		for (PlayTerminal terminal : this.listTerminals) {
			this.bgLayer.addDrawable(terminal);
		}
		/* Dua switch vao bg2Layer */

		for (PlaySwitch mSwitch : this.listSwitchs) {
			if (!(mSwitch.getListDirection().size() == 1 && mSwitch
					.getListInput().size() == 1))
				this.bg2Layer.addDrawable(mSwitch);
			else
				this.bgLayer.addDrawable(mSwitch);
		}

		/*
		 * sap xep lai cac doi tuong trong platformLayer, bgLayer, bg2Layer,
		 * objLayer
		 */
		this.platformLayer.sort();
		this.bgLayer.sort();
		this.bg2Layer.sort();
		this.objLayer.sort();

		platformLayer.render();
	}
	/**
	 * Initialize.
	 */
	private void initialize() {
		buffer = new BufferedImage(PlayState.WIDTH, PlayState.HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		//
		// platformLayer
		//
		this.platformLayer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// bgLayer
		//
		this.bgLayer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// objLayer
		//
		this.objLayer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// bg2Layter
		//
		this.bg2Layer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// scoreLayer
		//
		this.scoreLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// menuLayer
		//
		this.menuLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// pauseMenuLayer
		//
		this.pauseMenuLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		this.pauseMenuLayer.hide();
		//
		// gameOverMenuLayer
		//
		this.gameOverMenuLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		this.gameOverMenuLayer.hide();
		//
		// listLayer
		//
		this.listLayers = new ArrayList<Layer>();
		this.listLayers.add(platformLayer);
		this.listLayers.add(bgLayer);
		this.listLayers.add(bg2Layer);
		this.listLayers.add(objLayer);
		this.listLayers.add(scoreLayer);
		this.listLayers.add(menuLayer);
		this.listLayers.add(pauseMenuLayer);
		this.listLayers.add(gameOverMenuLayer);
		//
		// khoi tao cac button
		//
		btnPause = new Button(new Point(650, 5));
		btnPause.setImage(ButtonImage.PAUSE_BUTTON);
		btnEndGame = new Button(new Point(245, 160));
		btnEndGame.setImage(ButtonImage.BTN_END_GAME);
		btnContinue = new Button(new Point(245, 280));
		btnContinue.setImage(ButtonImage.BTN_CONTINUE_GAME);
		btnReplay = new Button(new Point(245, 220));
		btnReplay.setImage(ButtonImage.BTN_REPLAY);
		btnNextLevel = new Button(new Point(245, 280));
		btnNextLevel.setImage(ButtonImage.BTN_NEXT_LEVEL);

		menuLayer.addDrawable(btnPause);
		pauseMenuLayer.addDrawable(btnReplay);
		pauseMenuLayer.addDrawable(btnContinue);
		pauseMenuLayer.addDrawable(btnEndGame);
		Button temp = new Button(new Point(0, 0));
		temp.setImage(null, 700, 700);
		pauseMenuLayer.addDrawable(temp);

		BufferedImage tempImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = tempImage.getGraphics();
		g.setColor(new Color(0, 0, 0, 230));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();

		pauseMenuLayer.setBackground(tempImage);

		gameOverMenuLayer.addDrawable(btnNextLevel);
		gameOverMenuLayer.addDrawable(btnReplay);
		gameOverMenuLayer.addDrawable(btnEndGame);
		gameOverMenuLayer.addDrawable(temp);
		gameOverMenuLayer.setBackground(tempImage);

		BufferedImage background = OtherImage.BG;
		platformLayer.setBackground(background);

		menuLayer.render();

	}

	private void initItems(ArrayList<ModelItem> listItem) {
		for (ModelItem i : listItem) {
			PlayItem item = new PlayItem(i);
			if (item.getType() == 2) /* PLATFORM */
				platformLayer.addDrawable(item);
			else
				objLayer.addDrawable(item);
		}

	}

	private void initSquare() {
		for (PlaySwitch pSwitch : this.listSwitchs) {
			pSwitch.setFlag(0);
		}

		for (PlayFactory factory : this.listFactorys) {
			initFromFactory(factory);
		}

	}

	private void initSquareFromSwitch(PlaySwitch pSwitch) {
		if (pSwitch.getFlag() == 1)
			return;
		pSwitch.setFlag(1);
		for (Direction d : pSwitch.getListDirection()) {
			GraphNode temp = getNeighbor(pSwitch.getPosition(), d);
			pSwitch.addNeighbor(temp);
			if (temp.getClass().equals(PlaySwitch.class)) {
				((PlaySwitch) temp).addInput(d);
				initSquareFromSwitch((PlaySwitch) temp);
			}
		}
	}
	private void makeNewBox() {
		ArrayList<PlayBox> boxList = new ArrayList<PlayBox>();
		PlayBox box = null;
		for (PlayFactory i : listFactorys) {
			box = i.makeBox();
			if (box != null)
				boxList.add(box);
		}

		if (boxList.size() != 0) {
			int index = rnd.nextInt(boxList.size());
			box = boxList.get(index);

			for (PlayTerminal t : listTerminals) {
				if (t.getColor() == box.getColor() && t.isWaiting()) {
					t.setWaiting(false);
					break;
				}
			}
			addBox(box);

		} else {
			isFullBox = true;
			boxStep--;
		}
	}
	private void onGameEnd() {
		if (highscore != -1) {
			int id = 0;
			if (nextId == -1)
				id = MapSelect.LEVEL_COUNT;
			else {
				id = nextId - 1;
				if (!((isFullBox && listBoxs.size() == 0))) {
					saveFile.setLock(nextId, false);
					gameOverMenuLayer.removeDrawable(btnNextLevel);
				}
			}

			if (score > highscore)
				saveFile.setHighscore(id, score);

			saveFile.save();
		}

		pause();
		gameOverMenuLayer.show();
		gameOverMenuLayer.render();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				parent.repaint();

			}
		});

	}
	private void pause() {
		timer.cancel();
	}

	private void removeBox(PlayBox box) {
		this.listBoxs.remove(box);
		this.objLayer.removeDrawable(box);
	}

	private void removeOldScore() {
		ArrayList<PlayScore> listDelete = new ArrayList<PlayScore>();
		for (PlayScore i : this.listScores) {
			if (i.isDone())
				listDelete.add(i);
		}
		for (PlayScore i : listDelete) {
			removeScore(i);
		}
	}

	private void removeScore(PlayScore score) {
		this.listScores.remove(score);
		this.scoreLayer.removeDrawable(score);
	}

	private void resume() {
		timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				update();

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						parent.repaint();

					}
				});

			}
		}, 0, TIMER_DELAY);

	}

	private String secondToString(int second) {

		int m = second / 60;
		int s = second % 60;

		return s < 10 ? new String(Integer.toString(m) + ":0"
				+ Integer.toString(s)) : new String(Integer.toString(m) + ":"
				+ Integer.toString(s));
	}

	private boolean setBoxDestination(PlayBox box) {
		/* Lay doi tuong tai vi tri cua box o layer Switch */
		Drawable drawable = this.bg2Layer.getClickedObj(box.getLocation());
		if (drawable == null)
			drawable = this.bgLayer.getClickedObj(box.getLocation());

		if (drawable != null) {

			/* Neu tim thay switch ben duoi box */
			if (drawable.getClass().equals(PlaySwitch.class)) {
				PlaySwitch pSwitch = (PlaySwitch) drawable;

				/* Dat dich cho box */
				box.setDestination(pSwitch.getNeighbor(pSwitch.getDirection())
						.getPosition());
				return true;
			}
		}
		return false;
	}

	private void switchClickedHandle(final PlaySwitch pSwitch) {
		SoundEffect.BUTTONCLICK.play();
		pSwitch.changeDirection();
	}
	private void updateBoxs() {
		ArrayList<PlayBox> notMovingBoxs = new ArrayList<PlayBox>();
		ArrayList<PlayBox> onSwitchBoxs = new ArrayList<PlayBox>();
		for (PlayBox i : listBoxs) {
			i.update();
			if (!(i.isMoving())) { /* Chi kiem tra box da den dich */

				if (!(setBoxDestination(i)))
					notMovingBoxs.add(i);
				else
					onSwitchBoxs.add(i);
			}
		}

		collisionHandle(onSwitchBoxs);
		arrivalHandle(notMovingBoxs);
	}
	private void updateSquares() {
		for (PlaySquare i : listSquares) {
			i.update();
		}
	}

	private void updateSwitchs() {
		for (PlaySwitch i : listSwitchs) {
			i.update();
		}
	}

}
