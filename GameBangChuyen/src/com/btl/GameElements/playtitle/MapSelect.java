package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.playstate.Button;
import com.btl.GameElements.playstate.PlayState;
import com.btl.GameEngine.Layer;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;
import com.btl.data.ButtonImage;
import com.btl.data.OtherImage;
import com.btl.data.SaveFile;
import com.btl.data.SoundEffect;

public class MapSelect extends GameState {

	private SaveFile saveFile = SaveFile.create();
	public static final int LEVEL_COUNT = 15;
	private MapButton[] mButtons = new MapButton[LEVEL_COUNT];
	private Button btnBack;
	private Layer layer;
	private boolean needUpdate = false;

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
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

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
					ModelMap map = ModelMap.createMap(ConversionFunction
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
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

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
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
