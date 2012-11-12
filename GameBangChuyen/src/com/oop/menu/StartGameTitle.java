package com.oop.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.oop.data.ButtonImage;
import com.oop.data.OtherImage;
import com.oop.data.SoundEffect;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.gamepanel.Layer;
import com.oop.model.Helper;
import com.oop.model.ModelMap;
import com.oop.play.PlayState;

/**
 * The Class StartGameTitle.
 */
public class StartGameTitle extends GameState {

	private Button btnCampaign, btnCustom, btnBack;
	private Layer layer;

	/**
	 * Instantiates a new start game title.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 */
	public StartGameTitle(GamePanel parent, GameState lastState) {
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
	public void mousePressed(MouseEvent e) {
		Button clicked = (Button) layer.getClickedObj(new Point(e.getX(), e
				.getY()));
		if (clicked != null) {
			SoundEffect.BUTTONCLICK.play();

			if (clicked == btnBack) {
				changeState(lastState);
			} else if (clicked == btnCampaign) {
				changeState(new MapSelect(parent, this));
			} else if (clicked == btnCustom) {
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(Helper.getCurrentDirectory()
						+ "custom map//"));
				int returnVal = fc.showOpenDialog(parent);

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					ModelMap map = ModelMap.createMap(fc.getSelectedFile()
							.getAbsolutePath());
					if (map == null)
						JOptionPane.showMessageDialog(null, "Error");
					else
						changeState(new PlayState(parent, this, map));
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
	 * @see com.oop.gamepanel.GameState#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	private void initialize() {
		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.TITLE_BG);

		btnCampaign = new Button(new Point(245, 250));
		btnCampaign.setImage(ButtonImage.BTN_CAMPAIGN);
		btnCustom = new Button(new Point(245, 300));
		btnCustom.setImage(ButtonImage.BTN_CUSTOM_GAME);
		btnBack = new Button(new Point(245, 350));
		btnBack.setImage(ButtonImage.BTN_BACK);

		layer.addDrawable(btnBack);
		layer.addDrawable(btnCampaign);
		layer.addDrawable(btnCustom);

	}

}
