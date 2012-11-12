package com.oop.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import com.oop.data.ButtonImage;
import com.oop.data.OtherImage;
import com.oop.data.SaveFile;
import com.oop.data.SoundEffect;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.Drawable;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.gamepanel.Layer;

/**
 * The Class HighScore.
 */
public class HighScore extends GameState {

	private Button btnBack;
	private Layer layer;

	private class HighScoreTable implements Drawable {

		private SaveFile saveFile = SaveFile.create();
		private int[] score = new int[MapSelect.LEVEL_COUNT];

		public HighScoreTable() {

			for (int i = 0; i < MapSelect.LEVEL_COUNT; ++i) {

				score[i] = saveFile.getHighscore(i + 1);

			}

		}

		@Override
		public boolean contains(Point point) {
			return false;
		}

		@Override
		public void paint(Graphics g) {
			drawTable(g);
			drawString((Graphics2D) g);
		}

		private void drawTable(Graphics g) {
			g.setColor(Color.black);

			g.drawRect(100, 100, 485, 320);
			g.drawLine(325, 100, 325, 420);

			for (int i = 0; i < MapSelect.LEVEL_COUNT; ++i) {
				g.drawLine(100, 100 + (i + 1) * 320
						/ (MapSelect.LEVEL_COUNT + 1), 585, 100 + (i + 1) * 320
						/ (MapSelect.LEVEL_COUNT + 1));
			}
		}

		private void drawString(Graphics2D g) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g.drawString("Level", 110, 95 + 320 / (MapSelect.LEVEL_COUNT + 1));
			g.drawString("Score", 335, 95 + 320 / (MapSelect.LEVEL_COUNT + 1));

			for (int j = 0; j < MapSelect.LEVEL_COUNT; ++j) {
				int i = j + 1;
				g.drawString(Integer.toString(i), 110, 95 + (i + 1) * 320
						/ (MapSelect.LEVEL_COUNT + 1));

				g.drawString(Integer.toString(score[j]), 335, 95 + (i + 1)
						* 320 / (MapSelect.LEVEL_COUNT + 1));
			}
		}
	}

	/**
	 * Instantiates a new high score.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 */
	public HighScore(GamePanel parent, GameState lastState) {
		super(parent, lastState);

		btnBack = new Button(new Point(245, 450));
		btnBack.setImage(ButtonImage.BTN_BACK);

		HighScoreTable highscore = new HighScoreTable();

		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.BG);
		layer.addDrawable(btnBack);
		layer.addDrawable(highscore);
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

			if (clicked == btnBack) {
				changeState(lastState);
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
	 * @see com.oop.gamepanel.GameState#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
