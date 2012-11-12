package com.oop.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.oop.data.ButtonImage;
import com.oop.gamepanel.Button;
import com.oop.gamepanel.GamePanel;
import com.oop.gamepanel.GameState;
import com.oop.model.Helper;

/**
 * The Class About.
 */
public class About extends GameState {

	private Button backButton;
	private JScrollPane aboutScroll;
	private JTextArea aboutContent;

	/**
	 * Instantiates a new about.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 * @param name
	 *            the name
	 */
	public About(GamePanel parent, GameState lastState, String name) {
		super(parent, lastState);
		backButton = new Button(new Point(0, 0));
		backButton.setImage(ButtonImage.BACK_BUTTON_IMAGE, 40, 40);

		parent.setLayout(null);
		String fileName = Helper.getCurrentDirectory();
		fileName += "\\" + name;

		String about = null;
		try {
			about = readFile(fileName);
		} catch (Exception e) {
			about = "Lỗi";
		}
		Font f = new Font("UTF8", Font.PLAIN, 14);
		aboutContent = new JTextArea();
		aboutContent.setFont(f);

		aboutContent.setEditable(false);
		aboutContent.setText(about);
		aboutScroll = new JScrollPane(aboutContent);
		aboutScroll.setBounds(0, 100, parent.width, parent.height);

		aboutScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		aboutScroll.setVisible(true);
		parent.add(aboutScroll);

		parent.revalidate();
	}
	/**
	 * đọc lội dung của file about ra
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String readFile(String fileName) throws IOException {
		String result = "";
		try {
			File fileDir = new File(fileName);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileDir), "UTF8"));

			String strLine;
			while ((strLine = in.readLine()) != null) {
				result += "\n" + strLine;
			}

			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (backButton.contains(new Point(e.getX(), e.getY()))) {
			changeState(lastState);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.GameState#gameRender(java.awt.Graphics)
	 */
	@Override
	public void gameRender(Graphics g) {
		backButton.paint(g);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.GameState#changeState(com.oop.gamepanel.GameState)
	 */
	@Override
	public void changeState(GameState state) {
		parent.remove(aboutScroll);
		parent.revalidate();
		parent.setState(state);

	}
}
