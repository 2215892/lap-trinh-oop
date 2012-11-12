package com.oop.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.oop.data.ButtonImage;
import com.oop.data.OtherImage;
import com.oop.data.SoundEffect;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.gamepanel.Layer;
import com.oop.mapcreation.MapCreation;

/**
 * The Class GameTitle.
 */
public class GameTitle extends GameState {

	private Button btnStart, btnCreateMap, btnHighScore, btnAbout, btnHelp;
	private Layer layer;

	/**
	 * Instantiates a new game title.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 */
	public GameTitle(GamePanel parent, GameState lastState) {
		super(parent, lastState);
		initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.GameState#gameRender(java.awt.Graphics)
	 */
	@Override
	public void gameRender(Graphics g) {
		layer.render();
		g.drawImage(layer.getLayer(), 0, 0, null);

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

		Button clicked = (Button) layer.getClickedObj(new Point(arg0.getX(),
				arg0.getY()));

		if (clicked != null) {

			SoundEffect.BUTTONCLICK.play();

			if (clicked == btnStart) {
				changeState(new StartGameTitle(parent, this));

			} else if (clicked == btnCreateMap) {
				changeState(new MapCreation(parent, this));
			} else if (clicked == btnHighScore) {
				changeState(new HighScore(parent, this));
			} else if (clicked == btnHelp) {
				changeState(new About(parent, this, "help.txt"));
			} else if (clicked == btnAbout) {
				changeState(new About(parent, this, "about.txt"));
			}
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
	 * @see com.oop.gamepanel.GameState#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	private void initialize() {

		btnStart = new Button(new Point(245, 250));
		btnStart.setImage(ButtonImage.BTN_START_GAME);

		btnCreateMap = new Button(new Point(245, 300));
		btnCreateMap.setImage(ButtonImage.BTN_CREATE_MAP);

		btnHighScore = new Button(new Point(245, 350));
		btnHighScore.setImage(ButtonImage.BTN_HIGH_SCORE);

		btnAbout = new Button(new Point(245, 400));
		btnAbout.setImage(ButtonImage.BTN_ABOUT);

		btnHelp = new Button(new Point(245, 450));
		btnHelp.setImage(ButtonImage.BTN_HELP);

		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.TITLE_BG);
		layer.addDrawable(btnAbout);
		layer.addDrawable(btnCreateMap);
		layer.addDrawable(btnHelp);
		layer.addDrawable(btnHighScore);
		layer.addDrawable(btnStart);

	}

}
