package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.playstate.PlayState;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;
import com.btl.data.SaveFile;

public class MapSelect extends GameState {

	private SaveFile saveFile = SaveFile.create();
	private static final int LEVEL_COUNT = 15;
	private MapButton[] mButtons = new MapButton[LEVEL_COUNT];

	public MapSelect(GamePanel parent, GameState lastState) {
		super(parent, lastState);

		for (int i = 0; i < LEVEL_COUNT; ++i) {
			mButtons[i] = new MapButton(new Point(100 + 100 * (i % 5),
					100 + 100 * (i / 5)), i + 1, saveFile.getLock(i + 1));
		}
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
		Point p = new Point(arg0.getX(), arg0.getY());
		for (MapButton btn : mButtons) {
			if (btn != null && btn.contains(p)) {

				if (!saveFile.getLock(btn.getId())) {
					ModelMap map = ModelMap.createMap(ConversionFunction
							.getCurrentDirectory() + "map//" + btn.getId());
					if (map != null) {
						PlayState playState = new PlayState(parent, this, map,
								saveFile.getHighscore(btn.getId()));
						parent.setState(playState);
					}

				}

				break;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameRender(Graphics g) {
		for (MapButton btn : mButtons) {
			if (btn != null)
				btn.paint(g);
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
