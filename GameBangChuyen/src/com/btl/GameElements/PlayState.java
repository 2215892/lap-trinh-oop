package com.btl.GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameEngine.Drawable;
import com.btl.GameEngine.Layer;
import com.btl.Model.Direction;
import com.btl.Model.GraphNode;
import com.btl.Model.ModelFactory;
import com.btl.Model.ModelMap;
import com.btl.Model.ModelSwitch;
import com.btl.Model.ModelTerminal;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayState.
 */
public class PlayState extends GameState {

	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;

	private Image buffer;

	private ArrayList<PlaySquare> listSquare = new ArrayList<PlaySquare>();
	private ArrayList<PlayFactory> listFactory = new ArrayList<PlayFactory>();
	private ArrayList<PlayTerminal> listTerminal = new ArrayList<PlayTerminal>();
	private ArrayList<PlaySwitch> listSwitch = new ArrayList<PlaySwitch>();

	private Layer menuLayer, hiddenMenuLayer;
	private DrawLayer bgLayer, objLayer;
	private ArrayList<Layer> listLayer;

	/**
	 * Instantiates a new play state.
	 * 
	 * @param panel
	 *            the parent
	 */
	public PlayState(final GamePanel panel, final ModelMap map) {
		super(panel);
		initialize();
		initFromModelMap(map);

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				parent.repaint();
			}
		}, 0, 20);

	}

	private void initSquare() {
		for (PlaySwitch pSwitch : this.listSwitch) {
			pSwitch.setFlag(0);
		}

		for (PlayFactory factory : this.listFactory) {
			initFromFactory(factory);
		}

	}

	private void initFromFactory(final PlayFactory factory) {
		GraphNode neighbor = getNeighbor(factory.getPosition(),
				factory.getDirection());
		factory.addNeighbor(neighbor);
		if (neighbor.getClass().equals(PlaySwitch.class)) {
			((PlaySwitch) neighbor).addInput(factory.getDirection());
			DFS((PlaySwitch) neighbor);
		}
	}

	private GraphNode getNeighbor(final Point position, final Direction d) {
		Point newPoint = position;
		GraphNode node;
		do {
			newPoint = goDirection(newPoint, d);
			node = getNode(newPoint);
			if (node == null) { /* Tao square */
				this.listSquare.add(new PlaySquare(d, newPoint));

			} else
				return node;

		} while (true);
	}

	private GraphNode getNode(final Point p) {
		for (PlaySwitch i : this.listSwitch) {
			if (i.getPosition().equals(p))
				return i;
		}
		for (PlayTerminal i : this.listTerminal) {
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
	private void DFS(PlaySwitch pSwitch) {
		if (pSwitch.getFlag() == 1)
			return;
		pSwitch.setFlag(1);
		for (Direction d : pSwitch.getListDirection()) {
			GraphNode temp = getNeighbor(pSwitch.getPosition(), d);
			pSwitch.addNeighbor(temp);
			if (temp.getClass().equals(PlaySwitch.class)) {
				((PlaySwitch) temp).addInput(d);
				DFS((PlaySwitch) temp);
			}
		}
	}
	private void initFromModelMap(final ModelMap map) {
		/* them factory tu map vao listFactory */
		for (ModelFactory factory : map.getListFactory()) {
			this.listFactory.add(new PlayFactory(factory));
		}

		/* them terminal tu map vao listTerminal */
		for (ModelTerminal terminal : map.getListTerminal()) {
			this.listTerminal.add(new PlayTerminal(terminal));
		}

		/* them switch vao listSwitch */
		for (ModelSwitch mSwitch : map.getListSwitch()) {
			this.listSwitch.add(new PlaySwitch(mSwitch));
		}

		/* init duong trung gian va hinh thanh graph */
		initSquare();

		/* dua cac doi tuong vao bgLayer */
		for (PlayFactory factory : this.listFactory) {
			this.bgLayer.addDrawable(factory);
		}
		for (PlaySquare square : this.listSquare) {
			this.bgLayer.addDrawable(square);
		}
		for (PlayTerminal terminal : this.listTerminal) {
			this.bgLayer.addDrawable(terminal);
		}
		/* Dua switch vao objLayer */

		for (PlaySwitch mSwitch : this.listSwitch) {
			this.objLayer.addDrawable(mSwitch);
		}

		/* sap xep lai cac doi tuong trong bgLayer, objLayer */
		this.bgLayer.sort();
		this.objLayer.sort();

	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		buffer = new BufferedImage(PlayState.WIDTH, PlayState.HEIGHT,
				BufferedImage.TYPE_INT_ARGB);

		//
		// drawLayer
		//
		this.bgLayer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// boxLayer
		//
		this.objLayer = new DrawLayer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// menuLayer
		//
		this.menuLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		//
		// hiddenMenuLayer
		//
		this.hiddenMenuLayer = new Layer(PlayState.WIDTH, PlayState.HEIGHT);
		this.hiddenMenuLayer.hide();
		//
		// listLayer
		//
		this.listLayer = new ArrayList<Layer>();
		this.listLayer.add(bgLayer);
		this.listLayer.add(objLayer);
		this.listLayer.add(menuLayer);
		this.listLayer.add(hiddenMenuLayer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	private void switchClickedHandle(final PlaySwitch pSwitch) {
		pSwitch.changeDirection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

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

		for (Layer i : this.listLayer) {
			clicked = i.getClickedObj(p);
			if (clicked != null)
				break;
		}
		if (clicked != null) {
			if (clicked.getClass().equals(PlaySwitch.class))
				switchClickedHandle((PlaySwitch) clicked);
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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameBoard.GameState#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameBoard.GameState#gameRender(java.awt.Graphics)
	 */
	@Override
	public void gameRender(Graphics g) {
		Graphics g1 = buffer.getGraphics();
		for (Layer l : this.listLayer) {
			l.render();
			g1.drawImage(l.getLayer(), 0, 0, null);

		}

		g.drawImage(this.buffer, 0, 0, null);

	}
}
