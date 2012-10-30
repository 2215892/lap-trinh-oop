package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.playstate.Button;
import com.btl.GameElements.playstate.PlayState;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;

public class PlayTitle extends GameState {

	Button bnStart;
	public PlayTitle(GamePanel parent) {
		super(parent);

		bnStart = new Button(new Point(200, 200));
		bnStart.setImage(ConversionFunction
				.loadImage("E:\\Working project\\OOP\\res\\TESTSTART.png"),
				200, 100);
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
			ModelMap map = ModelMap
					.createMap("E:\\Working project\\OOP\\testBangchuyen2.xml");
			if (map == null)
				JOptionPane.showMessageDialog(null, "Error");
			else
				parent.setState(new PlayState(parent, map));
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

	@Override
	public void gameRender(Graphics g) {
		bnStart.paint(g);

	}

}
