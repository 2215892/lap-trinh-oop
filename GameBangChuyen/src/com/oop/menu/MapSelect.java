package com.oop.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.oop.data.ButtonImage;
import com.oop.data.OtherImage;
import com.oop.data.SaveFile;
import com.oop.data.SoundEffect;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.gamepanel.Layer;
import com.oop.model.Helper;
import com.oop.model.ModelMap;
import com.oop.play.PlayState;

/**
 * The Class MapSelect.
 */
public class MapSelect extends GameState {

	private SaveFile saveFile = SaveFile.create();

	/** The Constant LEVEL_COUNT. */
	public static final int LEVEL_COUNT = 15;
	private MapButton[] mButtons = new MapButton[LEVEL_COUNT];
	private Button btnBack;
	private Layer layer;
	private boolean needUpdate = false;

	/**
	 * Instantiates a new map select.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 */
	public MapSelect(GamePanel parent, GameState lastState) {
		super(parent, lastState);

		initialize();
	}

	private void initialize() {

		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.BG);

		/* Khoi tao cac button level */
		for (int i = 0; i < LEVEL_COUNT; ++i) {
			mButtons[i] = new MapButton(new Point(100 + 100 * (i % 5),
					70 + 100 * (i / 5)), i + 1, saveFile.getLock(i + 1));

			layer.addDrawable(mButtons[i]);
		}

		btnBack = new Button(new Point(245, 425));
		btnBack.setImage(ButtonImage.BTN_BACK);
		layer.addDrawable(btnBack);
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
			} else {
				MapButton btn = (MapButton) clicked;

				int id = btn.getId();
				if (!saveFile.getLock(id)) {
					ModelMap map = ModelMap.createMap(Helper
							.getCurrentDirectory() + "map//" + id);
					if (map != null) {
						int nextId = -1;
						if (id < LEVEL_COUNT)
							nextId = id + 1;
						PlayState playState = new PlayState(parent, this, map,
								saveFile.getHighscore(id), nextId);
						needUpdate = true;

						parent.setState(playState);
					}
				}
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

		if (needUpdate) {
			for (MapButton btn : mButtons) {
				if (btn != null) {
					btn.update();
				}
			}
		}

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
