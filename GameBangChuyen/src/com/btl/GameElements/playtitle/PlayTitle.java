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

public class PlayTitle extends GameState {

	Button bnStart;
	public PlayTitle(GamePanel parent) {
		super(parent);

		bnStart = new Button(new Point(200, 200));
		bnStart.setImage(ButtonImage.START_BUTTON, 200, 100);
	}

	@Override
	public void gameRender(Graphics g) {
		bnStart.paint(g);

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

		if (bnStart.contains(new Point(arg0.getX(), arg0.getY()))) {
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
					parent.setState(new PlayState(parent, map));
			}

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
