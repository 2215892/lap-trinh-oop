package com.btl.GameBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.btl.GameElements.PlayState;
import com.btl.Model.ModelMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	private JFrame frame;
	private GamePanel panel;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new GamePanel();

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		ModelMap map = ModelMap
				.createMap("E:\\Working project\\OOP\\bangchuyen.txt");
		if (map == null)
			JOptionPane.showMessageDialog(frame, "Error");
		else
			panel.setState(new PlayState(panel, map));

	}
}
