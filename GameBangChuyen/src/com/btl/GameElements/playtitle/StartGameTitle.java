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
import com.btl.GameEngine.Layer;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelMap;
import com.btl.data.ButtonImage;
import com.btl.data.OtherImage;
import com.btl.data.SoundEffect;

public class StartGameTitle extends GameState {

	private Button btnCampaign, btnCustom, btnBack;
	private Layer layer;

	public StartGameTitle(GamePanel parent, GameState lastState) {
		super(parent, lastState);
		initialize();
	}

	private void initialize() {
		layer = new Layer(parent.width, parent.height);
		layer.setBackground(OtherImage.BG);

		btnCampaign = new Button(new Point(245, 100));
		btnCampaign.setImage(ButtonImage.BTN_CAMPAIGN);
		btnCustom = new Button(new Point(245, 160));
		btnCustom.setImage(ButtonImage.BTN_CUSTOM_GAME);
		btnBack = new Button(new Point(245, 220));
		btnBack.setImage(ButtonImage.BTN_BACK);

		layer.addDrawable(btnBack);
		layer.addDrawable(btnCampaign);
		layer.addDrawable(btnCustom);

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
				fc.setCurrentDirectory(new File(ConversionFunction
						.getCurrentDirectory() + "custom map//"));
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
