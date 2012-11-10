package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.playstate.Button;
import com.btl.GameElements.playstate.PlayState;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;
import com.btl.data.ButtonImage;
import com.btl.data.SaveFile;

public class PlayTitle extends GameState {

	Button bnStart;
	Button bnTest;
	SaveFile save = SaveFile.create();

	public PlayTitle(GamePanel parent) {
		super(parent);
		System.gc();

		bnStart = new Button(new Point(200, 200));
		bnStart.setImage(ButtonImage.BTN_START_GAME, 200, 100);
		bnTest = new Button(new Point(10, 10));
		bnTest.setImage(ButtonImage.BTN_END_GAME, 200, 100);
	}

	@Override
	public void gameRender(Graphics g) {
		bnStart.paint(g);
		bnTest.paint(g);

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {

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
		Point c = new Point(arg0.getX(), arg0.getY());
		if (bnStart.contains(c)) {
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
					parent.setState(new PlayState(parent, map, 100));
			}

		} else if (bnTest.contains(c)) {
			save.setHighscore(2, 3000);
			save.save();
		}

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
