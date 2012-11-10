package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameBoard.MapCreation;
import com.btl.GameElements.playstate.Button;
import com.btl.GameElements.playstate.PlayState;
import com.btl.GameEngine.Layer;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;
import com.btl.data.ButtonImage;
import com.btl.data.OtherImage;

public class GameTitle extends GameState {

	Button btnStart, btnCreateMap, btnHighScore, btnAbout, btnHelp;
	Layer layer;

	public GameTitle(GamePanel parent, GameState lastState) {
		super(parent, lastState);
		initialize();
	}

	private void initialize() {

		btnStart = new Button(new Point(100, 100));
		btnStart.setImage(ButtonImage.BTN_START_GAME);

		btnCreateMap = new Button(new Point(100, 200));
		btnCreateMap.setImage(ButtonImage.BTN_CREATE_MAP);

		btnHighScore = new Button(new Point(100, 300));
		btnHighScore.setImage(ButtonImage.BTN_HIGH_SCORE);

		btnAbout = new Button(new Point(100, 400));
		btnAbout.setImage(ButtonImage.BTN_ABOUT);

		btnHelp = new Button(new Point(100, 500));
		btnHelp.setImage(ButtonImage.BTN_HELP);

		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.BG);
		layer.addDrawable(btnAbout);
		layer.addDrawable(btnCreateMap);
		layer.addDrawable(btnHelp);
		layer.addDrawable(btnHighScore);
		layer.addDrawable(btnStart);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		Button clicked = (Button) layer.getClickedObj(new Point(arg0.getX(),
				arg0.getY()));

		if (clicked == btnStart) {
			final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(ConversionFunction
					.getCurrentDirectory() + "custom map//"));
			int returnVal = fc.showOpenDialog(parent);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				ModelMap map = ModelMap.createMap(fc.getSelectedFile()
						.getAbsolutePath());
				if (map == null)
					JOptionPane.showMessageDialog(null, "Error");
				else
					changeState(new PlayState(parent, this, map, 100));
			}

		} else if (clicked == btnCreateMap) {
			changeState(new MapCreation(parent, this));
		} else if (clicked == btnHighScore) {
			// TODO
		} else if (clicked == btnHelp) {
			// TODO
		} else if (clicked == btnAbout) {
			// TODO
		}

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameRender(Graphics g) {
		layer.render();
		g.drawImage(layer.getLayer(), 0, 0, null);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
