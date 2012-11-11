package com.btl.GameElements.playtitle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.mapstate.MapCreation;
import com.btl.GameElements.playstate.Button;
import com.btl.GameEngine.Layer;
import com.btl.data.ButtonImage;
import com.btl.data.OtherImage;
import com.btl.data.SoundEffect;

public class GameTitle extends GameState {

	private Button btnStart, btnCreateMap, btnHighScore, btnAbout, btnHelp;
	private Layer layer;

	public GameTitle(GamePanel parent, GameState lastState) {
		super(parent, lastState);
		initialize();
	}

	private void initialize() {

		btnStart = new Button(new Point(245, 100));
		btnStart.setImage(ButtonImage.BTN_START_GAME);

		btnCreateMap = new Button(new Point(245, 160));
		btnCreateMap.setImage(ButtonImage.BTN_CREATE_MAP);

		btnHighScore = new Button(new Point(245, 220));
		btnHighScore.setImage(ButtonImage.BTN_HIGH_SCORE);

		btnAbout = new Button(new Point(245, 280));
		btnAbout.setImage(ButtonImage.BTN_ABOUT);

		btnHelp = new Button(new Point(245, 340));
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

		if (clicked != null) {

			SoundEffect.BUTTONCLICK.play();

			if (clicked == btnStart) {
				changeState(new StartGameTitle(parent, this));

		} else if (clicked == btnCreateMap) {
			changeState(new MapCreation(parent, this));
		} else if (clicked == btnHighScore) {
			// TODO
		} else if (clicked == btnHelp) {
			changeState(new About(parent,this,"help.txt"));
		} else if (clicked == btnAbout) {
				changeState(new About(parent,this,"about.txt"));

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
